package org.cmms.modules.report.zbgl.zbjg.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.report.zbgl.zbjg.entity.Zbjg;
import org.cmms.modules.report.zbgl.zbjg.mapper.ZbjgMapper;
import org.cmms.modules.report.zbgl.zbjg.service.IZbjgService;
import org.cmms.modules.report.zbgl.zbsjx.entity.Zbsjxgl;
import org.cmms.modules.report.zbgl.zbtqrzcx.entity.RepIndexExecuteLog;
import org.cmms.modules.report.zbgl.zbtqrzcx.service.IRepIndexExecuteLogService;
import org.cmms.modules.util.EtlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 指标结果
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Slf4j
@Service
public class ZbjgServiceImpl extends ServiceImpl<ZbjgMapper, Zbjg> implements IZbjgService {

    @Autowired
    private IRepIndexExecuteLogService RepIndexExecuteLogService;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Double dynamicSql(String sql) {
        return baseMapper.dynamicSql(sql);
    }

    @Override
    public long dynamicDwSql(String sql) {
        return baseMapper.dynamicDwSql(sql);
    }

    @Override
    public void dynamicCallSql(String sql) {
         baseMapper.dynamicCallSql(sql);
    }

    @Override
    public String executePcSql(List<Zbsjxgl> sqlList, Date sjrq){
        Date dateEtime = new Date();
        Date endDate = new Date();
        List<RepIndexExecuteLog> logList=new ArrayList<>();
        List<Zbjg> zbjgList=new ArrayList<>();
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        for(Zbsjxgl sql :sqlList){
            try {
                dateEtime=new Date();
                Double sjxjg =dynamicSql(sql.getJssql());
                Zbjg zbjg=new Zbjg();
                zbjg.setSjrq(sjrq);
                zbjg.setZbid(sql.getZbid());
                zbjg.setZbmc(sql.getZbmc());
                zbjg.setZbwd(sql.getZbwd());
                zbjg.setZblx(Integer.parseInt(sql.getZblx()));
                zbjg.setJgdm(qydm);
                zbjg.setZbjg(sjxjg==null?new BigDecimal(0):new BigDecimal(sjxjg).setScale(3,BigDecimal.ROUND_HALF_UP));
                zbjgList.add(zbjg);
                RepIndexExecuteLog logs =new RepIndexExecuteLog();
                logs.setSjrq(sjrq);
                logs.setZbid(sql.getZbid());
                logs.setZbmc(sql.getZbmc());
                logs.setZbwd(sql.getZbwd());
                logs.setZblx(sql.getZblx());
                logs.setJssql(sql.getJssql());
                logs.setSjxjg(sjxjg==null?new BigDecimal(0):new BigDecimal(sjxjg).setScale(3,BigDecimal.ROUND_HALF_UP));
                logs.setEtime(dateEtime);
                long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                logs.setUsetime((int) zxhs);
                logs.setEinfo("执行成功");
                logs.setEstat("1");
                logList.add(logs);
                endDate= new Date();
                log.info("do executePcSql: {}", sql.getZbmc()+":"+sql.getJssql()+"=="+sjxjg);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("do executePcSql: {}", e.getMessage());

                try {
                    RepIndexExecuteLog logs =new RepIndexExecuteLog();
                    logs.setSjrq(sjrq);
                    logs.setZbid(sql.getZbid());
                    logs.setZbmc(sql.getZbmc());
                    logs.setZbwd(sql.getZbwd());
                    logs.setZblx(sql.getZblx());
                    logs.setJssql(sql.getJssql());
                    logs.setEtime(dateEtime);
                    long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                    logs.setUsetime((int) zxhs);
                    int length=0;
                    if(e.getMessage().length()>=4000){
                        length=3999;
                    }else{
                        length=e.getMessage().length();
                    }
                    logs.setEinfo(e.getMessage().substring(0,length));
                    logs.setEstat("2");
                    logList.add(logs);
                }catch (Exception e1){
                    log.error("do add logs: {}", e.getMessage());
                }

            }
        }
        try {
            this.saveBatch(zbjgList);
        }catch (Exception e){
            e.printStackTrace();
            log.error("------------------保存指标结果失败-------------", e);
        }
        try {
            RepIndexExecuteLogService.saveBatch(logList);
        }catch (Exception e){
            e.printStackTrace();
            log.error("------------------保存指标数据提取日志失败-------------", e);
        }

        return "do executePcSql: " + sqlList.size()+",成功数："+zbjgList.size();
    }

    @Override
    public String executeDwzbSql(List<Zbsjxgl> sqlList, Date sjrq,String sfdsjpt){

        if(sfdsjpt.equals("true")){
            Date dateEtime = new Date();
            Date endDate = new Date();
            List<RepIndexExecuteLog> logList=new ArrayList<>();
            for(Zbsjxgl sql :sqlList){
                try {
                    dateEtime=new Date();
                    HashMap<String, String> params = new HashMap<>();
                    String sqlStr=sql.getJssql()==null?"":sql.getJssql();
                    if(sqlStr.indexOf("$ETL")>-1){
                        String etlCode =sqlStr.substring(sqlStr.indexOf("[")+1,sqlStr.indexOf("]"));
                        String etlParam=sqlStr.substring(sqlStr.indexOf("(")+1,sqlStr.indexOf(")"));
                        String[] split = etlParam.split(",");
                        for(String paramStr:split){
                            String[] paramStrs = paramStr.split(":");
                            params.put(paramStrs[0], paramStrs[1]);
                        }
                        params.put("etl_task", etlCode);
                        log.info("ETLCode <==> "+etlCode);
                        log.info("etlParam <==> "+etlParam);
                        log.info("ETL调度开始 <==> " + new Date().getTime());
                        long start = System.currentTimeMillis();
                        boolean completion = EtlUtil.callEtl("tjbb_common_init", params, 30);
                        log.info("ETL调度耗时 <==> " + (System.currentTimeMillis() - start) + "毫秒");
                        log.info("ETL调度结束 <==> " + new Date().getTime());
                        log.info("ETL调度是否完成？ ==> " + completion);
                    }else{
                        try {
                            if(StringUtils.isNotEmpty(sqlStr)){
                                dynamicDwSql(sqlStr);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                            log.error("------------------1.多维指标执行sql失败-------------:"+sqlStr, e);
                        }
                    }
                    RepIndexExecuteLog logs =new RepIndexExecuteLog();
                    logs.setSjrq(sjrq);
                    logs.setZbid(sql.getZbid());
                    logs.setZbmc(sql.getZbmc());
                    logs.setZbwd(sql.getZbwd());
                    logs.setZblx(sql.getZblx());
                    logs.setJssql(sql.getJssql());
                    logs.setEtime(dateEtime);
                    endDate= new Date();
                    long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                    logs.setUsetime((int) zxhs);
                    logs.setEinfo("执行成功");
                    logs.setEstat("1");
                    logList.add(logs);
                    log.info("do executeDwzbSql: {}", sql.getZbmc()+":"+sql.getJssql());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("do executeDwzbSql: {}", e.getMessage());
                    RepIndexExecuteLog logs =new RepIndexExecuteLog();
                    logs.setSjrq(sjrq);
                    logs.setZbid(sql.getZbid());
                    logs.setZbmc(sql.getZbmc());
                    logs.setZbwd(sql.getZbwd());
                    logs.setZblx(sql.getZblx());
                    logs.setJssql(sql.getJssql());
                    logs.setEtime(dateEtime);
                    endDate= new Date();
                    long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                    logs.setUsetime((int) zxhs);
                    int length=0;
                    if(e.getMessage().length()>=4000){
                        length=3999;
                    }else{
                        length=e.getMessage().length();
                    }
                    logs.setEinfo(e.getMessage().substring(0,length));
                    logs.setEstat("2");
                    logList.add(logs);
                }
            }
            try {
                RepIndexExecuteLogService.saveBatch(logList);
            }catch (Exception e){
                e.printStackTrace();
                log.error("------------------多维指标1-保存指标数据提取日志失败-------------", e);
            }
        }else{
            Date dateEtime = new Date();
            Date endDate = new Date();
            List<RepIndexExecuteLog> logList=new ArrayList<>();
            for(Zbsjxgl sql :sqlList){
                try {
                    dateEtime=new Date();
                    try {
                        if(StringUtils.isNotEmpty(sql.getJssql())&&sql.getJssql().toUpperCase().indexOf("PKG_")>-1){
                            dynamicCallSql(sql.getJssql());
                        }else{
                            if(StringUtils.isNotEmpty(sql.getJssql())){
                                dynamicDwSql(sql.getJssql());
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        log.error("------------------2.多维指标执行提取call失败-------------:"+sql.getJssql(), e);
                    }
                    RepIndexExecuteLog logs =new RepIndexExecuteLog();
                    logs.setSjrq(sjrq);
                    logs.setZbid(sql.getZbid());
                    logs.setZbmc(sql.getZbmc());
                    logs.setZbwd(sql.getZbwd());
                    logs.setZblx(sql.getZblx());
                    logs.setJssql(sql.getJssql());
                    logs.setEtime(dateEtime);
                    endDate= new Date();
                    long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                    logs.setUsetime((int) zxhs);
                    logs.setEinfo("执行成功");
                    logs.setEstat("1");
                    logList.add(logs);
                    log.info("do executeDwzbSql: {}", sql.getZbmc()+":"+sql.getJssql());
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("do executeDwzbSql: {}", e.getMessage());
                    RepIndexExecuteLog logs =new RepIndexExecuteLog();
                    logs.setSjrq(sjrq);
                    logs.setZbid(sql.getZbid());
                    logs.setZbmc(sql.getZbmc());
                    logs.setZbwd(sql.getZbwd());
                    logs.setZblx(sql.getZblx());
                    logs.setJssql(sql.getJssql());
                    logs.setEtime(dateEtime);
                    endDate= new Date();
                    long zxhs=(endDate.getTime()-dateEtime.getTime())/1000;
                    logs.setUsetime((int) zxhs);
                    int length=0;
                    if(e.getMessage().length()>=4000){
                        length=3999;
                    }else{
                        length=e.getMessage().length();
                    }
                    logs.setEinfo(e.getMessage().substring(0,length));
                    logs.setEstat("2");
                    logList.add(logs);
                }
            }
            try {
                RepIndexExecuteLogService.saveBatch(logList);
            }catch (Exception e){
                e.printStackTrace();
                log.error("------------------多维指标2-保存指标数据提取日志失败-------------", e);
            }
        }
        return "do executePcSql: " + sqlList.size();
    }
}

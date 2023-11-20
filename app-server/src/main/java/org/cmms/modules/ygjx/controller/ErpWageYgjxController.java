package org.cmms.modules.ygjx.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.appbase.datatime.service.IBasDataJobDaysService;
import org.cmms.modules.gr.grjxsj.entity.ErpWageYgjbgzglYx;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.khjg.yggzhz.entity.Yggzhz;
import org.cmms.modules.khjg.yggzhz.service.IYggzhzService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysRoleService;
import org.cmms.modules.ygjx.entity.*;
import org.cmms.modules.ygjx.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 员工绩效合计
 * @Author: jeecg-boot
 * @Date: 2022-02-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "员工绩效合计")
@RestController
@RequestMapping("/ygjx/erpWageYgjx")
public class ErpWageYgjxController extends JeecgController<ErpWageYgjx, IErpWageYgjxService> {
    @Autowired
    private IErpWageYgjxService erpWageYgjxService;
    @Autowired
    private IYggzhzService yggzhzService;
    @Autowired
    IBasicWageAllowabcesService iBasicWageAllowabcesService;
    @Autowired
    ISysDictService sysDictService;
    @Autowired
    IHrBasOrganizationService hrBasOrganizationService;
    @Autowired
    ISysRoleService sysRoleService;
    @Autowired
    IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    ISysDicService sysDicService;
    @Autowired
    IErpWageJbgzglClService erpWageJbgzglClService;
    @Autowired
    IErpWageJbgzglService erpWageJbgzglService;
    @Autowired
    IErpWageYgjxMxService erpWageYgjxMxService;
    @Autowired
    IPrTbYgjbgzmxbService prTbYgjbgzmxbService;
    @Autowired
    IErpBfgzService erpBfgzService;
    @Autowired
    IErpBkgzService erpBkgzService;
    @Autowired
    IErpTdjxService erpTdjxService;
    @Autowired
    IErpZxkhService erpZxkhService;
    @Autowired
    IErpQtxcService erpQtxcService;
    @Autowired
    IHrBasPostService hrBasPostService;
    @Autowired
    IErpWageJbgz140Service erpWageJbgz140Service;
    public static String ZDTGLSGLSBUSINESSINFO = "ZDTGLSGLSBUSINESSINFO";
    public static String ZDCBSBORMBASE = "ZDCBSBORMBASE";
    public static String ZDCBSINVMBASE = "ZDCBSINVMBASE";


    /**
     * 分页列表查询
     *
     * @param erpWageYgjx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "员工绩效合计-分页列表查询")
    @ApiOperation(value = "员工绩效合计-分页列表查询", notes = "员工绩效合计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(ErpWageYgjx erpWageYgjx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<ErpWageYgjx> queryWrapper = QueryGenerator.initQueryWrapper(erpWageYgjx, req.getParameterMap());
        Page<ErpWageYgjx> page = new Page<ErpWageYgjx>(pageNo, pageSize);
        IPage<ErpWageYgjx> pageList = erpWageYgjxService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * @param date 格式 yyyy-MM-dd
     */
    @GetMapping("/getXchj")
    public Result<?> getXchj(String date) {
        String maxDate = service.getMaxDate(getWorkNo());
        if (StringUtils.isBlank(maxDate))
            maxDate = DateUtil.today();
        DateTime parse = DateUtil.parse(maxDate);

        DateTime dqycrq = DateUtil.beginOfMonth(parse);

        //月除和月末日期字符串 获取基本工资
        String yc="";
        String ym="";

        //第一次进来 默认选择当天       当选择日期 《 当前日期时 直接查历史数据
        boolean flag = true;
        if (date != null) {
            date = date.replace("/","-");
            if (date.length() > 7){
                parse = DateUtil.parse(date);
            }else {
                parse = DateUtil.parse(date,"yyyy-MM");
                DateTime dateTime = DateUtil.endOfMonth(parse);
                DateTime dateTime1 = DateUtil.beginOfMonth(parse);
                if (dateTime1.compareTo(dqycrq) == -1){
                    flag =false;
                }

                yc = DateUtil.format(dateTime1,"yyyyMMdd");
                ym = DateUtil.format(dateTime,"yyyyMMdd");
                String format = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN);
                parse = DateUtil.parse(format);
                //parse = dateTime;
            }

        }
        DateTime dateTime = DateUtil.beginOfMonth(parse);
        DateTime dateTimeym = DateUtil.endOfMonth(parse);
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-", "");

        XchjVO xchjVO = new XchjVO();
        if (StringUtils.isNotBlank(date)){
            xchjVO.setGzrq(date.replace("-", "/"));
        }else {
            xchjVO.setGzrq(DateUtil.format(parse,"yyyy/MM/dd"));
        }
        LoginUser loginUser = getLoginUser();
        String yyyyMMddStr = DateUtil.format(parse, "yyyyMMdd");
        String yyyyMMddStrym = DateUtil.format(dateTimeym, "yyyyMMdd");
        log.info("===yyyyMMddStr||{}===",yyyyMMddStr);
        log.info("===yyyyMMddStrym||{}===",yyyyMMddStrym);
        log.info("===yc||{}===",yc);
        log.info("===ym||{}===",ym);
        log.info("===flag||{}===",flag);

        //绩效工资
        if (QydmEnums.JIANGHUA.getQydmCode().equals(getRedisQydm()) && flag){
            BigDecimal bigDecimal = new BigDecimal("0");
            List<String> zbids = service.zbids(getWorkNo(), yyyyMMddStr);
            if (CollUtil.isNotEmpty(zbids) && zbids.contains("D90060")) {
                //需要特殊处理D90060的工资
                LambdaQueryWrapper<ErpWageYgjxMx> lambdaQueryWrapper = new LambdaQueryWrapper<>();
                lambdaQueryWrapper.eq(ErpWageYgjxMx::getZbid, "D90060");
                lambdaQueryWrapper.eq(ErpWageYgjxMx::getYggh, getWorkNo());
                lambdaQueryWrapper.gt(ErpWageYgjxMx::getZbgz, 0);
                lambdaQueryWrapper.orderByDesc(ErpWageYgjxMx::getGzrq);
                List<ErpWageYgjxMx> list = erpWageYgjxMxService.list(lambdaQueryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    ErpWageYgjxMx erpWageYgjxMx = list.get(0);
                    Date gzrq = erpWageYgjxMx.getGzrq();
                    String gzstring = DateUtil.format(gzrq, "yyyyMMdd");
                    if (!gzstring.equals(yyyyMMddStr)) {
                        //加上工资
                        bigDecimal = erpWageYgjxMx.getZbgz();
                    }
                }
            }
            BigDecimal yyyyMMdd = erpWageYgjxService.getJhJxgz(getWorkNo(), yyyyMMddStr);
            if (yyyyMMdd != null){
                BigDecimal add = yyyyMMdd.add(bigDecimal);
                xchjVO.setJxxc(add);
            }else {
                xchjVO.setJxxc(bigDecimal);
            }
        } else if(QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())){
            //绩效3.0版本 直接查询Erp_wage_yggzhz汇总表的数据
            LambdaQueryWrapper<Yggzhz> yggzhzLambdaQueryWrapper = new LambdaQueryWrapper<>();
            yggzhzLambdaQueryWrapper.eq(Yggzhz::getYggh, getWorkNo());
            yggzhzLambdaQueryWrapper.eq(Yggzhz::getGzrq, parse);
            List<Yggzhz> list = yggzhzService.list(yggzhzLambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                BigDecimal bigDecimal = new BigDecimal(0);
                for (int i = 0; i < list.size(); i++) {
                    Yggzhz yggzhz = list.get(i);
                    if (yggzhz.getJxgzhj() != null) {
                        bigDecimal = bigDecimal.add(yggzhz.getJxgzhj());
                    }
                }
                xchjVO.setJxxc(bigDecimal);
            }
        } else {
            LambdaQueryWrapper<ErpWageYgjx> erpWageYgjxLambdaQueryWrapper = new LambdaQueryWrapper<>();
            erpWageYgjxLambdaQueryWrapper.eq(ErpWageYgjx::getYggh, getWorkNo());
            erpWageYgjxLambdaQueryWrapper.eq(ErpWageYgjx::getGzrq, parse);
            List<ErpWageYgjx> list = erpWageYgjxService.list(erpWageYgjxLambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)) {
                BigDecimal bigDecimal = new BigDecimal(0);
                for (int i = 0; i < list.size(); i++) {
                    ErpWageYgjx erpWageYgjx = list.get(i);
                    if (erpWageYgjx.getGzhj() != null) {
                        bigDecimal = bigDecimal.add(erpWageYgjx.getGzhj());
                    }
                }
                xchjVO.setJxxc(bigDecimal);
            }
        }

        BigDecimal jbgz = new BigDecimal(0);
        //基本工资
        if (QydmEnums.JIANGHUA.getQydmCode().equals(getRedisQydm())){
            String maxJbgz = null;
            if (StringUtils.isNotBlank(yc)){
                maxJbgz = service.getMaxJbgzAndDate(getWorkNo(),yc,ym);
            }else {

                //maxJbgz = service.getMaxJbgz(getWorkNo());
                maxJbgz = service.getMaxJbgzAndDate(getWorkNo(),yyyyMMddStr,yyyyMMddStrym);
            }
            if (StringUtils.isNotBlank(maxJbgz))
                jbgz = new BigDecimal(maxJbgz);
        }else if(QydmEnums.CILI.getQydmCode().equals(getRedisQydm())){
            //取每个月初
            DateTime dateTime1 = DateUtil.beginOfMonth(parse);
            LambdaQueryWrapper<ErpWageJbgzglCl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ErpWageJbgzglCl::getGzyf,dateTime1);
            lambdaQueryWrapper.eq(ErpWageJbgzglCl::getYggh,getWorkNo());
            lambdaQueryWrapper.orderByDesc(ErpWageJbgzglCl::getGzyf);
            List<ErpWageJbgzglCl> list1 = erpWageJbgzglClService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list1)){
                ErpWageJbgzglCl erpWageJbgzglCl = list1.get(0);
                if (erpWageJbgzglCl.getYfhj() != null){
                    jbgz = erpWageJbgzglCl.getYfhj();
                }
            }
        }else if(QydmEnums.LIANYUAN.getQydmCode().equals(getRedisQydm())){
            DateTime dateTime1 = DateUtil.beginOfMonth(parse);
            LambdaQueryWrapper<ErpWageJbgzgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ErpWageJbgzgl::getGzyf,dateTime1);
            lambdaQueryWrapper.eq(ErpWageJbgzgl::getYggh,getWorkNo());
            lambdaQueryWrapper.orderByDesc(ErpWageJbgzgl::getGzyf);
            List<ErpWageJbgzgl> list1 = erpWageJbgzglService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list1)){
                ErpWageJbgzgl erpWageJbgzgl = list1.get(0);
                if (erpWageJbgzgl.getNetAmount() != null){
                    jbgz = erpWageJbgzgl.getNetAmount();
                }
            }
        } else if (QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())){
            BigDecimal zjjjbgz = new BigDecimal("0");
            //张家界基本工资
            DateTime dateTime1 = DateUtil.beginOfMonth(parse);
            LambdaQueryWrapper<PrTbYgjbgzmxb> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(PrTbYgjbgzmxb::getTjyf,dateTime1);
            lambdaQueryWrapper.eq(PrTbYgjbgzmxb::getYggh,getWorkNo());
            lambdaQueryWrapper.orderByDesc(PrTbYgjbgzmxb::getTjyf);
            List<PrTbYgjbgzmxb> prTbYgjbgzmxbs = prTbYgjbgzmxbService.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(prTbYgjbgzmxbs)){
                PrTbYgjbgzmxb prTbYgjbgzmxb = prTbYgjbgzmxbs.get(0);
                if (prTbYgjbgzmxb.getGwgz() != null)
                    zjjjbgz = zjjjbgz.add(prTbYgjbgzmxb.getGwgz());
                //zcjt+ngjt+wsf+tsgwjt+tsdqjt+ldrznxjt jtxj,--津贴小计
                if (prTbYgjbgzmxb.getZcjt() != null && prTbYgjbgzmxb.getNgjt() != null && prTbYgjbgzmxb.getWsf() != null &&
                        prTbYgjbgzmxb.getTsgwjt() != null && prTbYgjbgzmxb.getTsdqjt() != null && prTbYgjbgzmxb.getLdrznxjt() != null)
                    zjjjbgz = zjjjbgz.add(prTbYgjbgzmxb.getZcjt()).add(prTbYgjbgzmxb.getNgjt()).add(prTbYgjbgzmxb.getWsf().add(prTbYgjbgzmxb.getTsgwjt()).add(prTbYgjbgzmxb.getTsdqjt()).add(prTbYgjbgzmxb.getLdrznxjt()));
                //gjj+ylj+sybx+ylbx+qynj+NVL(gjjKB,0)+NVL(yljKB,0)+NVL(sybxKB,0)+NVL(ylbxKB,0)+NVL(Qynjkb,0) kcxj,--扣除小计
                if (prTbYgjbgzmxb.getGjjkb() == null)
                    prTbYgjbgzmxb.setGjjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getSybxkb() == null)
                    prTbYgjbgzmxb.setSybxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getQynjkb() == null)
                    prTbYgjbgzmxb.setQynjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYljkb() == null)
                    prTbYgjbgzmxb.setYljkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getGjj() != null && prTbYgjbgzmxb.getYlj() != null && prTbYgjbgzmxb.getSybx() != null && prTbYgjbgzmxb.getYlbx() != null&& prTbYgjbgzmxb.getQynjkb() != null&&
                        prTbYgjbgzmxb.getQynj() != null && prTbYgjbgzmxb.getGjjkb() != null && prTbYgjbgzmxb.getYljkb() != null && prTbYgjbgzmxb.getSybxkb() != null && prTbYgjbgzmxb.getYlbxkb() != null){
                    zjjjbgz = zjjjbgz.subtract(prTbYgjbgzmxb.getGjj()).subtract(prTbYgjbgzmxb.getYlj()).subtract(prTbYgjbgzmxb.getSybx()).subtract(prTbYgjbgzmxb.getYlbx()).subtract(prTbYgjbgzmxb.getSybxkb())
                            .subtract(prTbYgjbgzmxb.getQynjkb()).subtract(prTbYgjbgzmxb.getQynj()).subtract(prTbYgjbgzmxb.getGjjkb()).subtract(prTbYgjbgzmxb.getYljkb()).subtract(prTbYgjbgzmxb.getYlbxkb());
                }

                //todo +补发工资 - 补扣工资
            }
            LambdaQueryWrapper<ErpBfgz> lambdaQueryWrapper2 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper2.eq(ErpBfgz::getTjyf,dateTime1);
            lambdaQueryWrapper2.eq(ErpBfgz::getYggh,getWorkNo());
            lambdaQueryWrapper2.orderByDesc(ErpBfgz::getTjyf);
            List<ErpBfgz> erpBfgzs = erpBfgzService.list(lambdaQueryWrapper2);
            if (CollUtil.isNotEmpty(erpBfgzs)){
                ErpBfgz erpBfgz = erpBfgzs.get(0);
                //加上补发工资
                if (erpBfgz.getBfgz() != null){
                    zjjjbgz = zjjjbgz.add(erpBfgz.getBfgz());
                }
            }
            LambdaQueryWrapper<ErpBkgz> lambdaQueryWrapper3 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper3.eq(ErpBkgz::getTjyf,dateTime1);
            lambdaQueryWrapper3.eq(ErpBkgz::getYggh,getWorkNo());
            lambdaQueryWrapper3.orderByDesc(ErpBkgz::getTjyf);
            List<ErpBkgz> erpBkgzs = erpBkgzService.list(lambdaQueryWrapper3);
            if (CollUtil.isNotEmpty(erpBkgzs)){
                ErpBkgz erpBkgz = erpBkgzs.get(0);
                //减去补扣工资
                if (erpBkgz.getBkgz() != null){
                    zjjjbgz = zjjjbgz.subtract(erpBkgz.getBkgz());
                }
            }
            jbgz = zjjjbgz;
            log.info(parse+"工资日期");
            log.info(jbgz+"=========||基本工资");
            String format = DateUtil.format(parse, "yyyy-MM-dd");
            String jc = getJc(format);
            Date parse1 = DateUtil.parse(jc);
            log.info(parse1+"======||季节日期");
            //团队绩效合计
            LambdaQueryWrapper<ErpTdjx> lambdaQueryWrapper4 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper4.eq(ErpTdjx::getTjjd,parse1);
            lambdaQueryWrapper4.eq(ErpTdjx::getYggh,getWorkNo());
            lambdaQueryWrapper4.orderByDesc(ErpTdjx::getTjjd);
            List<ErpTdjx> erpTdjxes = erpTdjxService.list(lambdaQueryWrapper4);
            if (CollUtil.isNotEmpty(erpTdjxes)){
                ErpTdjx erpTdjx = erpTdjxes.get(0);
                if (erpTdjx.getFpje() != null){
                    xchjVO.setTdjx(erpTdjx.getFpje());
                }
            }
            //专项考核合计
            LambdaQueryWrapper<ErpZxkh> lambdaQueryWrapper5 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper5.eq(ErpZxkh::getTjjd,parse1);
            lambdaQueryWrapper5.eq(ErpZxkh::getYggh,getWorkNo());
            lambdaQueryWrapper5.orderByDesc(ErpZxkh::getTjjd);
            List<ErpZxkh> erpZxkhs = erpZxkhService.list(lambdaQueryWrapper5);
            if (CollUtil.isNotEmpty(erpZxkhs)){
                ErpZxkh erpZxkh = erpZxkhs.get(0);
                if (erpZxkh.getZxkhhj() != null){
                    xchjVO.setZxkh(erpZxkh.getZxkhhj());
                }
            }
            //其他薪酬合计
            LambdaQueryWrapper<ErpQtxc> lambdaQueryWrapper6 = new LambdaQueryWrapper<>();
            lambdaQueryWrapper6.eq(ErpQtxc::getTjyf,dateTime1);
            lambdaQueryWrapper6.eq(ErpQtxc::getYggh,getWorkNo());
            lambdaQueryWrapper6.orderByDesc(ErpQtxc::getTjyf);
            List<ErpQtxc> erpQtxcs = erpQtxcService.list(lambdaQueryWrapper6);
            if (CollUtil.isNotEmpty(erpQtxcs)){
                ErpQtxc erpQtxc = erpQtxcs.get(0);
                if (erpQtxc.getQtxchj() != null){
                    xchjVO.setQtfy(erpQtxc.getQtxchj());
                }
            }

        }else if(QydmEnums.QIDONG.getQydmCode().equals(getRedisQydm())){
            LambdaQueryWrapper<ErpWageJbgz140> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(ErpWageJbgz140::getYggh,getWorkNo());
            lambdaQueryWrapper.inSql(ErpWageJbgz140::getGzyf," to_date('"+bMonStr+"','yyyymmdd')");
            List<ErpWageJbgz140> list = erpWageJbgz140Service.list(lambdaQueryWrapper);
            if (CollUtil.isNotEmpty(list)){
                 ErpWageJbgz140 erpWageJbgz140 = list.get(0);
                 BigDecimal bigDecimal = new BigDecimal(0);
                 if (erpWageJbgz140.getJbgzhj()!=null)
                     bigDecimal = bigDecimal.add(erpWageJbgz140.getJbgzhj());
                 if (erpWageJbgz140.getQtgzhj() != null)
                     bigDecimal = bigDecimal.add(erpWageJbgz140.getQtgzhj());
                 if (erpWageJbgz140.getDjdkhj() != null)
                     bigDecimal = bigDecimal.subtract(erpWageJbgz140.getDjdkhj());
                 jbgz = bigDecimal;
            }
        } else {
            jbgz = iBasicWageAllowabcesService.jbgz(loginUser.getUsername(), bMonStr);
        }
        if (jbgz != null)
            xchjVO.setJbxc(jbgz);

        //计算合计工资
        xchjVO.jsXchj();
        return Result.ok(xchjVO);
    }

    /**
     * 获取季度
     * @param yyyyMMdd
     * @return
     */
    public String getJc(String yyyyMMdd) {
        String yy = yyyyMMdd.substring(0, 4);
        String mm = yyyyMMdd.substring(5, 7);
        Integer integer = Integer.valueOf(mm);
        if (integer <= 3) {
            mm = "-01-01";
        } else if (integer > 3 && integer <= 6) {
            mm = "-04-01";
        } else if (integer > 6 && integer <= 9) {
            mm = "-07-01";
        } else if (integer > 9 && integer <= 12) {
            mm = "-10-01";
        }
        return yy + mm;
    }

    /**
     * 张家界基本工资当前月份 数据
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getJbgzByType")
    public Result<?> getJbgzByType(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        System.out.println(date);
        System.out.println(type);
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        if (StringUtils.isNotBlank(date)) {
            if (date.length() <= 7) {
                date = date + "/01";
            }
            date.replaceAll("/", "-");
            DateTime parse = DateUtil.parse(date);
            dateTime = DateUtil.beginOfMonth(parse);
        }else {
            return Result.error("请选择日期");
        }
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-","");
        System.out.println(bMonStr);

        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        //获取登录人岗位
        List<HrBasStaffPostVo> yggw = hrBasPostService.getYggw(loginUser.getWorkNo(),bMonStr);
        Integer gwbz = null;
        if (CollUtil.isNotEmpty(yggw)){
            gwbz = yggw.get(0).getGwbz();
        }

        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zzbz",hrBasOrganization.getZzbz());
        queryWrapper.eq("tjyf",dateTime);
        queryWrapper.orderByDesc("tjyf");
        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("yggh",getWorkNo());
        queryWrapper1.eq("tjyf",dateTime);
        queryWrapper1.orderByDesc("tjyf");
        //b:岗位工资
        if ("b".equals(type)){
            //行长查看当前机构下的数据
            if (gwbz == 100){
                Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
                Page<PrTbYgjbgzmxb> prTbYgjbgzmxbPage = prTbYgjbgzmxbService.page(page, queryWrapper);
                return Result.ok(prTbYgjbgzmxbPage);
            }else {
                Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
                Page<PrTbYgjbgzmxb> gwgzList = prTbYgjbgzmxbService.page(page, queryWrapper1);
                return Result.ok(gwgzList);
            }
        }else if ("c".equals(type)){
            BigDecimal zzjJtxj = new BigDecimal("0");
            List<PrTbYgjbgzmxb> prTbYgjbgzmxbs = prTbYgjbgzmxbService.list(queryWrapper1);
            if (CollUtil.isNotEmpty(prTbYgjbgzmxbs)){
                PrTbYgjbgzmxb prTbYgjbgzmxb = prTbYgjbgzmxbs.get(0);
                if (prTbYgjbgzmxb.getZcjt() != null && prTbYgjbgzmxb.getNgjt() != null && prTbYgjbgzmxb.getWsf() != null &&
                        prTbYgjbgzmxb.getTsgwjt() != null && prTbYgjbgzmxb.getTsdqjt() != null  && prTbYgjbgzmxb.getLdrznxjt() != null){
                    zzjJtxj = zzjJtxj.add(prTbYgjbgzmxb.getZcjt()).add(prTbYgjbgzmxb.getNgjt()).add(prTbYgjbgzmxb.getWsf().add(prTbYgjbgzmxb.getTsdqjt()).add(prTbYgjbgzmxb.getTsgwjt()).add(prTbYgjbgzmxb.getLdrznxjt()));
                }
            }
            Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
            Page<PrTbYgjbgzmxb> pageList = prTbYgjbgzmxbService.page(page, queryWrapper1);
            if (CollUtil.isNotEmpty(pageList.getRecords())){
                pageList.getRecords().get(0).setJbtxj(zzjJtxj);
            }
            return Result.ok(pageList);
        }else if ("d".equals(type)){
            BigDecimal zzjKcxj = new BigDecimal("0");
            List<PrTbYgjbgzmxb> prTbYgjbgzmxbs = prTbYgjbgzmxbService.list(queryWrapper1);
            if (CollUtil.isNotEmpty(prTbYgjbgzmxbs)) {
                PrTbYgjbgzmxb prTbYgjbgzmxb = prTbYgjbgzmxbs.get(0);
                if (prTbYgjbgzmxb.getGjjkb() == null)
                    prTbYgjbgzmxb.setGjjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getSybxkb() == null)
                    prTbYgjbgzmxb.setSybxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getQynjkb() == null)
                    prTbYgjbgzmxb.setQynjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYljkb() == null)
                    prTbYgjbgzmxb.setYljkb(new BigDecimal("0"));

                if (prTbYgjbgzmxb.getGjj() != null && prTbYgjbgzmxb.getYlj() != null && prTbYgjbgzmxb.getSybx() != null && prTbYgjbgzmxb.getYlbx() != null && prTbYgjbgzmxb.getQynjkb() != null &&
                        prTbYgjbgzmxb.getQynj() != null && prTbYgjbgzmxb.getGjjkb() != null && prTbYgjbgzmxb.getYljkb() != null && prTbYgjbgzmxb.getSybxkb() != null && prTbYgjbgzmxb.getYlbxkb() != null) {
                    zzjKcxj = zzjKcxj.add(prTbYgjbgzmxb.getGjj()).add(prTbYgjbgzmxb.getYlj()).add(prTbYgjbgzmxb.getSybx()).add(prTbYgjbgzmxb.getYlbx())
                            .add(prTbYgjbgzmxb.getQynjkb()).add(prTbYgjbgzmxb.getQynj()).add(prTbYgjbgzmxb.getGjjkb()).add(prTbYgjbgzmxb.getYljkb()).add(prTbYgjbgzmxb.getSybxkb()).add(prTbYgjbgzmxb.getYlbxkb());
                    System.out.println(zzjKcxj);
                }
            }
            log.info(zzjKcxj+"=======zzjKcxj==========");
            Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
            Page<PrTbYgjbgzmxb> pageList = prTbYgjbgzmxbService.page(page, queryWrapper1);
            if (CollUtil.isNotEmpty(pageList.getRecords())){
                pageList.getRecords().get(0).setKcxj2(zzjKcxj);
            }else {
                return Result.error("未找到数据");
            }
            return Result.ok(pageList);
        }else if ("e".equals(type)){
            Page<ErpBfgz> page = new Page<ErpBfgz>(pageNo, pageSize);
            Page<ErpBfgz> pageList = erpBfgzService.page(page, queryWrapper1);
            return Result.ok(pageList);

        }else{
            Page<ErpBkgz> page = new Page<ErpBkgz>(pageNo, pageSize);
            Page<ErpBkgz> pageList = erpBkgzService.page(page, queryWrapper1);
            return Result.ok(pageList);
        }
    }

    /**
     * 张家界基本工资 全年数据
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getJbgzByTypeQn")
    public Result<?> getJbgzByTypeQn(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,HttpServletRequest req,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        System.out.println("=======张家界基本工资全年数据========>");
        System.out.println(date);
        System.out.println(type);
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        if (StringUtils.isNotBlank(date)) {
            if (date.length() <= 7) {
                date = date + "/01";
            }
            date.replaceAll("/", "-");
            DateTime parse = DateUtil.parse(date);
            dateTime = DateUtil.beginOfMonth(parse);
            System.out.println(dateTime);
        }else {
            return Result.error("请选择日期");
        }
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-","");
        System.out.println(bMonStr+"=============bMonStr");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        //获取登录人岗位
        List<HrBasStaffPostVo> yggw = hrBasPostService.getYggw(loginUser.getWorkNo(),bMonStr);
        Integer gwbz = null;
        if (CollUtil.isNotEmpty(yggw)){
            gwbz = yggw.get(0).getGwbz();
        }

        QueryWrapper queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("yggh",getWorkNo());
        //b:岗位工资
        if ("b".equals(type)){
            //行长查看当前机构下的数据
            System.out.println(gwbz+"======gwbz");
            if (gwbz == 100){
                Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
                IPage<PrTbYgjbgzmxb> pageList = service.getJbgzHzDate(page, bMonStr, hrBasOrganization.getZzbz(), bMonStr);
                return Result.ok(pageList);
            }else {
                Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
                IPage<PrTbYgjbgzmxb> pageList = service.getJbgzDate(page, bMonStr, getWorkNo(), bMonStr);
                return Result.ok(pageList);
            }
        }else if ("c".equals(type)){
            BigDecimal zzjJtxj = new BigDecimal("0");
            System.out.println(getWorkNo()+"===getWorkNo");
            List<PrTbYgjbgzmxb> prTbYgjbgzmxbs = prTbYgjbgzmxbService.list(queryWrapper1);
            if (CollUtil.isNotEmpty(prTbYgjbgzmxbs)){
                PrTbYgjbgzmxb prTbYgjbgzmxb = prTbYgjbgzmxbs.get(0);
                if (prTbYgjbgzmxb.getZcjt() != null && prTbYgjbgzmxb.getNgjt() != null && prTbYgjbgzmxb.getWsf() != null &&
                        prTbYgjbgzmxb.getTsgwjt() != null && prTbYgjbgzmxb.getTsdqjt() != null  && prTbYgjbgzmxb.getLdrznxjt() != null){
                    zzjJtxj = zzjJtxj.add(prTbYgjbgzmxb.getZcjt()).add(prTbYgjbgzmxb.getNgjt()).add(prTbYgjbgzmxb.getWsf().add(prTbYgjbgzmxb.getTsdqjt()).add(prTbYgjbgzmxb.getTsgwjt()).add(prTbYgjbgzmxb.getLdrznxjt()));
                }
            }

            Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
            IPage<PrTbYgjbgzmxb> pageList = service.getJbgzDate(page, bMonStr, getWorkNo(), bMonStr);
            //Page<PrTbYgjbgzmxb> pageList = prTbYgjbgzmxbService.page(page, queryWrapper1);
            for (int i = 0; i < pageList.getRecords().size(); i++) {
                pageList.getRecords().get(i).setJbtxj(zzjJtxj);
            }

            return Result.ok(pageList);
        }else if ("d".equals(type)){
            BigDecimal zzjKcxj = new BigDecimal("0");
            List<PrTbYgjbgzmxb> prTbYgjbgzmxbs = prTbYgjbgzmxbService.list(queryWrapper1);
            if (CollUtil.isNotEmpty(prTbYgjbgzmxbs)) {
                PrTbYgjbgzmxb prTbYgjbgzmxb = prTbYgjbgzmxbs.get(0);
                if (prTbYgjbgzmxb.getGjjkb() == null)
                    prTbYgjbgzmxb.setGjjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getSybxkb() == null)
                    prTbYgjbgzmxb.setSybxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYlbxkb() == null)
                    prTbYgjbgzmxb.setYlbxkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getQynjkb() == null)
                    prTbYgjbgzmxb.setQynjkb(new BigDecimal("0"));
                if (prTbYgjbgzmxb.getYljkb() == null)
                    prTbYgjbgzmxb.setYljkb(new BigDecimal("0"));

                if (prTbYgjbgzmxb.getGjj() != null && prTbYgjbgzmxb.getYlj() != null && prTbYgjbgzmxb.getSybx() != null && prTbYgjbgzmxb.getYlbx() != null && prTbYgjbgzmxb.getQynjkb() != null &&
                        prTbYgjbgzmxb.getQynj() != null && prTbYgjbgzmxb.getGjjkb() != null && prTbYgjbgzmxb.getYljkb() != null && prTbYgjbgzmxb.getSybxkb() != null && prTbYgjbgzmxb.getYlbxkb() != null) {
                    zzjKcxj = zzjKcxj.add(prTbYgjbgzmxb.getGjj()).add(prTbYgjbgzmxb.getYlj()).add(prTbYgjbgzmxb.getSybx()).add(prTbYgjbgzmxb.getYlbx())
                            .add(prTbYgjbgzmxb.getQynjkb()).add(prTbYgjbgzmxb.getQynj()).add(prTbYgjbgzmxb.getGjjkb()).add(prTbYgjbgzmxb.getYljkb()).add(prTbYgjbgzmxb.getSybxkb()).add(prTbYgjbgzmxb.getYlbxkb());
                    System.out.println(zzjKcxj);
                }
            }
            log.info(zzjKcxj+"=======zzjKcxj==========");
            Page<PrTbYgjbgzmxb> page = new Page<PrTbYgjbgzmxb>(pageNo, pageSize);
            IPage<PrTbYgjbgzmxb> pageList = service.getJbgzDate(page, bMonStr, getWorkNo(), bMonStr);
            for (int i = 0; i < pageList.getRecords().size(); i++) {
                pageList.getRecords().get(i).setKcxj2(zzjKcxj);
            }
            return Result.ok(pageList);
        }else if ("e".equals(type)){
            Page<ErpBfgz> page = new Page<ErpBfgz>(pageNo, pageSize);
            IPage<ErpBfgz> pageList = service.getBfgzDate(page,bMonStr, getWorkNo(), bMonStr);
            return Result.ok(pageList);

        }else{
            Page<ErpBkgz> page = new Page<ErpBkgz>(pageNo, pageSize);
            IPage<ErpBkgz> pageList = service.getBkgzDate(page,bMonStr, getWorkNo(), bMonStr);
            return Result.ok(pageList);
        }
    }


    /**
     * 张家界 团队绩效 查看全年数据
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getTdjx")
    public Result<?> getTdjx(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        log.info(date+"团队绩效日期===============");
        DateTime parse1 = null;
        DateTime parse4 = null;
        if (StringUtils.isNotBlank(date)){
            String s1 = date + "-01-01";
            String s4 = date + "-10-01";
            parse1 = DateUtil.parse(s1);
            parse4 = DateUtil.parse(s4);
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        String zzbz = hrBasOrganization.getZzbz();
        QueryWrapper<ErpTdjx> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("tjjd",parse1,parse4);
        queryWrapper.eq("dw",zzbz);
        queryWrapper.orderByDesc("tjjd");
        Page<ErpTdjx> page = new Page<ErpTdjx>(pageNo, pageSize);
        Page<ErpTdjx> erpTdjxPage = erpTdjxService.page(page, queryWrapper);
        return Result.ok(erpTdjxPage);
    }

    /**
     *  张家界 专项考核 全年数据
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getZxkh")
    public Result<?> getZxkh(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        log.info(date+"专项考核日期===============");
        DateTime parse1 = null;
        DateTime parse4 = null;
        if (StringUtils.isNotBlank(date)){
            String s1 = date + "-01-01";
            String s4 = date + "-10-01";
            parse1 = DateUtil.parse(s1);
            parse4 = DateUtil.parse(s4);
        }
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        QueryWrapper<ErpZxkh> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wd",hrBasOrganization.getZzbz());
        queryWrapper.between("tjjd",parse1,parse4);
        queryWrapper.orderByDesc("tjjd");
        Page<ErpZxkh> page = new Page<ErpZxkh>(pageNo, pageSize);
        Page<ErpZxkh> erpTdjxPage = erpZxkhService.page(page, queryWrapper);
        return Result.ok(erpTdjxPage);
    }

    /**
     * 张家界其他薪酬当前月份
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getQtxc")
    public Result<?> getQtxc(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        if (StringUtils.isNotBlank(date)) {
            if (date.length() <= 7) {
                date = date + "/01";
            }
            date.replaceAll("/", "-");
            DateTime parse = DateUtil.parse(date);
            dateTime = DateUtil.beginOfMonth(parse);
        }
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-","");
        System.out.println(bMonStr);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        //获取登录人岗位
        List<HrBasStaffPostVo> yggw = hrBasPostService.getYggw(loginUser.getWorkNo(),bMonStr);
        Integer gwbz = null;
        if (CollUtil.isNotEmpty(yggw)){
            gwbz = yggw.get(0).getGwbz();
        }
        //行长查看本机构数据
        if (gwbz == 100){
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("wd",hrBasOrganization.getZzbz());
            queryWrapper.eq("tjyf",dateTime);
            queryWrapper.orderByDesc("tjyf");
            Page<ErpQtxc> page = new Page<ErpQtxc>(pageNo, pageSize);
            Page pageList = erpQtxcService.page(page, queryWrapper);
            return Result.ok(pageList);
        }else {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("yggh",loginUser.getWorkNo());
            queryWrapper.eq("tjyf",dateTime);
            queryWrapper.orderByDesc("tjyf");
            Page<ErpQtxc> page = new Page<ErpQtxc>(pageNo, pageSize);
            Page pageList = erpQtxcService.page(page, queryWrapper);
            return Result.ok(pageList);
        }
    }

    /**
     * 张家界其他薪酬全年
     * @param pageNo
     * @param pageSize
     * @param date
     * @param type
     * @return
     */
    @RequestMapping("/getQtxcQn")
    public Result<?> getQtxcQn(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,String date, String type) {
        DateTime dateTime = DateUtil.beginOfMonth(new Date());
        if (StringUtils.isNotBlank(date)) {
            if (date.length() <= 7) {
                date = date + "/01";
            }
            date.replaceAll("/", "-");
            DateTime parse = DateUtil.parse(date);
            dateTime = DateUtil.beginOfMonth(parse);
        }
        String bMonStr = DateUtil.format(dateTime, DatePattern.NORM_DATE_PATTERN).replaceAll("-","");
        System.out.println(bMonStr);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        //获取登录人岗位
        List<HrBasStaffPostVo> yggw = hrBasPostService.getYggw(loginUser.getWorkNo(),bMonStr);
        Integer gwbz = null;
        if (CollUtil.isNotEmpty(yggw)){
            gwbz = yggw.get(0).getGwbz();
        }
        String nf = dateTime.toString().substring(0, 4);
        System.out.println(nf+"当前年份======"+dateTime);
        DateTime begin = DateUtil.beginOfYear(dateTime);
        DateTime end = DateUtil.endOfYear(dateTime);
        System.out.println(begin+"======");
        System.out.println(end+"======");
        //行长查看本机构数据
        if (gwbz == 100){
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("wd",hrBasOrganization.getZzbz());
            queryWrapper.ne("tjyf",dateTime);
            queryWrapper.between("tjyf",begin,end);
            queryWrapper.orderByDesc("tjyf");
            Page<ErpQtxc> page = new Page<ErpQtxc>(pageNo, pageSize);
            Page pageList = erpQtxcService.page(page, queryWrapper);
            return Result.ok(pageList);
        }else {
            QueryWrapper<ErpQtxc> queryWrapper = new QueryWrapper<ErpQtxc>();
            queryWrapper.eq("yggh",loginUser.getWorkNo());
            queryWrapper.ne("tjyf",dateTime);
            queryWrapper.between("tjyf",begin,end);
            queryWrapper.orderByDesc("tjyf");
            Page<ErpQtxc> page = new Page<ErpQtxc>(pageNo, pageSize);
            Page pageList = erpQtxcService.page(page, queryWrapper);
            return Result.ok(pageList);
        }
    }

    /**
     * 获取机构首页数据
     * 默认进来查看全行排名第一的机构
     */
    @RequestMapping("/jgIndex")
    public Result<?> jgIndex(String zzbz) {
        log.info("===查询机构结果===");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(loginUser.getOrgCode());
        if (QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())){
            String maxJgDay = service.getMaxJgDay();
            //判断是否是支行行长
       /*     boolean roleCode = sysRoleService.isRoleCode(getUsername());
            if (roleCode){
                log.info("===当前用户{}为ZHHZ角色===",getUsername());
                //查所在zzbz
                HrBasStaffPost byNowDate = hrBasStaffPostService.getByNowDate(getWorkNo());
                if (StringUtils.isNotBlank(byNowDate.getZzbz())){
                    zzbz = byNowDate.getZzbz();
                    log.info("===当前用户所在zzbz为{}===",zzbz);
                }
            }*/
            log.info(zzbz+"=====选择张家界zzbz=="+maxJgDay+"==maxJgDay==");
            log.info(hrBasOrganization.getZzbz()+"=====登录张家界zzbz======");
            //支行人均
            JgDataVO zhrjZjj = service.getZhrjZjj(StringUtils.isBlank(zzbz) ? hrBasOrganization.getZzbz() : zzbz, maxJgDay.replaceAll("-","").substring(0,8));
            JgDataVO jgDataVO = new JgDataVO();
            jgDataVO.setZzbz(StringUtils.isBlank(zzbz) ? hrBasOrganization.getZzbz() : zzbz);
            jgDataVO.setZhrj(zhrjZjj.getZhrj());
            jgDataVO.setQhrj(zhrjZjj.getQhrj());
            jgDataVO.setQhrs(zhrjZjj.getQhrs());
            System.out.println(zhrjZjj+"======支行人均=======");
            //获取人均
            //String rj = service.getRj();
            // JgDataVO jgDataVO = service.jgIndex(zzbz);
            if (StringUtils.isNotBlank(jgDataVO.getZzbz())) {
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", jgDataVO.getZzbz());
                jgDataVO.setZzbzVal(s);
            }
            // jgDataVO.jszb(rj);
            return Result.ok(jgDataVO);
        }else {
            //判断是否是支行行长
            boolean roleCode = sysRoleService.isRoleCode(getUsername());
            if (roleCode){
                log.info("===当前用户{}为ZHHZ角色===",getUsername());
                //查所在zzbz
                HrBasStaffPost byNowDate = hrBasStaffPostService.getByNowDate(getWorkNo());
                if (StringUtils.isNotBlank(byNowDate.getZzbz())){
                    zzbz = byNowDate.getZzbz();
                    log.info("===当前用户所在zzbz为{}===",zzbz);
                }
            }

            //获取人均
            String rj = service.getRj();
            JgDataVO jgDataVO = service.jgIndex(zzbz);
            if (StringUtils.isNotBlank(jgDataVO.getZzbz())) {
                String s = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION", "zzjc", "zzbz", jgDataVO.getZzbz());
                jgDataVO.setZzbzVal(s);
            }
            jgDataVO.jszb(rj);
            return Result.ok(jgDataVO);
        }

    }

    @RequestMapping("/getGzPmByZzbz")
    public Result<?> getGzPmByZzbz(String zzbz) {
        if (StringUtils.isBlank(zzbz))
            return Result.error("组织标志不能为空");
        if (getRedisQydm().equals(QydmEnums.JIANGHUA.getQydmCode())){
            return Result.ok(service.getGzPmByZzbzJH(zzbz));
        }else {
            return Result.ok(service.getGzPmByZzbz(zzbz));
        }

    }

    /**
     * 获取最近半年的存贷款数据
     */
    @RequestMapping("/getCDKLineChart")
    public Result<?> getCDKLineChart(String zzbz) {
        if (StringUtils.isBlank(zzbz)) {
            return Result.error("组织标志不能为空");
        }
        String jgdm = null;
        if (QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())){
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(zzbz);
            jgdm = hrBasOrganization.getYwjgdm();
            //最近的6个年份
            List<String> list = new ArrayList<>();
            List<Integer> ck = new ArrayList<>();
            List<Integer> dk = new ArrayList<>();
            String maxDate = service.getZjjDate();
            String yymmdd = maxDate.replaceAll("-", "").substring(2, 8);

            DateTime maxDateTime = DateUtil.parse(maxDate, DatePattern.PURE_DATE_PATTERN);
            DateTime dateTime = DateUtil.beginOfMonth(maxDateTime);
            dateTime = DateUtil.offsetMonth(dateTime, -5);
            for (int i = 0; i < 5; i++) {
                String yyMM = DateUtil.format(dateTime, "yyMM");
                dateTime = DateUtil.endOfMonth(dateTime);
                String yyMMdd = DateUtil.format(dateTime, "yyMMdd");
                String tableName = ZDTGLSGLSBUSINESSINFO + yyMMdd;
                log.info(tableName+"=====张家界动态表拼接");
                list.add(yyMM);
                if (StringUtils.isNotBlank(jgdm)) {
                    Integer ckByJgdm = service.getZjjCK(tableName,jgdm);
                    ck.add(ckByJgdm);
                    Integer dkByJgdm = service.getZjjDK(tableName,jgdm);
                    dk.add(dkByJgdm);
                } else {
                    ck.add(0);
                    dk.add(0);
                }
                dateTime = DateUtil.offsetMonth(dateTime, 1);
            }
            //处理当前月
            dateTime = DateUtil.endOfMonth(dateTime);
            String yyMM = DateUtil.format(dateTime, "yyMM");
            list.add(yyMM);
            if (StringUtils.isNotBlank(jgdm)){
                if (dateTime != new DateTime()){
                    //最后一共月末没表，取入库最大日期
                    //String yymmdd = maxDate.replaceAll("-", "").substring(2, 8);
                    String tableName = ZDTGLSGLSBUSINESSINFO + yymmdd;
                    log.info(tableName+"===张家界最后一个月取入库最大日期动态拼接表");
                    Integer ckByJgdm = service.getZjjCK(tableName,jgdm);
                    ck.add(ckByJgdm);
                    Integer dkByJgdm = service.getZjjDK(tableName,jgdm);
                    dk.add(dkByJgdm);
                }else {
                    String tableName = ZDTGLSGLSBUSINESSINFO + dateTime;
                    Integer ckByJgdm = service.getZjjCK(tableName,jgdm);
                    ck.add(ckByJgdm);
                    Integer dkByJgdm = service.getZjjDK(tableName,jgdm);
                    dk.add(dkByJgdm);
                }
            } else {
                ck.add(0);
                dk.add(0);
            }

            CDKLineChartVO cdkLineChartVO = new CDKLineChartVO();
            cdkLineChartVO.setCategories(list);
            cdkLineChartVO.setCk(ck);
            cdkLineChartVO.setDk(dk);
            return Result.ok(cdkLineChartVO);

        }else {
            // 先查jgdm
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(zzbz);
            if (StringUtils.isNotBlank(hrBasOrganization.getYwjgdm()))
                jgdm = hrBasOrganization.getYwjgdm();

            //最近的6个年份
            List<String> list = new ArrayList<>();
            List<Integer> ck = new ArrayList<>();
            List<Integer> dk = new ArrayList<>();
            DateTime dateTime = DateUtil.beginOfMonth(new Date());
            dateTime = DateUtil.offsetMonth(dateTime, -5);

            for (int i = 0; i < 6; i++) {
                String yyMM = DateUtil.format(dateTime, "yyMM");
                String yyyyMMdd = DateUtil.format(dateTime, "yyyyMMdd");
                list.add(yyMM);

                if (StringUtils.isNotBlank(jgdm)) {
                    Integer ckByJgdm = service.getTjbsCK(jgdm, yyyyMMdd);
                    ck.add(ckByJgdm);
                    Integer dkByJgdm = service.getTjbsDK(jgdm, yyyyMMdd);
                    dk.add(dkByJgdm);
                } else {
                    ck.add(0);
                    dk.add(0);
                }
                dateTime = DateUtil.offsetMonth(dateTime, 1);
            }

            CDKLineChartVO cdkLineChartVO = new CDKLineChartVO();
            cdkLineChartVO.setCategories(list);
            cdkLineChartVO.setCk(ck);
            cdkLineChartVO.setDk(dk);
            return Result.ok(cdkLineChartVO);
        }

    }


    @RequestMapping("/getCDKBfb")
    public Result<?> getCDKBfb(String zzbz) {
        if (StringUtils.isBlank(zzbz)) {
            return Result.error("组织标志不能为空");
        }
        String jgdm = null;
        CDKDataPercentageVO cdkDataPercentageVO = new CDKDataPercentageVO();

        // 先查jgdm
        HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(zzbz);
        if (StringUtils.isNotBlank(hrBasOrganization.getYwjgdm()))
            jgdm = hrBasOrganization.getYwjgdm();
        if (QydmEnums.ZHANGJIAJIE.getQydmCode().equals(getRedisQydm())){
            if (StringUtils.isNotBlank(jgdm)) {
                String maxDate = service.getZjjDate();
                cdkDataPercentageVO.setSjrq(maxDate);
                String yymmdd = maxDate.substring(2, 8);
                String yyMM = maxDate.substring(2, 6);
                String dkrq = service.getZjjDkDate().substring(2,8);
                Date yyyyMMQdd = DateUtil.offsetDay(DateUtil.beginOfYear(DateUtil.parse(service.getZjjDkDate())), -1);
                String qnm = yyyyMMQdd.toString().replaceAll("-", "").substring(2, 8);
                log.info(qnm+"=====去年末日期=====");
                log.info(yymmdd+"=====入库最大日期-1天===");
                String tableName = ZDTGLSGLSBUSINESSINFO + yymmdd;
                String tableName1 = ZDCBSBORMBASE + dkrq;
                String tableName2 = ZDCBSBORMBASE + qnm;

                //贷款余额
                int dk = service.getZjjDK(tableName,jgdm);
                cdkDataPercentageVO.setDkye(new BigDecimal(dk));

                //cdkDataPercentageVO.jsdkjc();

                //存款余额
                int ck = service.getZjjCK(tableName,jgdm);
                cdkDataPercentageVO.setCk(new BigDecimal(ck));
                //低息余额
                int zjjDxye = service.getZjjDxye(tableName,jgdm);
                cdkDataPercentageVO.setHqck(new BigDecimal(zjjDxye));
                System.out.println(ck + "=ck");
                System.out.println(zjjDxye + "=zjjDxye");
                cdkDataPercentageVO.jdckzb();
                //资产质量 不良
                int zjjZczl = service.getZjjZczl(tableName1);
                int qnmbl = service.getZjjZczl(tableName2);
                int zjjZczlDkye = service.getZjjZczlDkye(tableName1);
                cdkDataPercentageVO.setBnbl(new BigDecimal(zjjZczl));
                cdkDataPercentageVO.setBnblnc(new BigDecimal(qnmbl));
                cdkDataPercentageVO.setZjjzczldkye(new BigDecimal(zjjZczlDkye));
                cdkDataPercentageVO.jszczlZjj();


                log.info("===jgdm>{},yymm>{}===", jgdm, yyMM);
                Map<String, BigDecimal> dkKHByJgdm = service.getDkKHByJgdm(jgdm, yyMM);
                if (CollUtil.isNotEmpty(dkKHByJgdm)) {
                    dkKHByJgdm.forEach((k, v) -> {
                        System.out.println(k + "==" + v);
                    });

                    int zrs = dkKHByJgdm.get("ZRS") != null ? dkKHByJgdm.get("ZRS").intValue() : 0;
                    int nr = dkKHByJgdm.get("NR") != null ? dkKHByJgdm.get("NR").intValue() : 0;

                    cdkDataPercentageVO.setDkkh(zrs);
                    cdkDataPercentageVO.setDkkh1(nr);
                    cdkDataPercentageVO.jsdkkh();
                }
            }

        }else {
            if (StringUtils.isNotBlank(jgdm)) {
                DateTime dateTime = DateUtil.beginOfMonth(new Date());
                DateTime dateTime1 = DateUtil.beginOfYear(new Date());
                DateTime dateTime2 = DateUtil.beginOfMonth(DateUtil.offsetDay(dateTime1, -1));

                String yyMM = DateUtil.format(dateTime, "yyMM");
                String yyyyMMdd = DateUtil.format(dateTime, "yyyyMMdd");
                //去年末 月初
                String yyMMQNM = DateUtil.format(dateTime2, "yyMM");
                String yyyyMMddQNM = DateUtil.format(dateTime2, "yyyyMMdd");

//            int dkye = service.getDkByJgdm(jgdm, yyMM);
//            int dkyecl = service.getDkByJgdm(jgdm, yyMMQNM);
                int dkye = service.getTjbsDK(jgdm, yyyyMMdd);
                int dkyecl = service.getTjbsDK(jgdm, yyyyMMddQNM);
                cdkDataPercentageVO.setDkye(new BigDecimal(dkye));
                cdkDataPercentageVO.setDkyecl(new BigDecimal(dkyecl));
                cdkDataPercentageVO.jsdkjc();

                //int hqCkByJgdm = service.getHqCkByJgdm(jgdm, yyMM);
                int hqCkByJgdm = service.getTjbsDxck(jgdm, yyyyMMdd);
                //int ckByJgdm = service.getCkByJgdm(jgdm, yyMM);
                int ckByJgdm = service.getTjbsCK(jgdm, yyyyMMdd);
                System.out.println(hqCkByJgdm + "=hqCkByJgdm");
                System.out.println(ckByJgdm + "=ckByJgdm");
                cdkDataPercentageVO.setCk(new BigDecimal(ckByJgdm));
                cdkDataPercentageVO.setHqck(new BigDecimal(hqCkByJgdm));
                cdkDataPercentageVO.jdckzb();

                log.info("===jgdm>{},yymm>{}===", jgdm, yyMM);
                Map<String, BigDecimal> dkKHByJgdm = service.getDkKHByJgdm(jgdm, yyMM);
                if (CollUtil.isNotEmpty(dkKHByJgdm)) {
                    dkKHByJgdm.forEach((k, v) -> {
                        System.out.println(k + "==" + v);
                    });

                    int zrs = dkKHByJgdm.get("ZRS") != null ? dkKHByJgdm.get("ZRS").intValue() : 0;
                    int nr = dkKHByJgdm.get("NR") != null ? dkKHByJgdm.get("NR").intValue() : 0;

                    cdkDataPercentageVO.setDkkh(zrs);
                    cdkDataPercentageVO.setDkkh1(nr);
                    cdkDataPercentageVO.jsdkkh();
                }

                //不良
                int bnblByJgdm = service.getBnblByJgdm(jgdm, yyMM);
                int bnblByJgdm1 = service.getBnblByJgdm(jgdm, yyMMQNM);

                cdkDataPercentageVO.setBnbl(new BigDecimal(bnblByJgdm));
                cdkDataPercentageVO.setBnblnc(new BigDecimal(bnblByJgdm1));
                cdkDataPercentageVO.jszcqk();

            }
        }
        return Result.ok(cdkDataPercentageVO);
    }




    @RequestMapping("/getGzt")
    public Result<?> getGzt(String yyyyMMdd) {
        if (StringUtils.isBlank(yyyyMMdd)){
            DateTime dateTime = DateUtil.beginOfMonth(new Date());
            DateTime syc = DateUtil.offsetDay(dateTime, -1);
            yyyyMMdd = DateUtil.format(syc, "yyyyMMdd");
        }else {
            yyyyMMdd = yyyyMMdd.replace("-","");
        }
        System.out.println(yyyyMMdd);
        ErpWageYgjbgzglYx impWage = service.getImpWage(yyyyMMdd, getLoginUser().getWorkNo());
        return Result.ok(impWage);
    }

    Map<String,String> updateMap = new HashMap<>();

    @RequestMapping("/getGztList")
    public Result<?> getGztList(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
//        log.info("===更新工资合计===");
        String workNo = getWorkNo();
//        String yyyyMMdd = DateUtil.format(new Date(), "yyyyMMdd");
//        if (CollUtil.isNotEmpty(updateMap)){
//            String s = updateMap.get(workNo);
//            if (StringUtils.isNotBlank(s)){
//                if (!yyyyMMdd.equals(s)){
//                    log.info("===调用了更新语句===");
//                    service.updateYfgz(workNo);
//                    updateMap.put(workNo,yyyyMMdd);
//                }
//                log.info("===今天已经更新过，不再更新===");
//            }
//        }else {
//            log.info("===调用了更新语句===");
//            service.updateYfgz(workNo);
//            updateMap.put(workNo,yyyyMMdd);
//        }
//        log.info("===更新工资完成===");
        service.updateYfgz(workNo);
        Page<ImpWageVO> page = new Page<ImpWageVO>(pageNo, pageSize);
        IPage<ImpWageVO> impWageList = service.getImpWageList(page, workNo);
        return Result.ok(impWageList);
    }

    @Autowired
    IBasDataJobDaysService basDataJobDaysService;
    @RequestMapping("/getCkrpAndCkje")
    public Result<?> getCkrpAndCkje(String acctNo){
        if (StringUtils.isBlank(acctNo))
            return Result.ok();
        String yyyy = DateUtil.format(new Date(), "yy");
        String snm = Integer.valueOf(yyyy) - 1 + "1231";

        Date maxEendDay = basDataJobDaysService.getMaxExtDay();
        String yyyymmdd = DateUtil.format(maxEendDay, "yyMMdd");

        String table1 = ZDCBSINVMBASE + snm;
        String table2 = ZDCBSINVMBASE + yyyymmdd;
        System.out.println(table1);
        System.out.println(table2);

        Map<String, BigDecimal> result = new HashMap<>();
        Map<String, BigDecimal> stringBigDecimalMap = service.ckrpAndCkje(table1, acctNo);
        if (CollUtil.isNotEmpty(stringBigDecimalMap)){
            stringBigDecimalMap.forEach((k,v)-> System.out.println(k+"=="+v));

            if (stringBigDecimalMap.get("RPYE_Y_B")!=null){
                result.put("snckrp",stringBigDecimalMap.get("RPYE_Y_B"));
            }
            if (stringBigDecimalMap.get("CURR_BAL")!=null){
                result.put("snckje",stringBigDecimalMap.get("CURR_BAL"));
            }

        }
        Map<String, BigDecimal> stringBigDecimalMap2 = service.ckrpAndCkje(table2, acctNo);
        if (CollUtil.isNotEmpty(stringBigDecimalMap2)){
            stringBigDecimalMap2.forEach((k,v)-> System.out.println(k+"=="+v));
            if (stringBigDecimalMap2.get("RPYE_Y_B")!=null){
                result.put("ckrp",stringBigDecimalMap.get("RPYE_Y_B"));
            }
            if (stringBigDecimalMap2.get("CURR_BAL")!=null){
                result.put("ckje",stringBigDecimalMap.get("CURR_BAL"));
            }
        }
        return Result.ok(result);
    }

}

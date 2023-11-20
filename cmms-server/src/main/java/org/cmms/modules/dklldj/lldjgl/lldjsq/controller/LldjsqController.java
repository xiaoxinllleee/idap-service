package org.cmms.modules.dklldj.lldjgl.lldjsq.controller;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.RateConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.IPUtils;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.csszgl.gzbdssz.service.IGzbdsszService;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.cmms.modules.dklldj.jbxxgl.zhrpcx.service.IRate_zhckrpService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.IrateKhzhglxxbService;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateZxlldjxx;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.Lldjsq;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.RateDjsqxq;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.RateKhjbxxbVO;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.ILldjsqService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dklldj.tjfxgl.llfdph.entity.Zxllcx;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.system.entity.SysExeinfoLog;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.system.service.ISysExeinfoLogService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.service.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 利率定价申请
 * @Author: penghr
 * @Date: 2022-04-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "利率定价申请")
@RestController
@RequestMapping("/dklldj/lldjsq")
public class LldjsqController extends JeecgController<Lldjsq, ILldjsqService> implements Job {
    @Autowired
    private IRate_khjbxxbService iRateKhjbxxbService;
    @Autowired
    private ILldjsqService lldjsqService;
    @Autowired
    private IRateZxlldjbService rateZxlldjbService;
    @Autowired
    private IRateZxllcxService iRateZxllcxService;
    @Autowired
    private IRateDbxxglService rateDbxxglService;
    @Autowired
    private IRateDjsqmxService rateDjsqmxService;
    @Autowired
    private IRateLldjZhckrpAllService iRateLldjZhckrpAllService;
    @Autowired
    private IrateKhzhglxxbService rateKhzhglxxbService;
    @Autowired
    private ISysExeinfoLogService iSysExeinfoLogService;
    @Autowired
    private ICkzdkbService iCkzdkbService;
    @Autowired
    private ISysDictService sysDictService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;
    @Value("${liuyang.testsystem:false}")
    private String testsystem;

    /**
     * 利率定价申请 / 分页列表查询
     *
     * @param lldjsq
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "利率定价申请-分页列表查询")
    @ApiOperation(value = "利率定价申请-分页列表查询", notes = "利率定价申请-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Lldjsq lldjsq,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Lldjsq>> result = new Result<IPage<Lldjsq>>();
        QueryWrapper<Lldjsq> queryWrapper = QueryGenerator.initQueryWrapper(lldjsq, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ILldjsqService.class, lldjsqService, pageNo, pageSize, queryWrapper, "djnf", "lrsj");
        result.setSuccess(true);
        result.setCode(200);
        result.setResult(pageList);
        return result;
    }

    /**
     * 贷款利率定价申请 / 添加 / 校验及数据准备
     *
     * @param zjhm 申请客户证件号码
     * @param zzbz 申请客户所属组织
     * @param djnf 申请定价年份
     * @param flag 操作标识 add 添加 edit 修改
     * @return
     */
    @RequestMapping("/prepareDjData")
    public Result<?> prepareDjData(@RequestParam(name = "zjhm", required = true) String zjhm,
                                   @RequestParam(name = "zzbz", required = true) String zzbz,
                                   @RequestParam(name = "djnf", required = true) String djnf,
                                   @RequestParam(name = "flag", required = true) String flag) throws ParseException, ExecutionException, InterruptedException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long beginTime = System.currentTimeMillis();
        Date date = new Date(beginTime);
        log.info("贷款利率定价-利率定价申请-开始时间："+ simpleDateFormat.format(date));
        log.info("======================================================================");

        RateKhjbxxbVO khjbxxVO = new RateKhjbxxbVO();
        Date djnfDate = org.cmms.modules.util.DateUtil.string2Date(djnf, "yyyy-MM-dd");

        if ("edit".equals(flag)) {
            QueryWrapper<RateZxllcx> zxllcx = new QueryWrapper<>();
            zxllcx.eq("djnf",djnfDate);
            zxllcx.eq("zjhm",zjhm);
            RateZxllcx check = iRateZxllcxService.getOne(zxllcx,false);
            if (check != null) {
                QueryWrapper<RateZxlldjb> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djid",check.getDjid());
                RateZxlldjb zxlldjb = rateZxlldjbService.getOne(queryWrapper,false);
                if (zxlldjb != null) {
                    return Result.error("该条申请记录已确认定价，不能修改！");
                }
            }
        }

        CompletableFuture<Rate_khjbxxb> completableFuture0 = CompletableFuture.supplyAsync(() -> {
            QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", zjhm);
            Rate_khjbxxb khjbxx = iRateKhjbxxbService.getOne(queryWrapper,false);
            return khjbxx;
        });

        CompletableFuture<Lldjsq> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            QueryWrapper<Lldjsq> queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("zjhm", zjhm);
            //queryWrapper1.eq("zzbz", zzbz);
            queryWrapper1.eq("djnf", djnfDate);
            Lldjsq djsqxx = lldjsqService.getOne(queryWrapper1,false);
            if (djsqxx != null) {
                khjbxxVO.setDkqx(djsqxx.getDkqx());
                khjbxxVO.setKhlx(djsqxx.getKhlx().toString());
                khjbxxVO.setSfbmk(djsqxx.getSfbmk());
                khjbxxVO.setSfbzbxdk(djsqxx.getSfbzbxdk());
                khjbxxVO.setSfjzxkh(djsqxx.getSfjzxkh());
                khjbxxVO.setSfgwhy(djsqxx.getSfgwhy());
                khjbxxVO.setNcsqdzydk(djsqxx.getNcsqdzydk());
                khjbxxVO.setSfhpqy(djsqxx.getSfhpqy());
                khjbxxVO.setZhsxed(djsqxx.getZhsxed());
                khjbxxVO.setCdck(new BigDecimal(djsqxx.getCdck()));
            }
            return djsqxx;
        });

        Rate_khjbxxb khjbxx = completableFuture0.get();
        if (khjbxx == null) {
            return Result.error("该客户信息不存在,请在客户基本信息中进行维护！");
        } else {
            log.info("当前操作用户名："+getLoginUser().getUsername());
            if (!"admin".equals(getLoginUser().getUsername())) {
                String loginUserOrgCode = getLoginUser().getOrgCode();
                String loginUserOrgName = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","zzjc","zzbz",loginUserOrgCode);
                String custOrgCode = khjbxx.getZzbz();
                /*if (custOrgCode.isEmpty()) {
                    return Result.error("该客户组织机构未分配,请在客户基本信息中重新添加！");
                }*/
                String custOrgName = sysDictService.queryTableDictTextByKey("HR_BAS_ORGANIZATION","zzjc","zzbz",custOrgCode);
                log.info("当前登录用户所属支行："+loginUserOrgCode+" - "+loginUserOrgName);
                log.info("定价申请客户所属支行："+custOrgCode+" - "+custOrgName);
                if (!loginUserOrgCode.equals(custOrgCode)) {
                    return Result.error(loginUserOrgName+"下该客户信息不存在,客户实际在"+custOrgName+",请在客户基本信息中进行转移！");
                }
            }
        }

        CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() -> {
            Long beginTime1 = System.currentTimeMillis();
            Date date1 = new Date(beginTime1);
            log.info("贷款利率定价-利率定价申请-客户前三年存款日平[QUERY/EXECUTE]-开始时间："+ simpleDateFormat.format(date1));
            BeanUtil.copyProperties(khjbxx, khjbxxVO);
            //客户前三年的存款日平
            BigDecimal khqsndynckrp = new BigDecimal(0);
            BigDecimal khqsndenckrp = new BigDecimal(0);
            BigDecimal khqsndsnckrp = new BigDecimal(0);
            //客户前三年的定期存款日平
            BigDecimal khqsndyndqckrp = new BigDecimal(0);
            BigDecimal khqsndendqckrp = new BigDecimal(0);
            BigDecimal khqsndsndqckrp = new BigDecimal(0);
            log.info("定价申请年份:"+djnfDate);
            List<String> listckzh = new ArrayList<>();
            QueryWrapper<rateKhzhglxxb> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("zjhm", zjhm);
            List<rateKhzhglxxb> rateKhzhglxxbList = rateKhzhglxxbService.list(queryWrapper1);
            for (rateKhzhglxxb r : rateKhzhglxxbList) {
                log.info("贷款利率定价申请-关联存款账号:" + r.getCkzh());
                listckzh.add(r.getCkzh());
            }
            List<RateLldjZhckrpAll> zhckrpList = new ArrayList<>();
            if (listckzh.size() > 0) {
                QueryWrapper<RateLldjZhckrpAll> queryWrapper3 = new QueryWrapper<>();
                queryWrapper3.eq("tjlx", "YYYY");
                queryWrapper3.eq("tjyf", djnfDate);
                queryWrapper3.in("ckzh", listckzh);
                //queryWrapper3.eq("zjhm", zjhm);
                zhckrpList = iRateLldjZhckrpAllService.list(queryWrapper3);
            }

            log.info("关联存款账号日平数据["+zhckrpList.size()+"]："+zhckrpList);

            if (CollUtil.isNotEmpty(zhckrpList)) {
                for (RateLldjZhckrpAll record : zhckrpList) {
                    BigDecimal zhqsndynCkrp = record.getQsndynckrp() == null ? new BigDecimal(0) : record.getQsndynckrp();
                    BigDecimal zhqsndenCkrp = record.getQsndenckrp() == null ? new BigDecimal(0) : record.getQsndenckrp();
                    BigDecimal zhqsndsnCkrp = record.getQsndsnckrp() == null ? new BigDecimal(0) : record.getQsndsnckrp();
                    khqsndynckrp = khqsndynckrp.add(zhqsndynCkrp).setScale(7, BigDecimal.ROUND_HALF_UP);
                    khqsndenckrp = khqsndenckrp.add(zhqsndenCkrp).setScale(7, BigDecimal.ROUND_HALF_UP);
                    khqsndsnckrp = khqsndsnckrp.add(zhqsndsnCkrp).setScale(7, BigDecimal.ROUND_HALF_UP);
                    if (record.getZhlx() == 2) {
                        khqsndyndqckrp = khqsndyndqckrp.add(new BigDecimal((zhqsndynCkrp == null ? "0" : zhqsndynCkrp.toString()))).setScale(7, BigDecimal.ROUND_HALF_UP);
                        khqsndendqckrp = khqsndendqckrp.add(new BigDecimal((zhqsndenCkrp == null ? "0" : zhqsndenCkrp.toString()))).setScale(7, BigDecimal.ROUND_HALF_UP);
                        khqsndsndqckrp = khqsndsndqckrp.add(new BigDecimal((zhqsndsnCkrp == null ? "0" : zhqsndsnCkrp.toString()))).setScale(7, BigDecimal.ROUND_HALF_UP);
                    }
                }
                //客户前三年第一年度定期存款日平余额
                khjbxxVO.setKhqsndyndqckrp(khqsndyndqckrp);
                //客户前三年第二年度定期存款日平余额
                khjbxxVO.setKhqsndendqckrp(khqsndendqckrp);
                //客户前三年第三年度定期存款日平余额
                khjbxxVO.setKhqsndsndqckrp(khqsndsndqckrp);
                //客户前三年第一年度存款日平余额
                khjbxxVO.setGZ00033(khqsndynckrp);
                //客户前三年第二年度存款日平余额
                khjbxxVO.setGZ00034(khqsndenckrp);
                //客户前三年第三年度存款日平余额
                khjbxxVO.setGZ00035(khqsndsnckrp);
            }

            Long endTime1 = System.currentTimeMillis();
            date1 = new Date(endTime1);
            log.info("贷款利率定价-利率定价申请-客户前三年存款日平[QUERY/EXECUTE]-结束时间："+ simpleDateFormat.format(date1));
            log.info("贷款利率定价-利率定价申请-客户前三年存款日平[QUERY/EXECUTE]-总耗时："+ (endTime1 - beginTime1) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        });

        CompletableFuture<Void> completableFuture3 = CompletableFuture.runAsync(() -> {
            Long beginTime2 = System.currentTimeMillis();
            Date date2 = new Date(beginTime2);
            log.info("贷款利率定价-利率定价申请-上年授信额度、执行利率[QUERY]-开始时间："+ simpleDateFormat.format(date2));

            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            Date year = null;
            try {
                year = yearFormat.parse(djnf.substring(0, 4));
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            Calendar cN = Calendar.getInstance();
            cN.setTime(year);
            cN.add(Calendar.YEAR, -1);
            Timestamp snnf = new Timestamp(cN.getTimeInMillis());
            QueryWrapper<RateZxllcx> queryWrapper4 = new QueryWrapper();
            queryWrapper4.eq("djnf", snnf);
            queryWrapper4.eq("zjhm", zjhm);
            RateZxllcx zxllcx = iRateZxllcxService.getOne(queryWrapper4,false);
            if (zxllcx != null) {
                QueryWrapper<RateZxlldjb> queryWrapper5 = new QueryWrapper();
                queryWrapper5.eq("djid", zxllcx.getDjid());
                RateZxlldjb zxlldjb = rateZxlldjbService.getOne(queryWrapper5,false);
                if (zxlldjb != null) {
                    khjbxxVO.setSnsxed(zxlldjb.getCdck());
                    khjbxxVO.setSnzxll(zxlldjb.getYhhzxll());
                }
            }

            Long endTime2 = System.currentTimeMillis();
            date2 = new Date(endTime2);
            log.info("贷款利率定价-利率定价申请-上年授信额度、执行利率[QUERY]-结束时间："+ simpleDateFormat.format(date2));
            log.info("贷款利率定价-利率定价申请-上年授信额度、执行利率[QUERY]-总耗时："+ (endTime2 - beginTime2) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        });

        Lldjsq djsqxx = completableFuture1.get();
        if ("add".equals(flag) && djsqxx != null) {
            return Result.error("已存在此客户" + DateUtil.format(djnfDate, "yyyy") + "年度的申请记录，若需操作请选择修改！");
        }
        completableFuture2.get();
        completableFuture3.get();

        Long endTime = System.currentTimeMillis();
        date = new Date(endTime);
        log.info("贷款利率定价-利率定价申请-结束时间："+ simpleDateFormat.format(date));
        log.info("贷款利率定价-利率定价申请-总耗时："+ (endTime - beginTime) / 1000 +" (s)");
        log.info("======================================================================");

        //khjbxxVO = lldjsqService.getByZjhm(zzbz, zjhm, djnf);
        return Result.ok(khjbxxVO);
    }

    /**
     * 利率定价申请 / 编辑 / 获取申请明细
     *
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价申请-用法不详 暂时用在修改页面")
    @ApiOperation(value = "利率定价申请-", notes = "利率定价申请-")
    @GetMapping(value = "/sqmxlist")
    public Result<?> querySpmxList(Lldjsq lldjsq) {
        QueryWrapper<RateDjsqmx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("djnf", lldjsq.getDjnf());
        queryWrapper.eq("zjhm", lldjsq.getZjhm());
        List<RateDjsqmx> list = rateDjsqmxService.list(queryWrapper);
        JSONObject jsonObject = new JSONObject();
        for (RateDjsqmx rateDjsqmx : list) {
            jsonObject.put(rateDjsqmx.getZbgzid(), rateDjsqmx.getZbgzjg());
        }
        return Result.ok(jsonObject);
    }

    /**
     * 利率定价申请 / 添加保存
     *
     * @param lldjsqVo
     * @return
     */
    @AutoLog(value = "利率定价申请-添加保存")
    @ApiOperation(value = "利率定价申请-添加保存", notes = "利率定价申请-添加保存")
    @PostMapping(value = "/add")
    public Result<?> addSave(@RequestBody RateDjsqxq lldjsqVo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Long beginTime = System.currentTimeMillis();
            Date date      = new Date(beginTime);
            log.info("贷款利率定价-定价申请-添加保存[ADDSAVE]-开始："+ simpleDateFormat.format(date));
            String djnfstr = lldjsqVo.getDjnf().length() == 4 ? lldjsqVo.getDjnf() + "-01-01" : lldjsqVo.getDjnf();
            lldjsqVo.setDjnf(djnfstr);
            Date djnf = org.cmms.modules.util.DateUtil.string2Date(djnfstr, "yyyy-MM-dd");
            QueryWrapper<Lldjsq> queryWrapper = new QueryWrapper();
            queryWrapper.eq("djnf", djnf);
            queryWrapper.eq("zjhm", lldjsqVo.getZjhm());
            Lldjsq form = lldjsqService.getOne(queryWrapper,false);
            if (form != null) {
                return Result.error("客户利率定价申请信息已存在，请核实！");
            }
            Long endTime3 = System.currentTimeMillis();
            Date date3    = new Date(endTime3);
            LoginUser loginUser = getLoginUser();
            log.info("贷款利率定价-定价申请-重复申请校验[CHECK]-结束："+ simpleDateFormat.format(date3));
            log.info("贷款利率定价-定价申请-重复申请校验[CHECK]-总耗时："+ (endTime3 - beginTime) / 1000 +" (s)");
            log.info("======================================================================");

            //定价申请信息保存
            CompletableFuture<Void> completableFuture0 = CompletableFuture.runAsync(() ->{
                SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long beginTime0 = System.currentTimeMillis();
                Date date0      = new Date(beginTime0);
                log.info("贷款利率定价-定价申请-定价申请信息保存[SAVE]-开始："+ simpleDateFormat0.format(date0));
                Lldjsq lldjsq = new Lldjsq();
                BeanUtil.copyProperties(lldjsqVo, lldjsq);
                lldjsq.setDjnf(djnf);
                lldjsq.setLrbz(1);
                lldjsq.setLrsj(new Timestamp(System.currentTimeMillis()));
                lldjsq.setLrr(loginUser.getUsername());
                lldjsqService.save(lldjsq);
                Long endTime0 = System.currentTimeMillis();
                date0 = new Date(endTime0);
                log.info("贷款利率定价-定价申请-定价申请信息保存[SAVE]-结束："+ simpleDateFormat0.format(date0));
                log.info("贷款利率定价-定价申请-定价申请信息保存[SAVE]-总耗时："+ (endTime0 - beginTime0) / 1000 +" (s)");
                log.info("======================================================================");
            });
            //定价担保信息保存
            CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() ->{
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long beginTime1 = System.currentTimeMillis();
                Date date1      = new Date(beginTime1);
                log.info("贷款利率定价-定价申请-定价担保信息保存[SAVE]-开始："+ simpleDateFormat1.format(date1));
                List<RateDbxxgl> dbxxglList = lldjsqVo.getRateDbxxgls();
                if (CollUtil.isNotEmpty(dbxxglList)) {
                    for (RateDbxxgl record : dbxxglList) {
                        RateDbxxgl dbxxgl = record;
                        dbxxgl.setDjnf(djnf);
                        dbxxgl.setZjhm(lldjsqVo.getZjhm());
                        dbxxgl.setZzbz(lldjsqVo.getZzbz());
                        dbxxgl.setLrbz(1);
                        dbxxgl.setLrsj(new Timestamp(System.currentTimeMillis()));
                        dbxxgl.setLrczy(loginUser.getUsername());
                        if (dbxxgl.getPgjz() != null && dbxxgl.getPgjz() != 0L) {
                            dbxxgl.setDbl(new BigDecimal(dbxxgl.getSjdbje())
                                    .divide(new BigDecimal(dbxxgl.getPgjz()), 2, BigDecimal.ROUND_HALF_UP)
                                    .multiply(new BigDecimal(100))
                                    .intValue()
                            );
                        } else {
                            dbxxgl.setDbl(0);
                        }
                        rateDbxxglService.save(dbxxgl);
                    }
                }
                Long endTime1 = System.currentTimeMillis();
                date1 = new Date(endTime1);
                log.info("贷款利率定价-定价申请-定价担保信息保存[SAVE]-结束："+ simpleDateFormat1.format(date1));
                log.info("贷款利率定价-定价申请-定价担保信息保存[SAVE]-总耗时："+ (endTime1 - beginTime1) / 1000 +" (s)");
                log.info("======================================================================");
            });
            //定价申请明细信息保存
            CompletableFuture<Void> completableFuture2 = CompletableFuture.runAsync(() ->{
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long beginTime2 = System.currentTimeMillis();
                Date date2      = new Date(beginTime2);
                log.info("贷款利率定价-定价申请-定价申请明细信息保存[SAVE]-开始："+ simpleDateFormat2.format(date2));
                List<RateDjsqmx> djsqmxList = new ArrayList<>();
                RateDjsqmx djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.KH00001);
                djsqmx.setZbgzjg(lldjsqVo.getKH00001().toUpperCase());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00009);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00009());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00010);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00010());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00013);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00013());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00014);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00014());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00015);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00015());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.KH00004);
                djsqmx.setZbgzjg(lldjsqVo.getKH00004().toUpperCase());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00021);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00021());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00022);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00022());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00023);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00023());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00031);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00031());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00033);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00033());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00034);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00034());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00035);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00035());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00032);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00032());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00036);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00036());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00037);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00037());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00038);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00038());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00043);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00043());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00044);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00044());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00045);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00045());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00046);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00046());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00047);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00047());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00048);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00048());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00049);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00049());
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setLrr(loginUser.getUsername());
                djsqmx.setLrbz(1);
                djsqmx.setLrsj(new Time(System.currentTimeMillis()));
                djsqmx.setZjhm(lldjsqVo.getZjhm());
                djsqmx.setZbgzid(RateConstant.GZ00057);
                djsqmx.setZbgzjg(lldjsqVo.getGZ00057());
                djsqmxList.add(djsqmx);
                Long endTime4 = System.currentTimeMillis();
                Date date4 = new Date(endTime4);
                log.info("贷款利率定价-定价申请-定价申请明细信息保存[ADD]-结束："+ simpleDateFormat2.format(date4));
                log.info("贷款利率定价-定价申请-定价申请明细信息保存[ADD]-结束："+(endTime4 - beginTime2) / 1000 +" (s)");

                rateDjsqmxService.saveBatch(djsqmxList);
                Long endTime2 = System.currentTimeMillis();
                date2 = new Date(endTime2);
                log.info("贷款利率定价-定价申请-定价申请明细信息保存[SAVE]-结束："+ simpleDateFormat2.format(date2));
                log.info("贷款利率定价-定价申请-定价申请明细信息保存[SAVE]-总耗时："+ (endTime2 - beginTime2) / 1000 +" (s)");
                log.info("======================================================================");
            });

            completableFuture0.get();
            completableFuture1.get();
            completableFuture2.get();

            Long endTime = System.currentTimeMillis();
            date = new Date(endTime3);
            log.info("贷款利率定价-定价申请-添加保存[ADDSAVE]-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价申请-添加保存[ADDSAVE]-总耗时："+ (endTime - beginTime) / 1000 +" (s)");
            log.info("======================================================================");
            return Result.ok("添加成功！");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("贷款利率定价 / 利率定价申请 / 添加失败！"+throwable.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 利率定价申请 / 编辑保存
     *
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价申请-编辑保存")
    @ApiOperation(value = "利率定价申请-编辑保存", notes = "利率定价申请-编辑保存")
    @PutMapping(value = "/edit")
    public Result<?> editSave(@RequestBody RateDjsqxq lldjsq) {
        try {
            Date dateTime = org.cmms.modules.util.DateUtil.getYearStartDayByString(lldjsq.getDjnf());
            //删除定价申请信息
            QueryWrapper<Lldjsq> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf", dateTime);
            djsqxxQueryWrapper.eq("zjhm", lldjsq.getZjhm());
            lldjsqService.remove(djsqxxQueryWrapper);
            //删除定价申请明细
            QueryWrapper<RateDjsqmx> djsqmxQueryWrapper = new QueryWrapper<>();
            djsqmxQueryWrapper.eq("djnf", dateTime);
            djsqmxQueryWrapper.eq("zjhm", lldjsq.getZjhm());
            rateDjsqmxService.remove(djsqmxQueryWrapper);
            //删除定价担保信息
            QueryWrapper<RateDbxxgl> dbxxglQueryWrapper = new QueryWrapper<>();
            dbxxglQueryWrapper.eq("djnf", dateTime);
            dbxxglQueryWrapper.eq("zjhm", lldjsq.getZjhm());
            rateDbxxglService.remove(dbxxglQueryWrapper);
            //调用添加保存
            addSave(lldjsq);
            return Result.ok("保存成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 利率定价申请 / 编辑保存失败！"+throwable.getMessage());
            return Result.error("编辑保存失败！");
        }
    }

    /**
     * 利率定价申请 / 删除
     *
     * @param djnfStr
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价申请-删除")
    @ApiOperation(value = "利率定价申请-删除", notes = "利率定价申请-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "djnf", required = true) String djnfStr,
                            @RequestParam(name = "zjhm", required = true) String zjhm) {
        try {
            Date djnf = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnfStr);
            QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
            zxlldjbQueryWrapper.eq("djnf", djnf);
            zxlldjbQueryWrapper.eq("zjhm", zjhm);
            List<RateZxlldjb> zxlldjxxList = rateZxlldjbService.list(zxlldjbQueryWrapper);
            if (CollUtil.isNotEmpty(zxlldjxxList)) {
                return Result.error("该客户在本年度进行了定价计算，不能删除本条数据！");
            }
            //删除定价申请信息
            //先去查询是否做了计算 如果做了就不能删除 rate_zxlldjb
            QueryWrapper<Lldjsq> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf", djnf);
            djsqxxQueryWrapper.eq("zjhm", zjhm);
            lldjsqService.remove(djsqxxQueryWrapper);
            //删除定价申请明细
            QueryWrapper<RateDjsqmx> djsqmxQueryWrapper = new QueryWrapper<>();
            djsqmxQueryWrapper.eq("djnf", djnf);
            djsqmxQueryWrapper.eq("zjhm", zjhm);
            rateDjsqmxService.remove(djsqmxQueryWrapper);
            //删除定价担保信息
            QueryWrapper<RateDbxxgl> dbxxglQueryWrapper = new QueryWrapper<>();
            dbxxglQueryWrapper.eq("djnf", djnf);
            dbxxglQueryWrapper.eq("zjhm", zjhm);
            rateDbxxglService.remove(dbxxglQueryWrapper);

            return Result.ok("删除成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 利率定价申请 / 删除失败！"+throwable.getMessage());
            return Result.error("删除失败！");
        }
    }

	/**
	 * 利率定价申请 / 获取担保信息
	 * @param zjhm 证件号码
	 * @param djnf 定价年份
	 * @return
	 */
	@RequestMapping("/getDbxxByZjhm")
	public Result<?> getDbxxByZjhm(@RequestParam(name = "zjhm", required = true) String zjhm,
								   @RequestParam(name = "djnf", required = true) String djnf) {
        try {
            Date date = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
            QueryWrapper<RateDbxxgl> queryWrapper = new QueryWrapper();
            queryWrapper.eq("zjhm", zjhm);
            queryWrapper.eq("djnf", date);
            queryWrapper.orderByAsc("dblx");
            List<RateDbxxgl> list = rateDbxxglService.list(queryWrapper);
            return Result.ok(list);
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 利率定价申请 / 获取担保信息失败！"+throwable.getMessage());
            return Result.error("获取担保信息失败！");
        }
	}

    /**
     * 利率定价申请 / 导出
     *
     * @param request
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价申请-导出定价申请信息")
    @ApiOperation(value = "利率定价申请-导出定价申请信息", notes = "利率定价申请-导出定价申请信息")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Lldjsq lldjsq) {
        return super.exportXls(request, lldjsq, Lldjsq.class, "利率定价申请信息");
    }

    /**
     * 利率定价申请 / 客户前三年存款日平余额 / ETL脚本自动抽取定时任务
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String djnf   = sdf.format(today)+"0101"; //当前定价年份，默认当前年份
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long startTime = System.currentTimeMillis();
        log.info(String.format("贷款利率定价/客户前三年存款日平数据抽取/定时任务-开始执行 => "+ sdf.format(startTime)));
        SysExeinfoLog exeinfoLog = new SysExeinfoLog();
        exeinfoLog.setLogType(2);
        exeinfoLog.setLogContent("贷款利率定价/客户前三年存款日平数据抽取/定时任务/rate_common_init");
        exeinfoLog.setMethod("org.cmms.modules.dklldj.lldjgl.lldjsq.controller.LldjsqController.execute()");
        exeinfoLog.setExeStartTime(sdf.format(startTime));
        boolean ok = false;
        if ("true".equals(sfdsjpt)) {
            String maxday = iCkzdkbService.queryMaxDataDate(); //获取存款主表最大数据日期
            String qsndynB = ""; //第一年开始日期
            String qsndynE = ""; //第一年结束日期
            String dynts   = ""; //第一年累计天数
            String qsndenB = ""; //第二年开始日期
            String qsndenE = ""; //第二年结束日期
            String dents   = ""; //第二年累计天数
            String qsndsnB = ""; //第三年开始日期
            String qsndsnE = ""; //第三年结束日期
            String dsnts   = ""; //第三年累计天数
            try {
                sdf = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(new Date());
                if (!maxday.substring(0, 4).equals(date.substring(0, 4))) {
                    //前三年第一年度开始时间、结束时间、统计天数
                    Date dynB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(new Date(), 0), -37);
                    Date dynE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(new Date(), 0), -26);
                    dynts   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(dynE), dynB) + 1 + "";
                    qsndynB = sdf.format(dynB);
                    qsndynE = sdf.format(dynE);
                    //前三年第二年度开始时间、结束时间、统计天数
                    Date denB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(new Date(), 0), -25);
                    Date denE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(new Date(), 0), -14);
                    dents   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(denE), denB) + 1 + "";
                    qsndenB = sdf.format(denB);
                    qsndenE = sdf.format(denE);
                    //前三年第三年度开始时间、结束时间、统计天数
                    Date dsnB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(new Date(), 0), -13);
                    Date dsnE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(new Date(), 0), -2);
                    dsnts   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(dsnE), dsnB) + 1 + "";
                    qsndsnB = sdf.format(dsnB);
                    qsndsnE = sdf.format(dsnE);
                } else {
                    String noDn = date.substring(0, 4) + date.substring(4, 8); //不是定价当年
                    //前三年第一年度开始时间、结束时间、统计天数
                    Date dynB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(sdf.parse(noDn), 0), -36);
                    Date dynE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(sdf.parse(noDn), 0), -25);
                    dynts   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(dynE), dynB) + 1 + "";
                    qsndynB = sdf.format(dynB);
                    qsndynE = sdf.format(dynE);
                    //前三年第二年度开始时间、结束时间、统计天数
                    Date denB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(sdf.parse(noDn), 0), -24);
                    Date denE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(sdf.parse(noDn), 0), -13);
                    dents   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(denE), denB) + 1 + "";
                    qsndenB = sdf.format(denB);
                    qsndenE = sdf.format(denE);
                    //前三年第三年度开始时间、结束时间、统计天数
                    Date dsnB = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getFirstday_Month(sdf.parse(noDn), 0), -12);
                    Date dsnE = org.cmms.common.util.DateUtil.addMonth(org.cmms.common.util.DateUtil.getLastday_Month(sdf.parse(noDn), 0), -1);
                    dsnts   = org.cmms.common.util.DateUtil.getDiffDays(org.cmms.common.util.DateUtil.getLastDay(dsnE), dsnB) + 1 + "";
                    qsndsnB = sdf.format(dsnB);
                    qsndsnE = sdf.format(dsnE);
                }
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
            HashMap<String, String> params = new HashMap<>();
            params.put("djnf", djnf);
            params.put("maxday", maxday);
            params.put("qsndynB", qsndynB);
            params.put("qsndynE", qsndynE);
            params.put("dynts", dynts);
            params.put("qsndenB", qsndenB);
            params.put("qsndenE", qsndenE);
            params.put("dents", dents);
            params.put("qsndsnB", qsndsnB);
            params.put("qsndsnE", qsndsnE);
            params.put("dsnts", dsnts);
            params.put("etl_task","kiss.domain.application.rate.proc_lldj_khqsnckrp_day");
            System.out.println("贷款利率定价/客户前三年存款日平数据抽取/定时任务-调度参数 => ");
            System.out.println(params);
            // count_lldj_khqsnckrp_day
            if ("true".equals(testsystem)) {
                // 测试系统不运行客户前三年定价日平跑批
                ok = true;
            } else {
                ok = EtlUtil.callEtl("rate_common_init", params, 30);
            }
            log.info(String.format("贷款利率定价/客户前三年存款日平数据抽取/定时任务-是否完成？（true 是/false 否） => "+ ok));
        }
        Long endTime   = System.currentTimeMillis();
        log.info(String.format("贷款利率定价/客户前三年存款日平数据抽取/定时任务-执行结束 => "+ sdf.format(endTime)));
        log.info(String.format("贷款利率定价/客户前三年存款日平数据抽取/定时任务-执行耗时 => "+ (endTime - startTime) / 1000 + " (s)"));

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        exeinfoLog.setExeEndTime(sdf.format(endTime));
        exeinfoLog.setCostTime(String.valueOf((endTime - startTime) / 1000));
        exeinfoLog.setExeStatus(String.valueOf(ok));
        iSysExeinfoLogService.save(exeinfoLog);
    }
}

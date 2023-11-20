package org.cmms.modules.dklldj.lldjgl.lldjjs.controller;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;

import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import org.cmms.modules.dklldj.csszgl.csgl.service.ICsszxxService;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.entity.Fdcsdzb;
import org.cmms.modules.dklldj.csszgl.fdcsdzb.service.IFdcsdzbService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.IrateKhzhglxxbService;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.entity.RateLsdjcx;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.service.IRateLsdjcxService;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.lldjjs.entity.RateZxlldjxx;
import org.cmms.modules.dklldj.lldjgl.lldjjs.service.IRateZxlldjxxService;
import org.cmms.modules.dklldj.lldjgl.lldjsq.entity.Lldjsq;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.ILldjsqService;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import org.cmms.modules.sjxf.hxxt.zzzhzb.service.IZzzhzbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xdgl.grdkgl.entity.*;
import org.cmms.modules.xdgl.grdkgl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 利率定价计算
 * @Author: penghr
 * @Date: 2022-04-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "利率定价计算")
@RestController
@RequestMapping("/dklldj/lldjjs")
public class LldjjsController extends JeecgController<RateZxlldjxx, IRateZxlldjxxService> {
    @Autowired
    private IRateZxlldjxxService iRateZxlldjxxService;
    @Autowired
    private ILldjsqService iLldjsqService;
    @Autowired
    private ICsszxxService csszxxService;
    @Autowired
    private IFdcsdzbService iFdcsdzbService;
    @Autowired
    private IRateZbkxxbService rateZbkxxbService;
    @Autowired
    private IRateZbgzxxbService rateZbgzxxbService;
    @Autowired
    private IRateGzbdsxxService rateGzbdsxxService;
    @Autowired
    private ILldjsqService lldjsqService;
    @Autowired
    private IRateDjsqmxService rateDjsqmxService;
    @Autowired
    private IRateDbxxglService rateDbxxglService;
    @Autowired
    private IZzzhzbService iZzzhzbService;
    @Autowired
    private IrateKhzhglxxbService rateKhzhglxxbService;
    @Autowired
    private IRateLldjZhckrpAllService rateLldjZhckrpAllService;
    @Autowired
    private IRateZxllcxService rateZxllcxService;
    @Autowired
    private IRateLsdjcxService iRateLsdjcxService;
    @Autowired
    private IDictValueQuery iDictValueQuery;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 利率定价计算 / 分页列表查询
     *
     * @param rateZxlldjxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "利率定价计算-分页列表查询")
    @ApiOperation(value = "利率定价计算-分页列表查询", notes = "利率定价计算-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateZxlldjxx rateZxlldjxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateZxlldjxx>> result = new Result<IPage<RateZxlldjxx>>();
        QueryWrapper<RateZxlldjxx> queryWrapper = QueryGenerator.initQueryWrapper(rateZxlldjxx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateZxlldjxxService.class, iRateZxlldjxxService, pageNo, pageSize, queryWrapper, "djnf", "zjhm");
        result.setSuccess(true);
        result.setCode(200);
        result.setResult(pageList);
        return result;
    }

    /**
     * 利率定价计算 / 查看、打印 / 数据获取
     *
     * @param rateZxlldjxx
     * @return
     */
    @AutoLog(value = "利率定价计算-查看/打印-数据获取")
    @ApiOperation(value = "利率定价计算-查看/打印-数据获取", notes = "利率定价计算-查看/打印-数据获取")
    @GetMapping(value = "/viewPrint")
    public Result<?> ViewPrint(RateZxlldjxx rateZxlldjxx) {
        QueryWrapper<Lldjsq> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", rateZxlldjxx.getZjhm());
        queryWrapper.eq("djnf", rateZxlldjxx.getDjnf());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ILldjsqService.class,iLldjsqService, 1, 10, queryWrapper, "djnf", "zjhm");
        if (pageList == null || pageList.getRecords().size() == 0) {
            return Result.error("未查询到对应的申请信息！");
        }
        return Result.ok(pageList);
    }

    @AutoLog(value = "利率定价计算-查看/打印-获取执行利率定价表数据")
    @ApiOperation(value = "利率定价计算-查看/打印-获取执行利率定价表数据", notes = "利率定价计算-查看/打印-获取执行利率定价表数据")
    @GetMapping(value = "/getZxxlldjbs")
    public Result<?> getZxxlldjbs(RateZxlldjxx rateZxlldjxx) {
        QueryWrapper<RateZxlldjxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("zjhm", rateZxlldjxx.getZjhm());
        queryWrapper.eq("djnf", rateZxlldjxx.getDjnf());
        List<RateZxlldjxx> list = iRateZxlldjxxService.list(queryWrapper);
        if (list == null || list.size() == 0) {
            return Result.error("未查询到对应的定价信息！");
        }
        IPage<RateZxlldjxx> pageList = PageUtil.getPages(list, list == null ? 0 : list.size(), 10, 1);
        return Result.ok(pageList);
    }

    /**
     * 利率定价计算 / 添加 / 校验重新添加计算
     *
     * @param ratedjsqxx
     * @return
     */
    @AutoLog(value = "利率定价计算-添加前校验-返回定价申请信息")
    @ApiOperation(value = "利率定价计算-添加前校验-返回定价申请信息", notes = "利率定价计算-添加前校验-返回定价申请信息")
    @PostMapping(value = "/checkRepeAdd")
    public Result<?> checkRepeAdd(@RequestBody Lldjsq ratedjsqxx) {
        QueryWrapper<RateZxlldjxx> rateZxlldjxx = new QueryWrapper<>();
        rateZxlldjxx.eq("djnf", ratedjsqxx.getDjnf());
        rateZxlldjxx.eq("zjhm", ratedjsqxx.getZjhm());
        rateZxlldjxx.last("and (spzt = 0)");
        RateZxlldjxx zxlldjxx = iRateZxlldjxxService.getOne(rateZxlldjxx);
        if (zxlldjxx != null) {
            return Result.error("同一年度内该客户只允许有一条未确认的定价信息！");
        } else {
            return Result.ok(true);
        }
    }

    /**
     * 利率定价计算 / 添加 / 获取定价申请信息
     *
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价计算-添加-返回定价申请信息")
    @ApiOperation(value = "利率定价计算-添加-返回定价申请信息", notes = "利率定价计算-添加-返回定价申请信息")
    @GetMapping(value = "/getSqxx")
    public Result<?> getSqxx(Lldjsq lldjsq) {
        QueryWrapper<Lldjsq> rateDjsqxx = new QueryWrapper<>();
        rateDjsqxx.eq("zjhm", lldjsq.getZjhm());
        rateDjsqxx.eq("djnf", lldjsq.getDjnf());
        Lldjsq djsqxx = lldjsqService.getOne(rateDjsqxx);
        if (djsqxx == null) {
            return Result.error("未查询到对应的申请信息！");
        } else {
            return Result.ok(djsqxx);
        }
    }

    /**
     * 利率定价计算 / 添加保存
     *
     * @param zxlldjxx
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @AutoLog(value = "利率定价计算-计算保存")
    @ApiOperation(value = "利率定价计算-计算保存", notes = "利率定价计算-计算保存")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody RateZxlldjxx zxlldjxx) {
        Long djid    = 0L; //定价ID
        double jzll  = 0d; //基准利率
        double lprll = 0d; //LPR利率
        try {
            String dkqx  = zxlldjxx.getDkqx();  //贷款期限 1 一年期 2 五年期以上
            log.info("利率定价计算-计算保存：：贷款期限：："+dkqx);
            //参数设置
            CompletableFuture<List<Csszxx>> completableFuture0 = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<Csszxx> queryWrapper0 = new QueryWrapper<>();
                List<Csszxx> csszList = csszxxService.list(queryWrapper0);
                return csszList;
            });
            //定价ID
            CompletableFuture<Long> completableFuture1 = CompletableFuture.supplyAsync(() -> {
                Long newDjid = 0L;
                if ("true".equals(sfdsjpt)) {
                    newDjid = Long.parseLong(iRateZxlldjxxService.getMaxDjidHive()); //Hive:拿到表内最大ID值，再累加1
                } else {
                    newDjid = Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval")); //Oracle:调用自增序列
                }
                return newDjid;
            });
            //定价计算信息
            CompletableFuture<RateZxlldjxx> completableFuture2 = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateZxlldjxx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djnf", zxlldjxx.getDjnf());
                queryWrapper.eq("zjhm", zxlldjxx.getZjhm());
                queryWrapper.last(" and (spzt = 0 )");
                RateZxlldjxx rateZxlldjxx = iRateZxlldjxxService.getOne(queryWrapper);
                return rateZxlldjxx;
            });
            List<Csszxx> csszList = completableFuture0.get();
            djid = completableFuture1.get();
            log.info("利率定价计算-计算保存：：定价ID：："+djid);
            RateZxlldjxx rateZxlldjxx = completableFuture2.get();
            if (rateZxlldjxx != null) {
                return Result.error("同一年度该客户只允许有一条未确认的定价信息！");
            }
            if ("1".equalsIgnoreCase(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream()
                        .filter(item -> item.getCsid().equalsIgnoreCase("CS0012") || item.getCsid().equalsIgnoreCase("CS0028"))
                        .collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    String rateCsid = cssz.getCsid();
                    if ("CS0012".equals(rateCsid)) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("CS0028".equals(rateCsid)) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            } else {
                List<Csszxx> CsszCollect = csszList.stream()
                        .filter(item -> item.getCsid().equalsIgnoreCase("CS0013") || item.getCsid().equalsIgnoreCase("CS0029"))
                        .collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    String rateCsid = cssz.getCsid();
                    if ("CS0013".equals(rateCsid)) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("CS0029".equals(rateCsid)) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            log.info("利率定价计算-计算保存：：lprll：："+lprll);
            log.info("利率定价计算-计算保存：：jzll：："+jzll);

            log.info("利率定价计算-计算保存：：SET值开始：：");
            zxlldjxx.setJjll(BigDecimal.valueOf(jzll));
            zxlldjxx.setJdbp(zxlldjxx.getJdbp());
            zxlldjxx.setYhhjdbp(zxlldjxx.getYhhLprjd7());
            zxlldjxx.setYhhzxll(zxlldjxx.getYhhzxll());
            zxlldjxx.setDjid(djid);
            zxlldjxx.setLrsj(new Timestamp(System.currentTimeMillis()));
            zxlldjxx.setLrbz(2);
            zxlldjxx.setLrczy(getLoginUser().getUsername());
            zxlldjxx.setSpzt(0);
            zxlldjxx.setXgzt(0);
            zxlldjxx.setDkqx(String.valueOf(dkqx));
            zxlldjxx.setLprll(BigDecimal.valueOf(lprll));
            zxlldjxx.setBjrq(zxlldjxx.getBjrq());
            log.info("利率定价计算-计算保存：：SET值结束：：");
            log.info("利率定价计算-计算保存：：保存开始：：");
            iRateZxlldjxxService.save(zxlldjxx);
            log.info("利率定价计算-计算保存：：保存结束：：");
            return Result.ok("添加保存成功！");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("贷款利率定价 / 利率定价计算 / 添加保存失败！"+throwable.getMessage());
            return Result.error("添加保存失败！");
        }
    }

    /**
     * 利率定价计算 / 重新计算 / 获取定价申请信息
     *
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价计算-重新计算")
    @ApiOperation(value = "利率定价计算-重新计算", notes = "利率定价计算-重新计算")
    @GetMapping(value = "/getSqxxByCxjs")
    public Result<?> getSqxxByCxjs(Lldjsq lldjsq) {
        QueryWrapper<Lldjsq> rateDjsqxx = new QueryWrapper<>();
        rateDjsqxx.eq("zjhm", lldjsq.getZjhm());
        rateDjsqxx.eq("djnf", lldjsq.getDjnf());
        Lldjsq djsqxx = lldjsqService.getOne(rateDjsqxx);
        if (djsqxx == null) {
            return Result.error("未查询到对应的申请信息！");
        }
        return Result.ok(djsqxx);
    }

    /**
     * 利率定价计算 / 重新计算保存
     *
     * @param rateZxlldjxx
     * @return
     */
    @AutoLog(value = "利率定价计算-重新计算保存")
    @ApiOperation(value = "利率定价计算-重新计算保存", notes = "利率定价计算-重新计算保存")
    @PostMapping(value = "/edit")
    public Result<?> edit(@RequestBody RateZxlldjxx rateZxlldjxx) {
        try {
            //如果重新计算发现该记录是已确认则需要覆盖最新的定价记录
            if (rateZxlldjxx.getSpzt() == 1d) {
                QueryWrapper<RateZxllcx> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("djnf", rateZxlldjxx.getDjnf());
                queryWrapper1.eq("zjhm", rateZxlldjxx.getZjhm());
                RateZxllcx zxllcx = rateZxllcxService.getOne(queryWrapper1);
                if (zxllcx != null) {
                    rateZxllcxService.remove(queryWrapper1);
                }
                RateZxllcx insert = new RateZxllcx();
                insert.setDjid(rateZxlldjxx.getDjid());
                insert.setDjrq(new Timestamp(System.currentTimeMillis()));
                insert.setDjnf(rateZxlldjxx.getDjnf());
                insert.setZzbz(rateZxlldjxx.getZzbz());
                insert.setZjhm(rateZxlldjxx.getZjhm());
                insert.setKhmc(rateZxlldjxx.getKhmc());
                insert.setDfhj(rateZxlldjxx.getDfhj());
                insert.setJjll(rateZxlldjxx.getJjll());
                insert.setSffd(rateZxlldjxx.getSffd());
                insert.setZxll(rateZxlldjxx.getZxll());
                insert.setLprll(rateZxlldjxx.getLprll());
                insert.setJdbp(rateZxlldjxx.getJdbp());
                insert.setYhhjdbp(rateZxlldjxx.getYhhjdbp());
                insert.setYhhzxll(rateZxlldjxx.getYhhzxll());
                insert.setLrsj(new Timestamp(System.currentTimeMillis()));
                insert.setLrczy(getLoginUser().getUsername());
                rateZxllcxService.save(insert);
            }
            rateZxlldjxx.setDkqx(rateZxlldjxx.getDkqx());
            rateZxlldjxx.setJjll(rateZxlldjxx.getJjll());
            rateZxlldjxx.setXgzt(1); //已修改
            rateZxlldjxx.setXgsj(new Timestamp(System.currentTimeMillis()));
            rateZxlldjxx.setXgczy(getLoginUser().getUsername());
            QueryWrapper<RateZxlldjxx> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("djnf",rateZxlldjxx.getDjnf());
            queryWrapper1.eq("zjhm",rateZxlldjxx.getZjhm());
            iRateZxlldjxxService.update(rateZxlldjxx,queryWrapper1);
            return Result.ok("重新计算成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 利率定价计算 / 重新计算失败！"+throwable.getMessage());
            return Result.error("重新计算失败！");
        }
    }

    /**
     * 利率定价计算 / 计算得分
     *
     * @param lldjsqEnty
     * @return
     * @throws Throwable
     */
    @AutoLog(value = "利率定价计算-计算得分")
    @ApiOperation(value = "利率定价计算-计算得分", notes = "利率定价计算-计算得分")
    @GetMapping(value = "/getCompute")
    public Result<?> getCompute(Lldjsq lldjsqEnty) throws Throwable {
        JSONObject view = new JSONObject();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long beginTime = System.currentTimeMillis();
        Date date = new Date(beginTime);
        log.info("贷款利率定价-定价计算-开始时间："+ simpleDateFormat.format(date));
        log.info("======================================================================");

        Long timeConsumingStart_1 = System.currentTimeMillis();
        date = new Date(timeConsumingStart_1);
        log.info("贷款利率定价-定价计算-基础变量初始化-开始："+ simpleDateFormat.format(date));

        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        //qydm = "020";
        DecimalFormat format0Decimal = new DecimalFormat("#0");
        DecimalFormat format2Decimal = new DecimalFormat("#0.00");
        DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
        double dfhj      = 0d;    //得分合计
        int sfyqzj       = 0;
        double xypj      = 0;     //信用评级得分
        String xydj      = "";    //客户信用等级
        double khsygxdf  = 0d;    //客户收益贡献得分
        double khckgxdf  = 0d;    //客户存款贡献得分
        double khdlywdf  = 0d;
        int rs           = 20;    //本年在本行代发工资人数
        boolean check1   = false; //本年在本行代发工资人数是否超过（参数CS0019所设置的数值）
        double whjsl     = 50d;   //实际控制企业有国际贸易业务外汇结算量（美元）
        boolean check2   = false; //实际控制企业有国际贸易业务外汇结算量（美元）是否超过（参数CS0020所设置的数值）
        double sffd      = 0d;    //上浮幅度：弃用
        //优惠前LPR基点
        double yhqLprjd  = 0d;
        //优惠前执行利率
        double yhqZxll   = 0d;
        //优惠后LPR基点
        double yhhLprjd  = 0d;
        //优惠后执行利率
        double yhhZxll6  = 0d;
        //优惠后最终LPR基点
        double yhhLprjd7 = 0d;
        //优惠后最终执行利率
        double yhhZxll   = 0d;
        double zcfzl     = 0d;    //资产负债率
        int kljbzhwl     = 0;     //在本行开立基本账户往来年限
        int zxbljl       = 0;     //征信不良记录
        int khlx         = 1;     //客户类型 1 个人 2 企业
        int sfgwhy       = 2;     //企业客户，是否高危行业 1 是 2 否
        double cdck      = 0d;    //贷款授信+承兑敞口
        double temp      = 0d;
        double cbfd      = 0d;    //查表幅度
        int sfbmk        = 0;     //是否便民卡
        int sfbzbxdk     = 0;     //是否保证保险贷款
        int sfjzxkh      = 0;     //是否享受`小微客户定价普惠措施`
        int dkqx         = 0;     //贷款期限 1 一年期 2 五年期以上
        int ncsqdzydk      = 0;   //农村三权抵（质）押贷款
        int sfhpqy      = 0;      //是否为花炮企业

        Long timeConsumingEnd_1 = System.currentTimeMillis();
        date = new Date(timeConsumingEnd_1);
        log.info("贷款利率定价-定价计算-基础变量初始化-结束："+ simpleDateFormat.format(date));
        log.info("贷款利率定价-定价计算-基础变量初始化-总耗时："+ (timeConsumingEnd_1 - timeConsumingStart_1) / 1000 +" (s)");
        log.info("======================================================================");

        //Date   djnf = lldjsqEnty.getDjnf();
        //String zjhm = lldjsqEnty.getZjhm();
        log.info("利率定价计算-根据定价年份、身份证号查询定价申请信息(定价年份："+lldjsqEnty.getDjnf()+";证件号码："+lldjsqEnty.getZjhm()+")");
        QueryWrapper<Lldjsq> RateDjsqxx = new QueryWrapper<>();
        RateDjsqxx.eq("djnf", lldjsqEnty.getDjnf());
        RateDjsqxx.eq("zjhm", lldjsqEnty.getZjhm());
        Lldjsq djsqxx = lldjsqService.getOne(RateDjsqxx);
        if (djsqxx != null) {
            khlx = djsqxx.getKhlx();
            cdck     = djsqxx.getCdck();
            //sfbmk    = djsqxx.getSfbmk() == null ? 0 : djsqxx.getSfbmk();
            //sfbzbxdk = djsqxx.getSfbzbxdk() == null ? 0 : djsqxx.getSfbzbxdk();
            sfjzxkh  = djsqxx.getSfjzxkh() == null ? 2 : djsqxx.getSfjzxkh();
            dkqx     = djsqxx.getDkqx() == null ? 1 : djsqxx.getDkqx();
            ncsqdzydk  = djsqxx.getNcsqdzydk() == null ? 2 : djsqxx.getNcsqdzydk();
            sfhpqy  = djsqxx.getSfhpqy() == null ? 2 : djsqxx.getSfhpqy();
        }
        log.info("======================================================================");

        try {
            Long timeConsumingStart_2 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_2);
            log.info("贷款利率定价-定价计算-考核项目分值计算-开始："+ simpleDateFormat.format(date));

            CompletableFuture<List<RateZbgzxxb>> completableFuture0 = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateZbgzxxb> RateZbgzxxb = new QueryWrapper();
                RateZbgzxxb.eq("qydm", qydm);
                List<RateZbgzxxb> zbgzxxList = rateZbgzxxbService.list(RateZbgzxxb);
                return zbgzxxList;
            });

            CompletableFuture<List<RateGzbdsxx>> completableFuture1 = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateGzbdsxx> RateGzbdsxx = new QueryWrapper<>();
                RateGzbdsxx.eq("qydm", qydm);
                List<RateGzbdsxx> gzbdsxxbList = rateGzbdsxxService.list(RateGzbdsxx);
                return gzbdsxxbList;
            });

            CompletableFuture<List<RateDjsqmx>> completableFuture2 = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateDjsqmx> RateDjsqmx = new QueryWrapper<>();
                RateDjsqmx.eq("djnf", lldjsqEnty.getDjnf());
                RateDjsqmx.eq("zjhm", lldjsqEnty.getZjhm());
                List<RateDjsqmx> djsqmxbList = rateDjsqmxService.list(RateDjsqmx);
                return djsqmxbList;
            });

            CompletableFuture<List<RateZbkxxb>> completableFuture3 = CompletableFuture.supplyAsync(() ->{
                //log.info("利率定价计算 - 2 - 根据法人行社编码（区域代码）查询考核项目信息");
                QueryWrapper<RateZbkxxb> RateZbkxxb = new QueryWrapper();
                RateZbkxxb.eq("qydm", qydm);
                List<RateZbkxxb> zbkxxbList = rateZbkxxbService.list(RateZbkxxb);
                return zbkxxbList;
            });

            CompletableFuture<List<Csszxx>> completableFuture4 = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Csszxx> RateCssz = new QueryWrapper<>();
                List<Csszxx> csszList = csszxxService.list(RateCssz);
                return csszList;
            });

            List<RateZbgzxxb> zbgzxxbList = completableFuture0.get();
            List<RateGzbdsxx> gzbdsxxList = completableFuture1.get();
            List<RateDjsqmx> djsqmxList   = completableFuture2.get();
            List<RateZbkxxb> zbkxxbList   = completableFuture3.get();
            List<Csszxx> csszList         = completableFuture4.get();

            for (RateZbkxxb zbkxxb : zbkxxbList) {
                String zbid = zbkxxb.getZbid();
                //log.info("利率定价计算 - 3 - 考核项目ID：" + zbid);
                double zbgzfzKH = 0d;
                int llfs = Integer.parseInt(zbkxxb.getLlfs());
                if (llfs == 1) {
                    //单选按钮
                    //获取选择的选项对应的分值
                    //log.info("利率定价计算 - 4 - 单选按钮 - 根据定价年份、身份证号、考核指标ID查询定价申请明细信息");
                    /*QueryWrapper<RateDjsqmx> RateDjsqmx = new QueryWrapper<>();
                    RateDjsqmx.eq("djnf", lldjsqEnty.getDjnf());
                    RateDjsqmx.eq("zjhm", lldjsqEnty.getZjhm());
                    RateDjsqmx.eq("zbgzid", zbid);*/
                    List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                    RateDjsqmx djsqmx = collect.get(0);
                    if (djsqmx != null) {
                        String zbgzjg = djsqmx.getZbgzjg();
                        if ("KH00001".equals(zbid)) {
                            xydj = djsqmx.getZbgzjg();
                        }
                        List<RateZbgzxxb> zbgzxxbCollect = zbgzxxbList.stream().filter(item ->  item.getZbid().equalsIgnoreCase(zbid) && item.getZbgzid().equalsIgnoreCase(zbgzjg)).collect(Collectors.toList());
                        RateZbgzxxb zbgzxxb = zbgzxxbCollect.get(0);
                        if (zbgzxxb != null) {
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            zbgzfzKH = zbgzxxb.getZbgzfz();
                            zbgzfzKH = zbabs * zbgzfzKH;
                            view.put(zbid, zbgzxxb.getZbgzmc());
                        }
                    }
                } else if (llfs == 2) {//文本框
                    if ("KH00002".equalsIgnoreCase(zbid)) {
                        //扣分项目
                        /*QueryWrapper<RateZbgzxxb> RateZbgzxxb = new QueryWrapper();
                        RateZbgzxxb.eq("qydm", qydm);
                        RateZbgzxxb.eq("zbid", zbid);
                        List<RateZbgzxxb> zbgzxxbList = rateZbgzxxbService.list(RateZbgzxxb);*/
                        List<RateZbgzxxb> zbgzxxbList1 = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                        for (RateZbgzxxb zbgzxxb : zbgzxxbList1) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = zbgzxxb.getZbgzfz();
                            List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                            if(!collect.isEmpty()) {
                                RateDjsqmx djsqmx = collect.get(0);
                                if (djsqmx != null) {
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    if (zbgzjg == 0) {
                                        zbgzfz = 0;
                                    } else {
                                        zbgzfz = zbabs * zbgzfz * zbgzjg;
                                    }
                                    if ("GZ00009".equalsIgnoreCase(zbgzid)) {
                                        //借款人（含法人代表、主要股东及配偶）征信有不良记录
                                        zxbljl = Double.valueOf(zbgzjg).intValue();
                                    }
                                    //zbgzfzKH += zbgzfz; 20180302修改 只保留两项纪录 所以只加这两项的份
                                    if ("GZ00009".equalsIgnoreCase(zbgzid) || "GZ00010".equalsIgnoreCase(zbgzid)) {
                                        zbgzfzKH += zbgzfz;
                                    }
                                    view.put(zbgzid, zbgzfz);
                                    view.put("CS_" + zbgzid, Double.valueOf(zbgzjg).intValue());
                                }
                            }
                        }
                        if (zbgzfzKH == 0d) {
                            view.put("KFX_KH00002", "0.00");
                        } else {
                            view.put("KFX_KH00002", Double.valueOf(format2Decimal.format(zbgzfzKH)).doubleValue());
                        }
                        //xypj += zbgzfzKH;
                    }
                    if ("KH00003".equalsIgnoreCase(zbid)) {
                        //资产负债率
                        List<RateZbgzxxb> zbgzxxbCollect = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid) && item.getZbgzid().equalsIgnoreCase("GZ00015")).collect(Collectors.toList());
                        RateZbgzxxb zbgzxxb = zbgzxxbCollect.get(0);
                        if (zbgzxxb != null) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbgzfz = zbgzxxb.getZbgzfz();
                            List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = collect.get(0);
                            if (djsqmx != null) {
                                double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                view.put("CS_GZ00015", zbgzjg);
                                zcfzl = zbgzjg;
                                //modify by liuwei 2019-3-7 14:09:09 浏阳需求变动
                                //资产负债率总分2分，资产负债率在30%以内按分值100%计分，30-50%按分值60%计分，50-70%按分值30%计分，70%以上不计分。
                                if (zbgzjg <= 30) {
                                    zbgzfzKH = zbgzfz;
                                } else if (zbgzjg > 30 && zbgzjg <= 50) {
                                    zbgzfzKH = zbgzfz * 0.6;
                                } else if (zbgzjg > 50 && zbgzjg <= 70) {
                                    zbgzfzKH = zbgzfz * 0.3;
                                } else {
                                    zbgzfzKH = 0;
                                }
                            }
                        }
                        if (zbgzfzKH == 0d) {
                            view.put("KFX_KH00003", "0.00");
                        } else {
                            view.put("KFX_KH00003", zbgzfzKH);
                        }
                        List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00013")).collect(Collectors.toList());
                        RateDjsqmx djsqmx = collect.get(0);
                        if (djsqmx != null) {
                            double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                            view.put("CS_GZ00013", Double.valueOf(zbgzjg).intValue());
                        }
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00014")).collect(Collectors.toList());
                        RateDjsqmx djsqmx2 = collect.get(0);
                        if (djsqmx2 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx2.getZbgzjg() == null ? "0" : djsqmx2.getZbgzjg());
                            view.put("CS_GZ00014", Double.valueOf(zbgzjg).intValue());
                        }
                    }
                    if ("KH00005".equalsIgnoreCase(zbid)) {
                        //销售收入归行:个人客户不计分
                        //销售收入
                        List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00021")).collect(Collectors.toList());
                        RateDjsqmx djsqmx= collect.get(0);
                        if (djsqmx != null) {
                            double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                            view.put("CS_GZ00021", Double.valueOf(zbgzjg).intValue());
                        }
                        //流动负债
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00022")).collect(Collectors.toList());
                        RateDjsqmx djsqmx1= collect.get(0);
                        if (djsqmx1 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx1.getZbgzjg() == null ? "0" : djsqmx1.getZbgzjg());
                            view.put("CS_GZ00022", Double.valueOf(zbgzjg).intValue());
                        }
                        //倍数
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00023")).collect(Collectors.toList());
                        RateDjsqmx djsqmx2= collect.get(0);
                        if (djsqmx2 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx2.getZbgzjg() == null ? "0" : djsqmx2.getZbgzjg());
                            view.put("CS_GZ00023", zbgzjg);
                        }
                        if (khlx == 2) {
                            List<RateZbgzxxb> zbgzxxbCollect = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid) && item.getZbgzid().equalsIgnoreCase("GZ00023")).collect(Collectors.toList());
                            RateZbgzxxb zbgzxxb = zbgzxxbCollect.get(0);
                            if (zbgzxxb != null) {
                                String zbgzid = zbgzxxb.getZbgzid();
                                double zbgzfz = zbgzxxb.getZbgzfz();
                                collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                                RateDjsqmx djsqmx3= collect.get(0);
                                if (djsqmx3 != null) {
                                    double zbgzjg = Double.parseDouble(djsqmx3.getZbgzjg() == null ? "0" : djsqmx3.getZbgzjg());
                                    //销售收入归行总分2分，公司客户年销售收入在其流动负债的5倍以上按分值100%计分，4倍以上按分值60%计分，3倍以上按分值30%计分。
                                    if (zbgzjg >= 3 && zbgzjg < 4) {
                                        zbgzfzKH = zbgzfz * 0.3;
                                    } else if (zbgzjg >= 4 && zbgzjg < 5) {
                                        zbgzfzKH = zbgzfz * 0.6;
                                    } else if (zbgzjg >= 5) {
                                        zbgzfzKH = zbgzfz;
                                    } else {
                                        zbgzfzKH = 0;
                                    }
                                }
                            }
                        }
                    }
                    if ("KH00007".equalsIgnoreCase(zbid)) {
                        //客户存款贡献
                        //开户基本账户往来年限
                        List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00031")).collect(Collectors.toList());
                        RateDjsqmx djsqmx= collect.get(0);
                        if (djsqmx != null) {
                            double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                            kljbzhwl = Double.valueOf(zbgzjg).intValue();
                            List<RateGzbdsxx> gzbdsxxCollect = gzbdsxxList.stream()
                                    .filter(item -> item.getZbgzid().equalsIgnoreCase(djsqmx.getZbgzid()) && item.getBdskey().equals(Double.valueOf(zbgzjg).intValue()))
                                    .collect(Collectors.toList());
                            RateGzbdsxx gzbdsxx = gzbdsxxCollect.get(0);
                            if (gzbdsxx != null) {
                                view.put("CS_GZ00031", gzbdsxx.getBdsvalue());
                            }
                        }
                        //前三年第一个年度存款日平
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00033")).collect(Collectors.toList());
                        RateDjsqmx djsqmx1= collect.get(0);
                        if (djsqmx1 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx1.getZbgzjg() == null ? "0" : djsqmx1.getZbgzjg());
                            view.put("CS_GZ00033", zbgzjg);
                        }
                        //前三年第二个年度存款日平
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00034")).collect(Collectors.toList());
                        RateDjsqmx djsqmx2= collect.get(0);
                        if (djsqmx2 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx2.getZbgzjg() == null ? "0" : djsqmx2.getZbgzjg());
                            view.put("CS_GZ00034", zbgzjg);
                        }
                        //前三年第三个年度存款日平
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00035")).collect(Collectors.toList());
                        RateDjsqmx djsqmx3= collect.get(0);
                        if (djsqmx3 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx3.getZbgzjg() == null ? "0" : djsqmx3.getZbgzjg());
                            view.put("CS_GZ00035", zbgzjg);
                        }
                        //客户上一个年度在其他银行存款日平
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00036")).collect(Collectors.toList());
                        RateDjsqmx djsqmx4= collect.get(0);
                        if (djsqmx4 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx4.getZbgzjg() == null ? "0" : djsqmx4.getZbgzjg());
                            view.put("CS_GZ00036", zbgzjg);
                        }
                        //客户上一个年度在其他银行定期存款日平
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00037")).collect(Collectors.toList());
                        RateDjsqmx djsqmx5= collect.get(0);
                        if (djsqmx5 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx5.getZbgzjg() == null ? "0" : djsqmx5.getZbgzjg());
                            view.put("CS_GZ00037", zbgzjg);
                        }
                        //定价存款日平合计
                        collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase("GZ00038")).collect(Collectors.toList());
                        RateDjsqmx djsqmx6= collect.get(0);
                        if (djsqmx6 != null) {
                            double zbgzjg = Double.parseDouble(djsqmx6.getZbgzjg() == null ? "0" : djsqmx6.getZbgzjg());
                            view.put("CS_GZ00038", zbgzjg);
                        }
                        //日平存款占贷款比例
                        List<RateZbgzxxb> zbgzxxbCollect = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid) && item.getZbgzid().equalsIgnoreCase("GZ00032")).collect(Collectors.toList());
                        RateZbgzxxb zbgzxxb = zbgzxxbCollect.get(0);
                        if (zbgzxxb != null) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx7= collect.get(0);
                            if (djsqmx7 != null) {
                                double zbgzjg = Double.parseDouble(djsqmx7.getZbgzjg() == null ? "0" : djsqmx7.getZbgzjg());
                                view.put("CS_GZ00032", zbgzjg);
                                //Ⅰ.日平存款占贷款授信比例低于15%（含）时，按每1%对应分值2分，最高30分.
                                //Ⅱ.日平存款占贷款授信比例高于15%时，其中15%-40%（含）部分按每1%对应分值1分，高于40%以上部分按每1%对应分值0.8分.
                                //Ⅲ.客户存款贡献得分最高45分（暂时取消）
                                if (zbgzjg <= 15) {
                                    zbgzfzKH = zbgzjg * 2;
                                } else if (zbgzjg > 15 && zbgzjg <= 40) {
                                    zbgzfzKH = (zbgzjg - 15) * 1 + 30;
                                } else if (zbgzjg > 40) {
                                    zbgzfzKH = (zbgzjg - 40) * 0.8 + 55;
                                }
                                //取消限高
                                /*if (zbgzfzKH > 45) {
                                    zbgzfzKH = 45;
                                }*/
                            }
                            khckgxdf = zbgzfzKH;
                            khckgxdf = Double.parseDouble(format2Decimal.format(khckgxdf));
                        }
                    }
                    if ("KH00009".equalsIgnoreCase(zbid)) {
                        //客户代理业务
                        List<RateZbgzxxb> zbgzxxbList1 = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                        for (RateZbgzxxb zbgzxxb : zbgzxxbList1) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = zbgzxxb.getZbgzfz();
                            List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx= collect.get(0);
                            if (djsqmx != null) {
                                if ("GZ00043".equalsIgnoreCase(zbgzid)) {
                                    int zbgzjg = Double.valueOf(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg()).intValue();
                                    List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equalsIgnoreCase("CS0019")).collect(Collectors.toList());
                                    Csszxx cssz = CsszCollect.get(0);
                                    if (cssz != null) {
                                        rs = Integer.parseInt(cssz.getCsvalue());
                                    }
                                    if (zbgzjg >= rs) {
                                        check1 = true;
                                    }
                                    view.put("CSZ_" + zbgzid, zbgzjg);
                                    view.put("CS_" + zbgzid, 0d);


                                } else if ("GZ00044".equals(zbgzid) || "GZ00045".equals(zbgzid)) {
                                    int zbgzjg = Double.valueOf(djsqmx.getZbgzjg() == null ? "2" : djsqmx.getZbgzjg()).intValue();
                                    List<RateGzbdsxx> gzbdsxxCollect = gzbdsxxList.stream()
                                            .filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg))
                                            .collect(Collectors.toList());
                                    RateGzbdsxx gzbdsxx = gzbdsxxCollect.get(0);
                                    if (gzbdsxx != null) {
                                        String bdsValue = gzbdsxx.getBdsvalue();
                                        double bdsfz = gzbdsxx.getBdsfz();
                                        if (bdsfz == 0) {
                                            zbgzfz = 0;
                                        } else {
                                            zbgzfz = bdsfz * zbabs;
                                        }
                                        //若为个人客户，无论`是否高危行业`选择哪项，都不扣GZ00044、GZ00045的分值
                                        //若为企业客户，`是否高危行业`选择是，则会扣GZ00044、GZ00045的分值，反之则不扣
                                        sfgwhy = djsqxx.getSfgwhy() == null ? 2 : djsqxx.getSfgwhy();
                                        if (khlx == 1) {
                                            zbgzfz = 0;
                                        } else {
                                            if (sfgwhy == 1) {
                                                zbgzfz = bdsfz * zbabs;
                                            } else {
                                                zbgzfz = 0;
                                            }
                                        }
                                        view.put("CSZ_"+zbgzid,bdsValue);
                                        view.put("CS_" + zbgzid,Double.valueOf(format2Decimal.format(zbgzfz)));
                                        zbgzfzKH += zbgzfz;
                                    }
                                } else {
                                    int zbgzjg = Double.valueOf(djsqmx.getZbgzjg() == null ? "2" : djsqmx.getZbgzjg()).intValue();
                                    List<RateGzbdsxx> gzbdsxxCollect = gzbdsxxList.stream()
                                            .filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg))
                                            .collect(Collectors.toList());
                                    RateGzbdsxx gzbdsxx = gzbdsxxCollect.get(0);
                                    if (gzbdsxx != null) {
                                        double bdsfz = gzbdsxx.getBdsfz();
                                        String bdsValue = gzbdsxx.getBdsvalue();
                                        if (bdsfz == 0) {
                                            zbgzfz = 0;
                                        } else {
                                            zbgzfz = bdsfz * zbabs;
                                        }
                                        view.put("CSZ_" + zbgzid, bdsValue);
                                        view.put("CS_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzfz)));
                                    }
                                    zbgzfzKH += zbgzfz;
                                }
                            }
                        }
                    }
                    if ("KH00010".equalsIgnoreCase(zbid)) {
                        //其他业务
                        /*QueryWrapper<RateZbgzxxb> RateZbgzxxb = new QueryWrapper();
                        RateZbgzxxb.eq("qydm", qydm);
                        RateZbgzxxb.eq("zbid", zbid);
                        List<RateZbgzxxb> zbgzxxbList = rateZbgzxxbService.list(RateZbgzxxb);*/
                        List<RateZbgzxxb> zbgzxxbList1 = zbgzxxbList.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                        for (RateZbgzxxb zbgzxxb : zbgzxxbList1) {
                            double zbgzdf = 0d;
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = zbgzxxb.getZbgzfz();
                            List<RateDjsqmx> collect = djsqmxList.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx= collect.get(0);
                            if (djsqmx != null) {
                                if ("GZ00047".equals(zbgzid)) {
                                    int zbgzjg = Double.valueOf(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg()).intValue();
                                    List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equalsIgnoreCase("CS0020")).collect(Collectors.toList());
                                    Csszxx cssz = CsszCollect.get(0);
                                    if (cssz != null) {
                                        whjsl = Integer.parseInt(cssz.getCsvalue());
                                    }
                                    if (zbgzjg >= whjsl) {
                                        check2 = true;
                                    }
                                    view.put("CSZ_" + zbgzid, zbgzjg);
                                    view.put("CS_" + zbgzid, 0d);
                                }
                                if ("GZ00048".equals(zbgzid)) {
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    if (zbgzjg > 0) {
                                        if (zbgzjg > 100) {
                                            zbgzdf = -3;
                                        } else if (zbgzjg > 50 && zbgzjg <= 100) {
                                            zbgzdf = -2;
                                        } else if (zbgzjg > 20 && zbgzjg <= 50) {
                                            zbgzdf = -1;
                                        } else {
                                            zbgzdf = 0;
                                        }
                                    } else {
                                        zbgzdf = 0;
                                    }
                                    view.put("CSZ_"+zbgzid,zbgzjg);
                                    view.put("CS_"+zbgzid,zbgzdf);
                                }
                                if ("GZ00049".equals(zbgzid)) {
                                    int zbgzjg = Double.valueOf(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg()).intValue();
                                    List<RateGzbdsxx> gzbdsxxCollect = gzbdsxxList.stream()
                                            .filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg))
                                            .collect(Collectors.toList());
                                    RateGzbdsxx gzbdsxx = gzbdsxxCollect.get(0);
                                    if (gzbdsxx != null) {
                                        double bdsfz = gzbdsxx.getBdsfz();
                                        if (bdsfz == 0) {
                                            zbgzdf = 0;
                                        } else {
                                            zbgzdf = bdsfz * zbabs;
                                        }
                                    }
                                    view.put("CSZ_" + zbgzid, gzbdsxx.getBdsvalue());
                                    view.put("CS_" + zbgzid, zbgzdf);
                                }
                                if ("GZ00057".equalsIgnoreCase(zbgzid)) {
                                    // 客户能够开立我行手机银行、口袋零钱及其他第三方支付绑定我行卡但未开通的，或开通未使用的，每项扣2分
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    if (zbgzjg == 0) {
                                        zbgzdf = 0;
                                    } else {
                                        zbgzdf = zbabs * zbgzfz * zbgzjg;
                                    }
                                    view.put("CSZ_"+zbgzid,zbgzjg);
                                    view.put("CS_"+zbgzid,zbgzdf);
                                }
                                zbgzfzKH += zbgzdf;
                            }
                        }
                    }
                }
                log.info("利率定价计算:考核指标项目ID：" + zbkxxb.getZbid());
                dfhj += zbgzfzKH;

                System.out.println("=============================zbid:"+zbid);
                System.out.println("=============================zbgzfzKH:"+zbgzfzKH);
                if(!"KH00011".equalsIgnoreCase(zbid) && !"KH00012".equalsIgnoreCase(zbid)) {
                    view.put("DF_" + zbid, format2Decimal.format(zbgzfzKH));
                }
            }

            //扣分项不能超过信用等级得分
            String xydjdf       = (String) view.get("DF_KH00001"); //信用等级得分
            String zkf          = (String) view.get("DF_KH00002"); //总扣分
            double xydjdfDouble = xydjdf == null ? 0d : Double.parseDouble(xydjdf);
            double zkfDouble    = zkf == null ? 0d : Double.parseDouble(zkf);
            if (zkfDouble * -1 > xydjdfDouble) {
                view.put("DF_KH00002", xydjdfDouble * -1);
                dfhj = dfhj + (zkfDouble * -1 - xydjdfDouble);
            }
            //客户贡献（收益+存款）得分
            double zhgxf = khsygxdf + khckgxdf;
            view.put("KHGXDF", zhgxf);

            Long timeConsumingEnd_2 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_2);
            log.info("贷款利率定价-定价计算-考核项目分值计算-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-考核项目分值计算-总耗时："+ (timeConsumingEnd_2 - timeConsumingStart_2) / 1000 +" (s)");
            log.info("======================================================================");

            Long timeConsumingStart_3 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_3);
            log.info("贷款利率定价-定价计算-计算担保得分-开始："+ simpleDateFormat.format(date));

            //计算担保得分
            //Ⅰ.担保类型得分40分
            //Ⅰ.担保类型为组合担保，且一类担保占比超过60%的，则非一类担保部分按同一类B计算分值
            boolean isHyldb = false; //是否含一类担保
            boolean isHeldb = false; //是否含二类担保
            boolean isHsldb = false; //是否含三类担保
            boolean isHqtdb = false; //是否含其它担保
            boolean iscyldb = false; //是否纯一类担保
            double yldbjezh = 0d;    //总一类担保金额总和
            double yldbzb   = 0d;    //一类担保占比
            double yljdbje  = 0d;    //已累计担保金额
            double dbzdf    = 0d;    //担保总得分

            //一类A担保(有所有权)按100%计算分值，一类A担保(借用)按80%计算分值，一类B担保(有所有权)按90%计算分值，一类B担保(借用)按70%，
            //将二类A及以下担保类型分值计算下调10%，调整后，二类A担保按60%计算分值，二类B担保按50%、三类担保按40%、四类担保按30%、五类担保按20%计算分值。
            //担保类型得分
            HashMap dbdfMap = new HashMap();
            dbdfMap.put("1", "45");
            dbdfMap.put("2", "36");
            dbdfMap.put("9", "40.5");
            dbdfMap.put("10", "31.5");
            dbdfMap.put("3", "27");
            dbdfMap.put("4", "22.5");
            dbdfMap.put("5", "18");
            dbdfMap.put("6", "13.5");
            dbdfMap.put("7", "9");
            dbdfMap.put("8", "0");
            int i = 1;
            QueryWrapper<RateDbxxgl> RateDbxxgl = new QueryWrapper();
            RateDbxxgl.eq("djnf", lldjsqEnty.getDjnf());
            RateDbxxgl.eq("zjhm", lldjsqEnty.getZjhm());
            RateDbxxgl.orderByAsc("dblx");
            List<RateDbxxgl> rateDbxxglList = rateDbxxglService.list(RateDbxxgl);
            for (RateDbxxgl dbxxgl : rateDbxxglList) {
                double tmpdf  = 0d;
                int rowNum    = i;
                double sjdbje = dbxxgl.getSjdbje();
                String dblx   = dbxxgl.getDblx();
                // Step1.计算总一类担保金额
                if ("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)) {
                    yldbjezh += sjdbje;
                    //Ⅰ.判断是否含有一类担保
                    isHyldb = true;
                } else if ("3".equals(dblx) || "4".equals(dblx)) {
                    //Ⅱ.判断是否含有二类担保
                    isHeldb = true;
                } else if ("5".equals(dblx)) {
                    //Ⅲ.判断是否含有三类担保
                    isHsldb = true;
                } else if ("6".equals(dblx) || "7".equals(dblx) || "8".equals(dblx)) {
                    //Ⅳ.判断是否含有其它担保
                    isHqtdb = true;
                }
                if (!StringUtils.isEmpty(dblx)) {
                    String dbdf = (String) dbdfMap.get(dblx);
                    if (cdck > 0) {
                        if (yljdbje == 0 && rowNum == 1) {
                            yljdbje = cdck - sjdbje;
                        } else {
                            if (yljdbje > 0) {
                                if (sjdbje >= yljdbje) {
                                    sjdbje = yljdbje;
                                } else {
                                    yljdbje = yljdbje - sjdbje;
                                }
                            } else {
                                sjdbje = 0;
                            }
                        }
                        double jfbfb = Double.parseDouble(dbdf);
                        //担保类型得分 = 实际担保金额/授信额度*对应分数
                        tmpdf = jfbfb * (sjdbje / cdck);
                        if (rateDbxxglList.size() == 1) {
                            if (tmpdf > jfbfb) {
                                tmpdf = jfbfb;
                            }
                        }
                        if (tmpdf > 45) {
                            tmpdf = 45;
                        }
                    }
                }
                dbzdf += tmpdf;
                i++;
            }
            if (isHyldb && !isHeldb && !isHsldb && !isHqtdb) {
                // Ⅳ.判断是否为纯一类担保
                iscyldb = true;
            }
            //计算一类担保占比：一类担保金额总和 / 承兑敞口(授信金额) * 100
            yldbzb = (yldbjezh / cdck) * 100;
            //Step4.担保类型为组合担保，且一类担保占比超过60%的，则非一类担保部分按同一类B计算分值
            //Ⅰ.必须含一类担保且包含其它任一类型担保
            if (isHyldb && (isHeldb || isHsldb || isHqtdb)) {
                if (yldbzb >= 60) {// 一类担保占比超过60%
                    double dbzdf_tmp = 0d;
                    for (RateDbxxgl dbxxgl : rateDbxxglList) {
                        double tmpdblxdf = 0d;                 //担保类型得分
                        double sjdbje    = dbxxgl.getSjdbje(); //客户实际担保金额
                        String khsxdblx  = dbxxgl.getDblx();   //客户所选担保类型
                        String dbdf      = (String) dbdfMap.get(khsxdblx);                 //对应担保类型所对应分数
                        double dydbdf    = Double.parseDouble(dbdf);                       //担保类型对应担保得分
                        double ylbdbdf   = Double.parseDouble((String) dbdfMap.get("10")); //一类B担保(借用)得分
                        if (!("1".equals(khsxdblx) || "2".equals(khsxdblx) || "9".equals(khsxdblx) || "10".equals(khsxdblx))) {
                            // Ⅱ.当担保类型为非一类时，担保得分=一类B担保(借用)=28
                            dydbdf = ylbdbdf;
                        }
                        // Ⅲ.担保类型得分=对应分数*（实际担保金额/授信额度）
                        tmpdblxdf = dydbdf * (sjdbje / cdck);
                        dbzdf_tmp += tmpdblxdf;
                        dbzdf = dbzdf_tmp;
                    }
                }
            }
            if (dbzdf > 45) {
                dbzdf = 45;
            }
            view.put("dbzdf", format2Decimal.format(dbzdf));
            log.info("担保类型总得分："+dbzdf);
            dfhj += dbzdf;

            Long timeConsumingEnd_3 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_3);
            log.info("贷款利率定价-定价计算-计算担保得分-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-计算担保得分-总耗时："+ (timeConsumingEnd_3 - timeConsumingStart_3) / 1000 +" (s)");
            log.info("======================================================================");

            Long timeConsumingStart_4 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_4);
            log.info("贷款利率定价-定价计算-担保类型判断-开始："+ simpleDateFormat.format(date));

            boolean ishasyldb  = false; //是否有一类担保
            boolean ishaseldb  = false; //是否有二类担保
            boolean ishassldb  = false; //是否有三类担保
            boolean ishasqtdb  = false; //是否有其它担保

            boolean isyldb     = false; //是否是纯一类担保
            boolean isbfyldb   = false; //是否是部分一类担保
            boolean iscyldbA   = false; //是否纯一类A担保
            boolean ishasyldbA = false; //是否含一类A担保
            boolean ishasyldbB = false; //是否含一类B担保
            BigDecimal yldbl   = new BigDecimal(0); //一类担保率
            BigDecimal zsjdbje = new BigDecimal(0); //总实际担保金额
            BigDecimal zyldbje = new BigDecimal(0); //总一类担保金额

            ArrayList<String> dblxArray = new ArrayList<>();
            for(RateDbxxgl dbxxgl : rateDbxxglList) {
                String dblx = dbxxgl.getDblx();
                dblxArray.add(dblx);
                BigDecimal sjdbje = new BigDecimal(dbxxgl.getSjdbje());
                zsjdbje = zsjdbje.add(sjdbje);
                BigDecimal dbl = new BigDecimal(dbxxgl.getDbl());
                if ("1".equals(dblx) || "2".equals(dblx) || "9".equals(dblx) || "10".equals(dblx)) {
                    zyldbje = zyldbje.add(sjdbje);
                }
            }
            log.info("担保类型dblxArray:" + dblxArray);
            for (String dblx : dblxArray) {
                if ("1".equals(dblx) || "2".equals(dblx)) {//一类A(有所有权)/一类A(借用)担保
                    //含有一类A担保
                    ishasyldbA = true;
                    //含有一类担保
                    ishasyldb = true;
                    //isbfyldb = true;
                }
                if ("9".equals(dblx) || "10".equals(dblx)) {//一类B(有所有权)/一类B(借用)担保
                    //含有一类B担保
                    ishasyldbB = true;
                    //含有一类担保
                    ishasyldb = true;
                    //isbfyldb = true;
                    //iscyldbA = false;
                }
                if ("3".equals(dblx) || "4".equals(dblx)) {//二类A/二类B担保
                    //含有二类担保
                    ishaseldb = true;
                    //iscyldbA = false;
                    //isyldb = false;
                }
                if ("5".equals(dblx) || "6".equals(dblx) || "7".equals(dblx)) {//三类/四类/五类担保
                    //含有三类担保
                    ishassldb = true;
                    //iscyldbA = false;
                    //isyldb = false;
                }
                if ("8".equals(dblx)) {//信用担保
                    //含有其它担保
                    ishasqtdb = true;
                    //iscyldbA = false;
                    //isyldb = false;
                }
            }

            log.info("是否有一类担保："+ishasyldb);
            log.info("是否有二类担保："+ishaseldb);
            log.info("是否有三类担保："+ishassldb);
            log.info("是否有其它担保："+ishasqtdb);
            log.info("是否含一类A担保："+ishasyldbA);
            log.info("是否含一类B担保："+ishasyldbB);

            // 判断是否纯一类担保
            if (ishasyldb && !ishaseldb && !ishassldb && !ishasqtdb) {
                isyldb = true;
            }
            // 判断是否纯一类A担保
            if (ishasyldbA && !ishasyldbB && !ishaseldb && !ishassldb && !ishasqtdb) {
                iscyldbA = true;
            }
            // 判断是否部分一类担保
            if (ishasyldb && (ishaseldb || ishassldb || ishasqtdb)) {
                isbfyldb = true;
            }
            if (zsjdbje.compareTo(new BigDecimal(0)) > 0) {
                yldbl = zyldbje.divide(zsjdbje, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
            }

            log.info("是否是纯一类担保："+isyldb);
            log.info("是否纯一类A担保："+iscyldbA);
            log.info("是否是部分一类担保："+isbfyldb);

            Long timeConsumingEnd_4 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_4);
            log.info("贷款利率定价-定价计算-担保类型判断-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-担保类型判断-总耗时："+ (timeConsumingEnd_4 - timeConsumingStart_4) / 1000 +" (s)");
            log.info("======================================================================");

            //得分合计
            dfhj = Double.parseDouble(format0Decimal.format(dfhj));
            if (dfhj < 0) {
                dfhj = 0;
            }

            view.put("dfhj", format0Decimal.format(dfhj));
            log.info("得分合计："+dfhj+"(分)");
            log.info("======================================================================");

            Long timeConsumingStart_5 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_5);
            log.info("贷款利率定价-定价计算-参数设置值查询-开始："+ simpleDateFormat.format(date));

            double lprbzz    = 0d;    //LPR利率(参数设置)
            double CS01Value = 25d;   //浮动查算对照最低分数
            double CS02Value = 125d;  //浮动查算对照最高分数
            double CS03Value = 485d;  //第二十条 对户额30万元以上贷款（含便民卡）利率设置上限：一年期贷款最高按1年期LPR加485个基点执行
            double CS04Value = 515d;  //第二十条 对户额30万元以上贷款（含便民卡）利率设置上限：五年期以上贷款最高按5年期以上LPR加515个基点执行
            double CS05Value = 50;    //文件第十五条限制第一点：担保物全部为一类担保时，贷款利率的最高上浮幅度
            double CS06Value = 60;    //文件第十五条限制第二点：担保物不完全是一类担保但符合情况时，贷款利率的最高上浮幅度
            double CS07Value = 55;    //文件第十六条限制，贷款利率的最高上浮幅度
            double CS08Value = 10;    //便民卡上浮幅度
            double CS09Value = 10;    //保证保险贷款上浮幅度
            double CS10Value = 90;    //最大上浮幅度
            double CS12Value = 3.55d; //1年期LPR利率
            double CS13Value = 4.20d; //5年期以上LPR利率
            double CS14Value = 0d;    //户额30万元以上客户贷款定价，在计算出执行利率后，再优惠的基点
            double CS15Value = 0d;    //他行存款日平计算比例
            String CS17Value = "2023-2-21"; //Lpr利率报价日期
            double CS21Value = 4.3500d; //第三十三条 （四）本行其他类贷款利率设年利率4.35%的下限，经本办法测算后，低于或等于年利率4.35%的客户，按年利率4.35%执行。
            double CS22Value = 50d;   //贷款利率定价最低基点BP
            double CS23Value = 100d;  //贷款户额在100(含)-500万(含)小微客户,提供担保类型全为一类A,经利率定价测算后，再优惠下调基点
            double CS24Value = 50d;   //贷款户额在100(含)-500万(含)小微客户,提供担保类型不全为一类A,经利率定价测算后，再优惠下调基点
            double CS25Value = 7.92d; //贷款利率定价系统中的最高执行利率(%)
            double CS26Value = 10d;   //代发工资本年在本行，代发人数不低于限定人数的客户，在计算出执行利率后，再优惠下调基点数
            double CS27Value = 10d;   //实际控制企业有国际贸易业务，外汇结算量大于等于指定美元数额的客户，在计算出执行利率后，再优惠下调基点数
            double CS28Value = 4.35d; //1年期基准利率
            double CS29Value = 4.75d; //5年期以上基准利率
            double CS30Value = 5.3250d; //第二十六条  经利率定价测算后，若定价结果低于年利率5.325%，即按定价结果执行，不再下调基点；若优惠基点下调后低于年利率5.325%，即按年利率5.325%执行。
            double CS31Value = 202.50d; //第十六条 户额30万元(含)以内担保类贷款客户按担保类型确定贷款执行利率。担保类型为一类担保的客户，按1年期LPR加202.50个基点执行
            double CS32Value = 252.50d; //第十六条 户额30万元(含)以内担保类贷款客户按担保类型确定贷款执行利率。担保类型为一类担保以下的客户，按1年期LPR加252.50个基点执行
            double CS33Value = 267.50d; //第二十一条 对客户提供全部为一类担保的贷款设置上限，五年期（含）以内贷款最高加267.5个基点
            double CS34Value = 270.00d; //第二十一条 对客户提供全部为一类担保的贷款设置上限，五年期以上贷款最高加270个基点
            double CS35Value = 267.50d; //第二十一条 对客户提供不完全是一类担保，利率设置上限，五年期（含）以内贷款最高加311个基点
            double CS36Value = 270.00d; //第二十一条 对客户提供不完全是一类担保，利率设置上限，五年期以上贷款最高加319个基点
            double CS37Value = 289.25d; //第二十二条  对客户提供不完全是一类担保，若一年期贷款计算出加点低于289.25个基点，加点后不高于289.25个基点
            double CS38Value = 21.75d; //第二十二条 对客户提供不完全是一类担保，若一年期贷款计算出加点低于289.25个基点，则在此基础上再加21.75个基点，加点后不高于289.25个基点
            double CS39Value = 294.50d; //第二十二条 对客户提供不完全是一类担保，若五年期以上贷款计算出加点低于294.5个基点，加点后不高于294.5个基点
            double CS40Value = 24.50d; //第二十二条 对客户提供不完全是一类担保，若五年期以上贷款计算出加点低于294.5个基点，则在此基础上再加24.5个基点，加点后不高于294.5个基点
            double CS41Value = 332.50d; //第三十三条 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：五年期（含）以内贷款最高按1年期LPR加332.5个基点执行
            double CS42Value = 365.00d; //第三十三条 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：五年期以上贷款最高按5年期以上LPR加365.00个基点执行
            QueryWrapper<Csszxx> rateCssz = new QueryWrapper();
            rateCssz.in("csid",
                    "CS0012", "CS0013", "CS0014", "CS0015", "CS0017", "CS0021", "CS0022",
                    "CS0023", "CS0024", "CS0025", "CS0026", "CS0027", "CS0028", "CS0029", "CS0030", "CS0031", "CS0032", "CS0033", "CS0034", "CS0035", "CS0036",
                    "CS0037", "CS0038", "CS0039", "CS0040", "CS0041", "CS0042");
            List<Csszxx> csszxxList = csszxxService.list(rateCssz);
            for (Csszxx cssz : csszxxList) {
                String rateCsid = cssz.getCsid();
                if ("CS0012".equals(rateCsid)) {//1-5年期LPR参数
                    CS12Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0013".equals(rateCsid)) {//5年以上LPR参数
                    CS13Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0014".equals(rateCsid)) {//LPR基点优惠参数
                    CS14Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0015".equals(rateCsid)) {
                    CS15Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0017".equals(rateCsid)) {//LPR利率报价日期
                    CS17Value = cssz.getCsvalue();
                } else if ("CS0021".equals(rateCsid)) {
                    CS21Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0022".equals(rateCsid)) {
                    CS22Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0023".equals(rateCsid)) {
                    CS23Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0024".equals(rateCsid)) {
                    CS24Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0025".equals(rateCsid)) {
                    CS25Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0026".equals(rateCsid)) {
                    CS26Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0027".equals(rateCsid)) {
                    CS27Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0028".equals(rateCsid)) {
                    CS28Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0029".equals(rateCsid)) {
                    CS29Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0030".equals(rateCsid)) {
                    CS30Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0031".equals(rateCsid)) {
                    CS31Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0032".equals(rateCsid)) {
                    CS32Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0033".equals(rateCsid)) {
                    CS33Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0034".equals(rateCsid)) {
                    CS34Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0035".equals(rateCsid)) {
                    CS35Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0036".equals(rateCsid)) {
                    CS36Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0037".equals(rateCsid)) {
                    CS37Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0038".equals(rateCsid)) {
                    CS38Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0039".equals(rateCsid)) {
                    CS39Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0040".equals(rateCsid)) {
                    CS40Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0041".equals(rateCsid)) {
                    CS41Value = Double.parseDouble(cssz.getCsvalue());
                } else if ("CS0042".equals(rateCsid)) {
                    CS42Value = Double.parseDouble(cssz.getCsvalue());
                }
            }
            view.put("thckrpjsbl", CS15Value); // 他行存款日平计算比例

            Long timeConsumingEnd_5 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_5);
            log.info("贷款利率定价-定价计算-参数设置值查询-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-参数设置值查询-总耗时："+ (timeConsumingEnd_5 - timeConsumingStart_5) / 1000 +" (s)");
            log.info("======================================================================");

            Long timeConsumingStart_6 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_6);
            log.info("贷款利率定价-定价计算-方案甄别特殊处理-开始："+ simpleDateFormat.format(date));
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            Long timeConsumingStart_6_1 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_6_1);
            log.info("贷款利率定价-定价计算-方案基础要素准备-开始："+ simpleDateFormat.format(date));

            double yldblDouble = yldbl.doubleValue();
            //String remind16 = "第十六条 户额30万元（含）以内担保类型为一类担保的客户，按1年期LPR加"+CS31Value+"个基点上限执行；担保类型为一类担保以下的客户，按1年期LPR加"+CS32Value+"个基点上限执行。";
            String remind20 = "第二十条 对户额30万元以上贷款（含便民卡）利率设置上限：五年期（含）以内贷款最高按1年期LPR加"+CS03Value+"个基点执行；五年期以上贷款最高按5年期以上LPR加"+CS04Value+"个基点执行。";
            String remind21A = "第二十一条 对客户提供全部为一类担保的贷款设置上限，五年期（含）以内贷款最高加"+CS33Value+"个基点、五年期以上贷款最高加"+CS34Value+"个基点。";
            String remind21B = "第二十一条 对客户提供不完全是一类担保，但符合以下情况，利率设置上限，五年期（含）以内贷款最高加"+CS35Value+"个基点，五年期以上贷款最高加"+CS36Value+"个基点。";
            String remind22 = "第二十二条 对客户提供不完全是一类担保,且不符合以下条件的,按下列要求执行：若一年期贷款计算出加点低于"+CS37Value+"个基点,则在此基础上再加"+CS38Value+"个基点,加点后不高于"+CS37Value+"个基点；若五年期以上贷款计算出加点低于"+CS39Value+"个基点,则在此基础上再加"+CS40Value+"个基点,加点后不高于"+CS39Value+"个基点。";
            String remind23 = "第二十三条 对贷款户额在100-1000万（含）小微客户，实行差异化普惠利率定价。";
            String remind24 = "第二十四条 对提供担保类型全为一类A的该类客户，经利率定价测算后，再优惠下调100个基点。";
            String remind25 = "第二十五条 对提供担保类型不全为一类A的该类客户，经利率定价测算后，再优惠下调50个基点。";
            String remind26A = "第二十六条 经利率定价测算后，若定价结果低于年利率5.325%，即按定价结果执行，不再下调基点。";
            String remind26B = "第二十六条 经利率定价测算后，若优惠基点下调后低于年利率5.325%，即按年利率5.325%执行。";
            String remind27 = "第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。";
            String remind29 = "第二十九条 代发工资本年在本行，代发人数不低于20人的客户，在计算出执行利率后，再优惠下调10个基点。";
            String remind30 = "第三十条 实际控制企业有国际贸易业务，外汇结算量达50万美元及以上的客户，在计算出执行利率后，再优惠下调10个基点。";
            String remind33 = "第三十三条 本行其他类贷款利率设年利率"+CS21Value+"的下限，经本办法测算后，低于或等于年利率\\"+CS21Value+"的客户，按年利率"+CS21Value+"执行。";
            String remind41 = "第三十三条 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：五年期（含）以内贷款最高按1年期LPR加"+CS41Value+"个基点执行。";
            String remind42 = "第三十三条 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：五年期以上贷款最高按5年期以上LPR加"+CS42Value+"个基点执行。";
            String remindA  = "* 得分合计低于"+CS01Value+"分，贷款授信30万以内按第十六条设定按LPR加基点（BP）,贷款授信30万以上按第二十条设定按LPR加基点（BP）。";
            String remindB  = "* 得分合计高于"+CS02Value+"分，优惠后执行利率为"+CS21Value+"%，优惠后LPR基点=优惠后执行利率-对应档次LPR。";
            String remindC  = "* 未在浮动查算对照信息内添加分值对应的按LPR加基点(BP)值，设定最低执行利率为4.35%。";

            double jzll = 0d;
            if (dkqx == 1) {
                lprbzz = CS12Value;
                jzll = CS28Value / 100; //1年期
            } else {
                lprbzz = CS13Value;
                jzll = CS29Value / 100; //5年期以上
            }
            /**
             * 便民卡要素联动已弃用
             * 2022年12月7日 星期三
             * @Author Penghr
             * 客户确认：浏阳农商银行-普惠金融部
             */
            //如果是便民卡，则不区分贷款期限，默认为1年期
            //if (sfbmk == 1) {
            //    jzll   = CS28Value / 100;
            //    lprbzz = CS12Value;
            //}
            view.put("jjll", format2Decimal.format(jzll * 100));
            view.put("lprll", format2Decimal.format(lprbzz));
            view.put("dyyhjdcs", format4Decimal.format(lprbzz));

            Long timeConsumingEnd_6_1 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_6_1);
            log.info("贷款利率定价-定价计算-方案基础要素准备卡-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-方案基础要素准备-总耗时："+ (timeConsumingEnd_6_1 - timeConsumingStart_6_1) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            log.info("一类担保部分实际担保金额："+yldbjezh);

            Long timeConsumingStart_6_2 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_6_2);
            log.info("贷款利率定价-定价计算-浮动查算对照表-开始："+ simpleDateFormat.format(date));

            //按LPR加基点(BP) = (一年期基准利率 * 得分合计对应浮动幅度) - 一年期LPR利率（理论上是这样算，但暂时使用`浮动查算对照表`内数据）
            double aLprJJdbp = 0d;
            QueryWrapper<Fdcsdzb> RateFdcsdzb = new QueryWrapper<>();
            RateFdcsdzb.eq("djdf", dfhj);
            RateFdcsdzb.eq("dkqx", dkqx);
            RateFdcsdzb.lt("dksxje_begin", cdck);
            RateFdcsdzb.ge("dksxje_end", cdck);
            Fdcsdzb form = iFdcsdzbService.getOne(RateFdcsdzb);
            if (form != null) {
                aLprJJdbp = form.getDyjdbp() == null ? 0d : form.getDyjdbp().doubleValue();
            }/* else {
                return Result.error("未查找到浮动查算对照信息，请联系管理员前往[参数设置管理/浮动查算对照表]设置！");
            }*/

            Long timeConsumingEnd_6_2 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_6_2);
            log.info("贷款利率定价-定价计算-浮动查算对照表-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-浮动查算对照表-总耗时："+ (timeConsumingEnd_6_2 - timeConsumingStart_6_2) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            Long timeConsumingStart_6_3 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_6_3);
            log.info("贷款利率定价-定价计算-方案处理-开始："+ simpleDateFormat.format(date));

            if (aLprJJdbp == 0d) {
                // 当`浮动查算对照表`内未设置相对应的按LPR加基点（BP）
                // 则有2种情况：
                // 1.分数低于25分或高于125分，结果按方案内标注的办法计算
                // 2.确实是没有设置或遗漏，则按照最低执行利率4.35%执行
                if (dfhj < CS01Value) {
                    // 得分低于25分
                    view.put("remindA", remindA);
                    if (cdck <= 30) {
                        log.info("得分低于25分，贷款授信额度小于等于30万元");
                        // 贷款授信30万以内按第十六条设定按LPR加基点（BP）
                        // 第十六条 户额30万元（含）以内担保类型为一类担保的客户，按1年期LPR加202.5个基点上限执行；担保类型为一类担保以下的客户，按1年期LPR加252.5个基点上限执行。
                        if (iscyldb) {
                            yhqLprjd = CS31Value;
                        } else {
                            yhqLprjd = CS32Value;
                        }
                    } else {
                        log.info("得分低于25分，贷款授信额度大于30万元");
                        // 贷款授信30万以上按第二十条设定按LPR加基点（BP）
                        // 第二十条 对户额30万元以上贷款（含便民卡）利率设置上限：五年期（含）以内贷款最高按1年期LPR加485个基点执行；五年期以上贷款最高按5年期以上LPR加515个基点执行。
                        if (dkqx == 1) {
                            yhqLprjd = CS03Value;
                        } else {
                            yhqLprjd = CS04Value;
                        }
                    }

                    //优惠前执行利率  = 一年期LPR利率 + (按LPR加基点(BP) / 100)
                    yhqZxll = lprbzz + (yhqLprjd / 100);
                    yhhLprjd = yhqLprjd;

                    //第六章 其他定价普惠措施
                    //第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。
                    if (cdck > 30) {
                        view.put("remind27", remind27);
                        yhhLprjd -= CS14Value;
                    }

                    // 2023年2月13日
                    // （三）对农村“三权”抵（质）押贷款给予利率优惠。
                    // 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：
                    //（1）五年期（含）以内贷款最高按1年期LPR加332.5个基点执行，
                    //（2）五年期以上贷款最高按5年期以上LPR加365个基点执行。
                    if (ncsqdzydk == 1) {
                        if (dkqx == 1) {
                            view.put("remind41", remind41);
                            if (yhhLprjd > CS41Value) yhhLprjd = CS41Value;
                        } else {
                            view.put("remind42", remind42);
                            if (yhhLprjd > CS42Value) yhhLprjd = CS42Value;
                        }
                    }

                    // 2023年2月13日
                    // (四）对提供一类和其他类联合抵押的花炮企业“一类”担保贷款部分给予利率优惠。
                    //花炮企业提供“一类”担保贷款部分再给予利率优惠20BP,但不重复享受我行其他利率优惠政策。优惠政策享受就高不就低
                    double yldbYhhLprjd = yhhLprjd;
                    if (sfjzxkh == 2 && sfhpqy == 1) {
                        if (isbfyldb || iscyldb) {
                            yldbYhhLprjd -= 20;
                            yhhZxll = lprbzz + (yldbYhhLprjd / 100);
                            String note = "花炮企业客户优惠政策\"一类担保部分实际担保金额" + format2Decimal.format(yldbjezh) + "万元，可再优惠20BP\"，优惠部分执行利率为" + format4Decimal.format(yhhZxll) + "%";
                            view.put("note", note);
                            //计算、重新计算、查看、打印 花炮企业优惠政策信息展示标识 1 展示 2 不展示
                            view.put("sfhpqyDisplay", "1");
                        } else {
                            view.put("sfhpqyDisplay", "2");
                        }
                    } else {
                        view.put("sfhpqyDisplay", "2");
                    }

                    // 优惠后执行利率 = 对应档次LPR利率 + （优惠后LPR基点/100）
                    yhhZxll6 = lprbzz + (yhhLprjd / 100);
                    // 优惠后最终执行利率 = 对应档次LPR利率 + （优惠后LPR基点/100）
                    yhhZxll = lprbzz + (yhhLprjd / 100);
                    // 优惠后最终LPR基点 = （优惠后最终执行利率-对应档次LPR利率）/100
                    yhhLprjd7 = (yhhZxll - lprbzz) * 100;
                } else if (dfhj > CS02Value) {
                    // 得分高于125分
                    view.put("remindB", remindB);
                    // 优惠前LPR基点
                    yhqLprjd = 0d;
                    // 优惠前执行利率
                    yhqZxll = 0d;
                    // 优惠后最终执行利率
                    yhhZxll = CS21Value;
                    // 优惠后LPR基点 = （优惠后最终执行利率-对应档次LPR利率）/100
                    yhhLprjd = (yhhZxll - lprbzz) * 100;

                    //第六章 其他定价普惠措施
                    //第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。
                    if (cdck > 30) {
                        view.put("remind27", remind27);
                        yhhLprjd -= CS14Value;
                    }

                    // 2023年2月13日
                    // （三）对农村“三权”抵（质）押贷款给予利率优惠。
                    // 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：
                    //（1）五年期（含）以内贷款最高按1年期LPR加332.5个基点执行，
                    //（2）五年期以上贷款最高按5年期以上LPR加365个基点执行。
                    if (ncsqdzydk == 1) {
                        if (dkqx == 1) {
                            view.put("remind41", remind41);
                            if (yhhLprjd > CS41Value) yhhLprjd = CS41Value;
                        } else {
                            view.put("remind42", remind42);
                            if (yhhLprjd > CS42Value) yhhLprjd = CS42Value;
                        }
                    }

                    // 2023年2月13日
                    // (四）对提供一类和其他类联合抵押的花炮企业“一类”担保贷款部分给予利率优惠。
                    //花炮企业提供“一类”担保贷款部分再给予利率优惠20BP,但不重复享受我行其他利率优惠政策。优惠政策享受就高不就低
                    double yldbYhhLprjd = yhhLprjd;
                    if (sfjzxkh == 2 && sfhpqy == 1) {
                        if (isbfyldb || iscyldb) {
                            yldbYhhLprjd -= 20;
                            yhhZxll = lprbzz + (yldbYhhLprjd / 100);
                            String note = "花炮企业客户优惠政策\"一类担保部分实际担保金额" + format2Decimal.format(yldbjezh) + "万元，可再优惠20BP\"，优惠部分执行利率为" + format4Decimal.format(yhhZxll) + "%";
                            view.put("note", note);
                            //计算、重新计算、查看、打印 花炮企业优惠政策信息展示标识 1 展示 2 不展示
                            view.put("sfhpqyDisplay", "1");
                        } else {
                            view.put("sfhpqyDisplay", "2");
                        }
                    } else {
                        view.put("sfhpqyDisplay", "2");
                    }

                    // 优惠后执行利率 = 对应档次LPR利率 + （优惠后LPR基点/100）
                    yhhZxll6 = lprbzz + (yhhLprjd / 100);
                    // 优惠后最终LPR基点 = （优惠后最终执行利率 - 对应档次LPR利率）/100
                    yhhLprjd7 = (yhhZxll - lprbzz) * 100;
                } else {
                    // 未设置得分对应基点
                    view.put("remindC", remindC);
                    // 优惠前执行利率 = 最低下限利率
                    yhqZxll = CS21Value;
                    // 优惠前LPR基点 = （优惠前执行利率 - 对应档次LPR利率）* 100
                    yhqLprjd = (yhqZxll - lprbzz) * 100;
                    // 优惠后LPR基点 = 优惠前LPR基点
                    yhhLprjd = yhqLprjd;

                    //第六章 其他定价普惠措施
                    //第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。
                    if (cdck > 30) {
                        view.put("remind27", remind27);
                        yhhLprjd -= CS14Value;
                    }

                    // 2023年2月13日
                    // （三）对农村“三权”抵（质）押贷款给予利率优惠。
                    // 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：
                    //（1）五年期（含）以内贷款最高按1年期LPR加332.5个基点执行，
                    //（2）五年期以上贷款最高按5年期以上LPR加365个基点执行。
                    if (ncsqdzydk == 1) {
                        if (dkqx == 1) {
                            view.put("remind41", remind41);
                            if (yhhLprjd > CS41Value) yhhLprjd = CS41Value;
                        } else {
                            view.put("remind42", remind42);
                            if (yhhLprjd > CS42Value) yhhLprjd = CS42Value;
                        }
                    }

                    // 2023年2月13日
                    // (四）对提供一类和其他类联合抵押的花炮企业“一类”担保贷款部分给予利率优惠。
                    //花炮企业提供“一类”担保贷款部分再给予利率优惠20BP,但不重复享受我行其他利率优惠政策。优惠政策享受就高不就低
                    double yldbYhhLprjd = yhhLprjd;
                    if (sfjzxkh == 2 && sfhpqy == 1) {
                        if (isbfyldb || iscyldb) {
                            yldbYhhLprjd -= 20;
                            yhhZxll = lprbzz + (yldbYhhLprjd / 100);
                            String note = "花炮企业客户优惠政策\"一类担保部分实际担保金额" + format2Decimal.format(yldbjezh) + "万元，可再优惠20BP\"，优惠部分执行利率为" + format4Decimal.format(yhhZxll) + "%";
                            view.put("note", note);
                            //计算、重新计算、查看、打印 花炮企业优惠政策信息展示标识 1 展示 2 不展示
                            view.put("sfhpqyDisplay", "1");
                        } else {
                            view.put("sfhpqyDisplay", "2");
                        }
                    } else {
                        view.put("sfhpqyDisplay", "2");
                    }
                    // 优惠后执行利率 = 优惠前执行利率
                    yhhZxll6 = yhqZxll;
                    // 优惠后最终LPR基点 = 优惠后LPR基点
                    yhhLprjd7 = yhhLprjd;
                    // 优惠后最终执行利率 = 优惠前执行利率
                    yhhZxll = yhqZxll;
                }
            } else {
                // 当`浮动查算对照表`内已设置相对应的按LPR加基点（BP）
                yhqLprjd = aLprJJdbp;
                yhqZxll = lprbzz + (yhqLprjd / 100);
                yhhLprjd = yhqLprjd;
                // Step1.先对按LPR加基点（BP）进行限高
                // 第十六条 户额30万元（含）以内担保类贷款客户按担保类型确定贷款执行利率。担保类型为一类担保的客户，按1年期LPR加217.5个基点执行；担保类型为一类担保以下的客户，按1年期LPR加267.5个基点执行。
                // 第二十条 对户额30万元以上贷款（含便民卡）利率设置上限：一年期贷款最高按1年期LPR加485个基点执行；五年期以上贷款最高按5年期以上LPR加515个基点执行
                if (cdck <= 30) {
                    //view.put("remind16", remind16);
                    if (iscyldb) {
                        if (yhhLprjd > CS31Value) {
                            yhhLprjd = CS31Value;
                        }
                    } else {
                        if (yhhLprjd > CS32Value) {
                            yhhLprjd = CS32Value;
                        }
                    }
                } else {
                    view.put("remind20", remind20);
                    if (dkqx == 1) {
                        if (yhhLprjd > CS03Value) {
                            yhhLprjd = CS03Value;
                        }
                    } else {
                        if (yhhLprjd > CS04Value) {
                            yhhLprjd = CS04Value;
                        }
                    }
                }
                // 第二十一条 对客户提供全部为一类担保的贷款设置上限，一年期贷款最高加252.5个基点、五年期以上贷款最高加255个基点。
                if (iscyldb) {
                    view.put("remind21A", remind21A);
                    if (dkqx == 1) {
                        if (yhhLprjd > CS33Value) {
                            yhhLprjd = CS33Value;
                        }
                    } else {
                        if (yhhLprjd > CS34Value) {
                            yhhLprjd = CS34Value;
                        }
                    }
                }
                //第二十一条 对客户提供不完全是一类担保，但符合下列情况，利率设置上限，五年期（含）以内贷款最高加311个基点，五年期以上贷款最高加319个基点。
                // 本年度贷款授信100-200（含）万元客户，提供的一类担保占总担保的比例在80%（含）以上；
                // 本年度贷款授信200-500（含）万元客户，提供的一类担保占总担保的比例在70%（含）以上；
                // 本年度贷款授信500-1000（含）万元客户，提供的一类担保占总担保的比例在60%（含）以上；
                // 本年度贷款授信1000万元以上客户，提供的一类担保占总担保的比例在50%（含）以上。
                if (isbfyldb) {
                    if ((cdck > 100 && cdck <= 200 && yldblDouble >= 80) ||
                            (cdck > 200 && cdck <= 500 && yldblDouble >= 70) ||
                            (cdck > 500 && cdck <= 1000 && yldblDouble >= 60) ||
                            (cdck > 1000 && yldblDouble >= 50)) {
                        view.put("remind21B", remind21B);
                        if (dkqx == 1) {
                            if (yhhLprjd > CS35Value) {
                                yhhLprjd = CS35Value;
                            }
                        } else {
                            if (yhhLprjd > CS36Value) {
                                yhhLprjd = CS36Value;
                            }
                        }
                    }
                }
                // 第二十二条 对客户提供不完全是一类担保，且不符合以下条件的，按下列要求执行：
                // 若一年期贷款计算出加点低于289.25个基点，则在此基础上再加21.75个基点，加点后不高于289.25个基点；
                // 若五年期以上贷款计算出加点低于294.5个基点，则在此基础上再加24.5个基点，加点后不高于294.5个基点。
                // 在本行开立基本账户往来1年以上；
                // 企业客户信用等级在A级（含）以上，个人客户信用等级为1级；
                // 资产负债率低于30%；
                // 公司及法人代表、主要股东及配偶征信记录无不良；
                // 担保类型为二类担保（含）以上。
                if (isbfyldb) {
                    if (kljbzhwl != 2 &&
                            (!"GZ00005".equals(xydj) && !"GZ00006".equals(xydj) && !"GZ00056".equals(xydj)) &&
                            zcfzl >= 30 &&
                            zxbljl > 0 &&
                            (!ishasyldb && !ishaseldb && ishassldb && ishasqtdb)) {
                        view.put("remind22", remind22);
                        if (dkqx == 1) {
                            if (yhhLprjd < CS37Value) {
                                yhhLprjd += CS38Value;
                                if (yhhLprjd > CS37Value) {
                                    yhhLprjd = CS37Value;
                                }
                            }
                        } else {
                            if (yhhLprjd < CS39Value) {
                                yhhLprjd += CS40Value;
                                if (yhhLprjd > CS39Value) {
                                    yhhLprjd = CS39Value;
                                }
                            }
                        }
                    }
                }
                // Step2.再对按LPR加基点（BP）进行优惠
                //第五章 小微客户定价普惠措施
                if (sfjzxkh == 1) {
                    if (cdck >= 100 && cdck <= 1000) {
                        // 第二十三条 对贷款户额在100-1000万（含）小微客户，实行差异化普惠利率定价。
                        view.put("remind23", remind23);
                        // 第二十四条  对提供担保类型全为一类A的该类客户，经利率定价测算后，再优惠下调100个基点。
                        if (iscyldbA) {
                            view.put("remind24", remind24);
                            yhhLprjd -= CS23Value;
                        }
                        // 第二十五条 对提供担保类型不全为一类A的该类客户，经利率定价测算后，再优惠下调50个基点。
                        if (!iscyldbA) {
                            view.put("remind25", remind25);
                            yhhLprjd -= CS24Value;
                        }
                    }
                }

//                check1 = true;
//                check2 = true;

                //第六章 其他定价普惠措施
                //第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。
                if (cdck > 30) {
                    view.put("remind27", remind27);
                    yhhLprjd -= CS14Value;
                    //第二十九条  代发工资本年在本行，代发人数不低于20人的客户，在计算出执行利率后，再优惠下调10个基点。
                    if (check1) {
                        view.put("remind29", remind29);
                        yhhLprjd -= CS26Value;
                    }
                    //第三十条  实际控制企业有国际贸易业务，外汇结算量达50万美元及以上的客户，在计算出执行利率后，再优惠下调10个基点。
                    if (check2) {
                        view.put("remind30", remind30);
                        yhhLprjd -= CS27Value;
                    }
                }

                // 2023年2月13日
                // （三）对农村“三权”抵（质）押贷款给予利率优惠。
                // 对农村“三权”抵（质）押贷款（含便民卡）利率设置上限：
                //（1）五年期（含）以内贷款最高按1年期LPR加332.5个基点执行，
                //（2）五年期以上贷款最高按5年期以上LPR加365个基点执行。
                if (ncsqdzydk == 1) {
                    if (dkqx == 1) {
                        view.put("remind41", remind41);
                        if (yhhLprjd > 332.5d) yhhLprjd = 332.5d;
                    } else {
                        view.put("remind42", remind42);
                        if (yhhLprjd > 365.0d) yhhLprjd = 365.0d;
                    }
                }

                // 2023年2月13日
                // (四）对提供一类和其他类联合抵押的花炮企业“一类”担保贷款部分给予利率优惠。
                //花炮企业提供“一类”担保贷款部分再给予利率优惠20BP,但不重复享受我行其他利率优惠政策。优惠政策享受就高不就低
                double yldbYhhLprjd = yhhLprjd;
                if (sfjzxkh == 2 && sfhpqy == 1) {
                    if (isbfyldb || iscyldb) {
                        yldbYhhLprjd -= 20;
                        yhhZxll = lprbzz + (yldbYhhLprjd / 100);
                        String note = "花炮企业客户优惠政策\"一类担保部分实际担保金额" + format2Decimal.format(yldbjezh) + "万元，可再优惠20BP\"，优惠部分执行利率为" + format4Decimal.format(yhhZxll) + "%";
                        view.put("note", note);
                        //计算、重新计算、查看、打印 花炮企业优惠政策信息展示标识 1 展示 2 不展示
                        view.put("sfhpqyDisplay", "1");
                    } else {
                        view.put("sfhpqyDisplay", "2");
                    }
                } else {
                    view.put("sfhpqyDisplay", "2");
                }

                //最低BP值
                if (yhhLprjd < CS22Value) {
                    yhhLprjd = CS22Value;
                }
                //优惠后执行利率  = 一年期LPR利率 + (优惠后按LPR加基点(BP) / 100)
                yhhZxll = lprbzz + (yhhLprjd / 100);
                yhhZxll6 = yhhZxll;
                yhhLprjd7 = (yhhZxll - lprbzz) * 100;

                if (sfjzxkh == 1) {
                    if (cdck >= 100 && cdck <= 1000) {
                        // 第二十六条 若优惠基点下调后低于年利率5.325%，即按年利率5.325%执行。
                        if (yhhZxll < CS30Value) {
                            view.put("remind26B", remind26B);
                            yhhZxll = CS30Value;
                        }
                        // 第二十六条 经利率定价测算后，若定价结果低于年利率5.325%，即按定价结果执行，不再下调基点；这里“即按定价结果执行”是要跟第六章优惠基点那几条重复享受
                        if (yhqZxll < CS30Value) {
                            view.put("remind26A", remind26A);
                            //第六章 其他定价普惠措施
                            //第二十七条 除专项类外的其他类户额30万元以上客户贷款定价，在计算出执行利率后，再优惠20个基点让利客户。
                            double yhqLprjd_ = yhqLprjd;
                            if (cdck > 30) {
                                view.put("remind27", remind27);
                                yhqLprjd_ -= CS14Value;
                            }
                            //第二十九条  代发工资本年在本行，代发人数不低于20人的客户，在计算出执行利率后，再优惠下调10个基点。
                            if (check1) {
                                view.put("remind29", remind29);
                                yhqLprjd_ -= CS26Value;
                            }
                            //第三十条  实际控制企业有国际贸易业务，外汇结算量达50万美元及以上的客户，在计算出执行利率后，再优惠下调10个基点。
                            if (check2) {
                                view.put("remind30", remind30);
                                yhqLprjd_ -= CS27Value;
                            }
                            yhhZxll = lprbzz + (yhqLprjd_ / 100);
                            //yhhZxll = yhqZxll;
                        }
                        yhhLprjd7 = (yhhZxll - lprbzz) * 100;
                    }
                }
            }

            //第三十三条  贷款利率定价的其他规定。
            //（四）本行其他类贷款利率设年利率4.35%的下限，经本办法测算后，低于或等于年利率4.35%的客户，按年利率4.35%执行。
            if (yhhZxll < CS21Value) {
                yhhZxll6 = yhhZxll;
                view.put("remind33", remind33);
                yhhZxll = CS21Value;
                yhhLprjd7 = (yhhZxll - lprbzz) * 100;
            }

            Long timeConsumingEnd_6_3 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_6_3);
            log.info("贷款利率定价-定价计算-方案处理-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-方案处理-总耗时："+ (timeConsumingEnd_6_3 - timeConsumingStart_6_3) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            Long timeConsumingStart_6_4 = System.currentTimeMillis();
            date = new Date(timeConsumingStart_6_4);
            log.info("贷款利率定价-定价计算-最终值传输处理-开始："+ simpleDateFormat.format(date));

            view.put("bjrq", CS17Value); // Lpr利率报价日期
            if (yhqLprjd < 0) { yhqLprjd = 0; }
            view.put("jdbp", format2Decimal.format(yhqLprjd)); // 优惠前LPR基点
            if (yhqZxll < 0) { yhqZxll = 0; }
            view.put("zxll", format4Decimal.format(yhqZxll)); // 优惠前执行利率
            if (yhhLprjd < 0) { yhhLprjd = 0; }
            view.put("yhhjdbp", format2Decimal.format(yhhLprjd)); // 优惠后LPR基点
            if (yhhZxll6 < 0) { yhhZxll6 = 0; }
            view.put("yhhZxll6",format4Decimal.format(yhhZxll6)); // 优惠后执行利率
            if (yhhLprjd7 < 0) { yhhLprjd7 = 0; }
            view.put("yhhLprjd7", format2Decimal.format(yhhLprjd7)); // 优惠后最终LPR基点
            if (yhhZxll < 0) { yhhZxll = 0; }
            view.put("yhhzxll", format4Decimal.format(yhhZxll)); // 优惠后最终执行利率

            Long timeConsumingEnd_6_4 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_6_4);
            log.info("贷款利率定价-定价计算-最终值传输处理-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-最终值传输处理-总耗时："+ (timeConsumingEnd_6_4 - timeConsumingStart_6_4) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            Long timeConsumingEnd_6 = System.currentTimeMillis();
            date = new Date(timeConsumingEnd_6);
            log.info("贷款利率定价-定价计算-方案甄别特殊处理-结束："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-方案甄别特殊处理-总耗时："+ (timeConsumingEnd_6 - timeConsumingStart_6) / 1000 +" (s)");
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            Long endTime = System.currentTimeMillis();
            date = new Date(endTime);
            log.info("贷款利率定价-定价计算-结束时间："+ simpleDateFormat.format(date));
            log.info("贷款利率定价-定价计算-总耗时："+ (endTime - beginTime) / 1000 +" (s)");
            log.info("======================================================================");

        } catch (Exception tx) {
            log.debug(tx.getMessage());
            throw tx;
        }
        return Result.ok(view);
    }

    /**
     * 利率定价计算 / 删除
     *
     * @param djid 定价ID
     * @return
     */
    @AutoLog(value = "利率定价计算-删除")
    @ApiOperation(value = "利率定价计算-删除", notes = "利率定价计算-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "djid", required = true) String djid) {
        QueryWrapper<RateZxlldjxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("djid", Long.parseLong(djid));
        iRateZxlldjxxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 利率定价计算 / 查看/打印 / 贷款利率定价附表一 / 客户活期存款明细
     *
     * @param zjhm
     * @param djnf
     * @return
     */
    @AutoLog(value = "利率定价计算-查看/打印-客户活期存款明细")
    @ApiOperation(value = "利率定价计算-查看/打印-客户活期存款明细", notes = "利率定价计算-查看/打印-客户活期存款明细")
    @RequestMapping("/getHqCkrpmx")
    public Result<?> getHqCkrpmx(@RequestParam(name = "zjhm", required = true) String zjhm,
                                 @RequestParam(name = "djnf", required = true) String djnf) {
        Date date = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
        QueryWrapper<rateKhzhglxxb> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zjhm", zjhm);
        List<rateKhzhglxxb> rateKhzhglxxbList = rateKhzhglxxbService.list(queryWrapper1);
        List<String> listckzh = new ArrayList<>();
        for (rateKhzhglxxb r : rateKhzhglxxbList) {
            listckzh.add(r.getCkzh());
        }
        List<RateLldjZhckrpAll> hqckrpList = new ArrayList<>();
        if (listckzh.size() > 0) {
            QueryWrapper<RateLldjZhckrpAll> queryWrapper = new QueryWrapper();
            queryWrapper.eq("tjyf", date);
            //queryWrapper.eq("zjhm", zjhm);
            queryWrapper.in("ckzh", listckzh);
            queryWrapper.eq("zhlx", 1);
            queryWrapper.orderByDesc("ckzh");
            hqckrpList = rateLldjZhckrpAllService.list(queryWrapper);
        }
        for (RateLldjZhckrpAll record : hqckrpList) {
            for (rateKhzhglxxb rateKhzhglxxb : rateKhzhglxxbList) {
                if (rateKhzhglxxb.getCkzh().equals(record.getCkzh())) {
                    String tmpJgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", rateKhzhglxxb.getZzbz());
                    String tmpKhgx = iSysDictService.queryDictTextByKey("zhgx", rateKhzhglxxb.getZhgx().toString().substring(0, 1));
                    record.setJgmc(tmpJgmc);
                    record.setHm(rateKhzhglxxb.getZhmc());
                    record.setKhgx(tmpKhgx);
                    record.setGlzhxxgx(rateKhzhglxxb.getGlzhxxgx());
                }
            }
        }
        return Result.ok(hqckrpList);
    }

    /**
     * 利率定价计算 / 查看/打印 / 贷款利率定价附表一 / 客户保证金存款明细
     *
     * @param zjhm
     * @param djnf
     * @return
     */
    @AutoLog(value = "利率定价计算-查看/打印-客户保证金存款明细")
    @ApiOperation(value = "利率定价计算-查看/打印-客户保证金存款明细", notes = "利率定价计算-查看/打印-客户保证金存款明细")
    @RequestMapping("/getBzjCkrpmx")
    public Result<?> getBzjCkrpmx(@RequestParam(name = "zjhm", required = true) String zjhm,
                                  @RequestParam(name = "djnf", required = true) String djnf) {
        JSONObject bzjckrpmx = new JSONObject();
        BigDecimal sumCkrp = new BigDecimal(0);
        BigDecimal bzjckrp = new BigDecimal(0);
        BigDecimal multiply = new BigDecimal("0.3");
        QueryWrapper<rateKhzhglxxb> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zjhm", zjhm);
        List<rateKhzhglxxb> rateKhzhglxxbList = rateKhzhglxxbService.list(queryWrapper1);
        List<String> listckzh = new ArrayList<>();
        for (rateKhzhglxxb r : rateKhzhglxxbList) {
            listckzh.add(r.getCkzh());
        }
        List<RateLldjZhckrpAll> bzjckrpList = new ArrayList<>();
        if (listckzh.size() > 0) {
            Date date = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
            QueryWrapper<RateLldjZhckrpAll> queryWrapper = new QueryWrapper();
            queryWrapper.eq("tjyf", date);
            // queryWrapper.eq("zjhm", zjhm);
            queryWrapper.in("ckzh", listckzh);
            queryWrapper.eq("zhlx", 3);
            queryWrapper.orderByDesc("tjyf");
            bzjckrpList = rateLldjZhckrpAllService.list(queryWrapper);
        }
        for (RateLldjZhckrpAll record : bzjckrpList) {
            for (rateKhzhglxxb rateKhzhglxxb : rateKhzhglxxbList) {
                if (rateKhzhglxxb.getCkzh().equals(record.getCkzh())) {
                    String tmpJgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", rateKhzhglxxb.getZzbz());
                    String tmpKhgx = iSysDictService.queryDictTextByKey("zhgx", rateKhzhglxxb.getZhgx().toString().substring(0, 1));
                    record.setJgmc(tmpJgmc);
                    record.setKhgx(tmpKhgx);
                    record.setGlzhxxgx(rateKhzhglxxb.getGlzhxxgx());
                }
            }
            //判断帐号尾数是8，并且科目号是201401、201402、201403、201404、201499
            if (record.getCkzh() != null && record.getCkzh().endsWith("8")) {
                //总账账户关联主表
                Zzzhzb form = new Zzzhzb();
                if ("true".equals(sfdsjpt)) {
                    form = iZzzhzbService.queryZzzhzbHive(record.getCkzh());//Hive
                } else {
                    form = iZzzhzbService.queryZzzhzbOracle(record.getCkzh());//Oracle
                }
                if (form != null) {
                    String kmh = "";
                    if ("201401".equals(kmh) || "201402".equals(kmh) || "201403".equals(kmh) || "201404".equals(kmh) || "201499".equals(kmh)) {
                        BigDecimal bzjQsndynckrp = new BigDecimal(String.valueOf(record.getQsndynckrp()));
                        BigDecimal bzjQsndenckrp = new BigDecimal(String.valueOf(record.getQsndenckrp()));
                        BigDecimal bzjQsndsnckrp = new BigDecimal(String.valueOf(record.getQsndsnckrp()));
                        BigDecimal bl = new BigDecimal(0.3d);
                        record.setQsndynckrp(bzjQsndynckrp.divide(bl, 4, BigDecimal.ROUND_HALF_UP));
                        record.setQsndenckrp(bzjQsndenckrp.divide(bl, 4, BigDecimal.ROUND_HALF_UP));
                        record.setQsndsnckrp(bzjQsndsnckrp.divide(bl, 4, BigDecimal.ROUND_HALF_UP));
                        //其中：各类保证金日平（按前三年第三年存款日平30%计算）
                        sumCkrp.add(record.getQsndsnckrp());
                    }
                }
            }
        }
        bzjckrp = sumCkrp.multiply(multiply).setScale(4, BigDecimal.ROUND_UP);
        bzjckrpmx.put("bzjckmx", bzjckrpList);
        bzjckrpmx.put("bzjckrp", bzjckrp);
        return Result.ok(bzjckrpmx);
    }

    /**
     * 利率定价计算 / 查看/打印 / 贷款利率定价附表二 / 客户定期存款明细
     *
     * @param zjhm
     * @param djnf
     * @return
     */
    @AutoLog(value = "利率定价计算-查看/打印-客户定期存款明细")
    @ApiOperation(value = "利率定价计算-查看/打印-客户定期存款明细", notes = "利率定价计算-查看/打印-客户定期存款明细")
    @RequestMapping("/getDqCkrpmx")
    public Result<?> getDqCkrpmx(@RequestParam(name = "zjhm", required = true) String zjhm,
                                 @RequestParam(name = "djnf", required = true) String djnf) {
        Date date = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
        QueryWrapper<rateKhzhglxxb> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("zjhm", zjhm);
        List<rateKhzhglxxb> rateKhzhglxxbList = rateKhzhglxxbService.list(queryWrapper1);
        List<String> listckzh = new ArrayList<>();
        for (rateKhzhglxxb r : rateKhzhglxxbList) {
            listckzh.add(r.getCkzh());
        }
        List<RateLldjZhckrpAll> dqckrpList = new ArrayList<>();
        if (listckzh.size() > 0) {
            QueryWrapper<RateLldjZhckrpAll> queryWrapper = new QueryWrapper();
            queryWrapper.eq("tjyf", date);
            //queryWrapper.eq("zjhm", zjhm);
            queryWrapper.in("ckzh", listckzh);
            queryWrapper.eq("zhlx", 2);
            queryWrapper.orderByDesc("ckzh");
            dqckrpList = rateLldjZhckrpAllService.list(queryWrapper);

        }
        for (RateLldjZhckrpAll record : dqckrpList) {
            for (rateKhzhglxxb rateKhzhglxxb : rateKhzhglxxbList) {
                if (rateKhzhglxxb.getCkzh().equals(record.getCkzh())) {
                    String tmpJgmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", rateKhzhglxxb.getZzbz());
                    record.setJgmc(tmpJgmc);
                    record.setHm(rateKhzhglxxb.getZhmc());
                }
            }
        }
        return Result.ok(dqckrpList);
    }

    /**
     * 利率定价计算 / 确认
     *
     * @param record
     * @return
     */
    @AutoLog(value = "利率定价计算-确认校验")
    @ApiOperation(value = "利率定价计算-确认校验", notes = "利率定价计算-确认校验")
    @RequestMapping("/confirmCheck")
    public Result<?> confirmCheck(@RequestBody RateZxlldjxx record) {
        QueryWrapper<RateZxlldjxx> zxlldjbQueryWrapper = new QueryWrapper<>();
        zxlldjbQueryWrapper.eq("djid", record.getDjid());
        RateZxlldjxx zxlldjb = iRateZxlldjxxService.getOne(zxlldjbQueryWrapper, false);
        if (zxlldjb == null) {
            return Result.error("没有找到需要确认的记录！");
        } else {
            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf", record.getDjnf());
            zxllcxQueryWrapper.eq("zjhm", record.getZjhm());
            RateZxllcx check = rateZxllcxService.getOne(zxllcxQueryWrapper,false);
            if (check != null) {
                return Result.ok(check);
            } else {
                return Result.ok();
            }
        }
    }

    /**
     * 利率定价计算 / 确认保存
     *
     * @param record
     * @return
     */
    @AutoLog(value = "利率定价计算-确认保存")
    @ApiOperation(value = "利率定价计算-确认保存", notes = "利率定价计算-确认保存")
    @RequestMapping("/updateSpzt")
    public Result<?> updateSpzt(@RequestBody RateZxlldjxx record) {
        try {
            int spzt = record.getSpzt();
            if (spzt == 1) {
                QueryWrapper<RateZxlldjxx> queryWrapper1 = new QueryWrapper<>();
                queryWrapper1.eq("djid",record.getDjid());
                RateZxlldjxx form = iRateZxlldjxxService.getOne(queryWrapper1,false);
                if (form != null) {
                    QueryWrapper<RateZxllcx> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq("djnf", form.getDjnf());
                    queryWrapper2.eq("zjhm", form.getZjhm());
                    RateZxllcx zxllcxCheck = rateZxllcxService.getOne(queryWrapper2,false);
                    if (zxllcxCheck != null) {
                        rateZxllcxService.remove(queryWrapper2);
                    }
                    RateZxllcx zxllcx = new RateZxllcx();
                    zxllcx.setDjid(form.getDjid());
                    zxllcx.setDjrq(new Date());
                    zxllcx.setDjnf(form.getDjnf());
                    zxllcx.setZzbz(form.getZzbz());
                    zxllcx.setZjhm(form.getZjhm());
                    zxllcx.setKhmc(form.getKhmc());
                    zxllcx.setDfhj(form.getDfhj());
                    zxllcx.setJjll(form.getJjll());
                    zxllcx.setSffd(form.getSffd());
                    zxllcx.setZxll(form.getZxll());
                    zxllcx.setLprll(form.getLprll());
                    zxllcx.setJdbp(form.getJdbp());
                    zxllcx.setYhhjdbp(form.getYhhjdbp());
                    zxllcx.setYhhzxll(form.getYhhzxll());
                    zxllcx.setLrsj(new Date());
                    zxllcx.setLrczy(getLoginUser().getUsername());
                    rateZxllcxService.save(zxllcx);

                    RateLsdjcx lsdjcx = new RateLsdjcx();
                    lsdjcx.setDjid(form.getDjid());
                    lsdjcx.setDjrq(new Timestamp(System.currentTimeMillis()));
                    lsdjcx.setDjnf(form.getDjnf());
                    lsdjcx.setZzbz(form.getZzbz());
                    lsdjcx.setZjhm(form.getZjhm());
                    lsdjcx.setKhmc(form.getKhmc());
                    lsdjcx.setDfhj(form.getDfhj());
                    lsdjcx.setJjll(form.getJjll());
                    lsdjcx.setSffd(form.getSffd());
                    lsdjcx.setLprll(form.getLprll());
                    lsdjcx.setZxll(form.getZxll());
                    lsdjcx.setYhhzxll(form.getYhhzxll());
                    lsdjcx.setJdbp(form.getJdbp());
                    lsdjcx.setYhhjdbp(form.getYhhjdbp());
                    lsdjcx.setLrsj(new Date());
                    lsdjcx.setLrczy(getLoginUser().getUsername());
                    lsdjcx.setDjbs(1);
                    iRateLsdjcxService.save(lsdjcx);
                } else {
                    return Result.error("未查找到对应的定价计算信息！");
                }
            }
            QueryWrapper<RateZxlldjxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("djid", record.getDjid());
            //表主键不能更新
            record.setDjid(null);
            record.setSpzt(spzt);
            record.setSpr(getLoginUser().getUsername());
            record.setSpsj(new Date());
            record.setNote(record.getNote());
            iRateZxlldjxxService.update(record, queryWrapper);
            return Result.ok("信息确认完成！");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Result.error("确认失败！请联系管理员处理！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 导出excel
     *
     * @param request
     * @param rateZxlldjxx
     * @return
     */
    @AutoLog(value = "利率定价计算-导出excel")
    @ApiOperation(value = "利率定价计算-导出excel", notes = "利率定价计算-导出excel")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateZxlldjxx rateZxlldjxx) {
        return super.exportXls(request, rateZxlldjxx, RateZxlldjxx.class, "利率定价计算");
    }

}

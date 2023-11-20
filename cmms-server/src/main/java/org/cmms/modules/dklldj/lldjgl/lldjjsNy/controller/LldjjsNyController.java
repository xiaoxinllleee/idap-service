package org.cmms.modules.dklldj.lldjgl.lldjjsNy.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.dictcache.IDictValueQuery;
import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import org.cmms.modules.dklldj.csszgl.csgl.service.ICsszxxService;
import org.cmms.modules.dklldj.csszgl.gzbdssz.entity.Gzbdssz;
import org.cmms.modules.dklldj.csszgl.gzbdssz.service.IGzbdsszService;
import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import org.cmms.modules.dklldj.csszgl.khxmsz.service.IKhxmszService;
import org.cmms.modules.dklldj.csszgl.xmgzsz.entity.Xmgzsz;
import org.cmms.modules.dklldj.csszgl.xmgzsz.service.IXmgzszService;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.entity.Zxllpzgl;
import org.cmms.modules.dklldj.csszgl.zxllpzgl.service.IZxllpzglService;
import org.cmms.modules.dklldj.lldjgl.entity.RateDjsqmx;
import org.cmms.modules.dklldj.lldjgl.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.entity.RateZxlldjb;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.entity.RateLsdjcx;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.service.IRateLsdjcxService;
import org.cmms.modules.dklldj.lldjgl.lldjjsNy.service.IRateZxlldjbNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDbxxglNy;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDjsqxxNy;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.ILldjsqNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateDbxxglNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateDjsqmxNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateZxllcxNyService;
import org.cmms.modules.system.service.ISysDictItemService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.dklldj.csszgl.gzbdssz.entity.Gzbdssz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Description: 宁远:利率定价计算
 * @Author: penghr
 * @Date: 2022-04-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "宁远:利率定价计算")
@RestController
@RequestMapping("/dklldj/lldjjsNy")
public class LldjjsNyController extends JeecgController<RateZxlldjb, IRateZxlldjbNyService> {
    @Autowired
    private IRateZxlldjbNyService zxlldjbService;
    @Autowired
    private ILldjsqNyService lldjsqService;
    @Autowired
    private IRateDjsqmxNyService djsqmxService;
    @Autowired
    private IRateDbxxglNyService dbxxglNyService;
    @Autowired
    private IRateZxllcxNyService zxllcxService;
    @Autowired
    private IRateLsdjcxService lsdjcxService;
    @Autowired
    private IKhxmszService zbkxxbService;
    @Autowired
    private IXmgzszService zbgzxxbService;
    @Autowired
    private IGzbdsszService gzbdsxxService;
    @Autowired
    private ICsszxxService csszxxService;
    @Autowired
    private IZxllpzglService iZxllpzglService;
    @Autowired
    private IDictValueQuery iDictValueQuery;
    @Autowired
    private ISysDictItemService iSysDictItemService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 利率定价计算 / 分页列表查询
     *
     * @param zxlldjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "利率定价计算-分页列表查询")
    @ApiOperation(value = "利率定价计算-分页列表查询", notes = "利率定价计算-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateZxlldjb zxlldjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateZxlldjb>> result = new Result<IPage<RateZxlldjb>>();
        QueryWrapper<RateZxlldjb> queryWrapper = QueryGenerator.initQueryWrapper(zxlldjb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateZxlldjbNyService.class, zxlldjbService, pageNo, pageSize, queryWrapper, "djnf", "zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 利率定价计算 / 添加 / 定价申请信息、明细获取
     *
     * @param djnfstr
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价计算-添加-定价申请信息、明细获取")
    @ApiOperation(value = "利率定价计算-添加-定价申请信息、明细获取", notes = "利率定价计算-添加-定价申请信息、明细获取")
    @RequestMapping(value = "/add")
    public Result<?> add(@RequestParam(value = "djnf", required = true) String djnfstr,
                         @RequestParam(value = "zjhm", required = true) String zjhm) {
        JSONObject view = new JSONObject();
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            CompletableFuture<RateDjsqxxNy> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxNy djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            CompletableFuture<RateZxlldjb> zxlldjbSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djnf",djnf);
                zxlldjbQueryWrapper.eq("zjhm",zjhm);
                zxlldjbQueryWrapper.eq("spzt","0");//确认状态 / 未确认
                RateZxlldjb zxlldjb = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
                return zxlldjb;
            });
            RateDjsqxxNy djsqxxCheck = djsqxxSupplyAsync.get();
            if (djsqxxCheck == null) {
                return Result.error("未查询到对应的申请信息，请核实！");
            }
            RateZxlldjb zxlldjbCheck = zxlldjbSupplyAsync.get();
            if (zxlldjbCheck != null) {
                return Result.error("同一年度内该客户只允许有一条未确认的定价信息，请核实！");
            }
            view.put("djsqxx",djsqxxCheck);
            return Result.ok(view);
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("添加失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 添加保存
     *
     * @return
     */
    @AutoLog(value = "利率定价计算-添加保存")
    @ApiOperation(value = "利率定价计算-添加保存", notes = "利率定价计算-添加保存")
    @PostMapping(value = "/addSave")
    public Result<?> addSave(@RequestBody RateZxlldjb zxlldjb) {
        String dkqx  = zxlldjb.getDkqx();
        Long   djid  = 0L;
        double jzll  = 0d;
        double lprll = 0d;
        String bjrq  = "";
        DecimalFormat format0Decimal = new DecimalFormat("#0");
        DecimalFormat format2Decimal = new DecimalFormat("#0.00");
        DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
        try {
            CompletableFuture<RateZxlldjb> zxlldjbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateZxlldjb> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djnf", zxlldjb.getDjnf());
                queryWrapper.eq("zjhm", zxlldjb.getZjhm());
                queryWrapper.eq("spzt","0");
                RateZxlldjb zxlldjxx = zxlldjbService.getOne(queryWrapper,false);
                return zxlldjxx;
            });
            CompletableFuture<Long> djidSupplyAsync = CompletableFuture.supplyAsync(() ->{
                Long djidL = 0L;
                djidL = Long.parseLong(iDictValueQuery.getSeqRateZxlldjbDjidNextval("SEQ_PUBLIC_ID.nextval"));
                return djidL;
            });
            CompletableFuture<List<Csszxx>> csszSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Csszxx> queryWrapper = new QueryWrapper<>();
                List<Csszxx> csszxxList = csszxxService.list(queryWrapper);
                return csszxxList;
            });

            RateZxlldjb zxlldjbCheck = zxlldjbSupplyAsync.get();
            if (zxlldjbCheck != null) {
                return Result.error("同一年度该客户只允许有一条未确认的定价信息！");
            }
            djid = djidSupplyAsync.get();
            List<Csszxx> csszList = csszSupplyAsync.get();
            //1 五年以上/2 一至五年（含）/3 一年（含）以内
            if ("1".equals(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("GZ00035") || item.getCsid().equals("GZ00039")).collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    log.info("参数ID："+cssz.getCsid());
                    if ("GZ00035".equals(cssz.getCsid())) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("GZ00039".equals(cssz.getCsid())) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            if ("2".equals(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("GZ00034") || item.getCsid().equals("GZ00038")).collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    log.info("参数ID："+cssz.getCsid());
                    if ("GZ00034".equals(cssz.getCsid())) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("GZ00038".equals(cssz.getCsid())) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            if ("3".equals(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("GZ00033") || item.getCsid().equals("GZ00038")).collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    log.info("参数ID："+cssz.getCsid());
                    if ("GZ00033".equals(cssz.getCsid())) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("GZ00038".equals(cssz.getCsid())) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("GZ00037")).collect(Collectors.toList());
            for (Csszxx csszxx : CsszCollect) {
                if ("GZ00037".equals(csszxx.getCsid())) {
                    bjrq = csszxx.getCsvalue();
                }
            }

            zxlldjb.setDjid(djid);
            zxlldjb.setJjll(new BigDecimal(format4Decimal.format(jzll)));
            zxlldjb.setLprll(new BigDecimal(format4Decimal.format(lprll)));
            zxlldjb.setBjrq(bjrq);
            zxlldjb.setSpzt(0);
            zxlldjb.setXgzt(0);
            zxlldjb.setLrbz(1);
            zxlldjb.setLrczy(getLoginUser().getUsername());
            zxlldjb.setLrsj(new Timestamp(System.currentTimeMillis()));
            zxlldjbService.save(zxlldjb);

            return Result.ok("添加成功！");
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("添加失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 重新计算 / 定价申请信息、明细获取
     *
     * @param djnfstr
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价计算-重新计算-定价申请信息、明细获取")
    @ApiOperation(value = "利率定价计算-重新计算-定价申请信息、明细获取", notes = "利率定价计算-重新计算-定价申请信息、明细获取")
    @RequestMapping(value = "/edit")
    public Result<?> edit(@RequestParam(value = "djnf", required = true) String djnfstr,
                          @RequestParam(value = "zjhm", required = true) String zjhm) {
        JSONObject view = new JSONObject();
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            CompletableFuture<RateDjsqxxNy> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxNy djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            RateDjsqxxNy djsqxxCheck = djsqxxSupplyAsync.get();
            if (djsqxxCheck == null) {
                return Result.error("未查询到对应的申请信息，请核实！");
            }
            view.put("djsqxx",djsqxxCheck);
            return Result.ok(view);
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("重新计算失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 编辑保存
     *
     * @param zxlldjb
     * @return
     */
    @AutoLog(value = "利率定价计算-编辑保存")
    @ApiOperation(value = "利率定价计算-编辑保存", notes = "利率定价计算-编辑保存")
    @RequestMapping(value = "/editSave")
    public Result<?> editSave(@RequestBody RateZxlldjb zxlldjb) {
        try {
            //如果重新计算发现该记录是已确认则需要覆盖最新的定价记录
            if (zxlldjb.getSpzt() == 1d) {
                QueryWrapper<RateZxllcx> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djnf",zxlldjb.getDjnf());
                queryWrapper.eq("zjhm",zxlldjb.getZjhm());
                RateZxllcx zxllcx = zxllcxService.getOne(queryWrapper,false);
                if (zxllcx != null) {
                    zxllcxService.remove(queryWrapper);
                }
                RateZxllcx insert = new RateZxllcx();
                insert.setDjid(zxlldjb.getDjid());
                insert.setDjrq(new Timestamp(System.currentTimeMillis()));
                insert.setDjnf(zxlldjb.getDjnf());
                insert.setZzbz(zxlldjb.getZzbz());
                insert.setZjhm(zxlldjb.getZjhm());
                insert.setKhmc(zxlldjb.getKhmc());
                insert.setDfhj(zxlldjb.getDfhj());
                insert.setJjll(zxlldjb.getJjll());
                insert.setZxll(zxlldjb.getZxll());
                insert.setLprll(zxlldjb.getLprll());
                insert.setLrsj(new Timestamp(System.currentTimeMillis()));
                insert.setLrczy(getLoginUser().getUsername());
                zxllcxService.save(insert);
            }

            zxlldjb.setLrbz(2);
            zxlldjb.setXgzt(1); //已修改
            zxlldjb.setXgsj(new Timestamp(System.currentTimeMillis()));
            zxlldjb.setXgczy(getLoginUser().getUsername());
            QueryWrapper<RateZxlldjb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("djnf",zxlldjb.getDjnf());
            queryWrapper.eq("zjhm",zxlldjb.getZjhm());
            zxlldjbService.update(zxlldjb, queryWrapper);

            return Result.ok("重新计算成功！");
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("重新计算保存失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 添加 / 分值计算
     *
     * @param djnfstr
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价计算-添加-分值计算")
    @ApiOperation(value = "利率定价计算-添加-分值计算", notes = "利率定价计算-添加-分值计算")
    @RequestMapping(value = "/compute")
    public Result<?> compute(@RequestParam(value = "djnf", required = true) String djnfstr,
                             @RequestParam(value = "zjhm", required = true) String zjhm) {
        JSONObject view = new JSONObject();
        DecimalFormat format0Decimal = new DecimalFormat("#0");
        DecimalFormat format2Decimal = new DecimalFormat("#0.00");
        DecimalFormat format3Decimal = new DecimalFormat("#0.000");
        DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
        double dksxed = 0d; //贷款授信+承兑敞口
        int dkqx = 1;       //贷款期限：1 五年以上/2 一至五年（含）/3 一年（含）以内
        int khlx = 1;       //客户类型：1 个人/2 企业
        int sfbmk = 1;      //是否便民卡：1 是/2 否
        int xddkpz = 1;     //信贷贷款品种：1 其他贷款/2 抵（质）押贷款
        String xydj = "";   //信用等级
        double dfhj = 0d;   //得分合计
        double zxll = 0d;   //执行利率
        double lprll = 0d;  //LPR利率
        double jdbp = 0d;   //按LPR加/减基点BP
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            log.info("当前系统法人行社编码："+qydm);

            CompletableFuture<List<Khxmsz>> zbkxxbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Khxmsz> zbkxxbQueryWrapper = new QueryWrapper();
                zbkxxbQueryWrapper.eq("qydm", "405");
                zbkxxbQueryWrapper.orderByAsc("zbid","pxxh");
                List<Khxmsz> zbkxxbList = zbkxxbService.list(zbkxxbQueryWrapper);
                return zbkxxbList;
            });
            CompletableFuture<List<Xmgzsz>> zbgzxxbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Xmgzsz> zbgzxxbQueryWrapper = new QueryWrapper();
                zbgzxxbQueryWrapper.eq("qydm", "405");
                zbgzxxbQueryWrapper.orderByAsc("zbid","pxxh");
                List<Xmgzsz> zbgzxxList = zbgzxxbService.list(zbgzxxbQueryWrapper);
                return zbgzxxList;
            });
            CompletableFuture<List<Gzbdssz>> gzbdsxxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Gzbdssz> gzbdsxxQueryWrapper = new QueryWrapper();
                gzbdsxxQueryWrapper.eq("qydm", "405");
                gzbdsxxQueryWrapper.orderByAsc("zbgzid","bdskey");
                List<Gzbdssz> gzbdsxxList = gzbdsxxService.list(gzbdsxxQueryWrapper);
                return gzbdsxxList;
            });
            CompletableFuture<List<Csszxx>> csszSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Csszxx> csszQueryWrapper = new QueryWrapper();
                List<Csszxx> csszList = csszxxService.list(csszQueryWrapper);
                return csszList;
            });
            CompletableFuture<RateDjsqxxNy> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxNy djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            CompletableFuture<List<RateDjsqmx>> djsqmxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateDjsqmx> djsqmxQueryWrapper = new QueryWrapper<>();
                djsqmxQueryWrapper.eq("djnf",djnf);
                djsqmxQueryWrapper.eq("zjhm",zjhm);
                List<RateDjsqmx> djsqmxList = djsqmxService.list(djsqmxQueryWrapper);
                return djsqmxList;
            });
//            CompletableFuture<List<RateDbxxglNy>> khdbxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
//                QueryWrapper<RateDbxxglNy> khdbxxQueryWrapper = new QueryWrapper<>();
//                khdbxxQueryWrapper.eq("djnf",djnf);
//                khdbxxQueryWrapper.eq("zjhm",zjhm);
//                List<RateDbxxglNy> khdbxxList = dbxxglNyService.list(khdbxxQueryWrapper);
//                return khdbxxList;
//            });

            RateDjsqxxNy djsqxxCheck = djsqxxSupplyAsync.get();
            if (djsqxxCheck != null) {
                dksxed = djsqxxCheck.getCdck();
                dkqx   = djsqxxCheck.getDkqx();
                khlx   = djsqxxCheck.getKhlx();
                sfbmk  = djsqxxCheck.getSfbmk();
                xddkpz = Integer.parseInt(djsqxxCheck.getXddkpz());
            }
            List<Khxmsz> zbkxxbLists       = zbkxxbSupplyAsync.get();
            List<Xmgzsz> zbgzxxbLists      = zbgzxxbSupplyAsync.get();
            List<Gzbdssz> gzbdsxxLists     = gzbdsxxSupplyAsync.get();
            List<Csszxx> csszLists         = csszSupplyAsync.get();
            List<RateDjsqmx> djsqmxLists = djsqmxSupplyAsync.get();
//            List<RateDbxxglNy> khdbxxLists = khdbxxSupplyAsync.get();

            for (Khxmsz zbkxxb : zbkxxbLists) {
                double zbgzfzZH = 0d; //指标规则分值总和
                String zbid = zbkxxb.getZbid();
                log.info("当前计算考核项目 / zbid："+zbid);
                String llfs = zbkxxb.getLlfs();
                if ("1".equals(llfs)) {
                    //信用等级
                    List<RateDjsqmx> collect = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbid)).collect(Collectors.toList());
                    RateDjsqmx djsqmx = collect.get(0);
                    if (djsqmx != null) {
                        String zbgzjg = djsqmx.getZbgzjg();
                        if ("KH00001".equals(zbid)) {
                            xydj = djsqmx.getZbgzjg();
                        }
                        List<Xmgzsz> zbgzxxbCollect = zbgzxxbLists.stream().filter(item ->  item.getZbid().equals(zbid) && item.getZbgzid().equals(zbgzjg)).collect(Collectors.toList());
                        Xmgzsz zbgzxxb = zbgzxxbCollect.get(0);
                        if (zbgzxxb != null) {
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            zbgzfzZH = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            zbgzfzZH = zbabs * zbgzfzZH;
                            view.put("ZBJG_" + zbid, xydj);
                            view.put("ZBJGZ_" + zbid, zbgzxxb.getZbgzmc());
                        }
                    }
                } else {
                    //扣分项目
                    if ("KH00002".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        Xmgzsz zbgzxxb = zbgzxxbList.get(0);
                        if (zbgzxxb != null) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                if (zbgzjg == 0) {
                                    zbgzfz = 0;
                                } else {
                                    zbgzfz = zbabs * zbgzjg;
                                }
                                zbgzfzZH += zbgzfz;
                                view.put("ZBJG_" + zbgzid, format0Decimal.format(zbgzjg));
                                view.put("ZBDF_" + zbgzid, zbgzfz);
                            }
                        }
                    }
                    //资产负债率
                    if ("KH00003".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                if ("GZ00011".equals(zbgzid)) {
                                    //资产负债率（%）
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                                    //资产负债率在30%以内计6分，30-50%（不含）计4分，50-70%（不含）计1分，70%（含）以上不计分
                                    if (zbgzjg < 30) {
                                        zbgzfz = 6d;
                                    }
                                    if (zbgzjg >= 30 && zbgzjg < 50) {
                                        zbgzfz = 4d;
                                    }
                                    if (zbgzjg >= 50 && zbgzjg < 70) {
                                        zbgzfz = 1d;
                                    }
                                    if (zbgzjg >= 70) {
                                        zbgzfz = 0d;
                                    }
                                    zbgzfzZH += zbgzfz;
                                    view.put("ZBJG_" + zbgzid, format2Decimal.format(zbgzjg));
                                    view.put("ZBDF_" + zbgzid, zbgzfz);
                                } else {
                                    //资产总额&&负债总额
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    view.put("ZBJG_" + zbgzid, format2Decimal.format(zbgzjg));
                                }
                            }
                        }
                    }
                    //经营期限/贷款投向
                    if ("KH00004".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzdf = 0d;
                            String bdsValue = "";
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "1" : djsqmx.getZbgzjg());
                                List<Gzbdssz> gzbdsxxList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                Gzbdssz gzbdsxx = gzbdsxxList.get(0);
                                if (gzbdsxx != null) {
                                    bdsValue = gzbdsxx.getBdsvalue();
                                    double bdsfz = Double.parseDouble(gzbdsxx.getBdsfz().toString());
                                    if (bdsfz == 0) {
                                        zbgzdf = 0d;
                                    } else {
                                        zbgzdf = zbabs * bdsfz;
                                    }
                                }
                                zbgzfzZH += zbgzdf;
                                view.put("ZBJGZ_" + zbgzid, bdsValue);
                                view.put("ZBJG_" + zbgzid, Double.valueOf(zbgzjg).intValue());
                                view.put("ZBDF_" + zbgzid, zbgzdf);
                            }
                        }
                    }
                    //客户存款贡献度
                    if ("KH00005".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            double zbgzdf = 0d;
                            //（摒弃）开户基本账户往来年限&&（摒弃）定价存款日平合计
                            if (!"GZ00014".equals(zbgzid) && !"GZ00015".equals(zbgzid)) {
                                List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                                RateDjsqmx djsqmx = djsqmxList.get(0);
                                if (djsqmx != null) {
                                    double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    if ("GZ00019".equals(zbgzid)) {
                                        //日平存款占贷款比例（%）
                                        //关联存款账户日平存款余额占贷款授信额度比例低于30%（含）时，按每1%对应分值0.5分；
                                        //关联存款账户日平存款余额占贷款授信额度比例高于30%时，其中30%-60%（含）部分按每1%对应分值0.33分，高于60%计满分。
                                        if (zbgzjg <= 30) {
                                            zbgzdf = zbgzjg * 0.5;
                                        }
                                        if (zbgzjg > 30 && zbgzjg <= 60) {
                                            zbgzdf = (zbgzjg * 0.5) + ((zbgzjg - 30) * 0.33);
                                        }
                                        if (zbgzjg > 60) {
                                            zbgzdf = zbgzfz;
                                        }
                                    }
                                    zbgzfzZH += zbgzdf;
                                    view.put("ZBJG_" + zbgzid, format3Decimal.format(zbgzjg));
                                }
                            }
                        }
                    }
                    //信贷业务往来及效益贡献度
                    if ("KH00006".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            double zbgzdf = 0d;
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                double zbgzjg = Double.parseDouble(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                //信贷业务往来时间
                                if ("GZ00020".equals(zbgzid)) {
                                    int zbjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "1" : djsqmx.getZbgzjg());
                                    String bdsValue = "";
                                    List<Gzbdssz> gzbdsxxList =
                                            gzbdsxxLists.stream()
                                            .filter(item -> item.getZbgzid().equals(zbgzid) && item.getBdskey().equals(zbjg))
                                            .collect(Collectors.toList());
                                    Gzbdssz gzbdsxx = gzbdsxxList.get(0);
                                    if (gzbdsxx != null) {
                                        bdsValue = gzbdsxx.getBdsvalue();
                                        double bdsfz = Double.parseDouble(gzbdsxx.getBdsfz().toString());
                                        if (bdsfz == 0) {
                                            zbgzdf = 0d;
                                        } else {
                                            zbgzdf = zbabs * bdsfz;
                                        }
                                    }
                                    zbgzfzZH += zbgzdf;
                                    view.put("ZBJGZ_" + zbgzid, bdsValue);
                                    view.put("ZBDF_" + zbgzid, zbgzdf);
                                    view.put("ZBJG_" + zbgzid, Double.valueOf(zbjg).intValue());
                                }
                                //客户效益贡献
                                if ("GZ00024".equals(zbgzid)) {
                                    //客户上年收益贡献在
                                    //① 80万元（不含）以上计14分；
                                    //② 60万元-80万（含）计12分；
                                    //③ 40万元-60万（含）计10分；
                                    //④ 30万元-40万（含）计8分；
                                    //⑤ 20万元-30万（含）计6分；
                                    //⑥ 10万元-20万（含）计4分；
                                    //⑦ 5万元-10万元（含）计2分；
                                    //⑧ 5万元（含）以下不计分。
                                    if (zbgzjg <= 5) {
                                        zbgzdf = 0d;
                                    }
                                    if (zbgzjg > 5 && zbgzjg <= 10) {
                                        zbgzdf = 2d;
                                    }
                                    if (zbgzjg > 10 && zbgzjg <= 20) {
                                        zbgzdf = 4d;
                                    }
                                    if (zbgzjg > 20 && zbgzjg <= 30) {
                                        zbgzdf = 6d;
                                    }
                                    if (zbgzjg > 30 && zbgzjg <= 40) {
                                        zbgzdf = 8d;
                                    }
                                    if (zbgzjg > 40 && zbgzjg <= 60) {
                                        zbgzdf = 10d;
                                    }
                                    if (zbgzjg > 60 && zbgzjg <= 80) {
                                        zbgzdf = 12d;
                                    }
                                    if (zbgzjg > 80) {
                                        zbgzdf = 14d;
                                    }
                                    zbgzfzZH += zbgzdf;
                                    view.put("ZBDF_" + zbgzid, zbgzdf);
                                }
                                //客户利息贡献
                                if ("GZ00025".equals(zbgzid)) {
                                    //客户上年度支付我行贷款利息在
                                    //① 20万元（不含）以上计12分；
                                    //② 15万元-20万（含）计10分；
                                    //③ 10万元-15万（含）计8分；
                                    //④ 5万元-10万（含）计6分；
                                    //⑤ 2万元-5万（含）计4分；
                                    //⑥ 2万元（含）以下不计分。
                                    if (zbgzjg <= 2) {
                                        zbgzdf = 0d;
                                    }
                                    if (zbgzjg > 2 && zbgzjg <= 5) {
                                        zbgzdf = 4d;
                                    }
                                    if (zbgzjg > 5 && zbgzjg <= 10) {
                                        zbgzdf = 6d;
                                    }
                                    if (zbgzjg > 10 && zbgzjg <= 15) {
                                        zbgzdf = 8d;
                                    }
                                    if (zbgzjg > 15 && zbgzjg <= 20) {
                                        zbgzdf = 10d;
                                    }
                                    if (zbgzjg > 20) {
                                        zbgzdf = 12d;
                                    }
                                    zbgzfzZH += zbgzdf;
                                    view.put("ZBDF_" + zbgzid, zbgzdf);
                                }
                                view.put("ZBJG_" + zbgzid, Double.valueOf(zbgzjg).intValue());
                            }
                        }
                    }
                    //其他业务扣分项
                    if ("KH00007".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            String bdsValue = "";
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "2" : djsqmx.getZbgzjg());
                                List<Gzbdssz> gzbdsxxList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                Gzbdssz gzbdsxx = gzbdsxxList.get(0);
                                if (gzbdsxx != null) {
                                    bdsValue = gzbdsxx.getBdsvalue();
                                    double bdsfz = Double.parseDouble(gzbdsxx.getBdsfz().toString());
                                    if (bdsfz == 0) {
                                        zbgzfz = 0d;
                                    } else {
                                        zbgzfz = zbabs * bdsfz;
                                    }
                                }
                                zbgzfzZH += zbgzfz;
                                view.put("ZBJGZ_" + zbgzid, bdsValue);
                                view.put("ZBJG_" + zbgzid, Double.valueOf(zbgzjg).intValue());
                                view.put("ZBDF_" + zbgzid, zbgzfz);
                            }
                        }
                    }
                    //其他业务加分项
                    if ("KH00008".equals(zbid)) {
                        List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equals(zbid)).collect(Collectors.toList());
                        for (Xmgzsz zbgzxxb : zbgzxxbList) {
                            String zbgzid = zbgzxxb.getZbgzid();
                            double zbabs = Double.parseDouble(zbgzxxb.getZbabs());
                            double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                            String bdsValue = "";
                            List<RateDjsqmx> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid)).collect(Collectors.toList());
                            RateDjsqmx djsqmx = djsqmxList.get(0);
                            if (djsqmx != null) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "2" : djsqmx.getZbgzjg());
                                List<Gzbdssz> gzbdsxxList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equals(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                Gzbdssz gzbdsxx = gzbdsxxList.get(0);
                                if (gzbdsxx != null) {
                                    bdsValue = gzbdsxx.getBdsvalue();
                                    double bdsfz = Double.parseDouble(gzbdsxx.getBdsfz().toString());
                                    if (bdsfz == 0) {
                                        zbgzfz = 0d;
                                    } else {
                                        zbgzfz = zbabs * bdsfz;
                                    }
                                }
                                zbgzfzZH += zbgzfz;
                                view.put("ZBJGZ_" + zbgzid, bdsValue);
                                view.put("ZBJG_" + zbgzid, Double.valueOf(zbgzjg).intValue());
                                view.put("ZBDF_" + zbgzid, zbgzfz);
                            }
                        }
                    }
                }

                log.info("当前计算考核项目-"+zbid+"-分值："+zbgzfzZH);
                if(!"KH00009".equalsIgnoreCase(zbid) && !"KH00010".equalsIgnoreCase(zbid) && !"KH00011".equalsIgnoreCase(zbid)) {
                    view.put("DF" + zbid, format2Decimal.format(zbgzfzZH));
                }
                dfhj += zbgzfzZH;
            }

            //注：以综合担保物品作抵押的按风险权重高的得分为准（加权平均）
            double dbdf = 0d;
            int i = 0;
            QueryWrapper<RateDbxxglNy> khdbxxQueryWrapper = new QueryWrapper<>();
            khdbxxQueryWrapper.eq("djnf",djnf);
            khdbxxQueryWrapper.eq("zjhm",zjhm);
            khdbxxQueryWrapper.orderByAsc("dblx");
            List<RateDbxxglNy> khdbxxList = dbxxglNyService.list(khdbxxQueryWrapper);
            for (RateDbxxglNy khdbxx : khdbxxList) {
                String dbwmsvalue = khdbxx.getDbwms();
                log.info("利率定价客户担保物描述: "+dbwmsvalue);
                double dbwscore = Double.parseDouble(iSysDictItemService.getCollateralScore("dbwsdtj_ningyuan",dbwmsvalue));
                log.info("利率定价客户担保物分数: "+dbwscore);
                dbdf += dbwscore;
                i++;
            }
            log.info("利率定价客户担保得分: "+dbdf);
            if (dbdf > 30) {
                dbdf = 30;
            }
            view.put("dbdf", format2Decimal.format(dbdf));
            dfhj += dbdf;

            //对应档次LPR利率
            /*QueryWrapper<Xmgzsz> zbgzxxQueryWrapper = new QueryWrapper<>();
            zbgzxxQueryWrapper.eq("zbid","KH00011");
            if (dkqx == 1) {
                zbgzxxQueryWrapper.eq("zbgzid","GZ00039");
            } else {
                zbgzxxQueryWrapper.eq("zbgzid","GZ00038");
            }
            Xmgzsz zbgzxx = zbgzxxbService.getOne(zbgzxxQueryWrapper,false);
            if (zbgzxx != null) {
                lprll = Double.parseDouble(zbgzxx.getZbjg());
            }
            log.info("对应档次LPR利率为: "+lprll);*/
            //按LPR加/减基点
            /*jdbp = (zxll - lprll) * 100;

            view.put("lprll",format4Decimal.format(lprll));
            view.put("jdbp",format2Decimal.format(jdbp));*/

            //根據'信貸貸款品種'、'貸款期限'、'得分合計'確認相對應執行利率
            //（1）定价得分1-10分（含）的按8.4%（年利率）执行;
            //（2）定价得分10（不含）-45分（含）的取降序区间年利率8.4%-7.8%的区间利率平均值（=（8.4%-7.8%）/（45-10）），按以每增定价得分一分值递减一个区间利率平均值执行;
            //（3）定价得分 45（不含）-65分（含）的取降序区间年利率7.8%-6.96%的区间利率平均值（=（7.8%-6.96%）/（65-45）），按以每增定价得分一分值递减一个区间利率平均值执行;
            //（4）定价得分65（不含）-80分（含）的取降序区间年利率6.96%-6%的区间利率平均值（=（6.96%-6%）/（80-65）），按以每增定价得分一分值递减一个区间利率平均值执行;
            //（5）定价得分80（不含）-90分（含）的取降序区间年利率6%-5.5%的区间利率平均值（=（6%-5.5%）/（90-80）），按以每增定价得分一分值递减一个区间利率平均值执行;
            //（6）定价得分90（不含）-100分（含）的按5.04%（年利率）执行;
            dfhj = Math.round(dfhj);
            log.info("利率定价客户得分合计："+dfhj);
            log.info("利率定价客户贷款期限："+dkqx);
            log.info("利率定价客户信贷贷款品种："+xddkpz);

            QueryWrapper<Zxllpzgl> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("xddkpz",xddkpz);
            queryWrapper.eq("dkqx",dkqx);
            if ((dfhj >= 0 && dfhj <= 10) || (dfhj >= 91 && dfhj <= 100)) {
                queryWrapper.ge("zhpcdf_begin",dfhj);
                queryWrapper.le("zhpcdf_end",dfhj);
            } else {
                queryWrapper.eq("zhpcdf_begin",dfhj);
                queryWrapper.eq("zhpcdf_end",dfhj);
            }
            Zxllpzgl zxllpzgl = iZxllpzglService.getOne(queryWrapper,false);
            if (zxllpzgl != null) {
                zxll = zxllpzgl.getZxllY() == null ? 0d : Double.parseDouble(zxllpzgl.getZxllY().toString());
            }

//            if (dfhj <= 10) {
//                zxll = 8.4d;
//            }
//            if (dfhj > 10 && dfhj <= 45) {}
//            if (dfhj > 45 && dfhj <= 65) {}
//            if (dfhj > 65 && dfhj <= 80) {}
//            if (dfhj > 80 && dfhj <= 90) {}
//            if (dfhj > 90 && dfhj <= 100) {
//                zxll = 5.04d;
//            }
//            if (dfhj > 100) {
//                zxll = 5.04d;
//            }
            view.put("dfhj",format0Decimal.format(dfhj));
            view.put("zxll",format4Decimal.format(zxll));
            return Result.ok(view);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.info(throwable.getMessage());
            return Result.error("分值计算失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 确认
     *
     * @param zxlldjb
     * @return
     */
    @AutoLog(value = "利率定价计算-确认")
    @ApiOperation(value = "利率定价计算-确认", notes = "利率定价计算-确认")
    @RequestMapping(value = "/confirm")
    public Result<?> confirm(@RequestBody RateZxlldjb zxlldjb) {
        try {
            QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
            zxlldjbQueryWrapper.eq("djid", zxlldjb.getDjid());
            RateZxlldjb check = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
            if (check == null) {
                return Result.error("未找到需要确认的记录！");
            }
            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",zxlldjb.getDjnf());
            zxllcxQueryWrapper.eq("zjhm",zxlldjb.getZjhm());
            RateZxllcx zxllcx = zxllcxService.getOne(zxllcxQueryWrapper,false);
            if (zxllcx != null) {
                return Result.ok(zxllcx);
            } else {
                return Result.ok();
            }
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("确认失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 确认保存
     *
     * @param zxlldjb
     * @return
     */
    @AutoLog(value = "利率定价计算-确认保存")
    @ApiOperation(value = "利率定价计算-确认保存", notes = "利率定价计算-确认保存")
    @RequestMapping("/confirmSave")
    public Result<?> confirmSave(@RequestBody RateZxlldjb zxlldjb) {
        try {
            QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
            zxlldjbQueryWrapper.eq("djid", zxlldjb.getDjid());
            RateZxlldjb check = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
            if (check != null) {
                QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
                zxllcxQueryWrapper.eq("djnf",check.getDjnf());
                zxllcxQueryWrapper.eq("zjhm",check.getZjhm());
                RateZxllcx zxllcxRemove = zxllcxService.getOne(zxllcxQueryWrapper,false);
                if (zxllcxRemove != null) {
                    zxllcxService.remove(zxllcxQueryWrapper);
                }
                RateZxllcx zxllcx = new RateZxllcx();
                zxllcx.setDjid(check.getDjid());
                zxllcx.setDjrq(new Timestamp(System.currentTimeMillis()));
                zxllcx.setDjnf(check.getDjnf());
                zxllcx.setZzbz(check.getZzbz());
                zxllcx.setZjhm(check.getZjhm());
                zxllcx.setKhmc(check.getKhmc());
                zxllcx.setDfhj(check.getDfhj());
                zxllcx.setJjll(check.getJjll());
                zxllcx.setZxll(check.getZxll());
                zxllcx.setLprll(check.getLprll());
                zxllcx.setLrsj(new Timestamp(System.currentTimeMillis()));
                zxllcx.setLrczy(getLoginUser().getUsername());
                zxllcxService.save(zxllcx);

                RateLsdjcx lsdjcx = new RateLsdjcx();
                lsdjcx.setDjbs(1);
                lsdjcx.setDjid(check.getDjid());
                lsdjcx.setDjrq(new Timestamp(System.currentTimeMillis()));
                lsdjcx.setDjnf(check.getDjnf());
                lsdjcx.setZzbz(check.getZzbz());
                lsdjcx.setZjhm(check.getZjhm());
                lsdjcx.setKhmc(check.getKhmc());
                lsdjcx.setDfhj(check.getDfhj());
                lsdjcx.setJjll(check.getJjll());
                lsdjcx.setSffd(check.getSffd());
                lsdjcx.setLprll(check.getLprll());
                lsdjcx.setZxll(check.getZxll());
                lsdjcx.setYhhzxll(check.getYhhzxll());
                lsdjcx.setJdbp(check.getJdbp());
                lsdjcx.setYhhjdbp(check.getYhhjdbp());
                lsdjcx.setLrsj(new Date());
                lsdjcx.setLrczy(getLoginUser().getUsername());
                lsdjcxService.save(lsdjcx);
            } else {
                return Result.error("未查找到对应的定价计算信息！");
            }
            zxlldjb.setSpzt(1);
            zxlldjb.setSpr(getLoginUser().getUsername());
            zxlldjb.setSpsj(new Date());
            QueryWrapper<RateZxlldjb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("djid",zxlldjb.getDjid());
            zxlldjbService.update(zxlldjb,queryWrapper);
            return Result.ok("确认成功！");
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("确认保存失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 删除
     *
     * @param djid
     * @return
     */
    @AutoLog(value = "利率定价计算-删除")
    @ApiOperation(value = "利率定价计算-删除", notes = "利率定价计算-删除")
    @RequestMapping("/delete")
    public Result<?> delete(@RequestParam(value = "djid", required = true) String djid) {
        try {
            QueryWrapper<RateZxlldjb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("djid",djid);
            zxlldjbService.remove(queryWrapper);
            return Result.ok("删除成功！");
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
            return Result.error("删除失败！，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价计算 / 导出excel
     *
     * @param request
     * @param zxlldjb
     * @return
     */
    @AutoLog(value = "利率定价计算-导出excel")
    @ApiOperation(value = "利率定价计算-导出excel", notes = "利率定价计算-导出excel")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateZxlldjb zxlldjb) {
        return super.exportXls(request, zxlldjb, RateZxlldjb.class, "利率定价计算");
    }

}

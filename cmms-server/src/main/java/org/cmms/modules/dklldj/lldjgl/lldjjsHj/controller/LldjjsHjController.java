package org.cmms.modules.dklldj.lldjgl.lldjjsHj.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.entity.RateLsdjcx;
import org.cmms.modules.dklldj.lldjgl.khlsdjcx.service.IRateLsdjcxService;
import org.cmms.modules.dklldj.lldjgl.lldjjsHj.entity.RateZxlldjbHj;
import org.cmms.modules.dklldj.lldjgl.lldjjsHj.service.IRateZxlldjbHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsq.service.ILldjsqService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqmxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqxxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateZxllcxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.ILldjsqHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.IRateDjsqmxHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.IRateZxllcxHjService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Description: 利率定价计算
 * @Author: penghr
 * @Date: 2022-04-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "利率定价计算")
@RestController
@RequestMapping("/dklldj/lldjjsHj")
public class LldjjsHjController extends JeecgController<RateZxlldjbHj, IRateZxlldjbHjService> {
    @Autowired
    private IRateZxlldjbHjService zxlldjbService;
    @Autowired
    private ILldjsqHjService lldjsqService;
    @Autowired
    private IRateDjsqmxHjService djsqmxService;
    @Autowired
    private IRateZxllcxHjService zxllcxService;
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
    private IDictValueQuery iDictValueQuery;
    @Autowired
    private ISysDictService iSysDictService;
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
    public Result<?> queryPageList(RateZxlldjbHj zxlldjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<RateZxlldjbHj>> result = new Result<IPage<RateZxlldjbHj>>();
        QueryWrapper<RateZxlldjbHj> queryWrapper = QueryGenerator.initQueryWrapper(zxlldjb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRateZxlldjbHjService.class, zxlldjbService, pageNo, pageSize, queryWrapper, "djnf", "zjhm");
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
            CompletableFuture<RateDjsqxxHj> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxHj djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            CompletableFuture<RateZxlldjbHj> zxlldjbSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateZxlldjbHj> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djnf",djnf);
                zxlldjbQueryWrapper.eq("zjhm",zjhm);
                zxlldjbQueryWrapper.eq("spzt","0");//确认状态 / 未确认
                RateZxlldjbHj zxlldjb = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
                return zxlldjb;
            });
            RateDjsqxxHj djsqxxCheck = djsqxxSupplyAsync.get();
            if (djsqxxCheck == null) {
                return Result.error("未查询到对应的申请信息，请核实！");
            }
            RateZxlldjbHj zxlldjbCheck = zxlldjbSupplyAsync.get();
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
    public Result<?> addSave(@RequestBody RateZxlldjbHj zxlldjb) {
        String dkqx  = zxlldjb.getDkqx();
        Long   djid  = 0L;
        double jzll  = 0d;
        double lprll = 0d;
        String bjrq  = "2022-07-20";
        DecimalFormat format0Decimal = new DecimalFormat("#0");
        DecimalFormat format2Decimal = new DecimalFormat("#0.00");
        DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
        try {
            CompletableFuture<RateZxlldjbHj> zxlldjbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateZxlldjbHj> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djnf", zxlldjb.getDjnf());
                queryWrapper.eq("zjhm", zxlldjb.getZjhm());
                queryWrapper.eq("spzt","0");
                RateZxlldjbHj zxlldjxx = zxlldjbService.getOne(queryWrapper,false);
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

            RateZxlldjbHj zxlldjbCheck = zxlldjbSupplyAsync.get();
            if (zxlldjbCheck != null) {
                return Result.error("同一年度该客户只允许有一条未确认的定价信息！");
            }
            djid = djidSupplyAsync.get();
            List<Csszxx> csszList = csszSupplyAsync.get();

            if ("1".equals(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("CS0012") || item.getCsid().equals("CS0028")).collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    log.info("参数ID："+cssz.getCsid());
                    if ("CS0012".equals(cssz.getCsid())) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("CS0028".equals(cssz.getCsid())) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            if ("2".equals(dkqx)) {
                List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("CS0013") || item.getCsid().equals("CS0029")).collect(Collectors.toList());
                for (Csszxx cssz : CsszCollect) {
                    log.info("参数ID："+cssz.getCsid());
                    if ("CS0013".equals(cssz.getCsid())) {
                        lprll = Double.parseDouble(cssz.getCsvalue());
                    } else if ("CS0029".equals(cssz.getCsid())) {
                        jzll = Double.parseDouble(cssz.getCsvalue());
                    }
                }
            }
            List<Csszxx> CsszCollect = csszList.stream().filter(item -> item.getCsid().equals("CS0017")).collect(Collectors.toList());
            for (Csszxx csszxx : CsszCollect) {
                if ("CS0017".equals(csszxx.getCsid())) {
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
            CompletableFuture<RateDjsqxxHj> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() -> {
                QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxHj djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            RateDjsqxxHj djsqxxCheck = djsqxxSupplyAsync.get();
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
    public Result<?> editSave(@RequestBody RateZxlldjbHj zxlldjb) {
        try {
            //如果重新计算发现该记录是已确认则需要覆盖最新的定价记录
            if (zxlldjb.getSpzt() == 1d) {
                QueryWrapper<RateZxllcxHj> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("djnf",zxlldjb.getDjnf());
                queryWrapper.eq("zjhm",zxlldjb.getZjhm());
                RateZxllcxHj zxllcx = zxllcxService.getOne(queryWrapper,false);
                if (zxllcx != null) {
                    zxllcxService.remove(queryWrapper);
                }
                RateZxllcxHj insert = new RateZxllcxHj();
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
            QueryWrapper<RateZxlldjbHj> queryWrapper = new QueryWrapper<>();
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
        DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
        double dksxed = 0d; //贷款授信额度
        int dbfs = 1;       //担保方式：1 信用/2 保证/3 抵（质）押
        int dkqx = 1;       //贷款期限：1 1年期/2 1年期以上
        int khlx = 1;       //客户类型：1 个人/2 企业
        int sfbmk = 1;      //是否便民卡：1 是/2 否
        int sfbzbxdk = 1;   //是否保证保险贷款：1 是/2 否
        double dfhj = 0d;   //得分合计
        double zxll = 0d;   //执行利率
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            log.info("当前系统法人行社编码："+qydm);

            CompletableFuture<List<Khxmsz>> zbkxxbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Khxmsz> zbkxxbQueryWrapper = new QueryWrapper();
                zbkxxbQueryWrapper.eq("qydm", "440");
                zbkxxbQueryWrapper.orderByAsc("zbid","pxxh");
                List<Khxmsz> zbkxxbList = zbkxxbService.list(zbkxxbQueryWrapper);
                return zbkxxbList;
            });
            CompletableFuture<List<Xmgzsz>> zbgzxxbSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Xmgzsz> zbgzxxbQueryWrapper = new QueryWrapper();
                zbgzxxbQueryWrapper.eq("qydm", "440");
                zbgzxxbQueryWrapper.orderByAsc("zbid","pxxh");
                List<Xmgzsz> zbgzxxList = zbgzxxbService.list(zbgzxxbQueryWrapper);
                return zbgzxxList;
            });
            CompletableFuture<List<Gzbdssz>> gzbdsxxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Gzbdssz> gzbdsxxQueryWrapper = new QueryWrapper();
                gzbdsxxQueryWrapper.eq("qydm", "440");
                gzbdsxxQueryWrapper.orderByAsc("zbgzid","bdskey");
                List<Gzbdssz> gzbdsxxList = gzbdsxxService.list(gzbdsxxQueryWrapper);
                return gzbdsxxList;
            });
            CompletableFuture<List<Csszxx>> csszSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Csszxx> csszQueryWrapper = new QueryWrapper();
                List<Csszxx> csszList = csszxxService.list(csszQueryWrapper);
                return csszList;
            });
            CompletableFuture<RateDjsqxxHj> djsqxxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf",djnf);
                djsqxxQueryWrapper.eq("zjhm",zjhm);
                RateDjsqxxHj djsqxx = lldjsqService.getOne(djsqxxQueryWrapper,false);
                return djsqxx;
            });
            CompletableFuture<List<RateDjsqmxHj>> djsqmxSupplyAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<RateDjsqmxHj> djsqmxQueryWrapper = new QueryWrapper<>();
                djsqmxQueryWrapper.eq("djnf",djnf);
                djsqmxQueryWrapper.eq("zjhm",zjhm);
                List<RateDjsqmxHj> djsqmxList = djsqmxService.list(djsqmxQueryWrapper);
                return djsqmxList;
            });

            RateDjsqxxHj djsqxxCheck = djsqxxSupplyAsync.get();
            if (djsqxxCheck != null) {
                dksxed   = djsqxxCheck.getCdck();
                dbfs     = Integer.parseInt(djsqxxCheck.getXddkpz()); //暂时使用此字段值作为担保方式存储字段
                dkqx     = djsqxxCheck.getDkqx();
                khlx     = djsqxxCheck.getKhlx();
                sfbmk    = djsqxxCheck.getSfbmk();
                sfbzbxdk = djsqxxCheck.getSfbzbxdk();
            }
            List<Khxmsz> zbkxxbLists       = zbkxxbSupplyAsync.get();
            List<Xmgzsz> zbgzxxbLists      = zbgzxxbSupplyAsync.get();
            List<Gzbdssz> gzbdsxxLists     = gzbdsxxSupplyAsync.get();
            List<Csszxx> csszLists         = csszSupplyAsync.get();
            List<RateDjsqmxHj> djsqmxLists = djsqmxSupplyAsync.get();

            for (Khxmsz zbkxxb : zbkxxbLists) {
                double zbgzfzZH = 0d; //指标规则分值总和
                String zbid = zbkxxb.getZbid();
                log.info("当前计算考核项目 / zbid："+zbid);

                //贡献度
                if ("KH00001".equals(zbid)) {
                    List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                    for (Xmgzsz zbgzxxb : zbgzxxbList) {
                        String zbgzid = zbgzxxb.getZbgzid();
                        double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                        double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                        List<RateDjsqmxHj> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                        RateDjsqmxHj djsqmx = djsqmxList.get(0);
                        if (djsqmx != null) {
                            //资金归行率
                            if ("GZ00001".equals(zbgzid)) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                List<Gzbdssz> gzbdsszList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                Gzbdssz gzbdssz = gzbdsszList.get(0);
                                if (gzbdssz != null) {
                                    String bdsvalue = gzbdssz.getBdsvalue();
                                    double dbsfz    = Double.parseDouble(gzbdssz.getBdsfz().toString());
                                    if (dbsfz == 0) {
                                        zbgzfz = 0;
                                    } else {
                                        zbgzfz = dbsfz * zbabs;
                                    }
                                    view.put("ZBJG_"+ zbgzid, bdsvalue);
                                }
                                view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzfz)));
                                zbgzfzZH += zbgzfz;
                            }
                            //利息贡献
                            if ("GZ00002".equals(zbgzid)) {
                                if (dksxed >= 300) {
                                    int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                    List<Gzbdssz> gzbdsszList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                    Gzbdssz gzbdssz = gzbdsszList.get(0);
                                    if (gzbdssz != null) {
                                        String bdsvalue = gzbdssz.getBdsvalue();
                                        double dbsfz    = Double.parseDouble(gzbdssz.getBdsfz().toString());
                                        if (dbsfz == 0) {
                                            zbgzfz = 0;
                                        } else {
                                            zbgzfz = dbsfz * zbabs;
                                        }
                                        view.put("ZBJG_" + zbgzid, bdsvalue);
                                    }
                                    view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzfz)));
                                    zbgzfzZH += zbgzfz;
                                } else {
                                    view.put("ZBJG_"+ zbgzid, "");
                                    view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(0)));
                                    zbgzfzZH += 0;
                                }
                            }
                        }
                    }
                }
                //忠诚度
                if ("KH00002".equals(zbid)) {
                    List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                    for (Xmgzsz zbgzxxb : zbgzxxbList) {
                        String zbgzid = zbgzxxb.getZbgzid();
                        double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                        double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                        double zbgzdf = 0d;
                        List<RateDjsqmxHj> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                        RateDjsqmxHj djsqmx = djsqmxList.get(0);
                        if (djsqmx != null) {
                            //合作时长
                            if ("GZ00003".equals(zbgzid)) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                if (zbgzjg <= 0) {
                                    zbgzdf = 0;
                                } else {
                                    zbgzdf = zbabs * zbgzjg * 2;
                                }
                                if (zbgzdf > zbgzfz) {
                                    zbgzdf = zbgzfz;
                                }
                                view.put("ZBJG_"+ zbgzid, zbgzjg);
                                view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzdf)));
                                zbgzfzZH += zbgzdf;
                            }
                            //产品使用
                            if ("GZ00004".equals(zbgzid)) {
                                String[] strings = new String[1];
                                if (djsqmx.getZbgzjg() != null) {
                                    strings = djsqmx.getZbgzjg().split(",");
                                }
                                if (strings.length > 0) {
                                    String zbgzjgstr = "";
                                    for (String s : strings) {
                                        int zbgzjg = Integer.parseInt(s);
                                        List<Gzbdssz> gzbdsszList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                        Gzbdssz gzbdssz = gzbdsszList.get(0);
                                        if (gzbdssz != null) {
                                            zbgzjgstr = zbgzjgstr.concat(gzbdssz.getBdsvalue()).concat(",");
                                            //300（含）万元以上：个人客户在本行已办理·3-手机银行·计5分、·1-信用卡·计2分、·2-E支付·计3分；对公客户·4-在本行开立基本账户·计7分、·2-E支付·计3分
                                            //300万元以下：个人客户在本行已办理·3-手机银行·计10分、·1-信用卡·计5分、·2-E支付·计5分；对公客户·4-在本行开立基本账户·计15分、·2-E支付·计5分
                                            if (khlx == 1) {
                                                if (dksxed >= 300) {
                                                    if (zbgzjg == 1) {
                                                        zbgzdf += 2;
                                                    }
                                                    if (zbgzjg == 2) {
                                                        zbgzdf += 3;
                                                    }
                                                    if (zbgzjg == 3) {
                                                        zbgzdf += 5;
                                                    }
                                                } else {
                                                    if (zbgzjg == 1) {
                                                        zbgzdf += 5;
                                                    }
                                                    if (zbgzjg == 2) {
                                                        zbgzdf += 5;
                                                    }
                                                    if (zbgzjg == 3) {
                                                        zbgzdf += 10;
                                                    }
                                                }
                                            } else {
                                                if (dksxed >= 300) {
                                                    if (zbgzjg == 2) {
                                                        zbgzdf += 3;
                                                    }
                                                    if (zbgzjg == 4) {
                                                        zbgzdf += 7;
                                                    }
                                                } else {
                                                    if (zbgzjg == 2) {
                                                        zbgzdf += 5;
                                                    }
                                                    if (zbgzjg == 4) {
                                                        zbgzdf += 15;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    view.put("ZBJG_"+ zbgzid, zbgzjgstr.substring(0,zbgzjgstr.length()-1));
                                    view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzdf)));
                                    zbgzfzZH += zbgzdf;
                                } else {
                                    view.put("ZBJG_"+ zbgzid, "");
                                    view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(0)));
                                    zbgzfzZH += 0;
                                }
                            }
                        }
                    }
                }
                //诚信度
                if ("KH00003".equals(zbid)) {
                    List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                    for (Xmgzsz zbgzxxb : zbgzxxbList) {
                        String zbgzid = zbgzxxb.getZbgzid();
                        double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                        double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                        double zbgzdf = 0d;
                        List<RateDjsqmxHj> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                        RateDjsqmxHj djsqmx = djsqmxList.get(0);
                        if (djsqmx != null) {
                            //客户征信记录
                            if ("GZ00005".equals(zbgzid)) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                List<Gzbdssz> gzbdsszList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                                Gzbdssz gzbdssz = gzbdsszList.get(0);
                                if (gzbdssz != null) {
                                    String bdsvalue = gzbdssz.getBdsvalue();
                                    double dbsfz    = Double.parseDouble(gzbdssz.getBdsfz().toString());
                                    if (dbsfz == 0) {
                                        zbgzdf = 0;
                                    } else {
                                        zbgzdf = dbsfz * zbabs;
                                    }
                                    view.put("ZBJG_"+ zbgzid, bdsvalue);
                                }
                                view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzdf)));
                                zbgzfzZH += zbgzdf;
                            }
                            //非恶意产生的贷款本息（含信用卡）逾期期数
                            if ("GZ00006".equals(zbgzid)) {
                                int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                                if (zbgzjg <= 0) {
                                    zbgzdf = 0;
                                } else {
                                    zbgzdf = zbabs * zbgzjg * 2;
                                }
                                if (zbgzdf > zbgzfz) {
                                    zbgzdf = zbgzfz;
                                }
                                view.put("ZBJG_"+ zbgzid, zbgzjg);
                                view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzdf)));
                                zbgzfzZH += zbgzdf;
                            }
                        }
                    }
                }
                //客户类别 / 贷款期限 / 还款方式
                if ("KH00004".equals(zbid) || "KH00005".equals(zbid) || "KH00006".equals(zbid)) {
                    List<Xmgzsz> zbgzxxbList = zbgzxxbLists.stream().filter(item -> item.getZbid().equalsIgnoreCase(zbid)).collect(Collectors.toList());
                    for (Xmgzsz zbgzxxb : zbgzxxbList) {
                        String zbgzid = zbgzxxb.getZbgzid();
                        double zbgzfz = Double.parseDouble(zbgzxxb.getZbgzfz().toString());
                        double zbabs  = Double.parseDouble(zbgzxxb.getZbabs());
                        double zbgzdf = 0d;
                        List<RateDjsqmxHj> djsqmxList = djsqmxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid)).collect(Collectors.toList());
                        RateDjsqmxHj djsqmx = djsqmxList.get(0);
                        if (djsqmx != null) {
                            int zbgzjg = Integer.parseInt(djsqmx.getZbgzjg() == null ? "0" : djsqmx.getZbgzjg());
                            List<Gzbdssz> gzbdsszList = gzbdsxxLists.stream().filter(item -> item.getZbgzid().equalsIgnoreCase(zbgzid) && item.getBdskey().equals(zbgzjg)).collect(Collectors.toList());
                            Gzbdssz gzbdssz = gzbdsszList.get(0);
                            if (gzbdssz != null) {
                                String bdsvalue = gzbdssz.getBdsvalue();
                                double dbsfz    = Double.parseDouble(gzbdssz.getBdsfz().toString());
                                if (dbsfz == 0) {
                                    zbgzfz = 0;
                                } else {
                                    zbgzfz = dbsfz * zbabs;
                                }
                                view.put("ZBJG_"+ zbgzid, bdsvalue);
                            }
                            view.put("ZBDF_" + zbgzid, Double.valueOf(format2Decimal.format(zbgzfz)));
                            zbgzfzZH += zbgzfz;
                        }
                    }
                }

                log.info("当前计算考核项目分值 / zbgzfzZH："+zbgzfzZH);
                view.put("DF_" + zbkxxb.getZbid(), format2Decimal.format(zbgzfzZH));
                dfhj += zbgzfzZH;
            }

            if (dfhj >= 90) {
                if (dbfs == 1) {
                    zxll = 6.5d;
                } else if (dbfs == 2) {
                    zxll = 6d;
                } else {
                    zxll = 5d;
                }
            } else if (dfhj >= 60 && dfhj < 90) {
                if (dbfs == 1) {
                    zxll = 7.5d;
                } else if (dbfs == 2) {
                    zxll = 7d;
                } else {
                    zxll = 6d;
                }
            } else {
                if (dbfs == 1) {
                    zxll = 9d;
                } else if (dbfs == 2) {
                    zxll = 8.5d;
                } else {
                    zxll = 7.5d;
                }
            }

            view.put("dfhj",format2Decimal.format(dfhj));
            view.put("zxll",format4Decimal.format(zxll));
            return Result.ok(view);
        } catch (Throwable throwable) {
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
    public Result<?> confirm(@RequestBody RateZxlldjbHj zxlldjb) {
        try {
            QueryWrapper<RateZxlldjbHj> zxlldjbQueryWrapper = new QueryWrapper<>();
            zxlldjbQueryWrapper.eq("djid", zxlldjb.getDjid());
            RateZxlldjbHj check = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
            if (check == null) {
                return Result.error("未找到需要确认的记录！");
            }
            QueryWrapper<RateZxllcxHj> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",zxlldjb.getDjnf());
            zxllcxQueryWrapper.eq("zjhm",zxlldjb.getZjhm());
            RateZxllcxHj zxllcx = zxllcxService.getOne(zxllcxQueryWrapper,false);
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
    public Result<?> confirmSave(@RequestBody RateZxlldjbHj zxlldjb) {
        try {
            QueryWrapper<RateZxlldjbHj> zxlldjbQueryWrapper = new QueryWrapper<>();
            zxlldjbQueryWrapper.eq("djid", zxlldjb.getDjid());
            RateZxlldjbHj check = zxlldjbService.getOne(zxlldjbQueryWrapper,false);
            if (check != null) {
                QueryWrapper<RateZxllcxHj> zxllcxQueryWrapper = new QueryWrapper<>();
                zxllcxQueryWrapper.eq("djnf",check.getDjnf());
                zxllcxQueryWrapper.eq("zjhm",check.getZjhm());
                RateZxllcxHj zxllcxRemove = zxllcxService.getOne(zxllcxQueryWrapper,false);
                if (zxllcxRemove != null) {
                    zxllcxService.remove(zxllcxQueryWrapper);
                }
                RateZxllcxHj zxllcx = new RateZxllcxHj();
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
            QueryWrapper<RateZxlldjbHj> queryWrapper = new QueryWrapper<>();
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
            QueryWrapper<RateZxlldjbHj> queryWrapper = new QueryWrapper<>();
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
    public ModelAndView exportXls(HttpServletRequest request, RateZxlldjbHj zxlldjb) {
        return super.exportXls(request, zxlldjb, RateZxlldjbHj.class, "利率定价计算");
    }

}

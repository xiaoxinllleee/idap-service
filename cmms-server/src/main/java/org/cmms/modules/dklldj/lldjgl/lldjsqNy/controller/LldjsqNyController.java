package org.cmms.modules.dklldj.lldjgl.lldjsqNy.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.constant.RateNyConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.cmms.modules.dklldj.lldjgl.entity.RateKhzbsjmx;
import org.cmms.modules.dklldj.lldjgl.entity.RateZhckrp;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.entity.RateZxlldjb;
import org.cmms.modules.dklldj.lldjgl.lldjjsNy.service.IRateZxlldjbNyService;
import org.cmms.modules.dklldj.lldjgl.entity.RateDjsqmx;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDbxxglNy;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDjsqxxNy;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.ILldjsqNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateDbxxglNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateDjsqmxNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.IRateZxllcxNyService;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.vo.LldjsqNyVO;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.util.DateUtil;
import org.cmms.modules.xdgl.grdkgl.entity.RateCssz;
import org.cmms.modules.xdgl.grdkgl.entity.RateZbgzxxb;
import org.cmms.modules.xdgl.grdkgl.service.IRateZbgzxxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.Bidi;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @Description: 利率定价申请
 * @Author: penghr
 * @Date: 2022-04-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "利率定价申请")
@RestController
@RequestMapping("/dklldj/lldjsqNy")
public class LldjsqNyController extends JeecgController<RateDjsqxxNy, ILldjsqNyService> {
    @Autowired
    private ILldjsqNyService lldjsqNyService;
    @Autowired
    private IRateDjsqmxNyService djsqmxNyService;
    @Autowired
    private IRateDbxxglNyService dbxxglNyService;
    @Autowired
    private IRateZxlldjbNyService zxlldjbNyService;
    @Autowired
    private IRate_khjbxxbService khjbxxbService;
    @Autowired
    private IRateZxllcxNyService zxllcxNyService;
    @Autowired
    private IRateZxllcxService iZxllcxService;
    @Autowired
    private IRateZbgzxxbService iZbgzxxbService;
    @Autowired
    private ICkzdkbService iCkzdkbService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 利率定价申请 / 分页列表查询
     * @param lldjsq
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "利率定价申请-分页列表查询")
    @ApiOperation(value = "利率定价申请-分页列表查询", notes = "利率定价申请-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(RateDjsqxxNy lldjsq,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RateDjsqxxNy> queryWrapper = QueryGenerator.initQueryWrapper(lldjsq, req.getParameterMap());
        Page<RateDjsqxxNy> page = new Page<RateDjsqxxNy>(pageNo, pageSize);
        IPage<RateDjsqxxNy> pageList = lldjsqNyService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 利率定价申请 / 添加 / 获取客户基本信息
     * @param zzbz
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价申请-添加-获取客户基本信息")
    @ApiOperation(value = "利率定价申请-添加-获取客户基本信息", notes = "利率定价申请-添加-获取客户基本信息")
    @RequestMapping("/addDjsq")
    public Result<?> addDjsq(@RequestParam(name = "djnf", required = true) String djnf,
                             @RequestParam(name = "zzbz", required = true) String zzbz,
                             @RequestParam(name = "zjhm", required = true) String zjhm) {
        LldjsqNyVO lldjsqNyVO = new LldjsqNyVO();
        try {
            DecimalFormat format0Decimal = new DecimalFormat("#0");
            DecimalFormat format2Decimal = new DecimalFormat("#0.00");
            DecimalFormat format3Decimal = new DecimalFormat("#0.000");
            DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
            BigDecimal snsxed  = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal snzxll  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal bhzjcbl = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal sndkrp  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal khxygx  = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal khlxgx  = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
            String GZ37 = "";
            BigDecimal GZ38  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal GZ39  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal QSJD_DYJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal QSJD_DEJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            BigDecimal QSJD_DSJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            String KTSJWY = "2";
            String KTETC  = "2";
            String KTXYK  = "2";

            //存储调用
            // Step1 客户加分项业务信息 PKG_LLDJ.P_KHZBSJ_TQ
            log.info("---------------------------"+djnf.replace("-","")+" - "+zjhm);
            log.info("客户加分项业务信息-存储调用开始");
            lldjsqNyService.ExtractionKhzbsj(djnf.replace("-",""),zjhm);
            log.info("客户加分项业务信息-存储调用结束");
            log.info("---------------------------");
            // Step2 客户前三季度存款日平 PKG_LLDJ.P_ZHJYNRP_TJ
            log.info("客户前三季度存款日平-存储调用开始");
            lldjsqNyService.ExtractionJynrp(djnf.replace("-",""),zjhm);
            log.info("客户前三季度存款日平-存储调用结束");
            log.info("---------------------------");

            //前三季度第一个季度存款日平 GZ00016、***第二个季度*** GZ00017、***第二个季度*** GZ00018
            List<RateZhckrp> zhckrpList = lldjsqNyService.QueryKhZhCkrp("YYYY",djnf.replace("-",""),zjhm);
            if (zhckrpList.size() > 0) {
                for (RateZhckrp zhckrp : zhckrpList) {
                    Double QsjdDyjdCkrp = zhckrp.getQsndynckrp() == null ? 0d : zhckrp.getQsndynckrp().doubleValue();
                    Double QsjdDejdCkrp = zhckrp.getQsndenckrp() == null ? 0d : zhckrp.getQsndenckrp().doubleValue();
                    Double QsjdDsjdCkrp = zhckrp.getQsndsnckrp() == null ? 0d : zhckrp.getQsndsnckrp().doubleValue();
                    QSJD_DYJD_CKRP = QSJD_DYJD_CKRP.add(new BigDecimal(QsjdDyjdCkrp == null ? 0 : QsjdDyjdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    QSJD_DEJD_CKRP = QSJD_DEJD_CKRP.add(new BigDecimal(QsjdDejdCkrp == null ? 0 : QsjdDejdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    QSJD_DSJD_CKRP = QSJD_DSJD_CKRP.add(new BigDecimal(QsjdDsjdCkrp == null ? 0 : QsjdDsjdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
            lldjsqNyVO.setGZ00016(QSJD_DYJD_CKRP.toString());
            lldjsqNyVO.setGZ00017(QSJD_DEJD_CKRP.toString());
            lldjsqNyVO.setGZ00018(QSJD_DSJD_CKRP.toString());
            //其他业务加分项 KH00008 GZ00029 GZ00031 GZ00032
            RateKhzbsjmx khzbsjmx = lldjsqNyService.QueryKhZbsjmx(djnf.replace("-",""),zjhm);
            if (khzbsjmx != null) {
                KTSJWY = khzbsjmx.getSfktsjwy() == null ? "2" : khzbsjmx.getSfktsjwy();
                KTETC  = khzbsjmx.getSfktetcyw() == null ? "2" : khzbsjmx.getSfktetcyw();
                KTXYK  = khzbsjmx.getSfktxykyw() == null ? "2" : khzbsjmx.getSfktxykyw();
            }
            lldjsqNyVO.setGZ00029(KTSJWY);
            lldjsqNyVO.setGZ00031(KTETC);
            lldjsqNyVO.setGZ00032(KTXYK);

            Date year = DateUtil.getYearStartDayByString(djnf);
            Calendar cN = Calendar.getInstance();
            cN.setTime(year);
            cN.add(Calendar.YEAR, -1);
            Date lastYear = new Timestamp(cN.getTimeInMillis());
            //检查定价申请客户信息是否已维护
            QueryWrapper<Rate_khjbxxb> khjbxxqueryWrapper = new QueryWrapper<>();
            khjbxxqueryWrapper.eq("zzbz", zzbz);
            khjbxxqueryWrapper.eq("zjhm", zjhm);
            Rate_khjbxxb khjbxxb = khjbxxbService.getOne(khjbxxqueryWrapper,false);
            if (khjbxxb == null) {
                return Result.error("该组织下客户信息不存在,请在客户基本信息中进行维护/移交！");
            }

            //校验定价申请信息中同一年度该客户是否已存在申请记录
            QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf",year);
            djsqxxQueryWrapper.eq("zzbz",zzbz);
            djsqxxQueryWrapper.eq("zjhm",zjhm);
            RateDjsqxxNy check = lldjsqNyService.getOne(djsqxxQueryWrapper,false);
            if (check != null) {
                return Result.error("已经存在此客户" + StringUtils.substring(djnf,0,4) + "年的申请记录，请选择修改！");
            }
            BeanUtil.copyProperties(khjbxxb,lldjsqNyVO);

            //查询上年授信额度
            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",lastYear);
            zxllcxQueryWrapper.eq("zjhm",zjhm);
            RateZxllcx zxllcx = iZxllcxService.getOne(zxllcxQueryWrapper,false);
            if (zxllcx != null) {
                QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djid",zxllcx.getDjid());
                RateZxlldjb zxlldjb = zxlldjbNyService.getOne(zxlldjbQueryWrapper,false);
                if (zxlldjb != null) {
                    snsxed = zxlldjb.getCdck() == null ? new BigDecimal(0) : zxlldjb.getCdck();
                }
            }
            log.info("当前利率定价客户上年授信额度: " + format3Decimal.format(snsxed));
            lldjsqNyVO.setSnzxll(snsxed);

            //查询上年执行利率
            String yyMM = DateUtil.getLastDayOfYearStr(DateUtils.date2Str(lastYear,new SimpleDateFormat("yyyy-MM-dd"))).substring(2,6);
            String tableName = "ZMCBSBORMBASE"+yyMM;
            snzxll = lldjsqNyService.querySnzxll(tableName, zjhm);
            snzxll = snzxll == null ? new BigDecimal(0) : snzxll.setScale(4,BigDecimal.ROUND_HALF_UP);
            log.info("当前利率定价客户上年执行利率: " + format4Decimal.format(snzxll));
            lldjsqNyVO.setSnzxll(snzxll == null ? new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP) : snzxll);
            lldjsqNyVO.setGZ00021(snzxll == null ? "0.0000" : snzxll.toString());

            tableName = "ZDCBSBORMBASE"+yyMM;
            sndkrp = lldjsqNyService.querySndkrp(tableName, zjhm);
            sndkrp = sndkrp == null ? new BigDecimal(0) : sndkrp.divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).setScale(4,BigDecimal.ROUND_HALF_UP);
            log.info("当前利率定价客户上年贷款日平: " + format4Decimal.format(sndkrp));
            lldjsqNyVO.setSndkrp(sndkrp == null ? new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP) : sndkrp);
            lldjsqNyVO.setGZ00023(sndkrp == null ? "0.0000" : sndkrp.toString());

            //本行资金成本率 KH00006 GZ00022
            QueryWrapper<RateZbgzxxb> zbgzxxbQueryWrapper = new QueryWrapper<>();
            zbgzxxbQueryWrapper.eq("qydm","405");
            zbgzxxbQueryWrapper.eq("zbid","KH00006");
            zbgzxxbQueryWrapper.eq("zbgzid","GZ00022");
            RateZbgzxxb zbgzxxb = iZbgzxxbService.getOne(zbgzxxbQueryWrapper,false);
            if (zbgzxxb != null) {
                bhzjcbl = BigDecimal.valueOf(Double.parseDouble(zbgzxxb.getZbjg())).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            bhzjcbl = bhzjcbl == null ? new BigDecimal(0) : bhzjcbl;
            log.info("本行资金成本率: " + format2Decimal.format(bhzjcbl));
            lldjsqNyVO.setGZ00022(bhzjcbl.toString());

            // 客户效益贡献=(（客户上年贷款执行月利率*12/10-本行资金成本率）/100*客户上年贷款日平)
            khxygx = snzxll.subtract(bhzjcbl).divide(new BigDecimal(100)).multiply(sndkrp).setScale(4,BigDecimal.ROUND_HALF_UP);
            // 客户利息贡献=(（客户上年贷款执行月利率*12/10）/100*客户上年贷款日平)
            khlxgx = snzxll.divide(new BigDecimal(100)).multiply(sndkrp).setScale(4,BigDecimal.ROUND_HALF_UP);
            lldjsqNyVO.setGZ00024(khxygx.toString());
            lldjsqNyVO.setGZ00025(khlxgx.toString());

            zbgzxxbQueryWrapper = new QueryWrapper<>();
            zbgzxxbQueryWrapper.eq("qydm","405");
            zbgzxxbQueryWrapper.eq("zbid","KH00011");
            zbgzxxbQueryWrapper.in("zbgzid","GZ00037","GZ00038","GZ00039");
            List<RateZbgzxxb> zbgzxxbList = iZbgzxxbService.list(zbgzxxbQueryWrapper);
            for (RateZbgzxxb record : zbgzxxbList) {
                if ("GZ00037".equals(record.getZbgzid())) {
                    GZ37 = record.getZbjg();
                } else if ("GZ00038".equals(record.getZbgzid())) {
                    GZ38 = BigDecimal.valueOf(Double.parseDouble(record.getZbjg())).setScale(4,BigDecimal.ROUND_HALF_UP);
                } else if ("GZ00039".equals(record.getZbgzid())) {
                    GZ39 = BigDecimal.valueOf(Double.parseDouble(record.getZbjg())).setScale(4,BigDecimal.ROUND_HALF_UP);
                }
            }
            lldjsqNyVO.setGZ00037(GZ37);
            lldjsqNyVO.setGZ00038(GZ38.toString());
            lldjsqNyVO.setGZ00039(GZ39.toString());

            return Result.ok(lldjsqNyVO);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("利率定价申请添加失败"+throwable);
            return Result.error("利率定价申请添加失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价申请 / 添加保存
     * @param lldjsqNyVO
     * @return
     */
    @AutoLog(value = "利率定价申请-添加保存")
    @ApiOperation(value = "利率定价申请-添加保存", notes = "利率定价申请-添加保存")
    @PostMapping("/add")
    public Result<?> addSave(@RequestBody LldjsqNyVO lldjsqNyVO) {
        try {
            Date djnf = DateUtil.string2Date(lldjsqNyVO.getDjnf(), "yyyy-MM-dd");
            QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf",djnf);
            djsqxxQueryWrapper.eq("zjhm",lldjsqNyVO.getZjhm());
            RateDjsqxxNy check = lldjsqNyService.getOne(djsqxxQueryWrapper,false);
            if (check != null) {
                return Result.error("定价申请信息已存在，请核实！");
            }
            Date Lrsj  = new Date();
            String Lrr = getLoginUser().getUsername();
            //保存定价申请信息
            CompletableFuture<Void> djsqxxRunAsync = CompletableFuture.runAsync(() ->{
                RateDjsqxxNy djsqxx = new RateDjsqxxNy();
                BeanUtil.copyProperties(lldjsqNyVO, djsqxx);
                djsqxx.setLrbz(1);
                djsqxx.setLrr(Lrr);
                djsqxx.setLrsj(Lrsj);
                lldjsqNyService.save(djsqxx);
            });
            //保存定价申请明细
            CompletableFuture<Void> djsqmxRunAsync = CompletableFuture.runAsync(() ->{
                List<RateDjsqmx> djsqmxList = new ArrayList<>();
                RateDjsqmx djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.KH00001);
                djsqmx.setZbgzjg(lldjsqNyVO.getKH00001());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00008);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00008());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00009);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00009());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00010);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00010());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00011);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00011());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00012);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00012());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00013);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00013());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00016);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00016());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00017);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00017());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00018);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00018());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00019);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00019());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00020);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00020());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00021);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00021());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00022);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00022());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00023);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00023());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00024);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00024());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00025);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00025());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00026);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00026());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00027);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00027());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00028);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00028());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00029);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00029());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00030);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00030());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00031);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00031());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00032);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00032());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00037);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00037());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00038);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00038());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmx();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqNyVO.getZjhm());
                djsqmx.setZbgzid(RateNyConstant.GZ00039);
                djsqmx.setZbgzjg(lldjsqNyVO.getGZ00039());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmxNyService.saveBatch(djsqmxList);
            });
            //客户担保信息
            CompletableFuture<Void> khdbxxRunAsync = CompletableFuture.runAsync(() -> {
                List<RateDbxxglNy> khdbxxList = lldjsqNyVO.getKhdbxxList();
                if (CollUtil.isNotEmpty(khdbxxList)) {
                    for (RateDbxxglNy khdbxx : khdbxxList) {
                        RateDbxxglNy dbxxgl = khdbxx;
                        dbxxgl.setDjnf(djnf);
                        dbxxgl.setZjhm(lldjsqNyVO.getZjhm());
                        dbxxgl.setZzbz(lldjsqNyVO.getZzbz());
                        dbxxgl.setLrbz(1);
                        dbxxgl.setLrsj(new Timestamp(System.currentTimeMillis()));
                        dbxxgl.setLrczy(getLoginUser().getUsername());
                        if (dbxxgl.getPgjz() != null && dbxxgl.getPgjz() != 0L) {
                            dbxxgl.setDbl(new BigDecimal(dbxxgl.getSjdbje())
                                  .divide(new BigDecimal(dbxxgl.getPgjz()), 2, BigDecimal.ROUND_HALF_UP)
                                  .multiply(new BigDecimal(100))
                                  .intValue()
                            );
                        } else {
                            dbxxgl.setDbl(0);
                        }
                        dbxxglNyService.save(dbxxgl);
                    }
                }
            });
            djsqxxRunAsync.get();
            djsqmxRunAsync.get();
            khdbxxRunAsync.get();

            return Result.ok("保存成功！");
        } catch (Throwable throwable) {
            log.error("利率定价申请/添加保存失败"+throwable);
            return Result.error("保存失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价申请 / 编辑保存
     * @param lldjsqNyVO
     * @return
     */
    @AutoLog(value = "利率定价申请-编辑保存")
    @ApiOperation(value = "利率定价申请-编辑保存", notes = "利率定价申请-编辑保存")
    @PutMapping("/edit")
    public Result<?> editSave(@RequestBody LldjsqNyVO lldjsqNyVO) {
        try {
            Date djnf = DateUtil.getYearStartDayByString(lldjsqNyVO.getDjnf());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("djnf", djnf);
            queryWrapper.eq("zjhm", lldjsqNyVO.getZjhm());
            lldjsqNyService.remove(queryWrapper);
            djsqmxNyService.remove(queryWrapper);
            dbxxglNyService.remove(queryWrapper);
            addSave(lldjsqNyVO);
            return Result.ok("保存成功！");
        } catch (Throwable throwable) {
            log.error("利率定价申请/编辑保存失败"+throwable);
            return Result.error("保存失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价申请 / 修改 / 获取客户基本信息 / 获取客户定价信息
     * @param djnfstring
     * @param zzbz
     * @param zjhm
     * @return
     */
    @RequestMapping("/editDjsq")
    public Result<?> editDjsq(@RequestParam(name = "djnf", required = true) String djnfstring,
                              @RequestParam(name = "zzbz", required = true) String zzbz,
                              @RequestParam(name = "zjhm", required = true) String zjhm) {
        LldjsqNyVO lldjsqNyVO = new LldjsqNyVO();
        try {
            Date djnf = DateUtil.string2Date(djnfstring,"yyyy-MM-dd");

            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",djnf);
            zxllcxQueryWrapper.eq("zjhm",zjhm);
            RateZxllcx zxllcx = iZxllcxService.getOne(zxllcxQueryWrapper,false);
            if (zxllcx != null) {
                QueryWrapper<RateZxlldjb> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djid",zxllcx.getDjid());
                RateZxlldjb zxlldjb = zxlldjbNyService.getOne(zxlldjbQueryWrapper,false);
                if (zxlldjb != null) {
                    return Result.error("该条申请记录已确认定价，不能修改！");
                }
            }

            QueryWrapper<RateDjsqxxNy> djsqQueryWrapper = new QueryWrapper<>();
            djsqQueryWrapper.eq("djnf",djnf);
            djsqQueryWrapper.eq("zjhm",zjhm);
            RateDjsqxxNy djsq = lldjsqNyService.getOne(djsqQueryWrapper,false);
            if (djsq == null) {
                return Result.error("该条申请记录数据不存在，请核实！");
            }

//            CompletableFuture<Void> pkgRunAsync = CompletableFuture.runAsync(() ->{
//                //存储调用
//                // Step1 客户加分项业务信息 PKG_LLDJ.P_KHZBSJ_TQ
//                lldjsqNyService.ExtractionKhzbsj(djnfstring.replace("-",""),zjhm);
//                // Step2 客户前三季度存款日平 PKG_LLDJ.P_ZHJYNRP_TJ
//                lldjsqNyService.ExtractionJynrp(djnfstring.replace("-",""),zjhm);
//            });
            CompletableFuture<Rate_khjbxxb> khjbxxRunAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zzbz", zzbz);
                queryWrapper.eq("zjhm", zjhm);
                Rate_khjbxxb khjbxx = khjbxxbService.getOne(queryWrapper,false);
                return khjbxx;
            });
            CompletableFuture<Void> djsqxxRunAsync = CompletableFuture.runAsync(() ->{
                QueryWrapper<RateDjsqxxNy> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf", djnf);
                djsqxxQueryWrapper.eq("zzbz", zzbz);
                djsqxxQueryWrapper.eq("zjhm", zjhm);
                RateDjsqxxNy djsqxx = lldjsqNyService.getOne(djsqxxQueryWrapper,false);
                if (djsqxx != null) {
                    lldjsqNyVO.setZzbz(djsqxx.getZzbz());
                    lldjsqNyVO.setZjhm(djsqxx.getZjhm());
                    lldjsqNyVO.setDjnf(djnfstring);
                    lldjsqNyVO.setKhmc(djsqxx.getKhmc());
                    lldjsqNyVO.setFrdb(djsqxx.getFrdb());
                    lldjsqNyVO.setSnsxed(djsqxx.getSnsxed());
                    lldjsqNyVO.setSnzxll(djsqxx.getSnzxll());
                    lldjsqNyVO.setZhsxed(djsqxx.getZhsxed());
                    lldjsqNyVO.setCdck(djsqxx.getCdck());
                    lldjsqNyVO.setDkqx(djsqxx.getDkqx());
                    lldjsqNyVO.setKhlx(djsqxx.getKhlx());
                    lldjsqNyVO.setSfbmk(djsqxx.getSfbmk());
                    lldjsqNyVO.setXddkpz(djsqxx.getXddkpz());
                }

                DecimalFormat format0Decimal = new DecimalFormat("#0");
                DecimalFormat format2Decimal = new DecimalFormat("#0.00");
                DecimalFormat format3Decimal = new DecimalFormat("#0.000");
                DecimalFormat format4Decimal = new DecimalFormat("#0.0000");
                BigDecimal QSJD_DYJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal QSJD_DEJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal QSJD_DSJD_CKRP  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal snzxll  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal sndkrp  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal khxygx  = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal khlxgx  = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
                String KTSJWY = "2";
                String KTETC  = "2";
                String KTXYK  = "2";
                BigDecimal bhzjcbl = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
                String GZ37 = "";
                BigDecimal GZ38  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
                BigDecimal GZ39  = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);

                //前三季度第一个季度存款日平 GZ00016、***第二个季度*** GZ00017、***第二个季度*** GZ00018
                List<RateZhckrp> zhckrpList = lldjsqNyService.QueryKhZhCkrp("YYYY",djnfstring.replace("-",""),zjhm);
                if (zhckrpList.size() > 0) {
                    for (RateZhckrp zhckrp : zhckrpList) {
                        Double QsjdDyjdCkrp = zhckrp.getQsndynckrp() == null ? 0d : zhckrp.getQsndynckrp().doubleValue();
                        Double QsjdDejdCkrp = zhckrp.getQsndenckrp() == null ? 0d : zhckrp.getQsndenckrp().doubleValue();
                        Double QsjdDsjdCkrp = zhckrp.getQsndsnckrp() == null ? 0d : zhckrp.getQsndsnckrp().doubleValue();
                        QSJD_DYJD_CKRP = QSJD_DYJD_CKRP.add(new BigDecimal(QsjdDyjdCkrp == null ? 0 : QsjdDyjdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        QSJD_DEJD_CKRP = QSJD_DEJD_CKRP.add(new BigDecimal(QsjdDejdCkrp == null ? 0 : QsjdDejdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        QSJD_DSJD_CKRP = QSJD_DSJD_CKRP.add(new BigDecimal(QsjdDsjdCkrp == null ? 0 : QsjdDsjdCkrp)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                }
                lldjsqNyVO.setGZ00016(QSJD_DYJD_CKRP.toString());
                lldjsqNyVO.setGZ00017(QSJD_DEJD_CKRP.toString());
                lldjsqNyVO.setGZ00018(QSJD_DSJD_CKRP.toString());

                //其他业务加分项 KH00008 GZ00029 GZ00031 GZ00032
                RateKhzbsjmx khzbsjmx = lldjsqNyService.QueryKhZbsjmx(djnfstring.replace("-",""),zjhm);
                if (khzbsjmx != null) {
                    KTSJWY = khzbsjmx.getSfktsjwy() == null ? "2" : khzbsjmx.getSfktsjwy();
                    KTETC  = khzbsjmx.getSfktetcyw() == null ? "2" : khzbsjmx.getSfktetcyw();
                    KTXYK  = khzbsjmx.getSfktxykyw() == null ? "2" : khzbsjmx.getSfktxykyw();
                }
                lldjsqNyVO.setGZ00029(KTSJWY);
                lldjsqNyVO.setGZ00031(KTETC);
                lldjsqNyVO.setGZ00032(KTXYK);

                //本行资金成本率 KH00006 GZ00022
                QueryWrapper<RateZbgzxxb> zbgzxxbQueryWrapper = new QueryWrapper<>();
                zbgzxxbQueryWrapper.eq("qydm","405");
                zbgzxxbQueryWrapper.eq("zbid","KH00006");
                zbgzxxbQueryWrapper.eq("zbgzid","GZ00022");
                RateZbgzxxb zbgzxxb = iZbgzxxbService.getOne(zbgzxxbQueryWrapper,false);
                if (zbgzxxb != null) {
                    bhzjcbl = BigDecimal.valueOf(Double.parseDouble(zbgzxxb.getZbjg())).setScale(2,BigDecimal.ROUND_HALF_UP);
                }
                bhzjcbl = bhzjcbl == null ? new BigDecimal(0) : bhzjcbl;
                log.info("定价客户本行资金成本率: " + bhzjcbl);
                lldjsqNyVO.setGZ00022(bhzjcbl.toString());

                //查询上年执行利率
                Date year = DateUtil.getYearStartDayByString(djnfstring);
                Calendar cN = Calendar.getInstance();
                cN.setTime(year);
                cN.add(Calendar.YEAR, -1);
                Date lastYear = new Timestamp(cN.getTimeInMillis());
                String yyMM = DateUtil.getLastDayOfYearStr(DateUtils.date2Str(lastYear,new SimpleDateFormat("yyyy-MM-dd"))).substring(2,6);
                String tableName = "ZMCBSBORMBASE"+yyMM;

                snzxll = lldjsqNyService.querySnzxll(tableName, zjhm);
                snzxll = snzxll == null ? new BigDecimal(0) : snzxll.setScale(4,BigDecimal.ROUND_HALF_UP);
                log.info("定价客户上年执行利率: " + format4Decimal.format(snzxll));
                lldjsqNyVO.setSnzxll(snzxll == null ? new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP) : snzxll);
                lldjsqNyVO.setGZ00021(snzxll == null ? "0.0000" : snzxll.toString());

                tableName = "ZDCBSBORMBASE"+yyMM;
                sndkrp = lldjsqNyService.querySndkrp(tableName, zjhm);
                sndkrp = sndkrp == null ? new BigDecimal(0) : sndkrp.divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_UP).setScale(4,BigDecimal.ROUND_HALF_UP);
                log.info("定价客户上年贷款日平: " + format4Decimal.format(sndkrp));
                lldjsqNyVO.setSndkrp(sndkrp == null ? new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP) : sndkrp);
                lldjsqNyVO.setGZ00023(sndkrp == null ? "0.0000" : sndkrp.toString());

                // 客户效益贡献=(（客户上年贷款执行月利率*12/10-本行资金成本率）/100*客户上年贷款日平)
                khxygx = snzxll.subtract(bhzjcbl).divide(new BigDecimal(100)).multiply(sndkrp).setScale(4,BigDecimal.ROUND_HALF_UP);
                // 客户利息贡献=(（客户上年贷款执行月利率*12/10）/100*客户上年贷款日平)
                khlxgx = snzxll.divide(new BigDecimal(100)).multiply(sndkrp).setScale(4,BigDecimal.ROUND_HALF_UP);
                lldjsqNyVO.setGZ00024(khxygx.toString());
                lldjsqNyVO.setGZ00025(khlxgx.toString());

                zbgzxxbQueryWrapper = new QueryWrapper<>();
                zbgzxxbQueryWrapper.eq("qydm","405");
                zbgzxxbQueryWrapper.eq("zbid","KH00011");
                zbgzxxbQueryWrapper.in("zbgzid","GZ00037","GZ00038","GZ00039");
                List<RateZbgzxxb> zbgzxxbList = iZbgzxxbService.list(zbgzxxbQueryWrapper);
                for (RateZbgzxxb record : zbgzxxbList) {
                    if ("GZ00037".equals(record.getZbgzid())) {
                        GZ37 = record.getZbjg();
                    } else if ("GZ00038".equals(record.getZbgzid())) {
                        GZ38 = BigDecimal.valueOf(Double.parseDouble(record.getZbjg())).setScale(4,BigDecimal.ROUND_HALF_UP);
                    } else if ("GZ00039".equals(record.getZbgzid())) {
                        GZ39 = BigDecimal.valueOf(Double.parseDouble(record.getZbjg())).setScale(4,BigDecimal.ROUND_HALF_UP);
                    }
                }
                lldjsqNyVO.setGZ00037(GZ37);
                lldjsqNyVO.setGZ00038(GZ38.toString());
                lldjsqNyVO.setGZ00039(GZ39.toString());
            });

//            pkgRunAsync.get();
            Rate_khjbxxb khjbxx = khjbxxRunAsync.get();
            if (khjbxx == null) {
                return Result.error("该组织下客户信息不存在,请在客户基本信息中进行维护/转移！");
            }
            djsqxxRunAsync.get();

            return Result.ok(lldjsqNyVO);
        } catch (Throwable throwable) {
            log.error("利率定价申请修改失败"+throwable);
            return Result.error("利率定价申请修改失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价申请 / 修改 / 获取申请明细数据
     *
     * @param djnfstr
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价申请-申请明细数据加载返回")
    @ApiOperation(value = "利率定价申请-申请明细数据加载返回", notes = "利率定价申请-申请明细数据加载返回")
    @GetMapping(value = "/sqmxlist")
    public Result<?> sqmxlist(@RequestParam(name = "djnf", required = true) String djnfstr, @RequestParam(name = "zjhm", required = true) String zjhm) {
        JSONObject jsonObject = new JSONObject();
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            QueryWrapper<RateDjsqmx> djsqmxQueryWrapper = new QueryWrapper<>();
            djsqmxQueryWrapper.eq("djnf", djnf);
            djsqmxQueryWrapper.eq("zjhm", zjhm);
            List<RateDjsqmx> djsqmxList = djsqmxNyService.list(djsqmxQueryWrapper);
            if (CollUtil.isNotEmpty(djsqmxList)) {
                for (RateDjsqmx djsqmx : djsqmxList) {
                    jsonObject.put(djsqmx.getZbgzid(), djsqmx.getZbgzjg());
                }
                return Result.ok(jsonObject);
            } else {
                return Result.error("系统未查询到定价明细，请联系系统管理员核实！");
            }
        } catch (Throwable throwable) {
            log.error("利率定价申请-申请明细数据查询失败"+throwable);
            return Result.error("利率定价申请-申请明细数据查询失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价担保信息查询
     *
     * @param djnfstring
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价担保信息查询")
    @ApiOperation(value = "利率定价担保信息查询", notes = "利率定价担保信息查询")
    @RequestMapping("/dbxxList")
    public Result<?> getDbxxList(@RequestParam(name = "djnf") String djnfstring, @RequestParam(name = "zjhm") String zjhm) {
        try {
            Date djnf = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnfstring);
            QueryWrapper<RateDbxxglNy> dbxxQueryWrapper = new QueryWrapper<>();
            dbxxQueryWrapper.eq("djnf",djnf);
            dbxxQueryWrapper.eq("zjhm",zjhm);
            dbxxQueryWrapper.orderByAsc("dbw");
            List<RateDbxxglNy> dbxxList = dbxxglNyService.list(dbxxQueryWrapper);
            return Result.ok(dbxxList);
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 利率定价申请 / 获取担保信息失败！"+throwable.getMessage());
            return Result.error("获取担保信息失败！");
        }
    }

    /**
     * 利率定价申请 / 删除
     * @param djnf
     * @param zjhm
     * @return
     */
    @AutoLog(value = "利率定价申请-通过定价年份、身份证号删除")
    @ApiOperation(value = "利率定价申请-通过定价年份、身份证号删除", notes = "利率定价申请-通过定价年份、身份证号删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "djnf", required = true) String djnf,
                            @RequestParam(name = "zjhm", required = true) String zjhm) {
        Date year = DateUtil.getYearStartDayByString(djnf);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("djnf", year);
        queryWrapper.eq("zjhm", zjhm);
        //先去查询是否做了计算 如果做了就不能删除:rate_zxlldjb
        List list = zxlldjbNyService.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return Result.error("该客户在本年度进行了定价计算，不能删除本条数据！");
        }
        //定价申请信息 rate_djsqxx
        lldjsqNyService.remove(queryWrapper);
        //定价明细信息 rate_djsqmx
        djsqmxNyService.remove(queryWrapper);
        //定价担保信息 rate_dbxxgl
        dbxxglNyService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 利率定价申请 / 导出
     * @param request
     * @param lldjsq
     * @return
     */
    @AutoLog(value = "利率定价申请-导出定价申请信息")
    @ApiOperation(value = "利率定价申请-导出定价申请信息", notes = "利率定价申请-导出定价申请信息")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RateDjsqxxNy lldjsq) {
        return super.exportXls(request, lldjsq, RateDjsqxxNy.class, "利率定价申请信息");
    }
}

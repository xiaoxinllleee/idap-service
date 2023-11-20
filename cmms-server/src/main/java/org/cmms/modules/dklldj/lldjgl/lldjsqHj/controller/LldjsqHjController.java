package org.cmms.modules.dklldj.lldjgl.lldjsqHj.controller;

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
import org.cmms.common.constant.RateHjConstant;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.entity.RateZxllcx;
import org.cmms.modules.dklldj.lldjgl.khzxllcx.service.IRateZxllcxService;
import org.cmms.modules.dklldj.lldjgl.lldjjsHj.entity.RateZxlldjbHj;
import org.cmms.modules.dklldj.lldjgl.lldjjsHj.service.IRateZxlldjbHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqmxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqxxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.ILldjsqHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.IRateDjsqmxHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.IRateZxllcxHjService;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.vo.LldjsqHjVO;
import org.cmms.modules.sjxf.hxxt.ckzdkb.service.ICkzdkbService;
import org.cmms.modules.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
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
@RequestMapping("/dklldj/lldjsqHj")
public class LldjsqHjController extends JeecgController<RateDjsqxxHj, ILldjsqHjService> {
    @Autowired
    private ILldjsqHjService lldjsqHjService;
    @Autowired
    private IRateDjsqmxHjService djsqmxHjService;
    @Autowired
    private IRateZxlldjbHjService zxlldjbHjService;
    @Autowired
    private IRate_khjbxxbService khjbxxbService;
    @Autowired
    private IRateZxllcxHjService zxllcxHjService;
    @Autowired
    private IRateZxllcxService iZxllcxService;
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
    public Result<?> queryPageList(RateDjsqxxHj lldjsq,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<RateDjsqxxHj> queryWrapper = QueryGenerator.initQueryWrapper(lldjsq, req.getParameterMap());
        Page<RateDjsqxxHj> page = new Page<RateDjsqxxHj>(pageNo, pageSize);
        IPage<RateDjsqxxHj> pageList = lldjsqHjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 利率定价申请 / 添加 / 获取客户基本信息
     * @param zzbz
     * @param zjhm
     * @return
     */
    @RequestMapping("/addDjsq")
    public Result<?> addDjsq(@RequestParam(name = "djnf", required = true) String djnf,
                             @RequestParam(name = "zzbz", required = true) String zzbz,
                             @RequestParam(name = "zjhm", required = true) String zjhm) {
        LldjsqHjVO lldjsqHjVO = new LldjsqHjVO();
        try {
            BigDecimal snsxed = new BigDecimal(0).setScale(2,BigDecimal.ROUND_HALF_UP);
            BigDecimal snzxll = new BigDecimal(0).setScale(4,BigDecimal.ROUND_HALF_UP);
            Date year = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
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
            QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf",year);
            djsqxxQueryWrapper.eq("zzbz",zzbz);
            djsqxxQueryWrapper.eq("zjhm",zjhm);
            RateDjsqxxHj check = lldjsqHjService.getOne(djsqxxQueryWrapper,false);
            if (check != null) {
                return Result.error("已经存在此客户" + StringUtils.substring(djnf,0,4) + "年的申请记录，请选择修改！");
            }
            BeanUtil.copyProperties(khjbxxb,lldjsqHjVO);

            //查询上年授信额度
            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",lastYear);
            zxllcxQueryWrapper.eq("zjhm",zjhm);
            RateZxllcx zxllcx = iZxllcxService.getOne(zxllcxQueryWrapper,false);
            if (zxllcx != null) {
                QueryWrapper<RateZxlldjbHj> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djid",zxllcx.getDjid());
                RateZxlldjbHj zxlldjb = zxlldjbHjService.getOne(zxlldjbQueryWrapper,false);
                if (zxlldjb != null) {
                    snsxed = zxlldjb.getCdck();
                }
            }
            lldjsqHjVO.setSnzxll(snsxed);

            //查询上年执行利率
            String yyMM = DateUtils.date2Str(lastYear,new SimpleDateFormat("yyMM"));
            String tableName = "ZMCBSBORMBASE"+yyMM;
            snzxll = lldjsqHjService.querySnzxll(tableName,zjhm);
            lldjsqHjVO.setSnzxll(snzxll);
            //查询客户最新存款日平余额总和（个人客户包含关联人，企业客户只包含自身）
            BigDecimal ckrpye = new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP);
            String yyMMdd = iCkzdkbService.getMaxLoadDate();
            tableName = "ZDCBSINVMBASE"+yyMMdd;
            if (lldjsqHjVO.getKhlx() == 1) {
                ckrpye = lldjsqHjService.queryCkrpyeSumGR(tableName,zjhm).setScale(4, BigDecimal.ROUND_HALF_UP);
            }
            if (lldjsqHjVO.getKhlx() == 2) {
                ckrpye = lldjsqHjService.queryCkrpyeSumQY(tableName,zjhm).setScale(4, BigDecimal.ROUND_HALF_UP);
            }
            lldjsqHjVO.setCkrpye(ckrpye);

            return Result.ok(lldjsqHjVO);
        } catch (Throwable throwable) {
            log.error("利率定价申请添加失败"+throwable);
            return Result.error("利率定价申请添加失败，请联系系统管理员！"+throwable.getMessage());
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
        LldjsqHjVO lldjsqHjVO = new LldjsqHjVO();
        try {
            Date djnf = DateUtil.string2Date(djnfstring,"yyyy-MM-dd");
            QueryWrapper<RateZxllcx> zxllcxQueryWrapper = new QueryWrapper<>();
            zxllcxQueryWrapper.eq("djnf",djnf);
            zxllcxQueryWrapper.eq("zjhm",zjhm);
            RateZxllcx zxllcx = iZxllcxService.getOne(zxllcxQueryWrapper,false);
            if (zxllcx != null) {
                QueryWrapper<RateZxlldjbHj> zxlldjbQueryWrapper = new QueryWrapper<>();
                zxlldjbQueryWrapper.eq("djid",zxllcx.getDjid());
                RateZxlldjbHj zxlldjb = zxlldjbHjService.getOne(zxlldjbQueryWrapper,false);
                if (zxlldjb != null) {
                    return Result.error("该条申请记录已确认定价，不能修改！");
                }
            }
            CompletableFuture<Rate_khjbxxb> khjbxxRunAsync = CompletableFuture.supplyAsync(() ->{
                QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("zzbz", zzbz);
                queryWrapper.eq("zjhm", zjhm);
                Rate_khjbxxb khjbxx = khjbxxbService.getOne(queryWrapper,false);
                return khjbxx;
            });
            CompletableFuture<Void> djsqxxRunAsync = CompletableFuture.runAsync(() ->{
                QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
                djsqxxQueryWrapper.eq("djnf", djnf);
                djsqxxQueryWrapper.eq("zzbz", zzbz);
                djsqxxQueryWrapper.eq("zjhm", zjhm);
                RateDjsqxxHj djsqxx = lldjsqHjService.getOne(djsqxxQueryWrapper,false);
                if (djsqxx != null) {
                    lldjsqHjVO.setZzbz(djsqxx.getZzbz());
                    lldjsqHjVO.setZjhm(djsqxx.getZjhm());
                    lldjsqHjVO.setDjnf(djnfstring);
                    lldjsqHjVO.setKhmc(djsqxx.getKhmc());
                    lldjsqHjVO.setFrdb(djsqxx.getFrdb());
                    lldjsqHjVO.setSnsxed(djsqxx.getSnsxed());
                    lldjsqHjVO.setSnzxll(djsqxx.getSnzxll());
                    lldjsqHjVO.setZhsxed(djsqxx.getZhsxed());
                    lldjsqHjVO.setCdck(djsqxx.getCdck());
                    lldjsqHjVO.setDkqx(djsqxx.getDkqx());
                    lldjsqHjVO.setXddkpz(djsqxx.getXddkpz());
                    lldjsqHjVO.setKhlx(djsqxx.getKhlx());
                    lldjsqHjVO.setSfbmk(djsqxx.getSfbmk());
                    lldjsqHjVO.setSfbzbxdk(djsqxx.getSfbzbxdk());
                    //查询客户最新存款日平余额总和（个人客户包含关联人，企业客户只包含自身）
                    BigDecimal ckrpye = new BigDecimal(0).setScale(4, BigDecimal.ROUND_HALF_UP);
                    String yyMMdd = iCkzdkbService.getMaxLoadDate();
                    String tableName = "ZDCBSINVMBASE"+yyMMdd;
                    if (lldjsqHjVO.getKhlx() == 1) {
                        ckrpye = lldjsqHjService.queryCkrpyeSumGR(tableName,zjhm).setScale(4, BigDecimal.ROUND_HALF_UP);
                    }
                    if (lldjsqHjVO.getKhlx() == 2) {
                        ckrpye = lldjsqHjService.queryCkrpyeSumQY(tableName,zjhm).setScale(4, BigDecimal.ROUND_HALF_UP);
                    }
                    lldjsqHjVO.setCkrpye(ckrpye);
                }
            });

            Rate_khjbxxb khjbxx = khjbxxRunAsync.get();
            if (khjbxx == null) {
                return Result.error("该组织下客户信息不存在,请在客户基本信息中进行维护/转移！");
            }
            djsqxxRunAsync.get();

            return Result.ok(lldjsqHjVO);
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
    public Result<?> sqmxlist(@RequestParam(name = "djnf", required = true) String djnfstr,
                              @RequestParam(name = "zjhm", required = true) String zjhm) {
        JSONObject jsonObject = new JSONObject();
        try {
            Date djnf = DateUtil.string2Date(djnfstr,"yyyy-MM-dd");
            QueryWrapper<RateDjsqmxHj> djsqmxQueryWrapper = new QueryWrapper<>();
            djsqmxQueryWrapper.eq("djnf", djnf);
            djsqmxQueryWrapper.eq("zjhm", zjhm);
            List<RateDjsqmxHj> djsqmxList = djsqmxHjService.list(djsqmxQueryWrapper);
            if (CollUtil.isNotEmpty(djsqmxList)) {
                for (RateDjsqmxHj djsqmx : djsqmxList) {
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
     * 利率定价申请 / 添加保存
     * @param lldjsqHjVO
     * @return
     */
    @AutoLog(value = "利率定价申请-添加保存")
    @ApiOperation(value = "利率定价申请-添加保存", notes = "利率定价申请-添加保存")
    @PostMapping("/add")
    public Result<?> addSave(@RequestBody LldjsqHjVO lldjsqHjVO) {
        try {
            Date djnf = org.cmms.modules.util.DateUtil.string2Date(lldjsqHjVO.getDjnf(), "yyyy-MM-dd");
            QueryWrapper<RateDjsqxxHj> djsqxxQueryWrapper = new QueryWrapper<>();
            djsqxxQueryWrapper.eq("djnf",djnf);
            djsqxxQueryWrapper.eq("zjhm",lldjsqHjVO.getZjhm());
            RateDjsqxxHj check = lldjsqHjService.getOne(djsqxxQueryWrapper,false);
            if (check != null) {
                return Result.error("定价申请信息已存在，请核实！");
            }
            Date Lrsj  = new Date();
            String Lrr = getLoginUser().getUsername();
            //保存定价申请信息
            CompletableFuture<Void> djsqxxRunAsync = CompletableFuture.runAsync(() ->{
                RateDjsqxxHj djsqxx = new RateDjsqxxHj();
                BeanUtil.copyProperties(lldjsqHjVO, djsqxx);
                djsqxx.setLrbz(1);
                djsqxx.setLrr(Lrr);
                djsqxx.setLrsj(Lrsj);
                lldjsqHjService.save(djsqxx);
            });
            //保存定价申请明细
            CompletableFuture<Void> djsqmxRunAsync = CompletableFuture.runAsync(() ->{
                List<RateDjsqmxHj> djsqmxList = new ArrayList<>();
                RateDjsqmxHj djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00001);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00001());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00002);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00002());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00003);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00003());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00004);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00004());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00005);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00005());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00006);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00006());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00007);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00007());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00008);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00008());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmx = new RateDjsqmxHj();
                djsqmx.setDjnf(djnf);
                djsqmx.setZjhm(lldjsqHjVO.getZjhm());
                djsqmx.setZbgzid(RateHjConstant.GZ00009);
                djsqmx.setZbgzjg(lldjsqHjVO.getGZ00009());
                djsqmx.setLrbz(1);
                djsqmx.setLrr(Lrr);
                djsqmx.setLrsj(Lrsj);
                djsqmxList.add(djsqmx);

                djsqmxHjService.saveBatch(djsqmxList);
            });
            djsqxxRunAsync.get();
            djsqmxRunAsync.get();

            return Result.ok("保存成功！");
        } catch (Throwable throwable) {
            log.error("利率定价申请/添加保存失败"+throwable);
            return Result.error("保存失败，请联系系统管理员！"+throwable.getMessage());
        }
    }

    /**
     * 利率定价申请 / 编辑保存
     * @param lldjsqHjVO
     * @return
     */
    @AutoLog(value = "利率定价申请-编辑保存")
    @ApiOperation(value = "利率定价申请-编辑保存", notes = "利率定价申请-编辑保存")
    @PutMapping("/edit")
    public Result<?> editSave(@RequestBody LldjsqHjVO lldjsqHjVO) {
        try {
            Date djnf = DateUtil.getYearStartDayByString(lldjsqHjVO.getDjnf());
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("djnf", djnf);
            queryWrapper.eq("zjhm", lldjsqHjVO.getZjhm());
            lldjsqHjService.remove(queryWrapper);
            djsqmxHjService.remove(queryWrapper);
            addSave(lldjsqHjVO);
            return Result.ok("保存成功！");
        } catch (Throwable throwable) {
            log.error("利率定价申请/编辑保存失败"+throwable);
            return Result.error("保存失败，请联系系统管理员！"+throwable.getMessage());
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
        Date year = org.cmms.modules.util.DateUtil.getYearStartDayByString(djnf);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("djnf", year);
        queryWrapper.eq("zjhm", zjhm);
        //先去查询是否做了计算 如果做了就不能删除:rate_zxlldjb
        List list = zxlldjbHjService.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return Result.error("该客户在本年度进行了定价计算，不能删除本条数据！");
        }
        //定价申请信息 rate_djsqxx
        lldjsqHjService.remove(queryWrapper);
        //定价明细信息 rate_djsqmx
        djsqmxHjService.remove(queryWrapper);
        //定价担保信息 rate_dbxxgl
        //dbxxglHjService.remove(queryWrapper);
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
    public ModelAndView exportXls(HttpServletRequest request, RateDjsqxxHj lldjsq) {
        return super.exportXls(request, lldjsq, RateDjsqxxHj.class, "利率定价申请信息");
    }
}

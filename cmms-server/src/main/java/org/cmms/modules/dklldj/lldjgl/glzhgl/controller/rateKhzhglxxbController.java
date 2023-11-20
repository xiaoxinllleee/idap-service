package org.cmms.modules.dklldj.lldjgl.glzhgl.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import net.sf.jsqlparser.expression.operators.relational.JsonOperator;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.jbxxgl.glrxxgl.entity.Rate_khglrxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.rateKhzhglxxb;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.ICbsInvmBaseService;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.IrateKhzhglxxbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.entity.Kzhglgx;
import org.cmms.modules.sjxf.hxxt.jjk.kzhglgx.service.IKzhglgxService;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户账号关联信息管理
 * @Author: jeecg-boot
 * @Date: 2020-03-30
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "关联账号管理")
@RestController
@RequestMapping("/rateKhzhglxxb/rateKhzhglxxb")
public class rateKhzhglxxbController extends JeecgController<rateKhzhglxxb, IrateKhzhglxxbService> {
    @Autowired
    private IrateKhzhglxxbService rateKhzhglxxbService;
    @Autowired
    private IRate_khjbxxbService iKhjbxxbService;
    @Autowired
    private ICbsInvmBaseService iCbsInvmBaseService;
    @Autowired
    private IKzhglgxService iCbscLinkService;
    @Autowired
    private IDkzdkbService iDkzdkbService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value(value = "${common.path.upload}")
    private String uploadPath;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 关联账号管理 / 分页列表查询
     *
     * @param rateKhzhglxxb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "关联账号管理-分页列表查询")
    @ApiOperation(value = "关联账号管理-分页列表查询", notes = "关联账号管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(rateKhzhglxxb rateKhzhglxxb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<rateKhzhglxxb>> result = new Result<IPage<rateKhzhglxxb>>();
        QueryWrapper<rateKhzhglxxb> queryWrapper = QueryGenerator.initQueryWrapper(rateKhzhglxxb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IrateKhzhglxxbService.class, rateKhzhglxxbService, pageNo, pageSize, queryWrapper, "zjhm", "ckzh");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 关联账号管理 / 添加
     *
     * @param form
     * @return
     */
    @AutoLog(value = "关联账号管理-添加")
    @ApiOperation(value = "关联账号管理-添加", notes = "关联账号管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody rateKhzhglxxb form) {
        try {
            form.setLrbz(1);
            form.setLrsj(new Timestamp(System.currentTimeMillis()));
            form.setLrczy(getLoginUser().getUsername());
            String zzmc = "";
            String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            if (form.getCkzh().startsWith("89") && "020".equalsIgnoreCase(qydm)) {
                return Result.error("不允许录入一本通主账号，请输入对应的子账号");
            }
            QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", form.getZjhm());
            Rate_khjbxxb khjbxx = iKhjbxxbService.getOne(queryWrapper);
            if (khjbxx == null) {
                return Result.error("证件号【" + form.getZjhm() + "】" + "在客户信息表中无信息，请先添加!");
            } else {
                zzmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",khjbxx.getZzbz());
                if (khjbxx.getZzbz() == null || khjbxx.getZzbz().length() == 0) {
                    return Result.error("证件号【" + form.getZjhm() + "】" + "所属机构为空，请到客户信息管理中进行维护!");
                } else {
                    form.setZzbz(khjbxx.getZzbz());
                    form.setKhmc(khjbxx.getKhmc());
                }
                if (!"admin".equalsIgnoreCase(getLoginUser().getUsername())) {
                    if (!khjbxx.getZzbz().equals(getLoginUser().getOrgCode())) {
                        return Result.error("关联失败，此证件信息所属网点【" + zzmc + "】" + ",和您所在网点不一致！");
                    }
                }
            }
            //检查表主键的唯一性
            QueryWrapper<rateKhzhglxxb> khzhglxxbQueryWrapper = new QueryWrapper<>();
            khzhglxxbQueryWrapper.eq("ckzh",form.getCkzh());
            rateKhzhglxxb check = rateKhzhglxxbService.getOne(khzhglxxbQueryWrapper);
            if (check != null) {
                zzmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",check.getZzbz());
                return Result.error("数据已被网点【" + zzmc + "】" + check.getKhmc() + "关联！");
            }
            QueryWrapper<CbsInvmBase> invmBaseQueryWrapper = new QueryWrapper<>();
            invmBaseQueryWrapper.eq("sub_acct_no", form.getCkzh());
            CbsInvmBase invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
            if (invmBase != null) {
                form.setZhmc(invmBase.getCustName());
            } else {
                //判断是否是卡号
                QueryWrapper<Kzhglgx> cbscLinkQueryWrapper = new QueryWrapper<>();
                cbscLinkQueryWrapper.eq("card","0"+form.getCkzh());
                cbscLinkQueryWrapper.in("iso_type","1","4");
                cbscLinkQueryWrapper.eq("is_primary","Y");
                Kzhglgx cbscLink = iCbscLinkService.getOne(cbscLinkQueryWrapper);
                if (cbscLink != null) {
                    invmBaseQueryWrapper = new QueryWrapper<>();
                    invmBaseQueryWrapper.eq("sub_acct_no", cbscLink.getAccount());
                    invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
                    if (invmBase != null) {
                        khzhglxxbQueryWrapper = new QueryWrapper<>();
                        khzhglxxbQueryWrapper.eq("ckzh",invmBase.getSubAcctNo());
                        check = rateKhzhglxxbService.getOne(khzhglxxbQueryWrapper);
                        if (check != null) {
                            zzmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",check.getZzbz());
                            return Result.error("数据已被网点【" + zzmc + "】" + check.getKhmc() + "关联！");
                        } else {
                            khzhglxxbQueryWrapper = new QueryWrapper<>();
                            khzhglxxbQueryWrapper.eq("dyzkh", form.getCkzh());
                            check = rateKhzhglxxbService.getOne(khzhglxxbQueryWrapper);
                            if (check != null) {
                                zzmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",check.getZzbz());
                                return Result.error("数据已被网点【" + zzmc + "】" + check.getKhmc() + "关联！");
                            }
                        }
                        form.setZhmc(invmBase.getCustName());
                        form.setDyzkh(form.getCkzh());
                        form.setCkzh(invmBase.getSubAcctNo());
                    }
                } else {
                    return Result.error("关联存款账号不存在或输入错误！");
                }
            }
            rateKhzhglxxbService.save(form);
            return Result.ok("添加成功！");
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            log.error("贷款利率定价 / 关联账号管理 / 添加失败！"+throwable.getMessage());
            return Result.error("添加失败！");
        }
    }

    /**
     * 关联账号管理 / 编辑
     *
     * @param form
     * @return
     */
    @AutoLog(value = "关联账号管理-编辑")
    @ApiOperation(value = "关联账号管理-编辑", notes = "关联账号管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody rateKhzhglxxb form) {
        try {
            QueryWrapper<rateKhzhglxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",form.getZjhm());
            queryWrapper.eq("ckzh",form.getCkzh());
            rateKhzhglxxb khzhglxxb = rateKhzhglxxbService.getOne(queryWrapper);
            if (khzhglxxb == null) {
                return Result.error("数据不存在，请核实！");
            }
            QueryWrapper<CbsInvmBase> invmBaseQueryWrapper = new QueryWrapper<>();
            invmBaseQueryWrapper.eq("sub_acct_no", form.getCkzh());
            CbsInvmBase invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
            if (invmBase != null) {
                form.setZhmc(invmBase.getCustName());
            } else {
                //判断是否是卡号
                QueryWrapper<Kzhglgx> cbscLinkQueryWrapper = new QueryWrapper<>();
                cbscLinkQueryWrapper.eq("card","0"+form.getCkzh());
                cbscLinkQueryWrapper.in("iso_type","1","4");
                Kzhglgx cbscLink = iCbscLinkService.getOne(cbscLinkQueryWrapper);
                if (cbscLink != null) {
                    invmBaseQueryWrapper = new QueryWrapper<>();
                    invmBaseQueryWrapper.eq("sub_acct_no", cbscLink.getAccount());
                    invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
                    if (invmBase != null) {
                        form.setZhmc(invmBase.getCustName());
                        form.setDyzkh(form.getCkzh());
                        form.setCkzh(invmBase.getSubAcctNo());
                    }
                } else {
                    return Result.error("关联存款账号不存在或输入错误！");
                }
            }
            form.setLrbz(2);
            form.setLrsj(new Timestamp(System.currentTimeMillis()));
            form.setLrczy(getLoginUser().getUsername());
            QueryWrapper<rateKhzhglxxb> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("zjhm", form.getZjhm());
            queryWrapper1.eq("ckzh", form.getCkzh());
            form.setZjhm(null);
            form.setCkzh(null);
            rateKhzhglxxbService.update(form, queryWrapper1);
            return Result.ok("编辑成功!");
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            log.error("贷款利率定价 / 关联账号管理 / 编辑失败！"+throwable.getMessage());
            return Result.error("编辑失败!");
        }
    }

    /**
     * 关联账号管理 / 删除 / 若该账号户主贷款余额大于0，则提示无法删除此存款账号信息
     *
     * @param zjhm
     * @param ckzh
     * @return
     */
    @AutoLog(value = "关联账号管理-删除")
    @ApiOperation(value = "关联账号管理-删除", notes = "关联账号管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "zjhm", required = true) String zjhm,
                            @RequestParam(name = "ckzh", required = true) String ckzh) {
        try {
            QueryWrapper<rateKhzhglxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",zjhm);
            queryWrapper.eq("ckzh",ckzh);
            rateKhzhglxxb zhglxx = rateKhzhglxxbService.getOne(queryWrapper);
            if (zhglxx == null) {
                return Result.error("删除失败，数据不存在!");
            } else {
                double dkye = iDkzdkbService.queryDkye(zhglxx.getZjhm());
                if (dkye > 0) {
                    return Result.error("该定价客户贷款余额大于0，不能删除此存款账号信息!");
                }
            }
            QueryWrapper<rateKhzhglxxb> deleteWrapper = new QueryWrapper<>();
            deleteWrapper.eq("zjhm",zjhm);
            deleteWrapper.eq("ckzh",ckzh);
            rateKhzhglxxbService.remove(deleteWrapper);
            return Result.ok("删除成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 关联账号管理 / 删除失败"+throwable.getMessage());
            return Result.error("删除失败!");
        }
    }

    /**
     * 关联账号管理 / 管理员删除
     *
     * @param ckzh
     * @return
     */
    @AutoLog(value = "关联账号管理-超级删除")
    @ApiOperation(value = "关联账号管理-超级删除", notes = "关联账号管理-超级删除")
    @DeleteMapping(value = "/adminDelete")
    public Result<?> deleteBatch(@RequestParam(name = "zjhm", required = true) String zjhm,
                                 @RequestParam(name = "ckzh", required = true) String ckzh) {
        try {
            QueryWrapper<rateKhzhglxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",zjhm);
            queryWrapper.eq("ckzh",ckzh);
            rateKhzhglxxb zhglxx = rateKhzhglxxbService.getOne(queryWrapper);
            if (zhglxx == null) {
                return Result.error("删除失败，数据不存在!");
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm",zjhm);
            queryWrapper.eq("ckzh",ckzh);
            rateKhzhglxxbService.remove(queryWrapper);
            return Result.ok("删除成功!");
        } catch (Exception e) {
            log.error("贷款利率定价 / 关联账号管理 / 超级删除失败！"+e.getMessage());
            return Result.error("删除失败!");
        }
    }

    /**
     * 关联账号管理 / 提取
     *
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract(@RequestBody JSONObject jsonObject) {
        Result result = new Result<>();
        //String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            String IDNET_NO = jsonObject.getString("zjhm");
            params.put("ident_no", IDNET_NO);
            params.put("etl_task","kiss.domain.application.rate.proc_rate_khzhglxx_tj");
            // count_rate_khzhglxx_tj
            boolean completionSignal = EtlUtil.callEtl("rate_common_init", params, 15);
            result.setSuccess(completionSignal);
        } else {
            try {
                rateKhzhglxxbService.extract(jsonObject.getString("zjhm"));
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                //System.out.println(e);
                log.error("提取失败！"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 关联账号管理 / 通过关联存款账号或卡号查询关联账号名称
     *
     * @param ckzh
     * @return
     */
    @AutoLog(value = "关联账号管理-通过关联存款账号/卡号查询关联账号名称")
    @ApiOperation(value = "关联账号管理-通过关联存款账号/卡号查询关联账号名称", notes = "关联账号管理-通过关联存款账号/卡号查询关联账号名称")
    @GetMapping(value = "/queryCustName")
    public Result<?> queryCustName(@RequestParam(name = "ckzh", required = true) String ckzh) {
        String glzhmc = "";
        try {
            if ("89".startsWith(ckzh)) {
                return Result.error("不允许录入一本通主账号，请输入对应的子账号！");
            } else {
                QueryWrapper<CbsInvmBase> invmBaseQueryWrapper = new QueryWrapper<>();
                invmBaseQueryWrapper.eq("sub_acct_no", ckzh);
                CbsInvmBase invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
                if (invmBase != null) {
                    glzhmc = invmBase.getCustName();
                } else {
                    QueryWrapper<Kzhglgx> cbscLinkQueryWrapper = new QueryWrapper<>();
                    cbscLinkQueryWrapper.eq("card","0" + ckzh);
                    cbscLinkQueryWrapper.in("iso_type","1","4");
                    Kzhglgx cbscLink = iCbscLinkService.getOne(cbscLinkQueryWrapper);
                    if (cbscLink != null) {
                        invmBaseQueryWrapper = new QueryWrapper<>();
                        invmBaseQueryWrapper.eq("sub_acct_no", cbscLink.getAccount());
                        invmBase = iCbsInvmBaseService.getOne(invmBaseQueryWrapper);
                        if (invmBase != null) {
                            glzhmc = invmBase.getCustName();
                        }
                    } else {
                        return Result.error("未找到对应的存款账号或卡号！");
                    }
                }
            }
            return Result.ok(glzhmc);
        } catch (Throwable throwable) {
            //throwable.printStackTrace();
            log.error("系统错误，请联系管理员处理！"+throwable.getMessage());
            return Result.error("查询关联账号名称失败！");
        }
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rateKhzhglxxb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, rateKhzhglxxb rateKhzhglxxb) {
        return super.exportXls(request, rateKhzhglxxb, rateKhzhglxxb.class, "客户关联信息");
    }

    /**
     * 导出Excel导入模板
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "关联账号信息导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, rateKhzhglxxb.class);
        ExportParams exportParams = new ExportParams("关联账号信息导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<rateKhzhglxxb>());
        return modelAndView;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(2);
            importParams.setHeadRows(1);
            importParams.setNeedSave(true);
            try {
                LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                List<rateKhzhglxxb> Rate_khjbxxbList = ExcelImportUtil.importExcel(file.getInputStream(), rateKhzhglxxb.class, importParams);
                List<String> list = new ArrayList<String>();
                for (rateKhzhglxxb khzhglxxb : Rate_khjbxxbList) {
                    if (khzhglxxb.getZjhm() != null && khzhglxxb.getZjhm().contains(".")) {
                        khzhglxxb.setZjhm(khzhglxxb.getZjhm().split("\\.")[0]);
                    }
                    if (khzhglxxb.getCkzh() != null && khzhglxxb.getCkzh().contains(".")) {
                        khzhglxxb.setZjhm(khzhglxxb.getCkzh().split("\\.")[0]);
                    }
                    if (khzhglxxb.getDyzkh() != null && khzhglxxb.getDyzkh().contains(".")) {
                        khzhglxxb.setZjhm(khzhglxxb.getDyzkh().split("\\.")[0]);
                    }
                    khzhglxxb.setLrbz(0);
                    khzhglxxb.setLrczy(sysUser.getUsername());
                    khzhglxxb.setLrsj(new Date());
                    //khzhglxxb.setKhlx(iSysDictService.queryTableDictTextByKey("SYS_DICT_ITEM","item_value","item_text",khzhglxxb.getKhlx()));
                }
                rateKhzhglxxbService.saveBatch(Rate_khjbxxbList);
                return Result.ok("文件导入成功！共[ " + Rate_khjbxxbList.size() + " ]条数据！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败！" + e.getMessage());
            }
        }
        return Result.ok("文件导入失败！");
    }
}

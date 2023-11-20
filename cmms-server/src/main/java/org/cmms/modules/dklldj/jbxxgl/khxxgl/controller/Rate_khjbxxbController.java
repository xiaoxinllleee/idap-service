package org.cmms.modules.dklldj.jbxxgl.khxxgl.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.entity.Rate_khjbxxb;
import org.cmms.modules.dklldj.jbxxgl.khxxgl.service.IRate_khjbxxbService;
import org.cmms.modules.dklldj.jbxxgl.khzyjl.entity.RateKhjbxxbLs;
import org.cmms.modules.dklldj.jbxxgl.khzyjl.service.IRateKhjbxxbLsService;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.service.IRateTszxlldjbService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.*;

/**
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date: 2020-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户基本信息管理层")
@RestController
@RequestMapping("/dklldj.jbxxgl.khxxgl/rate_khjbxxb")
public class Rate_khjbxxbController extends JeecgController<Rate_khjbxxb, IRate_khjbxxbService> {
    @Autowired
    private IRate_khjbxxbService rate_khjbxxbService;
    @Autowired
    private IRateKhjbxxbLsService rateKhjbxxbLsService;
    @Autowired
    private IRateTszxlldjbService rateTszxlldjbService;
    @Autowired
    private ISysDictService iSysDictService;
    @Value(value = "${common.path.upload}")
    private String uploadPath;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param rate_khjbxxb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户信息管理-分页列表查询")
    @ApiOperation(value = "客户信息管理-分页列表查询", notes = "客户信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Rate_khjbxxb rate_khjbxxb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Rate_khjbxxb>> result = new Result<IPage<Rate_khjbxxb>>();
        QueryWrapper<Rate_khjbxxb> queryWrapper = QueryGenerator.initQueryWrapper(rate_khjbxxb, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IRate_khjbxxbService.class,rate_khjbxxbService, pageNo, pageSize, queryWrapper, "zjhm");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 客户信息管理 / 添加
     *
     * @param khjbxx
     * @return
     */
    @AutoLog(value = "客户信息管理-添加")
    @ApiOperation(value = "客户信息管理-添加", notes = "客户信息管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Rate_khjbxxb khjbxx) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        try {
            khjbxx.setScbz(0);
            khjbxx.setLrczy(sysUser.getUsername());
            khjbxx.setLrbz(1);
            khjbxx.setLrsj(new Date());
            QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", khjbxx.getZjhm());
            Rate_khjbxxb check = rate_khjbxxbService.getOne(queryWrapper);
            if (check != null) {
                String zzmc = iSysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", check.getZzbz());
                return Result.error("此证件号码已经在机构【" + zzmc + "】存在，请联系对方网点进行客户转移！");
            }
            rate_khjbxxbService.save(khjbxx);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("系统错误，请联系管理员处理！", throwable.getMessage());
            return Result.error("添加失败！"+throwable.getMessage());
        }
        return Result.ok("添加成功！");
    }

    /**
     * 客户信息管理 / 编辑、转移
     *
     * @param rate_khjbxxb
     * @return
     */
    @AutoLog(value = "客户信息管理-编辑、转移")
    @ApiOperation(value = "客户信息管理-编辑、转移", notes = "客户信息管理-编辑、转移")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Rate_khjbxxb rate_khjbxxb) {
        try {
            QueryWrapper<Rate_khjbxxb> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zjhm", rate_khjbxxb.getZjhm());
            List<Rate_khjbxxb> list = rate_khjbxxbService.list(queryWrapper);
            //转移保存
            if (!list.get(0).getZzbz().equals(rate_khjbxxb.getZzbz())) {
                RateKhjbxxbLs ls = new RateKhjbxxbLs();
                ls.setFrdb(rate_khjbxxb.getFrdb());
                ls.setKhlx(rate_khjbxxb.getKhlx());
                ls.setKhmc(rate_khjbxxb.getKhmc());
                ls.setXgczy(getLoginUser().getUsername());
                ls.setXgsj(new Date());
                ls.setXzzbz(rate_khjbxxb.getZzbz());
                ls.setYzzbz(list.get(0).getZzbz());
                ls.setZjhm(rate_khjbxxb.getZjhm());
                rateKhjbxxbLsService.save(ls);
            }
            rate_khjbxxb.setLrbz(2);
            rate_khjbxxb.setLrczy(getLoginUser().getUsername());
            rate_khjbxxb.setLrsj(new Timestamp(System.currentTimeMillis()));
            QueryWrapper<Rate_khjbxxb> updateWrapper = new QueryWrapper<>();
            updateWrapper.eq("zjhm", rate_khjbxxb.getZjhm());
            rate_khjbxxbService.update(rate_khjbxxb, updateWrapper);
            return Result.ok("编辑成功!");
        } catch (Throwable throwable) {
            log.error("贷款利率定价 / 客户信息管理 / 编辑失败！"+throwable.getMessage());
            return Result.error("编辑失败！"+throwable.getMessage());
        }
    }

    /**
     * 客户信息管理 / 删除
     *
     * @param zjhm
     * @return
     */
    @AutoLog(value = "客户信息管理-删除")
    @ApiOperation(value = "客户信息管理-删除", notes = "客户信息管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "zjhm", required = true) String zjhm) {
        try {
            UpdateWrapper<Rate_khjbxxb> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("zjhm", zjhm);
            rate_khjbxxbService.remove(updateWrapper);
            return Result.ok("删除成功!");
        } catch (Exception exception) {
            log.error("贷款利率定价 / 客户信息管理 / 删除失败!"+exception.getMessage());
            return Result.error("删除失败!");
        }
    }

    /**
     * 贷款利率定价-客户基本信息-提取
     *
     * @return
     */
    @RequestMapping(value = "/extract", method = RequestMethod.PUT)
    public Result<?> extract() {
        // Result<List<Rate_khjbxxb>> result =new Result<>();
        //String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
        Result result = new Result<>();
        // `浏阳`调用ETL工具类执行ETL调度
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.rate.proc_rate_khjbxx_tj");
            // count_rate_khjbxx_tj
            boolean completionSignal = EtlUtil.callEtl("rate_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                rate_khjbxxbService.extract();
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 导出excel
     *
     * @param request
     * @param rate_khjbxxb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Rate_khjbxxb rate_khjbxxb) {
        return super.exportXls(request, rate_khjbxxb, Rate_khjbxxb.class, "客户信息管理");
    }

    /**
     * 导出Excel模板
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "客户基本信息导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, Rate_khjbxxb.class);
        ExportParams exportParams = new ExportParams("客户基本信息导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<Rate_khjbxxb>());
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
                List<Rate_khjbxxb> Rate_khjbxxbList = ExcelImportUtil.importExcel(file.getInputStream(), Rate_khjbxxb.class, importParams);
                for (Rate_khjbxxb rate_khjbxxb : Rate_khjbxxbList) {
                    if (rate_khjbxxb.getZjhm().contains(".")) {
                        rate_khjbxxb.setZjhm(rate_khjbxxb.getZjhm().split("\\.")[0]);
                    }
                    rate_khjbxxb.setLrbz(0);
                    LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
                    rate_khjbxxb.setLrczy(sysUser.getUsername());
                    rate_khjbxxb.setLrsj(new Date());
                    //rate_khjbxxb.setKhlx(iSysDictService.queryTableDictTextByKey("SYS_DICT_ITEM","item_value","item_text",rate_khjbxxb.getKhlx()));
                }
                rate_khjbxxbService.saveBatch(Rate_khjbxxbList);
                return Result.ok("文件导入成功！共[ " + Rate_khjbxxbList.size() + " ]条数据！");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败！" + e.getMessage());
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 根据证件号码获取客户信息
     * 用处暂时不明
     *
     * @param zjhm
     * @return
     */
    @RequestMapping("/getNameByZjhm")
    public Result<?> getNameByZjhm(@RequestParam(name = "zjhm", required = true) String zjhm) {
        return Result.ok(rate_khjbxxbService.getNameByZjhm(zjhm));
    }

    /**
     * 特殊利率定价计算 / 添加
     *
     * @param zjhm
     * @param zzbz
     * @return
     */
    @AutoLog(value = "特殊利率定价计算-删除")
    @ApiOperation(value = "特殊利率定价计算-删除", notes = "特殊利率定价计算-删除")
    @RequestMapping("/getNameByZjhmandZzbz")
    public Result<?> getNameByZjhm(@RequestParam(name = "zjhm", required = true) String zjhm,
                                   @RequestParam(name = "zzbz", required = true) String zzbz) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", zjhm);
        List<Rate_khjbxxb> list = service.list(queryWrapper);
        Rate_khjbxxb rate_khjbxxb = null;
        if (CollUtil.isNotEmpty(list)) {
            rate_khjbxxb = list.get(0);
            if (zzbz.equals(rate_khjbxxb.getZzbz())) {
                //判断特殊利率定价是不是有未确认的
                boolean notSureByZjhm = rateTszxlldjbService.isNotSureByZjhm(zjhm, DateUtil.beginOfYear(new Date()), "0");
                if (notSureByZjhm) {
                    return Result.error("同一年度内该客户只允许有一条未确认的定价信息！");
                }
                return Result.ok(rate_khjbxxb);
            }
            return Result.error("该客户不在本支行下");
        }
        return Result.error("客户信息不存在,请在客户信息管理中进行维护！");
    }
}

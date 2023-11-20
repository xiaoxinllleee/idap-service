package org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.controller;

import java.io.*;
import java.net.URLDecoder;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.entity.Dkhxdjb;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.service.IDkhxdjbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.verify.DkhxdjbImpVerify;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.vo.DkhxdjbImportVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;

import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款核销登记薄
 * @Author: Penghr
 * @Date: 2022-12-17
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款核销登记薄")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/dkhxdjb")
public class DkhxdjbController extends JeecgController<Dkhxdjb, IDkhxdjbService> {
    @Autowired
    private IDkhxdjbService dkhxdjbService;
    @Autowired
    private DkhxdjbImpVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param dkhxdjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款核销登记薄-分页列表查询")
    @ApiOperation(value = "贷款核销登记薄-分页列表查询", notes = "贷款核销登记薄-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dkhxdjb dkhxdjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dkhxdjb> queryWrapper = QueryGenerator.initQueryWrapper(dkhxdjb, req.getParameterMap());
        IPage<Dkhxdjb> pageList = PageUtil.toPage(IDkhxdjbService.class, dkhxdjbService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dkhxdjb
     * @return
     */
//    @AutoLog(value = "贷款核销登记薄-添加")
//    @ApiOperation(value = "贷款核销登记薄-添加", notes = "贷款核销登记薄-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Dkhxdjb dkhxdjb) {
//        dkhxdjbService.save(dkhxdjb);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param dkhxdjb
     * @return
     */
    @AutoLog(value = "贷款核销登记薄-编辑")
    @ApiOperation(value = "贷款核销登记薄-编辑", notes = "贷款核销登记薄-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dkhxdjb dkhxdjb) {
        UpdateWrapper<Dkhxdjb> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dkhxdjb.getDataDate());
        wrapper.eq("jgbm", dkhxdjb.getJgbm());
        wrapper.eq("ident_no", dkhxdjb.getIdentNo());
        dkhxdjb.setDataDate(null);
        dkhxdjb.setJgbm(null);
        dkhxdjb.setIdentNo(null);
        dkhxdjb.setLrbz(2);
        dkhxdjb.setLrr(getLoginUser().getUsername());
        dkhxdjb.setLrsj(new Date());
        dkhxdjbService.update(dkhxdjb, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过dataDate、jgbm、identNo删除
     *
     * @param date 数据日期
     * @param jgbm 开户机构编码
     * @param identNo 证件号码
     * @return
     */
    @AutoLog(value = "贷款核销登记薄-通过dataDate、jgbm、identNo删除")
    @ApiOperation(value = "贷款核销登记薄-通过dataDate、jgbm、identNo删除", notes = "贷款核销登记薄-通过dataDate、jgbm、identNo删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
                            @RequestParam(name = "jgbm", required = true) String jgbm,
                            @RequestParam(name = "identNo", required = true) String identNo) {
        Date dataDate = DateUtil.string2Date(date,"yyyy-MM-dd");
        UpdateWrapper<Dkhxdjb> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", dataDate);
        wrapper.eq("jgbm", jgbm);
        wrapper.eq("ident_no", identNo);
        dkhxdjbService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "贷款核销登记薄-批量删除")
//    @ApiOperation(value = "贷款核销登记薄-批量删除", notes = "贷款核销登记薄-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.dkhxdjbService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款核销登记薄-通过id查询")
    @ApiOperation(value = "贷款核销登记薄-通过id查询", notes = "贷款核销登记薄-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dkhxdjb dkhxdjb = dkhxdjbService.getById(id);
        return Result.ok(dkhxdjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dkhxdjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dkhxdjb dkhxdjb) {
        //return super.exportXls(request, dkhxdjb, Dkhxdjb.class, "贷款核销登记薄");
        QueryWrapper<Dkhxdjb> queryWrapper = new QueryWrapper<>();
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                Dkhxdjb form = JSON.parseObject(deString, Dkhxdjb.class);
                queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log.error("导出错误！贷款核销登记薄："+throwable.getMessage());
        }
        queryWrapper.orderByAsc("data_date","jgbm");
        List<Dkhxdjb> pageList = dkhxdjbService.list(queryWrapper);
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款核销登记薄");
        mv.addObject(NormalExcelConstants.CLASS, Dkhxdjb.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行贷款核销登记薄", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }


    /**
     * 导出模板Excel
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款核销登记薄导入模板");
        mv.addObject(NormalExcelConstants.CLASS, DkhxdjbImportVO.class);
        ExportParams exportParams = new ExportParams("贷款核销登记薄导入模板", "贷款核销登记薄");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return mv;
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"),"yyyy-MM-dd");
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        JSONObject obj = new JSONObject();
        for (String filePath : filePathList) {
            String baseFilePath = uploadpath + File.separator + filePath;
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            if (importVerify != null) {
                params.setVerifyHanlder(importVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                boolean checkResult = ExcelImportCheckUtil.check(fis, DkhxdjbImportVO.class, params, 1.0);
                if (!checkResult) {
                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
                }
                ExcelImportResult<DkhxdjbImportVO> importResult = ExcelImportUtil.importExcelVerify(file, DkhxdjbImportVO.class, params);
                List<DkhxdjbImportVO> list = importResult.getList();
                List<Dkhxdjb> dkhxdjbList = new ArrayList<>();
                for (DkhxdjbImportVO dkhxdjbImportVO : list) {
                    Dkhxdjb dkhxdjb = new Dkhxdjb();
                    BeanUtil.copyPropertiesIgnoreNull(dkhxdjbImportVO, dkhxdjb);
                    dkhxdjb.setDataDate(fiscalDate);
                    dkhxdjb.setLrbz(0);
                    dkhxdjb.setLrr(getLoginUser().getUsername());
                    dkhxdjb.setLrsj(new Date());
                    dkhxdjbList.add(dkhxdjb);
                }
                if (!dkhxdjbList.isEmpty()) {
                    //保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<Dkhxdjb> wrapper = new UpdateWrapper<>();
                    wrapper.eq("data_date",fiscalDate);
                    dkhxdjbService.remove(wrapper);
                    // 保存最新当月数据
                    dkhxdjbService.saveBatch(dkhxdjbList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

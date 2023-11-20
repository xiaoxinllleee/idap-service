package org.cmms.modules.report.sgtzgl.ywzkbckrmb.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.report.sgtzgl.ywzkbckrmb.entity.SgtzYwzkbCkrmb;
import org.cmms.modules.report.sgtzgl.ywzkbckrmb.entity.YwzkbCkrmbVO;
import org.cmms.modules.report.sgtzgl.ywzkbckrmb.service.ISgtzYwzkbCkrmbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.sgtzgl.ywzkbckrmb.vo.YwzkbCkrmbImpVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 浏阳农商行_业务状况表月报_存款人民币
 * @Author: jeecg-boot
 * @Date: 2023-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "浏阳农商行_业务状况表月报_存款人民币")
@RestController
@RequestMapping("/report/sgtzgl/YwzkbCkrmb")
public class SgtzYwzkbCkrmbController extends JeecgController<SgtzYwzkbCkrmb, ISgtzYwzkbCkrmbService> {
    @Autowired
    private ISgtzYwzkbCkrmbService sgtzYwzkbCkrmbService;
    @Autowired
    private YwzkbCkrmbImpVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param sgtzYwzkbCkrmb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-分页列表查询")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-分页列表查询", notes = "浏阳农商行_业务状况表月报_存款人民币-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(SgtzYwzkbCkrmb sgtzYwzkbCkrmb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<SgtzYwzkbCkrmb> queryWrapper = QueryGenerator.initQueryWrapper(sgtzYwzkbCkrmb, req.getParameterMap());
        IPage pageList = PageUtil.toPage(ISgtzYwzkbCkrmbService.class,sgtzYwzkbCkrmbService,pageNo,pageSize,queryWrapper,"fiscal_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param sgtzYwzkbCkrmb
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-添加")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-添加", notes = "浏阳农商行_业务状况表月报_存款人民币-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SgtzYwzkbCkrmb sgtzYwzkbCkrmb) {
        sgtzYwzkbCkrmbService.save(sgtzYwzkbCkrmb);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param sgtzYwzkbCkrmb
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-编辑")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-编辑", notes = "浏阳农商行_业务状况表月报_存款人民币-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SgtzYwzkbCkrmb sgtzYwzkbCkrmb) {
        sgtzYwzkbCkrmbService.updateById(sgtzYwzkbCkrmb);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-通过id删除")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-通过id删除", notes = "浏阳农商行_业务状况表月报_存款人民币-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sgtzYwzkbCkrmbService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-批量删除")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-批量删除", notes = "浏阳农商行_业务状况表月报_存款人民币-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.sgtzYwzkbCkrmbService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "浏阳农商行_业务状况表月报_存款人民币-通过id查询")
    @ApiOperation(value = "浏阳农商行_业务状况表月报_存款人民币-通过id查询", notes = "浏阳农商行_业务状况表月报_存款人民币-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        SgtzYwzkbCkrmb sgtzYwzkbCkrmb = sgtzYwzkbCkrmbService.getById(id);
        return Result.ok(sgtzYwzkbCkrmb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sgtzYwzkbCkrmb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgtzYwzkbCkrmb sgtzYwzkbCkrmb) {
        return super.exportXls(request, sgtzYwzkbCkrmb, SgtzYwzkbCkrmb.class, "存款进度表");
    }

    /**
     * 数据导入模板下载
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
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "浏阳农商行_业务状况表月报_存款人民币_导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, YwzkbCkrmbVO.class);
        ExportParams exportParams = new ExportParams("存款进度表", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        //return super.importExcel(request, response, SgtzYwzkbCkrmb.class);
        String branchNo = "";
        String fiscalDate = request.getParameter("fiscalDate");
        log.info("当前导入数据日期："+fiscalDate);
        JSONObject obj = new JSONObject();
        String filePaths = jsonObject.getString("filePaths");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
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
                boolean checkResult = ExcelImportCheckUtil.check(fis,YwzkbCkrmbVO.class,params,1.0);
                ExcelImportResult<YwzkbCkrmbVO> importResult = ExcelImportUtil.importExcelVerify(file,YwzkbCkrmbVO.class,params);
                List<SgtzYwzkbCkrmb> records = new ArrayList<>();
                List<YwzkbCkrmbVO> list = importResult.getList();
                for (YwzkbCkrmbVO ywzkbCkrmbVO : list) {
                    SgtzYwzkbCkrmb record = new SgtzYwzkbCkrmb();
                    BeanUtil.copyPropertiesIgnoreNull(ywzkbCkrmbVO, record);
                    record.setFiscalDate(fiscalDate);

                    String zhmc = record.getBranchName() == null ? record.getBranchName1().trim() : record.getBranchName().trim();
                    log.info("支行名称 =》 "+zhmc);
                    branchNo = zhmc.substring(zhmc.indexOf("[") + 1, zhmc.lastIndexOf("]"));
                    int jgbmLength = branchNo.length();
                    log.info("字符长度: "+jgbmLength);
                    if (jgbmLength > 5) {
                        branchNo = branchNo.substring(3);
                        if ("02000".equals(branchNo)) {
                            branchNo = branchNo.substring(0,branchNo.length()-2);
                        }
                    }
                    log.info("截取处理后机构编码 =》 "+branchNo);
                    record.setBranchNo(branchNo);

                    records.add(record);
                }
                if (!records.isEmpty()) {
                    // 保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<SgtzYwzkbCkrmb> deleteWrapper = new UpdateWrapper<>();
                    deleteWrapper.eq("fiscal_date",records.get(0).getFiscalDate());
                    sgtzYwzkbCkrmbService.remove(deleteWrapper);
                    sgtzYwzkbCkrmbService.saveBatch(records);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入成功！");
    }

    /*public static void main(String[] args) {
        String branchNo = "";
        String[] records = {
                //合  计
                "[80102000]湖南浏阳农村商业银行股份有限公司",
                //结  算
                "[02000]湖南浏阳农村商业银行股份有限公司",
                //营业部
                "[02001]湖南浏阳农村商业银行股份有限公司营业部",
                //城  关
                "[02003]湖南浏阳农村商业银行股份有限公司城关支行"
        };
        for (String record : records) {
            String zhmc = record;
            log.info("支行名称 =》 "+zhmc);
            branchNo = zhmc.substring(zhmc.indexOf("[") + 1, zhmc.lastIndexOf("]"));
            log.info("截取处理前机构编码 =》 "+branchNo);
            int jgbmLength = branchNo.length();
            log.info("字符长度: "+jgbmLength);
            if (jgbmLength > 5) {
                 //&& !"801".equals(branchNo.substring(0,3))
                branchNo = branchNo.substring(3);
                if ("02000".equals(branchNo)) {
                    branchNo = branchNo.substring(0,branchNo.length()-2);
                }
            }
            log.info("截取处理后机构编码 =》 "+branchNo);
            log.info("==============================");
        }
    }*/

}

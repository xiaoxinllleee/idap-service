package org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;
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
import org.cmms.common.util.DateUtil;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.entity.Qbqxdjb;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.service.IDkQbqxdjbService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.verify.QbqxdjbImportVerify;
import org.cmms.modules.ywgl.yxbldkgl.dkqbqxdjb.vo.QbqxdjbImportVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷款欠本欠息登记薄
 * @Author: penghr
 * @Date: 2022-08-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款欠本欠息登记薄")
@RestController
@RequestMapping("/yxbldkgl/dkqbqxdjb")
public class DkQbqxdjbController extends JeecgController<Qbqxdjb, IDkQbqxdjbService> {

    @Autowired
    private IDkQbqxdjbService iDkQbqxdjbService;
    @Autowired
    private QbqxdjbImportVerify importVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param qbqxdjb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款欠本欠息登记薄-分页列表查询")
    @ApiOperation(value = "贷款欠本欠息登记薄-分页列表查询", notes = "贷款欠本欠息登记薄-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Qbqxdjb qbqxdjb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Qbqxdjb> queryWrapper = QueryGenerator.initQueryWrapper(qbqxdjb, req.getParameterMap());
        IPage<Qbqxdjb> pageList = PageUtil.toPage(IDkQbqxdjbService.class,iDkQbqxdjbService,pageNo,pageSize,queryWrapper,"data_date","acct_no","qs");
        return Result.ok(pageList);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款欠本欠息登记薄-通过id查询")
    @ApiOperation(value = "贷款欠本欠息登记薄-通过id查询", notes = "贷款欠本欠息登记薄-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Qbqxdjb qbqxdjb = iDkQbqxdjbService.getById(id);
        return Result.ok(qbqxdjb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param qbqxdjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qbqxdjb qbqxdjb) {
        return super.exportXls(request, qbqxdjb, Qbqxdjb.class, "贷款欠本欠息登记薄");
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
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷款欠本欠息登记薄导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, QbqxdjbImportVO.class);
        ExportParams exportParams = new ExportParams("贷款欠本欠息登记薄导入模板", "欠本欠息登记薄工作表");
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
        String filePaths = jsonObject.getString("filePaths");
        String fiscal_date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
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
                boolean checkResult = ExcelImportCheckUtil.check(fis, QbqxdjbImportVO.class, params, 1.0);
                if (!checkResult) {
                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
                }
                ExcelImportResult<QbqxdjbImportVO> importResult = ExcelImportUtil.importExcelVerify(file, QbqxdjbImportVO.class, params);
                List<QbqxdjbImportVO> list = importResult.getList();
                List<Qbqxdjb> qbqxdjbList = new ArrayList<>();
                for (QbqxdjbImportVO qbqxdjbImportVO : list) {
                    Qbqxdjb qbqxdjb = new Qbqxdjb();
                    BeanUtil.copyPropertiesIgnoreNull(qbqxdjbImportVO, qbqxdjb);
                    // 日期处理，导入数据日期默认为每月21号
                    String dataDate = sdf.format(qbqxdjb.getDataDate())+"-21";
                    Date datadate = DateUtil.string2Date(dataDate, "yyyy-MM-dd");
                    qbqxdjb.setDataDate(datadate);
                    qbqxdjbList.add(qbqxdjb);
                }
                if (!qbqxdjbList.isEmpty()) {
                    // 保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<Qbqxdjb> deleteWrapper = new UpdateWrapper<>();
                    deleteWrapper.eq("data_date",qbqxdjbList.get(0).getDataDate());
                    iDkQbqxdjbService.remove(deleteWrapper);
                    // 保存最新当月数据
                    iDkQbqxdjbService.saveBatch(qbqxdjbList);
                    // 当月数据日期
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    fiscal_date = sdf.format(qbqxdjbList.get(0).getDataDate());
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                log.info("贷款欠本欠息登记薄-导入-数据日期: "+fiscal_date);
                // 导入欠本欠息登记薄成功以后，自动更新当月各个客户的欠息次数，以证件号码为准统计条目数
                if ("true".equals(sfdsjpt)) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("fiscal_date", fiscal_date);
                    param.put("etl_task", "kiss.domain.application.cdkyw.proc_yxbldkgl_qbqxdjb");
                    boolean flag = EtlUtil.callEtl("cdkyw_common_init", param, 15);
                    log.info("贷款欠本欠息登记薄-欠息次数处理逻辑-是否成功？-"+flag);
                } else {
                    log.info("贷款欠本欠息登记薄-导入-非大数据应用平台操作-未添加欠息次数处理逻辑");
                }

                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(),e);
                return Result.error("文件导入失败:"+e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

    /**
     * 数据抽取
     *
     * @return jsonObject
     */
//    @AutoLog(value = "贷款欠本欠息登记薄-数据抽取")
//    @ApiOperation(value = "贷款欠本欠息登记薄-数据抽取", notes = "贷款欠本欠息登记薄-数据抽取")
//    @RequestMapping(value = "/extraction")
//    public Result<?> Extraction(@RequestBody JSONObject jsonObject) {
//        try {
//            String tjyf = jsonObject.getString("tjyf");
//            if ("true".equals(sfdsjpt)) {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("tjyf",tjyf.replace("-",""));
//                params.put("etl_task", "kiss.domain.application.cdkyw.proc_yxbldkgl_qbqxxx");
//                boolean flag = EtlUtil.callEtl("cdkyw_common_init", params, 15);
//                log.info("贷款欠本欠息登记薄-数据抽取-"+flag);
//                return Result.ok();
//            } else {
//                log.info("贷款欠本欠息登记薄-数据抽取-未做Oracle数据提取");
//                return Result.ok();
//            }
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//            return Result.error(throwable.getMessage());
//        }
//    }

}

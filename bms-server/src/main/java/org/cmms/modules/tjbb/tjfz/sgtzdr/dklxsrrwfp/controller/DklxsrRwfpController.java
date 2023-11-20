package org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.controller;

import java.io.*;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
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
import org.cmms.modules.tjbb.tjfz.sgtzdr.dkhxdjb.vo.DkhxdjbImportVO;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity.DklxsrRwfp;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity.DklxsrRwfpVo;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.service.IDklxsrRwfpService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.verify.DklxsrRwfpImportVerify;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 手工台账：贷款利息收入任务分配
 * @Author: jeecg-boot
 * @Date: 2023-01-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "手工台账：贷款利息收入任务分配")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/dklxsrrwfp/dklxsrRwfp")
public class DklxsrRwfpController extends JeecgController<DklxsrRwfp, IDklxsrRwfpService> {
    @Autowired
    private IDklxsrRwfpService dklxsrRwfpService;
    @Autowired
    private DklxsrRwfpImportVerify dklxsrRwfpImportVerify;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param dklxsrRwfp
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-分页列表查询")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-分页列表查询", notes = "手工台账：贷款利息收入任务分配-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(DklxsrRwfp dklxsrRwfp,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<DklxsrRwfp> queryWrapper = QueryGenerator.initQueryWrapper(dklxsrRwfp, req.getParameterMap());
        Page<DklxsrRwfp> page = new Page<DklxsrRwfp>(pageNo, pageSize);
        IPage<DklxsrRwfp> pageList = PageUtil.toPage(IDklxsrRwfpService.class, dklxsrRwfpService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dklxsrRwfp
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-添加")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-添加", notes = "手工台账：贷款利息收入任务分配-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody DklxsrRwfp dklxsrRwfp) {
        dklxsrRwfpService.save(dklxsrRwfp);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dklxsrRwfp
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-编辑")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-编辑", notes = "手工台账：贷款利息收入任务分配-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody DklxsrRwfp dklxsrRwfp) {
        QueryWrapper<DklxsrRwfp> wrapper = new QueryWrapper<>();
        wrapper.eq("data_date", dklxsrRwfp.getDataDate()).eq("jgbm", dklxsrRwfp.getJgbm());
        dklxsrRwfpService.update(dklxsrRwfp, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-通过id删除")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-通过id删除", notes = "手工台账：贷款利息收入任务分配-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dklxsrRwfpService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-批量删除")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-批量删除", notes = "手工台账：贷款利息收入任务分配-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dklxsrRwfpService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手工台账：贷款利息收入任务分配-通过id查询")
    @ApiOperation(value = "手工台账：贷款利息收入任务分配-通过id查询", notes = "手工台账：贷款利息收入任务分配-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        DklxsrRwfp dklxsrRwfp = dklxsrRwfpService.getById(id);
        return Result.ok(dklxsrRwfp);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dklxsrRwfp
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, DklxsrRwfp dklxsrRwfp) {
        return super.exportXls(request, dklxsrRwfp, DklxsrRwfp.class, "手工台账：贷款利息收入任务分配");
    }

    /**
     * 导入模板Excel
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "贷款利息收入任务分配导入模板");
        mv.addObject(NormalExcelConstants.CLASS, DklxsrRwfpVo.class);
        ExportParams exportParams = new ExportParams("贷款利息收入任务分配导入模板", "贷款利息收入任务分配");
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
        Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"), "yyyy-MM-dd");
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
            if (dklxsrRwfpImportVerify != null) {
                params.setVerifyHanlder(dklxsrRwfpImportVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream(file);
                boolean checkResult = ExcelImportCheckUtil.check(fis, DklxsrRwfpVo.class, params, 1.0);
                ExcelImportResult<DklxsrRwfpVo> importResult = ExcelImportUtil.importExcelVerify(file, DklxsrRwfpVo.class, params);
                List<DklxsrRwfpVo> list = importResult.getList();
                List<DklxsrRwfp> dkhxdjbList = new ArrayList<>();
                for (DklxsrRwfpVo dklxsrRwfpVo : list) {
                    DklxsrRwfp dklxsrRwfp = new DklxsrRwfp();
                    BeanUtil.copyPropertiesIgnoreNull(dklxsrRwfpVo, dklxsrRwfp);
                    dklxsrRwfp.setDataDate(fiscalDate);
                    dklxsrRwfp.setLrbz(0);
                    dklxsrRwfp.setLrr(getLoginUser().getUsername());
                    dklxsrRwfp.setLrsj(new Date());
                    dkhxdjbList.add(dklxsrRwfp);
                }
                if (!dkhxdjbList.isEmpty()) {
                    //保存以前删除当月数据，以防止重复导入
                    UpdateWrapper<DklxsrRwfp> wrapper = new UpdateWrapper<>();
                    wrapper.eq("data_date", fiscalDate);
                    dklxsrRwfpService.remove(wrapper);
                    // 保存最新当月数据
                    dklxsrRwfpService.saveBatch(dkhxdjbList);
                }
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();

                //导入贷款利息收入任务分配成功以后，根据导入的机构名称自动更新机构编码
                if ("true".equals(sfdsjpt)) {
                    HashMap<String, String> param = new HashMap<>();
                    param.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_sgtz_dklxsrrwfp");
                    boolean flag = EtlUtil.callEtl("tjbb_common_init", param, 15);
                    log.info("贷款利息收入任务分配-机构编码处理逻辑-是否成功？-"+flag);
                } else {
                    log.info("贷款利息收入任务分配-导入-非大数据应用平台操作-未添加处理逻辑");
                }

                return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

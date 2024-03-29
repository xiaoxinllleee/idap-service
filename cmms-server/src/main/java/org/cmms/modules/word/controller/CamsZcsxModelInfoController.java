package org.cmms.modules.word.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.grkhgl.entity.CamsZcsxModelInfo;
import org.cmms.modules.khgl.grkhgl.service.IVKhglGrkhglService;
import org.cmms.modules.word.service.ICamsZcsxModelInfoService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 模型详情
 * @Author: jeecg-boot
 * @Date: 2020-08-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "模型详情")
@RestController
@RequestMapping("/grkh/camsZcsxModelInfo")
public class CamsZcsxModelInfoController extends JeecgController<CamsZcsxModelInfo, ICamsZcsxModelInfoService> {

    @Autowired
    IVKhglGrkhglService ivKhglGrkhglService;

    /**
     * 分页列表查询
     *
     * @param camsZcsxModelInfo
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "模型详情-分页列表查询")
    @ApiOperation(value = "模型详情-分页列表查询", notes = "模型详情-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsZcsxModelInfo camsZcsxModelInfo,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CamsZcsxModelInfo> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxModelInfo, req.getParameterMap());
        Page<CamsZcsxModelInfo> page = new Page<CamsZcsxModelInfo>(pageNo, pageSize);
        IPage<CamsZcsxModelInfo> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param camsZcsxModelInfo
     * @return
     */
    @AutoLog(value = "模型详情-添加")
    @ApiOperation(value = "模型详情-添加", notes = "模型详情-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsZcsxModelInfo camsZcsxModelInfo) {
        service.save(camsZcsxModelInfo);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param camsZcsxModelInfo
     * @return
     */
    @AutoLog(value = "模型详情-编辑")
    @ApiOperation(value = "模型详情-编辑", notes = "模型详情-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsZcsxModelInfo camsZcsxModelInfo) {
        service.updateById(camsZcsxModelInfo);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "模型详情-通过id删除")
    @ApiOperation(value = "模型详情-通过id删除", notes = "模型详情-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        service.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "模型详情-批量删除")
    @ApiOperation(value = "模型详情-批量删除", notes = "模型详情-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "模型详情-通过id查询")
    @ApiOperation(value = "模型详情-通过id查询", notes = "模型详情-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsZcsxModelInfo camsZcsxModelInfo = service.getById(id);
        return Result.ok(camsZcsxModelInfo);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsZcsxModelInfo
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsZcsxModelInfo camsZcsxModelInfo) {
        return super.exportXls(request, camsZcsxModelInfo, CamsZcsxModelInfo.class, "模型详情");
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
        return super.importExcel(request, response, CamsZcsxModelInfo.class);
    }


    @RequestMapping("/getModelInfo")
    public Result<?> getModelInfo(@RequestParam(name = "zjhm", required = true) String zjhm,
                                  @RequestParam(name = "hhbm") String hhbm) {

        if (StringUtils.isBlank(zjhm) || StringUtils.isBlank(hhbm))
            return Result.error("证件号码或者户名编码为空");

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ZJHM", zjhm);
        queryWrapper.eq("HHBM", hhbm);
        CamsZcsxModelInfo one = service.getOne(queryWrapper);

        if (one == null) {
            ivKhglGrkhglService.calculateModel(hhbm, zjhm);
        }else {
        	return Result.ok(one);
		}

        CamsZcsxModelInfo one1 = service.getOne(queryWrapper);
        if (one1 != null)
            return Result.ok(one1);

        return Result.error("未知错误");

    }
}

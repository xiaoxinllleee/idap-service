package org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.controller;

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
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity.DklxsrRwfp;
import org.cmms.modules.tjbb.tjfz.sgtzdr.dklxsrrwfp.entity.DklxsrRwfpVo;
import org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.entity.XykWjflBltj;
import org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.entity.XykWjflBltjVo;
import org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.service.IXykWjflBltjService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.xykwjflbltj.verify.XykWjflBltjImportVerify;
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
 * @Description: 手工台账：信用卡五级分类及不良统计
 * @Author: jeecg-boot
 * @Date: 2023-01-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "手工台账：信用卡五级分类及不良统计")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/xykwjflbltj/xykWjflBltj")
public class XykWjflBltjController extends JeecgController<XykWjflBltj, IXykWjflBltjService> {
    @Autowired
    private IXykWjflBltjService xykWjflBltjService;
    @Autowired
    private XykWjflBltjImportVerify xykWjflBltjImportVerify;

    /**
     * 分页列表查询
     *
     * @param xykWjflBltj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-分页列表查询")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-分页列表查询", notes = "手工台账：信用卡五级分类及不良统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(XykWjflBltj xykWjflBltj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<XykWjflBltj> queryWrapper = QueryGenerator.initQueryWrapper(xykWjflBltj, req.getParameterMap());
        Page<XykWjflBltj> page = new Page<XykWjflBltj>(pageNo, pageSize);
        IPage<XykWjflBltj> pageList = PageUtil.toPage(IXykWjflBltjService.class, xykWjflBltjService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param xykWjflBltj
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-添加")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-添加", notes = "手工台账：信用卡五级分类及不良统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody XykWjflBltj xykWjflBltj) {
        xykWjflBltjService.save(xykWjflBltj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param xykWjflBltj
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-编辑")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-编辑", notes = "手工台账：信用卡五级分类及不良统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody XykWjflBltj xykWjflBltj) {
        QueryWrapper<XykWjflBltj> wrapper = new QueryWrapper<>();
        wrapper.eq("data_date", xykWjflBltj.getDataDate()).eq("jgbm", xykWjflBltj.getJgbm());
        xykWjflBltjService.update(xykWjflBltj, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-通过id删除")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-通过id删除", notes = "手工台账：信用卡五级分类及不良统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        xykWjflBltjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-批量删除")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-批量删除", notes = "手工台账：信用卡五级分类及不良统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.xykWjflBltjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "手工台账：信用卡五级分类及不良统计-通过id查询")
    @ApiOperation(value = "手工台账：信用卡五级分类及不良统计-通过id查询", notes = "手工台账：信用卡五级分类及不良统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        XykWjflBltj xykWjflBltj = xykWjflBltjService.getById(id);
        return Result.ok(xykWjflBltj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param xykWjflBltj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, XykWjflBltj xykWjflBltj) {
        return super.exportXls(request, xykWjflBltj, XykWjflBltj.class, "信用卡五级分类及不良统计");
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
        mv.addObject(NormalExcelConstants.FILE_NAME, "信用卡五级分类及不良统计导入模板");
        mv.addObject(NormalExcelConstants.CLASS, XykWjflBltjVo.class);
        ExportParams exportParams = new ExportParams("信用卡五级分类及不良统计导入模板", "信用卡五级分类及不良统计");
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
			if (xykWjflBltjImportVerify != null) {
				params.setVerifyHanlder(xykWjflBltjImportVerify);
			}
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				boolean checkResult = ExcelImportCheckUtil.check(fis, XykWjflBltjVo.class, params, 1.0);
				ExcelImportResult<XykWjflBltjVo> importResult = ExcelImportUtil.importExcelVerify(file, XykWjflBltjVo.class, params);
				List<XykWjflBltjVo> list = importResult.getList();
				List<XykWjflBltj> dkhxdjbList = new ArrayList<>();
				for (XykWjflBltjVo xykWjflBltjVo : list) {
					XykWjflBltj xykWjflBltj = new XykWjflBltj();
					BeanUtil.copyPropertiesIgnoreNull(xykWjflBltjVo, xykWjflBltj);
					xykWjflBltj.setDataDate(fiscalDate);
					xykWjflBltj.setLrbz(0);
					xykWjflBltj.setLrr(getLoginUser().getUsername());
					xykWjflBltj.setLrsj(new Date());
					dkhxdjbList.add(xykWjflBltj);
				}
				if (!dkhxdjbList.isEmpty()) {
					//保存以前删除当月数据，以防止重复导入
					UpdateWrapper<XykWjflBltj> wrapper = new UpdateWrapper<>();
					wrapper.eq("data_date", dkhxdjbList.get(0).getDataDate());
					xykWjflBltjService.remove(wrapper);
					// 保存最新当月数据
					xykWjflBltjService.saveBatch(dkhxdjbList);
				}
				obj.put("filePath", filePath);
				fos = new FileOutputStream(baseFilePath);
				importResult.getWorkbook().write(fos);
				fos.flush();
				fos.close();
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

package org.cmms.modules.report.cwbb.zcfzb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.report.cwbb.zcfzb.entity.CwbbZcfzb;
import org.cmms.modules.report.cwbb.zcfzb.entity.CwbbZcfzbVO;
import org.cmms.modules.report.cwbb.zcfzb.service.ICwbbZcfzbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.report.cwbb.zcfzb.verify.ZcfzbImportVerify;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 财务报表资产负债表
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="财务报表资产负债表")
@RestController
@RequestMapping("/zcfzb/cwbbZcfzb")
public class CwbbZcfzbController extends JeecgController<CwbbZcfzb, ICwbbZcfzbService> {
	@Autowired
	private ICwbbZcfzbService cwbbZcfzbService;
	@Autowired
	private ZcfzbImportVerify zcfzbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param cwbbZcfzb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-分页列表查询")
	@ApiOperation(value="财务报表资产负债表-分页列表查询", notes="财务报表资产负债表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CwbbZcfzb cwbbZcfzb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CwbbZcfzb> queryWrapper = QueryGenerator.initQueryWrapper(cwbbZcfzb, req.getParameterMap());
		Page<CwbbZcfzb> page = new Page<CwbbZcfzb>(pageNo, pageSize);
		IPage<CwbbZcfzb> pageList = cwbbZcfzbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param cwbbZcfzb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-添加")
	@ApiOperation(value="财务报表资产负债表-添加", notes="财务报表资产负债表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CwbbZcfzb cwbbZcfzb) {
		cwbbZcfzbService.save(cwbbZcfzb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param cwbbZcfzb
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-编辑")
	@ApiOperation(value="财务报表资产负债表-编辑", notes="财务报表资产负债表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CwbbZcfzb cwbbZcfzb) {
		QueryWrapper<CwbbZcfzb> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",cwbbZcfzb.getSjrq()).eq("zc",cwbbZcfzb.getZc());
		cwbbZcfzbService.update(cwbbZcfzb,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-通过id删除")
	@ApiOperation(value="财务报表资产负债表-通过id删除", notes="财务报表资产负债表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="sjrq",required=true) String sjrq,@RequestParam(name = "zc",required = true) String zc) {
		QueryWrapper<CwbbZcfzb> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("sjrq",DateUtil.parse(sjrq)).eq("zc",zc);
		cwbbZcfzbService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-批量删除")
	@ApiOperation(value="财务报表资产负债表-批量删除", notes="财务报表资产负债表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@Param("sjrq") String sjrq) {
		QueryWrapper<CwbbZcfzb> queryWrapper = new QueryWrapper<>();
		DateTime parse = DateUtil.parse(sjrq);
		queryWrapper.eq("sjrq",parse);
		cwbbZcfzbService.remove(queryWrapper);
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "财务报表资产负债表-通过id查询")
	@ApiOperation(value="财务报表资产负债表-通过id查询", notes="财务报表资产负债表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CwbbZcfzb cwbbZcfzb = cwbbZcfzbService.getById(id);
		return Result.ok(cwbbZcfzb);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param cwbbZcfzb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CwbbZcfzb cwbbZcfzb) {
      return super.exportXls(request, cwbbZcfzb, CwbbZcfzb.class, "财务报表资产负债表");
    }

	/**
	 * 导出模板
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		// AutoPoi 导出Excel
		ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "财务报表资产负债表导入模板");
		modelAndView.addObject(NormalExcelConstants.CLASS, CwbbZcfzbVO.class);
		ExportParams exportParams = new ExportParams("财务报表资产负债表导入模板", "模板信息");
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
  /*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, CwbbZcfzb.class);
    }*/
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		String sjrq = request.getParameter("sjrq");
		System.out.println(sjrq + "----sjrq----");
		Date parse = DateUtil.parse(sjrq);
		String filePaths = jsonObject.getString("filePaths");
		if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			return Result.error("请先上传文件！");
		}
		String[] filePathList = filePaths.split(",");
		JSONObject obj = new JSONObject();
		for (String filePath : filePathList) {
			String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
			File file = new File(baseFilePath);
			ImportParams params = new ImportParams();
			params.setTitleRows(1);
			params.setHeadRows(1);
			params.setNeedSave(false);
			if (zcfzbImportVerify != null) {
				params.setVerifyHanlder(zcfzbImportVerify);
			}
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
			/*	fis = new FileInputStream(file);
				boolean checkResult = ExcelImportCheckUtil.check(fis, CwbbZcfzbVO.class, params);
				if (!checkResult) {
					return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				}*/
				ExcelImportResult<CwbbZcfzb> importResult = ExcelImportUtil.importExcelVerify(file, CwbbZcfzb.class,CwbbZcfzbVO.class, params);
				List<CwbbZcfzb> list = importResult.getList();
				List<CwbbZcfzb> qkmbList = new ArrayList<>();
				for (CwbbZcfzb qkmb : list) {
					qkmb.setSjrq(parse);
					qkmbList.add(qkmb);
				}
				service.saveBatch(qkmbList);
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
				IoUtil.close(fos);
				IoUtil.close(fos);
			}
		}
		return Result.ok("文件导入失败！");
	}
}

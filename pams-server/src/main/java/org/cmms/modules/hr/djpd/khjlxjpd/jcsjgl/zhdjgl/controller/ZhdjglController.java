package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity.Zhdjgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.entity.ZhdjglVO;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.service.IZhdjglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.zhdjgl.verify.ZhdjglImportVerify;
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
 * @Description: 支行等级管理
 * @Author: jeecg-boot
 * @Date:   2021-09-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行等级管理")
@RestController
@RequestMapping("/zhdjgl/zhdjgl")
public class ZhdjglController extends JeecgController<Zhdjgl, IZhdjglService> {
	@Autowired
	private IZhdjglService zhdjglService;
	@Autowired
	private ZhdjglImportVerify zhdjglImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param zhdjgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行等级管理-分页列表查询")
	@ApiOperation(value="支行等级管理-分页列表查询", notes="支行等级管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhdjgl zhdjgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zhdjgl> queryWrapper = QueryGenerator.initQueryWrapper(zhdjgl, req.getParameterMap());
		Page<Zhdjgl> page = new Page<Zhdjgl>(pageNo, pageSize);
		IPage<Zhdjgl> pageList = zhdjglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zhdjgl
	 * @return
	 */
	@AutoLog(value = "支行等级管理-添加")
	@ApiOperation(value="支行等级管理-添加", notes="支行等级管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zhdjgl zhdjgl) {
		if ("MM".equals(zhdjgl.getPdzq())){
			String format = DateUtil.format(zhdjgl.getPdrq(),"yyyy-MM-dd");
			String s = format.substring(0, 8) + "01";
			zhdjgl.setPdrq(DateUtil.parse(s));
		}
		zhdjglService.save(zhdjgl);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zhdjgl
	 * @return
	 */
	@AutoLog(value = "支行等级管理-编辑")
	@ApiOperation(value="支行等级管理-编辑", notes="支行等级管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zhdjgl zhdjgl) {
		QueryWrapper<Zhdjgl> queryWrapper = new QueryWrapper<Zhdjgl>();
		queryWrapper.eq("zzbz",zhdjgl.getZzbz());
		queryWrapper.eq("pdzq",zhdjgl.getPdzq());
		queryWrapper.eq("pdrq",zhdjgl.getPdrq());
		zhdjglService.update(zhdjgl,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "支行等级管理-通过id删除")
	@ApiOperation(value="支行等级管理-通过id删除", notes="支行等级管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("pdzq")String pdzq,@Param("pdrq")String pdrq) {
		QueryWrapper<Zhdjgl> queryWrapper = new QueryWrapper<Zhdjgl>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("pdzq",pdzq);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			queryWrapper.eq("pdrq",sdf.parse(pdrq));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		zhdjglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行等级管理-批量删除")
	@ApiOperation(value="支行等级管理-批量删除", notes="支行等级管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zhdjglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行等级管理-通过id查询")
	@ApiOperation(value="支行等级管理-通过id查询", notes="支行等级管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zhdjgl zhdjgl = zhdjglService.getById(id);
		return Result.ok(zhdjgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zhdjgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zhdjgl zhdjgl) {
      return super.exportXls(request, zhdjgl, Zhdjgl.class, "支行等级管理");
  }


	/**
	 * 导入模板
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
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "支行等级管理导入模板");
		modelAndView.addObject(NormalExcelConstants.CLASS, ZhdjglVO.class);
		ExportParams exportParams = new ExportParams("支行等级管理导入模板", "模板信息");
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
//		return super.importExcelByTemplate(jsonObject, request, response, Zhdjgl.class,zhdjglImportVerify);
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
			if (zhdjglImportVerify != null) {
				params.setVerifyHanlder(zhdjglImportVerify);
			}
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				boolean checkResult = ExcelImportCheckUtil.check(fis, ZhdjglVO.class, params);
				ExcelImportResult<Zhdjgl> importResult = ExcelImportUtil.importExcelVerify(file, Zhdjgl.class,ZhdjglVO.class, params);
				List<Zhdjgl> list = importResult.getList();
				service.saveBatch(list);
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
		return Result.ok("文件导入失败！");
	}


}

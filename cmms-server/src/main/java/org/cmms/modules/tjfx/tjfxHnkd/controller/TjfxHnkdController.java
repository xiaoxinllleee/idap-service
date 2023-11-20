package org.cmms.modules.tjfx.tjfxHnkd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkd;
import org.cmms.modules.tjfx.tjfxHnkd.entity.TjfxHnkdVO;
import org.cmms.modules.tjfx.tjfxHnkd.service.ITjfxHnkdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.tjfxHnkd.verify.TjfxHnkdImportVerify;
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
 * @Description: 统计分析惠农快贷
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="统计分析惠农快贷")
@RestController
@RequestMapping("/tjfxHnkd/tjfxHnkd")
public class TjfxHnkdController extends JeecgController<TjfxHnkd, ITjfxHnkdService> {
	@Autowired
	private ITjfxHnkdService tjfxHnkdService;
	@Autowired
	private TjfxHnkdImportVerify tjfxHnkdImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param tjfxHnkd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-分页列表查询")
	@ApiOperation(value="统计分析惠农快贷-分页列表查询", notes="统计分析惠农快贷-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxHnkd tjfxHnkd,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxHnkd> queryWrapper = QueryGenerator.initQueryWrapper(tjfxHnkd, req.getParameterMap());
		Page<TjfxHnkd> page = new Page<TjfxHnkd>(pageNo, pageSize);
		IPage<TjfxHnkd> pageList = tjfxHnkdService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfxHnkd
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-添加")
	@ApiOperation(value="统计分析惠农快贷-添加", notes="统计分析惠农快贷-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxHnkd tjfxHnkd) {
		tjfxHnkdService.save(tjfxHnkd);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfxHnkd
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-编辑")
	@ApiOperation(value="统计分析惠农快贷-编辑", notes="统计分析惠农快贷-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxHnkd tjfxHnkd) {
		tjfxHnkdService.updateById(tjfxHnkd);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-通过id删除")
	@ApiOperation(value="统计分析惠农快贷-通过id删除", notes="统计分析惠农快贷-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfxHnkdService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-批量删除")
	@ApiOperation(value="统计分析惠农快贷-批量删除", notes="统计分析惠农快贷-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxHnkdService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "统计分析惠农快贷-通过id查询")
	@ApiOperation(value="统计分析惠农快贷-通过id查询", notes="统计分析惠农快贷-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxHnkd tjfxHnkd = tjfxHnkdService.getById(id);
		return Result.ok(tjfxHnkd);
	}

	 /**
	  * 查询最新数据
	  *
	  * @return
	  */
	 @GetMapping(value = "/getByHhbm")
	 public Result<?> getByHhbm(String hhbm) {
		 List<TjfxHnkd> tjfxHnkdList = tjfxHnkdService.getByHhbm(hhbm);
		 if (!tjfxHnkdList.isEmpty()) {
		 	return Result.ok(tjfxHnkdList.get(0));
		 }
		 return Result.ok("未找到数据");
	 }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxHnkd
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxHnkd tjfxHnkd) {
      return super.exportXls(request, tjfxHnkd, TjfxHnkd.class, "统计分析惠农快贷");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "统计分析惠农快贷导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, TjfxHnkdVO.class);
		 ExportParams exportParams = new ExportParams("统计分析惠农快贷导入模板", "模板信息");
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
		String sjrq = request.getParameter("sjrq");
		Date parse = DateUtil.parse(sjrq);
		log.info(parse + "----sjrq----");
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
			if (tjfxHnkdImportVerify != null) {
				params.setVerifyHanlder(tjfxHnkdImportVerify);
			}
			FileOutputStream fos = null;
			try {
				ExcelImportResult<TjfxHnkd> importResult = ExcelImportUtil.importExcelVerify(file, TjfxHnkd.class,TjfxHnkdVO.class, params);
				List<TjfxHnkd> list = importResult.getList();
				List<TjfxHnkd> qkmbList = new ArrayList<>();
				for (TjfxHnkd qkmb : list) {
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
			}
		}
		return Result.ok("文件导入失败！");
	}
  /*  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, TjfxHnkd.class);
    }*/

}

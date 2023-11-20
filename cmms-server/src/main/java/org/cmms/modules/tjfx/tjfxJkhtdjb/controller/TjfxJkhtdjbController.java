package org.cmms.modules.tjfx.tjfxJkhtdjb.controller;

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
import org.cmms.modules.tjfx.tjfxJkhtdjb.entity.TjfxJkhtdjb;
import org.cmms.modules.tjfx.tjfxJkhtdjb.entity.TjfxJkhtdjbVO;
import org.cmms.modules.tjfx.tjfxJkhtdjb.service.ITjfxJkhtdjbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.tjfxJkhtdjb.verify.TjfxJkhtdjbImportVerify;
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
 * @Description: 借款合同登记簿
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="借款合同登记簿")
@RestController
@RequestMapping("/tjfxJkhtdjb/tjfxJkhtdjb")
public class TjfxJkhtdjbController extends JeecgController<TjfxJkhtdjb, ITjfxJkhtdjbService> {
	@Autowired
	private ITjfxJkhtdjbService tjfxJkhtdjbService;
	@Autowired
	private TjfxJkhtdjbImportVerify tjfxJkhtdjbImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param tjfxJkhtdjb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-分页列表查询")
	@ApiOperation(value="借款合同登记簿-分页列表查询", notes="借款合同登记簿-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TjfxJkhtdjb tjfxJkhtdjb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TjfxJkhtdjb> queryWrapper = QueryGenerator.initQueryWrapper(tjfxJkhtdjb, req.getParameterMap());
		Page<TjfxJkhtdjb> page = new Page<TjfxJkhtdjb>(pageNo, pageSize);
		IPage<TjfxJkhtdjb> pageList = tjfxJkhtdjbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param tjfxJkhtdjb
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-添加")
	@ApiOperation(value="借款合同登记簿-添加", notes="借款合同登记簿-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TjfxJkhtdjb tjfxJkhtdjb) {
		tjfxJkhtdjbService.save(tjfxJkhtdjb);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param tjfxJkhtdjb
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-编辑")
	@ApiOperation(value="借款合同登记簿-编辑", notes="借款合同登记簿-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TjfxJkhtdjb tjfxJkhtdjb) {
		tjfxJkhtdjbService.updateById(tjfxJkhtdjb);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-通过id删除")
	@ApiOperation(value="借款合同登记簿-通过id删除", notes="借款合同登记簿-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tjfxJkhtdjbService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-批量删除")
	@ApiOperation(value="借款合同登记簿-批量删除", notes="借款合同登记簿-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tjfxJkhtdjbService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "借款合同登记簿-通过id查询")
	@ApiOperation(value="借款合同登记簿-通过id查询", notes="借款合同登记簿-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TjfxJkhtdjb tjfxJkhtdjb = tjfxJkhtdjbService.getById(id);
		return Result.ok(tjfxJkhtdjb);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxJkhtdjb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxJkhtdjb tjfxJkhtdjb) {
      return super.exportXls(request, tjfxJkhtdjb, TjfxJkhtdjb.class, "借款合同登记簿");
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
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "借款合同登记簿导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, TjfxJkhtdjbVO.class);
		 ExportParams exportParams = new ExportParams("借款合同登记簿导入模板", "模板信息");
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
		String tjrq = request.getParameter("tjrq");
		Date parse = DateUtil.parse(tjrq);
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
			if (tjfxJkhtdjbImportVerify != null) {
				params.setVerifyHanlder(tjfxJkhtdjbImportVerify);
			}
			FileOutputStream fos = null;
			try {
				ExcelImportResult<TjfxJkhtdjb> importResult = ExcelImportUtil.importExcelVerify(file, TjfxJkhtdjb.class,TjfxJkhtdjbVO.class, params);
				List<TjfxJkhtdjb> list = importResult.getList();
				List<TjfxJkhtdjb> qkmbList = new ArrayList<>();
				for (TjfxJkhtdjb qkmb : list) {
					qkmb.setTjrq(parse);
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
    /*@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, TjfxJkhtdjb.class);
    }*/

}

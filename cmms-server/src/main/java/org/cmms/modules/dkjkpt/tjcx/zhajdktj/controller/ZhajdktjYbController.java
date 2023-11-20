package org.cmms.modules.dkjkpt.tjcx.zhajdktj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.entity.ZhajdktjYb;
import org.cmms.modules.dkjkpt.tjcx.zhajdktj.service.IZhajdktjYbService;
import org.cmms.modules.dkjkpt.tjcx.zhdktj.entity.ZhbndktjYb;
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
 * @Description: 支行按揭贷款统计_月报
 * @Author: jeecg-boot
 * @Date:   2022-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行按揭贷款统计_月报")
@RestController
@RequestMapping("/zhajdktj/zhajdktjYb")
public class ZhajdktjYbController extends JeecgController<ZhajdktjYb, IZhajdktjYbService> {
	@Autowired
	private IZhajdktjYbService zhajdktjYbService;
	
	/**
	 * 分页列表查询
	 *
	 * @param zhajdktjYb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-分页列表查询")
	@ApiOperation(value="支行按揭贷款统计_月报-分页列表查询", notes="支行按揭贷款统计_月报-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ZhajdktjYb zhajdktjYb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<ZhajdktjYb>> result = new Result<IPage<ZhajdktjYb>>();
		QueryWrapper<ZhajdktjYb> queryWrapper = QueryGenerator.initQueryWrapper(zhajdktjYb, req.getParameterMap());
		Page<ZhajdktjYb> page = new Page<ZhajdktjYb>(pageNo, pageSize);
		IPage<ZhajdktjYb> pageList = zhajdktjYbService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}
	/**
	 * 添加
	 *
	 * @param zhajdktjYb
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-添加")
	@ApiOperation(value="支行按揭贷款统计_月报-添加", notes="支行按揭贷款统计_月报-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ZhajdktjYb zhajdktjYb) {
		zhajdktjYbService.save(zhajdktjYb);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param zhajdktjYb
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-编辑")
	@ApiOperation(value="支行按揭贷款统计_月报-编辑", notes="支行按揭贷款统计_月报-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ZhajdktjYb zhajdktjYb) {
		Result<ZhajdktjYb> result = new Result<ZhajdktjYb>();
		ZhajdktjYb zhbndktjYbEntity = zhajdktjYbService.getById(zhajdktjYb.getJgdm());
		if(zhbndktjYbEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = zhajdktjYbService.updateById(zhajdktjYb);
			//TODO 返回false说明什么？
			if(ok) {
				result.success("修改成功!");
			}
		}

		return result;
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-通过id删除")
	@ApiOperation(value="支行按揭贷款统计_月报-通过id删除", notes="支行按揭贷款统计_月报-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		try {
			zhajdktjYbService.removeById(id);
		} catch (Exception e) {
			log.error("删除失败",e.getMessage());
			return Result.error("删除失败!");
		}
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-批量删除")
	@ApiOperation(value="支行按揭贷款统计_月报-批量删除", notes="支行按揭贷款统计_月报-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<ZhajdktjYb> result = new Result<ZhajdktjYb>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.zhajdktjYbService.removeByIds(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行按揭贷款统计_月报-通过id查询")
	@ApiOperation(value="支行按揭贷款统计_月报-通过id查询", notes="支行按揭贷款统计_月报-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ZhajdktjYb zhajdktjYb = zhajdktjYbService.getById(id);
		return Result.ok(zhajdktjYb);
	}

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param response
	  */
	 @RequestMapping(value = "/exportXls")
	 public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
		 // Step.1 组装查询条件
		 QueryWrapper<ZhajdktjYb> queryWrapper = null;
		 try {
			 String paramsStr = request.getParameter("paramsStr");
			 if (oConvertUtils.isNotEmpty(paramsStr)) {
				 String deString = URLDecoder.decode(paramsStr, "UTF-8");
				 ZhajdktjYb zhajdktjYb = JSON.parseObject(deString, ZhajdktjYb.class);
				 queryWrapper = QueryGenerator.initQueryWrapper(zhajdktjYb, request.getParameterMap());
			 }
		 } catch (UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }

		 //Step.2 AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 List<ZhajdktjYb> pageList = zhajdktjYbService.list(queryWrapper);
		 //导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "支行按揭贷款统计(月报)列表");
		 mv.addObject(NormalExcelConstants.CLASS, ZhajdktjYb.class);
		 mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("支行按揭贷款统计(月报)列表数据", "导出人:Jeecg", "导出信息"));
		 mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, ZhajdktjYb.class);
  }

	 /**
	  * 提取
	  * @param jsonObject
	  * @return
	  */
	 @PutMapping(value = "/extract")
	 public Result<?> extract1(@RequestBody JSONObject jsonObject) {
		 /*Map<String,String>parm = new HashMap<>();*/
		 try {
			 Map<String,String>parm = new HashMap<>();
			 String tjyf = jsonObject.getString("tjyf");
			 tjyf = tjyf.replace("-","");
			 parm.put("tjyf", tjyf);
			 parm.put("type", "2");
			 parm.put("tjwd", "");
			 System.out.println("@@@@@@@parm="+parm);
			 zhajdktjYbService.extract(parm);
		 }catch (Exception e) {
			 log.error(e.getMessage(), "提取失败");
			 return Result.error(e.getMessage());
		 }
		 return Result.ok("提取成功");

	 }

}

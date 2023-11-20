package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.controller;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptBndksjjktz;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjkptbwblxt;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.DkjkptbwblxtImport;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjlptbnblxt;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjkptbwblxtService;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.verify.BwblImportVerify;
import org.cmms.modules.system.entity.SysDic;
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
 * @Description: 贷款监控平台表外不良_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款监控平台表外不良_湘潭")
@RestController
@RequestMapping("/dksjjktz/dkjkptbwblxt")
public class DkjkptbwblxtController extends JeecgController<Dkjkptbwblxt, IDkjkptbwblxtService> {
	@Autowired
	private IDkjkptbwblxtService dkjkptbwblxtService;
	 @Autowired
	 private BwblImportVerify bwblImportVerify;
	/**
	 * 分页列表查询
	 *
	 * @param dkjkptbwblxt
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-分页列表查询")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-分页列表查询", notes="贷款监控平台表外不良_湘潭-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dkjkptbwblxt dkjkptbwblxt,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dkjkptbwblxt> queryWrapper = QueryGenerator.initQueryWrapper(dkjkptbwblxt, req.getParameterMap());
		Page<Dkjkptbwblxt> page = new Page<Dkjkptbwblxt>(pageNo, pageSize);
		IPage<Dkjkptbwblxt> pageList = dkjkptbwblxtService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 /**
	  *  查询数据湘潭表外不良
	  * @param
	  * @return
	  */
	 @RequestMapping(value = "/queryBwbl", method = RequestMethod.GET)
	 public Result<?> queryBwbl(@RequestParam(name="dkzh",required=true)String dkzh ) {
		 QueryWrapper<Dkjkptbwblxt> wrapper=new QueryWrapper<>();
		 wrapper.eq("dkzh",dkzh);
		 List<Dkjkptbwblxt> list2 = dkjkptbwblxtService.list(wrapper);
		 return Result.ok(list2);

	 }
	/**
	 * 添加
	 *
	 * @param dkjkptbwblxt
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-添加")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-添加", notes="贷款监控平台表外不良_湘潭-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dkjkptbwblxt dkjkptbwblxt) {
		dkjkptbwblxtService.save(dkjkptbwblxt);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-编辑")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-编辑", notes="贷款监控平台表外不良_湘潭-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody JSONObject jsonObject) {
		//获取表外不良
		JSONArray bwbl = jsonObject.getJSONArray("bwbl");
		List<Dkjkptbwblxt> dkjkptbwblxts = JSONObject.parseArray(bwbl.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxts)){
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			}
		}
		//获取表外不良
		JSONArray bwblqs = jsonObject.getJSONArray("bwblqs");
		List<Dkjkptbwblxt> dkjkptbwblxtsqs = JSONObject.parseArray(bwblqs.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxtsqs)){
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxtsqs) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			}
		}
		//获取表外不良
		JSONArray bwbl1 = jsonObject.getJSONArray("bwbl1");
		List<Dkjkptbwblxt> dkjkptbwblxts1 = JSONObject.parseArray(bwbl1.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxts1)) {
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts1) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setMonth1(dkjkptbwblxt.getMonth1());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
			}
			JSONArray bwbl2 = jsonObject.getJSONArray("bwbl2");
			List<Dkjkptbwblxt> dkjkptbwblxts2 = JSONObject.parseArray(bwbl2.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts2)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts2) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					System.out.println("dkjkptbwblxt2==" + dkjkptbwblxt);
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl3 = jsonObject.getJSONArray("bwbl3");
			List<Dkjkptbwblxt> dkjkptbwblxts3 = JSONObject.parseArray(bwbl3.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts3)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts3) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					System.out.println("dkjkptbwblxts3==" + dkjkptbwblxt);
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl4 = jsonObject.getJSONArray("bwbl4");
			List<Dkjkptbwblxt> dkjkptbwblxts4 = JSONObject.parseArray(bwbl4.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts4)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts4) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl5 = jsonObject.getJSONArray("bwbl5");
			List<Dkjkptbwblxt> dkjkptbwblxts5 = JSONObject.parseArray(bwbl5.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts5)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts5) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl6 = jsonObject.getJSONArray("bwbl6");
			List<Dkjkptbwblxt> dkjkptbwblxts6 = JSONObject.parseArray(bwbl6.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts6)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts6) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl7 = jsonObject.getJSONArray("bwbl7");
			List<Dkjkptbwblxt> dkjkptbwblxts7 = JSONObject.parseArray(bwbl7.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts7)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts7) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl8 = jsonObject.getJSONArray("bwbl8");
			List<Dkjkptbwblxt> dkjkptbwblxts8 = JSONObject.parseArray(bwbl8.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts8)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts8) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
			JSONArray bwbl9 = jsonObject.getJSONArray("bwbl9");
			List<Dkjkptbwblxt> dkjkptbwblxts9 = JSONObject.parseArray(bwbl9.toJSONString(), Dkjkptbwblxt.class);
			if (CollUtil.isNotEmpty(dkjkptbwblxts9)) {
				for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts9) {
					QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
					queryWrapperBwbl.eq("dkzh", dkjkptbwblxt.getDkzh());
					dkjkptbwblxt.setXgsj(new Date());
					dkjkptbwblxtService.update(dkjkptbwblxt, queryWrapperBwbl);
				}
			}
		}
		JSONArray bwbl10 = jsonObject.getJSONArray("bwbl10");
		List<Dkjkptbwblxt> dkjkptbwblxts10 = JSONObject.parseArray(bwbl10.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxts10)){
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts10) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			}
		}
		JSONArray bwbl11 = jsonObject.getJSONArray("bwbl11");
		List<Dkjkptbwblxt> dkjkptbwblxts11 = JSONObject.parseArray(bwbl11.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxts11)){
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts11) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			}
		}
		JSONArray bwbl12 = jsonObject.getJSONArray("bwbl12");
		List<Dkjkptbwblxt> dkjkptbwblxts12 = JSONObject.parseArray(bwbl12.toJSONString(), Dkjkptbwblxt.class);
		if (CollUtil.isNotEmpty(dkjkptbwblxts12)){
			for (Dkjkptbwblxt dkjkptbwblxt : dkjkptbwblxts12) {
				QueryWrapper<Dkjkptbwblxt> queryWrapperBwbl = new QueryWrapper<>();
				queryWrapperBwbl.eq("dkzh",dkjkptbwblxt.getDkzh());
				dkjkptbwblxt.setXgsj(new Date());
				dkjkptbwblxtService.update(dkjkptbwblxt,queryWrapperBwbl);
			}
		}
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-通过id删除")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-通过id删除", notes="贷款监控平台表外不良_湘潭-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dkjkptbwblxtService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-批量删除")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-批量删除", notes="贷款监控平台表外不良_湘潭-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dkjkptbwblxtService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款监控平台表外不良_湘潭-通过id查询")
	@ApiOperation(value="贷款监控平台表外不良_湘潭-通过id查询", notes="贷款监控平台表外不良_湘潭-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dkjkptbwblxt dkjkptbwblxt = dkjkptbwblxtService.getById(id);
		return Result.ok(dkjkptbwblxt);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param dkjkptbwblxt
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Dkjkptbwblxt dkjkptbwblxt) {
      return super.exportXls(request, dkjkptbwblxt, Dkjkptbwblxt.class, "贷款监控平台表外不良");
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
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
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
			 if (bwblImportVerify != null) {
				 params.setVerifyHanlder(bwblImportVerify);
			 }
			 FileInputStream fis = null;
			 FileOutputStream fos = null;
			 try {
				 fis = new FileInputStream(file);
				 ExcelImportResult<Dkjkptbwblxt> importResult = ExcelImportUtil.importExcelVerify(file, Dkjkptbwblxt.class, DkjkptbwblxtImport.class, params);
				 List<Dkjkptbwblxt> list = importResult.getList();
				 List<Dkjkptbwblxt> dkjlptgzlxts = new ArrayList<>();
				 for (Dkjkptbwblxt zzsfpxx : list) {
					 zzsfpxx.setLrr(sysUser.getUsername());
					 zzsfpxx.setLrsj(new Date());
					 dkjlptgzlxts.add(zzsfpxx);
				 }
				 dkjkptbwblxtService.saveBatch(dkjlptgzlxts);
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

	 /**
	  * 提取
	  * @return
	  */
	 @AutoLog(value = "提取")
	 @ApiOperation(value="提取", notes="提取")
	 @GetMapping(value = "/init")
	 public Result<Dkjkptbwblxt> init(HttpServletRequest request, HttpServletResponse response) {
		 Result<Dkjkptbwblxt> result = new Result<Dkjkptbwblxt>();
		 try {
			 dkjkptbwblxtService.init();
			 result.setSuccess(true);
			 result.setMessage("统计成功");
			 return result;
		 } catch (Exception e) {
			 log.error(e.getMessage(), e);
			 result.setSuccess(false);
			 result.setMessage("统计失败");
			 return result;
		 }

	 }
}

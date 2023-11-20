package org.cmms.modules.khgl.nh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.CamsPlpyYsfj;
import org.cmms.modules.khgl.nh.service.ICamsPlpyYsfjService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 批量评议验收附件
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="批量评议验收附件")
@RestController
@RequestMapping("/nh/camsPlpyYsfj")
public class CamsPlpyYsfjController extends JeecgController<CamsPlpyYsfj, ICamsPlpyYsfjService> {
	@Autowired
	private ICamsPlpyYsfjService camsPlpyYsfjService;
	
	/**
	 * 分页列表查询
	 *
	 * @param camsPlpyYsfj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-分页列表查询")
	@ApiOperation(value="批量评议验收附件-分页列表查询", notes="批量评议验收附件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsPlpyYsfj camsPlpyYsfj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsPlpyYsfj> queryWrapper = QueryGenerator.initQueryWrapper(camsPlpyYsfj, req.getParameterMap());
		Page<CamsPlpyYsfj> page = new Page<CamsPlpyYsfj>(pageNo, pageSize);
		IPage<CamsPlpyYsfj> pageList = camsPlpyYsfjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param camsPlpyYsfj
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-添加")
	@ApiOperation(value="批量评议验收附件-添加", notes="批量评议验收附件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CamsPlpyYsfj camsPlpyYsfj) {
		camsPlpyYsfjService.save(camsPlpyYsfj);
		return Result.ok("添加成功！");
	}

	 @PostMapping(value = "/add2")
	 public Result<?> add(@RequestBody JSONObject jsonObject) {
		 System.out.println(jsonObject);
		 String fwlj = jsonObject.getString("fwlj");
		 String zllx = jsonObject.getString("zllx");
		 String otherId = jsonObject.getString("otherId");

		 if (StringUtils.isBlank(otherId)){
			 otherId = IdUtil.fastSimpleUUID()+"temp";
		 }

		 if (zllx.contains(",")){
			 String[] split = zllx.split(",");
			 LambdaQueryWrapper<CamsPlpyYsfj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			 lambdaQueryWrapper.in(CamsPlpyYsfj::getZllx,split);
			 lambdaQueryWrapper.eq(CamsPlpyYsfj::getOtherId,otherId);
			 service.remove(lambdaQueryWrapper);
		 }else {
			 LambdaQueryWrapper<CamsPlpyYsfj> lambdaQueryWrapper = new LambdaQueryWrapper<>();
			 lambdaQueryWrapper.eq(CamsPlpyYsfj::getZllx,zllx);
			 lambdaQueryWrapper.eq(CamsPlpyYsfj::getOtherId,otherId);
			 service.remove(lambdaQueryWrapper);
		 }
		 if (fwlj.contains(",")){
			 String[] split = fwlj.split(",");
			 String[] split1 = zllx.split(",");
			 for (int i = 0; i < split.length; i++) {
				 if (StringUtils.isBlank(split1[i]))
					 continue;
				 CamsPlpyYsfj camsPlpyYsfj = new CamsPlpyYsfj();
				 camsPlpyYsfj.setFwlj(split[i]);
				 camsPlpyYsfj.setZllx(split1[i]);
				 camsPlpyYsfj.setOtherId(otherId);
				 camsPlpyYsfj.setCreateBy(getWorkNo());
				 camsPlpyYsfj.setCreateTime(new Date());
				 service.save(camsPlpyYsfj);
			 }
		 }else if (StringUtils.isNotBlank(fwlj)){
			 CamsPlpyYsfj camsPlpyYsfj = new CamsPlpyYsfj();
			 camsPlpyYsfj.setFwlj(fwlj);
			 camsPlpyYsfj.setZllx(zllx);
			 camsPlpyYsfj.setOtherId(otherId);
			 camsPlpyYsfj.setCreateBy(getWorkNo());
			 camsPlpyYsfj.setCreateTime(new Date());
			 service.save(camsPlpyYsfj);
		 }
		 if (otherId.contains("temp"))
			 return Result.ok(otherId);
		 return Result.ok("添加成功！");
	 }
	
	/**
	 * 编辑
	 *
	 * @param camsPlpyYsfj
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-编辑")
	@ApiOperation(value="批量评议验收附件-编辑", notes="批量评议验收附件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CamsPlpyYsfj camsPlpyYsfj) {
		camsPlpyYsfjService.updateById(camsPlpyYsfj);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-通过id删除")
	@ApiOperation(value="批量评议验收附件-通过id删除", notes="批量评议验收附件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		camsPlpyYsfjService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-批量删除")
	@ApiOperation(value="批量评议验收附件-批量删除", notes="批量评议验收附件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.camsPlpyYsfjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "批量评议验收附件-通过id查询")
	@ApiOperation(value="批量评议验收附件-通过id查询", notes="批量评议验收附件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsPlpyYsfj camsPlpyYsfj = camsPlpyYsfjService.getById(id);
		return Result.ok(camsPlpyYsfj);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param camsPlpyYsfj
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsPlpyYsfj camsPlpyYsfj) {
      return super.exportXls(request, camsPlpyYsfj, CamsPlpyYsfj.class, "批量评议验收附件");
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
      return super.importExcel(request, response, CamsPlpyYsfj.class);
  }

  @RequestMapping("/getByOtherId")
  public Result<?> getByOtherId(String otherId,String zllx){
	  if (otherId.contains(",")){}
	  LambdaQueryWrapper<CamsPlpyYsfj> lambdaQueryWrapper = new LambdaQueryWrapper();
	  if (otherId.contains(",")){
		  lambdaQueryWrapper.in(CamsPlpyYsfj::getOtherId,otherId.split(","));
	  }else {
		  lambdaQueryWrapper.eq(CamsPlpyYsfj::getOtherId,otherId);
	  }
	  if (StringUtils.isNotBlank(zllx))
		  lambdaQueryWrapper.eq(CamsPlpyYsfj::getZllx,zllx);

	  return Result.ok(service.list(lambdaQueryWrapper));
  }
}

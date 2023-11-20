package org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity.VWbsjglGrtjs;
import org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.service.IVWbsjglGrtjsService;
import java.util.Date;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.wbsjgl.tjf.tjfdh.entity.KhxxglGrtjfDh;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzxxgl;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
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
 * @Description: 个人碳积分详情
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人碳积分详情")
@RestController
@RequestMapping("/grtjf/vWbsjglGrtjs")
public class VWbsjglGrtjsController extends JeecgController<VWbsjglGrtjs, IVWbsjglGrtjsService> {
	@Autowired
	private IVWbsjglGrtjsService vWbsjglGrtjsService;
	@Autowired
	private INhxqService nhxqService;
	@Autowired
	private IKhglNhhzxxglService khglNhhzxxglService;

	/**
	 * 分页列表查询
	 *
	 * @param vWbsjglGrtjs
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-分页列表查询")
	@ApiOperation(value="个人碳积分详情-分页列表查询", notes="个人碳积分详情-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VWbsjglGrtjs vWbsjglGrtjs,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<VWbsjglGrtjs> queryWrapper = QueryGenerator.initQueryWrapper(vWbsjglGrtjs, req.getParameterMap());
		Page<VWbsjglGrtjs> page = new Page<VWbsjglGrtjs>(pageNo, pageSize);
		IPage<VWbsjglGrtjs> pageList = vWbsjglGrtjsService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 /**
	  * 根据证件号码查询个人碳积分
	  */
	 @AutoLog(value = "根据证件号码查询个人碳积分")
	 @ApiOperation(value="根据证件号码查询个人碳积分", notes="根据证件号码查询个人碳积分")
	 @GetMapping(value = "/queryInfoByZjhm")
	 public Result<?> queryInfoByZjhm(@RequestParam("zjhm") String zjhm){
	 	QueryWrapper<VWbsjglGrtjs> queryWrapper=new QueryWrapper<>();
	 	queryWrapper.eq("sfz",zjhm);
	 	List<VWbsjglGrtjs> list=vWbsjglGrtjsService.list(queryWrapper);
	 	return Result.ok(list);
	 }

	 /**
	  * 根据多个证件号码查询家庭总碳积分
	  */
	 @AutoLog(value = "根据多个证件号码查询家庭总碳积分")
	 @ApiOperation(value="根据多个证件号码查询家庭总碳积分", notes="根据多个证件号码查询家庭总碳积分")
	 @GetMapping(value = "/queryJttjfByZjhm")
	 public Result<?> queryJttjfByZjhm(@RequestParam("zjhm") String zjhm){
		 QueryWrapper<VWbsjglGrtjs> queryWrapper=new QueryWrapper<>();
		 queryWrapper.in("sfz",Arrays.asList(zjhm.split(",")));
		 List<VWbsjglGrtjs> list=vWbsjglGrtjsService.list(queryWrapper);
		 return Result.ok(list);
	 }

     /**
      * 根据多个证件号码查询家庭总碳积分
      */
     @AutoLog(value = "根据多个证件号码查询家庭总碳积分")
     @ApiOperation(value = "根据多个证件号码查询家庭总碳积分", notes = "根据多个证件号码查询家庭总碳积分")
     @GetMapping(value = "/queryJttjfByZjhm2")
     public Result<?> queryJttjfByZjhm2(@RequestParam("hhbm") String hhbm) {
         QueryWrapper<Nhxq> nhxqQueryWrapper = new QueryWrapper<Nhxq>();
         nhxqQueryWrapper.eq("hhbm", hhbm);
         List<String> zjhmList = nhxqService.list(nhxqQueryWrapper).stream().map(Nhxq::getZjhm).collect(Collectors.toList());

         QueryWrapper<KhglNhhzxxgl> khglNhhzxxglQueryWrapper=new QueryWrapper<>();
         khglNhhzxxglQueryWrapper.eq("hhbm",hhbm);
         zjhmList.add(khglNhhzxxglService.getOne(khglNhhzxxglQueryWrapper).getHzzjhm());

         QueryWrapper<VWbsjglGrtjs> queryWrapper = new QueryWrapper<>();
         queryWrapper.in("sfz", zjhmList);
         List<VWbsjglGrtjs> list = vWbsjglGrtjsService.list(queryWrapper);
         return Result.ok(list);
     }

	/**
	 * 添加
	 *
	 * @param vWbsjglGrtjs
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-添加")
	@ApiOperation(value="个人碳积分详情-添加", notes="个人碳积分详情-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VWbsjglGrtjs vWbsjglGrtjs) {
		vWbsjglGrtjsService.save(vWbsjglGrtjs);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param vWbsjglGrtjs
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-编辑")
	@ApiOperation(value="个人碳积分详情-编辑", notes="个人碳积分详情-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VWbsjglGrtjs vWbsjglGrtjs) {
		vWbsjglGrtjsService.updateById(vWbsjglGrtjs);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-通过id删除")
	@ApiOperation(value="个人碳积分详情-通过id删除", notes="个人碳积分详情-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vWbsjglGrtjsService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-批量删除")
	@ApiOperation(value="个人碳积分详情-批量删除", notes="个人碳积分详情-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vWbsjglGrtjsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人碳积分详情-通过id查询")
	@ApiOperation(value="个人碳积分详情-通过id查询", notes="个人碳积分详情-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VWbsjglGrtjs vWbsjglGrtjs = vWbsjglGrtjsService.getById(id);
		return Result.ok(vWbsjglGrtjs);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vWbsjglGrtjs
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VWbsjglGrtjs vWbsjglGrtjs) {
      return super.exportXls(request, vWbsjglGrtjs, VWbsjglGrtjs.class, "个人碳积分详情");
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
      return super.importExcel(request, response, VWbsjglGrtjs.class);
  }

}

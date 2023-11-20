package org.cmms.modules.gzap.grrwsz.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Update;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.gzap.grrwsz.entity.GzapGrrwsz;
import org.cmms.modules.gzap.grrwsz.entity.HrBasStaffpostm;
import org.cmms.modules.gzap.grrwsz.service.IGzapGrrwszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.gzap.grrwsz.service.IHrBasStaffpostmService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.YgxxService;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
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
 * @Description: 个人任务设置
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人任务设置")
@RestController
@RequestMapping("/gzap.grrwsz/gzapGrrwsz")
public class GzapGrrwszController extends JeecgController<GzapGrrwsz, IGzapGrrwszService> {
	@Autowired
	private IGzapGrrwszService gzapGrrwszService;
	@Autowired
	private IHrBasStaffpostmService hrBasStaffpostmService;
	 @Autowired
	 private YgxxService ygxxService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param gzapGrrwsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人任务设置-分页列表查询")
	@ApiOperation(value="个人任务设置-分页列表查询", notes="个人任务设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(GzapGrrwsz gzapGrrwsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<GzapGrrwsz> queryWrapper = QueryGenerator.initQueryWrapper(gzapGrrwsz, req.getParameterMap());
		Page<GzapGrrwsz> page = new Page<GzapGrrwsz>(pageNo, pageSize);
		IPage<GzapGrrwsz> pageList = gzapGrrwszService.page(page, queryWrapper);

		return Result.ok(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param hrBasStaffpostm
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "个人任务设置-分页列表查询")
	 @ApiOperation(value="个人任务设置-分页列表查询", notes="个人任务设置-分页列表查询")
	 @GetMapping(value = "/gwxxlist")
	 public Result<?> querygwPageList(HrBasStaffpostm hrBasStaffpostm,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
	 	Map<String,String[]> map = new HashMap<>();

		 QueryWrapper<HrBasStaffpostm> queryWrapper = QueryGenerator.initQueryWrapper(hrBasStaffpostm, req.getParameterMap());
		 Page<HrBasStaffpostm> page = new Page<HrBasStaffpostm>(pageNo, pageSize);
		 IPage<HrBasStaffpostm> pageList = hrBasStaffpostmService.page(page, queryWrapper);
		 JSONArray jsonArray = new JSONArray();
		 for (HrBasStaffpostm record : pageList.getRecords()) {
			 JSONObject jsonObject = new JSONObject();
			 jsonObject.put("yggh",record.getYggh());
			 jsonObject.put("zzbz",record.getZzbz());
			 jsonObject.put("zzmc",record.getZzbz()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", record.getZzbz()));
			 jsonObject.put("gyh",record.getGyh());
			 jsonObject.put("khjlbz",record.getKhjlbz());
			 jsonObject.put("gwbz",record.getGwbz());
			 HrBasStaff hrBasStaff = new HrBasStaff();
			 hrBasStaff.setYggh(record.getYggh());
			 QueryWrapper<HrBasStaff> queryWrapper1 = QueryGenerator.initQueryWrapper(hrBasStaff, map);
			 List<HrBasStaff> hrBasStaffList =  ygxxService.list(queryWrapper1);
			 if (hrBasStaffList.size()!=0){
				 jsonObject.put("ygxm",hrBasStaffList.get(0).getYgxm());
			 }
			 jsonArray.add(jsonObject);
		 }
		 JSONObject jsonObject = new JSONObject();
		 jsonObject.put("records", jsonArray);
		 jsonObject.put("total", pageList.getTotal());
		 return Result.ok(jsonObject);
	 }
	
	/**
	 * 添加
	 *
	 * @param gzapGrrwsz
	 * @return
	 */
	@AutoLog(value = "个人任务设置-添加")
	@ApiOperation(value="个人任务设置-添加", notes="个人任务设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody GzapGrrwsz gzapGrrwsz) {
		gzapGrrwszService.save(gzapGrrwsz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param gzapGrrwsz
	 * @return
	 */
	@AutoLog(value = "个人任务设置-编辑")
	@ApiOperation(value="个人任务设置-编辑", notes="个人任务设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody GzapGrrwsz gzapGrrwsz) {
		UpdateWrapper<GzapGrrwsz> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("rwrq",gzapGrrwsz.getRwrq()).eq("yggh",gzapGrrwsz.getYggh()).eq("zzbz",gzapGrrwsz.getZzbz());
		gzapGrrwszService.update(gzapGrrwsz,updateWrapper);
		//gzapGrrwszService.updateById(gzapGrrwsz);

		return Result.ok("编辑成功!");
	}

	 /**
	  * 通过id删除
	  *
	  * @param gzapGrrwsz
	  * @return
	  */
	 @ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	 @RequestMapping(value = "/delete", method = RequestMethod.PUT)
	 public Result<?> querynhfzqk(@RequestBody GzapGrrwsz gzapGrrwsz) {

	 	UpdateWrapper<GzapGrrwsz> updateWrapper = new UpdateWrapper<>();
	 	updateWrapper.eq("rwrq",gzapGrrwsz.getRwrq()).eq("yggh",gzapGrrwsz.getYggh()).eq("zzbz",gzapGrrwsz.getZzbz());
		 gzapGrrwszService.remove(updateWrapper);
		 return Result.ok("删除成功!");
	 }

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	/*@AutoLog(value = "个人任务设置-通过id删除")
	@ApiOperation(value="个人任务设置-通过id删除", notes="个人任务设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		gzapGrrwszService.removeById(id);
		return Result.ok("删除成功!");
	}*/
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人任务设置-批量删除")
	@ApiOperation(value="个人任务设置-批量删除", notes="个人任务设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.gzapGrrwszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人任务设置-通过id查询")
	@ApiOperation(value="个人任务设置-通过id查询", notes="个人任务设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		GzapGrrwsz gzapGrrwsz = gzapGrrwszService.getById(id);
		return Result.ok(gzapGrrwsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param gzapGrrwsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, GzapGrrwsz gzapGrrwsz) {
      return super.exportXls(request, gzapGrrwsz, GzapGrrwsz.class, "个人任务设置");
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
      return super.importExcel(request, response, GzapGrrwsz.class);
  }

}

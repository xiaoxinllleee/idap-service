package org.cmms.modules.khlc.grgpgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.hr.zzgl.gwxxgl.service.IHrBasPostService;
import org.cmms.modules.khlc.grgpgl.entity.Grgpgl;
import org.cmms.modules.khlc.grgpgl.service.IGrgpglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
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
 * @Description: 个人挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="个人挂片管理")
@RestController
@RequestMapping("/grgpgl/grgpgl")
public class GrgpglController extends JeecgController<Grgpgl, IGrgpglService> {
	@Autowired
	private IGrgpglService grgpglService;
	 @Autowired
	 private IHrBasStaffService hrBasStaffService;
	/**
	 * 分页列表查询
	 *
	 * @param grgpgl
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-分页列表查询")
	@ApiOperation(value="个人挂片管理-分页列表查询", notes="个人挂片管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Grgpgl grgpgl,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String ygxm =grgpgl.getYgxm();
		grgpgl.setYgxm(null);
		QueryWrapper<Grgpgl> queryWrapper = QueryGenerator.initQueryWrapper(grgpgl, req.getParameterMap());
		if (StringUtils.isNotBlank(ygxm)){
			QueryWrapper<HrBasStaff> wrapper = new QueryWrapper<>();
			wrapper.like("ygxm",ygxm);
			List<HrBasStaff> list = hrBasStaffService.list(wrapper);
			List<String> arrayList = new ArrayList<>();
			for (HrBasStaff o : list) {
				arrayList.add(o.getYggh());
			}
			queryWrapper.in("yggh",arrayList);
		}
		Page<Grgpgl> page = new Page<Grgpgl>(pageNo, pageSize);
		IPage<Grgpgl> pageList = grgpglService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-添加")
	@ApiOperation(value="个人挂片管理-添加", notes="个人挂片管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JSONObject jsonObject) {
		String yggh = jsonObject.getString("yggh");
		String gwbz = jsonObject.getString("gwbz");
		String zzbz = jsonObject.getString("zzbz");

		String bmbz = jsonObject.getString("bmbzList");
		String bm = bmbz.substring(1).replaceAll("]", "");
		String[] bmbzList = bm.split(",");


			QueryWrapper<Grgpgl> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("yggh",yggh);
			queryWrapper.eq("zzbz",zzbz);
			queryWrapper.eq("gwbz",gwbz);
			grgpglService.remove(queryWrapper);
			if (bmbzList != null && bmbzList.length > 0){
				for (String zzjg : bmbzList) {
					log.info(zzjg+"===zzjg===");
					Grgpgl grgpgl = new Grgpgl();
					grgpgl.setYggh(yggh);
					grgpgl.setGwbz(gwbz);
					grgpgl.setBmbz(zzjg);
					grgpgl.setZzbz(zzbz);
					grgpgl.setLrczy(getUsername());
					grgpgl.setLrsj(new Date());
					grgpgl.setLrbz(1);
					grgpglService.save(grgpgl);
				}
			}
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param grgpgl
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-编辑")
	@ApiOperation(value="个人挂片管理-编辑", notes="个人挂片管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Grgpgl grgpgl) {
		grgpglService.updateById(grgpgl);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-通过id删除")
	@ApiOperation(value="个人挂片管理-通过id删除", notes="个人挂片管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("bmbz") String bmbz,@Param("yggh") String yggh,
							@Param("zzbz") String zzbz,@Param("gwbz") String gwbz) {
		QueryWrapper<Grgpgl> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("bmbz",bmbz);
		queryWrapper.eq("yggh",yggh);
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		grgpglService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-批量删除")
	@ApiOperation(value="个人挂片管理-批量删除", notes="个人挂片管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.grgpglService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "个人挂片管理-通过id查询")
	@ApiOperation(value="个人挂片管理-通过id查询", notes="个人挂片管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Grgpgl grgpgl = grgpglService.getById(id);
		return Result.ok(grgpgl);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param grgpgl
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Grgpgl grgpgl) {
      return super.exportXls(request, grgpgl, Grgpgl.class, "个人挂片管理");
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
      return super.importExcel(request, response, Grgpgl.class);
  }
	 /**
	  * 获取认领列表
	  *
	  * @param jsonObject
	  * @return
	  */
	 @AutoLog(value = "认领列表")
	 @ApiOperation(value = "认领", notes = "待分配存款帐号管理")
	 @PostMapping(value = "/getListClaim")
	 public Result<?> getListClaim(@RequestBody JSONObject jsonObject) {
		 String zzbz = jsonObject.getString("zzbz");
		 String yggh = jsonObject.getString("yggh");
		 String ygxm = jsonObject.getString("ygxm");
		 List<HrBasStaffPostVo> list = grgpglService.getListClaim(zzbz, yggh,ygxm);
		 return Result.ok(list);
	 }
}

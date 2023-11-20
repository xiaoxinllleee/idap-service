package org.cmms.modules.pad.pyxx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.entity.VKhglNhplpy;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.cmms.modules.pad.pyxx.service.IVKhglNhplpyService;
import org.cmms.modules.qxfk.qx.util.JsonUtil;
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
 * @Description: 农户批量评议客户视图
 * @Author: jeecg-boot
 * @Date:   2022-03-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags="农户批量评议客户视图")
@RestController
@RequestMapping("/pad/pyxx/vKhglNhplpy")
public class VKhglNhplpyController extends JeecgController<VKhglNhplpy, IVKhglNhplpyService> {
	@Autowired
	private IVKhglNhplpyService vKhglNhplpyService;
	@Autowired
	private INhxqService nhxqService;
	@Autowired
	private INhplpyService nhplpyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param vKhglNhplpy
	 * @param currentPage
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-分页列表查询")
	@ApiOperation(value="农户批量评议客户视图-分页列表查询", notes="农户批量评议客户视图-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(VKhglNhplpy vKhglNhplpy,
								   @RequestParam(name="currentPage", defaultValue="1") Integer currentPage,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   @RequestParam(name="pywg") String pywg,
								   @RequestParam(name="pyyKhid", required = false) String pyyKhid,
								   @RequestParam(name="pyyid", required = false) String pyyid,
								   @RequestParam(name="pyyzjhmQuery", required = false) String pyyzjhmQuery,
								   @RequestParam(name="zuinfo", required = false) String zuinfo,
								   @RequestParam(name="pylx", required = false) String pylx,
								   HttpServletRequest req) {
		if (StringUtils.isNotEmpty(pyyKhid)) {
			//选择的客户，从客户信息中获取
			Nhxq nhxq = nhxqService.getById(pyyKhid);
			pyyzjhmQuery = nhxq.getZjhm();
		} else if (!StringUtils.isEmpty(pyyid)) {
			// 从批量评议信息表中获取
			//选择的评议员，从表中获取，否则直接取页面录入的信息
			Nhplpy nhplpy = nhplpyService.getById(pyyid);
			pyyzjhmQuery = nhplpy.getPyyzjhm();
		}
		List<String> sfsxList=null;
		if (StringUtils.isNotEmpty(vKhglNhplpy.getSfsx())) {
			sfsxList = Arrays.asList(vKhglNhplpy.getSfsx().split(","));
			vKhglNhplpy.setSfsx(null);
		}
		if(StringUtils.isNotEmpty(pylx) && "1".equals(pylx)) {
			vKhglNhplpy.setSfbysx(null);
		}
		String hzxm = vKhglNhplpy.getHzxm();
		String hzzjhm = vKhglNhplpy.getHzzjhm();
		vKhglNhplpy.setHzxm(null);
		vKhglNhplpy.setHzzjhm(null);
		QueryWrapper<VKhglNhplpy> queryWrapper = QueryGenerator.initQueryWrapper(vKhglNhplpy, req.getParameterMap());
		if (StringUtils.isNotEmpty(hzxm)) {
			String hzxmStr = hzxm.replaceAll("\\*", "");
			queryWrapper.and(wrapper -> wrapper.like("hzxm", hzxmStr).or().like("sxdx", hzxmStr));
		}
		if (StringUtils.isNotEmpty(hzzjhm)) {
			queryWrapper.and(wrapper -> wrapper.eq("hzzjhm", hzzjhm).or().eq("sxdxzjh", hzzjhm));
		}
		if(StringUtils.isNotEmpty(pyyzjhmQuery)) {
			queryWrapper.eq("pyyzjhm", pyyzjhmQuery);
		}

//		if (StringUtils.isNotBlank(vKhglNhplpy.getHjdz()))
//			queryWrapper.like("hjdz",vKhglNhplpy.getHjdz());

        if (StringUtils.isNotEmpty(pywg)) {
            String[] pywgList = pywg.split(",");
//            queryWrapper.in("ssyxdy", pywgList);
            queryWrapper.inSql("ssyxdy", "select wgbh from yxdygl_main where wgbh in ('" + pywg + "') or parent_id in ('" + pywg + "')");
        }

		if (StringUtils.isNotBlank(zuinfo)){
			String[] split = zuinfo.split(",");
			queryWrapper.in("ssyxdy",split);
		}

		if (CollUtil.isNotEmpty(sfsxList) && StringUtils.isEmpty(pylx)) {
			queryWrapper.in("sfsx", sfsxList);
		}
		if(StringUtils.isNotEmpty(pylx) && "1".equals(pylx)) {
			//白名单 同时查询白名单不予授信的数据
			queryWrapper.last(" and ((sfbysx=2 and sfsx in (1,2)) or (sfbysx=1 and zfsfbysx=2)) order by ssyxdy,hhbm");
		} else {
			queryWrapper.last(" order by ssyxdy,hhbm");
		}
//        queryWrapper.orderByAsc("ssyxdy");
//        queryWrapper.orderByAsc("hhbm");
		Page<VKhglNhplpy> page = new Page<VKhglNhplpy>(currentPage, pageSize);
		IPage<VKhglNhplpy> pageList = vKhglNhplpyService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param vKhglNhplpy
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-添加")
	@ApiOperation(value="农户批量评议客户视图-添加", notes="农户批量评议客户视图-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody VKhglNhplpy vKhglNhplpy) {
		vKhglNhplpyService.save(vKhglNhplpy);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param vKhglNhplpy
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-编辑")
	@ApiOperation(value="农户批量评议客户视图-编辑", notes="农户批量评议客户视图-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody VKhglNhplpy vKhglNhplpy) {
		vKhglNhplpyService.updateById(vKhglNhplpy);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-通过id删除")
	@ApiOperation(value="农户批量评议客户视图-通过id删除", notes="农户批量评议客户视图-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		vKhglNhplpyService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-批量删除")
	@ApiOperation(value="农户批量评议客户视图-批量删除", notes="农户批量评议客户视图-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.vKhglNhplpyService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "农户批量评议客户视图-通过id查询")
	@ApiOperation(value="农户批量评议客户视图-通过id查询", notes="农户批量评议客户视图-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		VKhglNhplpy vKhglNhplpy = vKhglNhplpyService.getById(id);
		return Result.ok(vKhglNhplpy);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param vKhglNhplpy
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, VKhglNhplpy vKhglNhplpy) {
      return super.exportXls(request, vKhglNhplpy, VKhglNhplpy.class, "农户批量评议客户视图");
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
      return super.importExcel(request, response, VKhglNhplpy.class);
  }
}

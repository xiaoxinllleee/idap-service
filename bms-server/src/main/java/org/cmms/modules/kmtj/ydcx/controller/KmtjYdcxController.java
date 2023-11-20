package org.cmms.modules.kmtj.ydcx.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.kmtj.ydcx.entity.KmtjYdcx;
import org.cmms.modules.kmtj.ydcx.service.IKmtjYdcxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 月度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags="月度查询")
@RestController
@RequestMapping("/ydcx/kmtjYdcx")
public class KmtjYdcxController extends JeecgController<KmtjYdcx, IKmtjYdcxService> {
	@Autowired
	private IKmtjYdcxService kmtjYdcxService;
	@Autowired
	private IHrBasOrganizationService hrBasOrganizationService;

	/**
	 * 分页列表查询
	 *
	 * @param kmtjYdcx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "月度查询-分页列表查询")
	@ApiOperation(value="月度查询-分页列表查询", notes="月度查询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KmtjYdcx kmtjYdcx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		String jgmc = kmtjYdcx.getShowjgdm();
		kmtjYdcx.setShowjgdm(null);
		List ywjgdm = new ArrayList();
		QueryWrapper<KmtjYdcx> queryWrapper = QueryGenerator.initQueryWrapper(kmtjYdcx, req.getParameterMap());
		Page<KmtjYdcx> page = new Page<KmtjYdcx>(pageNo, pageSize);
		if (StringUtils.isNotBlank(jgmc)){
			QueryWrapper<HrBasOrganization> wrapper = new QueryWrapper<>();
			wrapper.like("zzmc",jgmc);
			List<HrBasOrganization> list = hrBasOrganizationService.list(wrapper);
			if (CollUtil.isNotEmpty(list)){
			for (HrBasOrganization hrBasOrganization : list) {
				ywjgdm.add(hrBasOrganization.getYwjgdm());
				queryWrapper.in("jgdm",ywjgdm);
				}
			}
		}
		IPage<KmtjYdcx> pageList = kmtjYdcxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param kmtjYdcx
	 * @return
	 */
	@AutoLog(value = "月度查询-添加")
	@ApiOperation(value="月度查询-添加", notes="月度查询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KmtjYdcx kmtjYdcx) {
		kmtjYdcxService.save(kmtjYdcx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param kmtjYdcx
	 * @return
	 */
	@AutoLog(value = "月度查询-编辑")
	@ApiOperation(value="月度查询-编辑", notes="月度查询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KmtjYdcx kmtjYdcx) {
		kmtjYdcxService.updateById(kmtjYdcx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "月度查询-通过id删除")
	@ApiOperation(value="月度查询-通过id删除", notes="月度查询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		kmtjYdcxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "月度查询-批量删除")
	@ApiOperation(value="月度查询-批量删除", notes="月度查询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.kmtjYdcxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/init")
	 public Result<?> init(@RequestBody JSONObject jsonObject){
		 String tjyf = jsonObject.getString("tjyf");
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 Result result = new Result<>();
		 tjyf = tjyf.replaceAll("-", "");
		 try{
			 kmtjYdcxService.pYgmrscs(tjyf);
			 result.setSuccess(true);
			 return result;
		 }catch (Throwable e){
			 System.out.println(e);
			 log.error("提取失败",e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "月度查询-通过id查询")
	@ApiOperation(value="月度查询-通过id查询", notes="月度查询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		KmtjYdcx kmtjYdcx = kmtjYdcxService.getById(id);
		return Result.ok(kmtjYdcx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param kmtjYdcx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KmtjYdcx kmtjYdcx) {
      return super.exportXls(request, kmtjYdcx, KmtjYdcx.class, "月度查询");
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
      return super.importExcel(request, response, KmtjYdcx.class);
  }

}

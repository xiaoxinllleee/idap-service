package org.cmms.modules.ywgl.cdkfx.dkdyrp.controller;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.entity.ErpBasWyxcssz;
import org.cmms.modules.ywgl.cdkfx.dkdyrp.service.IErpBasWyxcsszService;
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
 * @Description: 贷款当月日平参数设置表
 * @Author: jeecg-boot
 * @Date:   2021-06-11
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款当月日平参数设置表")
@RestController
@RequestMapping("/dkdyrp/erpBasWyxcssz")
public class ErpBasWyxcsszController extends JeecgController<ErpBasWyxcssz, IErpBasWyxcsszService> {
	@Autowired
	private IErpBasWyxcsszService erpBasWyxcsszService;


	@GetMapping(value = "judgeCsbm")
    public boolean judgeCsbm(@Param("csbm")String csbm){
	    if (!StringUtils.isBlank(csbm)) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("CSBM", csbm);
            List<ErpBasWyxcssz> list = erpBasWyxcsszService.list(queryWrapper);
            if (list.size() > 0)
                return false;
        }
        return true;
    }

	/**
	 * 分页列表查询
	 *
	 * @param erpBasWyxcssz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-分页列表查询")
	@ApiOperation(value="贷款当月日平参数设置表-分页列表查询", notes="贷款当月日平参数设置表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ErpBasWyxcssz erpBasWyxcssz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ErpBasWyxcssz> queryWrapper = QueryGenerator.initQueryWrapper(erpBasWyxcssz, req.getParameterMap());
		IPage pageList = PageUtil.toPage(IErpBasWyxcsszService.class,erpBasWyxcsszService, 1, 10, queryWrapper, "csbm");
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param erpBasWyxcssz
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-添加")
	@ApiOperation(value="贷款当月日平参数设置表-添加", notes="贷款当月日平参数设置表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ErpBasWyxcssz erpBasWyxcssz) {
		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("CSBM", erpBasWyxcssz.getCsbm());
		List<ErpBasWyxcssz> list = erpBasWyxcsszService.list(queryWrapper);
		if (list.size() > 0)
			return Result.error("参数编码重复!");
		erpBasWyxcssz.setLrbz(1);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		erpBasWyxcssz.setLrr(sysUser.getRealname());
		erpBasWyxcssz.setLrsj(new Timestamp(System.currentTimeMillis()));
		erpBasWyxcsszService.save(erpBasWyxcssz);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param erpBasWyxcssz
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-编辑")
	@ApiOperation(value="贷款当月日平参数设置表-编辑", notes="贷款当月日平参数设置表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ErpBasWyxcssz erpBasWyxcssz) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("csbm",erpBasWyxcssz.getCsbm());
		erpBasWyxcssz.setCsbm(null);//hive里面分桶键不能改动，设为null
		erpBasWyxcssz.setLrbz(2);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		erpBasWyxcssz.setLrr(sysUser.getRealname());
		erpBasWyxcssz.setLrsj(new Timestamp(System.currentTimeMillis()));
		erpBasWyxcsszService.update(erpBasWyxcssz,queryWrapper);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-通过id删除")
	@ApiOperation(value="贷款当月日平参数设置表-通过id删除", notes="贷款当月日平参数设置表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("CSBM",id);
        erpBasWyxcsszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-批量删除")
	@ApiOperation(value="贷款当月日平参数设置表-批量删除", notes="贷款当月日平参数设置表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.erpBasWyxcsszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款当月日平参数设置表-通过id查询")
	@ApiOperation(value="贷款当月日平参数设置表-通过id查询", notes="贷款当月日平参数设置表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ErpBasWyxcssz erpBasWyxcssz = erpBasWyxcsszService.getById(id);
		return Result.ok(erpBasWyxcssz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param erpBasWyxcssz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ErpBasWyxcssz erpBasWyxcssz) {
      return super.exportXls(request, erpBasWyxcssz, ErpBasWyxcssz.class, "贷款当月日平参数设置表");
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
      return super.importExcel(request, response, ErpBasWyxcssz.class);
  }


}

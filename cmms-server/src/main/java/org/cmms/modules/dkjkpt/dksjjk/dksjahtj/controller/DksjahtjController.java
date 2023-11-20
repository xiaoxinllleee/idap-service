package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dksjahtj;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dkyeb;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.DkyebExport;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.IDksjahtjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service.IDkyebService;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 贷款数据按户统计
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷款数据按户统计")
@RestController
@RequestMapping("/dkjkpt/dksjjk/dksjahtj")
public class DksjahtjController extends JeecgController<Dksjahtj, IDksjahtjService> {
	@Autowired
	private IDksjahtjService dksjahtjService;
	@Autowired
	private IDkyebService dkyebService;
	@Autowired
	private ISysDictService iSysDictService;

	/**
	 * 分页列表查询
	 *
	 * @param dksjahtj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-分页列表查询")
	@ApiOperation(value="贷款数据按户统计-分页列表查询", notes="贷款数据按户统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Dksjahtj dksjahtj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Dksjahtj> queryWrapper = QueryGenerator.initQueryWrapper(dksjahtj, req.getParameterMap());
		Page<Dksjahtj> page = new Page<Dksjahtj>(pageNo, pageSize);
		IPage<Dksjahtj> pageList = dksjahtjService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param dksjahtj
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-添加")
	@ApiOperation(value="贷款数据按户统计-添加", notes="贷款数据按户统计-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Dksjahtj dksjahtj) {
		dksjahtjService.save(dksjahtj);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param dksjahtj
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-编辑")
	@ApiOperation(value="贷款数据按户统计-编辑", notes="贷款数据按户统计-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Dksjahtj dksjahtj) {
		dksjahtjService.updateById(dksjahtj);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-通过id删除")
	@ApiOperation(value="贷款数据按户统计-通过id删除", notes="贷款数据按户统计-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		dksjahtjService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-批量删除")
	@ApiOperation(value="贷款数据按户统计-批量删除", notes="贷款数据按户统计-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.dksjahtjService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "贷款数据按户统计-通过id查询")
	@ApiOperation(value="贷款数据按户统计-通过id查询", notes="贷款数据按户统计-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Dksjahtj dksjahtj = dksjahtjService.getById(id);
		return Result.ok(dksjahtj);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param dksjahtj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dksjahtj dksjahtj) {
      return super.exportXls(request, dksjahtj, Dksjahtj.class, "贷款数据按户统计");
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
      return super.importExcel(request, response, Dksjahtj.class);
    }

	 /**
	  * 贷款数据按户统计-提取
	  * @param object
	  * @return
	  */
    @RequestMapping(value = "/init", method = RequestMethod.PUT)
    public Result<?> InitData(@RequestBody JSONObject object) {
		System.out.println("tjyf-----"+object.getString("tjyf"));
		String tjyf = object.getString("tjyf");
		tjyf = tjyf.replace("-", "");
    	try {
    		dksjahtjService.InitData(tjyf);
		} catch (Exception e) {
			log.error("提取失败！",e.getMessage());
			return Result.error(e.getMessage());
		}
    	return Result.ok("提取成功！");
	}

	 /**
	  * 贷款数据按户统计-单户贷款数据明细
	  * @param tjrq
	  * @param jgdm
	  * @param zjhm
	  * @return
	  */
	@GetMapping(value = "/dhdksjmx")
	public Result<List<Dkyeb>> queryDhdksjmx(@RequestParam(name = "tjrq", required = true) String tjrq,
											 @RequestParam(name = "jgdm", required = true) String jgdm,
											 @RequestParam(name = "zjhm", required = true) String zjhm) {
    	Result<List<Dkyeb>> result = new Result<>();
    	try {
			List<Dkyeb> dkyebList = dkyebService.queryDhdksjmx(tjrq,jgdm,zjhm);
			result.setResult(dkyebList);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
	}

	 /**
	  * 单户贷款数据明细-导出
	  */
	@RequestMapping(value = "/DhdksjmxExportXls")
	public ModelAndView exportDhdksjmxXls(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView(new JeecgEntityExcelView());
		String tjrq = request.getParameter("tjrq");
		String jgdm = request.getParameter("jgdm");
		String zjhm = request.getParameter("zjhm");

		List<Dkyeb> dkyebList = new ArrayList<>();
		dkyebList = dkyebService.queryDhdksjmx(tjrq,jgdm,zjhm);
		List<DkyebExport> dkyebExportList = new ArrayList<>();
		for (Dkyeb dhdkmx: dkyebList) {
			dhdkmx.setJgdm(dhdkmx.getJgdm()==null?"":iSysDictService.queryTableDictTextByKey("V_HR_BAS_ORGANIZATION_CMMSZH","ZZJC","YWJGDM",dhdkmx.getJgdm()));
			dhdkmx.setDkxt(dhdkmx.getDkxt()==null?"":iSysDictService.queryDictTextByKey("dkxt",dhdkmx.getDkxt()));
			DkyebExport dkyebExport = new DkyebExport();
			BeanUtils.copyProperties(dhdkmx, dkyebExport);
			dkyebExportList.add(dkyebExport);
		}
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		// 文件导出信息
		mav.addObject(NormalExcelConstants.FILE_NAME, "单户贷款数据明细");
		mav.addObject(NormalExcelConstants.CLASS, DkyebExport.class);
		mav.addObject(NormalExcelConstants.PARAMS, new ExportParams("单户贷款数据明细","导出人："+sysUser.getRealname(),"单户贷款数据"));
		mav.addObject(NormalExcelConstants.DATA_LIST, dkyebExportList);
		return mav;
	}
}

package org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.entity.Pxkspf;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.entity.PxkspfVO;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.service.IPxkspfService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.pxkspf.verify.PxkspfImportVerify;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 培训考试评分
 * @Author: jeecg-boot
 * @Date:   2021-09-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="培训考试评分")
@RestController
@RequestMapping("/pxkspf/pxkspf")
public class PxkspfController extends JeecgController<Pxkspf, IPxkspfService> {
	@Autowired
	private IPxkspfService pxkspfService;
	@Autowired
	private PxkspfImportVerify pxkspfImportVerify;

	/**
	 * 分页列表查询
	 *
	 * @param pxkspf
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "培训考试评分-分页列表查询")
	@ApiOperation(value="培训考试评分-分页列表查询", notes="培训考试评分-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pxkspf pxkspf,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pxkspf> queryWrapper = QueryGenerator.initQueryWrapper(pxkspf, req.getParameterMap());
		Page<Pxkspf> page = new Page<Pxkspf>(pageNo, pageSize);
		IPage<Pxkspf> pageList = pxkspfService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pxkspf
	 * @return
	 */
	@AutoLog(value = "培训考试评分-添加")
	@ApiOperation(value="培训考试评分-添加", notes="培训考试评分-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pxkspf pxkspf) {
		QueryWrapper queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("yggh",pxkspf.getYggh());
		queryWrapper.eq("zzbz",pxkspf.getZzbz());
		queryWrapper.eq("gwbz",pxkspf.getGwbz());
		queryWrapper.eq("kssj",pxkspf.getKssj());
		Pxkspf check = pxkspfService.getOne(queryWrapper, false);
		if (check != null){
			pxkspfService.remove(queryWrapper);
		}
		pxkspf.setLrbz(2);
		pxkspf.setLrr(getUsername());
		pxkspf.setLrsj(new Date());
		pxkspfService.save(pxkspf);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param pxkspf
	 * @return
	 */
	@AutoLog(value = "培训考试评分-编辑")
	@ApiOperation(value="培训考试评分-编辑", notes="培训考试评分-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pxkspf pxkspf) {
		QueryWrapper<Pxkspf> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("zzbz",pxkspf.getZzbz());
		queryWrapper.eq("gwbz",pxkspf.getGwbz());
		queryWrapper.eq("yggh",pxkspf.getYggh());
		queryWrapper.eq("kssj",pxkspf.getKssj());
		pxkspf.setLrbz(2);
		pxkspf.setLrr(getUsername());
		pxkspf.setLrsj(new Date());
		pxkspfService.update(pxkspf,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "培训考试评分-通过id删除")
	@ApiOperation(value="培训考试评分-通过id删除", notes="培训考试评分-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("zzbz")String zzbz,@Param("gwbz")String gwbz,
							@Param("yggh")String yggh,@Param("kssj")String kssj) {
		QueryWrapper<Pxkspf> queryWrapper = new QueryWrapper<Pxkspf>();
		queryWrapper.eq("zzbz",zzbz);
		queryWrapper.eq("gwbz",gwbz);
		queryWrapper.eq("yggh",yggh);
		queryWrapper.eq("kssj",DateUtil.parse(kssj));
		pxkspfService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "培训考试评分-批量删除")
	@ApiOperation(value="培训考试评分-批量删除", notes="培训考试评分-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pxkspfService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "培训考试评分-通过id查询")
	@ApiOperation(value="培训考试评分-通过id查询", notes="培训考试评分-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pxkspf pxkspf = pxkspfService.getById(id);
		return Result.ok(pxkspf);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pxkspf
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pxkspf pxkspf) {
      return super.exportXls(request, pxkspf, Pxkspf.class, "培训考试评分");
  }
	 /**
	  * 导入模板
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 // AutoPoi 导出Excel
		 ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 modelAndView.addObject(NormalExcelConstants.FILE_NAME, "培训考试评分导入模板");
		 modelAndView.addObject(NormalExcelConstants.CLASS, PxkspfVO.class);
		 ExportParams exportParams = new ExportParams("培训考试评分导入模板", "模板信息");
		 modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		 modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		 return modelAndView;
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
		 return super.importExcelByTemplate(jsonObject, request, response, Pxkspf.class,PxkspfVO.class,pxkspfImportVerify);
	 }
}

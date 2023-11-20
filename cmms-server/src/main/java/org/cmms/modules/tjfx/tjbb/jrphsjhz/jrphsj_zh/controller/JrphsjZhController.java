package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.nh.entity.*;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZhImport;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.service.IJrphsjZhService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 支行金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行金融普惠数据汇总")
@RestController
@RequestMapping("/tjfx/jrphsjZh")
public class JrphsjZhController extends JeecgController<JrphsjZh, IJrphsjZhService> {
	@Autowired
	private IJrphsjZhService jrphsjZhService;
	 @Autowired
	 private ITjfxZhbyService tjfxZhbyService;
	 @Autowired
	 private Environment environment;

	/**
	 * 分页列表查询
	 *
	 * @param jrphsjZh
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-分页列表查询")
	@ApiOperation(value="支行金融普惠数据汇总-分页列表查询", notes="支行金融普惠数据汇总-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(JrphsjZh jrphsjZh,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<JrphsjZh> queryWrapper = QueryGenerator.initQueryWrapper(jrphsjZh, req.getParameterMap());
		Page<JrphsjZh> page = new Page<JrphsjZh>(pageNo, pageSize);
		IPage<JrphsjZh> pageList = jrphsjZhService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param jrphsjZh
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-添加")
	@ApiOperation(value="支行金融普惠数据汇总-添加", notes="支行金融普惠数据汇总-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody JrphsjZh jrphsjZh) {
		jrphsjZhService.save(jrphsjZh);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param jrphsjZh
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-编辑")
	@ApiOperation(value="支行金融普惠数据汇总-编辑", notes="支行金融普惠数据汇总-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody JrphsjZh jrphsjZh) {
		jrphsjZhService.updateById(jrphsjZh);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-通过id删除")
	@ApiOperation(value="支行金融普惠数据汇总-通过id删除", notes="支行金融普惠数据汇总-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		jrphsjZhService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-批量删除")
	@ApiOperation(value="支行金融普惠数据汇总-批量删除", notes="支行金融普惠数据汇总-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.jrphsjZhService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "支行金融普惠数据汇总-通过id查询")
	@ApiOperation(value="支行金融普惠数据汇总-通过id查询", notes="支行金融普惠数据汇总-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		JrphsjZh jrphsjZh = jrphsjZhService.getById(id);
		return Result.ok(jrphsjZh);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param jrphsjZh
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, JrphsjZh jrphsjZh) {
      return super.exportXls(request, jrphsjZh, JrphsjZh.class, "支行金融普惠数据汇总");
  }

	 /**
	  * 导出excel
	  *
	  * @param request
	  * @param jrphsjZh
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(JrphsjZh jrphsjZh,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		 // Step.1 组装查询条件
		 QueryWrapper<JrphsjZh> queryWrapper = QueryGenerator.initQueryWrapper(jrphsjZh, request.getParameterMap());
		 //AutoPoi 导出Excel
		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
		 Map<String,Object> map = new HashMap<String, Object>();
		 List<JrphsjZhImport> jrphsjZhImportList = new ArrayList<>();
		 List<JrphsjZh> pageList = jrphsjZhService.list(queryWrapper);
		 for (JrphsjZh jrphsjZh1 : pageList) {
			 jrphsjZh1.setSszh(jrphsjZh1.getSszh()== null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization","zzjc","zzbz",jrphsjZh1.getSszh()));
			 JrphsjZhImport jrphsjZhImport = new JrphsjZhImport();
			 BeanUtils.copyProperties(jrphsjZh1,jrphsjZhImport);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 jrphsjZhImport.setTjyf(sdf.format(jrphsjZh1.getTjyf()));
			 jrphsjZhImportList.add(jrphsjZhImport);
		 }
		 map.put("list",jrphsjZhImportList);
		 String port = environment.getProperty("common.path.export");
		 //导出文件名称
		 mv.addObject(JxlsConstants.FILE_NAME, "支行金融普惠数据汇总");
		 mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行金融普惠数据汇总.xls"));
		 mv.addObject(JxlsConstants.SAVE_FILE_NAME, port+"/支行金融普惠数据汇总.xls");
		 mv.addObject(JxlsConstants.MAP_DATA, map);
		 return mv;
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
      return super.importExcel(request, response, JrphsjZh.class);
  }

}

package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.entity.Pdzbk;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdzbk.service.IPdzbkService;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.entity.Pdzbsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.service.IPdzbszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.pdzbsz.vo.PdzbszImport;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 评定指标设置
 * @Author: jeecg-boot
 * @Date:   2021-09-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="评定指标设置")
@RestController
@RequestMapping("/pdzbsz/pdzbsz")
public class PdzbszController extends JeecgController<Pdzbsz, IPdzbszService> {
	@Autowired
	private IPdzbszService pdzbszService;
	@Autowired
	private IPdzbkService pdzbkService;

	/**
	 * 分页列表查询
	 *
	 * @param pdzbsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "评定指标设置-分页列表查询")
	@ApiOperation(value="评定指标设置-分页列表查询", notes="评定指标设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Pdzbsz pdzbsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Pdzbsz> queryWrapper = QueryGenerator.initQueryWrapper(pdzbsz, req.getParameterMap());
		Page<Pdzbsz> page = new Page<Pdzbsz>(pageNo, pageSize);
		IPage<Pdzbsz> pageList = pdzbszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param pdzbsz
	 * @return
	 */
	@AutoLog(value = "评定指标设置-添加")
	@ApiOperation(value="评定指标设置-添加", notes="评定指标设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Pdzbsz pdzbsz) {
		pdzbszService.save(pdzbsz);
		return Result.ok("添加成功！");
	}

	 @AutoLog(value = "评定指标设置-添加")
	 @ApiOperation(value="评定指标设置-添加", notes="评定指标设置-添加")
	 @RequestMapping(value = "/save")
	 public Result<?> save(@RequestBody JSONObject jsonObject) {
		 Pdzbsz pdzbsz1 = new Pdzbsz();
		 String pdzq = jsonObject.getString("pdzq");
		 String pdrq = jsonObject.getString("pdrq");
		 JSONArray jsonArray = jsonObject.getJSONArray("pdzbsz");
		 String js = JSONObject.toJSONString(jsonArray);
		 List<Pdzbsz> pdzbszList = JSONObject.parseArray(js, Pdzbsz.class);
		 for (Pdzbsz s : pdzbszList) {
			 System.out.println(s.getZbid()+"zbid");
			 QueryWrapper queryWrapper = new QueryWrapper();
			 queryWrapper.eq("zbwd",pdzq);
			 queryWrapper.in("zbid",s.getZbid());
			 List<Pdzbk> zbk = pdzbkService.list(queryWrapper);
			 if (zbk.size() > 0){
				 for (int i = 0; i < zbk.size(); i++) {
				 	 pdzbsz1.setZbabs(s.getZbabs());
				 	 pdzbsz1.setZbfz(s.getZbfz());
				 	 pdzbsz1.setJqbl(s.getJqbl());
				 	 pdzbsz1.setJqfz(s.getJqfz());
				 	 pdzbsz1.setJqxzfz(s.getJqxzfz());
				 	 pdzbsz1.setKqbl(s.getKqbl());
				 	 pdzbsz1.setKqfz(s.getKqfz());
				 	 pdzbsz1.setKqxzfz(s.getKqxzfz());
					 pdzbsz1.setZbmc(zbk.get(i).getZbmc());
					 pdzbsz1.setZbid(zbk.get(i).getZbid());
					 pdzbsz1.setPdzq(pdzq);
					 pdzbsz1.setPdrq(DateUtil.parse(pdrq));
					 pdzbsz1.setLrbz(0);
					 pdzbsz1.setLrsj(new Date());
					 pdzbsz1.setLrr(getUsername());
					 pdzbszService.save(pdzbsz1);

				 }
			 }
		 }
		 return Result.ok("添加成功！");
	 }

	/**
	 * 编辑
	 *
	 * @param pdzbsz
	 * @return
	 */
	@AutoLog(value = "评定指标设置-编辑")
	@ApiOperation(value="评定指标设置-编辑", notes="评定指标设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Pdzbsz pdzbsz) {
		QueryWrapper<Pdzbsz> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pdzq",pdzbsz.getPdzq());
		queryWrapper.eq("pdrq",pdzbsz.getPdrq());
		queryWrapper.eq("zbid",pdzbsz.getZbid());
		pdzbsz.setXgr(getUsername());
		pdzbsz.setLrbz(2);
		pdzbsz.setXgsj(new Date());
		pdzbszService.update(pdzbsz,queryWrapper);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param
	 * @return
	 */
	@AutoLog(value = "评定指标设置-通过id删除")
	@ApiOperation(value="评定指标设置-通过id删除", notes="评定指标设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("pdzq")String pdzq,@Param("zbid")String zbid) {
		QueryWrapper<Pdzbsz> queryWrapper = new QueryWrapper<Pdzbsz>();
		queryWrapper.eq("pdzq",pdzq);
		queryWrapper.eq("zbid",zbid);
		pdzbszService.remove(queryWrapper);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "评定指标设置-批量删除")
	@ApiOperation(value="评定指标设置-批量删除", notes="评定指标设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pdzbszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "评定指标设置-通过id查询")
	@ApiOperation(value="评定指标设置-通过id查询", notes="评定指标设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Pdzbsz pdzbsz = pdzbszService.getById(id);
		return Result.ok(pdzbsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param pdzbsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Pdzbsz pdzbsz) {
      return super.exportXls(request, pdzbsz, Pdzbsz.class, "评定指标设置");
  }

	 /**
	  * 导出模板Excel
	  *
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		 // 导出文件名称
		 mv.addObject(NormalExcelConstants.FILE_NAME, "评定指标设置导入模板");
		 mv.addObject(NormalExcelConstants.CLASS, PdzbszImport.class);
		 ExportParams exportParams = new ExportParams("评定指标设置导入模板", "评定指标设置");
		 mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		 mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
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
  public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
      String filePaths = jsonObject.getString("filePaths");
      if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
          return Result.error("请先上传文件！");
      }
      String[] filePathList = filePaths.split(",");
      JSONObject obj = new JSONObject();
      for (String filePath : filePathList) {
          String baseFilePath = uploadpath + File.separator + filePath;
//          MultipartFile file = entity.getValue();// 获取上传文件对象
          File file = new File(baseFilePath);
          ImportParams params = new ImportParams();
          params.setTitleRows(1);
          params.setHeadRows(1);
          params.setNeedSave(false);
//          if (verifyHandler != null) {
//              params.setVerifyHanlder(verifyHandler);
//          }
          FileOutputStream fos = null;
          FileInputStream fis = null;
          try {
              fis = new FileInputStream(file);
              boolean checkResult = ExcelImportCheckUtil.check(fis, PdzbszImport.class, params);
              ExcelImportResult<PdzbszImport> importResult = ExcelImportUtil.importExcelVerify(file, PdzbszImport.class, params);
              List<PdzbszImport> list = importResult.getList();
			  List<Pdzbsz> pdzbszList = new ArrayList<>();
			  for (PdzbszImport pdzbszImport : list) {
				  Pdzbsz pdzbsz = new Pdzbsz();
				  BeanUtil.copyPropertiesIgnoreNull(pdzbszImport, pdzbsz);
				  pdzbszList.add(pdzbsz);

			  }
              service.saveBatch(pdzbszList);
              obj.put("filePath", filePath);
              fos = new FileOutputStream(baseFilePath);
              importResult.getWorkbook().write(fos);
              fos.flush();
              fos.close();
              return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              IoUtil.close(fis);
              IoUtil.close(fos);
          }
      }
      return Result.ok("文件导入失败！");
  }

}

package org.cmms.modules.dklldj.zbgzsz.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.zbgzsz.entity.Inputsz;
import org.cmms.modules.dklldj.zbgzsz.entity.Radiosz;
import org.cmms.modules.dklldj.zbgzsz.entity.Selectsz;
import org.cmms.modules.dklldj.zbgzsz.entity.Zbgzsz;
import org.cmms.modules.dklldj.zbgzsz.service.IInputszService;
import org.cmms.modules.dklldj.zbgzsz.service.IRadioszService;
import org.cmms.modules.dklldj.zbgzsz.service.ISelectszService;
import org.cmms.modules.dklldj.zbgzsz.service.IZbgzszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import org.cmms.modules.hr.yggl.ygxxgl.entity.HrBasStaff;
import org.cmms.modules.system.service.ISysDictService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 指标规则设置
 * @Author: jeecg-boot
 * @Date:   2020-04-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="指标规则设置")
@RestController
@RequestMapping("/dklldj.zbgzsz/zbgzsz")
public class ZbgzszController extends JeecgController<Zbgzsz, IZbgzszService> {
	 @Autowired
	 private IZbgzszService zbgzszService;
	 @Autowired
	 private IInputszService inputszService;
	 @Autowired
	 private IRadioszService radioszService;
	 @Autowired
	 private ISelectszService selectszService;
	 @Autowired
	 private ISysDictService iSysDictService;

	 /**
	 * 分页列表查询
	 *
	 * @param zbgzsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "指标规则设置-分页列表查询")
	@ApiOperation(value="指标规则设置-分页列表查询", notes="指标规则设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zbgzsz zbgzsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Zbgzsz> queryWrapper = QueryGenerator.initQueryWrapper(zbgzsz, req.getParameterMap());
		Page<Zbgzsz> page = new Page<Zbgzsz>(pageNo, pageSize);
		IPage<Zbgzsz> pageList = zbgzszService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param zbgzsz
	 * @return
	 */
	@AutoLog(value = "指标规则设置-添加")
	@ApiOperation(value="指标规则设置-添加", notes="指标规则设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Zbgzsz zbgzsz) {
		zbgzszService.save(zbgzsz);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param zbgzsz
	 * @return
	 */
	@AutoLog(value = "指标规则设置-编辑")
	@ApiOperation(value="指标规则设置-编辑", notes="指标规则设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Zbgzsz zbgzsz) {
		zbgzszService.updateById(zbgzsz);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标规则设置-通过id删除")
	@ApiOperation(value="指标规则设置-通过id删除", notes="指标规则设置-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		zbgzszService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "指标规则设置-批量删除")
	@ApiOperation(value="指标规则设置-批量删除", notes="指标规则设置-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.zbgzszService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "指标规则设置-通过id查询")
	@ApiOperation(value="指标规则设置-通过id查询", notes="指标规则设置-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Zbgzsz zbgzsz = zbgzszService.getById(id);
		return Result.ok(zbgzsz);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param zbgzsz
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Zbgzsz zbgzsz) {
      return super.exportXls(request, zbgzsz, Zbgzsz.class, "指标规则设置");
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
      return super.importExcel(request, response, Zbgzsz.class);
  }



	 /**
	  * 通过对公客户证件号码查询回访信息
	  * @param object
	  * @return 回访信息
	  */
	 @ApiOperation(value = "获取利率定价组件json串", notes = "获取利率定价组件json串")
	 @RequestMapping(value = "/getjsonMock", method = RequestMethod.PUT)
	 public Result<JSONArray> getjsonMock(@RequestBody JSONObject object) {
		 JSONObject  resultO=new JSONObject();
		 Result<JSONArray> result = new Result<>();
		 String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 Zbgzsz zbgzsz = new Zbgzsz();
		 zbgzsz.setQydm(qydm);
		 zbgzsz.setSfqy("1");
		 Map<String, String[]> map = new HashMap<>();
		 QueryWrapper<Zbgzsz> queryWrapper= QueryGenerator.initQueryWrapper(zbgzsz, map);
		 List<Zbgzsz> zbgzszList = zbgzszService.list(queryWrapper);
		 if (zbgzszList == null) {
			 result.error500("未找到对应实体！");
		 } else {
			 JSONArray jsonArray = new JSONArray();
			 for (Zbgzsz zbgzszb : zbgzszList) {
			 	 if(zbgzszb.getZblx().equals("input")){
					 jsonArray.add(getInputJson(zbgzszb));
				 }else if(zbgzszb.getZblx().equals("radio")){
					 jsonArray.add(getRadioJson(zbgzszb));
				 }else if(zbgzszb.getZblx().equals("select")){
					 jsonArray.add(getSelectJson(zbgzszb));
				 }
			 }
			 result.setResult(jsonArray);
			 result.setSuccess(true);
		 }
		 return result;
	 }



	 public JSONObject getInputJson(Zbgzsz zbgzsz){
	 	String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 JSONObject jsonObject = new JSONObject();
		 Inputsz inputsz = new Inputsz();
		 inputsz.setZbgzid(zbgzsz.getZbgzid());
		 inputsz.setQydm(qydm);
		 Map<String, String[]> map = new HashMap<>();
		 QueryWrapper<Inputsz> queryWrapper= QueryGenerator.initQueryWrapper(inputsz, map);
		 Inputsz InputszOne = inputszService.getOne(queryWrapper);
		/* JSONObject onchange = new JSONObject();
		 onchange.put("change","function(){alert(1)}");*/
		 JSONArray jsonArrayemit = new JSONArray();
		 jsonArrayemit.add("change");
		 jsonObject.put("emit",jsonArrayemit);
		 //onClick":"function(){alert('1')}"
		 jsonObject.put("type",zbgzsz.getZblx());
		 jsonObject.put("title",zbgzsz.getZbgzmc());
		 if(zbgzsz.getZbgzid().equals("GZ00004")){
			 jsonObject.put("emitPrefix","zcfz");
		 }
		 jsonObject.put("field",InputszOne.getZbgzid());
			 JSONObject props = new JSONObject();
			 props.put("type",InputszOne.getType());
			 props.put("disabled",Boolean.parseBoolean(InputszOne.getDisabled()));
			 props.put("readonly",Boolean.parseBoolean(InputszOne.getReadonly()));
			 props.put("placeholder",InputszOne.getPlaceholder());
/*
			 props.put("required",Boolean.parseBoolean(InputszOne.getRequired()));
*/
		 jsonObject.put("props",props);
		 JSONArray jsonArray = new JSONArray();
			 JSONObject validate = new JSONObject();
			 validate.put("required",Boolean.parseBoolean(InputszOne.getValidateRequired()));
		     validate.put("message",InputszOne.getValidateMessage());
		     validate.put("trigger",InputszOne.getValidateTrigger());
		     jsonArray.add(validate);
		 jsonObject.put("validate",jsonArray);
		 return jsonObject;
	 }

	 public JSONObject getRadioJson(Zbgzsz zbgzsz){
	 	String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 JSONObject jsonObject = new JSONObject();
		 Radiosz radiosz = new Radiosz();
		 radiosz.setZbgzid(zbgzsz.getZbgzid());
		 radiosz.setQydm(qydm);
		 Map<String, String[]> map = new HashMap<>();
		 QueryWrapper<Radiosz> queryWrapper= QueryGenerator.initQueryWrapper(radiosz, map);
		 List<Radiosz> list=radioszService.list(queryWrapper);
		 if (list.size()==0) {
			 return jsonObject;
		 } else {
			 Radiosz radioszOne = list.get(0);
			 jsonObject.put("type",zbgzsz.getZblx());
			 jsonObject.put("title",zbgzsz.getZbgzmc());
			 jsonObject.put("field",zbgzsz.getZbgzid());
			 JSONObject props = new JSONObject();
			 props.put("vertical",radioszOne.getVertical());
			 props.put("type",radioszOne.getType());
			 jsonObject.put("props",props);
			 JSONArray optionsArray = new JSONArray();
			 for (Radiosz radioszlist :list) {
				 JSONObject option = new JSONObject();
				 option.put("value",radioszlist.getOptionsValue());
				 option.put("label",radioszlist.getOptionsLable());
				 option.put("disabled",Boolean.parseBoolean(radioszlist.getOptionsDisabled()));
				 optionsArray.add(option);
			 }
			 jsonObject.put("options",optionsArray);
			 return jsonObject;
		 }
	 }

	 public JSONObject getSelectJson(Zbgzsz zbgzsz){
	 	String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC","VALUE","CODE","101001");
		 JSONObject jsonObject = new JSONObject();
		 Selectsz selectsz = new Selectsz();
		 selectsz.setZbgzid(zbgzsz.getZbgzid());
		 selectsz.setQydm(qydm);
		 Map<String, String[]> map = new HashMap<>();
		 QueryWrapper<Selectsz> queryWrapper= QueryGenerator.initQueryWrapper(selectsz, map);
		 List<Selectsz> list=selectszService.list(queryWrapper);
		 if (list.size()==0) {
			 return jsonObject;
		 } else {
			 Selectsz selectszOne=list.get(0);
			 jsonObject.put("type",zbgzsz.getZblx());
			 jsonObject.put("title",zbgzsz.getZbgzmc());
			 jsonObject.put("field",zbgzsz.getZbgzid());
				 JSONObject props = new JSONObject();
				 props.put("multiple",Boolean.parseBoolean(selectszOne.getMultiple()));
				 props.put("filterable",Boolean.parseBoolean(selectszOne.getFilterable()));
				 props.put("placeholder",selectszOne.getPlaceholder());
				 props.put("not-found-text",selectszOne.getNotfoundtext());
				 props.put("disabled",Boolean.parseBoolean(selectszOne.getDisabled()));
				 props.put("clearable",Boolean.parseBoolean(selectszOne.getClearable()));
				 props.put("placement",selectszOne.getPlacement());
			 jsonObject.put("props",props);
			 JSONArray optionsArray = new JSONArray();
			 for (Selectsz selectszlist :list) {
				 JSONObject option = new JSONObject();
				 option.put("value",selectszlist.getOptionsValue());
				 option.put("label",selectszlist.getOptionsLable());
				 option.put("disabled",Boolean.parseBoolean(selectszlist.getOptionsDisabled()));
				 optionsArray.add(option);
			 }
			 jsonObject.put("options",optionsArray);
			 return jsonObject;
		 }


	 }
}

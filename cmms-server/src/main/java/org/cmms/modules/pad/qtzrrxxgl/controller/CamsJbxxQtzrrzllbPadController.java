package org.cmms.modules.pad.qtzrrxxgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.qtzrrxxgl.entity.CamsJbxxQtzrrzllbPad;
import org.cmms.modules.pad.qtzrrxxgl.entity.CamsJbxxQtzrrzllbPadRecive;
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhmcxx;
import org.cmms.modules.pad.qtzrrxxgl.service.ICamsJbxxQtzrrzllbPadService;
import org.cmms.modules.pad.qtzrrxxgl.service.IKhglQtzrrhmcxxPadService;
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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/CamsJbxxQtzrrzllbPad")
public class CamsJbxxQtzrrzllbPadController extends JeecgController<CamsJbxxQtzrrzllbPad, ICamsJbxxQtzrrzllbPadService> {
	@Autowired
	private ICamsJbxxQtzrrzllbPadService camsJbxxQtzrrzllbPadService;

	 @Autowired
	 private IKhglQtzrrhmcxxPadService khglQtzrrhmcxxPadService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param camsJbxxNhzllbPad
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsJbxxQtzrrzllbPad camsJbxxNhzllbPad,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsJbxxQtzrrzllbPad> queryWrapper = QueryGenerator.initQueryWrapper(camsJbxxNhzllbPad, req.getParameterMap());
		Page<CamsJbxxQtzrrzllbPad> page = new Page<CamsJbxxQtzrrzllbPad>(pageNo, pageSize);
		IPage<CamsJbxxQtzrrzllbPad> pageList = camsJbxxQtzrrzllbPadService.page(page, queryWrapper);
		return Result.ok(pageList);
	}


	 @RequestMapping(value = "/queryQtzrrGrFjxx",method = RequestMethod.GET)
	 public Result<?> queryQtzrrGrFjxx(@RequestParam(name = "hhbm") String hhbm, @RequestParam(name = "id") String id) {
		 try {
			 QueryWrapper<KhglQtzrrhmcxx> khhmcQueryWrapper = new QueryWrapper<>();
			 khhmcQueryWrapper.eq("id", id);
			 KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(khhmcQueryWrapper);
			 QueryWrapper<CamsJbxxQtzrrzllbPad> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm);
			 fjxxQueryWrapper.eq("zjhm", khhmcxx.getZjhm());
			 List<CamsJbxxQtzrrzllbPad> list = camsJbxxQtzrrzllbPadService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 return Result.ok(list);
			 }
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("查询成功");
	 }


	 @RequestMapping(value = "/saveQtzrrGrfjImage",method = RequestMethod.POST)
	 public Result<?> saveQtzrrGrfjImage(@RequestBody  List<CamsJbxxQtzrrzllbPadRecive>  jsonObject1) {
		 try {
			 if (jsonObject1!=null  && jsonObject1.size()>0){
				 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				 for (int i = 0; i < jsonObject1.size(); i++) {
					 if (StringUtils.isEmpty(jsonObject1.get(i).getZlbh())) {
						 QueryWrapper<KhglQtzrrhmcxx> queryWrapper=new QueryWrapper<>();
						 queryWrapper.eq("id",jsonObject1.get(i).getHmcId());
						 KhglQtzrrhmcxx khhmcxx = khglQtzrrhmcxxPadService.getOne(queryWrapper);
						 CamsJbxxQtzrrzllbPad fjgl = new CamsJbxxQtzrrzllbPad();
						 fjgl.setHhbm(jsonObject1.get(i).getHhbm());
						 fjgl.setZlbh(UUID.randomUUID().toString().substring(0, 32));
						 fjgl.setQydm(jsonObject1.get(i).getQydm());
						 fjgl.setHmcId(jsonObject1.get(i).getHmcId());
						 fjgl.setZjhm(khhmcxx.getZjhm());
						 fjgl.setZllx(jsonObject1.get(i).getZllx());
						 fjgl.setZldx(jsonObject1.get(i).getZldx());
						 fjgl.setFwlj(jsonObject1.get(i).getFwlj());
						 fjgl.setZlmc(jsonObject1.get(i).getZlmc());
						 fjgl.setZllj(uploadpath + "/" + jsonObject1.get(i).getFwlj());
						 fjgl.setLrr(sysUser.getUsername());
						 fjgl.setScr(sysUser.getUsername());
						 fjgl.setScsj(new Date());
						 fjgl.setLrbz("1");
						 camsJbxxQtzrrzllbPadService.save(fjgl);
					 }
				 }
			 }
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }

	 @RequestMapping(value = "/deleteQtzrrGrfjImage",method = RequestMethod.POST)
	 public Result<?> deleteQtzrrGrfjImage(@RequestBody  List<CamsJbxxQtzrrzllbPadRecive> jsonObject) {
		 try {
			 if (jsonObject!=null  && jsonObject.size()>0){
				 for (int i = 0; i < jsonObject.size(); i++) {
					 if (!StringUtils.isEmpty(jsonObject.get(i).getZlmc())){
						 UpdateWrapper<CamsJbxxQtzrrzllbPad> updateWrapper=new UpdateWrapper<>();
						 updateWrapper.eq("zlmc",jsonObject.get(i).getZlmc());
						 camsJbxxQtzrrzllbPadService.remove(updateWrapper);
					 }
				 }
			 }

		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }



	 /**
	 * 添加
	 *
	 * @param camsJbxxNhzllbPad
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CamsJbxxQtzrrzllbPad camsJbxxNhzllbPad) {
		camsJbxxQtzrrzllbPadService.save(camsJbxxNhzllbPad);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param camsJbxxNhzllbPad
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CamsJbxxQtzrrzllbPad camsJbxxNhzllbPad) {
		camsJbxxQtzrrzllbPadService.updateById(camsJbxxNhzllbPad);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id删除")
	@ApiOperation(value="1-通过id删除", notes="1-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		camsJbxxQtzrrzllbPadService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "1-批量删除")
	@ApiOperation(value="1-批量删除", notes="1-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		camsJbxxQtzrrzllbPadService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsJbxxQtzrrzllbPad camsJbxxQtzrrzllbPad = camsJbxxQtzrrzllbPadService.getById(id);
		return Result.ok(camsJbxxQtzrrzllbPad);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param camsJbxxQtzrrzllbPad
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsJbxxQtzrrzllbPad camsJbxxQtzrrzllbPad) {
      return super.exportXls(request, camsJbxxQtzrrzllbPad, CamsJbxxQtzrrzllbPad.class, "1");
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
      return super.importExcel(request, response, CamsJbxxQtzrrzllbPad.class);
  }

}

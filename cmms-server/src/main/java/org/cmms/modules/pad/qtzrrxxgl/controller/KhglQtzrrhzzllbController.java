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
import org.cmms.modules.pad.qtzrrxxgl.entity.KhglQtzrrhzzllb;
import org.cmms.modules.pad.qtzrrxxgl.service.IKhglQtzrrhzzllbService;
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
 * @Date:   2020-08-02
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/KhglQtzrrhzzllb")
public class KhglQtzrrhzzllbController extends JeecgController<KhglQtzrrhzzllb, IKhglQtzrrhzzllbService> {
	@Autowired
	private IKhglQtzrrhzzllbService khglQtzrrhzzllbService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param khglQtzrrhzzllb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhglQtzrrhzzllb khglQtzrrhzzllb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhglQtzrrhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(khglQtzrrhzzllb, req.getParameterMap());
		Page<KhglQtzrrhzzllb> page = new Page<KhglQtzrrhzzllb>(pageNo, pageSize);
		IPage<KhglQtzrrhzzllb> pageList = khglQtzrrhzzllbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @RequestMapping(value = "/queryQtzrrhzFjxx",method = RequestMethod.GET)
	 public Result<?> queryQtzrrhzFjxx(@RequestParam(name = "hhbm") String hhbm) {
		 try {
			 QueryWrapper<KhglQtzrrhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm);
			 List<KhglQtzrrhzzllb> list = khglQtzrrhzzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 for (KhglQtzrrhzzllb khglQtzrrhzzllb : list) {
					 khglQtzrrhzzllb.setZllj("");
				 }
              return Result.ok(list);
			 }
		/*	 Map<String,List<KhglQtzrrhzzllb>> map=new HashMap<>();
			 QueryWrapper<KhglQtzrrhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm)
					 .eq("zllx","1");
			 List<KhglQtzrrhzzllb> list = khglNhhzzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 map.put("RhzppicList",list);
//                return Result.ok(list);
			 }
			 QueryWrapper<KhglQtzrrhzzllb> JhzQueryWrapper=new QueryWrapper<>();
			 JhzQueryWrapper.eq("hhbm",hhbm)
					 .eq("zllx","2");
			 List<KhglQtzrrhzzllb> listJhz = khglNhhzzllbService.list(JhzQueryWrapper);
			 if (listJhz!=null && listJhz.size()>0){
				 map.put("FwzppicList",listJhz);
//                return Result.ok(list);
			 }
			 if (map.size()>0){
				 return Result.ok(map);
			 }*/
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("查询成功");
	 }

	 @RequestMapping(value = "/queryByHhbmAndZllx",method = RequestMethod.GET)
	 public Result<?> queryByHhbmAndZllx(@RequestParam(name = "hhbm") String hhbm,
										 @RequestParam(name = "zllx") String zllx) {
		 try {
			 QueryWrapper<KhglQtzrrhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm", hhbm);
			 fjxxQueryWrapper.eq("zllx", zllx);
			 List<KhglQtzrrhzzllb> list = khglQtzrrhzzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 list.get(0).setZllj("");
				 return Result.ok(list.get(0));
			 }
		 }catch (Exception e){
		 	 log.error("查询户主附件信息失败", e);
			 return  Result.error("查询户主附件信息失败");
		 }
		 return Result.ok("查询成功");
	 }

	 @RequestMapping(value = "/saveQtzrrhzfjImage",method = RequestMethod.POST)
	 public Result<?> saveQtzrrhzfjImage(@RequestBody  List<KhglQtzrrhzzllb>  jsonObject) {
		 try {
		 	if (jsonObject!=null  && jsonObject.size()>0){
				LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				for (int i = 0; i < jsonObject.size(); i++) {
					if (StringUtils.isEmpty(jsonObject.get(i).getId())) {
						KhglQtzrrhzzllb fjgl = new KhglQtzrrhzzllb();
						fjgl.setQydm(jsonObject.get(i).getQydm());
						fjgl.setHhbm(jsonObject.get(i).getHhbm());
						fjgl.setZlbh(UUID.randomUUID().toString().substring(0, 14));
						fjgl.setZllx(jsonObject.get(i).getZllx());
						fjgl.setZldx(jsonObject.get(i).getZldx());
						fjgl.setFwlj(jsonObject.get(i).getFwlj());
						fjgl.setZlmc(jsonObject.get(i).getZlmc());
						fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
						fjgl.setScsj(new Date());
						fjgl.setScr(sysUser.getUsername());
						fjgl.setLrsj(new Date());
						fjgl.setLrr(sysUser.getUsername());
						khglQtzrrhzzllbService.save(fjgl);
					}
				}
			}
		 }catch (Exception e){
		 	log.error("保存农户附件失败", e);
			 return  Result.error("保存农户附件失败");
		 }
		 return Result.ok("保存成功");
	 }

	 @RequestMapping(value = "/deleteQtzrrhzfjImage",method = RequestMethod.POST)
	 public Result<?> deleteQtzrrhzfjImage(@RequestBody  List<KhglQtzrrhzzllb> jsonObject) {
		 try {
			 if (jsonObject!=null  && jsonObject.size()>0){
				 for (int i = 0; i < jsonObject.size(); i++) {
					 if (!StringUtils.isEmpty(jsonObject.get(i).getZlbh())){
						 UpdateWrapper<KhglQtzrrhzzllb> khglNhzllbPadUpdateWrapper=new UpdateWrapper<>();
						 khglNhzllbPadUpdateWrapper.eq("zlbh",jsonObject.get(i).getZlbh());
						 khglQtzrrhzzllbService.remove(khglNhzllbPadUpdateWrapper);
					 }
				 }
			 }
		 }catch (Exception e){
			 log.error("删除农户附件失败", e);
			 return  Result.error("删除农户附件失败");
		 }
		 return Result.ok("保存成功");
	 }




	/**
	 * 添加
	 *
	 * @param khglNhhzzllb
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhglQtzrrhzzllb khglNhhzzllb) {
		khglQtzrrhzzllbService.save(khglNhhzzllb);
		return Result.ok("添加成功！");
	}


	/**
	 * 编辑
	 *
	 * @param khglNhhzzllb
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhglQtzrrhzzllb khglNhhzzllb) {
		khglQtzrrhzzllbService.updateById(khglNhhzzllb);
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
		khglQtzrrhzzllbService.removeById(id);
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
		khglQtzrrhzzllbService.removeByIds(Arrays.asList(ids.split(",")));
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
		KhglQtzrrhzzllb khglQtzrrhzzllb = khglQtzrrhzzllbService.getById(id);
		return Result.ok(khglQtzrrhzzllb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khglQtzrrhzzllb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhglQtzrrhzzllb khglQtzrrhzzllb) {
      return super.exportXls(request, khglQtzrrhzzllb, KhglQtzrrhzzllb.class, "1");
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
      return super.importExcel(request, response, KhglQtzrrhzzllb.class);
  }

}

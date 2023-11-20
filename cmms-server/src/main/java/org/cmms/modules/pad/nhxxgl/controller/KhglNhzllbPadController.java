package org.cmms.modules.pad.nhxxgl.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Update;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhzllbPad;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhzllbPadService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.pad.pyxx.entity.Pyfjxx;
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
@RequestMapping("/KhglNhzllbPad/khglNhzllbPad")
public class KhglNhzllbPadController extends JeecgController<KhglNhzllbPad, IKhglNhzllbPadService> {
	@Autowired
	private IKhglNhzllbPadService khglNhzllbPadService;

	@Autowired
	INhxqService nhxqService;

	@Value(value = "${common.path.upload}")
	private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param khglNhzllbPad
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhglNhzllbPad khglNhzllbPad,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhglNhzllbPad> queryWrapper = QueryGenerator.initQueryWrapper(khglNhzllbPad, req.getParameterMap());
		Page<KhglNhzllbPad> page = new Page<KhglNhzllbPad>(pageNo, pageSize);
		IPage<KhglNhzllbPad> pageList = khglNhzllbPadService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @RequestMapping(value = "/queryNhGrFjxx",method = RequestMethod.GET)
	 public Result<?> queryNhGrFjxx(  @RequestParam(name="hhbm")String hhbm,@RequestParam(name="zllx")String zllx,@RequestParam(name="zjhm")String zjhm) {
		 try {
		 	Map<String,List<KhglNhzllbPad>> map=new HashMap<>();
			 QueryWrapper<KhglNhzllbPad> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm)
					 .eq("zjhm",zjhm)
					 .eq("zllx","1");
            List<KhglNhzllbPad> list = khglNhzllbPadService.list(fjxxQueryWrapper);
            if (list!=null && list.size()>0){
            	map.put("sfz",list);
//                return Result.ok(list);
            }
			 QueryWrapper<KhglNhzllbPad> JhzQueryWrapper=new QueryWrapper<>();
			 JhzQueryWrapper.eq("hhbm",hhbm)
					 .eq("zjhm",zjhm)
					 .eq("zllx","2");
			 List<KhglNhzllbPad> listJhz = khglNhzllbPadService.list(JhzQueryWrapper);
			 if (listJhz!=null && listJhz.size()>0){
				 map.put("jhz",listJhz);
//                return Result.ok(list);
			 }
			 if (map.size()>0){
				 return Result.ok(map);
			 }
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("查询成功");
	 }


	 @RequestMapping(value = "/saveNhGrfjImage",method = RequestMethod.POST)
	 public Result<?> saveNhGrfjImage(KhglNhzllbPad jsonObject) {
		 try {
			 if (StringUtils.isEmpty(jsonObject.getId())) {
				 KhglNhzllbPad fjgl=new KhglNhzllbPad();
				 fjgl.setQydm(jsonObject.getQydm());
				 fjgl.setHhbm(jsonObject.getHhbm());
				 fjgl.setZjhm(jsonObject.getZjhm());
				 fjgl.setZlbh(UUID.randomUUID().toString().substring(0,14));
				 //fjgl.setId(jsonObject.getId());
				 fjgl.setZllx(jsonObject.getZllx());
				 fjgl.setZldx(jsonObject.getZldx());
				 fjgl.setFwlj(jsonObject.getFwlj());
				 fjgl.setZlmc(jsonObject.getZlmc());
				 fjgl.setZllj(uploadpath+"/"+jsonObject.getFwlj());
				 khglNhzllbPadService.save(fjgl);
			 }

		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }

	 @RequestMapping(value = "/deleteNhGrfjImage",method = RequestMethod.POST)
	 public Result<?> deleteNhGrfjImage(KhglNhzllbPad jsonObject) {
		 try {
			 UpdateWrapper<KhglNhzllbPad> khglNhzllbPadUpdateWrapper=new UpdateWrapper<>();
			 khglNhzllbPadUpdateWrapper.eq("zlmc",jsonObject.getZlmc());
			 khglNhzllbPadService.remove(khglNhzllbPadUpdateWrapper);
		 }catch (Exception e){
			 return  Result.error(e.toString());
		 }
		 return Result.ok("添加成功");
	 }

	/**
	 * 添加
	 *
	 * @param khglNhzllbPad
	 * @return
	 */
	@AutoLog(value = "1-添加")
	@ApiOperation(value="1-添加", notes="1-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody KhglNhzllbPad khglNhzllbPad) {
		khglNhzllbPadService.save(khglNhzllbPad);
		return Result.ok("添加成功！");
	}

	 @PostMapping(value = "/addList")
	 public Result<?> addList(@RequestBody KhglNhzllbPad khglNhzllbPad) {
		if (org.apache.commons.lang3.StringUtils.isBlank(khglNhzllbPad.getZjhm())){
			return Result.error("农户id为空");
		}

		Nhxq byId = nhxqService.getById(khglNhzllbPad.getZjhm());
		if (byId == null)
			return Result.error("根据id查不到农户信息");

		QueryWrapper queryWrapper = new QueryWrapper();
		queryWrapper.eq("zjhm",byId.getZjhm());
		queryWrapper.in("zllx","22","23");
		service.remove(queryWrapper);

		//场所
		if (org.apache.commons.lang3.StringUtils.isNotBlank(khglNhzllbPad.getZllj())){
			String[] split = khglNhzllbPad.getZllj().split(",");
			for (int i = 0; i < split.length; i++) {
				KhglNhzllbPad save = new KhglNhzllbPad();
				save.setZjhm(byId.getZjhm());
				String s = IdUtil.fastSimpleUUID();
				save.setZllx("22");
				save.setScsj(new Date());
				save.setScr(getUsername());
				save.setZllj(uploadpath+split[i]);
				save.setFwlj(split[i]);
				save.setId(s);
				save.setZlbh(s);
				service.save(save);
			}
		}

		 if (org.apache.commons.lang3.StringUtils.isNotBlank(khglNhzllbPad.getFwlj())){
			 String[] split = khglNhzllbPad.getFwlj().split(",");
			 for (int i = 0; i < split.length; i++) {
				 KhglNhzllbPad save = new KhglNhzllbPad();
				 save.setZjhm(byId.getZjhm());
				 String s = IdUtil.fastSimpleUUID();
				 save.setZllx("23");
				 save.setScsj(new Date());
				 save.setScr(getUsername());
				 save.setZllj(uploadpath+split[i]);
				 save.setFwlj(split[i]);
				 save.setId(s);
				 save.setZlbh(s);
				 service.save(save);
			 }
		 }

		 return Result.ok("添加成功！");
	 }

	 @RequestMapping("/getByZllxAndNhId")
	 public Result<?> getByZllxAndNhId(String id,String zllx){
		 Nhxq byId = nhxqService.getById(id);
		 if (byId != null && org.apache.commons.lang3.StringUtils.isNotBlank(byId.getZjhm())){
			 QueryWrapper queryWrapper = new QueryWrapper();
			 queryWrapper.eq("zjhm",byId.getZjhm());
			 if (org.apache.commons.lang3.StringUtils.isNotBlank(zllx)){
				 if (zllx.contains(",")){
					 String[] split = zllx.split(",");
					 queryWrapper.in("zllx",split);
				 }else {
					 queryWrapper.eq("zllx",zllx);
				 }
			 }
			 List list = service.list(queryWrapper);
			 return Result.ok(list);
		 }
		return Result.error("");
	 }
	
	/**
	 * 编辑
	 *
	 * @param khglNhzllbPad
	 * @return
	 */
	@AutoLog(value = "1-编辑")
	@ApiOperation(value="1-编辑", notes="1-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody KhglNhzllbPad khglNhzllbPad) {
		khglNhzllbPadService.updateById(khglNhzllbPad);
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
		khglNhzllbPadService.removeById(id);
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
		this.khglNhzllbPadService.removeByIds(Arrays.asList(ids.split(",")));
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
		KhglNhzllbPad khglNhzllbPad = khglNhzllbPadService.getById(id);
		return Result.ok(khglNhzllbPad);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khglNhzllbPad
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhglNhzllbPad khglNhzllbPad) {
      return super.exportXls(request, khglNhzllbPad, KhglNhzllbPad.class, "1");
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
      return super.importExcel(request, response, KhglNhzllbPad.class);
  }


}

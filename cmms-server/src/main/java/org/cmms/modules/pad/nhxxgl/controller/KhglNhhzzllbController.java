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
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.enums.QydmEnums;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.pad.nhxxgl.entity.FjxxRecive;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.entity.KhglNhhzzllb;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzzllbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.entity.SysUser;
import org.cmms.modules.system.service.ISysUserService;
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
@RequestMapping("/KhglNhhzzllb/khglNhhzzllb")
public class KhglNhhzzllbController extends JeecgController<KhglNhhzzllb, IKhglNhhzzllbService> {
	@Autowired
	private IKhglNhhzzllbService khglNhhzzllbService;

	 @Autowired
	 private IVhrbasstaffpostService vhrbasstaffpostService;

	 @Autowired
	 private ISysUserService iSysUserService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	/**
	 * 分页列表查询
	 *
	 * @param khglNhhzzllb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhglNhhzzllb khglNhhzzllb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhglNhhzzllb> queryWrapper = QueryGenerator.initQueryWrapper(khglNhhzzllb, req.getParameterMap());
		Page<KhglNhhzzllb> page = new Page<KhglNhhzzllb>(pageNo, pageSize);
		IPage<KhglNhhzzllb> pageList = khglNhhzzllbService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @RequestMapping(value = "/queryNhhzFjxx",method = RequestMethod.GET)
	 public Result<?> queryNhhzFjxx(@RequestParam(name = "hhbm") String hhbm) {
		 try {
			 QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm);
			 List<KhglNhhzzllb> list = khglNhhzzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 for (KhglNhhzzllb khglNhhzzllb : list) {
				 	khglNhhzzllb.setZllj("");
				 }
              return Result.ok(list);
			 }
		/*	 Map<String,List<KhglNhhzzllb>> map=new HashMap<>();
			 QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm",hhbm)
					 .eq("zllx","1");
			 List<KhglNhhzzllb> list = khglNhhzzllbService.list(fjxxQueryWrapper);
			 if (list!=null && list.size()>0){
				 map.put("RhzppicList",list);
//                return Result.ok(list);
			 }
			 QueryWrapper<KhglNhhzzllb> JhzQueryWrapper=new QueryWrapper<>();
			 JhzQueryWrapper.eq("hhbm",hhbm)
					 .eq("zllx","2");
			 List<KhglNhhzzllb> listJhz = khglNhhzzllbService.list(JhzQueryWrapper);
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
			 QueryWrapper<KhglNhhzzllb> fjxxQueryWrapper=new QueryWrapper<>();
			 fjxxQueryWrapper.eq("hhbm", hhbm);
			 fjxxQueryWrapper.eq("zllx", zllx);
			 List<KhglNhhzzllb> list = khglNhhzzllbService.list(fjxxQueryWrapper);
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

	 @RequestMapping(value = "/saveNhhzfjImage",method = RequestMethod.POST)
	 public Result<?> saveNhhzfjImage(@RequestBody  List<KhglNhhzzllb>  jsonObject) {
		 try {
		 	if (jsonObject!=null  && jsonObject.size()>0){
				LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				for (int i = 0; i < jsonObject.size(); i++) {
					if (StringUtils.isEmpty(jsonObject.get(i).getId()) && StringUtils.isNotEmpty(jsonObject.get(i).getFwlj())) {
						KhglNhhzzllb fjgl = new KhglNhhzzllb();
						fjgl.setQydm(jsonObject.get(i).getQydm());
						fjgl.setHhbm(jsonObject.get(i).getHhbm());
						fjgl.setZlbh(IdUtil.simpleUUID());
						fjgl.setZllx(jsonObject.get(i).getZllx());
						fjgl.setZldx(jsonObject.get(i).getZldx());
						fjgl.setFwlj(jsonObject.get(i).getFwlj());
						fjgl.setZlmc(jsonObject.get(i).getZlmc());
						fjgl.setZllj(uploadpath + "/" + jsonObject.get(i).getFwlj());
						fjgl.setScsj(new Date());
						fjgl.setScr(sysUser.getUsername());
						fjgl.setLrsj(new Date());
						fjgl.setLrr(sysUser.getUsername());
						khglNhhzzllbService.save(fjgl);
					}
				}
			}
		 }catch (Exception e){
		 	log.error("保存农户附件失败", e);
			 return  Result.error("保存农户附件失败");
		 }
		 return Result.ok("保存成功");
	 }

	 @RequestMapping(value = "/deleteNhhzfjImage",method = RequestMethod.POST)
	 public Result<?> deleteNhhzfjImage(@RequestBody  List<KhglNhhzzllb> jsonObject) {
		 StringBuffer buffer=new StringBuffer();
		 try {
			 LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
			 boolean flag=false;
			 if (jsonObject!=null  && jsonObject.size()>0){
				 for (int i = 0; i < jsonObject.size(); i++) {
					 if (!StringUtils.isEmpty(jsonObject.get(i).getZlbh())){
						 UpdateWrapper<KhglNhhzzllb> khglNhzllbPadUpdateWrapper=new UpdateWrapper<>();
						 khglNhzllbPadUpdateWrapper.eq("zlbh",jsonObject.get(i).getZlbh());
						 KhglNhhzzllb one = khglNhhzzllbService.getOne(khglNhzllbPadUpdateWrapper);
						 if(getRedisQydm().equals(QydmEnums.QIYANG.getQydmCode())){
						 	 if(one != null) {
								 String lrr = one.getLrr();
								 Date lrsj = one.getLrsj();
								 SysUser userByName = iSysUserService.getUserByName(lrr);
								 Vhrbasstaffpost vhrbasstaffpost = vhrbasstaffpostService.selectYgByLrsj(userByName.getWorkNo(), DateUtil.getDateString(lrsj));
								 Vhrbasstaffpost vhrbasstaffpost1 = vhrbasstaffpostService.selectByYggh(loginUser.getWorkNo());
								 if (vhrbasstaffpost != null) {
									 if (!vhrbasstaffpost.getZzbz().equals(vhrbasstaffpost1.getZzbz())) {
										 buffer.append(one.getZlmc() + ",");
										 log.error("该附件为[" + vhrbasstaffpost.getZzjc() + "]上传,禁止删除！");
									 } else {
										 khglNhhzzllbService.remove(khglNhzllbPadUpdateWrapper);
									 }
								 }
							 }
						 }else{
							 khglNhhzzllbService.remove(khglNhzllbPadUpdateWrapper);
						 }
					 }
				 }

			 }
		 }catch (Exception e){
			 log.error("删除农户附件失败", e);
			 return  Result.error("删除农户附件失败");
		 }
		 if(buffer.toString().length()>0){
			 return Result.ok("删除成功,其中资料["+buffer+"]没有权限删除");
		 }else{
			 return Result.ok("删除成功");
		 }
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
	public Result<?> add(@RequestBody KhglNhhzzllb khglNhhzzllb) {
		khglNhhzzllbService.save(khglNhhzzllb);
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
	public Result<?> edit(@RequestBody KhglNhhzzllb khglNhhzzllb) {
		khglNhhzzllbService.updateById(khglNhhzzllb);
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
		khglNhhzzllbService.removeById(id);
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
		this.khglNhhzzllbService.removeByIds(Arrays.asList(ids.split(",")));
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
		KhglNhhzzllb khglNhhzzllb = khglNhhzzllbService.getById(id);
		return Result.ok(khglNhhzzllb);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param khglNhhzzllb
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, KhglNhhzzllb khglNhhzzllb) {
      return super.exportXls(request, khglNhhzzllb, KhglNhhzzllb.class, "1");
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
      return super.importExcel(request, response, KhglNhhzzllb.class);
  }

}

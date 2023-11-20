package org.cmms.modules.khxxgl.khflgl.shxq.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.UUIDGenerator;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.ShxqImportVo;
import org.cmms.modules.khxxgl.khflgl.shxq.service.IShxqService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.cmms.modules.khxxgl.khflgl.shxq.verify.ShxqImportVoVerify;
import org.cmms.modules.khxxgl.khjbzl.entity.Khjbzl;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Slf4j
@Api(tags="商户采集信息")
@RestController
@RequestMapping("/khflgl/shxq")
public class ShxqController extends JeecgController<Shxq, IShxqService> {
	 @Autowired
	 private IShxqService shcjxxService;

	 @Value(value = "${common.path.upload}")
	 private String uploadpath;
	 @Autowired
	 private IKhjbzlService khjbzlService;
	 @Autowired
	 private IHrBasOrganizationService hrBasOrganizationService;
	 @Autowired
	 ShxqImportVoVerify shxqImportVoVerify;
	/**
	 * 分页列表查询
	 *
	 * @param shcjxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-分页列表查询")
	@ApiOperation(value="商户户采集信息-分页列表查询", notes="商户户采集信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Shxq shcjxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Shxq> queryWrapper = QueryGenerator.initQueryWrapper(shcjxx, req.getParameterMap());
		Page<Shxq> page = new Page<Shxq>(pageNo, pageSize);
		IPage<Shxq> pageList = shcjxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param shcjxx
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-添加")
	@ApiOperation(value="商户户采集信息-添加", notes="商户户采集信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Shxq shcjxx) {

		QueryWrapper queryWrapperZzbz =new QueryWrapper();
		queryWrapperZzbz.eq("ywjgdm",shcjxx.getJgdm());
		HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
		shcjxx.setSszh(hrBasOrganization.getZzbz());
		shcjxxService.save(shcjxx);
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		QueryWrapper<Khjbzl> queryWrapper =new QueryWrapper();
		queryWrapper.eq("zjhm",shcjxx.getTyshxydm());
		Khjbzl khjbzl=khjbzlService.getOne(queryWrapper);
		if(khjbzl!=null){
			if(khjbzl.getKhxz()==null||khjbzl.getKhxz().isEmpty()){
				khjbzl.setKhxz("2");
			}else{
				Boolean sfysh=false;
				String[] split = khjbzl.getKhxz().split(",");
				for(String khxz:split){
					if(khxz.equals("2")){
						sfysh=true;
					};
				}
				if(!sfysh){
					khjbzl.setKhxz(khjbzl.getKhxz()+",2");
				}
			}
			if(StringUtils.isEmpty(khjbzl.getWgbh())){
				khjbzl.setWgbh(shcjxx.getWgbh());
			}
			if(StringUtils.isEmpty(khjbzl.getJgdm())){
				khjbzl.setJgdm(shcjxx.getJgdm());
			}
			if (StringUtils.isEmpty(khjbzl.getKhlx())) {
				khjbzl.setKhlx("2");
			}
			if(StringUtils.isEmpty(khjbzl.getLxfs())){
				khjbzl.setLxfs(shcjxx.getLxfs());
			}
			if(StringUtils.isEmpty(khjbzl.getDz())){
				khjbzl.setDz(shcjxx.getDz());
			}
			khjbzlService.update(khjbzl,queryWrapper);

		}else{
			Khjbzl khjbzlSave =new Khjbzl();
			khjbzlSave.setWgbh(shcjxx.getWgbh());
			khjbzlSave.setJgdm(shcjxx.getJgdm());
			khjbzlSave.setKhmc(shcjxx.getShmc());
			khjbzlSave.setZjlx("45");
			khjbzlSave.setZjhm(shcjxx.getTyshxydm());
			khjbzlSave.setLxfs(shcjxx.getLxfs());
			khjbzlSave.setDz(shcjxx.getDz());
			khjbzlSave.setKhxz("2");
			khjbzlSave.setKhlx("2");
			khjbzlSave.setKhlb("2");
			khjbzlSave.setDabh(UUIDGenerator.generate());
			khjbzlSave.setCreateTime(new Date());
			khjbzlSave.setCreateBy(sysUser.getUsername());
			khjbzlService.save(khjbzlSave);
		}

		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param shcjxx
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-编辑")
	@ApiOperation(value="商户户采集信息-编辑", notes="商户户采集信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Shxq shcjxx) {

		QueryWrapper queryWrapperZzbz =new QueryWrapper();
		queryWrapperZzbz.eq("ywjgdm",shcjxx.getJgdm());
		HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
		shcjxx.setSszh(hrBasOrganization.getZzbz());

		shcjxxService.updateById(shcjxx);


		QueryWrapper<Khjbzl> queryWrapper =new QueryWrapper();
		queryWrapper.eq("zjhm",shcjxx.getTyshxydm());
		Khjbzl khjbzl=khjbzlService.getOne(queryWrapper);
		if(khjbzl!=null) {
			if (StringUtils.isEmpty(khjbzl.getWgbh())) {
				khjbzl.setWgbh(shcjxx.getWgbh());
			}
			if (StringUtils.isEmpty(khjbzl.getJgdm())) {
				khjbzl.setJgdm(shcjxx.getJgdm());
			}
			if (StringUtils.isEmpty(khjbzl.getKhlx())) {
				khjbzl.setKhlx("2");
			}
			if (StringUtils.isEmpty(khjbzl.getLxfs())) {
				khjbzl.setLxfs(khjbzl.getLxfs());
			}
			if (StringUtils.isEmpty(khjbzl.getDz())) {
				khjbzl.setDz(khjbzl.getDz());
			}

			khjbzlService.update(khjbzl, queryWrapper);
		}

		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-通过id删除")
	@ApiOperation(value="商户户采集信息-通过id删除", notes="商户户采集信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		shcjxxService.removeById(id);
		return Result.ok("删除成功!");
	}

	 /**
	  * 提取
	  */
	 @RequestMapping(value = "/extract")
	 public Result<?> extract() {
		 Result result = new Result<>();
		 try {
			 shcjxxService.init();
			 result.setSuccess(true);
			 return result;
		 } catch (Exception e) {
			 System.out.println(e);
			 log.error("提取失败", e.getMessage());
			 result.setSuccess(false);
		 }
		 return result;
	 }
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-批量删除")
	@ApiOperation(value="商户户采集信息-批量删除", notes="商户户采集信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.shcjxxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "商户户采集信息-通过id查询")
	@ApiOperation(value="商户户采集信息-通过id查询", notes="商户户采集信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Shxq shcjxx = shcjxxService.getById(id);
		return Result.ok(shcjxx);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param shcjxx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, Shxq shcjxx) {
      return super.exportXls(request, shcjxx, Shxq.class, "商户户采集信息");
  }

	 /**
	  * 通过excel导入数据
	  * @param request
	  * @param response
	  * @return
	  */
	 @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	 public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		 String filePaths = jsonObject.getString("filePaths");
		 if (StringUtils.isEmpty(filePaths)) {
			 return Result.error("请先上传文件！");
		 }
		 String[] filePathList = filePaths.split(",");
		 JSONObject obj = new JSONObject();
		 for (String filePath : filePathList) {
			 String baseFilePath = uploadpath + File.separator + filePath;
			 File file = new File(baseFilePath);
			 ImportParams params = new ImportParams();
			 params.setTitleRows(1);
			 params.setHeadRows(1);
			 params.setNeedSave(false);
			 params.setVerifyHanlder(shxqImportVoVerify);
			 FileOutputStream fos = null;
			 try {
				 ExcelImportResult<ShxqImportVo> importResult = ExcelImportUtil.importExcelVerify(file, ShxqImportVo.class, params);
				 List<ShxqImportVo> list = importResult.getList();
				 List<Shxq> shxqList = new ArrayList<>();
				 //todo 简单修改了
				 if (CollUtil.isNotEmpty(list)){
					 for (int i = 0; i < list.size(); i++) {
						 ShxqImportVo shxqImportVo = list.get(i);
						 System.out.println(shxqImportVo);

						 Shxq shxq = new Shxq();
						 BeanUtils.copyProperties(shxqImportVo, shxq);
						 QueryWrapper queryWrapperZzbz =new QueryWrapper();
						 queryWrapperZzbz.eq("ywjgdm",shxq.getJgdm());
						 HrBasOrganization hrBasOrganization = hrBasOrganizationService.getOne(queryWrapperZzbz);
						 if (hrBasOrganization != null){
							 shxq.setSszh(hrBasOrganization.getZzbz());
						 }
						 System.out.println(shxq);
						 shxqList.add(shxq);
					 }
				 }
				 if (CollUtil.isNotEmpty(shxqList)){
					 System.out.println(shxqList.size());
					 shcjxxService.saveBatch(shxqList);
				 }
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
				 IoUtil.close(fos);
			 }
		 }
		 return Result.ok("文件导入失败！");
	 }

	 /**
	  * 导出Excel模板
	  * @return
	  */
	 @RequestMapping(value = "/exportTemplateXls")
	 public ModelAndView exportTemplateXls() {
		 return super.exportTemplateXls(ShxqImportVo.class, "商户信息导入模板");
	 }
}

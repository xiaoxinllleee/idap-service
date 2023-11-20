package org.cmms.modules.word.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.K;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.word.entity.*;
import org.cmms.modules.word.service.ICamsZcsxWordinfoService;
import org.cmms.modules.word.service.IWordService;
import org.cmms.modules.xdgl.grdkgl.entity.Dydb;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkJtspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 文档补录信息
 * @Author: jeecg-boot
 * @Date:   2020-08-07
 * @Version: V1.0
 */
@Slf4j
@Api(tags="文档补录信息")
@RestController
@RequestMapping("/wordinfo/camsZcsxWordinfo")
public class CamsZcsxWordinfoController extends JeecgController<CamsZcsxWordinfo, ICamsZcsxWordinfoService> {
	@Autowired
	IWordService iWordService;

	@Autowired
	IGrdkJtspService iGrdkJtspService;


	 @Value(value = "${common.path.kkview}")
	 private String kkview;


	 @Value(value = "${common.path.pdfjs}")
	 private String pdfjs;

	/**
	 * 分页列表查询
	 *
	 * @param camsZcsxWordinfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文档补录信息-分页列表查询")
	@ApiOperation(value="文档补录信息-分页列表查询", notes="文档补录信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(CamsZcsxWordinfo camsZcsxWordinfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<CamsZcsxWordinfo> queryWrapper = QueryGenerator.initQueryWrapper(camsZcsxWordinfo, req.getParameterMap());
		Page<CamsZcsxWordinfo> page = new Page<CamsZcsxWordinfo>(pageNo, pageSize);
		IPage<CamsZcsxWordinfo> pageList = service.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param camsZcsxWordinfo
	 * @return
	 */
	@AutoLog(value = "文档补录信息-添加")
	@ApiOperation(value="文档补录信息-添加", notes="文档补录信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody CamsZcsxWordinfo camsZcsxWordinfo) {
		service.save(camsZcsxWordinfo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param camsZcsxWordinfo
	 * @return
	 */
	@AutoLog(value = "文档补录信息-编辑")
	@ApiOperation(value="文档补录信息-编辑", notes="文档补录信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody CamsZcsxWordinfo camsZcsxWordinfo) {
		//如果是新增

		camsZcsxWordinfo.setUpdateTime(new Date());
		if (camsZcsxWordinfo != null){
			if (StringUtils.isBlank(camsZcsxWordinfo.getId())){
			camsZcsxWordinfo.setId(IdUtil.simpleUUID());
			service.save(camsZcsxWordinfo);
			}
		}
		service.updateById(camsZcsxWordinfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文档补录信息-通过id删除")
	@ApiOperation(value="文档补录信息-通过id删除", notes="文档补录信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文档补录信息-批量删除")
	@ApiOperation(value="文档补录信息-批量删除", notes="文档补录信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文档补录信息-通过id查询")
	@ApiOperation(value="文档补录信息-通过id查询", notes="文档补录信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		CamsZcsxWordinfo camsZcsxWordinfo = service.getById(id);
		return Result.ok(camsZcsxWordinfo);
	}

	 /**
	  * 通过zjhm查询
	  *
	  * @param zjhm
	  * @return
	  */
	 @AutoLog(value = "文档补录信息-通过zjhm查询")
	 @ApiOperation(value="文档补录信息-通过zjhm查询", notes="文档补录信息-通过zjhm查询")
	 @GetMapping(value = "/queryByZjhm")
	 public Result<?> queryByZjhm(@RequestParam(name="zjhm",required=true) String zjhm,
								  @RequestParam(name="wordType") String wordType) {
	 	QueryWrapper queryWrapper = new QueryWrapper();
	 	queryWrapper.eq("ZJHM",zjhm);
	 	if (StringUtils.isBlank(wordType))
	 		wordType = "nhxe";
	 	queryWrapper.eq("WORD_TYPE",wordType);
	 	CamsZcsxWordinfo camsZcsxWordinfo = service.getOne(queryWrapper);
	 	if (camsZcsxWordinfo == null){
	 		camsZcsxWordinfo = new CamsZcsxWordinfo();
	 		camsZcsxWordinfo.setZjhm(zjhm);
		}
	 	return Result.ok(camsZcsxWordinfo);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param camsZcsxWordinfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, CamsZcsxWordinfo camsZcsxWordinfo) {
      return super.exportXls(request, camsZcsxWordinfo, CamsZcsxWordinfo.class, "文档补录信息");
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
      return super.importExcel(request, response, CamsZcsxWordinfo.class);
  }


  @RequestMapping("/grdkWord")
  public Result<?> generate(@RequestParam(name="id",required=true) String id){
	if (StringUtils.isBlank(id))
		return Result.error("参数不能为空");
  	//如果审批完成  就不生成文档了
	String result = iWordService.grdkWord(id);
	if (StringUtils.isNotBlank(result))
		return Result.ok(result);
	return Result.error("生成失败");
  }


	 @RequestMapping("/copyPdf")
	 public Result<?> copyPdf(@RequestParam(name="name",required=true) String name){
		 if (StringUtils.isBlank(name))
			 return Result.error("参数不能为空");
		 /*String kkviewPath=kkview+name+"grdk.pdf";
		 String pdfjsPath=pdfjs+name+".pdf";
		 try {
		 	 Thread.sleep(30000);
			 FileUtils.copyFile(new File(kkviewPath), new File(pdfjsPath));
			 return Result.ok();
		 }catch (Exception e) {
			 e.printStackTrace();
			 return Result.error("复制失败");
		 }*/
		 return Result.ok();
	 }



	 @RequestMapping("/grdkWordSx")
	 public Result<?> generatesx(@RequestParam(name="id",required=true) String id){
		 if (StringUtils.isBlank(id))
			 return Result.error("参数不能为空");
		 //如果审批完成  就不生成文档了
		 String result = iWordService.grdkWordSx(id);
		 if (StringUtils.isNotBlank(result))
			 return Result.ok(result);
		 return Result.error("生成失败");


	 }

	 @RequestMapping("/xendfujian")
	 public Result<?> xendfujian(@RequestParam(name="zjhm",required=true) String zjhm,
							   @RequestParam(name="hhbm") String hhbm){
		 log.info("zjhm={}",zjhm);
		 log.info("hhbm={}",hhbm);
		 String result = iWordService.xendFjWord(zjhm,hhbm);
		 if (StringUtils.isNotBlank(result))
			 return Result.ok(result);
		 return Result.error("生成失败");

	}

	 @RequestMapping("/grdkfujian")
	 public Result<?> grdkfujian(@RequestParam(name="zjhm",required=true) String zjhm,
								 @RequestParam(name="hhbm") String hhbm){
		 log.info("zjhm={}",zjhm);
		 log.info("hhbm={}",hhbm);
		 String result = iWordService.grdkFjWord(zjhm,hhbm);
		 if (StringUtils.isNotBlank(result))
			 return Result.ok(result);
		 return Result.error("生成失败");

	 }


	 @RequestMapping("/grdkMt")
	 public Result<?> grdkMt(@RequestBody GrdkMtDTO grdkMtDTO){
		 log.info("grdkMtDTO={}",grdkMtDTO.toString());
		 String fileName = iWordService.grdkMtWord(grdkMtDTO);
		 return Result.ok(fileName);
	 }

	 @RequestMapping("/grdkGt")
	 public Result<?> grdkGt(@RequestBody GrdkGtDTO grdkGtDTO){
		 log.info("grdkMtDTO={}",grdkGtDTO.toString());
		 String fileName = iWordService.grdkGtWord(grdkGtDTO);
		 return Result.ok(fileName);
	 }

	 @RequestMapping("/grdkDydb")
	 public Result<?> grdkDydb(@RequestBody GrdkdyDTO grdkdyDTO){
		 log.info("grdkMtDTO======{}",grdkdyDTO.toString());
		 if (grdkdyDTO.getDydbs() == null || grdkdyDTO.getDydbs().size() == 0)
		 	return Result.error("请填写抵押物");
		 String result = iWordService.grdkDyWord(grdkdyDTO);
		 return Result.ok(result);
	 }

	 @RequestMapping("/zxbg")
	 public Result<?> zxbg(@RequestBody ZxbgDTO zxbgDTO){
		 log.info("zxbgDTO======{}",zxbgDTO.toString());
		 String s = iWordService.ZxbgWord(zxbgDTO);
		 return Result.ok(s);
	 }

	 @RequestMapping("/jtspWord")
	 public Result<?> jtspWord(@RequestParam(name="id",required=true) String id){
		 log.info("jtspWord id---={}",id);
		 String s = iWordService.jtspWord(id);
		 return Result.ok(s);
	 }
}

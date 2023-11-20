package org.cmms.modules.khgl.jhsh.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.checkerframework.checker.units.qual.A;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.jhsh.service.ITgacsTpsMchntInfoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.xjjl.entity.AppJhshXjjl;
import org.cmms.modules.khgl.xjjl.service.IAppJhshXjjlService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 聚合商户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Slf4j
@Api(tags="聚合商户信息")
@RestController
@RequestMapping("/jhsh/tgacsTpsMchntInfo")
public class TgacsTpsMchntInfoController extends JeecgController<TgacsTpsMchntInfo, ITgacsTpsMchntInfoService> {
	@Autowired
	private ITgacsTpsMchntInfoService tgacsTpsMchntInfoService;
	@Autowired
	private IAppJhshXjjlService appJhshXjjlService;

	private Integer flag;
	
	/**
	 * 分页列表查询
	 *
	 * @param tgacsTpsMchntInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-分页列表查询")
	@ApiOperation(value="聚合商户信息-分页列表查询", notes="聚合商户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TgacsTpsMchntInfo tgacsTpsMchntInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   Integer flag,String namecn,
								   HttpServletRequest req) {
		Page<TgacsTpsMchntInfo> page = new Page<TgacsTpsMchntInfo>(pageNo, pageSize);
		QueryWrapper<TgacsTpsMchntInfo> queryWrapper = new QueryWrapper<>();
		if (1 == flag){
			queryWrapper.eq("mcc",getRealname());
		}
		if (StringUtils.isNotBlank(namecn)){
			queryWrapper.like("simp_mchnt_name",namecn);
		}
		IPage<TgacsTpsMchntInfo> pageList = tgacsTpsMchntInfoService.page(page, queryWrapper);
		return Result.ok(pageList);
//		}else {
//			String ks ="20220225";
//			String js ="20220325";
//			IPage<TgacsTpsMchntInfo> getxj = service.getxj(page, ks, js);
//			return Result.ok(getxj);
//		}
	}


	 @GetMapping(value = "/xjgl")
	 public Result<?> xjgl(TgacsTpsMchntInfo tgacsTpsMchntInfo,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									Integer flag,String namecn,
									HttpServletRequest req) {
		 System.out.println(flag);
		 Page<TgacsTpsMchntInfo> page = new Page<TgacsTpsMchntInfo>(pageNo, pageSize);
		 QueryWrapper<TgacsTpsMchntInfo> queryWrapper = new QueryWrapper<>();
		 if (StringUtils.isNotBlank(namecn)){
			 queryWrapper.like("simp_mchnt_name",namecn);
		 }
		 //todo  查表 int
		 IPage<TgacsTpsMchntInfo> pageList = null;
		 if (1 == flag){
			 DateTime dateTime = DateUtil.offsetDay(new Date(), -30);
			 String yyyyMMdd = DateUtil.format(dateTime, "yyyyMMdd");
			 String yyyyMMdd2 = DateUtil.format(new Date(), "yyyyMMdd");
			 pageList = service.getxj(page, yyyyMMdd, yyyyMMdd2);
		 }else {
			 pageList =  tgacsTpsMchntInfoService.page(page, queryWrapper);

		 }

		 List<TgacsTpsMchntInfo> records = pageList.getRecords();
		 if (CollUtil.isNotEmpty(records)){
			 for (int i = 0; i < records.size(); i++) {
				 TgacsTpsMchntInfo tgacsTpsMchntInfo1 = records.get(i);
				 LambdaQueryWrapper<AppJhshXjjl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
				 lambdaQueryWrapper.eq(AppJhshXjjl::getMchntId,tgacsTpsMchntInfo1.getMchntId());
				 DateTime dateTime = DateUtil.offsetDay(new Date(), -30);
				 lambdaQueryWrapper.between(AppJhshXjjl::getXjsj,dateTime,new Date());
				 long count = appJhshXjjlService.count(lambdaQueryWrapper);
				 if (count > 0){
					 records.get(i).setSfxj("1");
				 }
			 }
		 }
		 return Result.ok(pageList);
	 }

	 /**
	  * 待巡检
	  */
	 @GetMapping(value = "dxjkh")
	 public Result<?> dxjkh(@Param("mchntSt")String shzt){
		 QueryWrapper<TgacsTpsMchntInfo> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("mchntSt",shzt);
		 List<TgacsTpsMchntInfo> list = tgacsTpsMchntInfoService.list(queryWrapper);
		 return Result.ok(list);
	 }




	 /**
	  * 我的客户
	  */
	 @GetMapping(value = "/mycustomer")
	 public Result<?> mycustomer(String khjl){
		 //LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 //getUsername()
		 QueryWrapper<TgacsTpsMchntInfo> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("mcc",khjl);
		 List<TgacsTpsMchntInfo> list = tgacsTpsMchntInfoService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 添加
	 *
	 * @param tgacsTpsMchntInfo
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-添加")
	@ApiOperation(value="聚合商户信息-添加", notes="聚合商户信息-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TgacsTpsMchntInfo tgacsTpsMchntInfo) {
		tgacsTpsMchntInfoService.save(tgacsTpsMchntInfo);
		return Result.ok("添加成功！");
	}


	/**
	 * 编辑
	 *
	 * @param tgacsTpsMchntInfo
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-编辑")
	@ApiOperation(value="聚合商户信息-编辑", notes="聚合商户信息-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TgacsTpsMchntInfo tgacsTpsMchntInfo) {
		tgacsTpsMchntInfoService.updateById(tgacsTpsMchntInfo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-通过id删除")
	@ApiOperation(value="聚合商户信息-通过id删除", notes="聚合商户信息-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tgacsTpsMchntInfoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-批量删除")
	@ApiOperation(value="聚合商户信息-批量删除", notes="聚合商户信息-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tgacsTpsMchntInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "聚合商户信息-通过id查询")
	@ApiOperation(value="聚合商户信息-通过id查询", notes="聚合商户信息-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TgacsTpsMchntInfo tgacsTpsMchntInfo = tgacsTpsMchntInfoService.getById(id);
		return Result.ok(tgacsTpsMchntInfo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param tgacsTpsMchntInfo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, TgacsTpsMchntInfo tgacsTpsMchntInfo) {
      return super.exportXls(request, tgacsTpsMchntInfo, TgacsTpsMchntInfo.class, "聚合商户信息");
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
      return super.importExcel(request, response, TgacsTpsMchntInfo.class);
  }


	 @GetMapping(value = "/queryByNhZjhm")
	 public Result<?> queryByNhId(@RequestParam(name="zjhm",required=true) String zjhm) {
		LambdaQueryWrapper<TgacsTpsMchntInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(TgacsTpsMchntInfo::getLegalPersonId,zjhm);
		List<TgacsTpsMchntInfo> list = service.list(lambdaQueryWrapper);
		return Result.ok(list);
	 }
}

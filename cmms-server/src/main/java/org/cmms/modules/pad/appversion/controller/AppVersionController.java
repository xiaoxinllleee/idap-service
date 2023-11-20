package org.cmms.modules.pad.appversion.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.modules.appbase.checkupdate.entity.AppVersionInfo;
import org.cmms.modules.pad.appversion.entity.AppVersion;
import org.cmms.modules.pad.appversion.service.IAppVersionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: App版本更新
 * @Author: jeecg-boot
 * @Date:   2020-07-25
 * @Version: V1.0
 */
@Slf4j
@Api(tags="App版本更新")
@RestController
@RequestMapping("/appVersion")
public class AppVersionController extends JeecgController<AppVersion, IAppVersionService> {
	@Autowired
	private IAppVersionService appVersionService;

	/**
	 * 分页列表查询
	 *
	 * @param appVersion
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "App版本更新-分页列表查询")
	@ApiOperation(value="App版本更新-分页列表查询", notes="App版本更新-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppVersion appVersion,
                                   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                   HttpServletRequest req) {
		QueryWrapper<AppVersion> queryWrapper = QueryGenerator.initQueryWrapper(appVersion, req.getParameterMap());
		Page<AppVersion> page = new Page<AppVersion>(pageNo, pageSize);
		IPage<AppVersion> pageList = appVersionService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	/**
	 * 添加
	 *
	 * @param appVersion
	 * @return
	 */
	@AutoLog(value = "App版本更新-添加")
	@ApiOperation(value="App版本更新-添加", notes="App版本更新-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppVersion appVersion) {
        appVersionService.save(appVersion);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appVersion
	 * @return
	 */
	@AutoLog(value = "App版本更新-编辑")
	@ApiOperation(value="App版本更新-编辑", notes="App版本更新-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppVersion appVersion) {
        appVersionService.updateById(appVersion);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "App版本更新-通过id删除")
	@ApiOperation(value="App版本更新-通过id删除", notes="App版本更新-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        appVersionService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "App版本更新-批量删除")
	@ApiOperation(value="App版本更新-批量删除", notes="App版本更新-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appVersionService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "App版本更新-通过id查询")
	@ApiOperation(value="App版本更新-通过id查询", notes="App版本更新-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {

		AppVersion appVersion = appVersionService.getById(id);
		return Result.ok(appVersion);
	}

	 /**
	  * 查询最新版本
	  *
	  * @return
	  */
	 @AutoLog(value = "App版本更新-查询最新版本")
	 @ApiOperation(value="App版本更新-查询最新版本", notes="App版本更新-查询最新版本")
	 @GetMapping(value = "/queryLatest")
	 public Result<?> queryLatestVersion() {
		 AppVersion appVersion = appVersionService.queryLatestVersion();
		 return Result.ok(appVersion);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param appVersion
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppVersion appVersion) {
      return super.exportXls(request, appVersion, AppVersion.class, "App版本更新");
  }

  /**
   *
   *
   * @param request
   * @param
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request) {
	  int version = 1;
	  LambdaQueryWrapper<AppVersion> lambdaQueryWrapper = new LambdaQueryWrapper<>();
	  lambdaQueryWrapper.orderByDesc(AppVersion::getCreateTime);
	  List<AppVersion> list = service.list(lambdaQueryWrapper);

	  if (CollUtil.isNotEmpty(list)) {
		  AppVersion appVersion = list.get(0);
		  //更新版本号
		  if (Integer.valueOf(appVersion.getBbh()) != null) {
			  version = Integer.valueOf(appVersion.getBbh()) + 1;
		  }
	  }

	  LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
	  MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;//文件传输
	  Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	  for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
		  MultipartFile file = entity.getValue();// 获取上传文件对象
		  String path = uploadpath+ File.separator+file.getOriginalFilename();
		  try {
		  	//写入磁盘中
			  file.transferTo(new File(path));
		  }catch (Exception e){
			  e.printStackTrace();
		  }

		  AppVersion appVersion = new AppVersion();
		  appVersion.setCreateBy(getWorkNo());
		  appVersion.setCreateTime(new Date());
		  appVersion.setBbh(String.valueOf(version));
		  appVersion.setWjdx(new BigDecimal(file.getSize()));
		  appVersion.setWjlj(path);
		  appVersion.setFwlj(file.getOriginalFilename());
		  service.save(appVersion);

	  }
	  return Result.ok();
  }

}

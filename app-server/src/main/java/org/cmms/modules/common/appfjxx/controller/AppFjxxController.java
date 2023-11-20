package org.cmms.modules.common.appfjxx.controller;

import java.io.*;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.common.appfjxx.entity.AppFjxx;
import org.cmms.modules.common.appfjxx.service.IAppFjxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: app附件管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="app附件管理")
@RestController
@RequestMapping("/appfjxx/appFixx")
public class AppFjxxController extends JeecgController<AppFjxx, IAppFjxxService> {
	@Autowired
	private IAppFjxxService appFixxService;
	 @Value(value = "${common.path.upload}")
	 private String uploadpath;

	 private enum AllowedImageType {
		 PNG("89504E47", "png"), JPG("FFD8FF", "jpg"),
		 GIF("47494638", "gif"), BMP("424D", "bmp");
		 private String code;
		 private String type;

		 AllowedImageType(String code, String type) {
			 this.code = code;
			 this.type = type;
		 }
	 }

	 private enum AllowedImageExt {
		 PNG("png"), JPG("jpg"),GIF("gif"), BMP("bmp");
		 private String ext;

		 AllowedImageExt(String ext) {
			 this.ext = ext;
		 }
	 }

	/**
	 * 分页列表查询
	 *
	 * @param appFixx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "app附件管理-分页列表查询")
	@ApiOperation(value="app附件管理-分页列表查询", notes="app附件管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppFjxx appFixx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppFjxx> queryWrapper = QueryGenerator.initQueryWrapper(appFixx, req.getParameterMap());
		Page<AppFjxx> page = new Page<AppFjxx>(pageNo, pageSize);
		IPage<AppFjxx> pageList = appFixxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	 @PostMapping(value = "/app/upload")
	 public Result<?> Appupload(HttpServletRequest request, HttpServletResponse response) {
		 Result<?> result = new Result<>();
		 LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		 String realname = sysUser.getUsername();
		 String appUploadpath=uploadpath+"/app/"+realname+"/";
		 try {
			 StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
			 Iterator<String> iterator = req.getFileNames();
			 File folder = new File(appUploadpath);
			 if(!folder.exists()){
				 folder.mkdirs();
			 }
			 List<String> filePaths = new ArrayList<>();
			 while (iterator.hasNext()) {
				 MultipartFile multipartFile = req.getFile(iterator.next());
				 String originalFirstName = multipartFile.getOriginalFilename();
				 String extName = FileUtil.extName(originalFirstName);
				 String fileType = IoUtil.readHex28Upper(multipartFile.getInputStream());
				 boolean allowed = false;
				 for (AllowedImageExt item : AllowedImageExt.values()) {
					 if (!StringUtils.isEmpty(extName) && extName.equalsIgnoreCase(item.ext)) {
						 allowed = true;
						 break;
					 }
				 }
				 if (!allowed) {
					 log.info("非法的文件上传扩展名：" + extName);
					 return Result.error("非法文件上传");
				 }
				 allowed = false;
				 for (AllowedImageType item : AllowedImageType.values()) {
					 if (!StringUtils.isEmpty(fileType) && fileType.toUpperCase().startsWith(item.code)) {
						 allowed = true;
						 break;
					 }
				 }
				 if (!allowed) {
					 log.info("非法的文件上传类型：" + fileType);
					 return Result.error("非法文件上传");
				 }

				 String uuid = UUID.randomUUID().toString().replace("-", "");
				 String kzm = originalFirstName.substring(originalFirstName.lastIndexOf("."));
				 String filename = uuid + kzm;
				 File file = new File(appUploadpath + filename);
				 //真正写到磁盘上
				 OutputStream out = new FileOutputStream(file);
				 out.write(multipartFile.getBytes());
				 out.close();
				 String serverName = request.getServerName();
				 String path=request.getScheme() + "://" +
						   "idap:"
						 + request.getServerPort()+"/app/"+ realname+"/"+filename;
				 System.out.println(appUploadpath +filename);
     			 filePaths.add(path);
				 Map<String, Object> map = new HashMap<>();
				 map.put("data", filePaths);
				 return  Result.ok("请求成功",map);
			 }
		 } catch (Exception e) {
			 log.error("error:{}", e.getMessage(), e);
			 result.setCode(500);
			 result.setMessage("服务请求失败");
		 }
		 return result;
	 }

	 @AutoLog(value = "app附件管理-通过id删除")
	 @ApiOperation(value="app附件管理-通过id删除", notes="app附件管理-通过id删除")
	 @GetMapping(value = "/app/delete")
	 public Result<?> appDelete(@RequestParam(name="path",required=true) String path) {
		 File file = new File(path);
		  if (!file.exists()){
		  	return Result.error("文件路径不存在");
		  }
		  file.delete();
		 return Result.ok("删除成功!");
	 }
	/**
	 * 添加
	 *
	 * @param appFixx
	 * @return
	 */
	@AutoLog(value = "app附件管理-添加")
	@ApiOperation(value="app附件管理-添加", notes="app附件管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody AppFjxx appFixx) {
		appFixxService.save(appFixx);
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 *
	 * @param appFixx
	 * @return/ygrggl/hrBasStaffPost
	 */
	@AutoLog(value = "app附件管理-编辑")
	@ApiOperation(value="app附件管理-编辑", notes="app附件管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody AppFjxx appFixx) {
		appFixxService.updateById(appFixx);
		return Result.ok("编辑成功!");
	}

	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app附件管理-通过id删除")
	@ApiOperation(value="app附件管理-通过id删除", notes="app附件管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		appFixxService.removeById(id);
		return Result.ok("删除成功!");
	}

	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "app附件管理-批量删除")
	@ApiOperation(value="app附件管理-批量删除", notes="app附件管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.appFixxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "app附件管理-通过id查询")
	@ApiOperation(value="app附件管理-通过id查询", notes="app附件管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		AppFjxx appFixx = appFixxService.getById(id);
		return Result.ok(appFixx);
	}

	 @GetMapping(value = "/queryListById")
	 public Result<?> queryListById(@RequestParam(name="id",required=true) String id) {
		 QueryWrapper queryWrapper = new QueryWrapper();
		 queryWrapper.eq("id",id);
		 List list = service.list(queryWrapper);
		 return Result.ok(list);
	 }

  /**
   * 导出excel
   *
   * @param request
   * @param appFixx
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, AppFjxx appFixx) {
      return super.exportXls(request, appFixx, AppFjxx.class, "app附件管理");
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
      return super.importExcel(request, response, AppFjxx.class);
  }

}

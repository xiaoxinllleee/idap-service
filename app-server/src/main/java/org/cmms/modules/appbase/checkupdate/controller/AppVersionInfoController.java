package org.cmms.modules.appbase.checkupdate.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.appbase.checkupdate.entity.AppVersionInfo;
import org.cmms.modules.appbase.checkupdate.service.IAppVersionInfoService;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.khgl.ckkh.entity.AppCkkhGzList;
import org.cmms.modules.khgl.ckkh.entity.KhgxglCkzhghlsb;
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
 * @Description: APP版本信息
 * @Author: jeecg-boot
 * @Date: 2022-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "APP版本信息" )
@RestController
@RequestMapping("/app/appVersionInfo" )
public class AppVersionInfoController extends JeecgController<AppVersionInfo, IAppVersionInfoService> {


    @RequestMapping("/checkupdate" )
    public Result<?> checkupdate(String appid, String version, String imei, Integer versionCode) {
		log.info("=== APP检查更新 appid {} ,version {},imei {},versionCode {}",appid,version,imei,versionCode);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("code" );
        List<AppVersionInfo> list = service.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            AppVersionInfo appVersionInfo = list.get(0);
            if (versionCode < appVersionInfo.getCode()) {
                return Result.ok(list.get(0));
            }
        }
        return Result.error("" );
    }

    @RequestMapping("/getVersion" )
    public Result<?> getVersion() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("code" );
        List<AppVersionInfo> list = service.list(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return Result.ok(list.get(0));
        }
        return Result.ok();
    }

    @RequestMapping("/lsitAll" )
    public Result<?> lsitAll() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("create_time" );
        List<AppVersionInfo> list = service.list(queryWrapper);
        return Result.ok(list);
    }


    @AutoLog(value = "app版本信息-分页列表查询" )
    @ApiOperation(value = "app版本信息-分页列表查询" , notes = "app版本信息-分页列表查询" )
    @GetMapping(value = "/list" )
    public Result<?> queryPageList(AppVersionInfo appVersionInfo,
                                   @RequestParam(name = "pageNo" , defaultValue = "1" ) Integer pageNo,
                                   @RequestParam(name = "pageSize" , defaultValue = "10" ) Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<AppVersionInfo> queryWrapper = QueryGenerator.initQueryWrapper(appVersionInfo, req.getParameterMap());
        queryWrapper.orderByDesc("code");
        Page<AppVersionInfo> page = new Page<AppVersionInfo>(pageNo, pageSize);
        IPage<AppVersionInfo> pageList = service.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    @RequestMapping(value = "/importExcel" , method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request) {
        int version = 1;
        LambdaQueryWrapper<AppVersionInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(AppVersionInfo::getCreateTime);
        List<AppVersionInfo> list = service.list(lambdaQueryWrapper);

        if (CollUtil.isNotEmpty(list)) {
            AppVersionInfo appVersionInfo = list.get(0);
            if (appVersionInfo.getCode() != null) {
                version = appVersionInfo.getCode() + 1;
            }
        }

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		System.out.println(sysUser);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// 获取上传文件对象
			String path = uploadpath+File.separator+file.getOriginalFilename();
			try {
				file.transferTo(new File(path));
			}catch (Exception e){
				e.printStackTrace();
			}

            AppVersionInfo appVersionInfo = new AppVersionInfo();
            appVersionInfo.setCreator(getWorkNo());
            appVersionInfo.setCreateTime(new Date());
            appVersionInfo.setCode(version);
            appVersionInfo.setFileSize(new BigDecimal(file.getSize()));
			appVersionInfo.setWulj(path);
			appVersionInfo.setUrl(file.getOriginalFilename());
            service.save(appVersionInfo);

        }
        return Result.ok();
    }

	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		service.removeById(id);
		return Result.ok("删除成功!");
	}

    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody AppVersionInfo appVersionInfo) {
        service.updateById(appVersionInfo);
        return Result.ok("编辑成功!");
    }

}

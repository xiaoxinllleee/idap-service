package org.cmms.modules.dklldj.csszgl.khxmsz.controller;

import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import org.cmms.modules.dklldj.csszgl.khxmsz.service.IKhxmszService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 考核项目设置
 * @Author: Penghr
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Slf4j
@Api(tags="考核项目设置")
@RestController
@RequestMapping("/csszgl/khxmsz")
public class KhxmszController extends JeecgController<Khxmsz, IKhxmszService> {
	 @Autowired
	 private IKhxmszService khxmszService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Value(value = "${common.path.upload}")
	 private String uploadPath;
	 @Autowired
	 private RedisUtil redisUtil;

	/**
	 * 分页列表查询
	 *
	 * @param khxmsz
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "考核项目设置-分页列表查询")
	@ApiOperation(value="考核项目设置-分页列表查询", notes="考核项目设置-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Khxmsz khxmsz,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
	    String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	    khxmsz.setQydm(qydm);
		Result<IPage<Khxmsz>> result = new Result<IPage<Khxmsz>>();
		QueryWrapper<Khxmsz> queryWrapper = QueryGenerator.initQueryWrapper(khxmsz, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IKhxmszService.class,khxmszService,pageNo,pageSize,queryWrapper,"zbid");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 添加
	 * @param khxmsz
	 * @return
	 */
	@AutoLog(value = "考核项目设置-添加")
	@ApiOperation(value="考核项目设置-添加", notes="考核项目设置-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Khxmsz khxmsz) {
	    try {
	        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
	        khxmsz.setQydm(qydm);
            khxmszService.save(khxmsz);
        } catch (Exception e) {
            log.error("考核项目添加失败",e.getMessage());
            return Result.error("考核项目添加失败!");
        }
		return Result.ok("添加成功！");
	}

	/**
	 * 编辑
	 * @param khxmsz
	 * @return
	 */
	@AutoLog(value = "考核项目设置-编辑")
	@ApiOperation(value="考核项目设置-编辑", notes="考核项目设置-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Khxmsz khxmsz) {
		try {
			String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
			QueryWrapper<Khxmsz> updateWrapper = new QueryWrapper<>();
			updateWrapper.eq("qydm", qydm);
			updateWrapper.eq("zbid", khxmsz.getZbid());
			//表主键不能更新（Kudu）
			khxmsz.setQydm(null);
			khxmsz.setZbid(null);
			khxmszService.update(khxmsz, updateWrapper);
			return Result.ok("编辑成功!");
		} catch (Exception e) {
			log.error("编辑失败"+e.getMessage());
			return Result.error("编辑失败!");
		}
	}

	/**
	 * 通过指标id删除
	 * @param zbid
	 * @return
	 */
	@AutoLog(value = "考核项目设置-通过指标id删除")
	@ApiOperation(value="考核项目设置-通过指标id删除", notes="考核项目设置-通过指标id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="zbid",required=true) String zbid) {
	    try {
	        String qydm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + getLoginUser().getUsername());
            QueryWrapper<Khxmsz> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("zbid",zbid);
            queryWrapper.eq("qydm",qydm);
	        khxmszService.remove(queryWrapper);
			return Result.ok("删除成功!");
        } catch (Exception e) {
            log.error("删除失败"+e.getMessage());
            return Result.error("删除失败!");
        }
	}

    /**
     * 导出excel
     * @param request
     * @param khxmsz
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Khxmsz khxmsz) {
      return super.exportXls(request, khxmsz, Khxmsz.class, "考核项目设置");
    }

}

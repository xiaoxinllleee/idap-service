package org.cmms.modules.zhgl.khrl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.zhgl.khrl.entity.KhgxglSjyhkhxx;
import org.cmms.modules.zhgl.khrl.service.IKhgxglSjyhkhxxService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
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
 * @Description: 手机银行客户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags="手机银行客户信息")
@RestController
@RequestMapping("/khrl/khgxglSjyhkhxx")
public class KhgxglSjyhkhxxController extends JeecgController<KhgxglSjyhkhxx, IKhgxglSjyhkhxxService> {
	@Autowired
	private IKhgxglSjyhkhxxService khgxglSjyhkhxxService;
	@Autowired
	IHrBasOrganizationService hrBasOrganizationService;

	/**
	 * 分页列表查询
	 *
	 * @param khgxglSjyhkhxx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "手机银行客户信息-分页列表查询")
	@ApiOperation(value="手机银行客户信息-分页列表查询", notes="手机银行客户信息-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(KhgxglSjyhkhxx khgxglSjyhkhxx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<KhgxglSjyhkhxx> queryWrapper = QueryGenerator.initQueryWrapper(khgxglSjyhkhxx, req.getParameterMap());
		Page<KhgxglSjyhkhxx> page = new Page<KhgxglSjyhkhxx>(pageNo, pageSize);
		IPage<KhgxglSjyhkhxx> pageList = khgxglSjyhkhxxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

}

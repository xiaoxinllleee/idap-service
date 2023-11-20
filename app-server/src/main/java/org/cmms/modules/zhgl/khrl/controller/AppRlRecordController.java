package org.cmms.modules.zhgl.khrl.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.zhgl.khrl.entity.AppRlRecord;
import org.cmms.modules.zhgl.khrl.service.IAppRlRecordService;
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
 * @Description: 认领记录
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags="认领记录")
@RestController
@RequestMapping("/khrl/appRlRecord")
public class AppRlRecordController extends JeecgController<AppRlRecord, IAppRlRecordService> {
	@Autowired
	private IAppRlRecordService appRlRecordService;
	
	/**
	 * 分页列表查询
	 *
	 * @param appRlRecord
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "认领记录-分页列表查询")
	@ApiOperation(value="认领记录-分页列表查询", notes="认领记录-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(AppRlRecord appRlRecord,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<AppRlRecord> queryWrapper = QueryGenerator.initQueryWrapper(appRlRecord, req.getParameterMap());
		Page<AppRlRecord> page = new Page<AppRlRecord>(pageNo, pageSize);
		IPage<AppRlRecord> pageList = appRlRecordService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	

}

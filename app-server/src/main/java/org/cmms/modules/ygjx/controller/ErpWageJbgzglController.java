package org.cmms.modules.ygjx.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.ygjx.entity.ErpWageJbgzgl;
import org.cmms.modules.ygjx.entity.ErpWageJbgzglCl;
import org.cmms.modules.ygjx.service.IErpWageJbgzglService;
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
 * @Description: 基本工资管理涟源
 * @Author: jeecg-boot
 * @Date:   2022-10-27
 * @Version: V1.0
 */
@Slf4j
@Api(tags="基本工资管理涟源")
@RestController
@RequestMapping("/ygjx/erpWageJbgzgl")
public class ErpWageJbgzglController extends JeecgController<ErpWageJbgzgl, IErpWageJbgzglService> {
	 @GetMapping(value = "/applist")
	 public Result<?> queryPageAppList(@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									   HttpServletRequest req) {
		 Page<ErpWageJbgzgl> page = new Page<ErpWageJbgzgl>(pageNo, pageSize);
		 LambdaQueryWrapper<ErpWageJbgzgl> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(ErpWageJbgzgl::getYggh,getWorkNo());
		 lambdaQueryWrapper.orderByDesc(ErpWageJbgzgl::getGzyf);
		 IPage<ErpWageJbgzgl> pageList = service.page(page, lambdaQueryWrapper);
		 return Result.ok(pageList);
	 }

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "涟源基本工资管理-通过id查询")
	 @ApiOperation(value="涟源基本工资管理-通过id查询", notes="涟源基本工资管理-通过id查询")
	 @GetMapping(value = "/queryById")
	 public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		 ErpWageJbgzgl erpWageJbgzgl = service.getById(id);
		 return Result.ok(erpWageJbgzgl);
	 }
}

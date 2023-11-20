package org.cmms.modules.ywgl.yxbldkgl.qkqsmx.controller;

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
import org.cmms.modules.ywgl.yxbldkgl.qkqsmx.entity.Qkqsmx;
import org.cmms.modules.ywgl.yxbldkgl.qkqsmx.service.IQkqsmxService;
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
 * @Description: 欠款期数明细
 * @Author: Penghr
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags="欠款期数明细")
@RestController
@RequestMapping("/yxbldkgl/qkqsmx")
public class QkqsmxController extends JeecgController<Qkqsmx, IQkqsmxService> {
	@Autowired
	private IQkqsmxService qkqsmxService;

	/**
	 * 分页列表查询
	 *
	 * @param qkqsmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "欠款期数明细-分页列表查询")
	@ApiOperation(value="欠款期数明细-分页列表查询", notes="欠款期数明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qkqsmx qkqsmx,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Qkqsmx> queryWrapper = QueryGenerator.initQueryWrapper(qkqsmx, req.getParameterMap());
		Page<Qkqsmx> page = new Page<Qkqsmx>(pageNo, pageSize);
		IPage<Qkqsmx> pageList = qkqsmxService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

    /**
     * 导出excel
     *
     * @param request
     * @param qkqsmx
     */
	@AutoLog(value = "欠款期数明细-导出")
	@ApiOperation(value="欠款期数明细-导出", notes="欠款期数明细-导出")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qkqsmx qkqsmx) {
      return super.exportXls(request, qkqsmx, Qkqsmx.class, "欠款期数明细");
    }

}

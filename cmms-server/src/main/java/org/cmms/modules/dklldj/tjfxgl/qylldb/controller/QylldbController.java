package org.cmms.modules.dklldj.tjfxgl.qylldb.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Dhdkmx;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Qylldb;
import org.cmms.modules.dklldj.tjfxgl.qylldb.service.IDhdkmxService;
import org.cmms.modules.dklldj.tjfxgl.qylldb.service.IQylldbService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 签约利率对比
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="签约利率对比")
@RestController
@RequestMapping("/tjfxgl/qylldb")
public class QylldbController extends JeecgController<Qylldb, IQylldbService> {
	@Autowired
	private IQylldbService qylldbService;
	@Autowired
	private IDhdkmxService dhdkmxService;

	/**
	 * 分页列表查询
	 *
	 * @param qylldb
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "签约利率对比-分页列表查询")
	@ApiOperation(value="签约利率对比-分页列表查询", notes="签约利率对比-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Qylldb qylldb,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<Qylldb>> result = new Result<IPage<Qylldb>>();
		QueryWrapper<Qylldb> queryWrapper = QueryGenerator.initQueryWrapper(qylldb, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IQylldbService.class,qylldbService,pageNo,pageSize,queryWrapper,"tjyf","zjhm");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

    /**
     * 导出excel
     *
     * @param request
     * @param qylldb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Qylldb qylldb) {
      return super.exportXls(request, qylldb, Qylldb.class, "签约利率对比");
    }

	 /**
	  * 签约利率对比-提取
	  * @param object
	  * @return
	  */
	@PutMapping(value = "/init")
	public Result<?> init(@RequestBody JSONObject object) {
    	try {
			Map<String, String> param = new HashMap<>();
			param.put("tjyf", object.getString("tjyf"));
			qylldbService.init(param);
		} catch (Exception e) {
			log.error(e.getMessage(),"提取失败！");
			return Result.error(e.getMessage());
		}
    	return Result.ok("提取成功！");
	}

	 /**
	  * 签约利率对比-查看单户贷款明细
	  * @param month
	  * @param jgdm
	  * @param zjhm
	  * @param ywbh
	  * @return
	  */
	@GetMapping("/dhdkmx")
	public Result<List<Dhdkmx>> queryDhdkmx(@RequestParam(name = "tjyf", required = true) String month,
											@RequestParam(name = "jgdm", required = true) String jgdm,
											@RequestParam(name = "zjhm", required = true) String zjhm,
											@RequestParam(name = "ywbh", required = true) String ywbh) {
		Result<List<Dhdkmx>> result = new Result<>();
		try {
			Date tjyf = DateUtil.parseDateFormat(month, "yyyy-MM-dd");
			List<Dhdkmx> dhdkmxList = dhdkmxService.queryDhdkmx(tjyf,jgdm,zjhm,ywbh);
			result.setResult(dhdkmxList);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}

package org.cmms.modules.dklldj.tjfxgl.zhsftj.controller;

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
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.entity.Zhsftj;
import org.cmms.modules.dklldj.tjfxgl.zhsftj.service.IZhsftjService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
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
 * @Description: 支行上浮统计
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="支行上浮统计")
@RestController
@RequestMapping("/tjfxgl/zhsftj")
public class ZhsftjController extends JeecgController<Zhsftj, IZhsftjService> {
	@Autowired
	private IZhsftjService zhsftjService;
	 @Autowired
	 private ISysDictService iSysDictService;
	 @Value("${com.etl.sfdsjpt}")
	 private String sfdsjpt;

	/**
	 * 分页列表查询
	 *
	 * @param zhsftj
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "支行上浮统计-分页列表查询")
	@ApiOperation(value="支行上浮统计-分页列表查询", notes="支行上浮统计-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Zhsftj zhsftj,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<IPage<Zhsftj>> result = new Result<IPage<Zhsftj>>();
		QueryWrapper<Zhsftj> queryWrapper = QueryGenerator.initQueryWrapper(zhsftj, req.getParameterMap());
		IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZhsftjService.class,zhsftjService,pageNo,pageSize,queryWrapper,"tjyf","jgdm");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

    /**
     * 导出excel
     *
     * @param request
     * @param zhsftj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zhsftj zhsftj) {
      return super.exportXls(request, zhsftj, Zhsftj.class, "支行上浮统计");
    }

	 /**
	  * 支行上浮统计-提取
	  * @param object
	  * @return
	  */
    @PutMapping(value = "/init")
	public Result<?> init(@RequestBody JSONObject object) {
		String qydm = iSysDictService.queryTableDictTextByKey("SYS_DIC", "VALUE", "CODE", "101001");
		Result result = new Result<>();
		if ("true".equalsIgnoreCase(sfdsjpt)) {
			String STATISTICAL_MONTH = object.getString("tjyf");
			HashMap<String, String> params = new HashMap<>();
			params.put("fiscal_date", STATISTICAL_MONTH);
			// `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
			boolean completionSignal = EtlUtil.callEtl("count_rate_zhllsf_tj", params, 15);
			result.setSuccess(completionSignal);
		} else {
			try {
				Map<String, String> param = new HashMap<>();
				param.put("tjyf", object.getString("tjyf"));
				zhsftjService.init(param);
				result.setSuccess(true);
				return result;
			} catch (Exception e) {
				//log.error(e.getMessage(),"提取失败！");
				//return Result.error(e.getMessage());
				System.out.println(e);
				log.error("提取失败", e.getMessage());
				result.setSuccess(false);
			}
		}
		// return Result.ok("提取成功！");
		return result;
	}

}

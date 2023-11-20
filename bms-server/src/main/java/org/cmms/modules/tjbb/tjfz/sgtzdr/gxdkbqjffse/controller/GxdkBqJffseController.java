package org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.commons.lang.StringUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.BeanUtil;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.entity.GxdkBqJffse;
import org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.service.IGxdkBqJffseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.verify.GxdkBqJffseImportVerify;
import org.cmms.modules.tjbb.tjfz.sgtzdr.gxdkbqjffse.vo.GxdkBqJffseImportVO;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 各项贷款本期借方发生额
 * @Author: Penghr
 * @Date: 2022-12-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "各项贷款本期借方发生额")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/gxdkBqJffse")
public class GxdkBqJffseController extends JeecgController<GxdkBqJffse, IGxdkBqJffseService> {
    @Autowired
    private IGxdkBqJffseService gxdkBqJffseService;
    @Autowired
    private GxdkBqJffseImportVerify importVerify;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param gxdkBqJffse
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "各项贷款本期借方发生额-分页列表查询")
    @ApiOperation(value = "各项贷款本期借方发生额-分页列表查询", notes = "各项贷款本期借方发生额-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(GxdkBqJffse gxdkBqJffse,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<GxdkBqJffse> queryWrapper = QueryGenerator.initQueryWrapper(gxdkBqJffse, req.getParameterMap());
		if(gxdkBqJffse.getDataDate()!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			queryWrapper.eq("data_date", DateUtils.date2Str(gxdkBqJffse.getDataDate(),sdf));
			log.info(DateUtils.date2Str(gxdkBqJffse.getDataDate(),sdf));
		}
        IPage<GxdkBqJffse> pageList = PageUtil.toPage(IGxdkBqJffseService.class, gxdkBqJffseService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param gxdkBqJffse
     * @return
     */
//	@AutoLog(value = "各项贷款本期借方发生额-添加")
//	@ApiOperation(value="各项贷款本期借方发生额-添加", notes="各项贷款本期借方发生额-添加")
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody GxdkBqJffse gxdkBqJffse) {
//		gxdkBqJffseService.save(gxdkBqJffse);
//		return Result.ok("添加成功！");
//	}

    /**
     * 编辑
     *
     * @param gxdkBqJffse
     * @return
     */
    @AutoLog(value = "各项贷款本期借方发生额-编辑")
    @ApiOperation(value = "各项贷款本期借方发生额-编辑", notes = "各项贷款本期借方发生额-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody GxdkBqJffse gxdkBqJffse) {
        UpdateWrapper<GxdkBqJffse> wrapper = new UpdateWrapper<>();
        wrapper.eq("data_date", gxdkBqJffse.getDataDate());
        wrapper.eq("jgbm", gxdkBqJffse.getJgbm());
        gxdkBqJffse.setDataDate(null);
        gxdkBqJffse.setJgbm(null);
        gxdkBqJffse.setLrbz(2);
        gxdkBqJffse.setLrr(getLoginUser().getUsername());
        gxdkBqJffse.setLrsj(new Date());
        gxdkBqJffseService.update(gxdkBqJffse, wrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过dataDate、jgbm删除
     *
     * @param date 数据日期
     * @param jgbm 机构编码
     * @return
     */
    @AutoLog(value = "各项贷款本期借方发生额-通过dataDate、jgbm删除")
    @ApiOperation(value = "各项贷款本期借方发生额-通过dataDate、jgbm删除", notes = "各项贷款本期借方发生额-通过dataDate、jgbm删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
							@RequestParam(name = "jgbm", required = true) String jgbm) {
		Date dataDate = DateUtil.string2Date(date, "yyyy-MM-dd");
		UpdateWrapper<GxdkBqJffse> wrapper = new UpdateWrapper<>();
		wrapper.eq("data_date", dataDate);
		wrapper.eq("jgbm", jgbm);
        gxdkBqJffseService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//	@AutoLog(value = "各项贷款本期借方发生额-批量删除")
//	@ApiOperation(value="各项贷款本期借方发生额-批量删除", notes="各项贷款本期借方发生额-批量删除")
//	@DeleteMapping(value = "/deleteBatch")
//	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
//		this.gxdkBqJffseService.removeByIds(Arrays.asList(ids.split(",")));
//		return Result.ok("批量删除成功！");
//	}

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "各项贷款本期借方发生额-通过id查询")
    @ApiOperation(value = "各项贷款本期借方发生额-通过id查询", notes = "各项贷款本期借方发生额-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        GxdkBqJffse gxdkBqJffse = gxdkBqJffseService.getById(id);
        return Result.ok(gxdkBqJffse);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param gxdkBqJffse
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, GxdkBqJffse gxdkBqJffse) {
        //return super.exportXls(request, gxdkBqJffse, GxdkBqJffse.class, "各项贷款本期借方发生额");
		QueryWrapper<GxdkBqJffse> queryWrapper = new QueryWrapper<>();
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				GxdkBqJffse form = JSON.parseObject(deString, GxdkBqJffse.class);
				queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			log.error("导出错误！各项贷款本期借方发生额：" + throwable.getMessage());
		}
		queryWrapper.orderByAsc("data_date", "jgbm");
		List<GxdkBqJffse> pageList = gxdkBqJffseService.list(queryWrapper);
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "各项贷款本期借方发生额");
		mv.addObject(NormalExcelConstants.CLASS, GxdkBqJffse.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("新邵农村商业银行各项贷款本期借方发生额", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
		mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
		return mv;
    }

	/**
	 * 导出模板Excel
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "各项贷款本期借方发生额");
		mv.addObject(NormalExcelConstants.CLASS, GxdkBqJffseImportVO.class);
		ExportParams exportParams = new ExportParams("各项贷款本期借方发生额导入模板", "各项贷款本期借方发生额");
		mv.addObject(NormalExcelConstants.PARAMS, exportParams);
		mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		return mv;
	}

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        //return super.importExcel(request, response, GxdkBqJffse.class);
		//Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"),"yyyy-MM-dd");
		String filePaths = jsonObject.getString("filePaths");
		if (StringUtils.isEmpty(filePaths)) {
			return Result.error("请先上传文件！");
		}
		String[] filePathList = filePaths.split(",");
		JSONObject obj = new JSONObject();
		for (String filePath : filePathList) {
			String baseFilePath = uploadpath + File.separator + filePath;
			File file = new File(baseFilePath);
			ImportParams params = new ImportParams();
			params.setTitleRows(1);
			params.setHeadRows(1);
			params.setNeedSave(false);
			if (importVerify != null) {
				params.setVerifyHanlder(importVerify);
			}
			FileInputStream fis = null;
			FileOutputStream fos = null;
			try {
				fis = new FileInputStream(file);
				boolean checkResult = ExcelImportCheckUtil.check(fis, GxdkBqJffseImportVO.class, params, 1.0);
				ExcelImportResult<GxdkBqJffseImportVO> importResult = ExcelImportUtil.importExcelVerify(file, GxdkBqJffseImportVO.class, params);
				List<GxdkBqJffseImportVO> list = importResult.getList();
				List<GxdkBqJffse> gxdkBqJffseList = new ArrayList<>();
				for (GxdkBqJffseImportVO gxdkBqJffseImportVO : list) {
					GxdkBqJffse gxdkBqJffse = new GxdkBqJffse();
					BeanUtil.copyPropertiesIgnoreNull(gxdkBqJffseImportVO, gxdkBqJffse);
					//gxdkBqJffse.setDataDate(fiscalDate);
					gxdkBqJffse.setLrbz(0);
					gxdkBqJffse.setLrr(getLoginUser().getUsername());
					gxdkBqJffse.setLrsj(new Date());
					gxdkBqJffseList.add(gxdkBqJffse);
				}
				if (!gxdkBqJffseList.isEmpty()) {
					//保存以前删除当月数据，以防止重复导入
					UpdateWrapper<GxdkBqJffse> wrapper = new UpdateWrapper<>();
					wrapper.eq("data_date", gxdkBqJffseList.get(0).getDataDate());
					gxdkBqJffseService.remove(wrapper);
					// 保存最新当月数据
					gxdkBqJffseService.saveBatch(gxdkBqJffseList);
				}
				obj.put("filePath", filePath);
				fos = new FileOutputStream(baseFilePath);
				importResult.getWorkbook().write(fos);
				fos.flush();
				fos.close();

				//导入各项贷款本期借方发生额成功以后，根据导入的机构名称自动更新机构编码
				if ("true".equals(sfdsjpt)) {
					HashMap<String, String> param = new HashMap<>();
					param.put("etl_task", "kiss.domain.application.tjbb.proc_tjbb_sgtz_gxdkbqjffse");
					boolean flag = EtlUtil.callEtl("tjbb_common_init", param, 15);
					log.info("各项贷款本期借方发生额-机构编码处理逻辑-是否成功？-"+flag);
				} else {
					log.info("各项贷款本期借方发生额-导入-非大数据应用平台操作-未添加处理逻辑");
				}

				return Result.ok("文件导入完成！成功导入数据行数: " + list.size(), obj);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				return Result.error("文件导入失败:" + e.getMessage());
			} finally {
				IoUtil.close(fis);
				IoUtil.close(fos);
			}
		}
		return Result.ok("文件导入失败！");
    }

}

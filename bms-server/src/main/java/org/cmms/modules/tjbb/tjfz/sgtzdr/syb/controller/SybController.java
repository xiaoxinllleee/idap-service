package org.cmms.modules.tjbb.tjfz.sgtzdr.syb.controller;

import java.io.*;
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
import org.cmms.common.util.oConvertUtils;
import org.cmms.common.utils.PageUtil;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.entity.Syb;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.service.ISybService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.verify.SybImportVerify;
import org.cmms.modules.tjbb.tjfz.sgtzdr.syb.vo.SybImportVO;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
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
 * @Description: 湖南省农村信用社损益表
 * @Author: Penghr
 * @Date: 2022-12-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "湖南省农村信用社损益表")
@RestController
@RequestMapping("/tjbb/tjfz/sgtzdr/syb")
public class SybController extends JeecgController<Syb, ISybService> {
    @Autowired
    private ISybService sybService;
    @Autowired
	private SybImportVerify importVerify;

    /**
     * 分页列表查询
     *
     * @param syb
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "湖南省农村信用社损益表-分页列表查询")
    @ApiOperation(value = "湖南省农村信用社损益表-分页列表查询", notes = "湖南省农村信用社损益表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Syb syb,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Syb> queryWrapper = QueryGenerator.initQueryWrapper(syb, req.getParameterMap());
        IPage<Syb> pageList = PageUtil.toPage(ISybService.class, sybService, pageNo, pageSize, queryWrapper, "data_date");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param syb
     * @return
     */
//    @AutoLog(value = "湖南省农村信用社损益表-添加")
//    @ApiOperation(value = "湖南省农村信用社损益表-添加", notes = "湖南省农村信用社损益表-添加")
//    @PostMapping(value = "/add")
//    public Result<?> add(@RequestBody Syb syb) {
//        sybService.save(syb);
//        return Result.ok("添加成功！");
//    }

    /**
     * 编辑
     *
     * @param syb
     * @return
     */
    @AutoLog(value = "湖南省农村信用社损益表-编辑")
    @ApiOperation(value = "湖南省农村信用社损益表-编辑", notes = "湖南省农村信用社损益表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Syb syb) {
		UpdateWrapper<Syb> wrapper = new UpdateWrapper<>();
		wrapper.eq("data_date", syb.getDataDate());
		wrapper.eq("kmh", syb.getKmh());
		syb.setDataDate(null);
		syb.setKmh(null);
		syb.setLrbz(2);
		syb.setLrr(getLoginUser().getUsername());
		syb.setLrsj(new Date());
        sybService.update(syb, wrapper);
        return Result.ok("编辑成功!");
    }

	/**
	 * 通过dataDate、kmh删除
	 *
	 * @param date 数据日期
	 * @param kmh 科目号
	 * @return
	 */
    @AutoLog(value = "湖南省农村信用社损益表-通过dataDate、kmh删除")
    @ApiOperation(value = "湖南省农村信用社损益表-通过dataDate、kmh删除", notes = "湖南省农村信用社损益表-通过dataDate、kmh删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "dataDate", required = true) String date,
							@RequestParam(name = "kmh", required = true) String kmh) {
		Date dataDate = DateUtil.string2Date(date, "yyyy-MM-dd");
		UpdateWrapper<Syb> wrapper = new UpdateWrapper<>();
		wrapper.eq("data_date", dataDate);
		wrapper.eq("kmh", kmh);
        sybService.remove(wrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
//    @AutoLog(value = "湖南省农村信用社损益表-批量删除")
//    @ApiOperation(value = "湖南省农村信用社损益表-批量删除", notes = "湖南省农村信用社损益表-批量删除")
//    @DeleteMapping(value = "/deleteBatch")
//    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
//        this.sybService.removeByIds(Arrays.asList(ids.split(",")));
//        return Result.ok("批量删除成功！");
//    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "湖南省农村信用社损益表-通过id查询")
    @ApiOperation(value = "湖南省农村信用社损益表-通过id查询", notes = "湖南省农村信用社损益表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Syb syb = sybService.getById(id);
        return Result.ok(syb);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param syb
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Syb syb) {
        //return super.exportXls(request, syb, Syb.class, "湖南省农村信用社损益表");
		QueryWrapper<Syb> queryWrapper = new QueryWrapper<>();
		try {
			String paramsStr = request.getParameter("paramsStr");
			if (oConvertUtils.isNotEmpty(paramsStr)) {
				String deString = URLDecoder.decode(paramsStr, "UTF-8");
				Syb form = JSON.parseObject(deString, Syb.class);
				queryWrapper = QueryGenerator.initQueryWrapper(form, request.getParameterMap());
			}
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			log.error("导出错误！湖南省农村信用社损益表：" + throwable.getMessage());
		}
		queryWrapper.orderByAsc("data_date", "kmh");
		List<Syb> pageList = sybService.list(queryWrapper);
		ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
		//导出文件名称
		mv.addObject(NormalExcelConstants.FILE_NAME, "湖南省农村信用社损益表");
		mv.addObject(NormalExcelConstants.CLASS, Syb.class);
		mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("湖南省农村信用社损益表", "导出人:" + getLoginUser().getRealname() + "（单位/万元）", "导出信息"));
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
		mv.addObject(NormalExcelConstants.FILE_NAME, "湖南省农村信用社损益表");
		mv.addObject(NormalExcelConstants.CLASS, SybImportVO.class);
		ExportParams exportParams = new ExportParams("湖南省农村信用社损益表导入模板", "损益表");
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
        //return super.importExcel(request, response, Syb.class);
		Date fiscalDate = DateUtil.string2Date(request.getParameter("fiscalDate"),"yyyy-MM-dd");
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
				boolean checkResult = ExcelImportCheckUtil.check(fis, SybImportVO.class, params, 1.0);
				if (!checkResult) {
					return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
				}
				ExcelImportResult<SybImportVO> importResult = ExcelImportUtil.importExcelVerify(file, SybImportVO.class, params);
				List<SybImportVO> list = importResult.getList();
				List<Syb> sybList = new ArrayList<>();
				for (SybImportVO sybImportVO : list) {
					Syb syb = new Syb();
					BeanUtil.copyPropertiesIgnoreNull(sybImportVO, syb);
					syb.setDataDate(fiscalDate);
					syb.setLrbz(0);
					syb.setLrr(getLoginUser().getUsername());
					syb.setLrsj(new Date());
					sybList.add(syb);
				}
				if (!sybList.isEmpty()) {
					//保存以前删除当月数据，以防止重复导入
					UpdateWrapper<Syb> wrapper = new UpdateWrapper<>();
					wrapper.eq("data_date", fiscalDate);
					sybService.remove(wrapper);
					// 保存最新当月数据
					sybService.saveBatch(sybList);
				}
				obj.put("filePath", filePath);
				fos = new FileOutputStream(baseFilePath);
				importResult.getWorkbook().write(fos);
				fos.flush();
				fos.close();
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

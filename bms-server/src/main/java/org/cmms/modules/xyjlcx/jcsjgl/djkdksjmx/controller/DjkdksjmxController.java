package org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.hmdgl.hmdgl.verify.HmdglImportVerify;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.DjkdksjmxVO;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.verify.DjkdksjmxImportVerify;
import org.cmms.modules.ywgl.djkyw.djkrygl.entity.Djkrygl;
import org.cmms.modules.ywgl.djkyw.djkrygl.service.IDjkryglService;
import org.cmms.modules.ywgl.djkyw.djkwdgl.entity.Djkwdgl;
import org.cmms.modules.ywgl.djkyw.djkwdgl.service.IDjkwdglService;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.entity.Zzsfpxx;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 贷记卡贷款数据明细
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags="贷记卡贷款数据明细")
@RestController
@RequestMapping("/djkdksjmx/djkdksjmx")
public class DjkdksjmxController extends JeecgController<Djkdksjmx, IDjkdksjmxService> {
	@Autowired
	private IDjkdksjmxService djkdksjmxService;
	@Autowired
	private DjkdksjmxImportVerify djkdksjmxImportVerify;
	@Value("${com.etl.sfdsjpt}")
	private String sfdsjpt;
	@Autowired
	private IDjkryglService djkryglService;
	@Autowired
	private IDjkwdglService djkwdglService;

	/**
	 * 分页列表查询
	 *
	 * @param djkdksjmx
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "贷记卡贷款数据明细-分页列表查询")
	@ApiOperation(value = "贷记卡贷款数据明细-分页列表查询", notes = "贷记卡贷款数据明细-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Djkdksjmx djkdksjmx,
								   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
								   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
								   HttpServletRequest req) {

		Result<IPage<Djkdksjmx>> result = new Result<IPage<Djkdksjmx>>();
		QueryWrapper<Djkdksjmx> queryWrapper = QueryGenerator.initQueryWrapper(djkdksjmx, req.getParameterMap());
		IPage pageList = org.cmms.common.utils.PageUtil.toPage(IDjkdksjmxService.class, djkdksjmxService, pageNo, pageSize, queryWrapper, "kh", "fkrq");
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
	 * 贷记卡贷款数据明细 / 添加
	 *
	 * @param djkdksjmx
	 * @return
	 */
	@AutoLog(value = "贷记卡贷款数据明细-添加")
	@ApiOperation(value = "贷记卡贷款数据明细-添加", notes = "贷记卡贷款数据明细-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Djkdksjmx djkdksjmx) {
		try {
			QueryWrapper<Djkdksjmx> queryWrapper = new QueryWrapper<>();
			queryWrapper.eq("kh", djkdksjmx.getKh());
			queryWrapper.eq("fkrq", djkdksjmx.getFkrq());
			Djkdksjmx form = djkdksjmxService.getOne(queryWrapper,false);
			if (form != null) {
				return Result.error("该卡号已存在！");
			}
			djkdksjmx.setLrbz(1);
			djkdksjmx.setLrsj(new Timestamp(System.currentTimeMillis()));
			djkdksjmx.setLrr(getLoginUser().getRealname());
			djkdksjmxService.save(djkdksjmx);
			return Result.ok("添加成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 贷记卡贷款数据明细 / 添加失败！", tx);
			return Result.error("添加失败！" + tx.getMessage());
		}
	}

	/**
	 * 贷记卡贷款数据明细 / 编辑
	 *
	 * @param djkdksjmx
	 * @return
	 */
	@AutoLog(value = "贷记卡贷款数据明细-编辑")
	@ApiOperation(value = "贷记卡贷款数据明细-编辑", notes = "贷记卡贷款数据明细-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Djkdksjmx djkdksjmx) {
		try {
			QueryWrapper<Djkdksjmx> queryWrapper = new QueryWrapper<Djkdksjmx>();
			queryWrapper.eq("kh", djkdksjmx.getKh());
			queryWrapper.eq("fkrq", djkdksjmx.getFkrq());
			List<Djkdksjmx> djkdksjmxList = djkdksjmxService.list(queryWrapper);
			if (djkdksjmxList.isEmpty()) {
				return Result.error("该卡号信息不存在！");
			}
			Djkdksjmx record = djkdksjmxList.get(0);
			//表主键不能更新（Kudu）
			record.setKh(null);
			record.setFkrq(null);
			record.setKzl(djkdksjmx.getKzl());
			record.setXb(djkdksjmx.getXb());
			record.setHyzk(djkdksjmx.getHyzk());
			record.setJtzz(djkdksjmx.getJtzz());
			record.setSjhm(djkdksjmx.getSjhm());
			record.setSxje(djkdksjmx.getSxje());
			record.setTzbj(djkdksjmx.getTzbj());
			record.setTzye(djkdksjmx.getTzye());
			record.setKztbz(djkdksjmx.getKztbz());
			record.setTgrgh(djkdksjmx.getTgrgh());
			record.setLrbz(2);
			record.setLrsj(new Timestamp(System.currentTimeMillis()));
			record.setLrr(getLoginUser().getRealname());
			djkdksjmxService.update(record, queryWrapper);
			return Result.ok("编辑成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 贷记卡贷款数据明细 / 编辑失败！", tx);
			return Result.error("编辑失败，请联系系统管理员！" + tx.getMessage());
		}
	}

	/**
	 * 贷记卡贷款数据明细 / 删除
	 *
	 * @param kh
	 * @param fkrqStr
	 * @return
	 */
	@AutoLog(value = "贷记卡贷款数据明细-删除")
	@ApiOperation(value = "贷记卡贷款数据明细-删除", notes = "贷记卡贷款数据明细-删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@Param("kh") String kh,
							@Param("fkrq") String fkrqStr) {
		try {
			Date fkrq = DateUtil.string2Date(fkrqStr,"yyyy-MM-dd");
			QueryWrapper<Djkdksjmx> queryWrapper = new QueryWrapper<Djkdksjmx>();
			queryWrapper.eq("kh", kh);
			queryWrapper.eq("fkrq", fkrq);
			djkdksjmxService.remove(queryWrapper);
			return Result.ok("删除成功！");
		} catch (Throwable tx) {
			log.error("信用记录查询 / 贷记卡贷款数据明细 / 删除失败！", tx);
			return Result.error("删除失败！" + tx.getMessage());
		}
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param djkdksjmx
	 */
	@RequestMapping(value = "/exportXls")
	public ModelAndView exportXls(HttpServletRequest request, Djkdksjmx djkdksjmx) {
		return super.exportXls(request, djkdksjmx, Djkdksjmx.class, "贷记卡贷款数据明细");
	}

	/**
	 * 导出模板
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/exportTemplateXls")
	public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
		//return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
		// AutoPoi 导出Excel
		ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
		// 导出文件名称
		modelAndView.addObject(NormalExcelConstants.FILE_NAME, "贷记卡贷款数据明细导入模板");
		modelAndView.addObject(NormalExcelConstants.CLASS, DjkdksjmxVO.class);
		ExportParams exportParams = new ExportParams(null, "模板信息");
		modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
		modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
		return modelAndView;
	}

	/**
	 * 通过Excel导入数据
	 *
	 * @param jsonObject
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST)
	public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		String filePaths = jsonObject.getString("filePaths");
		if (org.apache.commons.lang.StringUtils.isEmpty(filePaths)) {
			return Result.error("请先上传文件！");
		}
		String[] filePathList = filePaths.split(",");
		JSONObject obj = new JSONObject();
		for (String filePath : filePathList) {
			System.out.println(filePath);
			String baseFilePath = uploadpath + File.separator + filePath;
			File file = new File(baseFilePath);
			ImportParams params = new ImportParams();
			params.setHeadRows(1);
			params.setNeedSave(false);
			if (djkdksjmxImportVerify != null) {
				params.setVerifyHanlder(djkdksjmxImportVerify);
			}
			FileOutputStream fos = null;
			try {
				ExcelImportResult<Djkdksjmx> importResult = ExcelImportUtil.importExcelVerify(file, Djkdksjmx.class,DjkdksjmxVO.class, params);
				List<Djkdksjmx> list = importResult.getList();
				service.saveBatch(list);
				obj.put("filePath", filePath);
				fos = new FileOutputStream(baseFilePath);
				importResult.getWorkbook().write(fos);
				fos.flush();
				fos.close();
				return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
			} catch (Exception e) {
				log.error(e.getMessage(),e);
				return Result.error("文件导入失败:"+e.getMessage());
			} finally {
				IoUtil.close(fos);
			}
		}
		return Result.ok("文件导入失败！");
	}

}




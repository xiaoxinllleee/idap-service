package org.cmms.modules.khdj.khdjpd.controller;

import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;
import org.cmms.modules.khdj.khdjpd.entity.khdjpdSjxmx;
import org.cmms.modules.khdj.khdjpd.service.IKhdjpdService;
import org.cmms.modules.khdj.khdjpd.service.IkhdjpdSjxmxService;
import org.cmms.modules.khdj.khdjpd.verify.KhdjpdImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description: 客户等级评定
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags="1")
@RestController
@RequestMapping("/khgl/khdjpd")
public class KhdjpdController extends JeecgController<Khdjpd, IKhdjpdService> implements Job {
	@Autowired
	private IKhdjpdService iKhdjpdService;
	@Autowired
    private IkhdjpdSjxmxService ikhdjpdSjxmxService;
	@Autowired
    private KhdjpdImportVerify khdjpdImportVerify;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
	/**
	  * 分页列表查询
	 * @param khdjpd
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "1-分页列表查询")
	@ApiOperation(value="1-分页列表查询", notes="1-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<Khdjpd>> queryPageList(Khdjpd khdjpd,
                                               @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                               HttpServletRequest req) {
		Result<IPage<Khdjpd>> result = new Result<IPage<Khdjpd>>();
		QueryWrapper<Khdjpd> queryWrapper = QueryGenerator.initQueryWrapper(khdjpd, req.getParameterMap());
		Page<Khdjpd> page = new Page<Khdjpd>(pageNo, pageSize);
		IPage<Khdjpd> pageList = iKhdjpdService.page(page, queryWrapper);
		result.setSuccess(true);
		result.setResult(pageList);
		return result;
	}

	/**
     * 通过id查询
	 * @param id
	 * @return
	 */
	@AutoLog(value = "1-通过id查询")
	@ApiOperation(value="1-通过id查询", notes="1-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<Khdjpd> queryById(@RequestParam(name="id",required=true) String id) {
		Result<Khdjpd> result = new Result<Khdjpd>();
		Khdjpd khdjpd = iKhdjpdService.getById(id);
		if(khdjpd ==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(khdjpd);
			result.setSuccess(true);
		}
		return result;
	}

    /**
     * 导出excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<Khdjpd> queryWrapper = null;
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              Khdjpd khdjpd = JSON.parseObject(deString, Khdjpd.class);
              queryWrapper = QueryGenerator.initQueryWrapper(khdjpd, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }
      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<Khdjpd> pageList = iKhdjpdService.list(queryWrapper);
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "客户等级评定数据");
      mv.addObject(NormalExcelConstants.CLASS, Khdjpd.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("客户等级评定数据", "导出人:"+sysUser.getRealname(), "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
     * 导出模板excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        return super.exportTemplateXls(Khdjpd.class, "客户等级导入模板");
    }

    /**
     * 通过excel导入数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        return super.importExcelByTemplate(jsonObject, request, response, Khdjpd.class, khdjpdImportVerify);
    }

    /**
     * 客户等级评定数据提取
     * @param jsonObject
     * @return
     */
    @PutMapping(value = "/initData")
    public Result<?> initData(@RequestBody JSONObject jsonObject) {
      try {
          Map<String, String> param = new HashMap<>();
          param.put("pdzq", jsonObject.getString("pdzq"));
          param.put("pdrq", jsonObject.getString("pdrq"));
          System.out.println("pdzq：："+jsonObject.getString("pdzq")+" | "
                            +"pdrq：："+jsonObject.getString("pdrq"));
          iKhdjpdService.initData(param);
      } catch (Exception e) {
          log.error(e.getMessage(), "提取失败！");
          return Result.error(e.getMessage());
      }
      return Result.ok("提取成功！");
    }

    /**
     * 等级评定数据明细查看
     * @param pdzq 评定周期
     * @param pdrq 评定日期
     * @param zjhm 证件号码
     * @return
     */
    @GetMapping(value = "/viewDetail")
    public Result<List<khdjpdSjxmx>> viewDetail(@RequestParam(name = "pdzq", required = true) String pdzq,
                                                @RequestParam(name = "pdrq", required = true) String pdrq,
                                                @RequestParam(name = "zjhm", required = true) String zjhm) {
        Result<List<khdjpdSjxmx>> result = new Result<List<khdjpdSjxmx>>();
        try {
            // 字符串 => 日期 (String => Date)
            Date pdrqDate = DateUtil.parseDateFormat(pdrq,"yyyy-MM-dd");
            List<khdjpdSjxmx> sjxmxList = ikhdjpdSjxmxService.viewDetail(pdzq, pdrqDate, zjhm);
            result.setResult(sjxmxList);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 定时提取任务
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        iKhdjpdService.callAutoMission();
        log.info(String.format("自动执行客户等级评定数据项所需数据准备 at => " + DateUtils.getTimestamp()));
    }
}

package org.cmms.modules.khgl.clkhxxgl.controller;

import java.io.File;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SshUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dklldj.lldjgl.glzhgl.entity.CbsInvmBase;
import org.cmms.modules.dklldj.lldjgl.glzhgl.service.ICbsInvmBaseService;
import org.cmms.modules.khgl.clkhxxgl.entity.Clgrkhjtcy;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhxxgl;
import org.cmms.modules.khgl.clkhxxgl.entity.ClkhxxglImport;
import org.cmms.modules.khgl.clkhxxgl.entity.VKhglClkhxxgl;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjbxxService;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhjtcyService;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhxxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.cmms.modules.khgl.clkhxxgl.service.IVKhglClkhxxglService;
import org.cmms.modules.khxxgl.clkhxx.service.IClkhglService;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.khxxgl.khjbzl.service.IKhjbzlService;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglDksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglCksjmxZhService;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglDksjmxZhService;
import org.cmms.modules.pad.nhxxgl.service.IKhglNhhzxxglService;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.system.entity.HrBasOrganization;
import org.cmms.modules.system.service.IHrBasOrganizationService;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecgframework.poi.excel.view.JeecgTemplateExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
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
 * @Description: 存量客户信息管理
 * @Author: cmms
 * @Date: 2019-09-19
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "存量客户信息管理")
@RestController
@RequestMapping("/khgl/clkhxxgl")
public class ClkhxxglController implements Job {
    @Autowired
    private IClkhxxglService clkhxxglService;
    @Autowired
    private IVKhglClkhxxglService vKhglClkhxxglService;
    @Autowired
    private IHrBasOrganizationService hrBasOrganizationService;
    @Value(value = "${common.path.upload}")
    private String uploadpath;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private ICbsInvmBaseService cbsInvmBaseService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private IEtlSgtzSjtbService etlSgtzSjtbService;

    @Value("${com.etl.etlName:数据下发ETL任务}")
    private String etlName;

    @Value("${com.etl.dagName:etl_day调度}")
    private String dagName;

    @Autowired
    private INhxqService nhxqService;
    @Autowired
    private IKhxxglDksjmxZhService khxxglDksjmxZhService;
    @Autowired
    private IKhxxglCksjmxZhService khxxglCksjmxZhService;
    @Autowired
    private IKhglNhhzxxglService khglNhhzxxglService;
    @Autowired
    private IClgrkhjbxxService clgrkhjbxxService;
    @Autowired
    private IKhjbzlService khjbzlService;
    @Autowired
    private IClkhglService clkhglService;

    /**
     * 分页列表查询
     *
     * @param vKhglClkhxxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "存量客户信息管理-分页列表查询")
    @ApiOperation(value = "存量客户信息管理-分页列表查询", notes = "存量客户信息管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<IPage<VKhglClkhxxgl>> queryPageList(VKhglClkhxxgl vKhglClkhxxgl,
                                                      @RequestParam(name = "sszh", required = false) String sszh,
                                                      @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                                      HttpServletRequest req) {
        Result<IPage<VKhglClkhxxgl>> result = new Result<IPage<VKhglClkhxxgl>>();
        QueryWrapper<VKhglClkhxxgl> queryWrapper = QueryGenerator.initQueryWrapper(vKhglClkhxxgl, req.getParameterMap());
        if (!StringUtils.isEmpty(sszh)) {
            HrBasOrganization hrBasOrganization = hrBasOrganizationService.queryByZzbz(sszh);
            queryWrapper.eq("jgdm", hrBasOrganization.getYwjgdm());
        }
        Page<VKhglClkhxxgl> page = new Page<VKhglClkhxxgl>(pageNo, pageSize);
        IPage<VKhglClkhxxgl> pageList = vKhglClkhxxglService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 添加
     *
     * @param clkhxxgl
     * @return
     */
    @AutoLog(value = "存量客户信息管理-添加")
    @ApiOperation(value = "存量客户信息管理-添加", notes = "存量客户信息管理-添加")
    @PostMapping(value = "/add")
    public Result<Clkhxxgl> add(@RequestBody Clkhxxgl clkhxxgl) {
        Result<Clkhxxgl> result = new Result<Clkhxxgl>();
        try {
            clkhxxglService.save(clkhxxgl);
            result.success("添加成功！");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 编辑
     *
     * @param clkhxxgl
     * @return
     */
    @AutoLog(value = "存量客户信息管理-编辑")
    @ApiOperation(value = "存量客户信息管理-编辑", notes = "存量客户信息管理-编辑")
    @PutMapping(value = "/edit")
    public Result<Clkhxxgl> edit(@RequestBody Clkhxxgl clkhxxgl) {
        Result<Clkhxxgl> result = new Result<Clkhxxgl>();
        Clkhxxgl clkhxxglEntity = clkhxxglService.getById(clkhxxgl.getId());
        if (clkhxxglEntity == null) {
            result.error500("未找到对应实体");
        } else {
            boolean ok = clkhxxglService.updateById(clkhxxgl);
            //TODO 返回false说明什么？
            if (ok) {
                result.success("修改成功!");
            }
        }

        return result;
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量客户信息管理-通过id删除")
    @ApiOperation(value = "存量客户信息管理-通过id删除", notes = "存量客户信息管理-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        try {
            clkhxxglService.removeById(id);
        } catch (Exception e) {
            log.error("删除失败", e.getMessage());
            return Result.error("删除失败!");
        }
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "存量客户信息管理-批量删除")
    @ApiOperation(value = "存量客户信息管理-批量删除", notes = "存量客户信息管理-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<Clkhxxgl> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        Result<Clkhxxgl> result = new Result<Clkhxxgl>();
        if (ids == null || "".equals(ids.trim())) {
            result.error500("参数不识别！");
        } else {
            this.clkhxxglService.removeByIds(Arrays.asList(ids.split(",")));
            result.success("删除成功!");
        }
        return result;
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "存量客户信息管理-通过id查询")
    @ApiOperation(value = "存量客户信息管理-通过id查询", notes = "存量客户信息管理-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<Clkhxxgl> queryById(@RequestParam(name = "id", required = true) String id) {
        Result<Clkhxxgl> result = new Result<Clkhxxgl>();
        Clkhxxgl clkhxxgl = clkhxxglService.getById(id);
        if (clkhxxgl == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(clkhxxgl);
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
        QueryWrapper<VKhglClkhxxgl> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                VKhglClkhxxgl clkhxxgl = JSON.parseObject(deString, VKhglClkhxxgl.class);
                queryWrapper = QueryGenerator.initQueryWrapper(clkhxxgl, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<VKhglClkhxxgl> pageList = vKhglClkhxxglService.list(queryWrapper);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String secondTitle = sysUser.getUsername();
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "存量客户信息管理列表");
        mv.addObject(NormalExcelConstants.CLASS, VKhglClkhxxgl.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("存量客户信息管理列表数据", "导出人:" + secondTitle, "导出信息"));
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
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "存量客户信息管理列表");
        mv.addObject(NormalExcelConstants.CLASS, ClkhxxglImport.class);
        ExportParams exportParams = new ExportParams("存量客户信息管理列表数据", "模板信息");
        mv.addObject(NormalExcelConstants.PARAMS, exportParams);
        mv.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<ClkhxxglImport>());
        return mv;

//		 ModelAndView mv = new ModelAndView(new TemplateExcelView());
//		 TemplateExportParams params = new TemplateExportParams();
//		 params.setHeadingRows(1);
//		 params.setHeadingStartRow(3);
//		 Map<String,Object> map = new HashMap<String, Object>();
//		 map.put("year", "2013");
////		 map.put("sunCourses", list.size());
//		 Map<String,Object> obj = new HashMap<String, Object>();
//		 map.put("obj", obj);
////		 obj.put("name", list.size());
//		 List<Clkhxxgl> list = new ArrayList<Clkhxxgl>();
//		 Clkhxxgl clkhxxgl = new Clkhxxgl();
//		 clkhxxgl.setDz("地址2");
//		 clkhxxgl.setKhmc("张三");
//		 clkhxxgl.setKhbh("124223");
//		 clkhxxgl.setZjhm("4123123213");
//		 list.add(clkhxxgl);
//		 params.setTemplateUrl("d:/存量客户.xls");
//		 //导出文件名称
//		 mv.addObject(NormalExcelConstants.FILE_NAME, "存量客户信息管理列表");
//		 mv.addObject(NormalExcelConstants.CLASS, Clkhxxgl.class);
//		 mv.addObject(NormalExcelConstants.PARAMS, params);
//		 mv.addObject("list", list);
//		 mv.addObject("map", map);
//		 return mv;


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
        String filePaths = jsonObject.getString("filePaths");
        if (StringUtils.isEmpty(filePaths)) {
            return Result.error("请先上传文件！");
        }
        String[] filePathList = filePaths.split(",");
        for (String filePath : filePathList) {
            filePath = uploadpath + File.separator + filePath;
            File file = new File(filePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<Clkhxxgl> listClkhxxgls = ExcelImportUtil.importExcel(file, Clkhxxgl.class, params);
                clkhxxglService.saveBatch(listClkhxxgls);
                return Result.ok("文件导入成功！数据行数:" + listClkhxxgls.size());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            }
        }
//      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//          MultipartFile file = entity.getValue();// 获取上传文件对象
//          ImportParams params = new ImportParams();
//          params.setTitleRows(2);
//          params.setHeadRows(1);
//          params.setNeedSave(true);
//          try {
//              List<Clkhxxgl> listClkhxxgls = ExcelImportUtil.importExcel(file.getInputStream(), Clkhxxgl.class, params);
//              clkhxxglService.saveBatch(listClkhxxgls);
//              return Result.ok("文件导入成功！数据行数:" + listClkhxxgls.size());
//          } catch (Exception e) {
//              log.error(e.getMessage(),e);
//              return Result.error("文件导入失败:"+e.getMessage());
//          } finally {
//              try {
//                  file.getInputStream().close();
//              } catch (IOException e) {
//                  e.printStackTrace();
//              }
//          }
//      }
        return Result.ok("文件导入失败！");
    }

    /**
     * 提取数据
     *
     * @return
     */
    @AutoLog(value = "存量客户信息管理-数据提取")
    @ApiOperation(value = "存量客户信息管理-数据提取", notes = "存量客户信息管理-数据提取")
    @GetMapping(value = "/init")
    public Result<?> init(HttpServletRequest request, HttpServletResponse response) {
        try {
            String qybm = sysDicService.queryByCode("101001").getValue();
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                //同步数据到impala
                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_grkhjbxx", "idap");
                EtlUtil.SHcallEtlRc(10, true, false, false, "khxxgl_qykhjbxx", "idap");
                EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clgrkhxx.py'");
                sshUtil.execShell("sh /home/exportdata/P_CLGRKHXX_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_CLGRKHXX_IMPORT.sh");

                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_clqykhxx.py'");
                sshUtil.execShell("sh /home/exportdata/P_CLQYKHXX_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_CLQYKHXX_IMPORT.sh");

                clkhxxglService.initClkhxxRC();
                System.out.println("执行完成");
            } else {
                clkhxxglService.initClkhxx();
            }
        } catch (Exception e) {
            log.error("提取失败", e.getMessage());
            return Result.error("提取失败!");
        }
        return Result.ok("提取成功!");
    }

    /**
     * 配置定时任务自动提取客户花名册信息与存量客户信息
     *
     * @param context
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            clkhxxglService.initKhxx();
        } catch (Exception e) {
            log.error("提取失败", e.getMessage());
        }
    }
}

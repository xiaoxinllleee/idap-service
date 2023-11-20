package org.cmms.modules.xdgl.dksp.dkzqywsp.controller;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Comment;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.ActivitiConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.ImageOverlapUtil;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.activiti.entity.ActBusiness;
import org.cmms.modules.activiti.service.ActBusinessService;
import org.cmms.modules.activiti.service.ActProcessService;
import org.cmms.modules.activiti.service.mybatis.IHistoryIdentityService;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.hr.yggl.ygxxgl.entity.Vhrbasstaffpost;
import org.cmms.modules.hr.yggl.ygxxgl.service.IVhrbasstaffpostService;
import org.cmms.modules.system.entity.*;
import org.cmms.modules.system.service.*;
import org.cmms.modules.util.WaterMarkUtil;
import org.cmms.modules.util.WordUtils;
import org.cmms.modules.xdgl.dksp.dkspkhzl.entity.DkspKhzl;
import org.cmms.modules.xdgl.dksp.dkspkhzl.service.IDkspKhzlService;
import org.cmms.modules.xdgl.dksp.dkspsxsp.entity.DkspSxsp;
import org.cmms.modules.xdgl.dksp.dkzqywsp.entity.CamsZqywspSqxx;
import org.cmms.modules.xdgl.dksp.dkzqywsp.entity.CamsZqywspYwzc;
import org.cmms.modules.xdgl.dksp.dkzqywsp.service.ICamsZqywspSqxxService;
import org.cmms.modules.xdgl.dksp.dkzqywsp.service.ICamsZqywspYwzcService;
import org.cmms.modules.xdgl.dksp.dkzqywsp.vo.CamsZqywspYwzcVo;
import org.cmms.modules.xdgl.dksp.dkzqywsp.vo.DkxxVo;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * @Description: 贷款展期业务审批注册表
 * @Author: jeecg-boot
 * @Date: 2023-10-10
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款展期业务审批注册表")
@RestController
@RequestMapping("/dkzqywsp/camsZqywspYwzc")
public class CamsZqywspYwzcController extends JeecgController<CamsZqywspYwzc, ICamsZqywspYwzcService> {
    @Autowired
    private ICamsZqywspYwzcService camsZqywspYwzcService;
    @Autowired
    private ActProcessService actProcessService;
    @Autowired
    private ActBusinessService actBusinessService;
    @Autowired
    private ICamsZqywspSqxxService camsZqywspSqxxService;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ISysDepartmentSignService sysDepartmentSignService;
    @Autowired
    private ISysUserSignOtherService sysUserSignOtherService;
    @Autowired
    private ISysUserSignService sysUserSignService;
    @Autowired
    private IHistoryIdentityService iHistoryIdentityService;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysDictService sysDictService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private IDkspKhzlService dkspKhzlService;

    /**
     * 分页列表查询
     *
     * @param camsZqywspYwzc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-分页列表查询")
    @ApiOperation(value = "贷款展期业务审批注册表-分页列表查询", notes = "贷款展期业务审批注册表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CamsZqywspYwzc camsZqywspYwzc,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String khmc=camsZqywspYwzc.getKhmc();
        camsZqywspYwzc.setKhmc(null);
        QueryWrapper<CamsZqywspYwzc> queryWrapper = QueryGenerator.initQueryWrapper(camsZqywspYwzc, req.getParameterMap());
        if (StringUtils.isNotBlank(khmc)){
            queryWrapper.like("khmc",khmc);
        }
        Page<CamsZqywspYwzc> page = new Page<CamsZqywspYwzc>(pageNo, pageSize);
        IPage<CamsZqywspYwzc> pageList = camsZqywspYwzcService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    @GetMapping("/getSpxx")
    public Result<?> getSpxx(@RequestParam("ywid") String ywid) {
        if (StringUtils.isNotBlank(ywid)) {
            QueryWrapper<CamsZqywspSqxx> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("ywid", ywid);
            List<CamsZqywspSqxx> list = camsZqywspSqxxService.list(queryWrapper);
            return Result.ok(list);
        }
        return Result.ok();
    }

    /**
     * 添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-添加")
    @ApiOperation(value = "贷款展期业务审批注册表-添加", notes = "贷款展期业务审批注册表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CamsZqywspYwzcVo camsZqywspYwzcVo) {
        CamsZqywspYwzc camsZqywspYwzc=new CamsZqywspYwzc();
        BeanUtils.copyProperties(camsZqywspYwzcVo, camsZqywspYwzc);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        camsZqywspYwzc.setCreateBy(sysUser.getWorkNo());
        camsZqywspYwzc.setCreateTime(new Date());
        String uuid = IdUtil.simpleUUID();
        camsZqywspYwzc.setId(uuid);

        List<DkspKhzl> fjxxList = new ArrayList<>();
        List<DkspKhzl> dkspKhzlList = camsZqywspYwzcVo.getDkspKhzlList();
        for(DkspKhzl dkspKhzl : dkspKhzlList) {
            dkspKhzl.setKhid(uuid);
            dkspKhzl.setZjhm(camsZqywspYwzc.getDkzh());
            dkspKhzl.setScr(getUsername());
            dkspKhzl.setScsj(new Date());
            fjxxList.add(dkspKhzl);
        }
        dkspKhzlService.saveBatch(dkspKhzlList);

        //新增贷款展期业务审批信息进入审批流程
        String businessNumber = "loanDeferApproval";
        String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
        JSONObject jsonObject = new JSONObject();
        //需要审批
        jsonObject.put("process", true);
        camsZqywspYwzc.setBusinessNumber(businessNumber);
        camsZqywspYwzc.setProcessId(processId);
        // 提交申请流程
        ActBusiness actBusiness = new ActBusiness();
        // 删除标识(0 正常 1 已删除)
        actBusiness.setDelFlag(0);
        actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
        actBusiness.setProcDefId(processId);
        // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
        actBusiness.setResult(0);
        // 处理状态(0 默认草稿 1 处理中 2 结束)
        actBusiness.setStatus(0);
        actBusiness.setTableId(uuid);
        String title = String.format("%s发起的贷款展期业务审批申请[申请人：%s]",
                getLoginUser().getRealname(), camsZqywspYwzc.getKhmc());
        actBusiness.setTitle(title);
        actBusiness.setUserId(getLoginUser().getId());
        actBusiness.setCreateBy(getLoginUser().getUsername());
        actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
        actBusinessService.save(actBusiness);
        camsZqywspYwzcService.save(camsZqywspYwzc);

        return Result.ok("添加成功！", jsonObject);
    }

    /**
     * 编辑
     *
     * @param
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-编辑")
    @ApiOperation(value = "贷款展期业务审批注册表-编辑", notes = "贷款展期业务审批注册表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CamsZqywspYwzcVo camsZqywspYwzcVo) {
        CamsZqywspYwzc camsZqywspYwzc=new CamsZqywspYwzc();
        BeanUtils.copyProperties(camsZqywspYwzcVo, camsZqywspYwzc);

        //是否删除附件
        if(camsZqywspYwzcVo.getDeleteFiles() != null && !camsZqywspYwzcVo.getDeleteFiles().isEmpty()) {
            dkspKhzlService.removeByIds(camsZqywspYwzcVo.getDeleteFiles());
        }
        List<DkspKhzl> dkspKhzlList = camsZqywspYwzcVo.getDkspKhzlList();
        for(DkspKhzl dkspKhzl : dkspKhzlList) {
            dkspKhzl.setKhid(camsZqywspYwzc.getId());
            dkspKhzl.setZjhm(camsZqywspYwzc.getDkzh());
            dkspKhzl.setScr(getUsername());
            dkspKhzl.setScsj(new Date());
        }
        dkspKhzlService.saveBatch(dkspKhzlList);

        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        camsZqywspYwzc.setUpdateBy(sysUser.getWorkNo());
        camsZqywspYwzc.setUpdateTime(new Date());
        camsZqywspYwzcService.updateById(camsZqywspYwzc);

        JSONObject jsonObject = new JSONObject();
        //判断是否已经进入审批流程
        List<ActBusiness> actBusinessList = actBusinessService.findByTableId(camsZqywspYwzc.getId());
        if (actBusinessList.isEmpty()) {
            String businessNumber = "loanDeferApproval";
            String processId = actProcessService.findByProcessKeyAndLatest(businessNumber, true).getId();
            //需要审批
            jsonObject.put("process", true);
            camsZqywspYwzc.setBusinessNumber(businessNumber);
            camsZqywspYwzc.setProcessId(processId);
            // 提交申请流程
            ActBusiness actBusiness = new ActBusiness();
            // 删除标识(0 正常 1 已删除)
            actBusiness.setDelFlag(0);
            actBusiness.setApplyTime(new Timestamp(System.currentTimeMillis()));
            actBusiness.setProcDefId(processId);
            // 结果状态(0 默认未提交 1 处理中 2 通过 3 驳回)
            actBusiness.setResult(0);
            // 处理状态(0 默认草稿 1 处理中 2 结束)
            actBusiness.setStatus(0);
            actBusiness.setTableId(camsZqywspYwzc.getId());
            String title = String.format("%s发起的贷款展期业务审批申请[申请人：%s]",
                    getLoginUser().getRealname(), camsZqywspYwzc.getKhmc());
            actBusiness.setTitle(title);
            actBusiness.setUserId(getLoginUser().getId());
            actBusiness.setCreateBy(getLoginUser().getUsername());
            actBusiness.setCreateTime(new Timestamp(System.currentTimeMillis()));
            actBusinessService.save(actBusiness);
        } else {
            //如果是驳回状态，需要重新申请
            if (actBusinessList.get(0).getResult() == ActivitiConstant.RESULT_FAIL) {
                jsonObject.put("process", true);
            } else {
                jsonObject.put("process", false);
            }
        }
        return Result.ok("编辑成功!", jsonObject);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-通过id删除")
    @ApiOperation(value = "贷款展期业务审批注册表-通过id删除", notes = "贷款展期业务审批注册表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        camsZqywspYwzcService.removeById(id);
        QueryWrapper<CamsZqywspSqxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ywid", id);
        camsZqywspSqxxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-批量删除")
    @ApiOperation(value = "贷款展期业务审批注册表-批量删除", notes = "贷款展期业务审批注册表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.camsZqywspYwzcService.removeByIds(Arrays.asList(ids.split(",")));
        QueryWrapper<CamsZqywspSqxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("ywid", Arrays.asList(ids.split(",")));
        camsZqywspSqxxService.remove(queryWrapper);
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款展期业务审批注册表-通过id查询")
    @ApiOperation(value = "贷款展期业务审批注册表-通过id查询", notes = "贷款展期业务审批注册表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CamsZqywspYwzc camsZqywspYwzc = camsZqywspYwzcService.getById(id);
        return Result.ok(camsZqywspYwzc);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param camsZqywspYwzc
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CamsZqywspYwzc camsZqywspYwzc) {
        return super.exportXls(request, camsZqywspYwzc, CamsZqywspYwzc.class, "贷款展期业务审批注册表");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CamsZqywspYwzc.class);
    }

    /**
     * 根据借款人和贷款账户获取贷款相关信息
     *
     * @param khmc
     * @param dkzh
     * @return
     */
    @GetMapping("getDkxx")
    public Result<?> getDkxx(@RequestParam(name = "khmc", required = false) String khmc,
                             @RequestParam(name = "dkzh", required = false) String dkzh,
                             @RequestParam(name = "zjhm", required = false) String zjhm) {
        List<DkxxVo> list = service.getDkxx(khmc, dkzh,zjhm);
        return Result.ok(list);
    }

    /**
     * 下载贷款展期业务审批表
     */
    @RequestMapping(value = "/download")
    public void download(CamsZqywspYwzc camsZqywspYwzc, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        OutputStream pdfOutPutStream = null;
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("khmc", camsZqywspYwzc.getKhmc());
            data.put("dkzh", camsZqywspYwzc.getDkzh());
            String dkzl=StringUtils.isNotBlank(camsZqywspYwzc.getDkzl()) ? sysDictService.queryDictTextByKey("dkzl", camsZqywspYwzc.getDkzl()) : "";
            data.put("dkzl", StringUtils.isNotBlank(dkzl) ? dkzl : "");
            data.put("yhtbh", camsZqywspYwzc.getYhtbh());
            data.put("ydkrq",camsZqywspYwzc.getYdkrq()==null?"":DateUtil.format(camsZqywspYwzc.getYdkrq(),"yyyy-MM-dd"));
            data.put("ydkje", camsZqywspYwzc.getYdkje()==null?"": Convert.digitToChinese(camsZqywspYwzc.getYdkje().multiply(new BigDecimal(10000))));
            data.put("ydqrq", camsZqywspYwzc.getYdqrq()==null?"":DateUtil.format(camsZqywspYwzc.getYdqrq(),"yyyy-MM-dd"));
            data.put("je", camsZqywspYwzc.getSqzqje()==null?"": Convert.digitToChinese(camsZqywspYwzc.getSqzqje().multiply(new BigDecimal(10000))));
            data.put("ll", camsZqywspYwzc.getLl());
            data.put("bszrr", camsZqywspYwzc.getBszrr());
            data.put("glzrr", camsZqywspYwzc.getGlzrr());
            data.put("sqzqhkly", camsZqywspYwzc.getSqzqhkly());
            QueryWrapper<CamsZqywspSqxx> camsZqywspSqxxQueryWrapper = new QueryWrapper<>();
            camsZqywspSqxxQueryWrapper.eq("ywid", camsZqywspYwzc.getId());
            data.put("sqzqqx",camsZqywspYwzc.getSqzqqx());
            data.put("rq",DateUtil.format(camsZqywspYwzc.getSqzqrq(),"yyyy-MM-dd"));
            data.put("sszh",sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", camsZqywspYwzc.getSszh()));
            //银行名称
            data.put("bankName", sysDicService.queryByCode("101002").getValue());
            //审批日期
            data.put("date", DateUtil.formatDateTime("yyyy年MM月dd日"));
            //查询审批历史
            data.put("spList", getApprovalHistory(camsZqywspYwzc.getId()));

            String docFileName = IdUtil.simpleUUID() + "展期业务审批表.docx";
            String docFilePath = uploadpath + File.separator + "dkzqywsp" + File.separator + docFileName;
            FileUtil.mkParentDirs(docFilePath);
            String resourceName = "展期业务审批表.ftl";
            WordUtils.generateWord(data, docFilePath, resourceName);

            inputStream = new FileInputStream(docFilePath);
            String pdfFileName = IdUtil.simpleUUID() + "展期业务审批表.pdf";
            String pdfFilePath = uploadpath + File.separator + "dkzqywsp" + File.separator + pdfFileName;

            pdfOutPutStream = new FileOutputStream(pdfFilePath);
            //转换成pdf文件
            IConverter converter = LocalConverter.builder().build();
            converter.convert(inputStream).as(DocumentType.DOCX).to(pdfOutPutStream).as(DocumentType.PDF).execute();
            String waterFileName = IdUtil.simpleUUID() + "展期业务审批表_水印.pdf";
            String waterFilePath = uploadpath + File.separator + "dkzqywsp" + File.separator + waterFileName;
            WaterMarkUtil.markPdf(pdfFilePath, waterFilePath, getLoginUser().getRealname() + getLoginUser().getWorkNo());

            FileInputStream fileInputStream = new FileInputStream(waterFilePath);
            byte[] bys = new byte[fileInputStream.available()];
            fileInputStream.read(bys);
            response.setContentType("application/force-download");// 设置强制下载不打开            
            response.addHeader("Content-Disposition", "attachment;fileName=" + new String(pdfFileName.getBytes("UTF-8"), "iso-8859-1"));
            outputStream = response.getOutputStream();
            outputStream.write(bys);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(inputStream);
            IoUtil.close(outputStream);
            IoUtil.close(pdfOutPutStream);
        }
    }

    public List getApprovalHistory(String tableId) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        List<ActBusiness> actBusinessesList = actBusinessService.findByTableId(tableId);
        String id = actBusinessesList.get(0).getProcInstId();
        List<HistoricTaskInstance> taskList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(id).orderByHistoricTaskInstanceEndTime().asc().list();

        taskList.forEach(e -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("sprq", DateUtil.format(e.getEndTime(), "yyyy年MM月dd日"));
            // 获取实际审批用户id
            String userId = iHistoryIdentityService.findUserIdByTypeAndTaskId(ActivitiConstant.EXECUTOR_TYPE, e.getId());
            //获取用户部门
            SysUser u = userService.getById(userId);
            HrBasStaffPost hrBasStaffPost=hrBasStaffPostService.getStaffPostInfoBySprq(u.getWorkNo(),DateUtil.format(e.getEndTime(),"yyyyMMdd"));
            map.put("realname", u.getRealname());
            map.put("username", u.getUsername());
            map.put("spbm", sysDictService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", hrBasStaffPost.getZzbz()));

            // 关联审批意见
            List<Comment> comments = taskService.getTaskComments(e.getId(), "comment");
            if (comments != null && comments.size() > 0) {
                map.put("spyj", comments.get(0).getFullMessage());
            } else {
                map.put("spyj", "");
            }
            //获取部门印章图片
            QueryWrapper<SysDepartmentSign> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("depart_id", hrBasStaffPost.getZzbz());
            SysDepartmentSign sysDepartmentSign = sysDepartmentSignService.getOne(queryWrapper, false);
            //获取用户签名图片
            QueryWrapper<SysUserSignOther> sysUserSignOtherQueryWrapper = new QueryWrapper<>();
            sysUserSignOtherQueryWrapper.eq("user_id", userId);
            SysUserSignOther sysUserSignOther = sysUserSignOtherService.getOne(sysUserSignOtherQueryWrapper, false);
            if (sysUserSignOther != null && sysDepartmentSign != null) {
                String dirPath = uploadpath + File.separator + "spyhqmyz";
                String savePath = uploadpath + File.separator + "spyhqmyz" + File.separator + "userSign" + DateUtil.format(new Date(), "yyyyMMddHHmmssSSS") + ".png";
                log.info("===============图片保存路径==========" + savePath);
                log.info("===============部门印章图片==========" + uploadpath + File.separator + sysDepartmentSign.getFwlj());
                log.info("===============用户签名图片==========" + uploadpath + File.separator + sysUserSignOther.getFwlj());
                ImageOverlapUtil.getTwoImageOverlap(uploadpath + File.separator + sysDepartmentSign.getFwlj(), uploadpath + File.separator + sysUserSignOther.getFwlj(), savePath, dirPath);
                map.put("image", getImageBase64String(savePath));
            } else {
                map.put("image", "");
                QueryWrapper<SysUserSign> userSignQueryWrapper = new QueryWrapper<>();
                userSignQueryWrapper.eq("user_id", userId);
                SysUserSign sysUserSign = sysUserSignService.getOne(userSignQueryWrapper);
                if (sysUserSign != null) {
                    String signPath = uploadpath + File.separator + sysUserSign.getFwlj();
                    map.put("image", getImageBase64String(signPath));
                }
            }
            list.add(map);
        });
        return list;
    }

    public static String getImageBase64String(String imageFile) {
        if (org.apache.commons.lang.StringUtils.isEmpty(imageFile)) {
            return "";
        }
        File file = new File(imageFile);
        if (!file.exists()) {
            return "";
        }
        InputStream is = null;
        byte[] data = null;
        try {
            is = new FileInputStream(file);
            data = new byte[is.available()];
            is.read(data);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}

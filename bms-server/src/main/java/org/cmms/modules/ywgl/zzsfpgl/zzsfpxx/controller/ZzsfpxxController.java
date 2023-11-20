package org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.controller;

import java.io.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.report.cwbb.bwkmb.entity.Bwkmb;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.entity.Zzsfpxx;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.entity.ZzsfpxxVO;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.service.IZzsfpxxService;
import org.cmms.modules.ywgl.zzsfpgl.zzsfpxx.verify.ZzsfpxxImportVerify;
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
 * @Description: 增值税发票信息
 * @Author: jeecg-boot
 * @Date: 2021-10-08
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "增值税发票信息")
@RestController
@RequestMapping("/zzsfpxx/zzsfpxx")
public class ZzsfpxxController extends JeecgController<Zzsfpxx, IZzsfpxxService> {
    @Autowired
    private IZzsfpxxService zzsfpxxService;
    @Autowired
    private ZzsfpxxImportVerify zzsfpxxImportVerify;

    /**
     * 分页列表查询
     *
     * @param zzsfpxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "增值税发票信息-分页列表查询")
    @ApiOperation(value = "增值税发票信息-分页列表查询", notes = "增值税发票信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zzsfpxx zzsfpxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req,
                                   String flag) {
        QueryWrapper<Zzsfpxx> queryWrapper = QueryGenerator.initQueryWrapper(zzsfpxx, req.getParameterMap());
        if ("false".equals(flag) && !"admin".equals(getLoginUser().getUsername())) {
            queryWrapper.eq("lryhm",getLoginUser().getUsername());
        }
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IZzsfpxxService.class, zzsfpxxService, pageNo, pageSize, queryWrapper, "fphm", "hydm");
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param zzsfpxx
     * @return
     */
    @AutoLog(value = "增值税发票信息-添加")
    @ApiOperation(value = "增值税发票信息-添加", notes = "增值税发票信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zzsfpxx zzsfpxx) {
        QueryWrapper<Zzsfpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fphm", zzsfpxx.getFphm());
        queryWrapper.eq("hydm", zzsfpxx.getHydm());
        Zzsfpxx zzsfpxx1 = zzsfpxxService.getOne(queryWrapper);
        if (zzsfpxx1 != null) {
            return Result.error("信息重复预警，[" + zzsfpxx1.getLrr() + "]用户已录入！");
        }
        System.out.println(zzsfpxx.getKprq());
        String format = DateUtil.format(zzsfpxx.getKprq(),"yyyy-MM-dd");
        Date kprq = DateUtil.parse(format);
        System.out.println(format);
        System.out.println(kprq);
        zzsfpxx.setKprq(kprq);
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        zzsfpxx.setLrr(sysUser.getRealname());
        zzsfpxx.setLryhm(sysUser.getUsername());
        zzsfpxx.setLrsj(new Date());
        zzsfpxxService.save(zzsfpxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zzsfpxx
     * @return
     */
    @AutoLog(value = "增值税发票信息-编辑")
    @ApiOperation(value = "增值税发票信息-编辑", notes = "增值税发票信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zzsfpxx zzsfpxx) {
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        zzsfpxx.setXgsj(new Timestamp(System.currentTimeMillis()));
        zzsfpxx.setXgr(sysUser.getUsername());
        QueryWrapper<Zzsfpxx> queryWrapper = new QueryWrapper<Zzsfpxx>();
        queryWrapper.eq("fphm", zzsfpxx.getFphm());
        queryWrapper.eq("hydm", zzsfpxx.getHydm());
        zzsfpxx.setFphm(null);
        zzsfpxx.setHydm(null);
        zzsfpxxService.update(zzsfpxx, queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param form
     * @return
     */
    @AutoLog(value = "增值税发票信息-通过id删除")
    @ApiOperation(value = "增值税发票信息-通过id删除", notes = "增值税发票信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(Zzsfpxx form) {
        QueryWrapper<Zzsfpxx> queryWrapper = new QueryWrapper<Zzsfpxx>();
        queryWrapper.eq("fphm", form.getFphm());
        queryWrapper.eq("hydm", form.getHydm());
        zzsfpxxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "增值税发票信息-批量删除")
    @ApiOperation(value = "增值税发票信息-批量删除", notes = "增值税发票信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zzsfpxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "增值税发票信息-通过id查询")
    @ApiOperation(value = "增值税发票信息-通过id查询", notes = "增值税发票信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Zzsfpxx zzsfpxx = zzsfpxxService.getById(id);
        return Result.ok(zzsfpxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zzsfpxx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zzsfpxx zzsfpxx) {
        return super.exportXls(request, zzsfpxx, Zzsfpxx.class, "增值税发票信息");
    }

    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(HttpServletRequest request, HttpServletResponse response) {
        //return super.exportTemplateXls(SsglVO.class, "诉讼管理导入模板");
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "增值税发票信息导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ZzsfpxxVO.class);
        ExportParams exportParams = new ExportParams("增值税发票信息导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
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
//          MultipartFile file = entity.getValue();// 获取上传文件对象
            File file = new File(baseFilePath);
            ImportParams params = new ImportParams();
            params.setTitleRows(1);
            params.setHeadRows(1);
            params.setNeedSave(false);
            if (zzsfpxxImportVerify != null) {
                params.setVerifyHanlder(zzsfpxxImportVerify);
            }
            FileInputStream fis = null;
            FileOutputStream fos = null;

            try {
                fis = new FileInputStream(file);
//                boolean checkResult = ExcelImportCheckUtil.check(fis, Zzsfpxx.class, params, 1.0);
//                if (!checkResult) {
//                    return Result.error("导入文件表头与模板文件不符，请下载导入模板文件进行导入！");
//                }
                ExcelImportResult<Zzsfpxx> importResult = ExcelImportUtil.importExcelVerify(file, Zzsfpxx.class, ZzsfpxxVO.class, params);
                List<Zzsfpxx> list = importResult.getList();
                List<Zzsfpxx> zzsfpxxList = new ArrayList<>();
                for (Zzsfpxx zzsfpxx : list) {
                    zzsfpxx.setLrr(sysUser.getRealname());
                    zzsfpxx.setLryhm(sysUser.getUsername());
                    zzsfpxx.setLrsj(new Timestamp(System.currentTimeMillis()));
                    zzsfpxxList.add(zzsfpxx);
                }
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
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }

}

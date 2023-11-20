package org.cmms.modules.sjbl.farwsz.controller;

import java.io.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.ExcelImportCheckUtil;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.StringUtils;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.hr.yggl.ygrggl.entity.HrBasStaffPost;
import org.cmms.modules.hr.yggl.ygrggl.service.IHrBasStaffPostService;
import org.cmms.modules.khlc.jczbgl.entity.ErpBasZbk;
import org.cmms.modules.khlc.jczbgl.service.IErpBasZbkService;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import org.cmms.modules.khlc.khfagl.service.IPmaASchemeService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.khlc.zbljgl.service.IErpBasSjxAreaService;
import org.cmms.modules.sjbl.farwsz.entity.*;
import org.cmms.modules.sjbl.farwsz.service.IErpAssessTasksetService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.result.ExcelImportResult;

import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 方案任务设置
 * @Author: jeecg-boot
 * @Date: 2023-03-22
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "方案任务设置")
@RestController
@RequestMapping("/farwsz/erpAssessTaskset")
public class ErpAssessTasksetController extends JeecgController<ErpAssessTaskset, IErpAssessTasksetService> {
    @Autowired
    private IErpAssessTasksetService erpAssessTasksetService;
    @Autowired
    private IErpBasSjxAreaService erpBasSjxAreaService;
    @Autowired
    private IHrBasStaffPostService hrBasStaffPostService;
    @Autowired
    private IErpBasZbkService erpBasZbkService;
    /**
     * 机构分页列表查询
     *
     * @param erpAssessTaskset
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "方案任务设置-分页列表查询")
    @ApiOperation(value = "方案任务设置-分页列表查询", notes = "方案任务设置-分页列表查询")
    @GetMapping(value = "/listJG")
    public Result<?> queryPageListJG(ErpAssessTaskset erpAssessTaskset,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<ErpAssessTaskset> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessTaskset, req.getParameterMap());
        queryWrapper.isNull("gwbz");
        Page<ErpAssessTaskset> page = new Page<ErpAssessTaskset>(pageNo, pageSize);
        IPage<ErpAssessTaskset> pageList = erpAssessTasksetService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 岗位分页列表查询
     *
     * @param erpAssessTaskset
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "方案任务设置-分页列表查询")
    @ApiOperation(value = "方案任务设置-分页列表查询", notes = "方案任务设置-分页列表查询")
    @GetMapping(value = "/listGW")
    public Result<?> queryPageListGW(ErpAssessTaskset erpAssessTaskset,
                                     @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                     HttpServletRequest req) {
        QueryWrapper<ErpAssessTaskset> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessTaskset, req.getParameterMap());
        queryWrapper.isNotNull("gwbz");
        Page<ErpAssessTaskset> page = new Page<ErpAssessTaskset>(pageNo, pageSize);
        IPage<ErpAssessTaskset> pageList = erpAssessTasksetService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 岗位查找带回
     *
     * @param
     * @return
     */
    @AutoLog(value = "方案任务设置岗位-列表查询")
    @ApiOperation(value = "方案任务设置岗位-列表查询岗位", notes = "方案任务设置岗位-列表查询")
    @GetMapping(value = "/listTasksetGw")
    public Result<?> listTasksetGw(ErpBasSjxArea erpBasSjxArea,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String zbbm	=erpBasSjxArea.getZbbm();
        erpBasSjxArea.setZbbm(null);
        QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
        QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
        if (StringUtils.isNotBlank(zbbm)){
            zbk.like("zbmc",zbbm);
            List<ErpBasZbk> list = erpBasZbkService.list(zbk);
            List<String> list1 = new ArrayList<>();
            if (CollUtil.isNotEmpty(list)){
                for (ErpBasZbk erpBasZbk : list) {
                    list1.add(erpBasZbk.getZbid());
                }
                queryWrapper.in("zbid",list1);
            }
        }
        queryWrapper.eq("zblx", 3);//岗位
        queryWrapper.eq("sfqy", '1');//是启用
        Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
        IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 岗位回显table
     *
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/listTasksetGwAll")
    public Result<?> listTasksetGwAll(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String zbid = JSONObject.toJSONString(jsonArray);
        String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"", "");
        String[] zbidList = zbid2.split(",");

        List<ErpBasSjxArea> list2 = new ArrayList<>();
        for (String s : zbidList) {
            List<ErpBasSjxArea> list = erpAssessTasksetService.tasksetGw(s.trim());
            list2.addAll(list);
        }
        return Result.ok(list2);
    }

    /**
     * 方案任务设置岗位-添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "方案任务设置岗位-添加")
    @ApiOperation(value = "方案任务设置岗位-添加", notes = "方案任务设置岗位-添加")
    @PostMapping(value = "/GwSave")
    public Result<?> GwSave(@RequestBody JSONObject jsonObject) {
        String zzbz = jsonObject.getString("zzbzLsit");
        String[] zzbzList = zzbz.split(",");

        String gwbz = jsonObject.getString("gwbzLsit");
        String[] gwbzList = gwbz.split(",");

        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String js = JSONObject.toJSONString(jsonArray);
        String schemeId = jsonObject.getString("schemeId");
        String khsj = jsonObject.getString("khsj");
        DateTime parse = DateUtil.parse(khsj);
        //考核日期转当月第一天
        DateTime firstDate = DateUtil.beginOfMonth(parse);
        System.out.println(firstDate + "----------考核日期转当月第一天");
        //考核时间转换为月末
        DateTime dateTime = DateUtil.endOfMonth(parse);
        String format = DateUtil.format(dateTime, "yyyy-MM-dd");
        DateTime lastDate = DateUtil.parse(format);
        System.out.println(lastDate + "-------考核时间转换为月末");

        List<ErpAssessTaskset> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessTaskset.class);
        //获取岗位员工
        QueryWrapper<HrBasStaffPost> staffPostQueryWrapper = new QueryWrapper<HrBasStaffPost>();
        staffPostQueryWrapper.in("zzbz", zzbzList);
        staffPostQueryWrapper.in("gwbz", gwbzList);
        staffPostQueryWrapper.eq("sfcykh", 1);
        staffPostQueryWrapper.le("rgrq", lastDate);
        staffPostQueryWrapper.apply(" (lgrq is null or lgrq >= {0})", firstDate);
        List<HrBasStaffPost> hrBasStaffPostList = hrBasStaffPostService.list(staffPostQueryWrapper);

        List<ErpAssessTaskset> insertList = new ArrayList<>();
        for (ErpAssessTaskset s : erpAssessAljcs) {//遍历指标
            //先删除数据
            QueryWrapper<ErpAssessTaskset> deleteWrapper = new QueryWrapper();
            deleteWrapper.eq("scheme_id", schemeId);
            deleteWrapper.in("zzbz", zzbzList);
            deleteWrapper.in("gwbz", gwbzList);
            deleteWrapper.eq("khfs", s.getKhfs());
            deleteWrapper.eq("khwd", s.getKhwd());
            deleteWrapper.eq("khsj", parse);
            deleteWrapper.eq("zbid", s.getZbid());
            erpAssessTasksetService.remove(deleteWrapper);

            for (HrBasStaffPost hrBasStaffPost : hrBasStaffPostList) {
                ErpAssessTaskset erpAssessTaskset = new ErpAssessTaskset();
                erpAssessTaskset.setId(IdUtil.simpleUUID());
                erpAssessTaskset.setSchemeId(schemeId);
                erpAssessTaskset.setZzbz(hrBasStaffPost.getZzbz());
                erpAssessTaskset.setGwbz(hrBasStaffPost.getGwbz());
                erpAssessTaskset.setYggh(hrBasStaffPost.getYggh());
                erpAssessTaskset.setZbid(s.getZbid());
                erpAssessTaskset.setKhwd(s.getKhwd());
                erpAssessTaskset.setKhsj(parse);
                erpAssessTaskset.setKhfs(s.getKhfs());
                erpAssessTaskset.setZbrw(s.getZbrw());
                erpAssessTaskset.setCreateBy(getUsername());
                erpAssessTaskset.setCreateTime(new Date());
                insertList.add(erpAssessTaskset);
            }
        }
        erpAssessTasksetService.saveBatch(insertList);
        return Result.ok("添加成功！");
    }

    /**
     * 机构查找带回
     *
     * @param
     * @return
     */
    @AutoLog(value = "方案任务设置机构-列表查询")
    @ApiOperation(value = "方案任务设置机构-列表查询岗位", notes = "方案任务设置机构-列表查询")
    @GetMapping(value = "/listTasksetJg")
    public Result<?> listTasksetJg(ErpBasSjxArea erpBasSjxArea,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        String zbbm	=erpBasSjxArea.getZbbm();
        erpBasSjxArea.setZbbm(null);
        QueryWrapper<ErpBasSjxArea> queryWrapper = QueryGenerator.initQueryWrapper(erpBasSjxArea, req.getParameterMap());
        QueryWrapper<ErpBasZbk> zbk = new QueryWrapper<>();
        if (StringUtils.isNotBlank(zbbm)){
            zbk.like("zbmc",zbbm);
            List<ErpBasZbk> list = erpBasZbkService.list(zbk);
            List<String> list1 = new ArrayList<>();
            if (CollUtil.isNotEmpty(list)){
                for (ErpBasZbk erpBasZbk : list) {
                    list1.add(erpBasZbk.getZbid());
                }
                queryWrapper.in("zbid",list1);
            }
        }
        queryWrapper.eq("zblx", 1);//机构
        queryWrapper.eq("sfqy", '1');//是启用
        Page<ErpBasSjxArea> page = new Page<ErpBasSjxArea>(pageNo, pageSize);
        IPage<ErpBasSjxArea> pageList = erpBasSjxAreaService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 机构回显table
     *
     * @param jsonObject
     * @return
     */
    @PostMapping(value = "/listTasksetJgAll")
    public Result<?> listTasksetJgAll(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String zbid = JSONObject.toJSONString(jsonArray);
        String zbid2 = zbid.substring(1).replaceAll("]", "").replaceAll("\"", "");
        String[] zbidList = zbid2.split(",");
        List<ErpBasSjxArea> list2 = new ArrayList<>();
        for (String s : zbidList) {
            List<ErpBasSjxArea> list = erpAssessTasksetService.tasksetJg(s.trim());
            list2.addAll(list);
        }
        return Result.ok(list2);
    }

    /**
     * 方案任务设置机构-添加
     *
     * @param
     * @return
     */
    @AutoLog(value = "方案任务设置机构-添加")
    @ApiOperation(value = "方案任务设置机构-添加", notes = "方案任务设置机构-添加")
    @PostMapping(value = "/JgSave")
    public Result<?> JgSave(@RequestBody JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("zbidList");
        String js = JSONObject.toJSONString(jsonArray);
        String schemeId = jsonObject.getString("schemeId");
        String khsj = jsonObject.getString("khsj");
        DateTime parse = DateUtil.parse(khsj);


        String zzbz = jsonObject.getString("zzbzLsit");
        String[] zzbzList = zzbz.split(",");

        List<ErpAssessTaskset> erpAssessAljcs = JSONObject.parseArray(js, ErpAssessTaskset.class);

        List<ErpAssessTaskset> saveList = new ArrayList<>();
        //遍历组织标识
        for (String bz : zzbzList) {
            for (ErpAssessTaskset s : erpAssessAljcs) {//遍历指标
                QueryWrapper<ErpAssessTaskset> queryWrapper = new QueryWrapper();
                queryWrapper.eq("scheme_id", schemeId);
                queryWrapper.eq("zzbz", bz.trim());
                queryWrapper.eq("khwd", s.getKhwd());
                queryWrapper.eq("khfs", s.getKhfs());
                queryWrapper.eq("khsj", parse);
                queryWrapper.in("zbid", s.getZbid());
                List<ErpAssessTaskset> list = erpAssessTasksetService.list(queryWrapper);
                if (CollUtil.isNotEmpty(list)) {
                    erpAssessTasksetService.remove(queryWrapper);
                }
                if (s.getZbrw() != null) {
                    ErpAssessTaskset erpAssessTaskset = new ErpAssessTaskset();
                    erpAssessTaskset.setId(IdUtil.simpleUUID());
                    erpAssessTaskset.setZzbz(bz.trim());
                    erpAssessTaskset.setZbid(s.getZbid());
                    erpAssessTaskset.setSchemeId(schemeId);
                    erpAssessTaskset.setKhwd(s.getKhwd());
                    erpAssessTaskset.setKhsj(parse);
                    erpAssessTaskset.setKhfs(s.getKhfs());
                    erpAssessTaskset.setZbrw(s.getZbrw());
                    erpAssessTaskset.setCreateBy(getUsername());
                    erpAssessTaskset.setCreateTime(new Date());
                    saveList.add(erpAssessTaskset);
                }
            }
        }
        if (!saveList.isEmpty()) {
            erpAssessTasksetService.saveBatch(saveList);
        }
        return Result.ok("添加成功！");
    }

    /**
     * 添加
     *
     * @param erpAssessTaskset
     * @return
     */
    @AutoLog(value = "方案任务设置-添加")
    @ApiOperation(value = "方案任务设置-添加", notes = "方案任务设置-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ErpAssessTaskset erpAssessTaskset) {
        QueryWrapper<ErpAssessTaskset> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("scheme_id", erpAssessTaskset.getSchemeId());
        queryWrapper.eq("zzbz", erpAssessTaskset.getZzbz());
        if (erpAssessTaskset.getGwbz() != null) {
            queryWrapper.eq("gwbz", erpAssessTaskset.getGwbz());
        }
        queryWrapper.eq("zbid", erpAssessTaskset.getZbid());
        queryWrapper.eq("khwd", erpAssessTaskset.getKhwd());
        queryWrapper.eq("khfs", erpAssessTaskset.getKhfs());
        queryWrapper.eq("khsj", erpAssessTaskset.getKhsj());
        List<ErpAssessTaskset> list = erpAssessTasksetService.list(queryWrapper);
        if (!list.isEmpty()) {
            return Result.error("已经存在的数据！");
        }
        erpAssessTasksetService.save(erpAssessTaskset);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param erpAssessTaskset
     * @return
     */
    @AutoLog(value = "方案任务设置-编辑")
    @ApiOperation(value = "方案任务设置-编辑", notes = "方案任务设置-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ErpAssessTaskset erpAssessTaskset) {
        erpAssessTasksetService.updateById(erpAssessTaskset);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "方案任务设置-通过id删除")
    @ApiOperation(value = "方案任务设置-通过id删除", notes = "方案任务设置-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        erpAssessTasksetService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "方案任务设置-批量删除")
    @ApiOperation(value = "方案任务设置-批量删除", notes = "方案任务设置-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.erpAssessTasksetService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "方案任务设置-通过id查询")
    @ApiOperation(value = "方案任务设置-通过id查询", notes = "方案任务设置-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        ErpAssessTaskset erpAssessTaskset = erpAssessTasksetService.getById(id);
        return Result.ok(erpAssessTaskset);
    }

    /**
     * 导出机构excel
     *
     * @param request
     * @param erpAssessTaskset
     */
    @RequestMapping(value = "/exportXlsJG")
    public ModelAndView exportXlsJG(HttpServletRequest request, ErpAssessTaskset erpAssessTaskset) {
        QueryWrapper<ErpAssessTaskset> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessTaskset, request.getParameterMap());
        queryWrapper.isNull("gwbz");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("ID", selectionList);
            }
        }

        List<ErpAssessTaskset> list = service.list(queryWrapper);
        List<ErpAssessTasksetExportJGVO> exportListJg = new ArrayList<>();
        for (ErpAssessTaskset erpAssessTaskset1 : list) {
            ErpAssessTasksetExportJGVO exportJGVO = new ErpAssessTasksetExportJGVO();
            BeanUtils.copyProperties(erpAssessTaskset1, exportJGVO);
            exportListJg.add(exportJGVO);
        }
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "机构任务设置");
        mv.addObject(NormalExcelConstants.CLASS, ErpAssessTasksetExportJGVO.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("机构任务设置" + "报表", "导出人:" + sysUser.getRealname(), "机构任务设置"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportListJg);
        return mv;
    }


    /**
     * 导出岗位excel
     *
     * @param request
     * @param erpAssessTaskset
     */
    @RequestMapping(value = "/exportXlsGW")
    public ModelAndView exportXlsGW(HttpServletRequest request, ErpAssessTaskset erpAssessTaskset) {
        QueryWrapper<ErpAssessTaskset> queryWrapper = QueryGenerator.initQueryWrapper(erpAssessTaskset, request.getParameterMap());
        queryWrapper.isNotNull("gwbz");
        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String selections = request.getParameter("selections");
        String rowKey = request.getParameter("rowKey");

        if (oConvertUtils.isNotEmpty(selections)) {
            List<String> selectionList = Arrays.asList(selections.split(","));
            if (oConvertUtils.isNotEmpty(rowKey)) {
                queryWrapper.in(rowKey, selectionList);
            } else {
                queryWrapper.in("ID", selectionList);
            }
        }

        List<ErpAssessTaskset> list = service.list(queryWrapper);
        List<ErpAssessTasksetExportGWVO> exportListGw = new ArrayList<>();
        for (ErpAssessTaskset erpAssessTaskset1 : list) {
            ErpAssessTasksetExportGWVO exportGWVO = new ErpAssessTasksetExportGWVO();
            BeanUtils.copyProperties(erpAssessTaskset1, exportGWVO);
            exportListGw.add(exportGWVO);
        }
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        mv.addObject(NormalExcelConstants.FILE_NAME, "岗位任务设置");
        mv.addObject(NormalExcelConstants.CLASS, ErpAssessTasksetExportGWVO.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("岗位任务设置" + "报表", "导出人:" + sysUser.getRealname(), "岗位任务设置"));
        mv.addObject(NormalExcelConstants.DATA_LIST, exportListGw);
        return mv;
    }


    /**
     * 导出机构模板
     */
    @RequestMapping(value = "/exportTemplateXlsJG")
    public ModelAndView exportTemplateXlsJG(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "机构任务设置导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessTasksetImportJGVO.class);
        ExportParams exportParams = new ExportParams("机构任务设置导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }


    /**
     * 导出岗位模板
     */
    @RequestMapping(value = "/exportTemplateXlsGW")
    public ModelAndView exportTemplateXlsGW(HttpServletRequest request, HttpServletResponse response) {
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "岗位任务设置导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ErpAssessTasksetImportGWVO.class);
        ExportParams exportParams = new ExportParams("岗位任务设置导入模板", "模板信息");
        modelAndView.addObject(NormalExcelConstants.PARAMS, exportParams);
        modelAndView.addObject(NormalExcelConstants.DATA_LIST, new ArrayList<>());
        return modelAndView;
    }


    /**
     * 通过excel导入机构数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcelJG", method = RequestMethod.POST)
    public Result<?> importExcelJG(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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

            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ExcelImportResult<ErpAssessTasksetImportJGVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessTasksetImportJGVO.class, params);
                List<ErpAssessTasksetImportJGVO> list = importResult.getList();
                List<ErpAssessTaskset> list1 = new ArrayList<>();
                for (ErpAssessTasksetImportJGVO erpAssessTasksetJgVO : list) {
                    ErpAssessTaskset erpAssessTaskset = new ErpAssessTaskset();
                    BeanUtils.copyProperties(erpAssessTasksetJgVO, erpAssessTaskset);
                    QueryWrapper<ErpAssessTaskset> queryWrapper = new QueryWrapper();
                    queryWrapper.eq("scheme_id", erpAssessTaskset.getSchemeId());
                    queryWrapper.eq("zzbz", erpAssessTaskset.getZzbz());
                    queryWrapper.eq("zbid", erpAssessTaskset.getZbid());
                    queryWrapper.eq("khwd", erpAssessTaskset.getKhwd());
                    queryWrapper.eq("khfs", erpAssessTaskset.getKhfs());
                    queryWrapper.isNull("gwbz");
                    service.remove(queryWrapper);
                    list1.add(erpAssessTaskset);
                }
                service.saveBatch(list1);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return Result.error("文件导入失败:" + e.getMessage());
            } finally {
                IoUtil.close(fis);
                IoUtil.close(fos);
            }
        }
        return Result.ok("文件导入失败！");
    }


    /**
     * 通过excel导入岗位数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcelGW", method = RequestMethod.POST)
    public Result<?> importExcelGW(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
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

            FileOutputStream fos = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                ExcelImportResult<ErpAssessTasksetImportGWVO> importResult = ExcelImportUtil.importExcelVerify(file, ErpAssessTasksetImportGWVO.class, params);
                List<ErpAssessTasksetImportGWVO> list = importResult.getList();
                List<ErpAssessTaskset> list1 = new ArrayList<>();
                for (ErpAssessTasksetImportGWVO erpAssessTasksetGwVO : list) {
                    ErpAssessTaskset erpAssessTaskset = new ErpAssessTaskset();
                    BeanUtils.copyProperties(erpAssessTasksetGwVO, erpAssessTaskset);
                    QueryWrapper<ErpAssessTaskset> queryWrapper = new QueryWrapper();
                    queryWrapper.eq("scheme_id", erpAssessTaskset.getSchemeId());
                    queryWrapper.eq("zzbz", erpAssessTaskset.getZzbz());
                    queryWrapper.eq("zbid", erpAssessTaskset.getZbid());
                    queryWrapper.eq("khwd", erpAssessTaskset.getKhwd());
                    queryWrapper.eq("khfs", erpAssessTaskset.getKhfs());
                    queryWrapper.eq("gwbz", erpAssessTaskset.getGwbz());
                    queryWrapper.eq("yggh", erpAssessTaskset.getYggh());
                    service.remove(queryWrapper);
                    list1.add(erpAssessTaskset);
                }
                service.saveBatch(list1);
                obj.put("filePath", filePath);
                fos = new FileOutputStream(baseFilePath);
                importResult.getWorkbook().write(fos);
                fos.flush();
                fos.close();
                return Result.ok("文件导入完成！成功导入数据行数:" + list.size(), obj);
            } catch (Exception e) {
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

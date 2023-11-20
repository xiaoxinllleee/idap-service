package org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.INhxqService;
import org.cmms.modules.sgtz.sjtb.service.IEtlSgtzSjtbService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.entity.Nhsxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhsxmx.service.INhsxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhyxmx.entity.Nhyxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.nhyxmx.service.INhyxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.entity.Shsxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shsxmx.service.IShsxmxService;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shyxmx.entity.Shyxmx;
import org.cmms.modules.tjfx.zfsjtj.sxyxtj.shyxmx.service.IShyxmxService;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.entity.KhjlZfyxtj;
import org.cmms.modules.tjfx.zfsjtj.zfyxtj.nh.gr.service.IKhjlZfyxtjService;

import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 客户经理走访营销统计
 * @Author: jeecg-boot
 * @Date: 2022-05-23
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "客户经理走访营销统计")
@RestController
@RequestMapping("/khjlzfyxtj/khjlZfyxtj")
public class KhjlZfyxtjController extends JeecgController<KhjlZfyxtj, IKhjlZfyxtjService> {
    @Autowired
    private IKhjlZfyxtjService khjlZfyxtjService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private INhsxmxService nhsxmxService;
    @Autowired
    private IShsxmxService shsxmxService;
    @Autowired
    private INhyxmxService nhyxmxService;
    @Autowired

    private IShyxmxService shyxmxService;
    @Autowired
    private IEtlSgtzSjtbService etlSgtzSjtbService;

    @Value("${com.etl.etlName:数据下发ETL任务}")
    private String etlName;

    @Value("${com.etl.dagName:etl_day调度}")
    private String dagName;

    @Autowired
    private INhxqService nhxqService;


    /**
     * 分页列表查询
     *
     * @param khjlZfyxtj
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-分页列表查询")
    @ApiOperation(value = "客户经理走访营销统计-分页列表查询", notes = "客户经理走访营销统计-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(KhjlZfyxtj khjlZfyxtj,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<KhjlZfyxtj> queryWrapper = QueryGenerator.initQueryWrapper(khjlZfyxtj, req.getParameterMap());
        queryWrapper.eq("tjwd", khjlZfyxtj.getTjwd());
        queryWrapper.eq("khlx", khjlZfyxtj.getKhlx());
        queryWrapper.orderByAsc("yxzfpm");
        Page<KhjlZfyxtj> page = new Page<KhjlZfyxtj>(pageNo, pageSize);
        IPage<KhjlZfyxtj> pageList = khjlZfyxtjService.page(page, queryWrapper);
        return Result.ok(pageList);
    }


    /**
     * 添加
     *
     * @param khjlZfyxtj
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-添加")
    @ApiOperation(value = "客户经理走访营销统计-添加", notes = "客户经理走访营销统计-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody KhjlZfyxtj khjlZfyxtj) {
        khjlZfyxtjService.save(khjlZfyxtj);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param khjlZfyxtj
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-编辑")
    @ApiOperation(value = "客户经理走访营销统计-编辑", notes = "客户经理走访营销统计-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody KhjlZfyxtj khjlZfyxtj) {
        khjlZfyxtjService.updateById(khjlZfyxtj);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-通过id删除")
    @ApiOperation(value = "客户经理走访营销统计-通过id删除", notes = "客户经理走访营销统计-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        khjlZfyxtjService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-批量删除")
    @ApiOperation(value = "客户经理走访营销统计-批量删除", notes = "客户经理走访营销统计-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.khjlZfyxtjService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "客户经理走访营销统计-通过id查询")
    @ApiOperation(value = "客户经理走访营销统计-通过id查询", notes = "客户经理走访营销统计-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        KhjlZfyxtj khjlZfyxtj = khjlZfyxtjService.getById(id);
        return Result.ok(khjlZfyxtj);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param khjlZfyxtj
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, KhjlZfyxtj khjlZfyxtj) {
        return super.exportXls(request, khjlZfyxtj, KhjlZfyxtj.class, "客户经理走访营销统计");
    }

    @RequestMapping(value = "/exportXlsnhrb")
    public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "1");
        queryWrapper.eq("tjwd", "DD");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(日报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(日报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsnhyb")
    public ModelAndView exportXlsnhyb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "1");
        queryWrapper.eq("tjwd", "MM");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(月报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(月报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsnhjb")
    public ModelAndView exportXlsnhjb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "1");
        queryWrapper.eq("tjwd", "Q");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(季报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(季报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsnhnb")
    public ModelAndView exportXlsnhnb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "1");
        queryWrapper.eq("tjwd", "YYYY");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(年报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(年报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsnhlj")
    public ModelAndView exportXlsnhlj(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "1");
        queryWrapper.eq("tjwd", "T");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "农户个人走访(累计)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("农户个人走访(累计)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsshrb")
    public ModelAndView exportXlsshrb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "2");
        queryWrapper.eq("tjwd", "DD");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(日报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(日报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }


    @RequestMapping(value = "/exportXlsshyb")
    public ModelAndView exportXlsshyb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "2");
        queryWrapper.eq("tjwd", "MM");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(月报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(月报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsshjb")
    public ModelAndView exportXlsshjb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "2");
        queryWrapper.eq("tjwd", "Q");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(季报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(季报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsshnb")
    public ModelAndView exportXlsshnb(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "2");
        queryWrapper.eq("tjwd", "YYYY");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(年报)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(年报)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }

    @RequestMapping(value = "/exportXlsshlj")
    public ModelAndView exportXlsshlj(HttpServletRequest request, HttpServletResponse response) {
        // Step.1 组装查询条件
        QueryWrapper<KhjlZfyxtj> queryWrapper = null;
        try {
            String paramsStr = request.getParameter("paramsStr");
            if (oConvertUtils.isNotEmpty(paramsStr)) {
                String deString = URLDecoder.decode(paramsStr, "UTF-8");
                KhjlZfyxtj zhbndktjXb = JSON.parseObject(deString, KhjlZfyxtj.class);
                queryWrapper = QueryGenerator.initQueryWrapper(zhbndktjXb, request.getParameterMap());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (queryWrapper == null) {
            queryWrapper = new QueryWrapper<>();
        }
        queryWrapper.eq("khlx", "2");
        queryWrapper.eq("tjwd", "T");

        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<KhjlZfyxtj> pageList = khjlZfyxtjService.list(queryWrapper);
        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "商户个人走访(累计)列表");
        mv.addObject(NormalExcelConstants.CLASS, KhjlZfyxtj.class);
        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("商户个人走访(累计)列表", "导出人:" + getRealname(), "导出信息"));
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
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
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, KhjlZfyxtj.class);
    }

    /**
     * 提取
     */
    @PostMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) throws ParseException {
        String tjrq = jsonObject.getString("tjrq");
        tjrq = tjrq.replaceAll("-", "");
        Result result = new Result<>();
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (false) {
                String endDate1 = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName).replace("-","");
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khxxgl_khxq_sh", "tjfx_cssz", "khxxgl_khxq_nh").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
                });
                QueryWrapper<Nhsxmx> queryWrapper = new QueryWrapper<Nhsxmx>();
                queryWrapper.apply("tjrq =to_date('" + endDate1+ "','YYYY-MM-dd')");
                nhsxmxService.remove(queryWrapper);
                QueryWrapper<Shsxmx> queryWrapper1 = new QueryWrapper<Shsxmx>();
                queryWrapper1.apply("tjrq =to_date('" + endDate1+ "','YYYY-MM-dd')");
                shsxmxService.remove(queryWrapper1);
                QueryWrapper<Nhyxmx> queryWrapper2 = new QueryWrapper<Nhyxmx>();
                queryWrapper2.apply("tjrq =to_date('" + endDate1+ "','YYYY-MM-dd')");
                nhyxmxService.remove(queryWrapper2);
                QueryWrapper<Shyxmx> queryWrapper3 = new QueryWrapper<Shyxmx>();
                queryWrapper3.apply("tjrq =to_date('" + endDate1 + "','YYYY-MM-dd')");
                shyxmxService.remove(queryWrapper3);
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxyxtj.py --fiscal_date " + endDate1 + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_SXYXTJ_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXYXTJ_IMPORT.sh");
                khjlZfyxtjService.syxyInit(endDate1);
                khjlZfyxtjService.init(tjrq);
                result.setSuccess(true);
                return result;
            } else {
                khjlZfyxtjService.init(tjrq);
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 批量提取
     */
    @PostMapping(value = "/batchInit")
    public Result<?> batchInit(@RequestBody JSONObject jsonObject) throws ParseException {
        String beginDate = jsonObject.getString("beginDate");
        String endDate = jsonObject.getString("endDate");
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
        Result result = new Result<>();
        try {
            if (false) {
                String endDate1 = etlSgtzSjtbService.getZdrkrq(etlName == null ? "数据下发ETL任务" : etlName, dagName == null ? "etl_day调度" : dagName).replace("-", "");
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("khxxgl_khxq_sh", "tjfx_cssz", "khxxgl_khxq_nh").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true, false, false, item, "idap");
                });
                QueryWrapper<Nhsxmx> queryWrapper = new QueryWrapper<Nhsxmx>();
                queryWrapper.apply("tjrq =to_date('" + endDate1 + "','YYYY-MM-dd')");
                nhsxmxService.remove(queryWrapper);
                QueryWrapper<Shsxmx> queryWrapper1 = new QueryWrapper<Shsxmx>();
                queryWrapper1.apply("tjrq =to_date('" + endDate1 + "','YYYY-MM-dd')");
                shsxmxService.remove(queryWrapper1);
                QueryWrapper<Nhyxmx> queryWrapper2 = new QueryWrapper<Nhyxmx>();
                queryWrapper2.apply("tjrq =to_date('" + endDate1 + "','YYYY-MM-dd')");
                nhyxmxService.remove(queryWrapper2);
                QueryWrapper<Shyxmx> queryWrapper3 = new QueryWrapper<Shyxmx>();
                queryWrapper3.apply("tjrq =to_date('" + endDate1 + "','YYYY-MM-dd')");
                shyxmxService.remove(queryWrapper3);
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxsxyxtj.py --fiscal_date " + endDate1 + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_SXYXTJ_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_SXYXTJ_IMPORT.sh");
                khjlZfyxtjService.syxyInit(endDate1);
                khjlZfyxtjService.batchInit(beginDate, endDate);
                result.setSuccess(true);
                return result;
            } else {
                khjlZfyxtjService.batchInit(beginDate, endDate);
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
            System.out.println(e);
            log.error("提取失败", e.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

}

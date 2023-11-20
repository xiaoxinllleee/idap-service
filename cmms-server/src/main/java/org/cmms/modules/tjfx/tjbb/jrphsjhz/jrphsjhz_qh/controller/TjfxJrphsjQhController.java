package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.enums.QybmEnum;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.*;
import org.cmms.modules.system.entity.SysDic;
import org.cmms.modules.system.service.ISysDicService;
import org.cmms.modules.tjfx.grcdksjmx.service.IKhglGrcdsjmxService;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZhImport;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.service.IJrphsjZhService;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQhimport;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.service.ITjfxJrphsjQhService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.cmms.modules.util.EtlUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date: 2020-08-14
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "金融普惠数据汇总")
@RestController
@RequestMapping("/TjfxJrphsjQh/tjfxJrphsjQh")
public class TjfxJrphsjQhController extends JeecgController<TjfxJrphsjQh, ITjfxJrphsjQhService> implements Job {
    @Autowired
    private ITjfxJrphsjQhService tjfxJrphsjQhService;
    @Autowired
    private ITjfxZhbyService tjfxZhbyService;
    @Autowired
    private Environment environment;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SshUtil sshUtil;
    @Autowired
    private IJrphsjZhService jrphsjZhServicel;
    @Autowired
    private ISysDicService sysDicService;
    @Autowired
    private IKhglGrcdsjmxService khglGrcdsjmxService;

    /**
     * 分页列表查询
     *
     * @param tjfxJrphsjQh
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-分页列表查询")
    @ApiOperation(value = "金融普惠数据汇总-分页列表查询", notes = "金融普惠数据汇总-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(TjfxJrphsjQh tjfxJrphsjQh,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<TjfxJrphsjQh> queryWrapper = QueryGenerator.initQueryWrapper(tjfxJrphsjQh, req.getParameterMap());
        Page<TjfxJrphsjQh> page = new Page<TjfxJrphsjQh>(pageNo, pageSize);
        IPage<TjfxJrphsjQh> pageList = tjfxJrphsjQhService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param tjfxJrphsjQh
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-添加")
    @ApiOperation(value = "金融普惠数据汇总-添加", notes = "金融普惠数据汇总-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody TjfxJrphsjQh tjfxJrphsjQh) {
        tjfxJrphsjQhService.save(tjfxJrphsjQh);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tjfxJrphsjQh
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-编辑")
    @ApiOperation(value = "金融普惠数据汇总-编辑", notes = "金融普惠数据汇总-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody TjfxJrphsjQh tjfxJrphsjQh) {
        tjfxJrphsjQhService.updateById(tjfxJrphsjQh);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-通过id删除")
    @ApiOperation(value = "金融普惠数据汇总-通过id删除", notes = "金融普惠数据汇总-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tjfxJrphsjQhService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-批量删除")
    @ApiOperation(value = "金融普惠数据汇总-批量删除", notes = "金融普惠数据汇总-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tjfxJrphsjQhService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "金融普惠数据汇总-通过id查询")
    @ApiOperation(value = "金融普惠数据汇总-通过id查询", notes = "金融普惠数据汇总-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        TjfxJrphsjQh tjfxJrphsjQh = tjfxJrphsjQhService.getById(id);
        return Result.ok(tjfxJrphsjQh);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tjfxJrphsjQh
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TjfxJrphsjQh tjfxJrphsjQh) {
        return super.exportXls(request, tjfxJrphsjQh, TjfxJrphsjQh.class, "金融普惠数据汇总");
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
        return super.importExcel(request, response, TjfxJrphsjQh.class);
    }


    /**
     * 导出excel
     *
     * @param request
     * @param jrphsjQh
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(TjfxJrphsjQh jrphsjQh, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // Step.1 组装查询条件
        QueryWrapper<TjfxJrphsjQh> queryWrapper = QueryGenerator.initQueryWrapper(jrphsjQh, request.getParameterMap());
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        Map<String, Object> map = new HashMap<String, Object>();
        List<TjfxJrphsjQhimport> jrphsjQhimportList = new ArrayList<>();
        List<TjfxJrphsjQh> pageList = tjfxJrphsjQhService.list(queryWrapper);
        for (TjfxJrphsjQh jrphsjQh1 : pageList) {
            TjfxJrphsjQhimport jrphsjQhimport = new TjfxJrphsjQhimport();
            BeanUtils.copyProperties(jrphsjQh1, jrphsjQhimport);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jrphsjQhimport.setTjyf(sdf.format(jrphsjQh1.getTjyf()));
            jrphsjQhimportList.add(jrphsjQhimport);
        }
        map.put("list", jrphsjQhimportList);
        String port = environment.getProperty("common.path.export");
        //导出文件名称
        mv.addObject(JxlsConstants.FILE_NAME, "全行金融普惠数据汇总");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("全行金融普惠数据汇总.xls"));
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/全行金融普惠数据汇总.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);
        return mv;
    }

    /**
     * 通过tjyf提取
     *
     * @param tjyf
     * @return
     */
    @PutMapping(value = "/extract")
    public Result<?> extract(@RequestParam(name = "tjyf") String tjyf) {
        try {
            LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            String qybm = (String) redisUtil.get(CommonConstant.PREFIX_USER_QYBM + loginUser.getUsername());
            if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
                QueryWrapper<JrphsjZh> queryWrapper=new QueryWrapper<>();
                Date date=DateUtil.getFirstday_Month(DateUtil.string2Date(tjyf,"YYYY-MM-dd"),0);
                queryWrapper.apply("(tjyf =to_date('"+tjyf+"','YYYY-MM-dd'))");
                jrphsjZhServicel.remove(queryWrapper);
                //需要同步到impala的表
                List<String> tableNameList = Stream.of("tjfx_zfsjmx_zh","tjfx_xdxtkhsxyxtj_zhmx","tjfx_khsxyxtj_zhmx","tjfx_khbkbpy_zh","tjfx_gsxxdr","tjfx_cssz").collect(Collectors.toList());
                //同步oracle到impala
                tableNameList.forEach(item -> {
                    EtlUtil.SHcallEtlRc(10, true, false,false,item, "idap");
                });
                EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");
                String clrq = khglGrcdsjmxService.getCsz("CS0001");
                //调用python脚本
                sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxjrphsjhz.py --fiscal_date " + clrq + "'");
                //同步impala到oracle
                sshUtil.execShell("sh /home/exportdata/P_TJFX_JRPHSJHZ_EXPORT.sh");
                sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_JRPHSJHZ_IMPORT.sh");
            }
            tjfxJrphsjQhService.extract(tjyf);

        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功");
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Calendar cale = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String firstday;
        // 获取当前月的第一天
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        String qybm = sysDicService.queryByCode("101001").getValue();
        if (StringUtils.isNotBlank(qybm) && QybmEnum.RUCHENG.getQybm().equals(qybm)) {
            QueryWrapper<JrphsjZh> queryWrapper=new QueryWrapper<>();
            queryWrapper.apply("(tjyf =to_date('"+firstday+"','YYYY-MM-dd'))");
            jrphsjZhServicel.remove(queryWrapper);
            //需要同步到impala的表
            List<String> tableNameList = Stream.of("tjfx_zfsjmx_zh","tjfx_xdxtkhsxyxtj_zhmx","tjfx_khsxyxtj_zhmx","tjfx_khbkbpy_zh","tjfx_gsxxdr","tjfx_cssz").collect(Collectors.toList());
            //同步oracle到impala
            tableNameList.forEach(item -> {
                EtlUtil.SHcallEtlRc(10, true,false,false, item, "idap");
            });
            EtlUtil.SHcallEtlRc(10, true, true, false, "hr_bas_organization", "idap");
            String clrq = khglGrcdsjmxService.getCsz("CS0001");
            //调用python脚本
            sshUtil.execShell("docker exec kanas bash -c 'cd /root/trunk/src/ && source ./env_py2.7.sh && cd /root/trunk/src/tests/kanas/idap_test/ && python exec_tjfxjrphsjhz.py --fiscal_date " + clrq + "'");
            //同步impala到oracle
            sshUtil.execShell("sh /home/exportdata/P_TJFX_JRPHSJHZ_EXPORT.sh");
            sshUtil.execShell("su - oracle - /home/importdata/P_TJFX_JRPHSJHZ_IMPORT.sh");
        }
        tjfxJrphsjQhService.extract(firstday);
        log.info(String.format("自动执行工作台数据提取：" + DateUtils.getTimestamp()));
    }
}


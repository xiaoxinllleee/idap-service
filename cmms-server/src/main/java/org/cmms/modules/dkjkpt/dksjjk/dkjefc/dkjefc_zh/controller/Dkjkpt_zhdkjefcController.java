package org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.cmms.common.api.vo.Result;
import org.cmms.common.excel.JxlsConstants;
import org.cmms.common.excel.view.TemplateExcelView;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.FileUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity.Dkjkpt_zhdkjefc;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.entity.Dkjkpt_zhdkjefc_Import;
import org.cmms.modules.dkjkpt.dksjjk.dkjefc.dkjefc_zh.service.IDkjkpt_zhdkjefcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZh;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsj_zh.entity.JrphsjZhImport;
import org.cmms.modules.tjfx.xdgtzytjbb.zhtjbby.service.ITjfxZhbyService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

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
 * @Description: 贷款金额分层_支行
 * @Author: jeecg-boot
 * @Date: 2020-11-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "贷款金额分层_支行")
@RestController
@RequestMapping("/dkjkpt.dksjjk.dkjefc.dkjefc_zh/dkjkpt_zhdkjefc")
public class Dkjkpt_zhdkjefcController extends JeecgController<Dkjkpt_zhdkjefc, IDkjkpt_zhdkjefcService> {
    @Autowired
    private IDkjkpt_zhdkjefcService dkjkpt_zhdkjefcService;
	@Autowired
	private ITjfxZhbyService tjfxZhbyService;
	@Autowired
	private Environment environment;
    /**
     * 分页列表查询
     *
     * @param dkjkpt_zhdkjefc
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-分页列表查询")
    @ApiOperation(value = "贷款金额分层_支行-分页列表查询", notes = "贷款金额分层_支行-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Dkjkpt_zhdkjefc dkjkpt_zhdkjefc,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<Dkjkpt_zhdkjefc> queryWrapper = QueryGenerator.initQueryWrapper(dkjkpt_zhdkjefc, req.getParameterMap());
        Page<Dkjkpt_zhdkjefc> page = new Page<Dkjkpt_zhdkjefc>(pageNo, pageSize);
        IPage<Dkjkpt_zhdkjefc> pageList = dkjkpt_zhdkjefcService.page(page, queryWrapper);
        return Result.ok(pageList);
    }

    /**
     * 添加
     *
     * @param dkjkpt_zhdkjefc
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-添加")
    @ApiOperation(value = "贷款金额分层_支行-添加", notes = "贷款金额分层_支行-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Dkjkpt_zhdkjefc dkjkpt_zhdkjefc) {
        dkjkpt_zhdkjefcService.save(dkjkpt_zhdkjefc);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param dkjkpt_zhdkjefc
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-编辑")
    @ApiOperation(value = "贷款金额分层_支行-编辑", notes = "贷款金额分层_支行-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Dkjkpt_zhdkjefc dkjkpt_zhdkjefc) {
        dkjkpt_zhdkjefcService.updateById(dkjkpt_zhdkjefc);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-通过id删除")
    @ApiOperation(value = "贷款金额分层_支行-通过id删除", notes = "贷款金额分层_支行-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        dkjkpt_zhdkjefcService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-批量删除")
    @ApiOperation(value = "贷款金额分层_支行-批量删除", notes = "贷款金额分层_支行-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.dkjkpt_zhdkjefcService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "贷款金额分层_支行-通过id查询")
    @ApiOperation(value = "贷款金额分层_支行-通过id查询", notes = "贷款金额分层_支行-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Dkjkpt_zhdkjefc dkjkpt_zhdkjefc = dkjkpt_zhdkjefcService.getById(id);
        return Result.ok(dkjkpt_zhdkjefc);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param dkjkpt_zhdkjefc
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Dkjkpt_zhdkjefc dkjkpt_zhdkjefc) {
        return super.exportXls(request, dkjkpt_zhdkjefc, Dkjkpt_zhdkjefc.class, "贷款金额分层_支行");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param jrphsjZh
     */
    @RequestMapping(value = "/exportTemplateXls")
    public ModelAndView exportTemplateXls(Dkjkpt_zhdkjefc jrphsjZh, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        // Step.1 组装查询条件
        QueryWrapper<Dkjkpt_zhdkjefc> queryWrapper = QueryGenerator.initQueryWrapper(jrphsjZh, request.getParameterMap());
        //AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new TemplateExcelView());
        Map<String, Object> map = new HashMap<String, Object>();
        List<Dkjkpt_zhdkjefc_Import> jrphsjZhImportList = new ArrayList<>();
        List<Dkjkpt_zhdkjefc> pageList = dkjkpt_zhdkjefcService.list(queryWrapper);
        for (Dkjkpt_zhdkjefc jrphsjZh1 : pageList) {
            jrphsjZh1.setJgdm(jrphsjZh1.getJgdm() == null ? "" : tjfxZhbyService.queryTableDictTextByKey("hr_bas_organization", "zzjc", "zzbz", jrphsjZh1.getJgdm()));
			Dkjkpt_zhdkjefc_Import jrphsjZhImport = new Dkjkpt_zhdkjefc_Import();
            BeanUtils.copyProperties(jrphsjZh1, jrphsjZhImport);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            jrphsjZhImport.setTjyf(sdf.format(jrphsjZh1.getTjyf()));
            jrphsjZhImportList.add(jrphsjZhImport);
        }
        map.put("list", jrphsjZhImportList);
        String port = environment.getProperty("common.path.export");
        //导出文件名称
        mv.addObject(JxlsConstants.FILE_NAME, "支行贷款金额分层");
        mv.addObject(JxlsConstants.TEMPLATE_FILE_NAME, FileUtil.getTempFilePath("支行贷款金额分层.xls"));
        mv.addObject(JxlsConstants.SAVE_FILE_NAME, port + "/支行贷款金额分层.xls");
        mv.addObject(JxlsConstants.MAP_DATA, map);
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
        return super.importExcel(request, response, Dkjkpt_zhdkjefc.class);
    }

    /**
     * 支行贷款金额分层-提取
     *
     * @param object
     * @return
     */
    @PutMapping(value = "/init")
    public Result<?> InitDataToQh(@RequestBody JSONObject object) {
        System.out.println("tjyf-----" + object.getString("tjyf"));
        try {
            Map<String, String> param = new HashMap<>();
            String tjyf = object.getString("tjyf");
            tjyf = tjyf.replaceAll("-", "");
            param.put("tjyf", tjyf);
            dkjkpt_zhdkjefcService.InitDataToQh(param);
        } catch (Exception e) {
            log.error(e.getMessage(), "提取失败！");
            return Result.error(e.getMessage());
        }
        return Result.ok("提取成功！");
    }

}

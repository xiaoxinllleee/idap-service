package org.cmms.modules.xyjlcx.sszxgl.zxgl.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;
import org.cmms.modules.sjxf.hxxt.dkzdkb.service.IDkzdkbService;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.entity.Bwdksjmx;
import org.cmms.modules.xyjlcx.bwdkgl.bwdksjmx.service.IBwdksjmxService;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.entity.Djkdksjmx;
import org.cmms.modules.xyjlcx.jcsjgl.djkdksjmx.service.IDjkdksjmxService;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.entity.Zxgl;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.entity.ZxglVO;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.service.IZxglService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.xyjlcx.sszxgl.zxgl.verify.ZxglImportVerify;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
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
 * @Description: 执行管理
 * @Author: jeecg-boot
 * @Date: 2021-08-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "执行管理")
@RestController
@RequestMapping("/zxgl/zxgl")
public class ZxglController extends JeecgController<Zxgl, IZxglService> {
    @Autowired
    private IZxglService zxglService;
    @Autowired
    private IBwdksjmxService bwdksjmxService;
    @Autowired
    private IDjkdksjmxService djkdksjmxService;
    @Autowired
    private IDkzdkbService dkzdkbService;
    @Autowired
    private ZxglImportVerify zxglImportVerify;

    /**
     * 分页列表查询
     *
     * @param zxgl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "执行管理-分页列表查询")
    @ApiOperation(value = "执行管理-分页列表查询", notes = "执行管理-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zxgl zxgl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Zxgl>> result = new Result<IPage<Zxgl>>();
        QueryWrapper<Zxgl> queryWrapper = QueryGenerator.initQueryWrapper(zxgl, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(IZxglService.class, zxglService, pageNo, pageSize, queryWrapper, "zh", "sqzxrq");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }


    /**
     * 执行管理 / 判断账号是否存在
     *
     * @param zh
     * @return
     */
    @RequestMapping(value = "/judgeCkzh", method = RequestMethod.GET)
    public Result<?> judgeCkzh(@Param("kh") String zh) {
        int a = 0;
        //表外贷款数据明细
        QueryWrapper<Bwdksjmx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dkzh", zh);
        Bwdksjmx bwdksjmx = bwdksjmxService.getOne(queryWrapper);
        if (bwdksjmx != null) {
            a = 1;
            HashMap<String, String> map = new HashMap<>();
            map.put("ywjg", bwdksjmx.getJgdm());
            map.put("jkrxm", bwdksjmx.getKhmc());
            map.put("zjhm", bwdksjmx.getZjhm());
            return Result.ok(map);

        }
        if (a == 0) {
            //贷记卡数据明细
            QueryWrapper<Djkdksjmx> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("kh", zh);
            Djkdksjmx djkdksjmx = djkdksjmxService.getOne(queryWrapper1);
            if (djkdksjmx != null) {
                a = 1;
                HashMap<String, String> map = new HashMap<>();
                map.put("ywjg", djkdksjmx.getYwjg());
                map.put("jkrxm", djkdksjmx.getKhmc());
                map.put("zjhm", djkdksjmx.getZjhm());
                return Result.ok(map);
            }
        }
        if (a == 0) {
            //贷款余额表
            QueryWrapper<Dkzdkb> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("acct_no", zh);
            Dkzdkb dkzdkb = dkzdkbService.getOne(queryWrapper2);
            if (dkzdkb != null) {
                a = 1;
                HashMap<String, String> map = new HashMap<>();
                map.put("ywjg", dkzdkb.getBrNo());
                map.put("jkrxm", dkzdkb.getCustName());
                map.put("zjhm", dkzdkb.getIdentNo());
                return Result.ok(map);
            }
        }
        return Result.error("账号/卡号不存在，请核实！");
    }

    /**
     * 执行管理 / 添加
     *
     * @param zxgl
     * @return
     */
    @AutoLog(value = "执行管理-添加")
    @ApiOperation(value = "执行管理-添加", notes = "执行管理-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zxgl zxgl) {
        QueryWrapper<Zxgl> zxglQueryWrapper = new QueryWrapper<>();
        zxglQueryWrapper.eq("zh", zxgl.getZh());
        Zxgl zxgl1 = zxglService.getOne(zxglQueryWrapper);
        if (zxgl1 != null) {
            return Result.error("账号信息已存在，请勿重复添加！");
        }
        zxgl.setLrr(getLoginUser().getRealname());
        zxgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        zxgl.setLrbz(1);
        zxglService.save(zxgl);
        return Result.ok("添加成功！");
    }

    /**
     * 执行管理 / 编辑
     *
     * @param zxgl
     * @return
     */
    @AutoLog(value = "执行管理-编辑")
    @ApiOperation(value = "执行管理-编辑", notes = "执行管理-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zxgl zxgl) {
        QueryWrapper<Zxgl> queryWrapper = new QueryWrapper<Zxgl>();
        queryWrapper.eq("zh",zxgl.getZh());
        queryWrapper.eq("sqzxrq",zxgl.getSqzxrq());
        List<Zxgl> zxglList = zxglService.list(queryWrapper);
        if (zxglList.isEmpty()) {
            return Result.error("该账号信息不存在！");
        }
        Zxgl updateZxgl = zxglList.get(0);
        QueryWrapper<Zxgl> zxglQueryWrapper = new QueryWrapper<>();
        zxglQueryWrapper.eq("zh", zxgl.getZh());
        zxglQueryWrapper.eq("sqzxrq", zxgl.getSqzxrq());
        //表主键不能更新
        updateZxgl.setZh(null);
        updateZxgl.setSqzxrq(null);
        updateZxgl.setZxbj(zxgl.getZxbj());
        updateZxgl.setZxlx(zxgl.getZxlx());
        updateZxgl.setDzxje(zxgl.getDzxje());
        updateZxgl.setZxah(zxgl.getZxah());
        updateZxgl.setDqzxfy(zxgl.getDqzxfy());
        updateZxgl.setKgzxcce(zxgl.getKgzxcce());
        updateZxgl.setDydbr(zxgl.getDydbr());
        updateZxgl.setBz(zxgl.getBz());
        updateZxgl.setLrr(getLoginUser().getRealname());
        updateZxgl.setLrsj(new Timestamp(System.currentTimeMillis()));
        updateZxgl.setLrbz(2);
        zxglService.update(updateZxgl, zxglQueryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 执行管理 / 删除
     *
     * @param zh
     * @param sqzxrqStr
     * @return
     */
    @AutoLog(value = "执行管理-删除")
    @ApiOperation(value = "执行管理-删除", notes = "执行管理-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("zh") String zh,
                            @Param("sqzxrq") String sqzxrqStr) {
        try {
            Date sqzxrq = DateUtil.string2Date(sqzxrqStr, "yyyy-MM-dd");
            QueryWrapper<Zxgl> queryWrapper = new QueryWrapper<Zxgl>();
            queryWrapper.eq("zh", zh);
            queryWrapper.eq("sqzxrq", sqzxrq);
            zxglService.remove(queryWrapper);
            return Result.ok("删除成功!");
        } catch (Throwable e) {
            e.printStackTrace();
            return Result.error("删除失败!");
        }
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zxgl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zxgl zxgl) {
        return super.exportXls(request, zxgl, Zxgl.class, "执行管理");
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
        // AutoPoi 导出Excel
        ModelAndView modelAndView = new ModelAndView(new JeecgEntityExcelView());
        // 导出文件名称
        modelAndView.addObject(NormalExcelConstants.FILE_NAME, "执行管理导入模板");
        modelAndView.addObject(NormalExcelConstants.CLASS, ZxglVO.class);
        ExportParams exportParams = new ExportParams("执行管理导入模板", "模板信息");
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
        return super.importExcelByTemplate(jsonObject, request, response, Zxgl.class,ZxglVO.class, zxglImportVerify);
    }

}

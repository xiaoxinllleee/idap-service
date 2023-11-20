package org.cmms.modules.tjbb.ywltj.zzsbywl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.SecurityUtils;
import org.cmms.common.api.vo.Result;
import org.cmms.common.constant.CommonConstant;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.system.vo.LoginUser;
import org.cmms.common.util.DateUtil;
import org.cmms.common.util.DateUtils;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.tjbb.ywltj.zzsbywl.entity.Zzsbywl;
import org.cmms.modules.tjbb.ywltj.zzsbywl.mapper.ZzsbywlMapper;
import org.cmms.modules.tjbb.ywltj.zzsbywl.service.IZzsbywlService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.service.ApiListing;

/**
 * @Description: 自助设备业务量
 * @Author: jeecg-boot
 * @Date: 2021-08-24
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "自助设备业务量")
@RestController
@RequestMapping("/zzsbywl/zzsbywl")
public class ZzsbywlController extends JeecgController<Zzsbywl, IZzsbywlService> {
    @Autowired
    private IZzsbywlService zzsbywlService;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param zzsbywl
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "自助设备业务量-分页列表查询")
    @ApiOperation(value = "自助设备业务量-分页列表查询", notes = "自助设备业务量-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Zzsbywl zzsbywl,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(zzsbywl, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IZzsbywlService.class,zzsbywlService,pageNo,pageSize,queryWrapper,"tjyf","tjwd","trans_code","trans_staff");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result<?> init(@RequestBody JSONObject jsonObject) {
        Result result = new Result<>();
        String ksrq = "";
        String jsrq = "";
        Date ksrq_D = null;
        Date jsrq_D = null;
        String tjwd = jsonObject.getString("tjwd");
        String tjyf = jsonObject.getString("tjyf");


        try {
            if ("MM".equalsIgnoreCase(tjwd)) {
                ksrq_D = DateUtils.parseDate(tjyf,"yyyy-MM-dd");
                jsrq_D = DateUtil.getLastday_Month(ksrq_D,0);
            }
            if ("Q".equalsIgnoreCase(tjwd)) {
                Date tjyf_Q = DateUtils.parseDate(tjyf,"yyyy-MM-dd");
                ksrq_D = DateUtil.getQBeginDay(tjyf_Q);
                jsrq_D = DateUtil.getQEndDay(ksrq_D);
            }
            if ("YYYY".equalsIgnoreCase(tjwd)) {
                Date tjyf_YYYY = DateUtils.parseDate(tjyf,"yyyy-MM-dd");
                ksrq_D = DateUtil.getYBeginDay(tjyf_YYYY);
                jsrq_D = DateUtil.getYEndDay(ksrq_D);
            }
            ksrq = DateUtils.date2Str(ksrq_D,new SimpleDateFormat("yyyyMMdd"));
            jsrq = DateUtils.date2Str(jsrq_D,new SimpleDateFormat("yyyyMMdd"));
            System.out.println("开始日期:"+ksrq);
            System.out.println("结束日期:"+jsrq);
        } catch (ParseException parseException) {
            log.error("日期转换失败！", parseException.getMessage());
            result.setMessage(parseException.getMessage());
            result.setSuccess(false);
        }
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            HashMap<String, String> params = new HashMap<>();
            params.put("tjwd", tjwd);
            params.put("fiscal_date", tjyf);
            params.put("p_ksrq", ksrq);
            params.put("p_jsrq", jsrq);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ywltj_zzsbywl");
            // count_tjbb_ywltj_zzsbywl
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                tjyf = tjyf.replaceAll("-", "");
                zzsbywlService.pZzsbywl(tjwd, tjyf);
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                log.error("提取失败", e.getMessage());
                result.setMessage(e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 添加
     *
     * @param zzsbywl
     * @return
     */
    @AutoLog(value = "自助设备业务量-添加")
    @ApiOperation(value = "自助设备业务量-添加", notes = "自助设备业务量-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Zzsbywl zzsbywl) {
        zzsbywlService.save(zzsbywl);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param zzsbywl
     * @return
     */
    @AutoLog(value = "自助设备业务量-编辑")
    @ApiOperation(value = "自助设备业务量-编辑", notes = "自助设备业务量-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Zzsbywl zzsbywl) {
        zzsbywlService.updateById(zzsbywl);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "自助设备业务量-通过id删除")
    @ApiOperation(value = "自助设备业务量-通过id删除", notes = "自助设备业务量-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        zzsbywlService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "自助设备业务量-批量删除")
    @ApiOperation(value = "自助设备业务量-批量删除", notes = "自助设备业务量-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.zzsbywlService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "自助设备业务量-通过id查询")
    @ApiOperation(value = "自助设备业务量-通过id查询", notes = "自助设备业务量-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Zzsbywl zzsbywl = zzsbywlService.getById(id);
        return Result.ok(zzsbywl);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param zzsbywl
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Zzsbywl zzsbywl) {
        return super.exportXls(request, zzsbywl, Zzsbywl.class, "自助设备业务量(行内业务)");
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
        return super.importExcel(request, response, Zzsbywl.class);
    }

}

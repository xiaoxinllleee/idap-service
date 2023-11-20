package org.cmms.modules.tjbb.ckywfx.whzhckxx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
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
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.oConvertUtils;
import org.cmms.modules.system.service.ISysLogService;
import org.cmms.modules.tjbb.ckywfx.dqfdtj.entity.Dqfdtj;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.entity.Whzhckxx;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.mapper.WhzhckxxMapper;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.service.IWhzhckxxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.PageUtil;
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
 * @Description: 外汇账户存款信息
 * @Author: jeecg-boot
 * @Date: 2021-08-20
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "外汇账户存款信息")
@RestController
@RequestMapping("/whzhckxx/whzhckxx")
public class WhzhckxxController extends JeecgController<Whzhckxx, IWhzhckxxService> {
    @Autowired
    private IWhzhckxxService whzhckxxService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private ISysLogService sysLogService;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param whzhckxx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-分页列表查询")
    @ApiOperation(value = "外汇账户存款信息-分页列表查询", notes = "外汇账户存款信息-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Whzhckxx whzhckxx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper queryWrapper = QueryGenerator.initQueryWrapper(whzhckxx, req.getParameterMap());
        IPage pageList=org.cmms.common.utils.PageUtil.toPage(IWhzhckxxService.class,whzhckxxService,pageNo,pageSize,queryWrapper,"tjyf","jgdm","zjhm");
        return Result.ok(pageList);
    }

    /**
     * 提取
     */
    @RequestMapping(value = "/init")
    public Result init(@RequestBody JSONObject jsonObject) {
        String tjyf = jsonObject.getString("tjyf");
        log.info("所选统计月份："+tjyf);
        if (-1 == DateUtil.getCurrentTS().compareTo(DateUtil.parseDateFormat(tjyf, "yyyy-MM-dd"))) {
            return Result.error("所选月份必须小于当前系统月份！");
        }
        Result result = new Result<>();
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            Date cksjrq = DateUtil.string2Date(sysLogService.cksjrqBig(),"yyyyMMdd");
            try {
                tjyf = org.cmms.modules.util.DateUtil.getSjQmrq(DateUtil.getLastDayString(tjyf.replace("-","")), cksjrq, "yyyyMMdd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            log.info("实际传参月份："+tjyf);
            HashMap<String, String> params = new HashMap<>();
            params.put("fiscal_date", tjyf);
            params.put("etl_task","kiss.domain.application.tjbb.proc_tjbb_ckyw_whzhckxx");
            // count_tjbb_ckyw_whzhckxx
            boolean falg = EtlUtil.callEtl("tjbb_common_init", params, 15);
            result.setSuccess(falg);
        } else {
            try {
                whzhckxxService.pWhzhckxx(tjyf.replace("-",""));
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                System.out.println(e);
                log.error("提取失败", e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }




    /**
     * 添加
     *
     * @param whzhckxx
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-添加")
    @ApiOperation(value = "外汇账户存款信息-添加", notes = "外汇账户存款信息-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Whzhckxx whzhckxx) {
        whzhckxxService.save(whzhckxx);
        return Result.ok("添加成功！");
    }

    /**
     * 编辑
     *
     * @param whzhckxx
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-编辑")
    @ApiOperation(value = "外汇账户存款信息-编辑", notes = "外汇账户存款信息-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Whzhckxx whzhckxx) {
        whzhckxxService.updateById(whzhckxx);
        return Result.ok("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-通过id删除")
    @ApiOperation(value = "外汇账户存款信息-通过id删除", notes = "外汇账户存款信息-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        whzhckxxService.removeById(id);
        return Result.ok("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-批量删除")
    @ApiOperation(value = "外汇账户存款信息-批量删除", notes = "外汇账户存款信息-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.whzhckxxService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.ok("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "外汇账户存款信息-通过id查询")
    @ApiOperation(value = "外汇账户存款信息-通过id查询", notes = "外汇账户存款信息-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Whzhckxx whzhckxx = whzhckxxService.getById(id);
        return Result.ok(whzhckxx);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request,Whzhckxx whzhckxx) {
        return super.exportXls(request, whzhckxx, Whzhckxx.class, "外汇账户存款信息");
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
        return super.importExcel(request, response, Whzhckxx.class);
    }

}

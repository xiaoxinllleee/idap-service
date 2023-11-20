package org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.jni.Time;
import org.cmms.common.api.vo.Result;
import org.cmms.common.system.query.QueryGenerator;
import org.cmms.common.aspect.annotation.AutoLog;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.system.service.ISysDictService;
import org.cmms.modules.util.EtlUtil;
import org.cmms.modules.util.EtlUtilOld;
import org.cmms.modules.util.PageUtil;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.entity.Sbgxmx;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.mapper.SbgxmxMapper;
import org.cmms.modules.xyjlcx.bwdkgl.sbgxmx.service.ISbgxmxService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 收本挂息明细
 * @Author: jeecg-boot
 * @Date: 2021-08-16
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "收本挂息明细")
@RestController
@RequestMapping("/sbgxmx/sbgxmx")
public class SbgxmxController extends JeecgController<Sbgxmx, ISbgxmxService> {
    @Autowired
    private ISbgxmxService sbgxmxService;
    @Autowired
    private ISysDictService iSysDictService;
    @Autowired(required = false)
    private SbgxmxMapper sbgxmxMapper;
    @Value("${com.etl.sfdsjpt}")
    private String sfdsjpt;

    /**
     * 分页列表查询
     *
     * @param sbgxmx
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "收本挂息明细-分页列表查询")
    @ApiOperation(value = "收本挂息明细-分页列表查询", notes = "收本挂息明细-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Sbgxmx sbgxmx,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        Result<IPage<Sbgxmx>> result = new Result<IPage<Sbgxmx>>();
        QueryWrapper<Sbgxmx> queryWrapper = QueryGenerator.initQueryWrapper(sbgxmx, req.getParameterMap());
        IPage pageList = org.cmms.common.utils.PageUtil.toPage(ISbgxmxService.class, sbgxmxService, pageNo, pageSize, queryWrapper, "zh");
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    /**
     * 收本挂息明细 / 提取
     *
     * @return
     */
    @RequestMapping(value = "/init")
    public Result<?> init() {
        Result result = new Result<>();
        // `浏阳`调用ETL工具类执行ETL调度
        if ("true".equalsIgnoreCase(sfdsjpt)) {
            // `参数1`:`任务调用code`，`参数2`:`ETL调度存储过程参数值`，`参数3`:`ETL任务预计执行时间(可根据实际执行时间酌情延长)`
            HashMap<String, String> params = new HashMap<>();
            params.put("etl_task","kiss.domain.application.zx.proc_credit_sbgxmx");
            boolean completionSignal = EtlUtil.callEtl("zx_common_init", params, 20);
            result.setSuccess(completionSignal);
        } else {
            try {
                sbgxmxMapper.pSbgxmx();
                result.setSuccess(true);
                return result;
            } catch (Throwable e) {
                log.error("信用记录查询 / 收本挂息明细 / 提取失败！"+e.getMessage());
                result.setSuccess(false);
            }
        }
        return result;
    }

    /**
     * 收本挂息明细 / 添加
     *
     * @param sbgxmx
     * @return
     */
    @AutoLog(value = "收本挂息明细-添加")
    @ApiOperation(value = "收本挂息明细-添加", notes = "收本挂息明细-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Sbgxmx sbgxmx) {
        sbgxmx.setLrbz(1);
        sbgxmx.setLrr(getLoginUser().getUsername());
        sbgxmx.setLrsj(new Timestamp(System.currentTimeMillis()));
        sbgxmxService.save(sbgxmx);
        return Result.ok("添加成功！");
    }

    /**
     * 收本挂息明细 / 编辑
     *
     * @param sbgxmx
     * @return
     */
    @AutoLog(value = "收本挂息明细-编辑")
    @ApiOperation(value = "收本挂息明细-编辑", notes = "收本挂息明细-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Sbgxmx sbgxmx) {
        QueryWrapper<Sbgxmx> queryWrapper = new QueryWrapper<Sbgxmx>();
        queryWrapper.eq("zh", sbgxmx.getZh());
        //表主键不能更新
        sbgxmx.setZh(null);
        sbgxmx.setLrbz(2);
        sbgxmx.setLrr(getLoginUser().getUsername());
        sbgxmx.setLrsj(new Timestamp(System.currentTimeMillis()));
        sbgxmxService.update(sbgxmx, queryWrapper);
        return Result.ok("编辑成功!");
    }

    /**
     * 收本挂息明细 / 删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "收本挂息明细-删除")
    @ApiOperation(value = "收本挂息明细-删除", notes = "收本挂息明细-删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@Param("zh") String zh) {
        QueryWrapper<Sbgxmx> queryWrapper = new QueryWrapper<Sbgxmx>();
        queryWrapper.eq("zh", zh);
        sbgxmxService.remove(queryWrapper);
        return Result.ok("删除成功!");
    }

    /**
     * 导出excel
     *
     * @param request
     * @param sbgxmx
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Sbgxmx sbgxmx) {
        return super.exportXls(request, sbgxmx, Sbgxmx.class, "收本挂息明细");
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
        return super.importExcel(request, response, Sbgxmx.class);
    }

}

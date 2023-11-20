package org.cmms.modules.quartz.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.api.vo.Result;
import org.cmms.common.aspect.annotation.AutoLog;
import lombok.extern.slf4j.Slf4j;
import org.cmms.common.system.base.controller.JeecgController;
import org.cmms.modules.quartz.entity.QuartzJob;
import org.cmms.modules.quartz.entity.QuartzLog;
import org.cmms.modules.quartz.service.ISysQuartLogService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: wdw
 * @Author: jeecg-boot
 * @Date: 2022-09-06
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "wdw")
@RestController
@RequestMapping("/lyr/sysQuartLog")
public class SysQuartLogController extends JeecgController<QuartzLog, ISysQuartLogService> {
    @Autowired
    private ISysQuartLogService sysQuartLogService;


    /**
     * 通过id查询
     *
     * @param
     * @return
     */
    @AutoLog(value = "wdw-通过id查询")
    @ApiOperation(value = "wdw-通过id查询", notes = "wdw-通过id查询")
    @GetMapping(value = "/queryByJobId")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String jobId) {
        LambdaUpdateWrapper<QuartzLog> logLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        logLambdaUpdateWrapper.eq(QuartzLog::getJobId, jobId);
        logLambdaUpdateWrapper.orderByDesc(QuartzLog::getStartTime);
        Page page = new Page<QuartzLog>();
        IPage<QuartzLog> pageList = sysQuartLogService.page(page, logLambdaUpdateWrapper);
        return Result.ok(pageList);
    }


    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "wdw-通过id删除")
    @ApiOperation(value = "wdw-通过id删除", notes = "wdw-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        sysQuartLogService.removeById(id);
        return Result.ok("删除成功!");
    }


    /**
     * 通过id删除
     *
     * @param
     * @return
     */
    @AutoLog(value = "重新执行")
    @RequestMapping(value = "/repeatExecute", method = RequestMethod.POST)
    public Result<?> RepeatExecuteByJobId(@RequestBody QuartzLog quartzLog) {
        try{
            sysQuartLogService.repeateExecuteByJobId(quartzLog);
        }catch (Exception e) {
            e.printStackTrace();
            return Result.error("执行失败!");
        }
        return Result.ok("执行成功!");
    }


}

package org.cmms.modules.quartz.listener;


import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.common.util.RedisUtil;
import org.cmms.common.util.SpringContextUtils;
import org.cmms.common.util.StringUtils;
import org.cmms.modules.quartz.constant.QuartzEnum;
import org.cmms.modules.quartz.entity.QuartzJob;
import org.cmms.modules.quartz.entity.QuartzLog;
import org.cmms.modules.quartz.mapper.QuartzJobMapper;
import org.cmms.modules.quartz.mapper.SysQuartLogMapper;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JobActionListener implements JobListener {
    private String prefix_key = "QUARTZ_RUNNING_ID_";

    @Override
    public String getName() {
        return "JobActionListener";
    }


    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("-----开始执行创建时间为11111111----");
        JobDetail jobDetail = context.getJobDetail();
        QuartzJob quartzJob = this.getJob(jobDetail);
        if (quartzJob != null) {
            SysQuartLogMapper sysQuartLogMapper = SpringContextUtils.getBean(SysQuartLogMapper.class);
            QuartzLog quartzLog = new QuartzLog();
            String id = IdUtil.simpleUUID();
            quartzLog.setId(id);
            quartzLog.setJobId(quartzJob.getId());
            quartzLog.setJobGroup(jobDetail.getKey().getGroup());
            quartzLog.setJobClassName(jobDetail.getKey().getName());
            quartzLog.setStatus(QuartzEnum.QuartExecution.getKey());
            quartzLog.setStartTime(new Date());
            quartzLog.setCreateBy("SYSTEM");
            quartzLog.setCreateTime(new Date());
            sysQuartLogMapper.insert(quartzLog);
            RedisUtil redisUtil = SpringContextUtils.getBean(RedisUtil.class);
            redisUtil.set(prefix_key + quartzJob.getId(), id);
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("-----开始执行中----");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        SysQuartLogMapper sysQuartLogMapper = SpringContextUtils.getBean(SysQuartLogMapper.class);
        JobDetail jobDetail = context.getJobDetail();
        QuartzJob quartzJob = this.getJob(jobDetail);
        RedisUtil redisUtil = SpringContextUtils.getBean(RedisUtil.class);
        String id = (String)redisUtil.get(prefix_key + quartzJob.getId());
        if(StringUtils.isNotEmpty(id))
        {
            QuartzLog quartzLog = sysQuartLogMapper.selectById(id);
            if (jobException != null) {
                String message = jobException.getMessage();
                if (StringUtils.isNotEmpty(message) && message.length() > 2000) {
                    message = message.substring(0, 2000);
                }
                quartzLog.setEndTime(new Date());
                quartzLog.setInfo(message);
                quartzLog.setStatus(QuartzEnum.QuartExecutionFail.getKey());
                sysQuartLogMapper.updateById(quartzLog);
                System.out.println("----------执行出现异常-----");
            } else {
                quartzLog.setEndTime(new Date());
                quartzLog.setInfo(QuartzEnum.QuartExecutionSuccess.getValue());
                quartzLog.setStatus(QuartzEnum.QuartExecutionSuccess.getKey());
                sysQuartLogMapper.updateById(quartzLog);
                System.out.println("----------执行成功-----");

            }
        }

    }

    private QuartzJob getJob(JobDetail jobDetail) {
        QuartzJobMapper quartzJobMapper = SpringContextUtils.getBean(QuartzJobMapper.class);
        QueryWrapper<QuartzJob> queryWrapper = new QueryWrapper<>();
        String className = jobDetail.getKey().getName();
        if (className != null) {
            queryWrapper.eq("JOB_CLASS_NAME", className);
        }
        QuartzJob quartzJob = quartzJobMapper.selectOne(queryWrapper);
        return quartzJob;
    }
}


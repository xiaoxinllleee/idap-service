package org.cmms.modules.quartz.service;



import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.quartz.entity.QuartzLog;

/**
 * @Description: wdw
 * @Author: jeecg-boot
 * @Date:   2022-09-06
 * @Version: V1.0
 */
public interface ISysQuartLogService extends IService<QuartzLog> {

    void repeateExecuteByJobId(QuartzLog quartzLog) throws Exception;
}

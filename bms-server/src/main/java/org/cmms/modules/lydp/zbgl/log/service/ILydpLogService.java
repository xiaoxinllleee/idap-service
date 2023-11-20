package org.cmms.modules.lydp.zbgl.log.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.lydp.zbgl.log.entity.LydpLog;

import java.util.Date;

/**
 * @Description: 浏阳大屏日期记录
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@DS("master")
public interface ILydpLogService extends IService<LydpLog> {

    Date dateMax();

}

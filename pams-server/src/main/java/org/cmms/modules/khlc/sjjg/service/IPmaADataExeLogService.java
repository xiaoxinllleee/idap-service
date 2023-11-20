package org.cmms.modules.khlc.sjjg.service;

import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * @Description: 数据加工日志
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
public interface IPmaADataExeLogService extends IService<PmaADataExeLog> {
    String getMaxTjyf(Integer zxsx);
    String getMaxCgrq();
    String getMaxTjyfByRwid(String rwid);
    Integer getZxzxss();
}

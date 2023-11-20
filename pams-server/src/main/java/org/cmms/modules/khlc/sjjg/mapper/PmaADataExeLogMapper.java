package org.cmms.modules.khlc.sjjg.mapper;

import java.util.Date;

import org.cmms.modules.khlc.sjjg.entity.PmaADataExeLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 数据加工日志
 * @Author: jeecg-boot
 * @Date:   2023-03-08
 * @Version: V1.0
 */
public interface PmaADataExeLogMapper extends BaseMapper<PmaADataExeLog> {
    String getMaxTjyf(Integer zxsx);
    String getMaxCgrq();
    String getMaxTjyfByRwid(String rwid);

    Integer getZxzxss();
}

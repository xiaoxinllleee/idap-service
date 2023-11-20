package org.cmms.modules.appbase.datatime.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.appbase.datatime.entity.BasDataJobDays;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 数据入库
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
public interface BasDataJobDaysMapper extends BaseMapper<BasDataJobDays> {

    Date getMaxExtDay();

    Date getMaxEendDay();
}

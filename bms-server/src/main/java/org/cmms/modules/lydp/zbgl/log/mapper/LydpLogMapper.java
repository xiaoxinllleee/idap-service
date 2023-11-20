package org.cmms.modules.lydp.zbgl.log.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.lydp.zbgl.log.entity.LydpLog;

/**
 * @Description: 浏阳大屏日期记录
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
public interface LydpLogMapper extends BaseMapper<LydpLog> {

    @Select("select max(sjrq) from LOG_LYDP t where zt='1'")
    Date dateMax();

}

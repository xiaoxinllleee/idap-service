package org.cmms.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.entity.SysLogMx;

/**
 * @Description: 系统日志明细表
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
public interface SysLogMxMapper extends BaseMapper<SysLogMx> {

    SysLogMx getUsername(@Param("userid") String userid);
}

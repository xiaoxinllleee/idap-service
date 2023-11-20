package org.cmms.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.system.entity.SysDic;

/**
 * <p>
 * 系统配置表 Mapper 接口
 * </p>
 *
 * @Author scott
 * @since 2018-12-19
 */
public interface SysDicMapper extends BaseMapper<SysDic> {
    public SysDic queryByCode(@Param("code") String code);
}

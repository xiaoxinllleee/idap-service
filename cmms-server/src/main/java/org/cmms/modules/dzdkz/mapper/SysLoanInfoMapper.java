package org.cmms.modules.dzdkz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dzdkz.entity.SysLoanInfo;
import org.springframework.stereotype.Component;

/**
 *  Mapper 接口
 */
@Component
public interface SysLoanInfoMapper extends BaseMapper<SysLoanInfo> {

    SysLoanInfo getByZjhm(@Param("zjhm")String zjhm);
}

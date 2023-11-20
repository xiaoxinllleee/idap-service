package org.cmms.modules.zhgl.jxtj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.zhgl.jxtj.entity.EepWageGzhzbCl2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 慈利工资表
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
public interface EepWageGzhzbCl2Mapper extends BaseMapper<EepWageGzhzbCl2> {

    public Date getMaxImpday();
}

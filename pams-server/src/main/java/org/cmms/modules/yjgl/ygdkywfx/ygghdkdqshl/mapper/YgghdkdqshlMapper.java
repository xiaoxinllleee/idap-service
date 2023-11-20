package org.cmms.modules.yjgl.ygdkywfx.ygghdkdqshl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygghdkdqshl.entity.Ygghdkdqshl;

/**
 * @Description: 员工管户贷款到期收回率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgghdkdqshlMapper extends BaseMapper<Ygghdkdqshl> {
void init(@Param("tjyf")String tjyf,@Param("jgdm")String jgdm,@Param("yggh")String yggh);
}

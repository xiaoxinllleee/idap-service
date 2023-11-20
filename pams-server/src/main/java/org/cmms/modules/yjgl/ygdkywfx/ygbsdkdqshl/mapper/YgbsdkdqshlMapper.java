package org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.yjgl.ygdkywfx.ygbsdkdqshl.entity.Ygbsdkdqshl;

/**
 * @Description: 员工包收贷款到期收回率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
public interface YgbsdkdqshlMapper extends BaseMapper<Ygbsdkdqshl> {
    void init(@Param("tjyf")String tjrf,@Param("jgdm")String jgdm,@Param("yggh")String yggh);
}

package org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjldkdqshltj.entity.ModDkfxJgkhjldkdqshlkhM;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理贷款到期收回率统计
 * @Author: jeecg-boot
 * @Date:   2021-06-16
 * @Version: V1.0
 */
public interface ModDkfxJgkhjldkdqshlkhMMapper extends BaseMapper<ModDkfxJgkhjldkdqshlkhM> {
    String getByName(@Param("ygxm") String ygxm);
}

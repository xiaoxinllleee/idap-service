package org.cmms.modules.ywgl.cdkfx.khjlcdkntj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdkntj.entity.ModDkfxJgkhjlzhcdkyetjY;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理存贷款年统计
 * @Author: jeecg-boot
 * @Date:   2021-06-28
 * @Version: V1.0
 */
public interface ModDkfxJgkhjlzhcdkyetjYMapper extends BaseMapper<ModDkfxJgkhjlzhcdkyetjY> {
    String getByName(String ygxm);
}

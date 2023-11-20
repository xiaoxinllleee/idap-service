package org.cmms.modules.ywgl.cdkfx.khjlcdkytj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlcdkytj.entity.ModDkfxJgkhjlzhcdkyetjM;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理存贷款月统计
 * @Author: jeecg-boot
 * @Date:   2021-06-25
 * @Version: V1.0
 */
public interface ModDkfxJgkhjlzhcdkyetjMMapper extends BaseMapper<ModDkfxJgkhjlzhcdkyetjM> {
    String getByName(String ygxm);
}

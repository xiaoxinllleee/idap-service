package org.cmms.modules.ywgl.cdkfx.khjlxzbltj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.cdkfx.khjlxzbltj.entity.ModDkfxKhjlxzbldktjMM;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户经理新增不良统计
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
public interface ModDkfxKhjlxzbldktjMMMapper extends BaseMapper<ModDkfxKhjlxzbldktjMM> {
 String getCustidByName(String ygxm);
}

package org.cmms.modules.qtsjdrjk.xyk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.qtsjdrjk.xyk.entity.ShywxxXyk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
public interface ShywxxXykMapper extends BaseMapper<ShywxxXyk> {

    public Integer deleteAll();
}

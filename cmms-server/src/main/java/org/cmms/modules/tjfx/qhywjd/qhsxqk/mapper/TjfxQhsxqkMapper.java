package org.cmms.modules.tjfx.qhywjd.qhsxqk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.qhywjd.qhsxqk.entity.TjfxQhsxqk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行授信情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface TjfxQhsxqkMapper extends BaseMapper<TjfxQhsxqk> {
    void initData(String yggh);
}

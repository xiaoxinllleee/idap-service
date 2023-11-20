package org.cmms.modules.khxxgl.khywxx.qhywxx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglCksjmxQh;

import java.util.List;

/**
 * @Description: 客户信息管理存款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
public interface KhxxglCksjmxQhMapper extends BaseMapper<KhxxglCksjmxQh> {

    List<KhxxglCksjmxQh> getCkqsByZjhm(@Param("zjhm") String zjhm, @Param("list") List<String> dateString);
}

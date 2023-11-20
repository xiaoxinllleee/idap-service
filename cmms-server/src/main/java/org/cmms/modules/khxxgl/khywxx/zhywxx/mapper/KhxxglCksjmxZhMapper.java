package org.cmms.modules.khxxgl.khywxx.zhywxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;

/**
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
public interface KhxxglCksjmxZhMapper extends BaseMapper<KhxxglCksjmxZh> {

    List<KhxxglCksjmxZh> getCkqsByZjhm(@Param("zjhm") String zjhm,@Param("list") List<String> dateString);
}

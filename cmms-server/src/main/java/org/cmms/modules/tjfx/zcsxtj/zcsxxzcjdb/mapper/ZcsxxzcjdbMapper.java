package org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.tjfx.zcsxtj.zcsxxzcjdb.entity.Zcsxxzcjdb;

/**
 * @Description: 整村授信行政村进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface ZcsxxzcjdbMapper extends BaseMapper<Zcsxxzcjdb> {

    @Select("select max(tjrq) from tjfx_zcsxjdb_cun")
    String maxDate();

    List<Zcsxxzcjdb> getCunList();
}

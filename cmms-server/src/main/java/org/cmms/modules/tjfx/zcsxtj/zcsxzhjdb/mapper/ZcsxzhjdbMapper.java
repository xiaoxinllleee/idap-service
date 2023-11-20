package org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.entity.Zcsxzhjdb;

/**
 * @Description: 整村授信支行进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface ZcsxzhjdbMapper extends BaseMapper<Zcsxzhjdb> {
    @Select("select max(tjrq) from tjfx_zcsxjdb_zh")
    String maxDate();

    void init(String tjrq);
}

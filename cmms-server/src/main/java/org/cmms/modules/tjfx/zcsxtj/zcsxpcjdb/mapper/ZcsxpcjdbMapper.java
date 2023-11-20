package org.cmms.modules.tjfx.zcsxtj.zcsxpcjdb.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.tjfx.zcsxtj.zcsxpcjdb.entity.Zcsxpcjdb;

/**
 * @Description: 整村授信批次进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface ZcsxpcjdbMapper extends BaseMapper<Zcsxpcjdb> {
    @Select("select max(tjrq) from tjfx_zcsxjdb_pc")
    String maxDate();
}

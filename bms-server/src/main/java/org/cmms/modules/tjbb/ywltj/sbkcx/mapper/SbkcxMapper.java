package org.cmms.modules.tjbb.ywltj.sbkcx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.sbkcx.entity.Sbkcx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 社保卡查询
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
public interface SbkcxMapper extends BaseMapper<Sbkcx> {
    void pSbkcx(@Param("tjyf")String tjyf);
}

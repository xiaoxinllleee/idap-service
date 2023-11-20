package org.cmms.modules.tjbb.ckywfx.whzhckxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.entity.Whzhckxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 外汇账户存款信息
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
public interface WhzhckxxMapper extends BaseMapper<Whzhckxx> {
    void pWhzhckxx(@Param("tjyf")String tjyf);
}

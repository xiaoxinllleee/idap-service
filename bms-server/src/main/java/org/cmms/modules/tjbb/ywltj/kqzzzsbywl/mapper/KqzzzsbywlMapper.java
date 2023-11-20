package org.cmms.modules.tjbb.ywltj.kqzzzsbywl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.kqzzzsbywl.entity.Kqzzzsbywl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 卡前置自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
public interface KqzzzsbywlMapper extends BaseMapper<Kqzzzsbywl> {
    void pKqzzzsbywl(@Param("tjwd")String tjwd,@Param("tjyf")String tjyf);
}

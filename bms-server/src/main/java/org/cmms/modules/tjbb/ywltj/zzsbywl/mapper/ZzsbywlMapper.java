package org.cmms.modules.tjbb.ywltj.zzsbywl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.zzsbywl.entity.Zzsbywl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
public interface ZzsbywlMapper extends BaseMapper<Zzsbywl> {
    void pZzsbywl(@Param("tjwd")String tjwd,@Param("tjyf")String tjyf);
}

package org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.entity.TjfxKhbkbpyQh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户背靠背评议_全行
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
public interface TjfxKhbkbpyQhMapper extends BaseMapper<TjfxKhbkbpyQh> {
    public void extract(@Param("tjyf") String tjyf);

}

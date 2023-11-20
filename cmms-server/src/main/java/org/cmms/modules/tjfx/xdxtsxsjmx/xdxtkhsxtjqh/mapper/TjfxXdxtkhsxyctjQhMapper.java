package org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.xdxtsxsjmx.xdxtkhsxtjqh.entity.TjfxXdxtkhsxyctjQh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 全行授信用信统计
 * @Author: jeecg-boot
 * @Date:   2020-08-11
 * @Version: V1.0
 */
public interface TjfxXdxtkhsxyctjQhMapper extends BaseMapper<TjfxXdxtkhsxyctjQh> {

    public  void extract(String tjyf);
}

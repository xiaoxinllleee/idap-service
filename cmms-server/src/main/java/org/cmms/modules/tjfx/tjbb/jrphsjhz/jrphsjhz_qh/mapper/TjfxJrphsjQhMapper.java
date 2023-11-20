package org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.tjbb.jrphsjhz.jrphsjhz_qh.entity.TjfxJrphsjQh;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 金融普惠数据汇总
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
public interface TjfxJrphsjQhMapper extends BaseMapper<TjfxJrphsjQh> {
    public  void extract(String tjyf);

}

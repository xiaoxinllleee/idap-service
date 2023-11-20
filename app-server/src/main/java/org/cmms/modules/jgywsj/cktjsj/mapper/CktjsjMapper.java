package org.cmms.modules.jgywsj.cktjsj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.jgywsj.cktjsj.entity.TbTjfxCktjsj;

/**
 * @Description: 存款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
public interface CktjsjMapper extends BaseMapper<TbTjfxCktjsj> {
    public List<Date> getLatestTjrq(String zzbz);

    public TbTjfxCktjsj queryCktjsjByTjrqAndZzbz(String tjrq, String zzbz);
}

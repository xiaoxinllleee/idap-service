package org.cmms.modules.jgywsj.dktjsj.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jgywsj.dktjsj.entity.Dktjsj;
import org.cmms.modules.jgywsj.dktjsj.entity.TbTjfxDktjsj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
public interface DktjsjMapper extends BaseMapper<TbTjfxDktjsj> {
    public List<Date> getLatestTjrq(String zzbz);

    public Dktjsj queryDkxxByTjrqAndZzbz(String tjrq, String zzbz);
}

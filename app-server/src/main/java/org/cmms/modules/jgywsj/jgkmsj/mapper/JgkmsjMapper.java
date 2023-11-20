package org.cmms.modules.jgywsj.jgkmsj.mapper;

import org.cmms.modules.jgywsj.jgkmsj.entity.JgkmsjCk;
import org.cmms.modules.jgywsj.jgkmsj.entity.TbTjfxJgkmsj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.List;

/**
 * @Description: 机构科目数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
public interface JgkmsjMapper extends BaseMapper<TbTjfxJgkmsj> {
    public JgkmsjCk queryCkxxByTjrqAndZzbz(String tjrq, String zzbz);

    public List<Date> getLatestTjrq(String zzbz);
}

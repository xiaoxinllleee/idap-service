package org.cmms.modules.jgywsj.dktjsj.service;

import org.cmms.modules.jgywsj.dktjsj.entity.Dktjsj;
import org.cmms.modules.jgywsj.dktjsj.entity.TbTjfxDktjsj;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 贷款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
public interface IDktjsjService extends IService<TbTjfxDktjsj> {
    public List<Date> getLatestTjrq(String zzbz);

    public Dktjsj queryDkxxByTjrqAndZzbz(String tjrq, String zzbz);
}

package org.cmms.modules.jgywsj.cktjsj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.jgywsj.cktjsj.entity.TbTjfxCktjsj;

import java.util.Date;
import java.util.List;

/**
 * @Description: 存款统计数据
 * @Author: jeecg-boot
 * @Date:   2021-05-28
 * @Version: V1.0
 */
public interface ICktjsjService extends IService<TbTjfxCktjsj> {
    public List<Date> getLatestTjrq(String zzbz);

    public TbTjfxCktjsj queryCktjsjByTjrqAndZzbz(String tjrq, String zzbz);
}

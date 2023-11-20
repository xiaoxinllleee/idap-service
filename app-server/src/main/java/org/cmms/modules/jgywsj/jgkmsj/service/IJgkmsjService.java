package org.cmms.modules.jgywsj.jgkmsj.service;

import org.cmms.modules.jgywsj.jgkmsj.entity.JgkmsjCk;
import org.cmms.modules.jgywsj.jgkmsj.entity.TbTjfxJgkmsj;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * @Description: 机构科目数据
 * @Author: jeecg-boot
 * @Date:   2021-05-14
 * @Version: V1.0
 */
public interface IJgkmsjService extends IService<TbTjfxJgkmsj> {
    public JgkmsjCk queryCkxxByTjrqAndZzbz(String tjrq, String zzbz);

    public List<Date> getLatestTjrq(String zzbz);
}

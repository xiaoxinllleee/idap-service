package org.cmms.modules.khxxgl.khflgl.shxq.service;

import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khxxgl.khflgl.shxq.entity.Shxq;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 商户户采集信息
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
public interface IShxqService extends IService<Shxq> {
    public void init();

    public List<Shxq> selectByShxx (String hhbm);

    public Integer getEzfskmBySjjyzZjhm(String zjhm);
}

package org.cmms.modules.khgl.khzhfx.khzhhz.service;

import org.cmms.modules.khgl.khzhfx.khzhhz.entity.Khjlhz;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户转化客户经理汇总
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
public interface IKhjlhzService extends IService<Khjlhz> {
    public void extract(String ksrq,String jsrq);
}

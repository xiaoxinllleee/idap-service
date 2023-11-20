package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShcjxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 商户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface ICamsZcsxShcjxxService extends IService<CamsZcsxShcjxx> {
    public CamsZcsxShcjxx getByShid(String shid);
}

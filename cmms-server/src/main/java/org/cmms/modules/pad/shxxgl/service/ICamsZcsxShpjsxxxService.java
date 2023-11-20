package org.cmms.modules.pad.shxxgl.service;

import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 商户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
public interface ICamsZcsxShpjsxxxService extends IService<CamsZcsxShpjsxxx> {
    public CamsZcsxShpjsxxx getByShid(String shid);
}

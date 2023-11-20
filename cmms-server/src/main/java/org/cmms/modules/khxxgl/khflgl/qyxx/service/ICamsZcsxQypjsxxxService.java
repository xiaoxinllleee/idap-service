package org.cmms.modules.khxxgl.khflgl.qyxx.service;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQypjsxxx;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;

/**
 * @Description: 企业评级授信信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
public interface ICamsZcsxQypjsxxxService extends IService<CamsZcsxQypjsxxx> {
    public CamsZcsxQypjsxxx getByQyid(String qyid);
}

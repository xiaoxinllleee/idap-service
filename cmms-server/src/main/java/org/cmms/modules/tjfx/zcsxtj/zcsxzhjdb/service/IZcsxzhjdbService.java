package org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.zcsxtj.zcsxzhjdb.entity.Zcsxzhjdb;

/**
 * @Description: 整村授信支行进度表
 * @Author: jeecg-boot
 * @Date:   2023-05-20
 * @Version: V1.0
 */
public interface IZcsxzhjdbService extends IService<Zcsxzhjdb> {
    void init(String tjrq);
}

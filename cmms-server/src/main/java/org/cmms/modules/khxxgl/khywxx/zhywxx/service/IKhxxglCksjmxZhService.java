package org.cmms.modules.khxxgl.khywxx.zhywxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;

import java.util.List;

/**
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
public interface IKhxxglCksjmxZhService extends IService<KhxxglCksjmxZh> {

    List<KhxxglCksjmxZh> getCkqsByZjhm(String zjhm);

}

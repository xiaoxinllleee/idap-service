package org.cmms.modules.khxxgl.khywxx.qhywxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglCksjmxQh;

import java.util.List;

/**
 * @Description: 客户信息管理存款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
public interface IKhxxglCksjmxQhService extends IService<KhxxglCksjmxQh> {

    List<KhxxglCksjmxQh> getCkqsByZjhm(String zjhm);

}

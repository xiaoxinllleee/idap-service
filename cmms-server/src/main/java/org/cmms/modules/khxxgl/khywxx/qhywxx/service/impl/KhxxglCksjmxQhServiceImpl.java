package org.cmms.modules.khxxgl.khywxx.qhywxx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khxxgl.khywxx.qhywxx.entity.KhxxglCksjmxQh;
import org.cmms.modules.khxxgl.khywxx.qhywxx.mapper.KhxxglCksjmxQhMapper;
import org.cmms.modules.khxxgl.khywxx.qhywxx.service.IKhxxglCksjmxQhService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 客户信息管理存款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
public class KhxxglCksjmxQhServiceImpl extends ServiceImpl<KhxxglCksjmxQhMapper, KhxxglCksjmxQh> implements IKhxxglCksjmxQhService {

    @Override
    public List<KhxxglCksjmxQh> getCkqsByZjhm(String zjhm) {


        return null;
    }

}

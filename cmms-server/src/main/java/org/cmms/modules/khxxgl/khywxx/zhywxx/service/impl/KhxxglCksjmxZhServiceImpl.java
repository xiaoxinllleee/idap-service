package org.cmms.modules.khxxgl.khywxx.zhywxx.service.impl;

import org.cmms.modules.khxxgl.khywxx.zhywxx.entity.KhxxglCksjmxZh;
import org.cmms.modules.khxxgl.khywxx.zhywxx.mapper.KhxxglCksjmxZhMapper;
import org.cmms.modules.khxxgl.khywxx.zhywxx.service.IKhxxglCksjmxZhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户信息管理存款数据明细支行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
public class KhxxglCksjmxZhServiceImpl extends ServiceImpl<KhxxglCksjmxZhMapper, KhxxglCksjmxZh> implements IKhxxglCksjmxZhService {

    @Override
    public List<KhxxglCksjmxZh> getCkqsByZjhm(String zjhm) {


        return null;
    }

}

package org.cmms.modules.khgl.khhmc.service.impl;

import org.cmms.modules.khgl.khhmc.entity.Khhmc;
import org.cmms.modules.khgl.khhmc.entity.KhhmcImport;
import org.cmms.modules.khgl.khhmc.mapper.KhhmcMapper;
import org.cmms.modules.khgl.khhmc.service.IKhhmcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Service
public class KhhmcServiceImpl extends ServiceImpl<KhhmcMapper, Khhmc> implements IKhhmcService {
    @Autowired
    private KhhmcMapper khhmcMapper;

    public Khhmc queryByZjhm(String zjhm) {
        return khhmcMapper.queryByZjhm(zjhm);
    }

    public Khhmc queryByZjhmAndHhbm(String zjhm, String hhbm) {
        return khhmcMapper.queryByZjhmAndHhbm(zjhm, hhbm);
    }

    public void initKhhmcxx() {
        khhmcMapper.initKhhmcxx();
    }

    @Override
    public List<Khhmc> selectByFrzjhm(String frzjhm) {
        return khhmcMapper.selectByFrzjhm(frzjhm);
    }
}

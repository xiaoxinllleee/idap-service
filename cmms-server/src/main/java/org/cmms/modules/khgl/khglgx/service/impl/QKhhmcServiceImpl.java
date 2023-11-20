package org.cmms.modules.khgl.khglgx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.khglgx.entity.Khhmc;
import org.cmms.modules.khgl.khglgx.mapper.QKhhmcMapper;
import org.cmms.modules.khgl.khglgx.service.IKhhmcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2019-09-26
 * @Version: V1.0
 */
@Service
public class QKhhmcServiceImpl extends ServiceImpl<QKhhmcMapper, Khhmc> implements IKhhmcService {
    @Autowired
    private QKhhmcMapper khhmcMapper;

    public Khhmc queryByZjhm(String zjhm) {
        return khhmcMapper.queryByZjhm(zjhm);
    }

    public Khhmc queryByZjhmAndHhbm(String zjhm, String hhbm) {
        return khhmcMapper.queryByZjhmAndHhbm(zjhm, hhbm);
    }

    public void initKhhmcxx() {
        khhmcMapper.initKhhmcxx();
    }
}

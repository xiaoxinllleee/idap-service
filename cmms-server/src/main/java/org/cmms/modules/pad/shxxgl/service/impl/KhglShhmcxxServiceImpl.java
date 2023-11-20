package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.KhglShhmcxx;
import org.cmms.modules.pad.shxxgl.mapper.KhglShhmcxxMapper;
import org.cmms.modules.pad.shxxgl.service.IKhglShhmcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;

/**
 * @Description: 商户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class KhglShhmcxxServiceImpl extends ServiceImpl<KhglShhmcxxMapper, KhglShhmcxx> implements IKhglShhmcxxService {
    @Autowired
    private KhglShhmcxxMapper mapper;

    @Override
    public HashMap<String, Object> getYwxxByZjhm(String zjhm) {
        return mapper.getYwxxByZjhm(zjhm);
    }


    @Override
    public void init(String shid, String yggh, String lrr){
        mapper.init(shid, yggh, lrr);
    }
}

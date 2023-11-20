package org.cmms.modules.khgl.nh.service.impl;

import org.cmms.modules.khgl.nh.entity.KhglNhhzzllb;
import org.cmms.modules.khgl.nh.mapper.KhglNhMapper;
import org.cmms.modules.khgl.nh.service.IKhglNhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
@Service
public class KhglNhServiceImpl extends ServiceImpl<KhglNhMapper, KhglNhhzzllb> implements IKhglNhService {

    @Autowired
    KhglNhMapper khglNhMapper;

    @Override
    public List<KhglNhhzzllb> selectByMainId(String hhbm){
        return khglNhMapper.selectByMainId(hhbm);
    }
}

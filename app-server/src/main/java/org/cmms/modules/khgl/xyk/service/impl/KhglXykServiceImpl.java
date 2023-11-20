package org.cmms.modules.khgl.xyk.service.impl;

import org.cmms.modules.khgl.xyk.entity.KhglXyk;
import org.cmms.modules.khgl.xyk.mapper.KhglXykMapper;
import org.cmms.modules.khgl.xyk.service.IKhglXykService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 信用卡_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Service
public class KhglXykServiceImpl extends ServiceImpl<KhglXykMapper, KhglXyk> implements IKhglXykService {

    @Override
    public String getYcshj(String jgdm, Date tjrq) {
        return baseMapper.getYcshj(jgdm,tjrq);
    }

    @Override
    public String getDate(String jgdm,String yggh) {
        return baseMapper.getDate(jgdm,yggh);
    }
}

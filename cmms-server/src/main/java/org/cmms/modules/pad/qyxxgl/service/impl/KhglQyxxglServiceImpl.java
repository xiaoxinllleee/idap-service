package org.cmms.modules.pad.qyxxgl.service.impl;

import org.cmms.modules.pad.qyxxgl.entity.VKhglQyxxgl;
import org.cmms.modules.pad.qyxxgl.mapper.KhglQyxxglMapper;
import org.cmms.modules.pad.qyxxgl.service.IKhglQyxxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 企业信息管理_pad
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
@Service
public class KhglQyxxglServiceImpl extends ServiceImpl<KhglQyxxglMapper, VKhglQyxxgl> implements IKhglQyxxglService {

    @Override
    public void init(String qyid, String yggh, String lrr) {
        baseMapper.init(qyid, yggh, lrr);
    }
}

package org.cmms.modules.khxxgl.khflgl.qyxx.service.impl;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.KhglQyhmcxx;
import org.cmms.modules.khxxgl.khflgl.qyxx.mapper.KhglQyhmcxxMapper;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.IKhglQyhmcxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 企业信息管理
 * @Author: jeecg-boot
 * @Date:   2022-11-04
 * @Version: V1.0
 */
@Service
public class KhglQyhmcxxServiceImpl extends ServiceImpl<KhglQyhmcxxMapper, KhglQyhmcxx> implements IKhglQyhmcxxService {

    @Override
    public void init(String shid, String yggh, String lrr) {
        baseMapper.init(shid, yggh, lrr);
    }
}

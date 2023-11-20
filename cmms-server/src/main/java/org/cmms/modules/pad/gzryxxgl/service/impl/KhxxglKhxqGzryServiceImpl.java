package org.cmms.modules.pad.gzryxxgl.service.impl;


import org.cmms.modules.pad.gzryxxgl.entity.KhxxglKhxqGzry;
import org.cmms.modules.pad.gzryxxgl.mapper.KhxxglKhxqGzryMapper;
import org.cmms.modules.pad.gzryxxgl.service.IKhxxglKhxqGzryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 公职人员信息管理
 * @Author: jeecg-boot
 * @Date:   2022-08-11
 * @Version: V1.0
 */
@Service
public class KhxxglKhxqGzryServiceImpl extends ServiceImpl<KhxxglKhxqGzryMapper, KhxxglKhxqGzry> implements IKhxxglKhxqGzryService {

    @Autowired
    private KhxxglKhxqGzryMapper khxxglKhxqGzryMapper;

    @Override
    public void init(String gzryid, String yggh, String lrr) {
        khxxglKhxqGzryMapper.init(gzryid,yggh,lrr);
    }
}

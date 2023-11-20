package org.cmms.modules.khxxgl.khflgl.qyxx.service.impl;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQypjsxxx;
import org.cmms.modules.khxxgl.khflgl.qyxx.mapper.CamsZcsxQypjsxxxMapper;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.ICamsZcsxQypjsxxxService;
import org.cmms.modules.pad.shxxgl.entity.CamsZcsxShpjsxxx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: 企业评级授信信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
@Service
public class CamsZcsxQypjsxxxServiceImpl extends ServiceImpl<CamsZcsxQypjsxxxMapper, CamsZcsxQypjsxxx> implements ICamsZcsxQypjsxxxService {
    @Autowired
    CamsZcsxQypjsxxxMapper mapper;

    @Override
    public CamsZcsxQypjsxxx getByQyid(String qyid) {
        return mapper.getByQyid(qyid);
    }
}

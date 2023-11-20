package org.cmms.modules.khxxgl.khflgl.qyxx.service.impl;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsZcsxQyfcxx;
import org.cmms.modules.khxxgl.khflgl.qyxx.mapper.CamsZcsxQyfcxxMapper;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.ICamsZcsxQyfcxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 企业房产信息
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
@Service
public class CamsZcsxQyfcxxServiceImpl extends ServiceImpl<CamsZcsxQyfcxxMapper, CamsZcsxQyfcxx> implements ICamsZcsxQyfcxxService {

    @Override
    public List<CamsZcsxQyfcxx> getByQyid(String qyid) {
        return baseMapper.getByQyid(qyid);
    }
}

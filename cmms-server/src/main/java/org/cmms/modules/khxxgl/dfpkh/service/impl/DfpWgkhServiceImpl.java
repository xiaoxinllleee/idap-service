package org.cmms.modules.khxxgl.dfpkh.service.impl;

import org.cmms.modules.khxxgl.dfpkh.entity.DfpWgkh;
import org.cmms.modules.khxxgl.dfpkh.mapper.DfpWgkhMapper;
import org.cmms.modules.khxxgl.dfpkh.service.IDfpWgkhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 待分配网格客户
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class DfpWgkhServiceImpl extends ServiceImpl<DfpWgkhMapper, DfpWgkh> implements IDfpWgkhService {
    @Override
    public void extract() {
        baseMapper.extract();
    }

}

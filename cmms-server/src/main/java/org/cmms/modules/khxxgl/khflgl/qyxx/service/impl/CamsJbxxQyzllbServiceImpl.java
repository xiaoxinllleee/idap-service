package org.cmms.modules.khxxgl.khflgl.qyxx.service.impl;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.CamsJbxxQyzllb;
import org.cmms.modules.khxxgl.khflgl.qyxx.mapper.CamsJbxxQyzllbMapper;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.ICamsJbxxQyzllbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 企业附件资料列表
 * @Author: jeecg-boot
 * @Date:   2022-11-03
 * @Version: V1.0
 */
@Service
public class CamsJbxxQyzllbServiceImpl extends ServiceImpl<CamsJbxxQyzllbMapper, CamsJbxxQyzllb> implements ICamsJbxxQyzllbService {

    @Override
    public List<CamsJbxxQyzllb> getByQyid(String qyid) {
        return baseMapper.getByQyid(qyid);
    }
}

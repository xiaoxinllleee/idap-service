package org.cmms.modules.khxxgl.khgs.service.impl;

import org.cmms.modules.khxxgl.khgs.entity.khsskhjl;
import org.cmms.modules.khxxgl.khgs.mapper.khsskhjlMapper;
import org.cmms.modules.khxxgl.khgs.service.IkhsskhjlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户归属客户经理
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Service
public class khsskhjlServiceImpl extends ServiceImpl<khsskhjlMapper, khsskhjl> implements IkhsskhjlService {
    @Override
    public List<String> getKhjbzlZjhmKhjlgh(String yggh) {
        return baseMapper.getKhjbzlZjhmKhjlgh(yggh);
    }
}

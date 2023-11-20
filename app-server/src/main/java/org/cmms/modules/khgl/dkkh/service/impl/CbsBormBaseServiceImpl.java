package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khgl.dkkh.entity.CbsBormBase;
import org.cmms.modules.khgl.dkkh.mapper.CbsBormBaseMapper;
import org.cmms.modules.khgl.dkkh.service.ICbsBormBaseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2022-04-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class CbsBormBaseServiceImpl extends ServiceImpl<CbsBormBaseMapper, CbsBormBase> implements ICbsBormBaseService {

    @Override
    public List<CbsBormBase> getCardNoIsNotNull(String zjhm) {
        return baseMapper.getCardNoIsNotNull(zjhm);
    }
}

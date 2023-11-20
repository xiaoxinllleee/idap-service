package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.ygjx.entity.BasicWageAllowabces;
import org.cmms.modules.ygjx.entity.GzMxVO;
import org.cmms.modules.ygjx.mapper.BasicWageAllowabcesMapper;
import org.cmms.modules.ygjx.service.IBasicWageAllowabcesService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 津贴
 * @Author: jeecg-boot
 * @Date:   2022-03-02
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class BasicWageAllowabcesServiceImpl extends ServiceImpl<BasicWageAllowabcesMapper, BasicWageAllowabces> implements IBasicWageAllowabcesService {

    @Override
    public GzMxVO getByYggh(String yggh, String date) {
        return baseMapper.getByYggh(yggh,date);
    }

    @Override
    public BigDecimal jbgz(String yggh, String date) {
        return baseMapper.getJbgz(yggh, date);
    }
}

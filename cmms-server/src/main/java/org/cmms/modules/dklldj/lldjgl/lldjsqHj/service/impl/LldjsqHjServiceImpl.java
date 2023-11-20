package org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.entity.RateDjsqxxHj;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.mapper.LldjsqHjMapper;
import org.cmms.modules.dklldj.lldjgl.lldjsqHj.service.ILldjsqHjService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Slf4j
@Service
public class LldjsqHjServiceImpl extends ServiceImpl<LldjsqHjMapper, RateDjsqxxHj> implements ILldjsqHjService {
    @Override
    public BigDecimal querySnzxll(String tablename, String zjhm) {
        return baseMapper.querySnzxll(tablename,zjhm);
    }

    @Override
    public BigDecimal queryCkrpyeSumGR(String tablename, String zjhm) {
        return baseMapper.queryCkrpyeSumGR(tablename,zjhm);
    }

    @Override
    public BigDecimal queryCkrpyeSumQY(String tablename, String zjhm) {
        return baseMapper.queryCkrpyeSumQY(tablename,zjhm);
    }
}

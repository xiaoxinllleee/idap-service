package org.cmms.modules.khjg.jczbjg.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.khjg.jczbjg.entity.PmaFBaseIndexRes;
import org.cmms.modules.khjg.jczbjg.mapper.PmaFBaseIndexResMapper;
import org.cmms.modules.khjg.jczbjg.service.IPmaFBaseIndexResService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 基础指标结果
 * @Author: jeecg-boot
 * @Date:   2021-05-10
 * @Version: V1.0
 */
@Service

public class PmaFBaseIndexResServiceImpl extends ServiceImpl<PmaFBaseIndexResMapper, PmaFBaseIndexRes> implements IPmaFBaseIndexResService {

    @Override
    public List<PmaFBaseIndexRes> getFormulaList(String statDate, List<String> indexList) {

        return baseMapper.getFormulaList(statDate,indexList);
    }
}

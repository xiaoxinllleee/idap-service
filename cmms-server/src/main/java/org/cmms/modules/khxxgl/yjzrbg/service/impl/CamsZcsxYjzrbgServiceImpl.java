package org.cmms.modules.khxxgl.yjzrbg.service.impl;

import org.cmms.modules.khxxgl.yjzrbg.entity.CamsZcsxYjzrbg;
import org.cmms.modules.khxxgl.yjzrbg.entity.YjzrbgScore;
import org.cmms.modules.khxxgl.yjzrbg.mapper.CamsZcsxYjzrbgMapper;
import org.cmms.modules.khxxgl.yjzrbg.service.ICamsZcsxYjzrbgService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 一键准入报告
 * @Author: jeecg-boot
 * @Date:   2023-04-14
 * @Version: V1.0
 */
@Service
public class CamsZcsxYjzrbgServiceImpl extends ServiceImpl<CamsZcsxYjzrbgMapper, CamsZcsxYjzrbg> implements ICamsZcsxYjzrbgService {

    @Override
    public void updateScore(YjzrbgScore yjzrbgScore) {
        baseMapper.updateScore(yjzrbgScore);
    }

    @Override
    public void updateCsed(BigDecimal hzed, String zjhm) {
        baseMapper.updateCsed(hzed, zjhm);
    }

    @Override
    public void mergeNhxx() {
        baseMapper.mergeNhxx();
    }
}

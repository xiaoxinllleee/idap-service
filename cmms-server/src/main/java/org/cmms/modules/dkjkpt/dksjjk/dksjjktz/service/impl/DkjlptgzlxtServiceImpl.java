package org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.entity.Dkjlptgzlxt;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.mapper.DkjlptgzlxtMapper;
import org.cmms.modules.dkjkpt.dksjjk.dksjjktz.service.IDkjlptgzlxtService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款监控平台关注类_湘潭
 * @Author: jeecg-boot
 * @Date:   2023-06-01
 * @Version: V1.0
 */
@Service
public class DkjlptgzlxtServiceImpl extends ServiceImpl<DkjlptgzlxtMapper, Dkjlptgzlxt> implements IDkjlptgzlxtService {

    @Override
    public void gzl(String tjnf, String zjhm) {
        baseMapper.gzl(tjnf,zjhm);
    }
}

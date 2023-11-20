package org.cmms.modules.dkjkpt.dkjkptfxtsh.service.impl;

import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DedkmxVo;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.DkjkptFxtsh;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.entity.YqdkmxVo;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.mapper.DkjkptFxtshMapper;
import org.cmms.modules.dkjkpt.dkjkptfxtsh.service.IDkjkptFxtshService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 风险提示函
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@Service
public class DkjkptFxtshServiceImpl extends ServiceImpl<DkjkptFxtshMapper, DkjkptFxtsh> implements IDkjkptFxtshService {

    @Override
    public void init(String tjyf) {
        baseMapper.init(tjyf);
    }

    @Override
    public List<DedkmxVo> dedk(String table, String jgdm, String tjyf) {
        return baseMapper.dedk(table, jgdm, tjyf);
    }

    @Override
    public List<YqdkmxVo> yqdk(String table, String jgdm, String tjyf) {
        return baseMapper.yqdk(table, jgdm, tjyf);
    }
}

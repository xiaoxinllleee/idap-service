package org.cmms.modules.pad.pyxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.cmms.modules.pad.pyxx.entity.Nhplpy;
import org.cmms.modules.pad.pyxx.mapper.NhplpyMapper;
import org.cmms.modules.pad.pyxx.service.INhplpyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户批量评议
 * @Author: jeecg-boot
 * @Date:   2022-03-07
 * @Version: V1.0
 */
@Service
public class NhplpyServiceImpl extends ServiceImpl<NhplpyMapper, Nhplpy> implements INhplpyService {

    @Override
    public List<Nhplpy> getByPyls(Integer pyls) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("pyls",pyls);
        return baseMapper.selectList(queryWrapper);
    }
}

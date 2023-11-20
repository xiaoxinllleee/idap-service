package org.cmms.modules.pad.nhczfp.service.impl;

import org.cmms.modules.pad.nhczfp.entity.Nhczfp;
import org.cmms.modules.pad.nhczfp.entity.NhczfpVo;
import org.cmms.modules.pad.nhczfp.mapper.NhczfpMapper;
import org.cmms.modules.pad.nhczfp.service.INhczfpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户村组复评
 * @Author: jeecg-boot
 * @Date:   2023-03-27
 * @Version: V1.0
 */
@Service
public class NhczfpServiceImpl extends ServiceImpl<NhczfpMapper, Nhczfp> implements INhczfpService {
    @Autowired
    private NhczfpMapper nhczfpMapper;

    @Override
    public List<NhczfpVo> getYwcbkbpyList(String wgbh) {
        return nhczfpMapper.getYwcbkbpyList(wgbh);
    }
}

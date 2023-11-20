package org.cmms.modules.pad.nhzhsd.service.impl;

import org.cmms.modules.pad.nhzhsd.entity.Nhzhsd;
import org.cmms.modules.pad.nhzhsd.mapper.NhzhsdMapper;
import org.cmms.modules.pad.nhzhsd.service.INhzhsdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户支行审定
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Service
public class NhzhsdServiceImpl extends ServiceImpl<NhzhsdMapper, Nhzhsd> implements INhzhsdService {
    @Autowired
    private NhzhsdMapper nhzhsdMapper;

    @Override
    public List<String> getWwcCzfpWg(String khjl) {
        return nhzhsdMapper.getWwcCzfpWg(khjl);
    }
}

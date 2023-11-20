package org.cmms.modules.pad.pyxx.service.impl;

import org.cmms.modules.pad.nhxxgl.entity.KhglYwhywwlxxPad;
import org.cmms.modules.pad.pyxx.entity.Pyyxx;
import org.cmms.modules.pad.pyxx.mapper.PyyxxMapper;
import org.cmms.modules.pad.pyxx.service.IPyyxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 评议员信息
 * @Author: jeecg-boot
 * @Date:   2020-07-24
 * @Version: V1.0
 */
@Service
public class PyyxxServiceImpl extends ServiceImpl<PyyxxMapper, Pyyxx> implements IPyyxxService {

    @Override
    public List<String> getByPyls(Integer pyls) {
        return baseMapper.getByPyls(pyls);
    }

    @Override
    public void updateSxxx(String id){
        baseMapper.updateSxxx(id);
    }

    @Override
    public List<KhglYwhywwlxxPad> getbldkInfo(String hhbm) {
        return baseMapper.getbldkInfo(hhbm);
    }
}

package org.cmms.modules.tjfx.pjsxtjbb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cmms.modules.tjfx.pjsxtjbb.entity.Pjsxtjbb;
import org.cmms.modules.tjfx.pjsxtjbb.mapper.PjsxtjbbMapper;
import org.cmms.modules.tjfx.pjsxtjbb.service.IPjsxtjbbService;
import org.cmms.modules.tjfx.pjsxtjbb.vo.NhPjsxxxMx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Service
public class PjsxtjbbServiceImpl extends ServiceImpl<PjsxtjbbMapper, Pjsxtjbb> implements IPjsxtjbbService {

    @Override
    public void init(String tjyf) {
        baseMapper.init(tjyf);
    }


    @Override
    public List<NhPjsxxxMx> pjsxtjbbExl(String sswg,String sjrq) {
        return baseMapper.pjsxtjbbMx(sswg,sjrq);
    }
}

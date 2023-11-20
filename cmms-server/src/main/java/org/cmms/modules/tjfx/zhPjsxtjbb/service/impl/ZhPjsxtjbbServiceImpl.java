package org.cmms.modules.tjfx.zhPjsxtjbb.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.cmms.modules.tjfx.zhPjsxtjbb.Vo.ZhPjsxxxMx;
import org.cmms.modules.tjfx.zhPjsxtjbb.entity.ZhPjsxtjbb;
import org.cmms.modules.tjfx.zhPjsxtjbb.mapper.ZhPjsxtjbbMapper;
import org.cmms.modules.tjfx.zhPjsxtjbb.service.IZhPjsxtjbbService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 支行评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Service
public class ZhPjsxtjbbServiceImpl extends ServiceImpl<ZhPjsxtjbbMapper, ZhPjsxtjbb> implements IZhPjsxtjbbService {



    @Override
    public List<ZhPjsxxxMx> zhPjsxtjbbMx(String sszh, String sjrq) {
        return baseMapper.zhPjsxtjbbMx(sszh, sjrq);
    }
}

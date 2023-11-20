package org.cmms.modules.tjfx.shpjsx.zh.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.shpjsx.zh.entity.ShZhPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.zh.entity.TjfxZhpjsxtjbbSh;
import org.cmms.modules.tjfx.shpjsx.zh.mapper.TjfxZhpjsxtjbbShMapper;
import org.cmms.modules.tjfx.shpjsx.zh.service.ITjfxZhpjsxtjbbShService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 支行评级授信统计-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
@Service
public class TjfxZhpjsxtjbbShServiceImpl extends ServiceImpl<TjfxZhpjsxtjbbShMapper, TjfxZhpjsxtjbbSh> implements ITjfxZhpjsxtjbbShService {

    @Override
    public IPage<ShZhPjsxMxVo> queryPageListMx(Page page, String sjrq, String sszh,String type) {
        return baseMapper.queryPageListMx(page,sjrq,sszh,type);
    }

    @Override
    public List<ShZhPjsxMxVo> queryListMx(String sjrq, String sszh,String type) {
        return baseMapper.queryListMx(sjrq,sszh,type);
    }
}

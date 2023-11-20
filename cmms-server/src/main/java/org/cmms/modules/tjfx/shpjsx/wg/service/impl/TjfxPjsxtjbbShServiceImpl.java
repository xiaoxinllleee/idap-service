package org.cmms.modules.tjfx.shpjsx.wg.service.impl;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.tjfx.shpjsx.wg.entity.ShPjsxMxVo;
import org.cmms.modules.tjfx.shpjsx.wg.entity.TjfxPjsxtjbbSh;
import org.cmms.modules.tjfx.shpjsx.wg.mapper.TjfxPjsxtjbbShMapper;
import org.cmms.modules.tjfx.shpjsx.wg.service.ITjfxPjsxtjbbShService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 评级授信统计报表-商户
 * @Author: jeecg-boot
 * @Date:   2023-09-09
 * @Version: V1.0
 */
@Service
public class TjfxPjsxtjbbShServiceImpl extends ServiceImpl<TjfxPjsxtjbbShMapper, TjfxPjsxtjbbSh> implements ITjfxPjsxtjbbShService {

    @Override
    public void initData(String sjrq,String yggh) {
        baseMapper.initData(sjrq,yggh);
    }

    @Override
    public IPage<ShPjsxMxVo> queryPageListMx(Page page, String sjrq, String wgbh,String type) {
        return baseMapper.queryPageListMx(page,sjrq,wgbh,type);
    }

    @Override
    public List<ShPjsxMxVo> queryListMx(String sjrq, String wgbh,String type) {
        return baseMapper.queryListMx(sjrq,wgbh,type);
    }
}

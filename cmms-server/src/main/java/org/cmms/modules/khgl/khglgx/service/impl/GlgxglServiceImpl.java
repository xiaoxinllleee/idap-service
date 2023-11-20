package org.cmms.modules.khgl.khglgx.service.impl;

import org.cmms.modules.khgl.khglgx.entity.Glgxgl;
import org.cmms.modules.khgl.khglgx.mapper.GlgxglMapper;
import org.cmms.modules.khgl.khglgx.service.IGlgxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 关联关系管理
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Service
public class GlgxglServiceImpl extends ServiceImpl<GlgxglMapper, Glgxgl> implements IGlgxglService {

    @Override
    public void updateGlgx(String ykhjl ,String zyhkhjl,String name) {
        baseMapper.updateGlgx(ykhjl,zyhkhjl,name);
    }

    @Override
    public void updateKhGlgx(String zjhm, String zyhkhjl, String name, String khxz) {
        baseMapper.updateKhGlgx(zjhm,zyhkhjl,name,khxz);

    }


}

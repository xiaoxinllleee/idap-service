package org.cmms.modules.tjfx.jcsjgl.gsxxdr.service.impl;

import org.cmms.modules.tjfx.jcsjgl.gsxxdr.entity.TjfxGsxxdr;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.mapper.TjfxGsxxdrMapper;
import org.cmms.modules.tjfx.jcsjgl.gsxxdr.service.ITjfxGsxxdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-12
 * @Version: V1.0
 */
@Service
public class TjfxGsxxdrServiceImpl extends ServiceImpl<TjfxGsxxdrMapper, TjfxGsxxdr> implements ITjfxGsxxdrService {

    @Autowired
    private TjfxGsxxdrMapper tjfxGsxxdrMapper;

    @Override
    public int delete2(String zjhm,String sszh,Date gsrq) {
        return tjfxGsxxdrMapper.delete2(zjhm,sszh,gsrq);
    }

    @Override
    public TjfxGsxxdr queryByMsg(String sszh, String zjhm, Date gsrq) {
        return tjfxGsxxdrMapper.queryByMsg(sszh,zjhm,gsrq);
    }

    @Override
    public Long querygscs(String sszh,String zjhm) {
        return tjfxGsxxdrMapper.querygscs(sszh,zjhm);
    }

}

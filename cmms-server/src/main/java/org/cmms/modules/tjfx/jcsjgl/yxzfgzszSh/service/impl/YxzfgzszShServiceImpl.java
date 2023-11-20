package org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.service.impl;

import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.entity.YxzfgzszSh;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.mapper.YxzfgzszShMapper;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzszSh.service.IYxzfgzszShService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 有效走访规则设置_商户
 * @Author: jeecg-boot
 * @Date:   2022-05-11
 * @Version: V1.0
 */
@Service
public class YxzfgzszShServiceImpl extends ServiceImpl<YxzfgzszShMapper, YxzfgzszSh> implements IYxzfgzszShService {
    public List<Yxzfmx> getZfzbxxByShid(String shid,String tjrq) {
        return baseMapper.getZfzbxxByShid(shid,tjrq);
    }
}

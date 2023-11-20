package org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.service.impl;

import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.entity.Yxzfgzsz;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.mapper.YxzfgzszMapper;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.service.IYxzfgzszService;
import org.cmms.modules.tjfx.jcsjgl.yxzfgzsz.vo.Yxzfmx;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 农户有效走访规则设置
 * @Author: jeecg-boot
 * @Date:   2022-01-17
 * @Version: V1.0
 */
@Service
public class YxzfgzszServiceImpl extends ServiceImpl<YxzfgzszMapper, Yxzfgzsz> implements IYxzfgzszService {

    public List<Yxzfmx> getZfzbxxByHhbm(String hhbm,String tjrq) {
        return baseMapper.getZfzbxxByHhbm(hhbm,tjrq);
    }
}

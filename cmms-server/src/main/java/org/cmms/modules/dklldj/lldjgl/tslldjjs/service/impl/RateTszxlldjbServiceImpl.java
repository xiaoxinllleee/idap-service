package org.cmms.modules.dklldj.lldjgl.tslldjjs.service.impl;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.entity.RateTszxlldjb;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.mapper.RateTszxlldjbMapper;
import org.cmms.modules.dklldj.lldjgl.tslldjjs.service.IRateTszxlldjbService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 特殊利率定价
 * @Author: jeecg-boot
 * @Date:   2021-04-02
 * @Version: V1.0
 */
@Service
public class RateTszxlldjbServiceImpl extends ServiceImpl<RateTszxlldjbMapper, RateTszxlldjb> implements IRateTszxlldjbService {

    @Override
    public boolean isNotSureByZjhm(String zjhm, Date djnf, String spzt) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("zjhm", zjhm);
        queryWrapper.eq("djnf", djnf);
        queryWrapper.eq("spzt", Integer.parseInt(spzt));
        List list = baseMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(list)) {
            return true;
        } else {
            return false;
        }
    }
}

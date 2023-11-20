package org.cmms.modules.khgl.grkhgl.service.impl;

import org.cmms.modules.khgl.grkhgl.entity.ZcsxNhcjxx;
import org.cmms.modules.khgl.grkhgl.mapper.ZcsxNhcjxxMapper;
import org.cmms.modules.khgl.grkhgl.service.IZcsxNhcjxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 农户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Service
public class ZcsxNhcjxxServiceImpl extends ServiceImpl<ZcsxNhcjxxMapper, ZcsxNhcjxx> implements IZcsxNhcjxxService {

    @Autowired
    private ZcsxNhcjxxMapper zcsxNhcjxxMapper;
    @Override
    public int updateCjzt(String id) {
        return zcsxNhcjxxMapper.updateCjzt(id);
    }
}

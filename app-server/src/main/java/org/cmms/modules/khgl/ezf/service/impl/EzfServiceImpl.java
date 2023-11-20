package org.cmms.modules.khgl.ezf.service.impl;

import org.cmms.modules.khgl.ezf.entity.Ezf;
import org.cmms.modules.khgl.ezf.mapper.EzfMapper;
import org.cmms.modules.khgl.ezf.service.IEzfService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: E支付
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Service
public class EzfServiceImpl extends ServiceImpl<EzfMapper, Ezf> implements IEzfService {

    @Override
    public String getDate() {
        return baseMapper.getDate();
    }
}

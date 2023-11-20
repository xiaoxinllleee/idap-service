package org.cmms.modules.pad.shxxgl.service.impl;

import org.cmms.modules.pad.shxxgl.entity.Xjlghjc;
import org.cmms.modules.pad.shxxgl.mapper.XjlghjcMapper;
import org.cmms.modules.pad.shxxgl.service.IXjlghjcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 现金流归行检测
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
@Service
public class XjlghjcServiceImpl extends ServiceImpl<XjlghjcMapper, Xjlghjc> implements IXjlghjcService {

    @Autowired
    XjlghjcMapper xjlghjcMapper;

    @Override
    public List<Xjlghjc> queryXjlGhjc(String hhbm){
        return xjlghjcMapper.queryXjlGhjc(hhbm);
    }
}

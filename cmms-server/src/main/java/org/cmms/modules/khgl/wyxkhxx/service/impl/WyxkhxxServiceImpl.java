package org.cmms.modules.khgl.wyxkhxx.service.impl;

import org.cmms.modules.khgl.wyxkhxx.entity.Wyxkhxx;
import org.cmms.modules.khgl.wyxkhxx.mapper.WyxkhxxMapper;
import org.cmms.modules.khgl.wyxkhxx.service.IWyxkhxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 未用信客户信息
 * @Author: jeecg-boot
 * @Date:   2019-09-29
 * @Version: V1.0
 */
@Service
public class WyxkhxxServiceImpl extends ServiceImpl<WyxkhxxMapper, Wyxkhxx> implements IWyxkhxxService {
    @Autowired
    private WyxkhxxMapper wyxkhxxMapper;

    public void initWyxkhxx() {
        wyxkhxxMapper.initWyxkhxx();
    }

    public List<Wyxkhxx> queryByHzcustid(String hzcustid) {
        return wyxkhxxMapper.queryByHzcustid(hzcustid);
    }
}

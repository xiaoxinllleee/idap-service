package org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.service.impl;

import org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.entity.Zhetcywyxtj;
import org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.mapper.ZhetcywyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.service.IZhetcywyxtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 支行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Service
public class ZhetcywyxtjServiceImpl extends ServiceImpl<ZhetcywyxtjMapper, Zhetcywyxtj> implements IZhetcywyxtjService {

    @Override
    public void pZhetcywyxtj(String tjyf) {
        baseMapper.pZhetcywyxtj(tjyf);
    }
}

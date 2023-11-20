package org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.service.impl;

import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.entity.Ygetcywyxtj;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.mapper.YgetcywyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.service.IYgetcywyxtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Service
public class YgetcywyxtjServiceImpl extends ServiceImpl<YgetcywyxtjMapper, Ygetcywyxtj> implements IYgetcywyxtjService {

    @Override
    public void pYgetcywyxtj(String tjyf) {
        baseMapper.pYgetcywyxtj(tjyf);
    }
}

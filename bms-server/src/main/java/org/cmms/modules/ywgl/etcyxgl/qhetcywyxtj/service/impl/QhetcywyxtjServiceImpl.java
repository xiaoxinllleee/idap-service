package org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.service.impl;

import org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.entity.Qhetcywyxtj;
import org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.mapper.QhetcywyxtjMapper;
import org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.service.IQhetcywyxtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 全行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Service
public class QhetcywyxtjServiceImpl extends ServiceImpl<QhetcywyxtjMapper, Qhetcywyxtj> implements IQhetcywyxtjService {

    @Override
    public void pQhetcywyxtj(String tjyf) {
        baseMapper.pQhetcywyxtj(tjyf);
    }
}

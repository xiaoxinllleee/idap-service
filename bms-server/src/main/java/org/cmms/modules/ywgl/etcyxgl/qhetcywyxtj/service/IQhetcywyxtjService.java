package org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.entity.Qhetcywyxtj;

/**
 * @Description: 全行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IQhetcywyxtjService extends IService<Qhetcywyxtj> {
    void pQhetcywyxtj(String tjyf);
}

package org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.entity.Ygetcywyxtj;

/**
 * @Description: 员工etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYgetcywyxtjService extends IService<Ygetcywyxtj> {
    void pYgetcywyxtj(String tjyf);

}

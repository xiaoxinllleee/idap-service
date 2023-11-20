package org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.zhetcywyxtj.entity.Zhetcywyxtj;

/**
 * @Description: 支行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IZhetcywyxtjService extends IService<Zhetcywyxtj> {
    void pZhetcywyxtj(String tjyf);

}

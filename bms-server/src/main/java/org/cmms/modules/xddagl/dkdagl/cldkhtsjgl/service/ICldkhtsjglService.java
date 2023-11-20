package org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddagl.dkdagl.cldkhtsjgl.entity.Cldkhtsjgl;

/**
 * @Description: 存量贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-06
 * @Version: V1.0
 */
@DS("eweb")
public interface ICldkhtsjglService extends IService<Cldkhtsjgl> {
    public void pCldkhtsjgl();
}

package org.cmms.modules.performance.loancustomer.dkhtzhxx.service;

import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.CldkkhxxImp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存量贷款客户信息导入
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
public interface ICldkkhxxImpService extends IService<CldkkhxxImp> {

    /**
     * 贷款客户 存量认领 数据导入处理
     * @param username 当前操作员用户名
     */
    void pRldkkhxxImp(String username);

}

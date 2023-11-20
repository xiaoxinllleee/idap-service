package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.CkkhghlsbImp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款客户管户历史表（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
public interface ICkkhghlsbImpService extends IService<CkkhghlsbImp> {

    /**
     * 存款客户 管户信息 数据导入处理
     * @param username 当前操作员用户名
     */
    void pCkkhGhxxImp(String username);

}

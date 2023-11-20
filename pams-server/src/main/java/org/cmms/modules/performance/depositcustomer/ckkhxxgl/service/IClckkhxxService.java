package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存量存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
public interface IClckkhxxService extends IService<Clckkhxx> {

    /**
     * 存款客户 存量认领 数据导入处理
     * @param username 当前操作员用户名
     */
    void pRlckkhxxImp(String username);

}

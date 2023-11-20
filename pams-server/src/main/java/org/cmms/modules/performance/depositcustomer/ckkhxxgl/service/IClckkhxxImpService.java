package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service;

import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.ClckkhxxImp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存量存款客户信息（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-11
 * @Version: V1.0
 */
public interface IClckkhxxImpService extends IService<ClckkhxxImp> {

    /**
     * 删除 存量存款客户信息（数据导入临时表）
     */
    void ClckkhxxImpDelete();

}

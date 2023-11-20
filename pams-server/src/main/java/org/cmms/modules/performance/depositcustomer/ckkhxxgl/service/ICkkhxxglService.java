package org.cmms.modules.performance.depositcustomer.ckkhxxgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.entity.Ckkhxxgl;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;

import java.util.List;

/**
 * @Description: 存款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2023-02-28
 * @Version: V1.0
 */
public interface ICkkhxxglService extends IService<Ckkhxxgl> {
    /**
     * 管户拓展信息提取
     */
    void init();
    /**
     * 认领存款客户信息提取
     * @param jgdm
     */
    void rlckkhxx(@Param("jgdm") String jgdm);

}

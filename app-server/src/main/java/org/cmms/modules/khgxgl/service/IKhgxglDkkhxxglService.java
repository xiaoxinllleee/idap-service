package org.cmms.modules.khgxgl.service;

import org.cmms.modules.khgxgl.entity.KhgxglDkkhxxgl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
public interface IKhgxglDkkhxxglService extends IService<KhgxglDkkhxxgl> {

    /**
     * 通过证件号码去查所有的贷款产品信息
     * */
    String getAllCpxxByZjhm(String zjhm);
    String getAllCpxxByHth(String hth);
}

package org.cmms.modules.workplace.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.workplace.entity.WorkPlaceSystemVersion;

/**
 * @Description: 系统版本号
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
public interface IWorkPlaceSystemVersionService extends IService<WorkPlaceSystemVersion> {

    /**
     * 获取测试环境最新版本号
     * @return
     */
    String getLatestTestVersion(String qydm);

    /**
     * 获取生产环境最新版本号
     * @return
     */
    String getLatestProdVersion(String qydm);

}

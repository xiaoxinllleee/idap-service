package org.cmms.modules.kmtj.jdcx.service;

import org.cmms.modules.kmtj.jdcx.entity.KmtjJdcx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 季度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface IKmtjJdcxService extends IService<KmtjJdcx> {
    void pYgmrscs(String tjyf);
}

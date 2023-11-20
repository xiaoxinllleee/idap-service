package org.cmms.modules.kmtj.ndcx.service;

import org.cmms.modules.kmtj.ndcx.entity.KmtjNdcx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 年度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface IKmtjNdcxService extends IService<KmtjNdcx> {
    void pYgmrscs(String tjyf);
}

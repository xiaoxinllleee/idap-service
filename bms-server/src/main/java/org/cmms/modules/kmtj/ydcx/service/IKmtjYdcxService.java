package org.cmms.modules.kmtj.ydcx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.kmtj.ydcx.entity.KmtjYdcx;

/**
 * @Description: 月度查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface IKmtjYdcxService extends IService<KmtjYdcx> {
    void pYgmrscs(String tjyf);
}

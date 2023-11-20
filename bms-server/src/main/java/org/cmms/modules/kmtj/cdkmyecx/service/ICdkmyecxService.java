package org.cmms.modules.kmtj.cdkmyecx.service;

import org.cmms.modules.kmtj.cdkmyecx.entity.Cdkmyecx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存贷科目余额查询
 * @Author: jeecg-boot
 * @Date:   2023-03-24
 * @Version: V1.0
 */
public interface ICdkmyecxService extends IService<Cdkmyecx> {
    void pYgmrscs(String tjyf);
}

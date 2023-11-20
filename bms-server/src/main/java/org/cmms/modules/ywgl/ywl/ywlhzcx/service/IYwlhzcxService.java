package org.cmms.modules.ywgl.ywl.ywlhzcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.ywl.ywlhzcx.entity.Ywlhzcx;

/**
 * @Description: 业务量汇总查询
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYwlhzcxService extends IService<Ywlhzcx> {
    void pYwlhzcx(String tjyf);
}

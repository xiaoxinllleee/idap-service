package org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.entity.Jcdkmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 交叉贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@DS("zx")//zx
public interface IJcdkmxService extends IService<Jcdkmx> {
    @DS("eweb")
    void pJcdkmx();
}

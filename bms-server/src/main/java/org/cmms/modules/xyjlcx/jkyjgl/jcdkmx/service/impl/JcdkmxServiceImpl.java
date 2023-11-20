package org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.service.impl;

import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.entity.Jcdkmx;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.mapper.JcdkmxMapper;
import org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.service.IJcdkmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 交叉贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@Service
public class JcdkmxServiceImpl extends ServiceImpl<JcdkmxMapper, Jcdkmx> implements IJcdkmxService {
    @Autowired
    private JcdkmxMapper mapper;

    @Override
    public void pJcdkmx() {
        mapper.pJcdkmx();
    }
}

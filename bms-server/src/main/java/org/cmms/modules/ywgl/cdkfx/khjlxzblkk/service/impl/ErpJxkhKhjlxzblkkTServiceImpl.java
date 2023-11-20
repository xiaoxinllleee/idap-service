package org.cmms.modules.ywgl.cdkfx.khjlxzblkk.service.impl;

import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.entity.ErpJxkhKhjlxzblkkT;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.mapper.ErpJxkhKhjlxzblkkTMapper;
import org.cmms.modules.ywgl.cdkfx.khjlxzblkk.service.IErpJxkhKhjlxzblkkTService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户经理新增不良扣款
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Service
public class ErpJxkhKhjlxzblkkTServiceImpl extends ServiceImpl<ErpJxkhKhjlxzblkkTMapper, ErpJxkhKhjlxzblkkT> implements IErpJxkhKhjlxzblkkTService {

    @Override
    public void pJxkhKhjlxzblkkTwo(String tjyf) {
        baseMapper.pJxkhKhjlxzblkkTwo(tjyf);
    }
}

package org.cmms.modules.ywgl.cdkfx.xzblkkmx.service.impl;

import org.cmms.modules.ywgl.cdkfx.xzblkkmx.entity.ErpJxkhKhjlxzblkk;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.mapper.ErpJxkhKhjlxzblkkMapper;
import org.cmms.modules.ywgl.cdkfx.xzblkkmx.service.IErpJxkhKhjlxzblkkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 新增不良扣款明细
 * @Author: jeecg-boot
 * @Date:   2021-06-24
 * @Version: V1.0
 */
@Service
public class ErpJxkhKhjlxzblkkServiceImpl extends ServiceImpl<ErpJxkhKhjlxzblkkMapper, ErpJxkhKhjlxzblkk> implements IErpJxkhKhjlxzblkkService {

    @Override
    public void pJxkhKhjlxzblkk(String tjyf, String username) {
        baseMapper.pJxkhKhjlxzblkk(tjyf, username);
    }
}

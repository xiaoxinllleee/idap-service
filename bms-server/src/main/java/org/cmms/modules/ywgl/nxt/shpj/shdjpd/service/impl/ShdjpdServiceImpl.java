package org.cmms.modules.ywgl.nxt.shpj.shdjpd.service.impl;

import org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity.Shdjpd;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.mapper.ShdjpdMapper;
import org.cmms.modules.ywgl.nxt.shpj.shdjpd.service.IShdjpdService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.Map;

/**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Service
public class ShdjpdServiceImpl extends ServiceImpl<ShdjpdMapper, Shdjpd> implements IShdjpdService {
    @Override
    public void pShdjpd(Map<String, String> sql, String username) {
        baseMapper.pShdjpd(sql, username);
    }

//    @Override
//    public void pShdjpd(String pdlx, String pdzq, String username) {
//        baseMapper.pShdjpd(pdlx, pdzq, username);
//    }
}

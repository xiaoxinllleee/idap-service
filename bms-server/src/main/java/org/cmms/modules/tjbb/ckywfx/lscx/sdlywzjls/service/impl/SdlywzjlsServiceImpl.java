package org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.service.impl;

import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.entity.Sdlywzjls;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.mapper.SdlywzjlsMapper;
import org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.service.ISdlywzjlsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 收单类业务资金流水
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Service
public class SdlywzjlsServiceImpl extends ServiceImpl<SdlywzjlsMapper, Sdlywzjls> implements ISdlywzjlsService {

    @Override
    public void pSdlywzjls(String ksrq, String jsrq) {
        baseMapper.pSdlywzjls(ksrq,jsrq);
    }
}

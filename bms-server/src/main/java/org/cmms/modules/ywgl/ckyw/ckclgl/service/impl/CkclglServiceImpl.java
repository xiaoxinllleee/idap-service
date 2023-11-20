package org.cmms.modules.ywgl.ckyw.ckclgl.service.impl;

import org.cmms.modules.ywgl.ckyw.ckclgl.entity.Ckclgl;
import org.cmms.modules.ywgl.ckyw.ckclgl.mapper.CkclglMapper;
import org.cmms.modules.ywgl.ckyw.ckclgl.service.ICkclglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款存量管理
 * @Author: jeecg-boot
 * @Date:   2021-10-09
 * @Version: V1.0
 */
@Service
public class CkclglServiceImpl extends ServiceImpl<CkclglMapper, Ckclgl> implements ICkclglService {
    @Override
    public void pCkclgl(String clnf) {
        baseMapper.pCkclgl(clnf);
    }
}

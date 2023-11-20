package org.cmms.modules.xddaglxt.dkdagl.ygmrscs.service.impl;

import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.entity.Ygmrscs;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.mapper.YgmrscsMapper;
import org.cmms.modules.xddaglxt.dkdagl.ygmrscs.service.IYgmrscsService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Service
public class YgmrscsServiceImpl extends ServiceImpl<YgmrscsMapper, Ygmrscs> implements IYgmrscsService {

    @Override
    public void pYgmrscs(String tjrqBegin, String tjrqEnd, String username) {
        baseMapper.pYgmrscs(tjrqBegin, tjrqEnd, username);
    }
}

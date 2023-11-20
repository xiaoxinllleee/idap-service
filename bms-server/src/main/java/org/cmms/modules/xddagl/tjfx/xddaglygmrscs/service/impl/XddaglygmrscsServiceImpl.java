package org.cmms.modules.xddagl.tjfx.xddaglygmrscs.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xddagl.tjfx.xddaglygmrscs.entity.Xddaglygmrscs;
import org.cmms.modules.xddagl.tjfx.xddaglygmrscs.mapper.XddaglygmrscsMapper;
import org.cmms.modules.xddagl.tjfx.xddaglygmrscs.service.IXddaglygmrscsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 员工每日上传数
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Service
public class XddaglygmrscsServiceImpl extends ServiceImpl<XddaglygmrscsMapper, Xddaglygmrscs> implements IXddaglygmrscsService {
    @Override
    public void pYgmrscs(String tjrqBegin, String tjrqEnd, String username) {
        baseMapper.pYgmrscs(tjrqBegin, tjrqEnd, username);
    }
}

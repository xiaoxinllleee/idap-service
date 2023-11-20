package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;
import org.cmms.modules.pad.nhxxgl.mapper.KhxxglGrsxlxmxNhMapper;
import org.cmms.modules.pad.nhxxgl.service.IKhxxglGrsxlxmxNhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 客户信息管理-个人授信类型明细-农户
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Service
public class KhxxglGrsxlxmxNhServiceImpl extends ServiceImpl<KhxxglGrsxlxmxNhMapper, KhxxglGrsxlxmxNh> implements IKhxxglGrsxlxmxNhService {

    @Autowired
    private KhxxglGrsxlxmxNhMapper khxxglGrsxlxmxNhMapper;

    @Override
    public int syncYesNHGrsxlxmx() {
        return khxxglGrsxlxmxNhMapper.syncYesNHGrsxlxmx();
    }

    @Override
    public int delNhgrsxlxmxByHhbm(String hhbm) {
        return khxxglGrsxlxmxNhMapper.delNhgrsxlxmxByHhbm(hhbm);
    }

    @Override
    public int delNhgrsxlxmx() {
        return khxxglGrsxlxmxNhMapper.delNhgrsxlxmx();
    }
}

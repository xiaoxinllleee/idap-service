package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.pad.nhxxgl.entity.KhxxglGrsxlxmxNh;
import org.cmms.modules.pad.nhxxgl.mapper.KhxxglGrsxlxmxNhMapper;
import org.cmms.modules.pad.nhxxgl.service.IKhxxglGrsxlxmxNh1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 龚辉
 * @date 2023/6/17 17:07 周六
 */
@Service
public class KhxxglGrsxlxmxNh1ServiceImpl extends ServiceImpl<KhxxglGrsxlxmxNhMapper, KhxxglGrsxlxmxNh> implements IKhxxglGrsxlxmxNh1Service {
    @Autowired
    private KhxxglGrsxlxmxNhMapper khxxglGrsxlxmxNhMapper;

    @Override
    public int delNhgrsxlxmx() {
        return khxxglGrsxlxmxNhMapper.delNhgrsxlxmx();
    }
}

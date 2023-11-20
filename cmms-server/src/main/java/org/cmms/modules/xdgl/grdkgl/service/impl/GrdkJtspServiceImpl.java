package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.entity.GrdkJtsp;
import org.cmms.modules.xdgl.grdkgl.mapper.GrdkJtspMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkJtspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 集体审批书
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Service
public class GrdkJtspServiceImpl extends ServiceImpl<GrdkJtspMapper, GrdkJtsp> implements IGrdkJtspService {

    @Autowired
    private GrdkJtspMapper grdkJtspMapper;

    @Override
    public int isComplete(String id) {
        GrdkJtsp grdkJtsp = baseMapper.selectById(id);
        if (grdkJtsp != null && grdkJtsp.getJtspzzzt() != null && "1".equals(grdkJtsp.getJtspzzzt()))
            return 1;
        return 0;
    }

    @Override
    public Integer updById(String id) {
        return grdkJtspMapper.updById(id);
    }

    @Override
    public List<GrdkDbxx> getDbxx(String zjhm) {
        return grdkJtspMapper.getDbxx(zjhm);
    }
}

package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Grdkspjl;
import org.cmms.modules.xdgl.grdkgl.mapper.GrdkspjlMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkspjlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 个人贷款审批结果
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Service
public class GrdkspjlServiceImpl extends ServiceImpl<GrdkspjlMapper, Grdkspjl> implements IGrdkspjlService {

    @Override
    public void extractJtspxx(String id, String zjhm) {
        getBaseMapper().extractJtspxx(id,zjhm);
    }
}

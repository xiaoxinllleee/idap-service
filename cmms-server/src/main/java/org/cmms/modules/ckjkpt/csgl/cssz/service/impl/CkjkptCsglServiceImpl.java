package org.cmms.modules.ckjkpt.csgl.cssz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ckjkpt.csgl.cssz.entity.CkjkptCsgl;
import org.cmms.modules.ckjkpt.csgl.cssz.mapper.CkjkptCsglMapper;
import org.cmms.modules.ckjkpt.csgl.cssz.service.ICkjkptCsglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
@Service
public class CkjkptCsglServiceImpl extends ServiceImpl<CkjkptCsglMapper, CkjkptCsgl> implements ICkjkptCsglService {

    @Autowired
    private CkjkptCsglMapper mapper;

    @Override
    public String queryParamValue(String csbm) {
        return mapper.queryParamValue(csbm);
    }
}

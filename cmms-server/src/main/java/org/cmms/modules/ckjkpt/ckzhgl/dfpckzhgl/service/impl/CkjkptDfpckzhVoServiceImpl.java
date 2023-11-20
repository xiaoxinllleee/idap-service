package org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.entity.CkjkptDfpckzhVo;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.mapper.CkjkptDfpckzhVoMapper;
import org.cmms.modules.ckjkpt.ckzhgl.dfpckzhgl.service.ICkjkptDfpckzhVoService;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 待分配存款帐号管理
 * @Author: jeecg-boot
 * @Date:   2021-07-13
 * @Version: V1.0
 */
@Service
public class CkjkptDfpckzhVoServiceImpl extends ServiceImpl<CkjkptDfpckzhVoMapper, CkjkptDfpckzhVo> implements ICkjkptDfpckzhVoService {

    @Override
    public void delBykhzh(String khzh) {
        baseMapper.delBykhzh(khzh);
    }
}

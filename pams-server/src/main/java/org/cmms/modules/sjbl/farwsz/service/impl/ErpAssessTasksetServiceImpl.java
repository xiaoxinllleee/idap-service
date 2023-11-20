package org.cmms.modules.sjbl.farwsz.service.impl;

import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.sjbl.farwsz.entity.ErpAssessTaskset;
import org.cmms.modules.sjbl.farwsz.mapper.ErpAssessTasksetMapper;
import org.cmms.modules.sjbl.farwsz.service.IErpAssessTasksetService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 方案任务设置
 * @Author: jeecg-boot
 * @Date:   2023-03-22
 * @Version: V1.0
 */
@Service
public class ErpAssessTasksetServiceImpl extends ServiceImpl<ErpAssessTasksetMapper, ErpAssessTaskset> implements IErpAssessTasksetService {

    @Override
    public List<ErpBasSjxArea> tasksetJg(String zbid) {
        return baseMapper.tasksetJg(zbid);
    }

    @Override
    public List<ErpBasSjxArea> tasksetGw(String zbid) {
        return baseMapper.tasksetGw(zbid);
    }
}

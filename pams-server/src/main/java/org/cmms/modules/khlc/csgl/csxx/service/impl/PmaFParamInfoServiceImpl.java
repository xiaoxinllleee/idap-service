package org.cmms.modules.khlc.csgl.csxx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khlc.csgl.csxx.entity.PmaFParamInfo;
import org.cmms.modules.khlc.csgl.csxx.mapper.PmaFParamInfoMapper;
import org.cmms.modules.khlc.csgl.csxx.service.IPmaFParamInfoService;
import org.cmms.modules.khlc.khfagl.entity.PmaAScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 参数信息
 * @Author: jeecg-boot
 * @Date:   2021-03-23
 * @Version: V1.0
 */
@Service

public class PmaFParamInfoServiceImpl extends ServiceImpl<PmaFParamInfoMapper, PmaFParamInfo> implements IPmaFParamInfoService {
    @Autowired
    PmaFParamInfoMapper pmaFParamInfoMapper;

    @Override
    public IPage<PmaFParamInfo> getParamInfoByJdId(Page<PmaFParamInfo> page, String jdid, String csmc, String csbh) {
        return baseMapper.getParamInfoByJdId(page, jdid,csmc ,csbh);

    }

    @Override
    public int deleteByParamIdAndArea(String paramId, String area) {
        UpdateWrapper queryWrapper = new UpdateWrapper();
        queryWrapper.eq("PARAM_ID",paramId);
        queryWrapper.eq("AREA","1");
        return pmaFParamInfoMapper.delete(queryWrapper);
    }
}

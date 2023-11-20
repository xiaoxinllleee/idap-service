package org.cmms.modules.khlc.khfagl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import org.cmms.modules.khlc.khfagl.mapper.ErpAssessAljcMapper;
import org.cmms.modules.khlc.khfagl.service.IErpAssessAljcService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 按量计酬考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Service
public class ErpAssessAljcServiceImpl extends ServiceImpl<ErpAssessAljcMapper, ErpAssessAljc> implements IErpAssessAljcService {

    @Override
    public List<HrPostOrg> khdx() {
        return baseMapper.khdx();
    }

    @Override
    public List<ErpBasSjxArea> area( String zbid) {
        return baseMapper.area( zbid);
    }

    @Override
    public List<ErpBasSjxArea> areaJg(String zbid) {
        return baseMapper.areaJg(zbid);
    }

    @Override
    public List<ErpAssessAljc> getZbxxBySchemeId(String schemeId) {
        return baseMapper.getZbxxBySchemeId(schemeId);
    }
}

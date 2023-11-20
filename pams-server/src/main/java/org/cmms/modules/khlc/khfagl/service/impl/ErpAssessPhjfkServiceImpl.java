package org.cmms.modules.khlc.khfagl.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessPhjfk;
import org.cmms.modules.khlc.khfagl.mapper.ErpAssessPhjfkMapper;
import org.cmms.modules.khlc.khfagl.service.IErpAssessPhjfkService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 平衡积分卡考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Service
public class ErpAssessPhjfkServiceImpl extends ServiceImpl<ErpAssessPhjfkMapper, ErpAssessPhjfk> implements IErpAssessPhjfkService {

    @Override
    public List<ErpBasSjxArea> phjfkGw(String zbid) {
        return baseMapper.phjfkGw(zbid);
    }

    @Override
    public List<ErpBasSjxArea> phjfkJg(String zbid) {
        return baseMapper.phjfkJg(zbid);
    }

    @Override
    public List<ErpAssessPhjfk> getZbxxBySchemeId(String schemeId) {
        return baseMapper.getZbxxBySchemeId(schemeId);
    }
}

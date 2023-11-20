package org.cmms.modules.dklldj.csszgl.csgl.service.impl;

import org.cmms.modules.dklldj.csszgl.csgl.entity.Csszxx;
import org.cmms.modules.dklldj.csszgl.csgl.mapper.CsszxxMapper;
import org.cmms.modules.dklldj.csszgl.csgl.service.ICsszxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 参数管理
 * @Author: jeecg-boot
 * @Date:   2020-03-06
 * @Version: V1.0
 */
@Service
public class CsszxxServiceImpl extends ServiceImpl<CsszxxMapper, Csszxx> implements ICsszxxService {


    @Override
    public void deleteByCsid(String csid) {
        baseMapper.deleteByCsid(csid);
    }
}

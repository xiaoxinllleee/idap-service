package org.cmms.modules.report.bbgl.bbmbgl.service.impl;

import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.entity.BbmbglVo;
import org.cmms.modules.report.bbgl.bbmbgl.mapper.BbmbglMapper;
import org.cmms.modules.report.bbgl.bbmbgl.service.IBbmbglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
@Service
public class BbmbglServiceImpl extends ServiceImpl<BbmbglMapper, Bbmbgl> implements IBbmbglService {

    @Override
    public List<BbmbglVo> getBbmbsjList(String bbyf, BbmbglVo bbmbglVo) {
        return baseMapper.getBbmbsjList(bbyf, bbmbglVo);
    }
}

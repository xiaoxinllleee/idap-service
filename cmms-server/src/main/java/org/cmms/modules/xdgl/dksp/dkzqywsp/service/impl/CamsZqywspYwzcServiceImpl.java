package org.cmms.modules.xdgl.dksp.dkzqywsp.service.impl;


import org.cmms.modules.xdgl.dksp.dkzqywsp.entity.CamsZqywspYwzc;
import org.cmms.modules.xdgl.dksp.dkzqywsp.mapper.CamsZqywspYwzcMapper;
import org.cmms.modules.xdgl.dksp.dkzqywsp.service.ICamsZqywspYwzcService;
import org.cmms.modules.xdgl.dksp.dkzqywsp.vo.DkxxVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 贷款展期业务审批注册表
 * @Author: jeecg-boot
 * @Date:   2023-10-10
 * @Version: V1.0
 */
@Service
public class CamsZqywspYwzcServiceImpl extends ServiceImpl<CamsZqywspYwzcMapper, CamsZqywspYwzc> implements ICamsZqywspYwzcService {

    @Override
    public List<DkxxVo> getDkxx(String khmc, String dkzh,String zjhm) {
        return baseMapper.getDkxx(khmc,dkzh,zjhm);
    }
}

package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.Grdkcjxx;
import org.cmms.modules.xdgl.grdkgl.mapper.GrdkcjxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkcjxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;

/**
 * @Description: 个人贷款采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-17
 * @Version: V1.0
 */
@Service
public class GrdkcjxxServiceImpl extends ServiceImpl<GrdkcjxxMapper, Grdkcjxx> implements IGrdkcjxxService {
    public void updateSxed(String pddj, BigDecimal zzsxed, String yj, String zjhm){
        baseMapper.updateSxed(pddj,zzsxed,yj,zjhm);
    };
    @Override
    public void updateSpjled( String pddj, BigDecimal zzsxed, String yj, String spid) {
        baseMapper.updateSpjled(pddj,zzsxed,yj,spid);
    }
}

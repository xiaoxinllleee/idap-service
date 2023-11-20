package org.cmms.modules.xdgl.grkhpjsx.service.impl;

import org.cmms.modules.xdgl.grkhpjsx.entity.Grpjsxspjl;
import org.cmms.modules.xdgl.grkhpjsx.mapper.GrpjsxspjlMapper;
import org.cmms.modules.xdgl.grkhpjsx.service.IGrpjsxspjlService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人客户评级授信记录
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
@Service
public class GrpjsxspjlServiceImpl extends ServiceImpl<GrpjsxspjlMapper, Grpjsxspjl> implements IGrpjsxspjlService {
    @Override
    public void deleteByspid(String spid) {
        baseMapper.deleteByspid(spid);
    }

    @Override
    public void deleteGrdkByspid(String spid) {
        baseMapper.deleteGrdkByspid(spid);
    }

    @Override
    public List<Grpjsxspjl> getDzdkzData() {
        return baseMapper.getDzdkzData();
    }
}

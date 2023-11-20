package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.common.api.vo.Result;
import org.cmms.modules.xdgl.grdkgl.entity.Vgrdkspjl;
import org.cmms.modules.xdgl.grdkgl.mapper.VgrdkspjlMapper;
import org.cmms.modules.xdgl.grdkgl.service.IVgrdkspjlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人贷款审批记录
 * @Author: jeecg-boot
 * @Date:   2020-08-22
 * @Version: V1.0
 */
@Service
public class VgrdkspjlServiceImpl extends ServiceImpl<VgrdkspjlMapper, Vgrdkspjl> implements IVgrdkspjlService {

    @Autowired
    private VgrdkspjlMapper vgrdkspjlMapper;

    @Override
    public List<Vgrdkspjl> queryDksp(String userId) {
        return vgrdkspjlMapper.queryDksp(userId);
    }

    @Override
    public List<Vgrdkspjl> queryByZjhm(String userId, String zjhm,String khmc) {
        return vgrdkspjlMapper.queryByZjhm(userId,zjhm,khmc);
    }

    @Override
    public List<Vgrdkspjl> selectByZjhm(String userId, String zjhm) {
        return vgrdkspjlMapper.selectByZjhm(userId,zjhm);
    }

    @Override
    public List<Vgrdkspjl> selectByKhmc(String userId, String khmc) {
        return vgrdkspjlMapper.selectByKhmc(userId,khmc);
    }
}

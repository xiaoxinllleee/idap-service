package org.cmms.modules.lydp.zbgl.lydPzbsjx.service.impl;

import org.cmms.modules.lydp.zbgl.lydPzbsjx.entity.LydpZbsjx;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.mapper.LydpZbsjxMapper;
import org.cmms.modules.lydp.zbgl.lydPzbsjx.service.ILydpZbsjxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 浏阳大屏指标数据项
 * @Author: jeecg-boot
 * @Date:   2023-02-15
 * @Version: V1.0
 */
@Service
public class LydpZbsjxServiceImpl extends ServiceImpl<LydpZbsjxMapper, LydpZbsjx> implements ILydpZbsjxService {

    @Override
    public List<LydpZbsjx> getListByQydm(String qydm,  String zblx, String zbwd, String zbid) {
        return baseMapper.getListByQydm(qydm,zblx, zbwd,zbid);
    }

    @Override
    public List<LydpZbsjx> getListByQydm(String qydm, String zblx) {
        return baseMapper.getList(qydm, zblx);
    }
}

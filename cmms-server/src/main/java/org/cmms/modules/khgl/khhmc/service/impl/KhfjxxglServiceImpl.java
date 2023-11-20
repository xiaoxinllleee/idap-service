package org.cmms.modules.khgl.khhmc.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khhmc.entity.Khfjxxgl;
import org.cmms.modules.khgl.khhmc.entity.KhfjxxglExp;
import org.cmms.modules.khgl.khhmc.mapper.KhfjxxglMapper;
import org.cmms.modules.khgl.khhmc.service.IKhfjxxglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户附加信息管理
 * @Author: jeecg-boot
 * @Date:   2020-03-27
 * @Version: V1.0
 */
@Service
public class KhfjxxglServiceImpl extends ServiceImpl<KhfjxxglMapper, Khfjxxgl> implements IKhfjxxglService {

    public void updateKhhmc() {
        baseMapper.updateKhhmc();
    }

    public void updateywwl() {
        baseMapper.updateywwl();
    }

    @Override
    public void updateHzxx() {
        baseMapper.updateHzxx();
    }

    @Override
    public IPage<Khfjxxgl> getByWgbh(Page page, String wgbh, String type) {
        return baseMapper.getByWgbh(page, wgbh, type);
    }

    @Override
    public List<KhfjxxglExp> getFjxxByWgbh(String wgbh,String yggh) {
        return baseMapper.getFjxxByWgbh(wgbh,yggh);
    }
}

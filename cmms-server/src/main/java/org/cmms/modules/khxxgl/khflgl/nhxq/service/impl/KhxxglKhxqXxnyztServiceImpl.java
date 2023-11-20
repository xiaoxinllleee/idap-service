package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.AntSelectOptions;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.KhxxglKhxqXxnyzt;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztTjfx2;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.XxnyztYxzfVo;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.KhxxglKhxqXxnyztMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IKhxxglKhxqXxnyztService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2022-12-03
 * @Version: V1.0
 */
@Service
public class KhxxglKhxqXxnyztServiceImpl extends ServiceImpl<KhxxglKhxqXxnyztMapper, KhxxglKhxqXxnyzt> implements IKhxxglKhxqXxnyztService {

    @Override
    public IPage<XxnyztTjfx2> getTjfx2(Page page, KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        return baseMapper.getTjfx2(page, khxxglKhxqXxnyzt);
    }

    @Override
    public List<XxnyztTjfx2> getTjfx2(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        return baseMapper.getTjfx2exp(khxxglKhxqXxnyzt);
    }

    @Override
    public List<AntSelectOptions> getAllGhzrr() {
        return baseMapper.getAllGhzrr();
    }

    @Override
    public void xxnyztYxzfInit(String id, String yggh) {
        baseMapper.xxnyztYxzfInit(id,yggh);
    }

    @Override
    public List<XxnyztYxzfVo> getZfzbxxByXxnyztId(String id) {
        return baseMapper.getZfzbxxByXxnyztId(id);
    }

    @Override
    public IPage<XxnyztTjfx2> getTjfx2Qy(Page page, KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        return baseMapper.getTjfx2Qy(page,khxxglKhxqXxnyzt);
    }

    @Override
    public List<XxnyztTjfx2> getTjfx2Qy(KhxxglKhxqXxnyzt khxxglKhxqXxnyzt) {
        return baseMapper.getTjfx2expQy(khxxglKhxqXxnyzt);
    }
}

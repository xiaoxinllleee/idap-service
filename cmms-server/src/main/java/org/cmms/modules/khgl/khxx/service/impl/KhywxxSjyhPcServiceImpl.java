package org.cmms.modules.khgl.khxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khgl.khxx.mapper.KhywxxSjyhPcMapper;
import org.cmms.modules.khgl.khxx.service.IKhywxxSjyhPcService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Service
public class KhywxxSjyhPcServiceImpl extends ServiceImpl<KhywxxSjyhPcMapper, KhywxxSjyhPc> implements IKhywxxSjyhPcService {

    @Override
    public IPage<KhywxxSjyhPc> getByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbh(page,wgbhList);
    }

    @Override
    public IPage<Nhxq> getWktByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(page,wgbhList);
    }

    @Override
    public List<KhywxxSjyhPc> getByWgbhList(List<String> wgbhList) {
        return baseMapper.getByWgbhList(wgbhList);
    }

    @Override
    public List<Nhxq> getWktByWgbhList(List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(wgbhList);
    }
}

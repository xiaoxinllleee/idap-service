package org.cmms.modules.khgl.khxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khxx.entity.KhywxxSjyhPc;
import org.cmms.modules.khgl.khxx.entity.KhywxxWsyhPc;
import org.cmms.modules.khgl.khxx.mapper.KhywxxWsyhPcMapper;
import org.cmms.modules.khgl.khxx.service.IKhywxxWsyhPcService;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 网上银行
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Service
public class KhywxxWsyhPcServiceImpl extends ServiceImpl<KhywxxWsyhPcMapper, KhywxxWsyhPc> implements IKhywxxWsyhPcService {

    @Override
    public IPage<KhywxxWsyhPc> getByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbh(page,wgbhList);
    }

    @Override
    public IPage<Nhxq> getWktByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(page,wgbhList);
    }


    @Override
    public List<KhywxxWsyhPc> getByWgbhList(List<String> wgbhList) {
        return baseMapper.getByWgbhList(wgbhList);
    }

    @Override
    public List<Nhxq> getWktByWgbhList(List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(wgbhList);
    }
}

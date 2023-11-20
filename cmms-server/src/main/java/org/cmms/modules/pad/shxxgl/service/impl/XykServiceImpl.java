package org.cmms.modules.pad.shxxgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khxxgl.khflgl.nhxq.entity.Nhxq;
import org.cmms.modules.pad.shxxgl.entity.Xyk;
import org.cmms.modules.pad.shxxgl.entity.XykVO;
import org.cmms.modules.pad.shxxgl.mapper.XykMapper;
import org.cmms.modules.pad.shxxgl.service.IXykService;
import org.cmms.modules.sjxf.qtxt.djkxt.kpzlb.entity.Kpzlb;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 信用卡
 * @Author: jeecg-boot
 * @Date:   2020-11-03
 * @Version: V1.0
 */
@Service
public class XykServiceImpl extends ServiceImpl<XykMapper, Xyk> implements IXykService {

    @Override
    public IPage<XykVO> getByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbh(page,wgbhList);
    }

    @Override
    public IPage<Nhxq> getWktByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(page,wgbhList);
    }


    @Override
    public List<XykVO> getByWgbhList(List<String> wgbhList) {
        return baseMapper.getByWgbhList(wgbhList);
    }

    @Override
    public List<Nhxq> getWktByWgbhList(List<String> wgbhList) {
        return baseMapper.getWktByWgbhList(wgbhList);
    }
}

package org.cmms.modules.khgl.khxx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.khxx.entity.KhywxxEtcPc;
import org.cmms.modules.khgl.khxx.mapper.KhywxxEtcPcMapper;
import org.cmms.modules.khgl.khxx.service.IKhywxxEtcPcService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: ETC
 * @Author: jeecg-boot
 * @Date:   2020-11-27
 * @Version: V1.0
 */
@Service
public class KhywxxEtcPcServiceImpl extends ServiceImpl<KhywxxEtcPcMapper, KhywxxEtcPc> implements IKhywxxEtcPcService {

    @Override
    public IPage<KhywxxEtcPc> getByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbh(page,wgbhList);
    }

    @Override
    public List<KhywxxEtcPc> getByWgbh(List<String> wgbhList) {
        return baseMapper.getByWgbhList(wgbhList);
    }
}

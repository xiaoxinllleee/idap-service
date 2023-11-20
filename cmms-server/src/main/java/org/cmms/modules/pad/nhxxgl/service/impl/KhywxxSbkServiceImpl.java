package org.cmms.modules.pad.nhxxgl.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.pad.nhxxgl.entity.KhywxxSbk;
import org.cmms.modules.pad.nhxxgl.mapper.KhywxxSbkMapper;
import org.cmms.modules.pad.nhxxgl.service.IKhywxxSbkService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 社保卡业务信息
 * @Author: jeecg-boot
 * @Date:   2021-07-23
 * @Version: V1.0
 */
@Service
public class KhywxxSbkServiceImpl extends ServiceImpl<KhywxxSbkMapper, KhywxxSbk> implements IKhywxxSbkService {

    @Override
    public IPage<KhywxxSbk> getByWgbh(Page page, List<String> wgbhList) {
        return baseMapper.getByWgbh(page, wgbhList);
    }
}

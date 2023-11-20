package org.cmms.modules.khxxgl.khflgl.nhxq.service.impl;

import org.cmms.modules.khxxgl.khflgl.nhxq.entity.VKhxxglKhxqXxnyzt;
import org.cmms.modules.khxxgl.khflgl.nhxq.mapper.VKhxxglKhxqXxnyztMapper;
import org.cmms.modules.khxxgl.khflgl.nhxq.service.IVKhxxglKhxqXxnyztService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 新型农业主体
 * @Author: jeecg-boot
 * @Date:   2023-10-13
 * @Version: V1.0
 */
@Service
public class VKhxxglKhxqXxnyztServiceImpl extends ServiceImpl<VKhxxglKhxqXxnyztMapper, VKhxxglKhxqXxnyzt> implements IVKhxxglKhxqXxnyztService {

    @Override
    public void initData(String sjrq) {
        baseMapper.initData(sjrq);
    }

    @Override
    public Date getMaxDate() {
        return baseMapper.getMaxDate();
    }
}

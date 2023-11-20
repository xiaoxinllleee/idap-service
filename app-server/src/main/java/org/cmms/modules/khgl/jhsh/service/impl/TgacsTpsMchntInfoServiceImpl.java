package org.cmms.modules.khgl.jhsh.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.jhsh.entity.TgacsTpsMchntInfo;
import org.cmms.modules.khgl.jhsh.mapper.TgacsTpsMchntInfoMapper;
import org.cmms.modules.khgl.jhsh.service.ITgacsTpsMchntInfoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 聚合商户信息
 * @Author: jeecg-boot
 * @Date:   2022-03-09
 * @Version: V1.0
 */
@Service
public class TgacsTpsMchntInfoServiceImpl extends ServiceImpl<TgacsTpsMchntInfoMapper, TgacsTpsMchntInfo> implements ITgacsTpsMchntInfoService {

    @Override
    public IPage<TgacsTpsMchntInfo> getxj(Page page, String ks, String js) {
        return baseMapper.getxj(page, ks, js);
    }
}

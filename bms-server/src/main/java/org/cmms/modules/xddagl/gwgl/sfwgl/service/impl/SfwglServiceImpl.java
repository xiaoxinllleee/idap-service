package org.cmms.modules.xddagl.gwgl.sfwgl.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.xddagl.gwgl.sfwgl.entity.Sfwgl;
import org.cmms.modules.xddagl.gwgl.sfwgl.mapper.SfwglMapper;
import org.cmms.modules.xddagl.gwgl.sfwgl.service.ISfwglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 收发文管理
 * @Author: jeecg-boot
 * @Date:   2022-01-08
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class SfwglServiceImpl extends ServiceImpl<SfwglMapper, Sfwgl> implements ISfwglService {

    @Override
    public IPage<Sfwgl> getQuery(Page<Sfwgl> page, Sfwgl sfwgl, String zzbz) {
        return baseMapper.getQuery(page,sfwgl,zzbz);
    }
}

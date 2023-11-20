package org.cmms.modules.khgl.dkkh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.dkkh.entity.TbDkYgdkzhsjmx;
import org.cmms.modules.khgl.dkkh.mapper.TbDkYgdkzhsjmxMapper;
import org.cmms.modules.khgl.dkkh.service.ITbDkYgdkzhsjmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 贷款员工管理综合数据明细
 * @Author: jeecg-boot
 * @Date:   2022-03-11
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class TbDkYgdkzhsjmxServiceImpl extends ServiceImpl<TbDkYgdkzhsjmxMapper, TbDkYgdkzhsjmx> implements ITbDkYgdkzhsjmxService {

    @Override
    public IPage<TbDkYgdkzhsjmx> getAppList(Page page, TbDkYgdkzhsjmx tbDkYgdkzhsjmx,String yggh) {
        return baseMapper.getAppList(page,tbDkYgdkzhsjmx,yggh);
    }

    @Override
    public String dbfs(String hth) {
        return baseMapper.dbfs(hth);
    }
}

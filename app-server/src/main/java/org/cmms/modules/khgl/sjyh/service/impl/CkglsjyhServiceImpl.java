package org.cmms.modules.khgl.sjyh.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.khgl.sjyh.entity.Ckglsjyh;
import org.cmms.modules.khgl.sjyh.entity.KhzlbVo;
import org.cmms.modules.khgl.sjyh.mapper.CkglsjyhMapper;
import org.cmms.modules.khgl.sjyh.service.ICkglsjyhService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户管理_手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-16
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class CkglsjyhServiceImpl extends ServiceImpl<CkglsjyhMapper, Ckglsjyh> implements ICkglsjyhService {
    @Override
    public List<KhzlbVo> getList(int start, int end, int jx, int px, String ssmc) {
        return baseMapper.getList(start, end, jx, px,ssmc);

    }

    @Override
    public IPage<KhzlbVo> getPageList(Page page, int jx, int px, String ssmc) {
        return baseMapper.getPageList(page,jx,px,ssmc);
    }
}

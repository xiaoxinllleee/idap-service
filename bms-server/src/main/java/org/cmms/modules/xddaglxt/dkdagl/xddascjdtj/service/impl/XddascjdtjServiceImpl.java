package org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.service.impl;

import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.entity.Xddascjdtj;
import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.mapper.XddascjdtjMapper;
import org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.service.IXddascjdtjService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 信贷档案上传进度统计
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Service
public class XddascjdtjServiceImpl extends ServiceImpl<XddascjdtjMapper, Xddascjdtj> implements IXddascjdtjService {

    @Override
    public void pXddascjdtj() {
        baseMapper.pXddascjdtj();
    }
}

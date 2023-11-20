package org.cmms.modules.jx.ckyx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxx;
import org.cmms.modules.jx.ckyx.entity.TbTjfxCktzmxxxVO;
import org.cmms.modules.jx.ckyx.mapper.TbTjfxCktzmxxxMapper;
import org.cmms.modules.jx.ckyx.service.ITbTjfxCktzmxxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存款拓展明细
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Service
public class TbTjfxCktzmxxxServiceImpl extends ServiceImpl<TbTjfxCktzmxxxMapper, TbTjfxCktzmxxx> implements ITbTjfxCktzmxxxService {

    @Override
    public IPage<TbTjfxCktzmxxxVO> getListByPage(Page page, String yggh) {
        return baseMapper.getListByPage(page,yggh);
    }
}

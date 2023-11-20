package org.cmms.modules.khgl.etckh.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.etckh.entity.BdxxbVO;
import org.cmms.modules.khgl.etckh.entity.Etckh;
import org.cmms.modules.khgl.etckh.entity.SbxxVO;
import org.cmms.modules.khgl.etckh.mapper.EtckhMapper;
import org.cmms.modules.khgl.etckh.service.IEtckhService;
import org.springframework.stereotype.Service;

/**
 * @Description: ETC绑定信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Service

public class EtckhServiceImpl extends ServiceImpl<EtckhMapper, Etckh> implements IEtckhService {


    @Override
    public IPage<BdxxbVO> getKhxxList(Page page, String username, String khmc) {
        return baseMapper.getKhxxList(page,username,khmc);
    }

    @Override
    public IPage<SbxxVO> getSbxxList(Page page, String zjhm) {
        return baseMapper.getSbxxList(page,zjhm);
    }
}

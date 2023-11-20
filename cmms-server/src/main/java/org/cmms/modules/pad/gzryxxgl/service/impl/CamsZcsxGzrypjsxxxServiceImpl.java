package org.cmms.modules.pad.gzryxxgl.service.impl;

import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzrypjsxxx;
import org.cmms.modules.pad.gzryxxgl.mapper.CamsZcsxGzrypjsxxxMapper;
import org.cmms.modules.pad.gzryxxgl.service.ICamsZcsxGzrypjsxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 公职人员评级授信表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
@Service
public class CamsZcsxGzrypjsxxxServiceImpl extends ServiceImpl<CamsZcsxGzrypjsxxxMapper, CamsZcsxGzrypjsxxx> implements ICamsZcsxGzrypjsxxxService {

    @Autowired
    private CamsZcsxGzrypjsxxxMapper camsZcsxGzrypjsxxxMapper;

    @Override
    public CamsZcsxGzrypjsxxx getByGzryid(String gzryid) {
        return camsZcsxGzrypjsxxxMapper.getByGzryid(gzryid);
    }
}

package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.RateCssz;
import org.cmms.modules.xdgl.grdkgl.mapper.RateCsszMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateCsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: Rate_cssz
 * @Author: jeecg-boot
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@Service
public class RateCsszServiceImpl extends ServiceImpl<RateCsszMapper, RateCssz> implements IRateCsszService {

    @Autowired
    RateCsszMapper rateCsszMapper;

    @Override
    public List<RateCssz> querycssz (){
        return rateCsszMapper.querycssz();
    }

}

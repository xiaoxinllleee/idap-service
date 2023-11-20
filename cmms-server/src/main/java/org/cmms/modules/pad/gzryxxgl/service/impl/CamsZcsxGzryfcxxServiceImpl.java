package org.cmms.modules.pad.gzryxxgl.service.impl;

import org.cmms.modules.pad.gzryxxgl.entity.CamsZcsxGzryfcxx;
import org.cmms.modules.pad.gzryxxgl.mapper.CamsZcsxGzryfcxxMapper;
import org.cmms.modules.pad.gzryxxgl.service.ICamsZcsxGzryfcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 公职人员房产信息表
 * @Author: jeecg-boot
 * @Date:   2022-09-05
 * @Version: V1.0
 */
@Service
public class CamsZcsxGzryfcxxServiceImpl extends ServiceImpl<CamsZcsxGzryfcxxMapper, CamsZcsxGzryfcxx> implements ICamsZcsxGzryfcxxService {

    @Autowired
    private CamsZcsxGzryfcxxMapper camsZcsxGzryfcxxMapper;

    @Override
    public List<CamsZcsxGzryfcxx> getByGzryid(String gzryid) {
        return camsZcsxGzryfcxxMapper.getByGzryid(gzryid);
    }
}

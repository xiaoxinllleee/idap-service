package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkDbxx;
import org.cmms.modules.xdgl.grdkgl.mapper.GrdkDbxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkDbxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人贷款担保信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
@Service
public class GrdkDbxxServiceImpl extends ServiceImpl<GrdkDbxxMapper, GrdkDbxx> implements IGrdkDbxxService {

    @Override
    public List<GrdkDbxx> selectByMainId(String mainId) {
        return baseMapper.selectByMainId(mainId);
    }
}

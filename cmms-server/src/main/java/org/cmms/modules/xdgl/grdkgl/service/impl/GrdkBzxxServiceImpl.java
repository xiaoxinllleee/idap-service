package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.GrdkBzxx;
import org.cmms.modules.xdgl.grdkgl.mapper.GrdkBzxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IGrdkBzxxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 个人贷款保证信息
 * @Author: jeecg-boot
 * @Date:   2020-09-11
 * @Version: V1.0
 */
@Service
public class GrdkBzxxServiceImpl extends ServiceImpl<GrdkBzxxMapper, GrdkBzxx> implements IGrdkBzxxService {

    @Override
    public List<GrdkBzxx> selectByMainId(String mainId) {
        return baseMapper.selectByMainId(mainId);
    }
}

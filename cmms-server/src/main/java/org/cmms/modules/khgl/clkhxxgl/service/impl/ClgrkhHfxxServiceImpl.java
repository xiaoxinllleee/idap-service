package org.cmms.modules.khgl.clkhxxgl.service.impl;


import org.cmms.modules.khgl.clkhxxgl.entity.ClgrkhHfxx;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClgrkhHfxxMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClgrkhHfxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户回访信息表
 * @Author: jeecg-boot
 * @Date:   2020-02-28
 * @Version: V1.0
 */
@Service
public class ClgrkhHfxxServiceImpl extends ServiceImpl<ClgrkhHfxxMapper, ClgrkhHfxx> implements IClgrkhHfxxService {

    @Override
    public List<ClgrkhHfxx> queryHfxxByZjhm(String zjhm) {
        return baseMapper.queryHfxxByZjhm(zjhm);
    }

    @Override
    public List<String> queryTodayHfxx(String yggh) {

        return baseMapper.queryTodayHfxx(yggh);
    }
}

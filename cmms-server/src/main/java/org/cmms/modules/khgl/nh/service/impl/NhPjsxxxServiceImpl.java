package org.cmms.modules.khgl.nh.service.impl;

import org.cmms.modules.khgl.nh.entity.NhPjsxxx;
import org.cmms.modules.khgl.nh.mapper.NhPjsxxxMapper;
import org.cmms.modules.khgl.nh.service.INhPjsxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Service
public class NhPjsxxxServiceImpl extends ServiceImpl<NhPjsxxxMapper, NhPjsxxx> implements INhPjsxxxService {

    @Autowired
    NhPjsxxxMapper nhPjsxxxMapper;

    @Override
    public List<NhPjsxxx> selectByMainId(String zjhm){
        return nhPjsxxxMapper.selectByMainId(zjhm);
    }

}

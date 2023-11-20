package org.cmms.modules.khgl.nh.service.impl;

import org.cmms.modules.khgl.nh.entity.Nhfcxx;
import org.cmms.modules.khgl.nh.mapper.NhfcxxMapper;
import org.cmms.modules.khgl.nh.service.INhfcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 农户房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Service
public class NhfcxxServiceImpl extends ServiceImpl<NhfcxxMapper, Nhfcxx> implements INhfcxxService {

    @Autowired
    NhfcxxMapper nhfcxxMapper;

    @Override
    public List<Nhfcxx> selectByMainId(String zjhm){
        return nhfcxxMapper.selectByMainId(zjhm);
    }

    @Override
    public Nhfcxx selectFcjz(String hhbm){
        return nhfcxxMapper.selectFcjz(hhbm);
    }


}

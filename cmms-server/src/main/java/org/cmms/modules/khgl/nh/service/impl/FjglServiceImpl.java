package org.cmms.modules.khgl.nh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.nh.entity.Fjgl;
import org.cmms.modules.khgl.nh.mapper.FjglMapper;
import org.cmms.modules.khgl.nh.service.IFjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-11-30
 * @Version: V1.0
 */
@Service
public class FjglServiceImpl extends ServiceImpl<FjglMapper, Fjgl> implements IFjglService {


    @Autowired
    FjglMapper fjglMapper;


    @Override
    public List<Fjgl> selectByMainId(String zjhm){
        return fjglMapper.selectByMainId(zjhm);
    }

    @Override
    public boolean deleteImg(String zjhm,String zllx){
        return fjglMapper.deleteImg(zjhm,zllx);
    }
}

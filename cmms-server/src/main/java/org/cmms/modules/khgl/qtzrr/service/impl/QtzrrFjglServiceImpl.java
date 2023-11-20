package org.cmms.modules.khgl.qtzrr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.qtzrr.entity.Fjgl;
import org.cmms.modules.khgl.qtzrr.mapper.QtzrrFjglMapper;
import org.cmms.modules.khgl.qtzrr.service.IQtzrrFjglService;
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
public class QtzrrFjglServiceImpl extends ServiceImpl<QtzrrFjglMapper, Fjgl> implements IQtzrrFjglService {


    @Autowired
    QtzrrFjglMapper fjglMapper;


    @Override
    public List<Fjgl> selectByMainId(String zjhm){
        return fjglMapper.selectByMainId(zjhm);
    }

    @Override
    public boolean deleteImg(String zjhm,String zllx){
        return fjglMapper.deleteImg(zjhm,zllx);
    }
}

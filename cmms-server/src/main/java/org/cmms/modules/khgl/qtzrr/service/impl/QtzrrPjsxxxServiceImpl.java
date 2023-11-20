package org.cmms.modules.khgl.qtzrr.service.impl;

import org.cmms.modules.khgl.qtzrr.entity.QtzrrPjsxxx;
import org.cmms.modules.khgl.qtzrr.mapper.QtzrrPjsxxxMapper;
import org.cmms.modules.khgl.qtzrr.service.IQtzrrPjsxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 其他自然人评级授信信息表
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Service
public class QtzrrPjsxxxServiceImpl extends ServiceImpl<QtzrrPjsxxxMapper, QtzrrPjsxxx> implements IQtzrrPjsxxxService {

    @Autowired
    QtzrrPjsxxxMapper qtzrrPjsxxxMapper;

    @Override
    public List<QtzrrPjsxxx> selectByMainId(String zjhm){
        return qtzrrPjsxxxMapper.selectByMainId(zjhm);
    }

}

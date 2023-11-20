package org.cmms.modules.khgl.qtzrr.service.impl;

import org.cmms.modules.khgl.qtzrr.entity.Qtzrrfcxx;
import org.cmms.modules.khgl.qtzrr.mapper.QtzrrfcxxMapper;
import org.cmms.modules.khgl.qtzrr.service.IQtzrrfcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 其他自然人房产信息
 * @Author: cmms
 * @Date:   2019-11-29
 * @Version: V1.0
 */
@Service
public class QtzrrfcxxServiceImpl extends ServiceImpl<QtzrrfcxxMapper, Qtzrrfcxx> implements IQtzrrfcxxService {

    @Autowired
    QtzrrfcxxMapper qtzrrfcxxMapper;

    @Override
    public List<Qtzrrfcxx> selectByMainId(String zjhm){
        return qtzrrfcxxMapper.selectByMainId(zjhm);
    }

    @Override
    public Qtzrrfcxx selectFcjz(String hhbm){
        return qtzrrfcxxMapper.selectFcjz(hhbm);
    }


}

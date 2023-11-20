package org.cmms.modules.khgl.qtzrr.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khgl.qtzrr.entity.KhglQtzrrhzzllb;
import org.cmms.modules.khgl.qtzrr.mapper.QtzrrhzzllbMapper;
import org.cmms.modules.khgl.qtzrr.service.IQtzrrhzzllbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 农户资料
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
@Service
public class QtzrrhzzllbServiceImpl extends ServiceImpl<QtzrrhzzllbMapper, KhglQtzrrhzzllb> implements IQtzrrhzzllbService {

    @Autowired
    QtzrrhzzllbMapper qtzrrhzzllbMapper;

    @Override
    public List<KhglQtzrrhzzllb> selectByMainId(String hhbm){
        return qtzrrhzzllbMapper.selectByMainId(hhbm);
    }
}

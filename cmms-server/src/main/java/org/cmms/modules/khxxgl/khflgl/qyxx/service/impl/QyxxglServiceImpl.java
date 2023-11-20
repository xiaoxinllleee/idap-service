package org.cmms.modules.khxxgl.khflgl.qyxx.service.impl;

import org.cmms.modules.khxxgl.khflgl.qyxx.entity.Qyxxgl;
import org.cmms.modules.khxxgl.khflgl.qyxx.mapper.QyxxglMapper;
import org.cmms.modules.khxxgl.khflgl.qyxx.service.IQyxxglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 企业信息
 * @Author: jeecg-boot
 * @Date:   2022-11-02
 * @Version: V1.0
 */
@Service
public class QyxxglServiceImpl extends ServiceImpl<QyxxglMapper, Qyxxgl> implements IQyxxglService {

    @Override
    public void init() {
        baseMapper.init();
    }

    @Override
    public List<Qyxxgl> selectByQyxx(String hhbm) {
        return baseMapper.selectByQyxx(hhbm);
    }
}

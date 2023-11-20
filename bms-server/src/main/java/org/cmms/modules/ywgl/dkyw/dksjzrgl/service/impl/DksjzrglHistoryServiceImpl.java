package org.cmms.modules.ywgl.dkyw.dksjzrgl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.DksjzrglHistory;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.mapper.DksjzrglHistoryMapper;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.mapper.DksjzrglMapper;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglServiceHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Service
public class DksjzrglHistoryServiceImpl extends ServiceImpl<DksjzrglHistoryMapper, DksjzrglHistory> implements IDksjzrglServiceHistory {

    @Override
    public List<DksjzrglHistory> deleteDkzh(String dkzh , String tableName) {
        return baseMapper.deleteDkzh(dkzh,tableName);
    }

    @Override
    public List<DksjzrglHistory> saveTjyf( List list, String tableName) {
        return baseMapper.saveTjyf(list,tableName);
    }
}

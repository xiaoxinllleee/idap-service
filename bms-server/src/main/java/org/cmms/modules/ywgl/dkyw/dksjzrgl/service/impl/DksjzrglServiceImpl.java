package org.cmms.modules.ywgl.dkyw.dksjzrgl.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.entity.Dksjzrgl;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.mapper.DksjzrglMapper;
import org.cmms.modules.ywgl.dkyw.dksjzrgl.service.IDksjzrglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description: 贷款数据责任管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Service
public class DksjzrglServiceImpl extends ServiceImpl<DksjzrglMapper, Dksjzrgl> implements IDksjzrglService {


    @Override
    public List<Dksjzrgl> deleteDkzh(String dkzh , String tableName) {
        return baseMapper.deleteDkzh(dkzh,tableName);
    }

    @Override
    public List<Dksjzrgl> saveTjyf( List list, String tableName) {
        return baseMapper.saveTjyf(list,tableName);
    }
}

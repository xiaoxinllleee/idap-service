package org.cmms.modules.khdj.khdjcsgl.service.impl;

import org.cmms.modules.khdj.khdjcsgl.entity.KhdjCsgl;
import org.cmms.modules.khdj.khdjcsgl.mapper.KhdjCsglMapper;
import org.cmms.modules.khdj.khdjcsgl.service.IKhdjCsglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户等级评定参数管理
 * @Author: cmms
 * @Date:   2019-10-28
 * @Version: V1.0
 */
@Service
public class KhdjCsglServiceImpl extends ServiceImpl<KhdjCsglMapper, KhdjCsgl> implements IKhdjCsglService {

    @Autowired
    private KhdjCsglMapper mapper;

    @Override
    public KhdjCsgl queryByPid(Map<String, String> sql) {
        return mapper.queryByPid(sql);
    }
}

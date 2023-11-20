package org.cmms.modules.khdj.khdjpdsjx.service.impl;

import org.cmms.modules.khdj.khdjpdsjx.entity.KhdjpdSjx;
import org.cmms.modules.khdj.khdjpdsjx.mapper.KhdjpdSjxMapper;
import org.cmms.modules.khdj.khdjpdsjx.service.IKhdjpdSjxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户等级评定数据项
 * @Author: cmms
 * @Date:   2019-10-16
 * @Version: V1.0
 */
@Service
public class KhdjpdSjxServiceImpl extends ServiceImpl<KhdjpdSjxMapper, KhdjpdSjx> implements IKhdjpdSjxService {

    @Autowired
    private KhdjpdSjxMapper mapper;

    @Override
    public KhdjpdSjx queryBySjxid(Map<String, String> sql) {
        return mapper.queryBySjxid(sql);
    }
}

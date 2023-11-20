package org.cmms.modules.khdj.khdjpd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khdj.khdjpd.entity.Khdjpd;
import org.cmms.modules.khdj.khdjpd.mapper.KhdjpdMapper;
import org.cmms.modules.khdj.khdjpd.service.IKhdjpdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-20
 * @Version: V1.0
 */
@Service
public class KhdjpdServiceImpl extends ServiceImpl<KhdjpdMapper, Khdjpd> implements IKhdjpdService {

    @Resource
    private KhdjpdMapper mapper;

    @Override
    public void initData(Map<String, String> sql) {
        mapper.initData(sql);
    }

    @Override
    public void callAutoMission() {
        mapper.callAutoMission();
    }
}

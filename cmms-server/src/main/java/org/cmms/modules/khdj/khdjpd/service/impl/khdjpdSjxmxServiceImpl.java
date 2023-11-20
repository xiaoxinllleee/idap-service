package org.cmms.modules.khdj.khdjpd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khdj.khdjpd.entity.khdjpdSjxmx;
import org.cmms.modules.khdj.khdjpd.mapper.khdjpdSjxmxMapper;
import org.cmms.modules.khdj.khdjpd.service.IkhdjpdSjxmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 客户等级评定数据项明细
 * @Author: cmms
 * @Date:   2019-11-08
 * @Version: V1.0
 */
@Service
public class khdjpdSjxmxServiceImpl extends ServiceImpl<khdjpdSjxmxMapper, khdjpdSjxmx> implements IkhdjpdSjxmxService {

    @Autowired
    private khdjpdSjxmxMapper mapper;

    @Override
    public List<khdjpdSjxmx> viewDetail(String pdzq, Date pdrq, String zjhm) {
        return mapper.viewDetail(pdzq, pdrq, zjhm);
    }
}

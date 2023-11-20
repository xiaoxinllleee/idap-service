package org.cmms.modules.tjfx.khjbfctj.service.impl;

import org.cmms.modules.tjfx.khjbfctj.entity.KhjbfctjQh;
import org.cmms.modules.tjfx.khjbfctj.mapper.KhjbfctjQhMapper;
import org.cmms.modules.tjfx.khjbfctj.service.IKhjbfctjQhService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 客户级别分层统计_全行
 * @Author: cmms
 * @Date:   2019-12-06
 * @Version: V1.0
 */
@Service
public class KhjbfctjQhServiceImpl extends ServiceImpl<KhjbfctjQhMapper, KhjbfctjQh> implements IKhjbfctjQhService {

    @Autowired
    private KhjbfctjQhMapper mapperQ;

    @Override
    public void extract(Map<String, String> sql) {
        mapperQ.extract(sql);
    }

    @Override
    public Date getMaxDateM() {
        return mapperQ.getMaxDateM();
    }
    @Override
    public Date getMaxDateQ() {
        return mapperQ.getMaxDateQ();
    }
    @Override
    public Date getMaxDateY() {
        return mapperQ.getMaxDateY();
    }

    @Override
    public List getQhDataM() { return mapperQ.getQhDataM(); }
    @Override
    public List getQhDataQ() {
        return mapperQ.getQhDataQ();
    }
    @Override
    public List getQhDataY() {
        return mapperQ.getQhDataY();
    }
}

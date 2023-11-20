package org.cmms.modules.khgl.clkhxxgl.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.clkhxxgl.entity.Clkhxxgl;
import org.cmms.modules.khgl.clkhxxgl.mapper.ClkhxxglMapper;
import org.cmms.modules.khgl.clkhxxgl.service.IClkhxxglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 存量客户信息管理
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Service
public class ClkhxxglServiceImpl extends ServiceImpl<ClkhxxglMapper, Clkhxxgl> implements IClkhxxglService {
    @Autowired
    private ClkhxxglMapper clkhxxglMapper;

    public void initClkhxx() {
        clkhxxglMapper.initClkhxx();
    }

    public void initClkhxxRC() {
        clkhxxglMapper.initClkhxxRC();
    }

    public void initKhxx() {
        clkhxxglMapper.initKhxx();
    }

    public void initKhxxRC(String zxrkrq) {
        clkhxxglMapper.initKhxxRC(zxrkrq);
    }

    public int checkTableExist( String tableName){return clkhxxglMapper.checkTableExist(tableName);}

    public String getCsz(String csbm){return  clkhxxglMapper.getCsz(csbm);}

    @Override
    public int syncYesYwhywxxwl() {
        return clkhxxglMapper.syncYesYwhywxxwl();
    }

    @Override
    public int syncYesYwhywxxwlZh() {
        return clkhxxglMapper.syncYesYwhywxxwlZh();
    }

    @Override
    public int delYwhxxwlDayBySjrq(String sjrq) {
        return clkhxxglMapper.delYwhxxwlDayBySjrq(sjrq);
    }

    @Override
    public int delYwhxxwlDayZhBySjrq(String sjrq) {
        return clkhxxglMapper.delYwhxxwlDayZhBySjrq(sjrq);
    }
}

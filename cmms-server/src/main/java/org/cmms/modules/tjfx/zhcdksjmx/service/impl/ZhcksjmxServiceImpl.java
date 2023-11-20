package org.cmms.modules.tjfx.zhcdksjmx.service.impl;

import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import org.cmms.modules.tjfx.zhcdksjmx.mapper.ZhcksjmxMapper;
import org.cmms.modules.tjfx.zhcdksjmx.service.IZhcksjmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
@Service
public class ZhcksjmxServiceImpl extends ServiceImpl<ZhcksjmxMapper, Zhcksjmx> implements IZhcksjmxService {
    @Resource
    private ZhcksjmxMapper zhcksjmxMapper;

    @Override
    public List<Map> getzhjynck() {
        return zhcksjmxMapper.getzhjynck();
    }
    @Override
    public List<Map> getzhdqhqck() {
        return zhcksjmxMapper.getzhdqhqck();
    }
    @Override
    public List<Map> getzhdgdsck() {
        return zhcksjmxMapper.getzhdgdsck();
    }
    @Override
    public List<Map> getzhanlqjck(Date tjyf) {
        return zhcksjmxMapper.getzhanlqjck(tjyf);
    }
    @Override
    public List<Map> getzhdhckqj(Date tjyf) {
        return zhcksjmxMapper.getzhdhckqj(tjyf);
    }

    @Override
    public List<Map> getjynck(String jgdm) {
        return zhcksjmxMapper.getjynck(jgdm);
    }
    @Override
    public List<Map> getdqhqck(String jgdm) {
        return zhcksjmxMapper.getdqhqck(jgdm);
    }
    @Override
    public List<Map> getdgdsck(String jgdm) {
        return zhcksjmxMapper.getdgdsck(jgdm);
    }
    @Override
    public List<Map> getanlqjck(Date tjyf,String jgdm) {
        return zhcksjmxMapper.getanlqjck(tjyf,jgdm);
    }
    @Override
    public List<Map> getdhckqj(Date tjyf,String jgdm) {
        return zhcksjmxMapper.getdhckqj(tjyf,jgdm);
    }

    @Override
    public List<Map> getckpm(Date tjyf) {
        return zhcksjmxMapper.getckpm(tjyf);
    }

    @Override
    public List<Map> getckwclpm(Date tjyf) {
        return zhcksjmxMapper.getckwclpm(tjyf);
    }

    @Override
    public List<Map> getdkpm(Date tjyf) {
        return zhcksjmxMapper.getdkpm(tjyf);
    }

    @Override
    public List<Map> getdkwclpm(Date tjyf) {
        return zhcksjmxMapper.getdkwclpm(tjyf);
    }


    @Override
    public Date getAnlqjcRq() {
        return zhcksjmxMapper.getAnlqjcRq();
    }

    @Override
    public Date getZhdksjrq() {
        return zhcksjmxMapper.getZhdksjrq();
    }

    @Override
    public Date getZhcksjrq() {
        return zhcksjmxMapper.getZhcksjrq();
    }
    @Override
    public List<Map> getzhjyndksj() {
        return zhcksjmxMapper.getzhjyndksj();
    }

    @Override
    public List<Map> getjyndksj(String jgdm) {
        return zhcksjmxMapper.getjyndksj(jgdm);
    }

    @Override
    public List<Map> getzhwjfldk( Date tjyf) {
        return zhcksjmxMapper.getzhwjfldk(tjyf);
    }
    @Override
    public List<Map> getwjfldk(Date tjyf, String jgdm) {
        return zhcksjmxMapper.getwjfldk(tjyf,jgdm);
    }

    @Override
    public List<Map> getzhanlqjdk(Date tjyf) {
        return zhcksjmxMapper.getzhanlqjdk(tjyf);
    }

    @Override
    public List<Map> getanlqjdk(Date tjyf,String jgdm) {
        return zhcksjmxMapper.getanlqjdk(tjyf,jgdm);
    }

    @Override
    public List<Map> getzhdhdkqj(Date tjyf) {
        return zhcksjmxMapper.getzhdhdkqj(tjyf);
    }

    @Override
    public List<Map> getdhdkqj(Date tjyf,String jgdm) {
        return zhcksjmxMapper.getdhdkqj(tjyf,jgdm);
    }

    @Override
    public List<Map> getqhcksj(Date tjyf) {
        return zhcksjmxMapper.getqhcksj(tjyf);
    }

    @Override
    public List<Map> getzhcksj(Date tjyf,String jgdm) {
        return zhcksjmxMapper.getzhcksj(tjyf,jgdm);
    }

    @Override
    public Date getAnlqjcRqDk() {
        return zhcksjmxMapper.getAnlqjcRqDk();
    }

    @Override
    public List<Map> getqhcdkxzs() {
        return zhcksjmxMapper.getqhcdkxzs();
    }

    @Override
    public List<Map> getcdkxzs(String jgdm) {
        return zhcksjmxMapper.getcdkxzs(jgdm);
    }

    @Override
    public void callSysjmx() {
        zhcksjmxMapper.callSysjmx();
    }
}

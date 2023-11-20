package org.cmms.modules.khgl.grkhgl.service.impl;

import org.cmms.modules.khgl.grkhgl.entity.Khhmcxx;
import org.cmms.modules.khgl.grkhgl.mapper.KhhmcxxMapper;
import org.cmms.modules.khgl.grkhgl.service.IKhhmcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 客户花名册
 * @Author: jeecg-boot
 * @Date:   2020-07-06
 * @Version: V1.0
 */
@Service
public class KhhmcxxServiceImpl extends ServiceImpl<KhhmcxxMapper, Khhmcxx> implements IKhhmcxxService {
    @Autowired
    KhhmcxxMapper khhmcxxMapper;

    @Override
    public Khhmcxx selectByMainId(String zjhm){
        return khhmcxxMapper.selectByMainId(zjhm);
    }

    @Override
    public List<Khhmcxx>selectByhhbm(String hhbm,String zjhm){
        return khhmcxxMapper.selectByhhbm(hhbm, zjhm);
    }

    @Override
    public List<Khhmcxx>selectByhhbmId(String hhbm,String id){
        return khhmcxxMapper.selectByhhbmId(hhbm, id);
    }

    @Override
    public List<Khhmcxx> GetKhhmcPartialInfoByZjhm(String zjhm) {
        return khhmcxxMapper.GetKhhmcPartialInfoByZjhm(zjhm);
    }

    @Override
    public List<Khhmcxx> getJtcyxxByHmcHhbmAndId(String hhbm, String id) {
        return khhmcxxMapper.getJtcyxxByHmcHhbmAndId(hhbm,id);
    }
}

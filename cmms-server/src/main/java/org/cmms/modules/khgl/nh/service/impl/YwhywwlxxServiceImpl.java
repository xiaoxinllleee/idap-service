package org.cmms.modules.khgl.nh.service.impl;

import org.cmms.modules.khgl.nh.entity.Ywhywwlxx;
import org.cmms.modules.khgl.nh.mapper.YwhywwlxxMapper;
import org.cmms.modules.khgl.nh.service.IYwhywwlxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-04-02
 * @Version: V1.0
 */
@Service
public class YwhywwlxxServiceImpl extends ServiceImpl<YwhywwlxxMapper, Ywhywwlxx> implements IYwhywwlxxService {

    @Autowired
    YwhywwlxxMapper ywhywwlxxMapper;

    @Override
    public List<Ywhywwlxx> selectByMainId (String zjhm){
        return ywhywwlxxMapper.selectByMainId(zjhm);
    }

    @Override
    public List<Ywhywwlxx> selectByHhbm(String hhbm) {
        return ywhywwlxxMapper.selectByHhbm(hhbm);
    }

    public  boolean  deleteByMainId( String zjhm){
      return   ywhywwlxxMapper.deleteByMainId(zjhm);

    }

    @Override
    public String endDate(String zjhm) {
        return baseMapper.endDate(zjhm);
    }

    @Override
    public String appMaturityDate(String zjhm) {
        return baseMapper.appMaturityDate(zjhm);
    }
}

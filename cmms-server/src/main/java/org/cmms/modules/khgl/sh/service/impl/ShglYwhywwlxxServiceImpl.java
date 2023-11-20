package org.cmms.modules.khgl.sh.service.impl;

import org.cmms.modules.khgl.sh.entity.ShglYwhywwlxx;
import org.cmms.modules.khgl.sh.mapper.ShglYwhywwlxxMapper;
import org.cmms.modules.khgl.sh.service.IShglYwhywwlxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 与我行业务往来信息
 * @Author: jeecg-boot
 * @Date:   2020-10-24
 * @Version: V1.0
 */
@Service
public class ShglYwhywwlxxServiceImpl extends ServiceImpl<ShglYwhywwlxxMapper, ShglYwhywwlxx> implements IShglYwhywwlxxService {

    @Autowired
    ShglYwhywwlxxMapper shglYwhywwlxxMapper;

    @Override
    public List<ShglYwhywwlxx> selectByMainId (String zjhm){
        return shglYwhywwlxxMapper.selectByMainId(zjhm);
    }

}

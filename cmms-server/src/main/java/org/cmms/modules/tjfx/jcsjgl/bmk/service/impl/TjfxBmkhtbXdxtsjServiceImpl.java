package org.cmms.modules.tjfx.jcsjgl.bmk.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjfx.jcsjgl.bmk.entity.TjfxBmkhtbXdxtsj;
import org.cmms.modules.tjfx.jcsjgl.bmk.mapper.TjfxBmkhtbXdxtsjMapper;
import org.cmms.modules.tjfx.jcsjgl.bmk.service.ITjfxBmkhtbXdxtsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-08-10
 * @Version: V1.0
 */
@Service
public class TjfxBmkhtbXdxtsjServiceImpl extends ServiceImpl<TjfxBmkhtbXdxtsjMapper, TjfxBmkhtbXdxtsj> implements ITjfxBmkhtbXdxtsjService {

    @Autowired
    private TjfxBmkhtbXdxtsjMapper tjfxBmkhtbXdxtsjMapper;

    @Override
    public TjfxBmkhtbXdxtsj queryMsg(String zjhm, String jgdm, String tjyf) {
        return tjfxBmkhtbXdxtsjMapper.queryMsg(zjhm, jgdm, tjyf);
    }


//    @Override
//    public int deleteMsg(String zjhm, String jgdm, String tjyf) {
//        return tjfxBmkhtbXdxtsjMapper.deleteMsg(zjhm, jgdm, tjyf);
//    }
    @Override
    public int deleteMsg(String tjyf) {
        return tjfxBmkhtbXdxtsjMapper.deleteMsg(tjyf);
    }


    @Override
    public  void deletemian(@Param("tjyf") Date tjyf){
        tjfxBmkhtbXdxtsjMapper.deletemian(tjyf);
    }


}

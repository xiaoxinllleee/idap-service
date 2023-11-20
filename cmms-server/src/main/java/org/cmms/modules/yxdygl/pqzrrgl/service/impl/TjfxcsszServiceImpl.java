package org.cmms.modules.yxdygl.pqzrrgl.service.impl;

import org.cmms.modules.yxdygl.pqzrrgl.entity.Tjfxcssz;
import org.cmms.modules.yxdygl.pqzrrgl.mapper.TjfxcsszMapper;
import org.cmms.modules.yxdygl.pqzrrgl.service.ITjfxcsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 参数设置
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */

@Service
public class TjfxcsszServiceImpl extends ServiceImpl<TjfxcsszMapper, Tjfxcssz> implements ITjfxcsszService {
    @Autowired
     private  TjfxcsszMapper tjfxcsszMapper;

    @Override
        public String selectcsz(){
        return tjfxcsszMapper.selectcsz();
    }

}

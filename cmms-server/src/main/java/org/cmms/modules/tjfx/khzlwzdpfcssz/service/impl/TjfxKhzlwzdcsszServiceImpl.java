package org.cmms.modules.tjfx.khzlwzdpfcssz.service.impl;

import org.cmms.modules.tjfx.khzlwzdpfcssz.entity.TjfxKhzlwzdcssz;
import org.cmms.modules.tjfx.khzlwzdpfcssz.mapper.TjfxKhzlwzdcsszMapper;
import org.cmms.modules.tjfx.khzlwzdpfcssz.service.ITjfxKhzlwzdcsszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;
import java.util.Map;

/**
 * @Description: 客户建档完整度评分参数设置
 * @Author: cmms
 * @Date:   2019-12-05
 * @Version: V1.0
 */
@Service
public class TjfxKhzlwzdcsszServiceImpl extends ServiceImpl<TjfxKhzlwzdcsszMapper, TjfxKhzlwzdcssz> implements ITjfxKhzlwzdcsszService {

    @Autowired
    TjfxKhzlwzdcsszMapper tjfxKhzlwzdcsszMapper;

    public List<TjfxKhzlwzdcssz> selectByMainId(String csbm){

        return  tjfxKhzlwzdcsszMapper.selectByMainId(csbm);
    }

    @Override
    public List<Map<String,String>> selectTable(String tablename) {
        return tjfxKhzlwzdcsszMapper.selectTable(tablename);
    }

   /* public List<Map<String,Object>> selectTable(String tablename){
        return  tjfxKhzlwzdcsszMapper.selectTable(tablename);
    }*/
}

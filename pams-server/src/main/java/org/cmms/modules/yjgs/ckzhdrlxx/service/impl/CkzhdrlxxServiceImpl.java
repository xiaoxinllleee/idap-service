package org.cmms.modules.yjgs.ckzhdrlxx.service.impl;

import org.cmms.modules.yjgs.ckzhdrlxx.entity.Ckzhdrlxx;
import org.cmms.modules.yjgs.ckzhdrlxx.mapper.CkzhdrlxxMapper;
import org.cmms.modules.yjgs.ckzhdrlxx.service.ICkzhdrlxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 存款账号待认领
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
@Service
public class CkzhdrlxxServiceImpl extends ServiceImpl<CkzhdrlxxMapper, Ckzhdrlxx> implements ICkzhdrlxxService {
    @Autowired
    private CkzhdrlxxMapper ckzhdrlxxMapper;

    @Override
    public void init(){
        ckzhdrlxxMapper.init();
    }

    @Override
    public List<Ckzhdrlxx> getListByIds(List<String> ids) {
        return ckzhdrlxxMapper.getListByIds(ids);
    }

    @Override
    public void deleteSptgsj(String tableId) {
        ckzhdrlxxMapper.deleteSptgsj(tableId);
    }
}

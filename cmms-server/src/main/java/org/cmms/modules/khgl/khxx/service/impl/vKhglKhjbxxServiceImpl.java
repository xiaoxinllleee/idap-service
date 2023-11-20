package org.cmms.modules.khgl.khxx.service.impl;

import org.cmms.modules.khgl.khxx.entity.vKhglKhjbxx;
import org.cmms.modules.khgl.khxx.mapper.vKhglKhjbxxMapper;
import org.cmms.modules.khgl.khxx.service.IvKhglKhjbxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 客户信息管理
 * @Author: jeecg-boot
 * @Date:   2020-11-25
 * @Version: V1.0
 */
@Service
public class vKhglKhjbxxServiceImpl extends ServiceImpl<vKhglKhjbxxMapper, vKhglKhjbxx> implements IvKhglKhjbxxService {
    @Autowired
    vKhglKhjbxxMapper khglKhjbxxMapper;

    @Override
    public Map<String,Object> selectByHhbm(String hhbm){
        return khglKhjbxxMapper.selectByHhbm(hhbm);
    }

}

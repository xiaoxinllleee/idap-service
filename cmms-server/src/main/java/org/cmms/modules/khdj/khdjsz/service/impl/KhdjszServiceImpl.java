package org.cmms.modules.khdj.khdjsz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.khdj.khdjsz.entity.Khdjsz;
import org.cmms.modules.khdj.khdjsz.mapper.khdjszMapper;
import org.cmms.modules.khdj.khdjsz.service.IkhdjszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Service
public class KhdjszServiceImpl extends ServiceImpl<khdjszMapper, Khdjsz> implements IkhdjszService {

    @Autowired
    private khdjszMapper mapper;

    @Override
    public Khdjsz queryByDjbh(Map<String, String> sql) {
        return mapper.queryByDjbh(sql);
    }
}

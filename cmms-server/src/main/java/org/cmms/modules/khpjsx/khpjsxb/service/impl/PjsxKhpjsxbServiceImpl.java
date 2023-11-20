package org.cmms.modules.khpjsx.khpjsxb.service.impl;

import org.cmms.modules.khpjsx.khpjsxb.entity.PjsxKhpjsxb;
import org.cmms.modules.khpjsx.khpjsxb.mapper.PjsxKhpjsxbMapper;
import org.cmms.modules.khpjsx.khpjsxb.service.IPjsxKhpjsxbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.transaction.Transactional;

/**
 * @Description: 客户评级授信表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@Service
public class PjsxKhpjsxbServiceImpl extends ServiceImpl<PjsxKhpjsxbMapper, PjsxKhpjsxb> implements IPjsxKhpjsxbService {
    @Autowired
    private PjsxKhpjsxbMapper nhjbxxMapper;



    @Override
    @Transactional
    public void extractPjsx(String khlx,String tjyf) {
        nhjbxxMapper.extractPjsx(khlx,tjyf);
    }
}

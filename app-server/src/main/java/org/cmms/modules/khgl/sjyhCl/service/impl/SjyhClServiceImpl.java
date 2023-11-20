package org.cmms.modules.khgl.sjyhCl.service.impl;

import org.cmms.modules.khgl.sjyhCl.entity.SjyhCl;
import org.cmms.modules.khgl.sjyhCl.mapper.SjyhClMapper;
import org.cmms.modules.khgl.sjyhCl.service.ISjyhClService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 手机银行_慈利
 * @Author: jeecg-boot
 * @Date:   2022-11-14
 * @Version: V1.0
 */
@Service
public class SjyhClServiceImpl extends ServiceImpl<SjyhClMapper, SjyhCl> implements ISjyhClService {

    @Override
    public String getDate() {
        return baseMapper.getDate();
    }
}

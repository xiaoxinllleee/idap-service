package org.cmms.modules.khpjsx.pjsxdjsz.service.impl;

import org.cmms.modules.khpjsx.pjsxdjsz.entity.PjsxKhdjsz;
import org.cmms.modules.khpjsx.pjsxdjsz.mapper.PjsxKhdjszMapper;
import org.cmms.modules.khpjsx.pjsxdjsz.service.IPjsxKhdjszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 评级授信等级设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class PjsxKhdjszServiceImpl extends ServiceImpl<PjsxKhdjszMapper, PjsxKhdjsz> implements IPjsxKhdjszService {

    @Autowired
    private PjsxKhdjszMapper mapper;

    @Override
    public PjsxKhdjsz queryDjbh(Map<String, String> sql) {
        return mapper.queryDjbh(sql);
    }
}

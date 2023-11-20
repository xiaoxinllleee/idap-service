package org.cmms.modules.khpjsx.pjxmsz.service.impl;

import org.cmms.modules.khpjsx.pjxmsz.entity.PjsxPjxmsz;
import org.cmms.modules.khpjsx.pjxmsz.mapper.PjsxPjxmszMapper;
import org.cmms.modules.khpjsx.pjxmsz.service.IPjsxPjxmszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 评级项目设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Service
public class PjsxPjxmszServiceImpl extends ServiceImpl<PjsxPjxmszMapper, PjsxPjxmsz> implements IPjsxPjxmszService {

    @Autowired
    PjsxPjxmszMapper pjsxPjxmszMapper;

    public PjsxPjxmsz queryxmbh (Map<String,String>sql) {
        return pjsxPjxmszMapper.queryxmbh(sql);
    }

}

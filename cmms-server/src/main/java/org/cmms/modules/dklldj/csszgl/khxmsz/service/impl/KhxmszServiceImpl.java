package org.cmms.modules.dklldj.csszgl.khxmsz.service.impl;

import org.cmms.modules.dklldj.csszgl.khxmsz.entity.Khxmsz;
import org.cmms.modules.dklldj.csszgl.khxmsz.mapper.KhxmszMapper;
import org.cmms.modules.dklldj.csszgl.khxmsz.service.IKhxmszService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 考核项目设置
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Service
public class KhxmszServiceImpl extends ServiceImpl<KhxmszMapper, Khxmsz> implements IKhxmszService {

    @Override
    public Khxmsz queryByQydmAndZbid(Map<String, String> sql) {
        return baseMapper.queryByQydmAndZbid(sql);
    }

}

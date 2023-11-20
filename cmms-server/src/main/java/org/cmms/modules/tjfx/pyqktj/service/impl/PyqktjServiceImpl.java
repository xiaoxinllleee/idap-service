package org.cmms.modules.tjfx.pyqktj.service.impl;

import org.cmms.modules.tjfx.pyqktj.entity.Pyqktj;
import org.cmms.modules.tjfx.pyqktj.mapper.PyqktjMapper;
import org.cmms.modules.tjfx.pyqktj.service.IPyqktjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 评议情况统计
 * @Author: jeecg-boot
 * @Date:   2022-12-30
 * @Version: V1.0
 */
@Service
public class PyqktjServiceImpl extends ServiceImpl<PyqktjMapper, Pyqktj> implements IPyqktjService {
    @Autowired
    private PyqktjMapper pyqktjMapper;

    @Override
    public void init(String tjrq) {
        pyqktjMapper.init(tjrq);
    }
}

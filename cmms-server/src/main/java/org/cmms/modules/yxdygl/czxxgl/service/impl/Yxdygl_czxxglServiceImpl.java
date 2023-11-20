package org.cmms.modules.yxdygl.czxxgl.service.impl;

import org.cmms.modules.yxdygl.czxxgl.entity.Yxdygl_czxxgl;
import org.cmms.modules.yxdygl.czxxgl.mapper.Yxdygl_czxxglMapper;
import org.cmms.modules.yxdygl.czxxgl.service.IYxdygl_czxxglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 村信息管理
 * @Author: cmms
 * @Date:   2019-11-05
 * @Version: V1.0
 */
@Service
public class Yxdygl_czxxglServiceImpl extends ServiceImpl<Yxdygl_czxxglMapper, Yxdygl_czxxgl> implements IYxdygl_czxxglService {
    @Autowired
    private Yxdygl_czxxglMapper czxxglMapper;

    public Yxdygl_czxxgl queryByQybm(String qybm) {
        return czxxglMapper.queryByQybm(qybm);
    }

    public List<Yxdygl_czxxgl> queryByCmc(String cmc) {
        return czxxglMapper.queryByCmc(cmc);
    }
}

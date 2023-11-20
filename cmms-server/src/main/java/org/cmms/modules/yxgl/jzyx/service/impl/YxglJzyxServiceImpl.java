package org.cmms.modules.yxgl.jzyx.service.impl;

import org.cmms.modules.yxgl.jzyx.entity.YxglJzyx;
import org.cmms.modules.yxgl.jzyx.mapper.YxglJzyxMapper;
import org.cmms.modules.yxgl.jzyx.service.IYxglJzyxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 精准营销
 * @Author: cmms
 * @Date:   2019-12-17
 * @Version: V1.0
 */
@Service
public class YxglJzyxServiceImpl extends ServiceImpl<YxglJzyxMapper, YxglJzyx> implements IYxglJzyxService {

    @Autowired
    YxglJzyxMapper yxglJzyxMapper;

    public void init(){
        yxglJzyxMapper.init();
    }


}

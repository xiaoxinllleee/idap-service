package org.cmms.modules.xdgl.grdkgl.service.impl;

import org.cmms.modules.xdgl.grdkgl.entity.RateDjsqxx;
import org.cmms.modules.xdgl.grdkgl.mapper.RateDjsqxxMapper;
import org.cmms.modules.xdgl.grdkgl.service.IRateDjsqxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 等级申请信息
 * @Author: jeecg-boot
 * @Date:   2020-08-28
 * @Version: V1.0
 */
@Service
public class RateDjsqxxServiceImpl extends ServiceImpl<RateDjsqxxMapper, RateDjsqxx> implements IRateDjsqxxService {

    @Autowired
     RateDjsqxxMapper rateDjsqxxMapper;

    @Override
    public RateDjsqxx querydjsqxx(String zjhm , Date djnf){
        return rateDjsqxxMapper.querydjsqxx(zjhm,djnf);
    }

}

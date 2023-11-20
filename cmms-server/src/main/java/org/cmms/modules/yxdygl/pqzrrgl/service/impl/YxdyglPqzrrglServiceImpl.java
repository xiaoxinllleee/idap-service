package org.cmms.modules.yxdygl.pqzrrgl.service.impl;

import org.cmms.modules.yxdygl.pqzrrgl.entity.YxdyglPqzrrgl;
import org.cmms.modules.yxdygl.pqzrrgl.mapper.YxdyglPqzrrglMapper;
import org.cmms.modules.yxdygl.pqzrrgl.service.IYxdyglPqzrrglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 片区责任人管理
 * @Author: jeecg-boot
 * @Date:   2020-06-30
 * @Version: V1.0
 */
@Service
public class YxdyglPqzrrglServiceImpl extends ServiceImpl<YxdyglPqzrrglMapper, YxdyglPqzrrgl> implements IYxdyglPqzrrglService {

    @Autowired
    YxdyglPqzrrglMapper yxdyglPqzrrglMapper;

    @Override
    public  YxdyglPqzrrgl queryqydm(Map<String,String>sql){
        return yxdyglPqzrrglMapper.queryqydm(sql);

    }
    @Override
    public  int queryCountBykhjl(String khjl){
        return yxdyglPqzrrglMapper.queryCountBykhjl(khjl);
    }

}

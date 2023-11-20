package org.cmms.modules.khgl.sh.service.impl;

import org.cmms.modules.khgl.sh.entity.Shfcxx;
import org.cmms.modules.khgl.sh.mapper.ShfcxxMapper;
import org.cmms.modules.khgl.sh.service.IShfcxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 商户房产信息
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
@Service
public class ShfcxxServiceImpl extends ServiceImpl<ShfcxxMapper, Shfcxx> implements IShfcxxService {
    @Autowired
    ShfcxxMapper shfcxxMapper;

    @Override
    public Map<String,Object> queryFcxx(String shid){
        return shfcxxMapper.queryFcxx(shid);
    }

}

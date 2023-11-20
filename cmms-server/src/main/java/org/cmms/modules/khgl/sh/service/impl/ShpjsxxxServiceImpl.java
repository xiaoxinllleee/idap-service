package org.cmms.modules.khgl.sh.service.impl;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khgl.sh.entity.Shpjsxxx;
import org.cmms.modules.khgl.sh.mapper.ShpjsxxxMapper;
import org.cmms.modules.khgl.sh.service.IShpjsxxxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Map;

/**
 * @Description: 商户评级授信信息
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
@Service
public class ShpjsxxxServiceImpl extends ServiceImpl<ShpjsxxxMapper, Shpjsxxx> implements IShpjsxxxService {
    @Autowired
    ShpjsxxxMapper shpjsxxxMapper;

    @Override
    public Map<String,Object> queryZcxx(String shid){
        return shpjsxxxMapper.queryZcxx(shid);
    }

}

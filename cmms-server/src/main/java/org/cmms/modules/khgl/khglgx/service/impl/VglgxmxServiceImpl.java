package org.cmms.modules.khgl.khglgx.service.impl;

import org.cmms.modules.khgl.khglgx.entity.Vglgxmx;
import org.cmms.modules.khgl.khglgx.mapper.VglgxmxMapper;
import org.cmms.modules.khgl.khglgx.service.IVglgxmxService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 关联关系明细
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
@Service
public class VglgxmxServiceImpl extends ServiceImpl<VglgxmxMapper, Vglgxmx> implements IVglgxmxService {
    @Override
    public  int queryCountBykhjl(String khjl){
        return baseMapper.queryCountBykhjl(khjl);
    }

}

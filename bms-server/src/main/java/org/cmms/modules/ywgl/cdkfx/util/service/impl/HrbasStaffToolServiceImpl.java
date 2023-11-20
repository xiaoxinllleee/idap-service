package org.cmms.modules.ywgl.cdkfx.util.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.cmms.modules.system.vo.TableComments;
import org.cmms.modules.ywgl.cdkfx.util.mapper.HrbasStaffToolMapper;
import org.cmms.modules.ywgl.cdkfx.util.service.HrbasStaffToolService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 
 * @Author: jeecg-boot
 * @Date:   2021-06-21
 * @Version: V1.0
 */
@Service
public class HrbasStaffToolServiceImpl extends ServiceImpl<HrbasStaffToolMapper, TableComments> implements HrbasStaffToolService {

    @Override
    public String getCustidByName(String ygxm) {
        return baseMapper.getCustidByName(ygxm);
    }

    @Override
    public String getYgghByName(String ygxm) {
        return baseMapper.getYgghByName(ygxm);
    }
}

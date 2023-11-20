package org.cmms.modules.khlc.bmgpgl.service.impl;

import org.cmms.modules.khlc.bmgpgl.entity.Bmgpgl;
import org.cmms.modules.khlc.bmgpgl.entity.HrPostBmxx;
import org.cmms.modules.khlc.bmgpgl.mapper.BmgpglMapper;
import org.cmms.modules.khlc.bmgpgl.service.IBmgpglService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 * @Description: 部门挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@Service
public class BmgpglServiceImpl extends ServiceImpl<BmgpglMapper, Bmgpgl> implements IBmgpglService {

    @Override
    public List<HrPostBmxx> bmxx() {
        return baseMapper.bmxx();
    }
}

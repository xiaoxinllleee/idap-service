package org.cmms.modules.khgl.wcwgkhmx.service.impl;

import org.cmms.modules.khgl.wcwgkhmx.entity.Wcwgkhmx;
import org.cmms.modules.khgl.wcwgkhmx.mapper.WcwgkhmxMapper;
import org.cmms.modules.khgl.wcwgkhmx.service.IWcwgkhmxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 外出务工客户明细
 * @Author: penghr
 * @Date:   2020-02-11
 * @Version: V1.0
 */
@Service
public class WcwgkhmxServiceImpl extends ServiceImpl<WcwgkhmxMapper, Wcwgkhmx> implements IWcwgkhmxService {

    @Autowired
    private WcwgkhmxMapper wcwgkhmxMapper;

    @Override
    public void initData() {
        wcwgkhmxMapper.initData();
    }
}

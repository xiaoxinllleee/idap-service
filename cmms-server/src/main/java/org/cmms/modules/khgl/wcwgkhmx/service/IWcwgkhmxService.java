package org.cmms.modules.khgl.wcwgkhmx.service;

import org.cmms.modules.khgl.wcwgkhmx.entity.Wcwgkhmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 外出务工客户明细
 * @Author: penghr
 * @Date:   2020-02-11
 * @Version: V1.0
 */
public interface IWcwgkhmxService extends IService<Wcwgkhmx> {
    public void initData();
}

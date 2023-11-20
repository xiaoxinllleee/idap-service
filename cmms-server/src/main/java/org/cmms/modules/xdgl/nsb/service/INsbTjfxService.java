package org.cmms.modules.xdgl.nsb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.nsb.entity.NsbTjfx;

/**
 * @Description: 年审统计分析
 * @Author: jeecg-boot
 * @Date:   2023-07-04
 * @Version: V1.0
 */
public interface INsbTjfxService extends IService<NsbTjfx> {

    void tq();
}

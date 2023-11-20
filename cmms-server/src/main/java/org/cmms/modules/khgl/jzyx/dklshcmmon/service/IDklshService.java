package org.cmms.modules.khgl.jzyx.dklshcmmon.service;

import org.cmms.modules.khgl.jzyx.dklshcmmon.entity.Dklsh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
public interface IDklshService extends IService<Dklsh> {
    public void extract();
}

package org.cmms.modules.khgl.khglgx.service;

import org.cmms.modules.khgl.khglgx.entity.Vglgxmx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 关联关系明细
 * @Author: jeecg-boot
 * @Date:   2020-08-08
 * @Version: V1.0
 */
public interface IVglgxmxService extends IService<Vglgxmx> {
    public int queryCountBykhjl(String khjl);
}

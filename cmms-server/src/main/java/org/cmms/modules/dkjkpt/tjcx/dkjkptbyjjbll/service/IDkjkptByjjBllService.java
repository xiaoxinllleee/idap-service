package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjbll.entity.DkjkptByjjBll;

/**
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptByjjBllService extends IService<DkjkptByjjBll> {
    public void extract(String tjyf);
}

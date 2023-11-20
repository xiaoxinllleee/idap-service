package org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.dkjkpt.tjcx.dkjkptbyjjgz.entity.DkjkptByjjgzl;

/**
 * @Description: 本月将进关注
 * @Author: jeecg-boot
 * @Date:   2023-08-31
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkjkptByjjgzlService extends IService<DkjkptByjjgzl> {
    public void extract(String tjyf);

}

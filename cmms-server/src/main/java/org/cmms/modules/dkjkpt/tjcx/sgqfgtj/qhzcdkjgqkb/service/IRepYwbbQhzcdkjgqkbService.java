package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhzcdkjgqkb.entity.RepYwbbQhzcdkjgqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IRepYwbbQhzcdkjgqkbService extends IService<RepYwbbQhzcdkjgqkb> {
    public void extract(String tjyf);
}

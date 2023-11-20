package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhzcdkjgqkb.entity.RepYwbbZhzcdkjgqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行正常贷款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IRepYwbbZhzcdkjgqkbService extends IService<RepYwbbZhzcdkjgqkb> {
    public void extract(String tjyf);
}

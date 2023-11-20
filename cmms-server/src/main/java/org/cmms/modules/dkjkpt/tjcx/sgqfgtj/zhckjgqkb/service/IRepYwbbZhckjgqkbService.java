package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhckjgqkb.entity.RepYwbbZhckjgqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IRepYwbbZhckjgqkbService extends IService<RepYwbbZhckjgqkb> {
    public void extract(String tjyf);
}

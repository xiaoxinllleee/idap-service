package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhckjgqkb.entity.RepYwbbQhckjgqkb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行存款结构情况表
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IRepYwbbQhckjgqkbService extends IService<RepYwbbQhckjgqkb> {

    public void extract(String tjyf);
}

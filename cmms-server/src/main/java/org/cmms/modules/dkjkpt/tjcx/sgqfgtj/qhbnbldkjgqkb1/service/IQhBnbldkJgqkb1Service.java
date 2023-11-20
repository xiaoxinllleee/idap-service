package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb1.entity.QhBnbldkJgqkb1;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IQhBnbldkJgqkb1Service extends IService<QhBnbldkJgqkb1> {

    public void extract(String tjyf);
}

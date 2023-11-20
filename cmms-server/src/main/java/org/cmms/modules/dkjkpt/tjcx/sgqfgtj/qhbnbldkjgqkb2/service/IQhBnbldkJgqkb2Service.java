package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.qhbnbldkjgqkb2.entity.QhBnbldkJgqkb2;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 全行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IQhBnbldkJgqkb2Service extends IService<QhBnbldkJgqkb2> {
    public void extract(String tjyf);

}

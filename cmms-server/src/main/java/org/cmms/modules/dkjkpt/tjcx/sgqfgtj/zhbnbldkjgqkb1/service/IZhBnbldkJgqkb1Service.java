package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb1.entity.ZhBnbldkJgqkb1;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行表内不良贷款结构情况表1
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IZhBnbldkJgqkb1Service extends IService<ZhBnbldkJgqkb1> {
    public void extract(String tjyf);
}

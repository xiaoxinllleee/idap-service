package org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dkjkpt.tjcx.sgqfgtj.zhbnbldkjgqkb2.entity.ZhBnbldkJgqkb2;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行表内不良贷款结构情况表2
 * @Author: cmms
 * @Date:   2019-10-17
 * @Version: V1.0
 */
public interface IZhBnbldkJgqkb2Service extends IService<ZhBnbldkJgqkb2> {
    public void extract(String tjyf);
}

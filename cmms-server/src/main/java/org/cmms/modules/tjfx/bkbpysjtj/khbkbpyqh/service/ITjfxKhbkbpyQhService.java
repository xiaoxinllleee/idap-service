package org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.service;

import org.cmms.modules.tjfx.bkbpysjtj.khbkbpyqh.entity.TjfxKhbkbpyQh;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 客户背靠背评议_全行
 * @Author: jeecg-boot
 * @Date:   2020-08-14
 * @Version: V1.0
 */
public interface ITjfxKhbkbpyQhService extends IService<TjfxKhbkbpyQh> {
    public void extract(String tjyf);

}

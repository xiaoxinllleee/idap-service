package org.cmms.modules.tjbb.ckywfx.ckjgfxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.ckjgfxb.entity.Ckjgfxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface ICkjgfxbService extends IService<Ckjgfxb> {
    void pCkjgfx(String tjrq);
}

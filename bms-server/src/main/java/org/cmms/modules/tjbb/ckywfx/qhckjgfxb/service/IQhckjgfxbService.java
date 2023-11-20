package org.cmms.modules.tjbb.ckywfx.qhckjgfxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.qhckjgfxb.entity.Qhckjgfxb;

/**
 * @Description: 全行存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IQhckjgfxbService extends IService<Qhckjgfxb> {
    void pQhckjgfxb(String tjrq);
}

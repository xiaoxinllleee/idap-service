package org.cmms.modules.tjbb.dkywfx.dkjgfxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkjgfxb.entity.Dkjgfxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDkjgfxbService extends IService<Dkjgfxb> {
    void pDkjgfxb(String tjrq);
}

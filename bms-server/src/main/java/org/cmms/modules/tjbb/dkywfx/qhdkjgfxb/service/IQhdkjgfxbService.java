package org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.qhdkjgfxb.entity.Qhdkjgfxb;

/**
 * @Description: 全行贷款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-10-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IQhdkjgfxbService extends IService<Qhdkjgfxb> {
    void pDkjgfxb(String tjrq);
}

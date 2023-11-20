package org.cmms.modules.tjbb.dkywfx.dkkhfxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.dkkhfxb.entity.Dkkhfxb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 贷款客户分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IDkkhfxbService extends IService<Dkkhfxb> {
    void pDkkhfxb(String tjyf);
}

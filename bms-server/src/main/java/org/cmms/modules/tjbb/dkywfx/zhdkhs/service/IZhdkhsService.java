package org.cmms.modules.tjbb.dkywfx.zhdkhs.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.dkywfx.zhdkhs.entity.Zhdkhs;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 支行贷款户数
 * @Author: jeecg-boot
 * @Date:   2021-08-23
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IZhdkhsService extends IService<Zhdkhs> {
    void pZhdkhs(String tjyf);
}

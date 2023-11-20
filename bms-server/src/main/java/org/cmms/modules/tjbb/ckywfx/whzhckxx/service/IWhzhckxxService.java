package org.cmms.modules.tjbb.ckywfx.whzhckxx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ckywfx.whzhckxx.entity.Whzhckxx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 外汇账户存款信息
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IWhzhckxxService extends IService<Whzhckxx> {
    void pWhzhckxx(String tjyf);
}

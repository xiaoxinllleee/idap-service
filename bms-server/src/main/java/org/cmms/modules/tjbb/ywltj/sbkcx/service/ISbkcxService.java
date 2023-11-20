package org.cmms.modules.tjbb.ywltj.sbkcx.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.sbkcx.entity.Sbkcx;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 社保卡查询
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface ISbkcxService extends IService<Sbkcx> {
    void pSbkcx(String tjyf);
}

package org.cmms.modules.tjbb.ywltj.kqzzzsbywl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.kqzzzsbywl.entity.Kqzzzsbywl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 卡前置自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IKqzzzsbywlService extends IService<Kqzzzsbywl> {
    void pKqzzzsbywl(String tjwd, String tjyf);
}

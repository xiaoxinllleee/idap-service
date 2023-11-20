package org.cmms.modules.tjbb.ywltj.zzsbywl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.tjbb.ywltj.zzsbywl.entity.Zzsbywl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-24
 * @Version: V1.0
 */
@DS("tjbb")//tjbb
public interface IZzsbywlService extends IService<Zzsbywl> {
    void pZzsbywl(String tjwd, String tjyf);
}

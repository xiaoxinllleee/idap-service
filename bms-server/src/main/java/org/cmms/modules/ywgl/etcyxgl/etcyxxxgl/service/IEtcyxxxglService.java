package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.Etcyxxxgl;

import java.util.Date;

/**
 * @Description: ETC营销信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IEtcyxxxglService extends IService<Etcyxxxgl> {
    Date getMaxTjyf();

    boolean updateNewTrans(Etcyxxxgl entity, Wrapper<Etcyxxxgl> updateWrapper);
}

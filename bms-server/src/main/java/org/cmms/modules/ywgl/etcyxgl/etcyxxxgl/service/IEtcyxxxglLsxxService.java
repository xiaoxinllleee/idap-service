package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglLsxx;

import java.util.List;

/**
 * @Description: ETC营销信息管理历史信息
 * @Author: jeecg-boot
 * @Date:   2021-12-23
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IEtcyxxxglLsxxService extends IService<EtcyxxxglLsxx> {
    boolean saveBatchNewTrans(List<EtcyxxxglLsxx> list);
}

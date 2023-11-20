package org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.etcyxgl.etcyxxxgl.entity.EtcyxxxglSpxx;

import java.util.List;

/**
 * @Description: ETC营销管理审批信息
 * @Author: jeecg-boot
 * @Date:   2021-12-22
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IEtcyxxxglSpxxService extends IService<EtcyxxxglSpxx> {
    boolean saveBatchNewTrans(List<EtcyxxxglSpxx> list);

    List<EtcyxxxglSpxx> listNewTrans(Wrapper<EtcyxxxglSpxx> queryWrapper);

    boolean updateNewTrans(EtcyxxxglSpxx etcyxxxglSpxx, Wrapper<EtcyxxxglSpxx> updateWrapper);
}

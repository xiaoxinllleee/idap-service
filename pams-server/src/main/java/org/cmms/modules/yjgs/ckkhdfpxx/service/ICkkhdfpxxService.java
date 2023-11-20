package org.cmms.modules.yjgs.ckkhdfpxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yjgs.ckkhdfpxx.entity.Ckkhdfpxx;

import java.util.List;

/**
 * @Description: 待认领存款客户信息
 * @Author: jeecg-boot
 * @Date:   2023-03-30
 * @Version: V1.0
 */
public interface ICkkhdfpxxService extends IService<Ckkhdfpxx> {
    public void init();

    public void savaCkkhghxx(String id);

    List<Ckkhdfpxx> getListByIds(List<String> ids);

    void deleteSptgsj(String tableId);
}

package org.cmms.modules.yjgs.ckzhdrlxx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.yjgs.ckzhdrlxx.entity.Ckzhdrlxx;

import java.util.List;

/**
 * @Description: 存款账号待认领
 * @Author: jeecg-boot
 * @Date:   2023-04-10
 * @Version: V1.0
 */
public interface ICkzhdrlxxService extends IService<Ckzhdrlxx> {
    public void init();

    List<Ckzhdrlxx> getListByIds(List<String> ids);

    void deleteSptgsj(String tableId);
}

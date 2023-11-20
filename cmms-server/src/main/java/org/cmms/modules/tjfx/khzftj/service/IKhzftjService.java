package org.cmms.modules.tjfx.khzftj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.tjfx.khzftj.entity.Khzftj;

/**
 * @Description: 客户走访统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
public interface IKhzftjService extends IService<Khzftj> {

    public void extract(String tjrq);

}

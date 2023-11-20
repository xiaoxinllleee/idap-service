package org.cmms.modules.dklldj.tjfxgl.qylldb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.dklldj.tjfxgl.qylldb.entity.Qylldb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 签约利率对比
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@DS("rate") // rate
public interface IQylldbService extends IService<Qylldb> {

    void init(Map<String, String> sql);

}

package org.cmms.modules.gzap.gzrl.service;

import org.cmms.modules.tjfx.zhcdksjmx.entity.Zhcksjmx;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 支行存款数据明细
 * @Author: cmms
 * @Date:   2019-09-17
 * @Version: V1.0
 */
public interface IGzrlService extends IService<Zhcksjmx> {
    List<Map> getgzrlxx(String dx);
}

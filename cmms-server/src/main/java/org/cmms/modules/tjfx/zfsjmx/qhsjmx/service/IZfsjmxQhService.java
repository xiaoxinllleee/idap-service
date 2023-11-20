package org.cmms.modules.tjfx.zfsjmx.qhsjmx.service;

import org.cmms.modules.tjfx.zfsjmx.qhsjmx.entity.ZfsjmxQh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 全行走访数据明细统计
 * @Author: jeecg-boot
 * @Date:   2020-03-19
 * @Version: V1.0
 */
public interface IZfsjmxQhService extends IService<ZfsjmxQh> {

    void InitDataToQh(Map<String, String> sql);

}

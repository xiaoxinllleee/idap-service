package org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.service;

import org.cmms.modules.tjfx.shzftj.shzfsjmx_qh.entity.Tjfx_shzfsjmx_qh;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @Description: 商户走访数据明细统计_全行
 * @Author: jeecg-boot
 * @Date:   2020-11-04
 * @Version: V1.0
 */
public interface ITjfx_shzfsjmx_qhService extends IService<Tjfx_shzfsjmx_qh> {

    void InitDataToQh(Map<String, String> sql);

}

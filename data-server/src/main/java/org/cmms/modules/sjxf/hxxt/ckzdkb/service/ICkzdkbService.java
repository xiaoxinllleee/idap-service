package org.cmms.modules.sjxf.hxxt.ckzdkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.sjxf.hxxt.ckzdkb.entity.Ckzdkb;

/**
 * @Description: 存款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@DS("sjxf") //sjxf
public interface ICkzdkbService extends IService<Ckzdkb> {

    /**
     * 获取存款主表最新数据日期（Hive）
     * 浏阳
     *
     * @return
     */
    String queryMaxDataDate();

    /**
     * 获取存款主表最新数据日期的前一天（Oracle）
     * 洪江贷款利率定价
     *
     * @return 例：`XX`170717
     */
    @DS("eweb")
    String getMaxLoadDate();
}

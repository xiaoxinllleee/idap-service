package org.cmms.modules.sjxf.hxxt.dkzdkb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.hxxt.dkzdkb.entity.Dkzdkb;

import java.util.Date;

/**
 * @Description: 贷款主档宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@DS("sjxf")
public interface IDkzdkbService extends IService<Dkzdkb> {

    /**
     * 获取贷款主表最新数据日期（Hive）
     * 浏阳
     *
     * @return
     */
    String queryMaxDataDate();

    /**
     * 查询客户的贷款余额
     *
     * @param zjhm
     * @return
     */
    double queryDkye(@Param("zjhm") String zjhm);

}

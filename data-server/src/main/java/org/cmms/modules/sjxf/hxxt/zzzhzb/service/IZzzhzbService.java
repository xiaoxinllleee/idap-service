package org.cmms.modules.sjxf.hxxt.zzzhzb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.hxxt.zzzhzb.entity.Zzzhzb;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 总账账户主表
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@DS("sjxf")
public interface IZzzhzbService extends IService<Zzzhzb> {

    /**
     * 通过存款账号获取总账账户主表信息
     *
     * @param ckzh
     * @return
     */
    @DS("eweb")
    Zzzhzb queryZzzhzbOracle(@Param("ckzh") String ckzh);

    /**
     * 通过存款账号获取总账账户主表信息
     *
     * @param ckzh
     * @return
     */
    Zzzhzb queryZzzhzbHive(@Param("ckzh") String ckzh);
}

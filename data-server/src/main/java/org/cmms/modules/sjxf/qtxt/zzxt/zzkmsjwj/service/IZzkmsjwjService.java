package org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.sjxf.qtxt.zzxt.zzkmsjwj.entity.Zzkmsjwj;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 总帐科目数据文件
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@DS("sjxf")
public interface IZzkmsjwjService extends IService<Zzkmsjwj> {

    /**
     * 获取总账科目表最大数据日期
     * Impala
     *
     * @return
     */
    String getMaxDataDateImpala();

    /**
     * 获取总账科目表最大数据日期
     * Oracle
     *
     * @return
     */
    // String getMaxDataDateOracle();

}

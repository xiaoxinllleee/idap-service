package org.cmms.modules.sjxf.qtxt.khxxst.khxxb.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.sjxf.qtxt.khxxst.khxxb.entity.Khxxb;

/**
 * @Description: 客户信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@DS("sjxf")
public interface IKhxxbService extends IService<Khxxb> {

    /**
     * 获取被调查人姓名及所属机构代码-大数据
     *
     * @param zjhm
     * @return
     */
    Khxxb getKhxxbHive(@Param("zjhm") String zjhm);

    /**
     * 获取被调查人姓名及所属机构代码-oracle
     *
     * @param zjhm
     * @return
     */
    Khxxb getKhxxbOracle(@Param("zjhm") String zjhm);

}

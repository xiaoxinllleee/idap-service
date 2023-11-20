package org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.xyjlcx.jcsjgl.dkkhglrgl.entity.Dkkhglrgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 贷款客户关联人管理
 * @Author: jeecg-boot
 * @Date:   2021-08-10
 * @Version: V1.0
 */
@DS("zx") // zx
public interface IDkkhglrglService extends IService<Dkkhglrgl> {

    @DS("eweb")
    void pDkkhglrgl();

    List<Dkkhglrgl> QueryRelatedPartyInfoOne(String zjhm);

    List<Dkkhglrgl> QueryRelatedPartyInfoTwo(String zjhm);

    List<Dkkhglrgl> queryRelatedPersonInfo(String zjhm);

}

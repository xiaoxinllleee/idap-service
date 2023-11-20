package org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.Dkhtsjgl;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;

import java.util.List;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2021-11-24
 * @Version: V1.0
 */
@DS("eweb")
public interface IDkhtsjglService extends IService<Dkhtsjgl> {
    void pDkhtsjgl();

    List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh);
}

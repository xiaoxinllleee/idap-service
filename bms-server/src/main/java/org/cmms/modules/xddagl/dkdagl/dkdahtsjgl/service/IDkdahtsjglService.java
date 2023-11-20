package org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.service;

import org.cmms.modules.xddagl.dkdagl.dkdahtsjgl.entity.Dkdahtsjgl;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.xddaglxt.dksjgl.dkhtsjgl.entity.HrBasStaffPostVo;

import java.util.List;

/**
 * @Description: 贷款合同数据管理
 * @Author: jeecg-boot
 * @Date:   2022-01-07
 * @Version: V1.0
 */
public interface IDkdahtsjglService extends IService<Dkdahtsjgl> {
    void pDkhtsjgl();

    List<HrBasStaffPostVo> getListClaim(String ywjgdm, String rglx, String gwbz, String khjlbz, String yggh);
}

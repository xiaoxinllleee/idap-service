package org.cmms.modules.ywgl.ywl.ywltz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.ywgl.ywl.ywltz.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.ywl.ywltz.entity.Ywltz;

import java.util.List;

/**
 * @Description: 业务量调整
 * @Author: jeecg-boot
 * @Date:   2021-09-29
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IYwltzService extends IService<Ywltz> {
    List<HrBasStaffPostVo> getListClaim(String ywjgdm,String rglx,String gwbz,String khjlbz,String yggh);
}

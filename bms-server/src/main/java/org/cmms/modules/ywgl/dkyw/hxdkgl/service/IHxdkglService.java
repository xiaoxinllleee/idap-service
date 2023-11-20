package org.cmms.modules.ywgl.dkyw.hxdkgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.HrBasStaffPostVo;
import org.cmms.modules.ywgl.dkyw.hxdkgl.entity.Hxdkgl;

import java.util.List;

/**
 * @Description: 核销贷款管理
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@DS("cdkyw")
public interface IHxdkglService extends IService<Hxdkgl> {
    List<HrBasStaffPostVo> getListClaim(String gyh, String khjlbz, String yggh);
}

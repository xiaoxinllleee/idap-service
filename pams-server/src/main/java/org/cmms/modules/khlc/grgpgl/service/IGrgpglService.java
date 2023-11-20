package org.cmms.modules.khlc.grgpgl.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasStaffPostVo;
import org.cmms.modules.khlc.grgpgl.entity.Grgpgl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 个人挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
@DS("eweb")
public interface IGrgpglService extends IService<Grgpgl> {
    List<HrBasStaffPostVo> getListClaim(String zzbz,String yggh,String ygxm);
}

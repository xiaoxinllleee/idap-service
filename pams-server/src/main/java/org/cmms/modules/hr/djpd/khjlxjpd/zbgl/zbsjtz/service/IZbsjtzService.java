package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdsjxgl.entity.Pdsjxgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 指标数据调整
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
@DS("eweb")
public interface IZbsjtzService extends IService<Zbsjtz> {

    List<Khjljcsjsz> getListClaim(String pdzq, String pdrq,
                                  String zzbz, String yggh,
                                  String khjlbz, String ygxm);
    List<Pdsjxgl> getListZbid(String sjxid, String sjxmc,String sjxwd);
}

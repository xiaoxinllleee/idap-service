package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.djpd.khjlxjpd.csgl.pdsjxgl.entity.Pdsjxgl;
import org.cmms.modules.hr.djpd.khjlxjpd.jcsjgl.khjljcsjsz.entity.Khjljcsjsz;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjtz.entity.Zbsjtz;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标数据调整
 * @Author: jeecg-boot
 * @Date:   2021-09-14
 * @Version: V1.0
 */
public interface ZbsjtzMapper extends BaseMapper<Zbsjtz> {

    List<Khjljcsjsz> getListClaim(@Param("pdzq")String pdzq, @Param("pdrq")String pdrq,
                                  @Param("zzbz")String zzbz, @Param("yggh")String yggh,
                                  @Param("khjlbz")String khjlbz,@Param("ygxm")String ygxm);

List<Pdsjxgl> getListZbid(@Param("sjxid")String sjxid, @Param("sjxmc")String sjxmc, @Param("sjxwd")String sjxwd);

}

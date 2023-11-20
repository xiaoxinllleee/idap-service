package org.cmms.modules.khlc.bmgpgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.khlc.bmgpgl.entity.Bmgpgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khlc.bmgpgl.entity.HrPostBmxx;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;

/**
 * @Description: 部门挂片管理
 * @Author: jeecg-boot
 * @Date:   2023-03-09
 * @Version: V1.0
 */
public interface BmgpglMapper extends BaseMapper<Bmgpgl> {
    @Select("select t.zzbz,t.zzjc from Hr_bas_organization t where t.zzlb = 3")
    List<HrPostBmxx> bmxx();
}

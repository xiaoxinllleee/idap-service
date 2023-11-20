package org.cmms.modules.khlc.khfagl.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;

/**
 * @Description: 按量计酬考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
public interface ErpAssessAljcMapper extends BaseMapper<ErpAssessAljc> {

    @Select("select t.* from hr_bas_post t ")
    List<HrPostOrg> khdx();
    //area岗位
    List<ErpBasSjxArea> area(@Param("zbid")String zbid);
    //机构
    List<ErpBasSjxArea> areaJg(@Param("zbid")String zbid);

    List<ErpAssessAljc> getZbxxBySchemeId(@Param("schemeId") String schemeId);
}

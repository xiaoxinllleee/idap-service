package org.cmms.modules.khlc.khfagl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.zzgl.gwxxgl.entity.HrBasPost;
import org.cmms.modules.khlc.jczbgl.entity.HrPostOrg;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessAljc;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;

import java.util.List;

/**
 * @Description: 按量计酬考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
public interface IErpAssessAljcService extends IService<ErpAssessAljc> {
    List<HrPostOrg> khdx();

    List<ErpBasSjxArea> area( String zbid);
    List<ErpBasSjxArea> areaJg( String zbid);

    List<ErpAssessAljc> getZbxxBySchemeId(@Param("schemeId") String schemeId);
}

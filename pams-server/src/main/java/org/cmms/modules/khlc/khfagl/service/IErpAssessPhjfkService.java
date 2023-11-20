package org.cmms.modules.khlc.khfagl.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessPhjfk;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;

import java.util.List;

/**
 * @Description: 平衡积分卡考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
public interface IErpAssessPhjfkService extends IService<ErpAssessPhjfk> {
    List<ErpBasSjxArea> phjfkGw(String zbid);
    List<ErpBasSjxArea> phjfkJg( String zbid);

    List<ErpAssessPhjfk> getZbxxBySchemeId(String schemeId);
}

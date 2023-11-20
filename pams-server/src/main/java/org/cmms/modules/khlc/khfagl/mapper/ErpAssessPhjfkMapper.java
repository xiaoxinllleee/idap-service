package org.cmms.modules.khlc.khfagl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.khlc.khfagl.entity.ErpAssessPhjfk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;

/**
 * @Description: 平衡积分卡考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
public interface ErpAssessPhjfkMapper extends BaseMapper<ErpAssessPhjfk> {

    //area岗位
    List<ErpBasSjxArea> phjfkGw(@Param("zbid")String zbid);
    //机构
    List<ErpBasSjxArea> phjfkJg(@Param("zbid")String zbid);

    List<ErpAssessPhjfk> getZbxxBySchemeId(@Param("schemeId") String schemeId);
}

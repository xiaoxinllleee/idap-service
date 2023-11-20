package org.cmms.modules.sjbl.farwsz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.sjbl.farwsz.entity.ErpAssessTaskset;

/**
 * @Description: 方案任务设置
 * @Author: jeecg-boot
 * @Date:   2023-03-22
 * @Version: V1.0
 */
public interface ErpAssessTasksetMapper extends BaseMapper<ErpAssessTaskset> {
    //机构
    List<ErpBasSjxArea> tasksetJg(@Param("zbid")String zbid);
    //岗位
    List<ErpBasSjxArea> tasksetGw(@Param("zbid")String zbid);
}

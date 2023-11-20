package org.cmms.modules.sjbl.farwsz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.khlc.zbljgl.entity.ErpBasSjxArea;
import org.cmms.modules.sjbl.farwsz.entity.ErpAssessTaskset;

import java.util.List;

/**
 * @Description: 方案任务设置
 * @Author: jeecg-boot
 * @Date:   2023-03-22
 * @Version: V1.0
 */
public interface IErpAssessTasksetService extends IService<ErpAssessTaskset> {
    List<ErpBasSjxArea> tasksetJg(String zbid);
    List<ErpBasSjxArea> tasksetGw(String zbid);
}

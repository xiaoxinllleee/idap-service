package org.cmms.modules.report.bbgl.bbmbgl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.entity.BbmbglVo;

import java.util.List;

/**
 * @Description: 报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
public interface IBbmbglService extends IService<Bbmbgl> {
    List<BbmbglVo> getBbmbsjList(String bbyf, BbmbglVo bbmbglVo);
}

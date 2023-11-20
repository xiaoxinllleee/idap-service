package org.cmms.modules.report.bbgl.bbmbgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.report.bbgl.bbmbgl.entity.Bbmbgl;
import org.cmms.modules.report.bbgl.bbmbgl.entity.BbmbglVo;

/**
 * @Description: 报表模板管理
 * @Author: jeecg-boot
 * @Date:   2022-03-22
 * @Version: V1.0
 */
public interface BbmbglMapper extends BaseMapper<Bbmbgl> {
    List<BbmbglVo> getBbmbsjList(@Param("bbyf") String bbyf, @Param("bbmbglVo") BbmbglVo bbmbglVo);

}

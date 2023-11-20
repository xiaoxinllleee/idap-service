package org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.hr.djpd.khjlxjpd.zbgl.zbsjgl.entity.Zbsjgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 指标数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-15
 * @Version: V1.0
 */
public interface ZbsjglMapper extends BaseMapper<Zbsjgl> {
    void pZbsjgl(@Param("pdzq")String pdzq, @Param("pdrq")String pdrq);
}

package org.cmms.modules.xdgl.grdkgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.xdgl.grdkgl.entity.Grdkgl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @Description: 个人贷款
 * @Author: jeecg-boot
 * @Date:   2020-08-15
 * @Version: V1.0
 */
@Component
public interface GrdkglMapper extends BaseMapper<Grdkgl> {

    /**
     * 根据"HHBM"提取"与我行业务往来信息"
     * @param hhbm
     */
    void CountYwhywxxDataByHhbm(@Param("hhbm") String hhbm);

}

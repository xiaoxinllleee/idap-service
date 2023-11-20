package org.cmms.modules.ckjkpt.csgl.cssz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.ckjkpt.csgl.cssz.entity.CkjkptCsgl;

/**
 * @Description: 1
 * @Author: cmms
 * @Date:   2019-10-10
 * @Version: V1.0
 */
public interface CkjkptCsglMapper extends BaseMapper<CkjkptCsgl> {

    /**
     * 通过参数编码获取参数值
     * @param csbm
     * @return
     */
    String queryParamValue(@Param("csbm") String csbm);

}

package org.cmms.modules.jylrhs.csgl.zbk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.system.model.DuplicateCheckVo;

/**
 * @Description: 经营利润核算（指标库）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
public interface JylrhsZbkMapper extends BaseMapper<JylrhsZbk> {

    Long duplicateCheckCount(@Param("id") String id);

}

package org.cmms.modules.jylrhs.csgl.zbk.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.jylrhs.csgl.zbk.entity.JylrhsZbk;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 经营利润核算（指标库）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@DS("jylrhs")
public interface IJylrhsZbkService extends IService<JylrhsZbk> {

    Long duplicateCheckCount(String id);

}

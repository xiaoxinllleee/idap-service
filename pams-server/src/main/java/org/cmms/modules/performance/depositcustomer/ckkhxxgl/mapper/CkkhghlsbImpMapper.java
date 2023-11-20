package org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.CkkhghlsbImp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.security.core.parameters.P;

/**
 * @Description: 存款客户管户历史表（数据导入临时表）
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
public interface CkkhghlsbImpMapper extends BaseMapper<CkkhghlsbImp> {

    void pCkkhGhxxImp(@Param("username") String username);

}

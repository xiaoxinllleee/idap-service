package org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量存款客户信息
 * @Author: penghr
 * @Date:   2023-04-11
 * @Version: V1.0
 */
public interface ClckkhxxMapper extends BaseMapper<Clckkhxx> {

    void pRlckkhxxImp(@Param("username") String username);

}

package org.cmms.modules.performance.loancustomer.dkhtzhxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.loancustomer.dkhtzhxx.vo.CldkkhxxImp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存量贷款客户信息导入
 * @Author: jeecg-boot
 * @Date:   2023-04-12
 * @Version: V1.0
 */
public interface CldkkhxxImpMapper extends BaseMapper<CldkkhxxImp> {

    void pRldkkhxxImp(@Param("username") String username);

}

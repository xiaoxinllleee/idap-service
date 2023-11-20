package org.cmms.modules.performance.depositcustomer.ckkhxxgl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.entity.Ckkhxxgl;
import org.cmms.modules.performance.depositcustomer.ckkhxxgl.vo.Clckkhxx;

/**
 * @Description: 存款客户信息管理
 * @Author: jeecg-boot
 * @Date:   2023-02-28
 * @Version: V1.0
 */
public interface CkkhxxglMapper extends BaseMapper<Ckkhxxgl> {
    void init();

    void rlckkhxx(@Param("jgdm") String jgdm);

}

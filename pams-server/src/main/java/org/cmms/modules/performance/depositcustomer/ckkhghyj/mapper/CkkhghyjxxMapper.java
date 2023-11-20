package org.cmms.modules.performance.depositcustomer.ckkhghyj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 存款客户管户移交
 * @Author: Penghr
 * @Date:   2021-03-25
 * @Version: V1.0
 */
public interface CkkhghyjxxMapper extends BaseMapper<Ckkhghyjxx> {

    String getProcessIdByProcessKey(@Param("processkey") String processKey);

    List<Ckkhghyjxx> getListByIds(@Param("ids") List<String> ids);
}

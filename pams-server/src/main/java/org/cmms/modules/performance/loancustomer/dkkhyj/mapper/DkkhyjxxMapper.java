package org.cmms.modules.performance.loancustomer.dkkhyj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.performance.depositcustomer.ckkhghyj.entity.Ckkhghyjxx;
import org.cmms.modules.performance.loancustomer.dkkhyj.entity.Dkkhyjxx;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 贷款客户移交
 * @Author: jeecg-boot
 * @Date:   2023-04-03
 * @Version: V1.0
 */
public interface DkkhyjxxMapper extends BaseMapper<Dkkhyjxx> {
    String getProcessIdByProcessKey(@Param("processkey") String processKey);

    List<Dkkhyjxx> getListByIds(@Param("ids") List<String> ids);

}

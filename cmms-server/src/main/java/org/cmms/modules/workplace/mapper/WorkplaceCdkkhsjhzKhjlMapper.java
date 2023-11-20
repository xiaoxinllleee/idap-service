package org.cmms.modules.workplace.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.workplace.entity.WorkplaceCdkkhsjhzKhjl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 工作台-存贷款数据汇总-客户经理
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
public interface WorkplaceCdkkhsjhzKhjlMapper extends BaseMapper<WorkplaceCdkkhsjhzKhjl> {

    Map<String,Object> getCdkkhsjForKhjlByYggh(@Param("yggh") String yggh);

}

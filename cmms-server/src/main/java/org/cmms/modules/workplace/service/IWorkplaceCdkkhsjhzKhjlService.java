package org.cmms.modules.workplace.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.workplace.entity.WorkplaceCdkkhsjhzKhjl;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 工作台-存贷款数据汇总-客户经理
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
public interface IWorkplaceCdkkhsjhzKhjlService extends IService<WorkplaceCdkkhsjhzKhjl> {

    Map<String,Object> getCdkkhsjForKhjlByYggh(@Param("yggh") String yggh);

}

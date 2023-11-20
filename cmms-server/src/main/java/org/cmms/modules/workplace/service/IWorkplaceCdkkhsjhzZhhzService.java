package org.cmms.modules.workplace.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.workplace.entity.WorkplaceCdkkhsjhzZhhz;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 工作台-存贷款数据汇总-支行行长
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
public interface IWorkplaceCdkkhsjhzZhhzService extends IService<WorkplaceCdkkhsjhzZhhz> {

    Map<String,Object> getCdkkhsjForZhhzByZzbz(@Param("zzbz") String zzbz);

}

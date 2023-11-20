package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity.Dkyeb;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description: 贷款余额表
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@DS("dkjkpt")
public interface IDkyebService extends IService<Dkyeb> {

    List<Dkyeb> queryDhdksjmx(@Param("tjrq") String tjrq,
                              @Param("jgdm") String jgdm,
                              @Param("zjhm") String zjhm);

}

package org.cmms.modules.automission.execlog.service;

import org.apache.ibatis.annotations.Param;
import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.cmms.modules.automission.execlog.vo.ErpBasWyxcsszVO;

import java.util.Date;

/**
 * @Description: 大数据应用平台每日调度日志信息
 * @Author: jeecg-boot
 * @Date:   2021-12-31
 * @Version: V1.0
 */
public interface IBigdataAutoexecLogService extends IService<BigdataAutoexecLog> {

    String getAssessParamValue(@Param("paramcode") String paramcode);

    String conversionConfigInfo(@Param("beginOfMonth") Date beginOfMonth);

    String getSystemConfigParamValue(@Param("cfgcode") String cfgcode);

    String getSystemConfigParamValueNumber(@Param("cfgcode") String cfgcode);

    String getDailyLoanLimit(@Param("csbm") String csbm);

    String querySubjectNo(@Param("cfgcode") String cfgcode);

    ErpBasWyxcsszVO getOneErpBasWyxcssz(@Param("qybz") String qybz);

    String getMaxId();

    Integer judgeExtract();
}

package org.cmms.modules.automission.execlog.service.impl;

import org.cmms.modules.automission.execlog.entity.BigdataAutoexecLog;
import org.cmms.modules.automission.execlog.mapper.BigdataAutoexecLogMapper;
import org.cmms.modules.automission.execlog.service.IBigdataAutoexecLogService;
import org.cmms.modules.automission.execlog.vo.ErpBasWyxcsszVO;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 大数据应用平台每日调度日志信息
 * @Author: jeecg-boot
 * @Date:   2021-12-31
 * @Version: V1.0
 */
@Service
public class BigdataAutoexecLogServiceImpl extends ServiceImpl<BigdataAutoexecLogMapper, BigdataAutoexecLog> implements IBigdataAutoexecLogService {

    @Override
    public String conversionConfigInfo(Date beginOfMonth) {
        return baseMapper.conversionConfigInfo(beginOfMonth);
    }

    @Override
    public String getAssessParamValue(String paramcode) {
        return baseMapper.getAssessParamValue(paramcode);
    }

    @Override
    public String getSystemConfigParamValue(String cfgcode) {
        return baseMapper.getSystemConfigParamValue(cfgcode);
    }

    @Override
    public String getSystemConfigParamValueNumber(String cfgcode) {
        return baseMapper.getSystemConfigParamValueNumber(cfgcode);
    }

    @Override
    public String getDailyLoanLimit(String csbm) {
        return baseMapper.getDailyLoanLimit(csbm);
    }

    @Override
    public String querySubjectNo(String cfgcode) {
        return baseMapper.querySubjectNo(cfgcode);
    }

    @Override
    public ErpBasWyxcsszVO getOneErpBasWyxcssz(String qybz) {
        return baseMapper.getOneErpBasWyxcssz(qybz);
    }

    @Override
    public String getMaxId() {
        return baseMapper.getMaxId();
    }

    @Override
    public Integer judgeExtract() {
        return baseMapper.judgeExtract();
    }
}

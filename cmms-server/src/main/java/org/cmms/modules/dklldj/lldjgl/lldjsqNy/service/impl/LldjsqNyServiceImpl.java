package org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.cmms.modules.dklldj.lldjgl.entity.RateKhzbsjmx;
import org.cmms.modules.dklldj.lldjgl.entity.RateZhckrp;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.entity.RateDjsqxxNy;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.mapper.LldjsqNyMapper;
import org.cmms.modules.dklldj.lldjgl.lldjsqNy.service.ILldjsqNyService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 利率定价申请
 * @Author: jeecg-boot
 * @Date:   2020-04-03
 * @Version: V1.0
 */
@Slf4j
@Service
public class LldjsqNyServiceImpl extends ServiceImpl<LldjsqNyMapper, RateDjsqxxNy> implements ILldjsqNyService {

//    @Override
//    public BigDecimal queryColumnValue(String tablename, String columnname, String zjhm) {
//        return baseMapper.queryColumnValue(tablename,columnname,zjhm);
//    }

    @Override
    public BigDecimal querySnzxll(String tablename, String zjhm) {
        return baseMapper.querySnzxll(tablename, zjhm);
    }

    @Override
    public BigDecimal querySndkrp(String tablename, String zjhm) {
        return baseMapper.querySndkrp(tablename, zjhm);
    }

    @Override
    public BigDecimal queryCkrpyeSumGR(String tablename, String zjhm) {
        return baseMapper.queryCkrpyeSumGR(tablename,zjhm);
    }

    @Override
    public BigDecimal queryCkrpyeSumQY(String tablename, String zjhm) {
        return baseMapper.queryCkrpyeSumQY(tablename,zjhm);
    }

    @Override
    public void ExtractionGlzhxx(String zjhm) {}

    @Override
    public void ExtractionKhzbsj(String djnf, String zjhm) {
        baseMapper.ExtractionKhzbsj(djnf, zjhm);
    }

    @Override
    public void ExtractionJynrp(String djnf, String zjhm) {
        baseMapper.ExtractionJynrp(djnf, zjhm);
    }

    @Override
    public List<RateZhckrp> QueryKhZhCkrp(String tjlx, String djnf, String zjhm) {
        return baseMapper.QueryKhZhCkrp(tjlx,djnf,zjhm);
    }

    @Override
    public RateKhzbsjmx QueryKhZbsjmx(String djnf, String zjhm) {
        return baseMapper.QueryKhZbsjmx(djnf,zjhm);
    }
}

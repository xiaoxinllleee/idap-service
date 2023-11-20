package org.cmms.modules.ygjx.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cmms.common.constant.CacheConstant;
import org.cmms.common.util.RedisUtil;
import org.cmms.modules.gr.grjxsj.entity.ErpWageYgjbgzglYx;
import org.cmms.modules.ygjx.entity.*;
import org.cmms.modules.ygjx.mapper.ErpWageYgjxMapper;
import org.cmms.modules.ygjx.service.IErpWageYgjxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 员工绩效合计
 * @Author: jeecg-boot
 * @Date: 2022-02-28
 * @Version: V1.0
 */
@Service
@DS("eweb")
public class ErpWageYgjxServiceImpl extends ServiceImpl<ErpWageYgjxMapper, ErpWageYgjx> implements IErpWageYgjxService {

    @Autowired
    RedisUtil redisUtil;

    @Override
    @Cacheable(value = CacheConstant.QHRJ)
    public String getRj() {
        return baseMapper.getRj();
    }

    @Override
    @DS("idap")
    public JgDataVO jgIndex(String zzbz) {
        return baseMapper.jgIndex(zzbz);
    }

    @Override
    @DS("idap")
    public JgDataVO getZhrjZjj(String zzbz,String gzrq) {
        return baseMapper.getZhrjZjj(zzbz,gzrq);
    }

    @Override
    @DS("idap")
    public String getMaxJgDay() {
        return baseMapper.getMaxJgDay();
    }

    @Override
    @DS("idap")
    public List<ErpWageYgjx> getGzPmByZzbz(String zzbz) {
        return baseMapper.getGzPmByZzbz(zzbz);
    }

    @Override
    public List<ErpWageYgjx> getGzPmByZzbzJH(String zzbz) {
        return baseMapper.getGzPmByZzbzJH(zzbz);
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_CKHJ,key = "#yyMM+':'+#jgdm")
    public Integer getCkByJgdm(String jgdm, String yyMM) {
        yyMM = "ZMCBSINVMBASE"+yyMM;
        try {
            Integer ckByJgdm = baseMapper.getCkByJgdm(jgdm, yyMM);
            return ckByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_HQCKHJ,key = "#yyMM+':'+#jgdm")
    public Integer getHqCkByJgdm(String jgdm, String yyMM) {
        yyMM = "ZMCBSINVMBASE"+yyMM;
        try {
            Integer ckByJgdm = baseMapper.getHqCkByJgdm(jgdm, yyMM);
            return ckByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_DKHJ,key = "#yyMM+':'+#jgdm")
    public Integer getDkByJgdm(String jgdm, String yyMM) {
        yyMM = "ZMCBSBORMBASE"+yyMM;
        try {
            Integer dkByJgdm = baseMapper.getDkByJgdm(jgdm, yyMM);
            return dkByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_DKKHRS,key = "#yyMM+':'+#jgdm")
    public Map<String, BigDecimal> getDkKHByJgdm(String jgdm, String yyMM) {
        Map<String, BigDecimal> map = new HashMap<>();
        yyMM = "ZMCBSBORMBASE"+yyMM;
        try {
            return  baseMapper.getDkkhJgdm(jgdm, yyMM);
        }catch (Exception e){
            e.printStackTrace();
            return map;
        }
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_BNBLDK,key = "#yyMM+':'+#jgdm")
    public Integer getBnblByJgdm(String jgdm, String yyMM) {
        yyMM = "ZMCBSBORMBASE"+yyMM;
        try {
            Integer dkByJgdm = baseMapper.getBnblByJgdm(jgdm, yyMM);
            return dkByJgdm;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public ErpWageYgjbgzglYx getImpWage(String date, String yggh) {
        return baseMapper.getImpWage(date, yggh);
    }

    @Override
    public void updateYfgz(String yggh) {
        baseMapper.updateYfgz(yggh);
    }

    @Override
    public IPage<ImpWageVO> getImpWageList(Page page, String yggh) {
        return baseMapper.getImpWageList(page, yggh);
    }

    @Override
    @DS("idap")
    public String getMaxDate(String yggh) {
        return baseMapper.getMaxDate(yggh);
    }

    @Override
    public String getZjjDate() {
        return baseMapper.getZjjDate();
    }
    @Override
    public String getZjjDkDate() {
        return baseMapper.getZjjDkDate();
    }

    @Override
    public Integer getZjjDK(String tableName,String jgdm) {
        try {
            Integer zjjCK = baseMapper.getZjjDK(tableName,jgdm);
            return zjjCK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Integer getZjjCK(String tableName,String jgdm) {
        try {
            Integer zjjCK = baseMapper.getZjjCK(tableName, jgdm);
            return zjjCK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Integer getZjjDxye(String tableName,String jgdm ) {
        try {
            Integer zjjCK = baseMapper.getZjjDxye(tableName, jgdm);
            return zjjCK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Integer getZjjZczl(String tableName) {
        try {
            Integer zjjCK = baseMapper.getZjjZczl(tableName);
            return zjjCK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Integer getZjjZczlDkye(String tableName ) {
        try {
            Integer zjjCK = baseMapper.getZjjZczlDkye(tableName);
            return zjjCK;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public IPage<PrTbYgjbgzmxb> getJbgzHzDate(Page page, String nf, String zzbz , String tjyf) {
        return baseMapper.getJbgzHzDate(page,nf,zzbz,tjyf);
    }
    @Override
    public IPage<PrTbYgjbgzmxb> getJbgzDate(Page page, String nf, String yggh , String tjyf) {
        return baseMapper.getJbgzDate(page,nf,yggh,tjyf);
    }
    @Override
    public IPage<ErpBfgz> getBfgzDate(Page page, String nf, String yggh , String tjyf) {
        return baseMapper.getBfgzDate(page,nf,yggh,tjyf);
    }
    @Override
    public IPage<ErpBkgz> getBkgzDate(Page page, String nf, String yggh , String tjyf) {
        return baseMapper.getBkgzDate(page,nf,yggh,tjyf);
    }


    @Override
    @Cacheable(value = CacheConstant.ZH_CKHJ_TBJS,key = "#yyyyMMdd+':'+#jgdm")
    public Integer getTjbsCK(String jgdm, String yyyyMMdd) {
        return baseMapper.getTjbsCK(jgdm, yyyyMMdd);
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_DKHJ_TBJS,key = "#yyyyMMdd+':'+#jgdm")
    public Integer getTjbsDK(String jgdm, String yyyyMMdd) {
        return baseMapper.getTjbsDK(jgdm, yyyyMMdd);
    }

    @Override
    @Cacheable(value = CacheConstant.ZH_DXCKHJ_TBJS,key = "#yyyyMMdd+':'+#jgdm")
    public Integer getTjbsDxck(String jgdm, String yyyyMMdd) {
        return baseMapper.getTjbsDxck(jgdm, yyyyMMdd);
    }

    @Override
    public String getMaxJbgz(String yggh) {
        return baseMapper.getMaxJbgz(yggh);
    }

    @Override
    public String getMaxJbgzAndDate(String yggh, String yc, String ym) {
        return baseMapper.getMaxJbgzAndDate(yggh, yc, ym);
    }

    @Override
    public BigDecimal getJhJxgz(String yggh, String date) {
        return baseMapper.getJhJxgz(yggh,date);
    }

    @Override
    public List<String> zbids(String yggh, String date) {
        return baseMapper.zbids(yggh,date);
    }

    @Override
    public Map<String, BigDecimal> ckrpAndCkje(String table, String acctNo) {
        try {
            return baseMapper.ckrpAndCkje(table, acctNo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

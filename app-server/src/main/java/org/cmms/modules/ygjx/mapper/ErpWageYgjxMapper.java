package org.cmms.modules.ygjx.mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.cmms.modules.gr.grjxsj.entity.ErpWageYgjbgzglYx;
import org.cmms.modules.ygjx.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 员工绩效合计
 * @Author: jeecg-boot
 * @Date:   2022-02-28
 * @Version: V1.0
 */
public interface ErpWageYgjxMapper extends BaseMapper<ErpWageYgjx> {
    public String getRj();

    public JgDataVO jgIndex(String zzbz);

    public JgDataVO getZhrjZjj(String zzbz,String gzrq);

    @Select("select max(t.gzrq) from erp_wage_ygjx_mx t")
    public String getMaxJgDay();

    public List<ErpWageYgjx> getGzPmByZzbz(String zzbz);
    public List<ErpWageYgjx> getGzPmByZzbzJH(String zzbz);

    public Integer getCkByJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public Integer getHqCkByJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public Integer getDkByJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public Map<String,BigDecimal> getDkkhJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public Integer getBnblByJgdm(@Param("jgdm") String jgdm, @Param("yyMM") String yyMM);
    public ErpWageYgjbgzglYx getImpWage(@Param("date") String date, @Param("yggh") String yggh);
    public void updateYfgz(String yggh);
    public IPage<ImpWageVO> getImpWageList(Page page, @Param("yggh")String yggh);
    public String getMaxDate(String yggh);

    public String getZjjDate();

    @Select("select max(DATA_DATE -1) from CBS_BORM_BASE")
    public String getZjjDkDate();

    public Integer getTjbsCK(@Param("jgdm")String jgdm, @Param("yyyyMMdd") String yyyyMMdd);
    public Integer getTjbsDK(@Param("jgdm")String jgdm, @Param("yyyyMMdd") String yyyyMMdd);
    public Integer getTjbsDxck(@Param("jgdm")String jgdm, @Param("yyyyMMdd") String yyyyMMdd);

    public String getMaxJbgz(String yggh);
    public String getMaxJbgzAndDate(@Param("yggh")String yggh,@Param("yc") String yc,@Param("ym") String ym);


    public BigDecimal getJhJxgz(@Param("yggh")String yggh,@Param("gzrq") String date);

    List<String> zbids(@Param("yggh") String yggh,@Param("yymmdd") String yymmdd);


    public Integer getZjjDK( String tableName,String jgdm);

    public Integer getZjjCK( String tableName,String jgdm);

    public Integer getZjjDxye( String tableName ,String jgdm);

    public Integer getZjjZczl( String tableName );
    public Integer getZjjZczlDkye( String tableName);

    @Select("select t.* from Pr_Tb_Ygjbgzmxb t where trunc(t.tjyf,'YYYY')=trunc(to_date(#{nf},'yyyyMMdd'),'YYYY') and t.zzbz=#{zzbz} and t.tjyf != to_date(#{tjyf},'yyyyMMdd') order By tjyf Desc")
    public IPage<PrTbYgjbgzmxb> getJbgzHzDate(Page page, @Param("nf") String nf, @Param("zzbz") String zzbz, @Param("tjyf") String tjyf);

    @Select("select t.* from Pr_Tb_Ygjbgzmxb t where trunc(t.tjyf,'YYYY')=trunc(to_date(#{nf},'yyyyMMdd'),'YYYY') and t.yggh=#{yggh} and t.tjyf != to_date(#{tjyf},'yyyyMMdd') order By tjyf Desc")
    public IPage<PrTbYgjbgzmxb> getJbgzDate(Page page, @Param("nf") String nf, @Param("yggh") String yggh, @Param("tjyf") String tjyf);

    @Select("select t.* from erp_bfgz t where trunc(t.tjyf,'YYYY')=trunc(to_date(#{nf},'yyyyMMdd'),'YYYY') and t.yggh=#{yggh} and t.tjyf != to_date(#{tjyf},'yyyyMMdd') order By tjyf Desc")
    public IPage<ErpBfgz> getBfgzDate(Page page, @Param("nf") String nf, @Param("yggh") String yggh, @Param("tjyf") String tjyf);

    @Select("select t.* from erp_bkgz t where trunc(t.tjyf,'YYYY')=trunc(to_date(#{nf},'yyyyMMdd'),'YYYY') and t.yggh=#{yggh} and t.tjyf != to_date(#{tjyf},'yyyyMMdd') order By tjyf Desc")
    public IPage<ErpBkgz> getBkgzDate(Page page, @Param("nf") String nf, @Param("yggh") String yggh, @Param("tjyf") String tjyf);

    public Map<String, BigDecimal> ckrpAndCkje(@Param("table")String table,@Param("acctNo") String acctNo);
}

package org.cmms.modules.dklldj.lldjgl.lldjsq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.cmms.common.constant.RateConstant;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RateKhjbxxbVO {
    private String zzbz;
    /**证件号码*/
    @TableId(type = IdType.NONE)
    private String zjhm;
    /**客户名称*/
    private String khmc;
    /**客户类型*/
    private String khlx;
    /**法人代表*/
    private String frdb;
    /**删除标志*/
    private String scbz;
    /**录入时间*/
    private Date lrsj;
    /**录入操作员*/
    private String lrczy;
    /**录入标志（0、通过存储过程自动写入；1、操作员通过界面添加；2、操作员通过界面修改）*/
    private String lrbz;
    /**上年贷款授信额度*/
    private BigDecimal snsxed;
    /**上年贷款利率上浮幅度*/
    private BigDecimal sndkllsffd;
    /**上年执行利率*/
    private BigDecimal snzxll;
    /**综合授信额度*/
    private BigDecimal zhsxed;
    /**承兑敞口*/
    private BigDecimal cdck;

    /**贷款期限*/
    private Integer dkqx;
    /**是否便民卡*/
    private Integer sfbmk;
    /**是否保证保险贷款*/
    private Integer sfbzbxdk;
    /**是否享受`小微客户定价普惠措施`*/
    private Integer sfjzxkh;
    /**是否高危行业*/
    private Integer sfgwhy;

    /**农村三权抵（质）押贷款*/
    private java.lang.Integer ncsqdzydk;
    /**是否为花炮企业*/
    private java.lang.Integer sfhpqy;

    /**上年贷款基点(加/减)BP*/
    private BigDecimal sndkjdbp;

    private BigDecimal GZ00033;
    private BigDecimal GZ00034;
    private BigDecimal GZ00035;

    private BigDecimal khqsndyndqckrp;
    private BigDecimal khqsndendqckrp;
    private BigDecimal khqsndsndqckrp;

    private BigDecimal GZ00039;
    private String GZ00040 = RateConstant.GZ00040_NUMBER;
    private String GZ00050 = RateConstant.GZ00050_NUMBER;
    private String GZ00051 = RateConstant.GZ00051_NUMBER;
    private String GZ00052 = RateConstant.GZ00052_NUMBER;
    private String GZ00053 = RateConstant.GZ00053_NUMBER;

    @JsonProperty("GZ00033")
    public BigDecimal getGZ00033() {
        return GZ00033;
    }

    public void setGZ00033(BigDecimal GZ00033) {
        this.GZ00033 = GZ00033;
    }

    @JsonProperty("GZ00034")
    public BigDecimal getGZ00034() {
        return GZ00034;
    }

    public void setGZ00034(BigDecimal GZ00034) {
        this.GZ00034 = GZ00034;
    }

    @JsonProperty("GZ00035")
    public BigDecimal getGZ00035() {
        return GZ00035;
    }

    public void setGZ00035(BigDecimal GZ00035) {
        this.GZ00035 = GZ00035;
    }

    @JsonProperty("GZ00039")
    public BigDecimal getGZ00039() {
        return GZ00039;
    }

    public void setGZ00039(BigDecimal GZ00039) {
        this.GZ00039 = GZ00039;
    }

    @JsonProperty("GZ00040")
    public String getGZ00040() {
        return GZ00040;
    }

    public void setGZ00040(String GZ00040) {
        this.GZ00040 = GZ00040;
    }

    @JsonProperty("GZ00050")
    public String getGZ00050() {
        return GZ00050;
    }

    public void setGZ00050(String GZ00050) {
        this.GZ00050 = GZ00050;
    }

    @JsonProperty("GZ00051")
    public String getGZ00051() {
        return GZ00051;
    }

    public void setGZ00051(String GZ00051) {
        this.GZ00051 = GZ00051;
    }

    @JsonProperty("GZ00052")
    public String getGZ00052() {
        return GZ00052;
    }

    public void setGZ00052(String GZ00052) {
        this.GZ00052 = GZ00052;
    }

    @JsonProperty("GZ00053")
    public String getGZ00053() {
        return GZ00053;
    }

    public void setGZ00053(String GZ00053) {
        this.GZ00053 = GZ00053;
    }

   /* public String getZzbz() {
        return zzbz;
    }

    public void setZzbz(String zzbz) {
        this.zzbz = zzbz;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

    public String getFrdb() {
        return frdb;
    }

    public void setFrdb(String frdb) {
        this.frdb = frdb;
    }

    public String getScbz() {
        return scbz;
    }

    public void setScbz(String scbz) {
        this.scbz = scbz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrczy() {
        return lrczy;
    }

    public void setLrczy(String lrczy) {
        this.lrczy = lrczy;
    }

    public String getLrbz() {
        return lrbz;
    }

    public void setLrbz(String lrbz) {
        this.lrbz = lrbz;
    }

    @JsonProperty("GZ00033")
    public String getGZ00033() {
        return GZ00033;
    }

    public void setGZ00033(String GZ00033) {
        this.GZ00033 = GZ00033;
    }

    @JsonProperty("GZ00034")
    public String getGZ00034() {
        return GZ00034;
    }

    public void setGZ00034(String GZ00034) {
        this.GZ00034 = GZ00034;
    }

    @JsonProperty("GZ00035")
    public String getGZ00035() {
        return GZ00035;
    }

    public void setGZ00035(String GZ00035) {
        this.GZ00035 = GZ00035;
    }

    @JsonProperty("GZ00040")
    public String getGZ00040() {
        return GZ00040;
    }

    public void setGZ00040(String GZ00040) {
        this.GZ00040 = GZ00040;
    }

    @JsonProperty("GZ00050")
    public String getGZ00050() {
        return GZ00050;
    }

    public void setGZ00050(String GZ00050) {
        this.GZ00050 = GZ00050;
    }

    @JsonProperty("GZ00051")
    public String getGZ00051() {
        return GZ00051;
    }

    public void setGZ00051(String GZ00051) {
        this.GZ00051 = GZ00051;
    }

    @JsonProperty("GZ00052")
    public String getGZ00052() {
        return GZ00052;
    }

    public void setGZ00052(String GZ00052) {
        this.GZ00052 = GZ00052;
    }

    @JsonProperty("GZ00053")
    public String getGZ00053() {
        return GZ00053;
    }

    public void setGZ00053(String GZ00053) {
        this.GZ00053 = GZ00053;
    }*/
}

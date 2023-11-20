package org.cmms.modules.pad.qtzrrxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class QtzrrJtcyxx {

    /**系统评定说明*/
    @Excel(name = "系统评定说明", width = 15)
    @ApiModelProperty(value = "系统评定说明")
    private String xtpdsm;
    /**修改时间*/
    @Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date xgsj;
    /**修改人*/
    @Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private String xgr;
    /**是否吸毒人员*/
    @Excel(name = "是否吸毒人员", width = 15)
    @ApiModelProperty(value = "是否吸毒人员")
    private String sfxdry;
    /**是否贫困户*/
    @Excel(name = "是否贫困户", width = 15)
    @ApiModelProperty(value = "是否贫困户")
    private String sfpkh;
    /**是否低保*/
    @Excel(name = "是否低保", width = 15)
    @ApiModelProperty(value = "是否低保")
    private String sfdb;
    /**从事职业*/
    @Excel(name = "从事职业", width = 15)
    @ApiModelProperty(value = "从事职业")
    private String cszy;
    /**是否公职人员*/
    @Excel(name = "是否公职人员", width = 15)
    @ApiModelProperty(value = "是否公职人员")
    private String sfgzry;
    /**是否非法集资*/
    @Excel(name = "是否非法集资", width = 15)
    @ApiModelProperty(value = "是否非法集资")
    private String sfffjz;
    /**患病记录*/
    @Excel(name = "患病记录", width = 15)
    @ApiModelProperty(value = "患病记录")
    private String hbjl;
    /**建档完整度*/
    @Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
    private BigDecimal infoRate;
    /**出生年月*/
    @Excel(name = "出生年月", width = 15)
    @ApiModelProperty(value = "出生年月")
    private String csny;
    /**是否领取社保卡（1：是 2：否）*/
    @Excel(name = "是否领取社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否领取社保卡（1：是 2：否）")
    private String sflqsbk;
    /**是否开通社保卡（1：是 2：否）*/
    @Excel(name = "是否开通社保卡（1：是 2：否）", width = 15)
    @ApiModelProperty(value = "是否开通社保卡（1：是 2：否）")
    private String sfktsbk;
    /**系统评定结果（1：灰名单 2：白名单 3：黑名单）*/
    @Excel(name = "系统评定结果（1：灰名单 2：白名单 3：黑名单）", width = 15)
    @ApiModelProperty(value = "系统评定结果（1：灰名单 2：白名单 3：黑名单）")
    private Integer xtpdjg;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
    private String jgdm;
    /**id*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private String id;
    /**所属支行*/
    @Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
    private String sszh;
    /**所属营销单元*/
    @Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
    private String ssyxdy;
    /**户号编码*/
    @Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
    private String hhbm;
    /**与户主关系*/
    @Excel(name = "与户主关系", width = 15)
    @ApiModelProperty(value = "与户主关系")
    private String yhzgx;
    /**是否户主*/
    @Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
    private String sfhz;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
    private String khlx;
    /**联系方式*/
    @Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
    private String lxfs;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;
    /**性别*/
    @Excel(name = "性别", width = 15)
    @ApiModelProperty(value = "性别")
    private String xb;
    /**年龄*/
    @Excel(name = "年龄", width = 15)
    @ApiModelProperty(value = "年龄")
    private String nl;
    /**民族*/
    @Excel(name = "民族", width = 15)
    @ApiModelProperty(value = "民族")
    private String mz;
    /**婚姻状况*/
    @Excel(name = "婚姻状况", width = 15)
    @ApiModelProperty(value = "婚姻状况")
    private String hyzk;
    /**备注*/
    @Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String bz;
    /**录入标识*/
    @Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
    private String lrbz;
    /**录入时间*/
    @Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
    private Date lrsj;
    /**录入人*/
    @Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
    private String lrr;

    /**是否开通存款业务*/
    @Excel(name = "是否开通存款业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通存款业务")
    private String sfktckyw;
    /**存款余额*/
    @Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
    private BigDecimal ckye;
    /**存款日平余额*/
    @Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
    private BigDecimal ckrpye;
    /**存款年日平余额*/
    @Excel(name = "存款年日平余额", width = 15)
    @ApiModelProperty(value = "存款年日平余额")
    private BigDecimal cknrpye;
    /**活期存款余额*/
    @Excel(name = "活期存款余额", width = 15)
    @ApiModelProperty(value = "活期存款余额")
    private BigDecimal hqckye;
    /**定期存款余额*/
    @Excel(name = "定期存款余额", width = 15)
    @ApiModelProperty(value = "定期存款余额")
    private BigDecimal dqckye;
    /**活期存款日平余额*/
    @Excel(name = "活期存款日平余额", width = 15)
    @ApiModelProperty(value = "活期存款日平余额")
    private BigDecimal hqckrpye;
    /**定期存款日平余额*/
    @Excel(name = "定期存款日平余额", width = 15)
    @ApiModelProperty(value = "定期存款日平余额")
    private BigDecimal dqckrpye;
    /**活期存款年日平余额*/
    @Excel(name = "活期存款年日平余额", width = 15)
    @ApiModelProperty(value = "活期存款年日平余额")
    private BigDecimal hqcknrpye;
    /**定期存款年日平余额*/
    @Excel(name = "定期存款年日平余额", width = 15)
    @ApiModelProperty(value = "定期存款年日平余额")
    private BigDecimal dqcknrpye;
    /**是否开通贷款业务*/
    @Excel(name = "是否开通贷款业务", width = 15)
    @ApiModelProperty(value = "是否开通贷款业务")
    private String sfktdkyw;
    /**贷款金额*/
    @Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
    private BigDecimal dkje;
    /**贷款余额*/
    @Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
    private BigDecimal dkye;
    /**最近贷款到期日期*/
    @Excel(name = "最近贷款到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最近贷款到期日期")
    private Date zjdkdqrq;
    /**不良贷款余额*/
    @Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
    private BigDecimal bldkye;
    /**表外不良贷款余额*/
    @Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
    private BigDecimal bwbldkye;
    /**是否开通手机银行业务*/
    @Excel(name = "是否开通手机银行业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通手机银行业务")
    @Dict(dicCode = "sfbz")
    private String sfktsjyhyw;
    /**是否开通网上银行业务*/
    @Excel(name = "是否开通网上银行业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通网上银行业务")
    @Dict(dicCode = "sfbz")
    private String sfktwsyhyw;
    /**是否办理etc业务*/
    @Excel(name = "是否办理etc业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理etc业务")
    @Dict(dicCode = "sfbz")
    private String sfbletcyw;

    /**是否代发工资*/
    @Excel(name = "是否代发工资", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否代发工资")
    @Dict(dicCode = "sfbz")
    private String sfdfgz;
    /**是否开通POS机*/
    @Excel(name = "是否开通POS机",  width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通POS机")
    @Dict(dicCode = "sfbz")
    private String sfktpos;
    /**是否开通聚合支付*/
    @Excel(name = "是否开通聚合支付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通聚合支付")
    @Dict(dicCode = "sfbz")
    private String sfktjhzf;
    /**是否办理E支付*/
    @Excel(name = "是否办理E支付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理E支付")
    @Dict(dicCode = "sfbz")
    private String sfblezf;
    /**是否办理E缴费*/
    @Excel(name = "是否办理E缴费", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理E缴费")
    @Dict(dicCode = "sfbz")
    private String sfblejf;
    /**是否办理助农终端*/
    @Excel(name = "是否办理助农终端", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理助农终端")
    @Dict(dicCode = "sfbz")
    private String sfblznzd;
    /**是否办理理财业务*/
    @Excel(name = "是否办理理财业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理理财业务")
    @Dict(dicCode = "sfbz")
    private String sfbllcyw;
    /**是否办理代理保险业务*/
    @Excel(name = "是否办理代理保险业务", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否办理代理保险业务")
    @Dict(dicCode = "sfbz")
    private String sfbldlbx;
    /**是否关注我行公众号*/
    @Excel(name = "是否关注我行公众号", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否关注我行公众号")
    @Dict(dicCode = "sfbz")
    private String sfgzgzh;
    /**是否开通信用卡*/
    @Excel(name = "是否开通信用卡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通信用卡")
    @Dict(dicCode = "sfbz")
    private String sfktxyk;
    /**是否开通福民卡*/
    @Excel(name = "是否开通福民卡", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开通福民卡")
    @Dict(dicCode = "sfbz")
    private String sfktfmk;
    /**是否开扫码付*/
    @Excel(name = "是否开扫码付", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "是否开扫码付")
    @Dict(dicCode = "sfbz")
    private String sfktsmf;

    public String getXtpdsm() {
        return xtpdsm;
    }

    public void setXtpdsm(String xtpdsm) {
        this.xtpdsm = xtpdsm;
    }

    public Date getXgsj() {
        return xgsj;
    }

    public void setXgsj(Date xgsj) {
        this.xgsj = xgsj;
    }

    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    public String getSfxdry() {
        return sfxdry;
    }

    public void setSfxdry(String sfxdry) {
        this.sfxdry = sfxdry;
    }

    public String getSfpkh() {
        return sfpkh;
    }

    public void setSfpkh(String sfpkh) {
        this.sfpkh = sfpkh;
    }

    public String getSfdb() {
        return sfdb;
    }

    public void setSfdb(String sfdb) {
        this.sfdb = sfdb;
    }

    public String getCszy() {
        return cszy;
    }

    public void setCszy(String cszy) {
        this.cszy = cszy;
    }

    public String getSfgzry() {
        return sfgzry;
    }

    public void setSfgzry(String sfgzry) {
        this.sfgzry = sfgzry;
    }

    public String getSfffjz() {
        return sfffjz;
    }

    public void setSfffjz(String sfffjz) {
        this.sfffjz = sfffjz;
    }

    public String getHbjl() {
        return hbjl;
    }

    public void setHbjl(String hbjl) {
        this.hbjl = hbjl;
    }

    public BigDecimal getInfoRate() {
        return infoRate;
    }

    public void setInfoRate(BigDecimal infoRate) {
        this.infoRate = infoRate;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getSflqsbk() {
        return sflqsbk;
    }

    public void setSflqsbk(String sflqsbk) {
        this.sflqsbk = sflqsbk;
    }

    public String getSfktsbk() {
        return sfktsbk;
    }

    public void setSfktsbk(String sfktsbk) {
        this.sfktsbk = sfktsbk;
    }

    public Integer getXtpdjg() {
        return xtpdjg;
    }

    public void setXtpdjg(Integer xtpdjg) {
        this.xtpdjg = xtpdjg;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSszh() {
        return sszh;
    }

    public void setSszh(String sszh) {
        this.sszh = sszh;
    }

    public String getSsyxdy() {
        return ssyxdy;
    }

    public void setSsyxdy(String ssyxdy) {
        this.ssyxdy = ssyxdy;
    }

    public String getHhbm() {
        return hhbm;
    }

    public void setHhbm(String hhbm) {
        this.hhbm = hhbm;
    }

    public String getYhzgx() {
        return yhzgx;
    }

    public void setYhzgx(String yhzgx) {
        this.yhzgx = yhzgx;
    }

    public String getSfhz() {
        return sfhz;
    }

    public void setSfhz(String sfhz) {
        this.sfhz = sfhz;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getKhlx() {
        return khlx;
    }

    public void setKhlx(String khlx) {
        this.khlx = khlx;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getHyzk() {
        return hyzk;
    }

    public void setHyzk(String hyzk) {
        this.hyzk = hyzk;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getLrbz() {
        return lrbz;
    }

    public void setLrbz(String lrbz) {
        this.lrbz = lrbz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getSfktckyw() {
        return sfktckyw;
    }

    public void setSfktckyw(String sfktckyw) {
        this.sfktckyw = sfktckyw;
    }

    public BigDecimal getCkye() {
        return ckye;
    }

    public void setCkye(BigDecimal ckye) {
        this.ckye = ckye;
    }

    public BigDecimal getCkrpye() {
        return ckrpye;
    }

    public void setCkrpye(BigDecimal ckrpye) {
        this.ckrpye = ckrpye;
    }

    public BigDecimal getCknrpye() {
        return cknrpye;
    }

    public void setCknrpye(BigDecimal cknrpye) {
        this.cknrpye = cknrpye;
    }

    public BigDecimal getHqckye() {
        return hqckye;
    }

    public void setHqckye(BigDecimal hqckye) {
        this.hqckye = hqckye;
    }

    public BigDecimal getDqckye() {
        return dqckye;
    }

    public void setDqckye(BigDecimal dqckye) {
        this.dqckye = dqckye;
    }

    public BigDecimal getHqckrpye() {
        return hqckrpye;
    }

    public void setHqckrpye(BigDecimal hqckrpye) {
        this.hqckrpye = hqckrpye;
    }

    public BigDecimal getDqckrpye() {
        return dqckrpye;
    }

    public void setDqckrpye(BigDecimal dqckrpye) {
        this.dqckrpye = dqckrpye;
    }

    public BigDecimal getHqcknrpye() {
        return hqcknrpye;
    }

    public void setHqcknrpye(BigDecimal hqcknrpye) {
        this.hqcknrpye = hqcknrpye;
    }

    public BigDecimal getDqcknrpye() {
        return dqcknrpye;
    }

    public void setDqcknrpye(BigDecimal dqcknrpye) {
        this.dqcknrpye = dqcknrpye;
    }

    public String getSfktdkyw() {
        return sfktdkyw;
    }

    public void setSfktdkyw(String sfktdkyw) {
        this.sfktdkyw = sfktdkyw;
    }

    public BigDecimal getDkje() {
        return dkje;
    }

    public void setDkje(BigDecimal dkje) {
        this.dkje = dkje;
    }

    public BigDecimal getDkye() {
        return dkye;
    }

    public void setDkye(BigDecimal dkye) {
        this.dkye = dkye;
    }

    public Date getZjdkdqrq() {
        return zjdkdqrq;
    }

    public void setZjdkdqrq(Date zjdkdqrq) {
        this.zjdkdqrq = zjdkdqrq;
    }

    public BigDecimal getBldkye() {
        return bldkye;
    }

    public void setBldkye(BigDecimal bldkye) {
        this.bldkye = bldkye;
    }

    public BigDecimal getBwbldkye() {
        return bwbldkye;
    }

    public void setBwbldkye(BigDecimal bwbldkye) {
        this.bwbldkye = bwbldkye;
    }

    public String getSfktsjyhyw() {
        return sfktsjyhyw;
    }

    public void setSfktsjyhyw(String sfktsjyhyw) {
        this.sfktsjyhyw = sfktsjyhyw;
    }

    public String getSfktwsyhyw() {
        return sfktwsyhyw;
    }

    public void setSfktwsyhyw(String sfktwsyhyw) {
        this.sfktwsyhyw = sfktwsyhyw;
    }

    public String getSfbletcyw() {
        return sfbletcyw;
    }

    public void setSfbletcyw(String sfbletcyw) {
        this.sfbletcyw = sfbletcyw;
    }

    public String getSfdfgz() {
        return sfdfgz;
    }

    public void setSfdfgz(String sfdfgz) {
        this.sfdfgz = sfdfgz;
    }

    public String getSfktpos() {
        return sfktpos;
    }

    public void setSfktpos(String sfktpos) {
        this.sfktpos = sfktpos;
    }

    public String getSfktjhzf() {
        return sfktjhzf;
    }

    public void setSfktjhzf(String sfktjhzf) {
        this.sfktjhzf = sfktjhzf;
    }

    public String getSfblezf() {
        return sfblezf;
    }

    public void setSfblezf(String sfblezf) {
        this.sfblezf = sfblezf;
    }

    public String getSfblejf() {
        return sfblejf;
    }

    public void setSfblejf(String sfblejf) {
        this.sfblejf = sfblejf;
    }

    public String getSfblznzd() {
        return sfblznzd;
    }

    public void setSfblznzd(String sfblznzd) {
        this.sfblznzd = sfblznzd;
    }

    public String getSfbllcyw() {
        return sfbllcyw;
    }

    public void setSfbllcyw(String sfbllcyw) {
        this.sfbllcyw = sfbllcyw;
    }

    public String getSfbldlbx() {
        return sfbldlbx;
    }

    public void setSfbldlbx(String sfbldlbx) {
        this.sfbldlbx = sfbldlbx;
    }

    public String getSfgzgzh() {
        return sfgzgzh;
    }

    public void setSfgzgzh(String sfgzgzh) {
        this.sfgzgzh = sfgzgzh;
    }

    public String getSfktxyk() {
        return sfktxyk;
    }

    public void setSfktxyk(String sfktxyk) {
        this.sfktxyk = sfktxyk;
    }

    public String getSfktfmk() {
        return sfktfmk;
    }

    public void setSfktfmk(String sfktfmk) {
        this.sfktfmk = sfktfmk;
    }

    public String getSfktsmf() {
        return sfktsmf;
    }

    public void setSfktsmf(String sfktsmf) {
        this.sfktsmf = sfktsmf;
    }
}

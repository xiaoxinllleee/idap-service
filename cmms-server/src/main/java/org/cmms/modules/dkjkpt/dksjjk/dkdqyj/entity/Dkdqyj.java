package org.cmms.modules.dkjkpt.dksjjk.dkdqyj.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款到期预警
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Data
@TableName("V_DKJKPT_DKDQYJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_DKJKPT_DKDQYJ对象", description="贷款到期预警")
public class Dkdqyj {

    /**统计月份*/
    @Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
    private Date tjyf;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15, dicCode="ywjgdm", dictTable="hr_bas_organization", dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    private String jgdm;
    /**客户名称*/
    @Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String khmc;
    /**证件号码*/
    @Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
    private String zjhm;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15, dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "dkjkpt_khlx")
    private String khlx;
    /**地址*/
    @Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private String dz;
    /**电话号码*/
    /*@Excel(name = "电话号码", width = 15)*/
    @ApiModelProperty(value = "电话号码")
    private String dhhm;
    /**贷款账号*/
    @Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
    private String dkzh;
    /**便民卡卡号*/
    /*@Excel(name = "便民卡卡号", width = 15)*/
    @ApiModelProperty(value = "便民卡卡号")
    private String bmkkh;
    /**贷款金额*/
    @Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
    private java.math.BigDecimal dkje;
    /**贷款余额*/
    @Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
    private java.math.BigDecimal dkye;
    /**借款日期*/
    @Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
    private Date jkrq;
    /**到期日期*/
    @Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
    private Date dqrq;
    /**起息日*/
    /*@Excel(name = "起息日", width = 15, format = "yyyy-MM-dd")*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日")
    private Date qxr;
    /**结息日*/
    /*@Excel(name = "结息日", width = 15)*/
    @ApiModelProperty(value = "结息日")
    private String jxr;
    /**贷款期限*/
    @Excel(name = "贷款期限", width = 15, dicCode = "dkqx")
    @ApiModelProperty(value = "贷款期限")
    @Dict(dicCode = "dkqx")
    private String dkqx;
    /**产品名称*/
    /*@Excel(name = "产品名称", width = 15)*/
    @ApiModelProperty(value = "产品名称")
    private String cpmc;
    /**剩余天数*/
    @Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
    private Integer syts;
    /**欠息天数*/
    /*@Excel(name = "欠息天数", width = 15)*/
    @ApiModelProperty(value = "欠息天数")
    private String qxts;
	/**表内应计利息*/
	/*@Excel(name = "表内应计利息", width = 15)*/
    @ApiModelProperty(value = "表内应计利息")
	private java.math.BigDecimal bnyjlx;
	/**表内应收利息*/
	/*@Excel(name = "表内应收利息", width = 15)*/
    @ApiModelProperty(value = "表内应收利息")
	private java.math.BigDecimal bnyslx;
	/**表外应计利息*/
	/*@Excel(name = "表外应计利息", width = 15)*/
    @ApiModelProperty(value = "表外应计利息")
	private java.math.BigDecimal bwyjlx;
	/**表外应收利息*/
	/*@Excel(name = "表外应收利息", width = 15)*/
    @ApiModelProperty(value = "表外应收利息")
	private java.math.BigDecimal bwyslx;
	/**表内外欠息和*/
	/*@Excel(name = "表内外欠息和", width = 15)*/
    @ApiModelProperty(value = "表内外欠息和")
	private java.math.BigDecimal bnwqxh;
	/**贷款利率(%)*/
	/*@Excel(name = "贷款利率(%)", width = 15)*/
    @ApiModelProperty(value = "贷款利率")
	private java.math.BigDecimal dkll;
	/**担保方式*/
	/*@Excel(name = "担保方式", width = 15)*/
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**主要责任人*/
	@Excel(name = "主要责任人", width = 15)
    @ApiModelProperty(value = "主要责任人")
	private String zyzrr;
	/**次要责任人*/
	@Excel(name = "次要责任人", width = 15)
    @ApiModelProperty(value = "次要责任人")
	private String cyzrr;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**客户经理员工工号*/
	/*@Excel(name = "客户经理员工工号", width = 15)*/
    @ApiModelProperty(value = "客户经理员工工号")
	private String khjlyggh;
	/**客户所属行业类型*/
	/*@Excel(name = "客户所属行业类型", width = 15)*/
    @ApiModelProperty(value = "客户所属行业类型")
	private String khsshylx;
	/**贷款投向*/
	/*@Excel(name = "贷款投向", width = 15)*/
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15, dicCode = "dkxt")
    @ApiModelProperty(value = "贷款形态")
    @Dict(dicCode = "dkxt")
	private String dkxt;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15, dicCode = "dkzl")
    @ApiModelProperty(value = "信贷贷款品种")
    @Dict(dicCode = "dkzl")
	private String xddkpz;
	/**到期情况监测*/
	/*@Excel(name = "到期情况监测", width = 15)*/
    @ApiModelProperty(value = "到期情况监测")
	private String dqqkjc;
	/**不良形成原因*/
	/*@Excel(name = "不良形成原因", width = 15)*/
    @ApiModelProperty(value = "不良形成原因")
	private String blxcyy;
	/**责任界定*/
	/*@Excel(name = "责任界定", width = 15)*/
    @ApiModelProperty(value = "责任界定")
	private String zrjd;
	/**清收处置措施*/
	/*@Excel(name = "清收处置措施", width = 15)*/
    @ApiModelProperty(value = "清收处置措施")
	private String qsczcs;
	/**清收处置时限*/
	/*@Excel(name = "清收处置时限", width = 15)*/
    @ApiModelProperty(value = "清收处置时限")
	private String qsczsx;
	/**贷款责任人*/
	/*@Excel(name = "贷款责任人", width = 15)*/
    @ApiModelProperty(value = "贷款责任人")
	private String dkzrr;
	/**清收责任人*/
	@Excel(name = "清收责任人", width = 15)
    @ApiModelProperty(value = "清收责任人")
	private String qszrr;
	/**xwqy*/
    /*@Excel(name = "xwqy", width = 15)*/
    @ApiModelProperty(value = "xwqy")
	private String xwqy;
	/**备注*/
	/*@Excel(name = "备注", width = 15)*/
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**到期时间区间*/
	/*@Excel(name = "到期时间区间", width = 15)*/
    @ApiModelProperty(value = "到期时间区间")
	private Integer dqlx;

    public BigDecimal getBnyjlx() {
        return bnyjlx;
    }

    public void setBnyjlx(BigDecimal bnyjlx) {
        this.bnyjlx = bnyjlx;
    }

    public BigDecimal getBnyslx() {
        return bnyslx;
    }

    public void setBnyslx(BigDecimal bnyslx) {
        this.bnyslx = bnyslx;
    }

    public BigDecimal getBwyjlx() {
        return bwyjlx;
    }

    public void setBwyjlx(BigDecimal bwyjlx) {
        this.bwyjlx = bwyjlx;
    }

    public BigDecimal getBwyslx() {
        return bwyslx;
    }

    public void setBwyslx(BigDecimal bwyslx) {
        this.bwyslx = bwyslx;
    }

    public BigDecimal getBnwqxh() {
        return bnwqxh;
    }

    public void setBnwqxh(BigDecimal bnwqxh) {
        this.bnwqxh = bnwqxh;
    }

    public BigDecimal getDkll() {
        return dkll;
    }

    public void setDkll(BigDecimal dkll) {
        this.dkll = dkll;
    }

    public String getDbfs() {
        return dbfs;
    }

    public void setDbfs(String dbfs) {
        this.dbfs = dbfs;
    }

    public String getDyzrr() {
        return dyzrr;
    }

    public void setDyzrr(String dyzrr) {
        this.dyzrr = dyzrr;
    }

    public String getZyzrr() {
        return zyzrr;
    }

    public void setZyzrr(String zyzrr) {
        this.zyzrr = zyzrr;
    }

    public String getCyzrr() {
        return cyzrr;
    }

    public void setCyzrr(String cyzrr) {
        this.cyzrr = cyzrr;
    }

    public String getKhjlbz() {
        return khjlbz;
    }

    public void setKhjlbz(String khjlbz) {
        this.khjlbz = khjlbz;
    }

    public String getKhjlyggh() {
        return khjlyggh;
    }

    public void setKhjlyggh(String khjlyggh) {
        this.khjlyggh = khjlyggh;
    }

    public String getKhsshylx() {
        return khsshylx;
    }

    public void setKhsshylx(String khsshylx) {
        this.khsshylx = khsshylx;
    }

    public String getDktx() {
        return dktx;
    }

    public void setDktx(String dktx) {
        this.dktx = dktx;
    }

    public String getDkxt() {
        return dkxt;
    }

    public void setDkxt(String dkxt) {
        this.dkxt = dkxt;
    }

    public String getXddkpz() {
        return xddkpz;
    }

    public void setXddkpz(String xddkpz) {
        this.xddkpz = xddkpz;
    }

    public String getDqqkjc() {
        return dqqkjc;
    }

    public void setDqqkjc(String dqqkjc) {
        this.dqqkjc = dqqkjc;
    }

    public String getBlxcyy() {
        return blxcyy;
    }

    public void setBlxcyy(String blxcyy) {
        this.blxcyy = blxcyy;
    }

    public String getZrjd() {
        return zrjd;
    }

    public void setZrjd(String zrjd) {
        this.zrjd = zrjd;
    }

    public String getQsczcs() {
        return qsczcs;
    }

    public void setQsczcs(String qsczcs) {
        this.qsczcs = qsczcs;
    }

    public String getQsczsx() {
        return qsczsx;
    }

    public void setQsczsx(String qsczsx) {
        this.qsczsx = qsczsx;
    }

    public String getDkzrr() {
        return dkzrr;
    }

    public void setDkzrr(String dkzrr) {
        this.dkzrr = dkzrr;
    }

    public String getQszrr() {
        return qszrr;
    }

    public void setQszrr(String qszrr) {
        this.qszrr = qszrr;
    }

    public String getXwqy() {
        return xwqy;
    }

    public void setXwqy(String xwqy) {
        this.xwqy = xwqy;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }

    public String getLrbz() {
        return lrbz;
    }

    public void setLrbz(String lrbz) {
        this.lrbz = lrbz;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public Integer getDqlx() {
        return dqlx;
    }

    public void setDqlx(Integer dqlx) {
        this.dqlx = dqlx;
    }

    public Date getTjyf() {
        return tjyf;
    }

    public void setTjyf(Date tjyf) {
        this.tjyf = tjyf;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
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

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getDhhm() {
        return dhhm;
    }

    public void setDhhm(String dhhm) {
        this.dhhm = dhhm;
    }

    public String getDkzh() {
        return dkzh;
    }

    public void setDkzh(String dkzh) {
        this.dkzh = dkzh;
    }

    public String getBmkkh() {
        return bmkkh;
    }

    public void setBmkkh(String bmkkh) {
        this.bmkkh = bmkkh;
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

    public Date getJkrq() {
        return jkrq;
    }

    public void setJkrq(Date jkrq) {
        this.jkrq = jkrq;
    }

    public Date getDqrq() {
        return dqrq;
    }

    public void setDqrq(Date dqrq) {
        this.dqrq = dqrq;
    }

    public Date getQxr() {
        return qxr;
    }

    public void setQxr(Date qxr) {
        this.qxr = qxr;
    }

    public String getJxr() {
        return jxr;
    }

    public void setJxr(String jxr) {
        this.jxr = jxr;
    }

    public String getDkqx() {
        return dkqx;
    }

    public void setDkqx(String dkqx) {
        this.dkqx = dkqx;
    }

    public String getCpmc() {
        return cpmc;
    }

    public void setCpmc(String cpmc) {
        this.cpmc = cpmc;
    }

    public Integer getSyts() {
        return syts;
    }

    public void setSyts(Integer syts) {
        this.syts = syts;
    }

    public String getQxts() {
        return qxts;
    }

    public void setQxts(String qxts) {
        this.qxts = qxts;
    }
}

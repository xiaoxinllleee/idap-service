package org.cmms.modules.tjfx.khzftj.entity;

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
 * @Description: 客户走访统计
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHZFTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHZFTJ对象", description="客户走访统计")
public class Khzftj implements Serializable {

    private static final long serialVersionUID = -2887696438059995774L;

    /**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15, dicCode="YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
    @ApiModelProperty(value = "客户经理")
    @Dict(dicCode="YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String khjlbh;
	/**总人数*/
	@Excel(name = "总人数", width = 15)
    @ApiModelProperty(value = "总人数")
	private Integer zrs;
	/**回访人数*/
	@Excel(name = "回访人数", width = 15)
    @ApiModelProperty(value = "回访人数")
	private Integer hfrs;
	/**未回访人数*/
	@Excel(name = "未回访人数", width = 15)
    @ApiModelProperty(value = "未回访人数")
	private Integer whfrs;
	/**完成率*/
	@Excel(name = "完成率", width = 15)
    @ApiModelProperty(value = "完成率")
	private java.math.BigDecimal wcl;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
    @Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;

    public Date getTjrq() {
        return tjrq;
    }

    public void setTjrq(Date tjrq) {
        this.tjrq = tjrq;
    }

    public String getZzbz() {
        return zzbz;
    }

    public void setZzbz(String zzbz) {
        this.zzbz = zzbz;
    }

    public String getKhjlbh() {
        return khjlbh;
    }

    public void setKhjlbh(String khjlbh) {
        this.khjlbh = khjlbh;
    }

    public Integer getZrs() {
        return zrs;
    }

    public void setZrs(Integer zrs) {
        this.zrs = zrs;
    }

    public Integer getHfrs() {
        return hfrs;
    }

    public void setHfrs(Integer hfrs) {
        this.hfrs = hfrs;
    }

    public Integer getWhfrs() {
        return whfrs;
    }

    public void setWhfrs(Integer whfrs) {
        this.whfrs = whfrs;
    }

    public BigDecimal getWcl() {
        return wcl;
    }

    public void setWcl(BigDecimal wcl) {
        this.wcl = wcl;
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

    public Date getLrsj() {
        return lrsj;
    }

    public void setLrsj(Date lrsj) {
        this.lrsj = lrsj;
    }


}

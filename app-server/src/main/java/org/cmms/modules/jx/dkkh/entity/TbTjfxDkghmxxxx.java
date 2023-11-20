package org.cmms.modules.jx.dkkh.entity;

import java.io.Serializable;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款管户明细信息
 * @Author: jeecg-boot
 * @Date:   2021-05-25
 * @Version: V1.0
 */
@Data
@TableName("TB_TJFX_DKGHMXXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TB_TJFX_DKGHMXXX对象", description="贷款管户明细信息")
public class TbTjfxDkghmxxxx {
    
	/**起息日期*/
	@Excel(name = "起息日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起息日期")
	private Date qxrq;
	/**贷款利率*/
	@Excel(name = "贷款利率", width = 15)
    @ApiModelProperty(value = "贷款利率")
	private java.math.BigDecimal dklv;
	/**调查人*/
	@Excel(name = "调查人", width = 15)
    @ApiModelProperty(value = "调查人")
	private String dcr;
	/**审批人*/
	@Excel(name = "审批人", width = 15)
    @ApiModelProperty(value = "审批人")
	private String spr;
	/**包收人*/
	@Excel(name = "包收人", width = 15)
    @ApiModelProperty(value = "包收人")
	private String bsr;
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**贷款所在机构代码*/
	@Excel(name = "贷款所在机构代码", width = 15)
    @ApiModelProperty(value = "贷款所在机构代码")
	private String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型 ( 1：个人
2：企业)*/
	@Excel(name = "客户类型 ( 1：个人 2：企业)", width = 15)
    @ApiModelProperty(value = "客户类型 ( 1：个人 2：企业)")
	private Integer khlx;
	/**客户性别 ( 1：男
2：女)*/
	@Excel(name = "客户性别 ( 1：男 2：女)", width = 15)
    @ApiModelProperty(value = "客户性别 ( 1：男 2：女)")
	private Integer khxb;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**发放日期*/
	@Excel(name = "发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发放日期")
	private Date ffrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**五级分类状态*/
	@Excel(name = "五级分类状态", width = 15)
    @ApiModelProperty(value = "五级分类状态")
	private Integer wjflzt;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	private String dkpz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
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
}

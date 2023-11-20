package org.cmms.modules.ywgl.yxbldkgl.qkqsmx.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 欠款期数明细
 * @Author: Penghr
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("yxbldk_qkqsmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yxbldk_qkqsmx对象", description="欠款期数明细")
public class Qkqsmx {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String branchNo;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**借款人*/
	@Excel(name = "借款人", width = 15)
    @ApiModelProperty(value = "借款人")
	private String custName;
	/**最多欠款期数贷款账号*/
	@Excel(name = "最多欠款期数贷款账号", width = 15)
    @ApiModelProperty(value = "最多欠款期数贷款账号")
	private String acctNo;
	/**利息欠款期数*/
	@Excel(name = "利息欠款期数", width = 15)
    @ApiModelProperty(value = "利息欠款期数")
	private String lxqkqs;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15, numFormat = ",##0.000")
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal currBal;
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "操作标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

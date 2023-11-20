package org.cmms.modules.ywgl.yxbldkgl.djkblxx.entity;

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
 * @Description: 贷记卡不良信息
 * @Author: Penghr
 * @Date:   2022-08-23
 * @Version: V1.0
 */
@Data
@TableName("yxbldk_djkblxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="yxbldk_djkblxx对象", description="贷记卡不良信息")
public class Djkblxx {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**开户机构号*/
	@Excel(name = "开户机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String branchNo;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String identNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date qxDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date endDate;
	/**贷款期限(1 短期贷款 2 长期贷款)*/
	@Excel(name = "贷款期限", width = 15, dicCode = "yxbldkqx")
    @ApiModelProperty(value = "贷款期限(1 短期贷款 2 长期贷款)")
	@Dict(dicCode = "yxbldkqx")
	private Integer dkqx;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private String syts;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15, numFormat = ",##0.000")
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal currBal;
	/**贷款形态(1 正常 2 关注 3 次级 4 可疑 5 损失)*/
	@Excel(name = "贷款形态", width = 15, dicCode = "wjflbz")
    @ApiModelProperty(value = "贷款形态(1 正常 2 关注 3 次级 4 可疑 5 损失)")
	@Dict(dicCode = "wjflbz")
	private String fiveClassType;
	/**录入标识(0 导入 1 录入 2 修改)*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

package org.cmms.modules.ywgl.ckyw.khckrjcx.entity;

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
 * @Description: 客户存款日均查询
 * @Author: jeecg-boot
 * @Date:   2021-10-26
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_KHCKRJCX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_KHCKRJCX对象", description="客户存款日均查询")
public class Khckrjcx {
    
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private java.math.BigDecimal pch;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**账号*/
	@Excel(name = "账号", width = 15)
	@ApiModelProperty(value = "账号")
	private String zh;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
	@ApiModelProperty(value = "卡号")
	private String kh;
	/**户名*/
	@Excel(name = "户名", width = 15)
	@ApiModelProperty(value = "户名")
	private String hm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**账号类型*/
	@Excel(name = "账号类型", width = 15,dicCode = "zhxz")
	@ApiModelProperty(value = "账号类型")
	@Dict(dicCode = "zhxz")
	private String zhlx;
	/**日均余额*/
	@Excel(name = "日均余额", width = 15)
	@ApiModelProperty(value = "日均余额")
	private java.math.BigDecimal rjye;
	/**结束日期当天余额*/
	@Excel(name = "结束日期当天余额", width = 15)
    @ApiModelProperty(value = "结束日期当天余额")
	private java.math.BigDecimal ye;
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**统计天数*/
	@Excel(name = "统计天数", width = 15)
	@ApiModelProperty(value = "统计天数")
	private Integer tjts;
	/**录入标识（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入操作员*/
	@Excel(name = "录入操作员", width = 15)
	@ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;

	/**xjlr*/
    @ApiModelProperty(value = "xjlr")
	private java.math.BigDecimal xjlr;
	/**xjlc*/
    @ApiModelProperty(value = "xjlc")
	private java.math.BigDecimal xjlc;
	/**开户日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
}

package org.cmms.modules.report.sgtzgl.khsxcx.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户授信查询
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_dksxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_dksxdjb对象", description="客户授信查询")
public class SgtzglKhsxcx {

/*	*//**主键ID*//*
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;*/

	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**序号*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgmc;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**注册号码*/
	@Excel(name = "注册号码", width = 15)
    @ApiModelProperty(value = "注册号码")
	private String zchm;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String khh;
	/**业务品种编号*/
	@Excel(name = "业务品种编号", width = 15)
    @ApiModelProperty(value = "业务品种编号")
	private String ywpzbh;
	/**业务品种名称*/
	@Excel(name = "业务品种名称", width = 15)
    @ApiModelProperty(value = "业务品种名称")
	private String ywpzmc;
	/**授信开始日期*/
	@Excel(name = "授信开始日期", width = 15)
    @ApiModelProperty(value = "授信开始日期")
	private String sxksrq;
	/**授信到期日期*/
	@Excel(name = "授信到期日期", width = 15)
    @ApiModelProperty(value = "授信到期日期")
	private String sxdqrq;
	/**授信种类*/
	@Excel(name = "授信种类", width = 15)
    @ApiModelProperty(value = "授信种类")
	private String sxzl;
	/**内部授信额度*/
	@Excel(name = "内部授信额度", width = 15)
    @ApiModelProperty(value = "内部授信额度")
	private java.math.BigDecimal nbsxed;
	/**内部授信已使用额度*/
	@Excel(name = "内部授信已使用额度", width = 15)
    @ApiModelProperty(value = "内部授信已使用额度")
	private java.math.BigDecimal nbsxysyed;
	/**内部授信冻结额度*/
	@Excel(name = "内部授信冻结额度", width = 15)
    @ApiModelProperty(value = "内部授信冻结额度")
	private java.math.BigDecimal nbsxdjed;
	/**公开授信额度*/
	@Excel(name = "公开授信额度", width = 15)
    @ApiModelProperty(value = "公开授信额度")
	private java.math.BigDecimal gksxed;
	/**公开授信已使用额度*/
	@Excel(name = "公开授信已使用额度", width = 15)
    @ApiModelProperty(value = "公开授信已使用额度")
	private java.math.BigDecimal gksxysyed;
	/**公开授信冻结额度*/
	@Excel(name = "公开授信冻结额度", width = 15)
    @ApiModelProperty(value = "公开授信冻结额度")
	private java.math.BigDecimal gksxdjed;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	//@ExcelVerify(interHandler = true)
	private String zkhjl;
	/**创建人*/
	/*@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;*/
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
/*	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;*/
	/**修改时间*/
/*	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;*/
}

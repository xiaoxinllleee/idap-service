package org.cmms.modules.khgl.jzyx.dklshcmmon.entity;

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
 * @Description: 贷款流失户
 * @Author: jeecg-boot
 * @Date:   2023-07-10
 * @Version: V1.0
 */
@Data
@TableName("khxxgl_jzyx_dklsh_c")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khxxgl_jzyx_dklsh_c对象", description="贷款流失户")
public class Dklsh {
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private java.lang.String id;

	/**原组织标志*/
	@Excel(name = "原支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "原支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private java.lang.String zzbz;
	/**原机构代码*//*
	@Excel(name = "原机构代码", width = 15)
    @ApiModelProperty(value = "原机构代码")
	private java.lang.String brNo;*/
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String identNo;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private java.lang.String hth;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同起始日期*/
	@Excel(name = "合同起始日期", width = 15)
    @ApiModelProperty(value = "合同起始日期")
	private java.lang.String htqsrq;
	/**合同结束日期*/
	@Excel(name = "合同结束日期", width = 15)
    @ApiModelProperty(value = "合同结束日期")
	private java.lang.String htdqrq;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private java.lang.String qyrq;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15,dicCode = "dbfs")
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "dbfs")
	private java.lang.String dbfs;
	/**借款原因*/
	@Excel(name = "借款原因", width = 15)
    @ApiModelProperty(value = "借款原因")
	private java.lang.String jkyy;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private java.lang.String dyzrr;
	/**电话号码*/
	@Excel(name = "电话号码", width = 15)
    @ApiModelProperty(value = "电话号码")
	private java.lang.String dhhm;
	/**名单情况*/
	@Excel(name = "名单情况", width = 15, dicCode = "xtpdjg")
	@ApiModelProperty(value = "名单情况")
	@Dict(dicCode = "xtpdjg")
	private java.lang.String khlx;

	/**户籍所在网格*/
	@Excel(name = "户籍所在网格", width = 15, dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "户籍所在网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String hjszwg;


	/**户籍所属支行*/
	@Excel(name = "户籍所属支行", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "户籍所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String hjsszh;


	/**数据时间*/
	@Excel(name = "数据时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据时间")
	private java.util.Date createTime;

	/**经度*/
	@Excel(name = "经度", width = 15)
	@ApiModelProperty(value = "经度")
	private String longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
	@ApiModelProperty(value = "纬度")
	private String latitude;
}

package org.cmms.modules.khpjsx.khpjsxb.entity;

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
 * @Description: 评级结果明细表
 * @Author: jeecg-boot
 * @Date:   2020-01-18
 * @Version: V1.0
 */
@Data
@TableName("PJSX_PJJGMXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_PJJGMXB对象", description="评级结果明细表")
public class PjsxPjjgmxb {

	/**组织标识*//*
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;*/
	/**评议日期*/
	@Excel(name = "评议日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评议日期")
	private Date pyrq;
/*	*//**员工工号*//*
	@Excel(name = "员工工号", width = 15)
	@ApiModelProperty(value = "员工工号")
	private String yggh;
	*//**客户名称*//*
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;*/

	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**项目ID*/
	@Excel(name = "项目ID", width = 15)
    @ApiModelProperty(value = "项目ID")
	private String xmid;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
	@ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**项目分值*/
	@Excel(name = "项目分值", width = 15)
	@ApiModelProperty(value = "项目分值")
	private java.math.BigDecimal xmfz;
	/**项目结果*/
	@Excel(name = "项目结果", width = 15)
    @ApiModelProperty(value = "项目结果")
	private java.math.BigDecimal xmjg;

	/**指标类别*//*
	@Excel(name = "指标类别", width = 15)
    @ApiModelProperty(value = "指标类别")
	private Integer zblb;
	*//**录入标志*//*
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	*//**客户类型*//*
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String khlx;*/

	/**创建人*//*
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	*//**创建时间*//*
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;*/

}

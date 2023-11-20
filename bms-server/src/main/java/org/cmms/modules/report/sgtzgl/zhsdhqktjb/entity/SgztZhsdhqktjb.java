package org.cmms.modules.report.sgtzgl.zhsdhqktjb.entity;

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
 * @Description: 支行首贷户情况统计表
 * @Author: jeecg-boot
 * @Date:   2022-08-26
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_ZHSDHQKTJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_ZHSDHQKTJB对象", description="支行首贷户情况统计表")
public class SgztZhsdhqktjb {
    
	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**支行名称*/
	@Excel(name = "支行名称", width = 15)
    @ApiModelProperty(value = "支行名称")
	private String zhmc;
	/**首贷户户名*/
	@Excel(name = "首贷户户名", width = 15)
    @ApiModelProperty(value = "首贷户户名")
	private String sdhhm;
	/**首次贷款日期*/
	@Excel(name = "首次贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "首次贷款日期")
	private Date scdkrq;
	/**贷款账号（17位）*/
	@Excel(name = "贷款账号（17位）", width = 15)
    @ApiModelProperty(value = "贷款账号（17位）")
	private String dkzh;
	/**授信金额（万元）*/
	@Excel(name = "授信金额（万元）", width = 15)
    @ApiModelProperty(value = "授信金额（万元）")
	private java.math.BigDecimal sxje;
	/**首次贷款金额（万元）*/
	@Excel(name = "首次贷款金额（万元）", width = 15)
    @ApiModelProperty(value = "首次贷款金额（万元）")
	private java.math.BigDecimal scdkje;
	/**首贷户客户类型*/
	@Excel(name = "首贷户客户类型", width = 15)
    @ApiModelProperty(value = "首贷户客户类型")
	private String sdhkhlx;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**授信（元）*/
	@Excel(name = "授信（元）", width = 15)
    @ApiModelProperty(value = "授信（元）")
	private java.math.BigDecimal sx;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}

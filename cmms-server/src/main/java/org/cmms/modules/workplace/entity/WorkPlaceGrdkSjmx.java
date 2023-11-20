package org.cmms.modules.workplace.entity;

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
 * @Description: 个人贷款工作台明细
 * @Author: Penghr
 * @Date:   2020-08-30
 * @Version: V1.0
 */
@Data
@TableName("WORKPLACE_GRDKSJMX_LY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WORKPLACE_GRDKSJMX_LY对象", description="个人贷款工作台明细")
public class WorkPlaceGrdkSjmx {
    
	/**申请日期*/
	@Excel(name = "申请日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "申请日期")
	private Date sqrq;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String sszh;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**镇*/
	@Excel(name = "镇", width = 15)
    @ApiModelProperty(value = "镇")
	private String town;
	/**村*/
	@Excel(name = "村", width = 15)
    @ApiModelProperty(value = "村")
	private String village;
	/**组*/
	@Excel(name = "组", width = 15)
    @ApiModelProperty(value = "组")
	private String organize;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**授信等级*/
	@Excel(name = "授信等级", width = 15)
    @ApiModelProperty(value = "授信等级")
	private String sxdj;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
    @ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;
	/**执行利率*/
	@Excel(name = "执行利率", width = 15)
    @ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
}

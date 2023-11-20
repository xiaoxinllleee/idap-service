package org.cmms.modules.report.sgtzgl.yhkywqkb.entity;

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
 * @Description: 银行卡业务情况表
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_yhkywqkb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_yhkywqkb对象", description="银行卡业务情况表")
public class SgtzYhkywqkb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**机构编码*/
	@Excel(name = "机构编码", width = 15)
    @ApiModelProperty(value = "机构编码")
	private String branchNo;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15)
    @ApiModelProperty(value = "机构名称")
	private String branchName;
	/**贷记卡年初存款余额*/
	@Excel(name = "贷记卡年初存款余额", width = 15)
    @ApiModelProperty(value = "贷记卡年初存款余额")
	private java.math.BigDecimal ncckye;
	/**贷记卡本月存款余额*/
	@Excel(name = "贷记卡本月存款余额", width = 15)
    @ApiModelProperty(value = "贷记卡本月存款余额")
	private java.math.BigDecimal byckye;
	/**贷记卡年初低息存款余额*/
	@Excel(name = "贷记卡年初低息存款余额", width = 15)
    @ApiModelProperty(value = "贷记卡年初低息存款余额")
	private java.math.BigDecimal ncdxckye;
	/**贷记卡本月低息存款余额*/
	@Excel(name = "贷记卡本月低息存款余额", width = 15)
    @ApiModelProperty(value = "贷记卡本月低息存款余额")
	private java.math.BigDecimal bydxckye;
	/**贷记卡年初透支本金余额*/
	@Excel(name = "贷记卡年初透支本金余额", width = 15)
    @ApiModelProperty(value = "贷记卡年初透支本金余额")
	private java.math.BigDecimal nctzckye;
	/**贷记卡本月透支本金余额*/
	@Excel(name = "贷记卡本月透支本金余额", width = 15)
    @ApiModelProperty(value = "贷记卡本月透支本金余额")
	private java.math.BigDecimal bytzckye;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}

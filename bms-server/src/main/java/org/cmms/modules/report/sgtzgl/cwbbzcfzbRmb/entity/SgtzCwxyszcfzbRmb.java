package org.cmms.modules.report.sgtzgl.cwbbzcfzbRmb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务报表资产负债表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cwxyszcfzb_rmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwxyszcfzb_rmb对象", description="财务报表资产负债表-人民币")
public class SgtzCwxyszcfzbRmb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**资产*/
	@Excel(name = "资产", width = 15)
	@ApiModelProperty(value = "资产")
	private String zc;
	/**次行*/
	@Excel(name = "次行", width = 15)
	@ApiModelProperty(value = "次行")
	private String hc1;
	/**资产期末余额*/
	@Excel(name = "资产期末余额", width = 15)
	@ApiModelProperty(value = "资产期末余额")
	private java.math.BigDecimal zcqmye;
	/**年初余额*/
	@Excel(name = "资产年初余额", width = 15)
	@ApiModelProperty(value = "资产年初余额")
	private java.math.BigDecimal zcncye;
	/**负债和所有者权益*/
	@Excel(name = "负债和所有者权益", width = 15)
	@ApiModelProperty(value = "负债和所有者权益")
	private String fzhsyzqy;
	/**次行*/
	@Excel(name = "次行", width = 15)
	@ApiModelProperty(value = "次行")
	private String hc2;
	/**负债年初余额*/
	@Excel(name = "负债年初余额", width = 15)
	@ApiModelProperty(value = "负债年初余额")
	private java.math.BigDecimal fzncye;
	/**负债期末余额*/
	@Excel(name = "负债期末余额", width = 15)
	@ApiModelProperty(value = "负债期末余额")
	//@ExcelVerify(interHandler = true)
	private java.math.BigDecimal fzqmye;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;



}

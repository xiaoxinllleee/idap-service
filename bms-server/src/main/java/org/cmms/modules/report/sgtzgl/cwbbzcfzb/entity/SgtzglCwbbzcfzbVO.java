package org.cmms.modules.report.sgtzgl.cwbbzcfzb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 财务报表资产负债表
 * @Author: jeecg-boot
 * @Date:   2022-08-27
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwxyszcfzb_bwb对象", description="财务报表资产负债表")
public class SgtzglCwbbzcfzbVO {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "主键ID")
	private String id;
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
	/**负债期末余额*/
	@Excel(name = "负债期末余额", width = 15)
	@ApiModelProperty(value = "负债期末余额")
	private java.math.BigDecimal fzqmye;
	/**负债年初余额*/
	@Excel(name = "负债年初余额", width = 15)
	@ApiModelProperty(value = "负债年初余额")
	private java.math.BigDecimal fzncye;


}

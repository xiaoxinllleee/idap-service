package org.cmms.modules.report.sgtzgl.ywzkbbwrmb.entity;

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
 * @Description: 浏阳农商行_业务状况表月报_表外人民币 导入实体类
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class YwzkbBwrmbVO {

	/**数据日期*/
	private String fiscalDate;
	/**机构编码*/
	private String branchNo;
	/**机构名称(1)*/
	@Excel(name = "机构名称", width = 15)
	@ExcelVerify(notNull = true)
	private String branchName;
	/**机构名称(2)*/
	@Excel(name = "机构名称(1)", width = 15)
	private String branchName1;

	/**承兑汇票:期初借方余额*/
	@Excel(name = "承兑汇票:期初借方余额", width = 15)
	private java.math.BigDecimal cdhpQcjfye;
	/**承兑汇票:期初贷方余额*/
	@Excel(name = "承兑汇票:期初贷方余额", width = 15)
	private java.math.BigDecimal cdhpQcdfye;
	/**承兑汇票:本期借方发生额*/
	@Excel(name = "承兑汇票:本期借方发生额", width = 15)
	private java.math.BigDecimal cdhpBqjffse;
	/**承兑汇票:本期贷方发生额*/
	@Excel(name = "承兑汇票:本期贷方发生额", width = 15)
	private java.math.BigDecimal cdhpBqdffse;
	/**承兑汇票:期末借方余额*/
	@Excel(name = "承兑汇票:期末借方余额", width = 15)
	private java.math.BigDecimal cdhpQmjfye;
	/**承兑汇票:期末贷方余额*/
	@Excel(name = "承兑汇票:期末贷方余额", width = 15)
	private java.math.BigDecimal cdhpQmdfye;

	/**已核销资产:期初借方余额*/
	@Excel(name = "已核销资产:期初借方余额", width = 15)
	private java.math.BigDecimal yhxzcQcjfye;
	/**已核销资产:期初贷方余额*/
	@Excel(name = "已核销资产:期初贷方余额", width = 15)
	private java.math.BigDecimal yhxzcQcdfye;
	/**已核销资产:本期借方发生额*/
	@Excel(name = "已核销资产:本期借方发生额", width = 15)
	private java.math.BigDecimal yhxzcBqjffse;
	/**已核销资产:本期贷方发生额*/
	@Excel(name = "已核销资产:本期贷方发生额", width = 15)
	private java.math.BigDecimal yhxzcBqdffse;
	/**已核销资产:期末借方余额*/
	@Excel(name = "已核销资产:期末借方余额", width = 15)
	private java.math.BigDecimal yhxzcQmjfye;
	/**已核销资产:期末贷方余额*/
	@Excel(name = "已核销资产:期末贷方余额", width = 15)
	private java.math.BigDecimal yhxzcQmdfye;

	/**已置换资产:期初借方余额*/
	@Excel(name = "已置换资产:期初借方余额", width = 15)
	private java.math.BigDecimal yzhzcQcjfye;
	/**已置换资产:期初贷方余额*/
	@Excel(name = "已置换资产:期初贷方余额", width = 15)
	private java.math.BigDecimal yzhzcQcdfye;
	/**已置换资产:本期借方发生额*/
	@Excel(name = "已置换资产:本期借方发生额", width = 15)
	private java.math.BigDecimal yzhzcBqjffse;
	/**已置换资产:本期借方发生额(1)*/
	@Excel(name = "已置换资产:本期借方发生额(1)", width = 15)
	private java.math.BigDecimal yzhzcBqjffse1;
	/**已置换资产:本期贷方发生额*/
	@Excel(name = "已置换资产:本期贷方发生额", width = 15)
	private java.math.BigDecimal yzhzcBqdffse;
	/**已置换资产:期末借方余额*/
	@Excel(name = "已置换资产:期末借方余额", width = 15)
	private java.math.BigDecimal yzhzcQmjfye;
	/**已置换资产:期末贷方余额*/
	@Excel(name = "已置换资产:期末贷方余额", width = 15)
	private java.math.BigDecimal yzhzcQmdfye;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}

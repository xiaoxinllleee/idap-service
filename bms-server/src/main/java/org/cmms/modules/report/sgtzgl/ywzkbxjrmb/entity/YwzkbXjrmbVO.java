package org.cmms.modules.report.sgtzgl.ywzkbxjrmb.entity;

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
 * @Description: 浏阳农商行_业务状况表月报_现金人民币 导入实体类
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class YwzkbXjrmbVO {

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

	/**现金:期初借方余额*/
	@Excel(name = "现金:期初借方余额", width = 15)
	private java.math.BigDecimal xjQcjfye;
	/**现金:期初贷方余额*/
	@Excel(name = "现金:期初贷方余额", width = 15)
	private java.math.BigDecimal xjQcdfye;
	/**现金:本期借方发生额*/
	@Excel(name = "现金:本期借方发生额", width = 15)
	private java.math.BigDecimal xjBqjffse;
	/**现金:本期贷方发生额*/
	@Excel(name = "现金:本期贷方发生额", width = 15)
	private java.math.BigDecimal xjBqdffse;
	/**现金:期末借方余额*/
	@Excel(name = "现金:期末借方余额", width = 15)
	private java.math.BigDecimal xjQmjfye;
	/**现金:期末贷方余额*/
	@Excel(name = "现金:期末贷方余额", width = 15)
	private java.math.BigDecimal xjQmdfye;

	/**应收利息:期初借方余额*/
	@Excel(name = "应收利息:期初借方余额", width = 15)
	private java.math.BigDecimal yslxQcjfye;
	/**应收利息:期初贷方余额*/
	@Excel(name = "应收利息:期初贷方余额", width = 15)
	private java.math.BigDecimal yslxQcdfye;
	/**应收利息:本期借方发生额*/
	@Excel(name = "应收利息:本期借方发生额", width = 15)
	private java.math.BigDecimal yslxBqjffse;
	/**应收利息:本期贷方发生额*/
	@Excel(name = "应收利息:本期贷方发生额", width = 15)
	private java.math.BigDecimal yslxBqdffse;
	/**应收利息:期末借方余额*/
	@Excel(name = "应收利息:期末借方余额", width = 15)
	private java.math.BigDecimal yslxQmjfye;
	/**应收利息:期末贷方余额*/
	@Excel(name = "应收利息:期末贷方余额", width = 15)
	private java.math.BigDecimal yslxQmdfye;

	/**贴现资产:期初借方余额*/
	@Excel(name = "贴现资产:期初借方余额", width = 15)
	private java.math.BigDecimal txzcQcjfye;
	/**贴现资产:期初贷方余额*/
	@Excel(name = "贴现资产:期初贷方余额", width = 15)
	private java.math.BigDecimal txzcQcdfye;
	/**贴现资产:本期借方发生额*/
	@Excel(name = "贴现资产:本期借方发生额", width = 15)
	private java.math.BigDecimal txzcBqjffse;
	/**贴现资产:本期贷方发生额*/
	@Excel(name = "贴现资产:本期贷方发生额", width = 15)
	private java.math.BigDecimal txzcBqdffse;
	/**贴现资产:期末借方余额*/
	@Excel(name = "贴现资产:期末借方余额", width = 15)
	private java.math.BigDecimal txzcQmjfye;
	/**贴现资产:期末贷方余额*/
	@Excel(name = "贴现资产:期末贷方余额", width = 15)
	private java.math.BigDecimal txzcQmdfye;

	/**各项贷款:期初借方余额*/
	@Excel(name = "各项贷款:期初借方余额", width = 15)
	private java.math.BigDecimal gxdkQcjfye;
	/**各项贷款:期初贷方余额*/
	@Excel(name = "各项贷款:期初贷方余额", width = 15)
	private java.math.BigDecimal gxdkQcdfye;
	/**各项贷款:本期借方发生额*/
	@Excel(name = "各项贷款:本期借方发生额", width = 15)
	private java.math.BigDecimal gxdkBqjffse;
	/**各项贷款:本期贷方发生额*/
	@Excel(name = "各项贷款:本期贷方发生额", width = 15)
	private java.math.BigDecimal gxdkBqdffse;
	/**各项贷款:期末借方余额*/
	@Excel(name = "各项贷款:期末借方余额", width = 15)
	private java.math.BigDecimal gxdkQmjfye;
	/**各项贷款:期末贷方余额*/
	@Excel(name = "各项贷款:期末贷方余额", width = 15)
	private java.math.BigDecimal gxdkQmdfye;

	/**农业贷款小计:期初借方余额*/
	@Excel(name = "农业贷款小计:期初借方余额", width = 15)
	private java.math.BigDecimal nydkxjQcjfye;
	/**农业贷款小计:期初贷方余额*/
	@Excel(name = "农业贷款小计:期初贷方余额", width = 15)
	private java.math.BigDecimal nydkxjQcdfye;
	/**农业贷款小计:本期借方发生额*/
	@Excel(name = "农业贷款小计:本期借方发生额", width = 15)
	private java.math.BigDecimal nydkxjBqjffse;
	/**农业贷款小计:本期贷方发生额*/
	@Excel(name = "农业贷款小计:本期贷方发生额", width = 15)
	private java.math.BigDecimal nydkxjBqdffse;
	/**农业贷款小计:期末借方余额*/
	@Excel(name = "农业贷款小计:期末借方余额", width = 15)
	private java.math.BigDecimal nydkxjQmjfye;
	/**农业贷款小计:期末贷方余额*/
	@Excel(name = "农业贷款小计:期末贷方余额", width = 15)
	private java.math.BigDecimal nydkxjQmdfye;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}

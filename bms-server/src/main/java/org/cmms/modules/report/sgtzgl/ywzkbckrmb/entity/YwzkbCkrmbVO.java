package org.cmms.modules.report.sgtzgl.ywzkbckrmb.entity;

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
 * @Description: 浏阳农商行_业务状况表月报_存款人民币 导入实体类
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class YwzkbCkrmbVO {

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

	/**单位活期存款:期初借方余额*/
	@Excel(name = "单位活期存款:期初借方余额", width = 15)
	private java.math.BigDecimal dwhqckQcjfye;
	/**单位活期存款:期初贷方余额*/
	@Excel(name = "单位活期存款:期初贷方余额", width = 15)
	private java.math.BigDecimal dwhqckQcdfye;
	/**单位活期存款:本期借方发生额*/
	@Excel(name = "单位活期存款:本期借方发生额", width = 15)
	private java.math.BigDecimal dwhqckBqjffse;
	/**单位活期存款:本期贷方发生额*/
	@Excel(name = "单位活期存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal dwhqckBqdffse;
	/**单位活期存款:期末借方余额*/
	@Excel(name = "单位活期存款:期末借方余额", width = 15)
	private java.math.BigDecimal dwhqckQmjfye;
	/**单位活期存款:期末贷方余额*/
	@Excel(name = "单位活期存款:期末贷方余额", width = 15)
	private java.math.BigDecimal dwhqckQmdfye;

	/**个人活期存款:期初借方余额*/
	@Excel(name = "个人活期存款:期初借方余额", width = 15)
	private java.math.BigDecimal grhqckQcjfye;
	/**个人活期存款:期初贷方余额*/
	@Excel(name = "个人活期存款:期初贷方余额", width = 15)
	private java.math.BigDecimal grhqckQcdfye;
	/**个人活期存款:本期借方发生额*/
	@Excel(name = "个人活期存款:本期借方发生额", width = 15)
	private java.math.BigDecimal grhqckBqjffse;
	/**个人活期存款:本期贷方发生额*/
	@Excel(name = "个人活期存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal grhqckBqdffse;
	/**个人活期存款:期末借方余额*/
	@Excel(name = "个人活期存款:期末借方余额", width = 15)
	private java.math.BigDecimal grhqckQmjfye;
	/**个人活期存款:期末贷方余额*/
	@Excel(name = "个人活期存款:期末贷方余额", width = 15)
	private java.math.BigDecimal grhqckQmdfye;

	/**银行卡存款:期初借方余额*/
	@Excel(name = "银行卡存款:期初借方余额", width = 15)
	private java.math.BigDecimal yhkckQcjfye;
	/**银行卡存款:期初贷方余额*/
	@Excel(name = "银行卡存款:期初贷方余额", width = 15)
	private java.math.BigDecimal yhkckQcdfye;
	/**银行卡存款:本期借方发生额*/
	@Excel(name = "银行卡存款:本期借方发生额", width = 15)
	private java.math.BigDecimal yhkckBqjffse;
	/**银行卡存款:本期贷方发生额*/
	@Excel(name = "银行卡存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal yhkckBqdffse;
	/**银行卡存款:期末借方余额*/
	@Excel(name = "银行卡存款:期末借方余额", width = 15)
	private java.math.BigDecimal yhkckQmjfye;
	/**银行卡存款:期末贷方余额*/
	@Excel(name = "银行卡存款:期末贷方余额", width = 15)
	private java.math.BigDecimal yhkckQmdfye;

	/**财政性存款:期初借方余额*/
	@Excel(name = "财政性存款:期初借方余额", width = 15)
	private java.math.BigDecimal czxckQcjfye;
	/**财政性存款:期初贷方余额*/
	@Excel(name = "财政性存款:期初贷方余额", width = 15)
	private java.math.BigDecimal czxckQcdfye;
	/**财政性存款:本期借方发生额*/
	@Excel(name = "财政性存款:本期借方发生额", width = 15)
	private java.math.BigDecimal czxckBqjffse;
	/**财政性存款:本期贷方发生额*/
	@Excel(name = "财政性存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal czxckBqdffse;
	/**财政性存款:期末借方余额*/
	@Excel(name = "财政性存款:期末借方余额", width = 15)
	private java.math.BigDecimal czxckQmjfye;
	/**财政性存款:期末贷方余额*/
	@Excel(name = "财政性存款:期末贷方余额", width = 15)
	private java.math.BigDecimal czxckQmdfye;

	/**应解汇款:期初借方余额*/
	@Excel(name = "应解汇款:期初借方余额", width = 15)
	private java.math.BigDecimal yjhkQcjfye;
	/**应解汇款:期初贷方余额*/
	@Excel(name = "应解汇款:期初贷方余额", width = 15)
	private java.math.BigDecimal yjhkQcdfye;
	/**应解汇款:本期借方发生额*/
	@Excel(name = "应解汇款:本期借方发生额", width = 15)
	private java.math.BigDecimal yjhkBqjffse;
	/**应解汇款:本期贷方发生额*/
	@Excel(name = "应解汇款:本期贷方发生额", width = 15)
	private java.math.BigDecimal yjhkBqdffse;
	/**应解汇款:期末借方余额*/
	@Excel(name = "应解汇款:期末借方余额", width = 15)
	private java.math.BigDecimal yjhkQmjfye;
	/**应解汇款:期末贷方余额*/
	@Excel(name = "应解汇款:期末贷方余额", width = 15)
	private java.math.BigDecimal yjhkQmdfye;

	/**保证金存款:期初借方余额*/
	@Excel(name = "保证金存款:期初借方余额", width = 15)
	private java.math.BigDecimal bzjckQcjfye;
	/**保证金存款:期初贷方余额*/
	@Excel(name = "保证金存款:期初贷方余额", width = 15)
	private java.math.BigDecimal bzjckQcdfye;
	/**保证金存款:本期借方发生额*/
	@Excel(name = "保证金存款:本期借方发生额", width = 15)
	private java.math.BigDecimal bzjckBqjffse;
	/**保证金存款:本期贷方发生额*/
	@Excel(name = "保证金存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal bzjckBqdffse;
	/**保证金存款:期末借方余额*/
	@Excel(name = "保证金存款:期末借方余额", width = 15)
	private java.math.BigDecimal bzjckQmjfye;
	/**保证金存款:期末贷方余额*/
	@Excel(name = "保证金存款:期末贷方余额", width = 15)
	private java.math.BigDecimal bzjckQmdfye;

	/**各项存款*/
	@Excel(name = "各项存款", width = 15)
	private java.math.BigDecimal gxck;
	/**各项存款:期初贷方余额*/
	@Excel(name = "各项存款:期初贷方余额", width = 15)
	private java.math.BigDecimal gxckQcdfye;
	/**各项存款:本期借方发生额*/
	@Excel(name = "各项存款:本期借方发生额", width = 15)
	private java.math.BigDecimal gxckBqjffse;
	/**各项存款:本期贷方发生额*/
	@Excel(name = "各项存款:本期贷方发生额", width = 15)
	private java.math.BigDecimal gxckBqdffse;
	/**各项存款:期末借方余额*/
	@Excel(name = "各项存款:期末借方余额", width = 15)
	private java.math.BigDecimal gxckQmjfye;
	/**各项存款:期末贷方余额*/
	@Excel(name = "各项存款:期末贷方余额", width = 15)
	private java.math.BigDecimal gxckQmdfye;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}

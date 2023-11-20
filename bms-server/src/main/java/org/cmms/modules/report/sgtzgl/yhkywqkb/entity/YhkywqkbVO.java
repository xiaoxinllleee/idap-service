package org.cmms.modules.report.sgtzgl.yhkywqkb.entity;

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
 * @Description: 银行卡业务情况表 导入实体类
 * @Author: jeecg-boot
 * @Date:   2023-05-23
 * @Version: V1.0
 */
@Data
public class YhkywqkbVO {

	/**数据日期*/
	//@Excel(name = "数据日期", width = 15)
	private String fiscalDate;
	/**机构编码*/
	//@Excel(name = "机构编码", width = 15)
	private String branchNo;
	/**项目数据名称*/
	@Excel(name = "项目数据名称", width = 15)
	@ExcelVerify(notNull = true)
	private String branchName;
	/**贷记卡年初存款余额*/
	@Excel(name = "贷记卡年初存款余额", width = 15)
	private java.math.BigDecimal ncckye;
	/**贷记卡本月存款余额*/
	@Excel(name = "贷记卡本月存款余额", width = 15)
	private java.math.BigDecimal byckye;
	/**贷记卡年初低息存款余额*/
	@Excel(name = "贷记卡年初低息存款余额", width = 15)
	private java.math.BigDecimal ncdxckye;
	/**贷记卡本月低息存款余额*/
	@Excel(name = "贷记卡本月低息存款余额", width = 15)
	private java.math.BigDecimal bydxckye;
	/**贷记卡年初透支本金余额*/
	@Excel(name = "贷记卡年初透支本金余额", width = 15)
	private java.math.BigDecimal nctzckye;
	/**贷记卡本月透支本金余额*/
	@Excel(name = "贷记卡本月透支本金余额", width = 15)
	private java.math.BigDecimal bytzckye;

	/**创建人*/
	private String createBy;
	/**创建时间*/
	private Date createTime;
	/**修改人*/
	private String updateBy;
	/**修改时间*/
	private Date updateTime;
}

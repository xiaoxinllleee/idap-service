package org.cmms.modules.ywgl.dkyw.lsdksjgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 历史贷款数据管理
 * @Author: jeecg-boot
 * @Date:   2021-09-24
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_LSDKSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_LSDKSJ对象", description="历史贷款数据管理")
@ToString
public class LsdksjglVo {
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	@Dict(dicCode = "khjlbh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String custManagerId;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
	@ApiModelProperty(value = "机构代码")
	private String org;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
	@ApiModelProperty(value = "贷款账号")
	private String acctNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String custName;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String ctfcCd;
	/**科目号*/
	@Excel(name = "记账科目", width = 15)
	@ApiModelProperty(value = "记账科目")
	private String jzkm;
	/**贷款发放日期*/
	@Excel(name = "贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款发放日期")
	private Date putOutDate;
	/**贷款到期日期*/
	@Excel(name = "贷款到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "贷款到期日期")
	private Date maturity;
	/**最早欠息日期*/
	@Excel(name="最早欠息日期", width = 15)
	@ApiModelProperty(value = "最早欠息日期")
	private String minCalcDate;
	/**贷款发放金额*/
	@Excel(name = "贷款发放金额", width = 15)
	@ApiModelProperty(value = "贷款发放金额")
	private java.math.BigDecimal putoutSum;

}

package org.cmms.modules.xyjlcx.sszxgl.ssgl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 诉讼管理
 * @Author: jeecg-boot
 * @Date:   2021-08-16
 * @Version: V1.0
 */
@Data
@TableName("Credit_ssgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_ssgl对象", description="诉讼管理")
public class SsglVO {
    

	/**申请日期*/
	@Excel(name = "申请日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "申请日期")
	private Date sqrq;
	/**合规审查日期*/
	@Excel(name = "合规审查日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合规审查日期")
	private Date hgscrq;
	/**支行联系人*/
	@Excel(name = "支行联系人", width = 15)
    @ApiModelProperty(value = "支行联系人")
	private String zhlxr;
	/**账号/卡号*/
	@Excel(name = "账号/卡号", width = 15)
	@ApiModelProperty(value = "账号/卡号")
	@ExcelVerify(notNull = true)
	private String zh;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal je;
	/**被申请执行人*/
	@Excel(name = "被申请执行人", width = 15)
    @ApiModelProperty(value = "被申请执行人")
	private String bsqzxr;
	/**起诉日期*/
	@Excel(name = "起诉日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "起诉日期")
	private Date qsrq;
	/**生效法律文书号*/
	@Excel(name = "生效法律文书号", width = 15)
    @ApiModelProperty(value = "生效法律文书号")
	private String sxflwsh;
	/**案件受理费*/
	@Excel(name = "案件受理费", width = 15)
    @ApiModelProperty(value = "案件受理费")
	private java.math.BigDecimal ajslf;
	/**律师费*/
	@Excel(name = "律师费", width = 15)
    @ApiModelProperty(value = "律师费")
	private java.math.BigDecimal lsf;
	/**考核单位执行要求*/
	@Excel(name = "考核单位执行要求", width = 15)
    @ApiModelProperty(value = "考核单位执行要求")
	private String khdwzxyq;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
}

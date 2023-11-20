package org.cmms.modules.khxxgl.wbsjgl.bwtzgl.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 表外台账管理
 * @Author: jeecg-boot
 * @Date:   2020-09-16
 * @Version: V1.0
 */
@Data
@TableName("loan_bwdk_sjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_bwdk_sjmx对象", description="表外台账管理 ")
public class LoanBwdkSjmx {

	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private Integer xh;
	/**原机构名称*/
	//@Excel(name = "原贷款机构名称", width = 15)
    @ApiModelProperty(value = "原机构名称")
	private String yjgmc;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String khmc;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "借款日期")
	private Date dkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**转入表外类型*/
	@Excel(name = "转入表外类型", width = 15,dicCode = "zrbwlx")
	@ApiModelProperty(value = "转入表外类型")
	@Dict(dicCode = "zrbwlx")
	private java.lang.Integer zrbwlx;
	/**处置金额（包含本金+利息）*/
	@Excel(name = "处置金额", width = 15)
    @ApiModelProperty(value = "处置金额")
	private java.math.BigDecimal je;
	/**欠息*/
	@Excel(name = "欠息", width = 15)
	@ApiModelProperty(value = "欠息")
	private java.math.BigDecimal qx;
	/**新收回本金*/
	@Excel(name = "新收回本金", width = 15)
	@ApiModelProperty(value = "新收回本金")
	private java.math.BigDecimal shbj;
	/**新收回利息*/
	@Excel(name = "新收回利息", width = 15)
	@ApiModelProperty(value = "新收回利息")
	private java.math.BigDecimal shlx;
	/**处置类型*/
	@Excel(name = "处置类型", width = 15)
	@ApiModelProperty(value = "处置类型")
	private java.lang.String czlx;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private java.math.BigDecimal hxye;

	@ApiModelProperty(value = "转入表外类型")
	@TableField(exist = false)
	private java.lang.String zrbwlxShow;

}

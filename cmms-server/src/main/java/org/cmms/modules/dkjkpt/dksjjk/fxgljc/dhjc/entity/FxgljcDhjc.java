package org.cmms.modules.dkjkpt.dksjjk.fxgljc.dhjc.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷后检查
 * @Author: jeecg-boot
 * @Date:   2023-08-24
 * @Version: V1.0
 */
@Data
@TableName("FXGLJC_DHJC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="FXGLJC_DHJC对象", description="贷后检查")
public class FxgljcDhjc {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户名称*/
	@Excel(name = "客户经理工号", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khjlgh;

	/**单户贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**最新借款日期*/
	@Excel(name = "最新借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "最新借款日期")
	private Date jkrq;
	/**检查到期日期*/
	@Excel(name = "检查到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "检查到期日期")
	private Date dqrq;
	/**附件类型*/
	@Excel(name = "附件类型", width = 15,dicCode="dhjcfjlx")
	@ApiModelProperty(value = "附件类型")
	@Dict(dicCode="dhjcfjlx")
	private String txlx;
	/**未上传报告类型*/
	@Excel(name = "未上传报告类型", width = 15)
	@ApiModelProperty(value = "未上传报告类型")
	private String wsc;
	/**本次年检是否已完成*/
	@Excel(name = "本次年检是否已完成", width = 15)
	@ApiModelProperty(value = "本次年检是否已完成")
	@Dict(dicCode = "sfbz")
	private String bcnjsfywc;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
	/**合同签订日期*/
	@Excel(name = "合同签订日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同签订日期")
	private Date htqdrq;
}

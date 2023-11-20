package org.cmms.modules.dkjkpt.dkjkptfxtsh.entity;

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
 * @Description: 风险提示函
 * @Author: jeecg-boot
 * @Date:   2023-09-22
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_FXTSH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_FXTSH对象", description="风险提示函")
public class DkjkptFxtsh {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**各项存款*/
	@Excel(name = "各项存款", width = 15)
    @ApiModelProperty(value = "各项存款")
	private java.math.BigDecimal gxck;
	/**各项贷款*/
	@Excel(name = "各项贷款", width = 15)
    @ApiModelProperty(value = "各项贷款")
	private java.math.BigDecimal gxdk;
	/**不良贷款*/
	@Excel(name = "不良贷款", width = 15)
    @ApiModelProperty(value = "不良贷款")
	private java.math.BigDecimal bldk;
	/**新增不良贷款笔数*/
	@Excel(name = "新增不良贷款笔数", width = 15)
    @ApiModelProperty(value = "新增不良贷款笔数")
	private Long xzbldkbs;
	/**新增不良贷款余额*/
	@Excel(name = "新增不良贷款余额", width = 15)
    @ApiModelProperty(value = "新增不良贷款余额")
	private java.math.BigDecimal xzbldkye;
	/**逾期贷款余额*/
	@Excel(name = "逾期贷款余额", width = 15)
    @ApiModelProperty(value = "逾期贷款余额")
	private java.math.BigDecimal yqdkye;
	/**新增逾期贷款笔数*/
	@Excel(name = "新增逾期贷款笔数", width = 15)
    @ApiModelProperty(value = "新增逾期贷款笔数")
	private Long xzyqdkbs;
	/**新增逾期贷款余额*/
	@Excel(name = "新增逾期贷款余额", width = 15)
    @ApiModelProperty(value = "新增逾期贷款余额")
	private java.math.BigDecimal xzyqdkye;
	/**金卡逾期笔数*/
	@Excel(name = "金卡逾期笔数", width = 15)
    @ApiModelProperty(value = "金卡逾期笔数")
	private Long jkyqbs;
	/**金卡透支金额*/
	@Excel(name = "金卡透支金额", width = 15)
    @ApiModelProperty(value = "金卡透支金额")
	private java.math.BigDecimal jktzje;
	/**福民卡逾期笔数*/
	@Excel(name = "福民卡逾期笔数", width = 15)
    @ApiModelProperty(value = "福民卡逾期笔数")
	private Long fmkyqbs;
	/**福民金卡透支金额*/
	@Excel(name = "福民金卡透支金额", width = 15)
    @ApiModelProperty(value = "福民金卡透支金额")
	private java.math.BigDecimal fmktzje;
	/**本月至下月即将到期大额贷款*/
	@Excel(name = "本月至下月即将到期大额贷款", width = 15)
    @ApiModelProperty(value = "本月至下月即将到期大额贷款")
	private java.math.BigDecimal dedk;

}

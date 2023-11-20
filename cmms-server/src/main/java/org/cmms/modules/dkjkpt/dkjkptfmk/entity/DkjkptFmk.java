package org.cmms.modules.dkjkpt.dkjkptfmk.entity;

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
 * @Description: 福民卡
 * @Author: jeecg-boot
 * @Date:   2023-09-21
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_FMK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_FMK对象", description="福民卡")
public class DkjkptFmk {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**机构名称*/
	@Excel(name = "机构名称", width = 15,dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
    @ApiModelProperty(value = "机构名称")
	@Dict(dicCode="YWJGDM",dictTable="HR_BAS_ORGANIZATION",dicText="ZZJC")
	private String jgdm;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private String xm;
	/**信用额度*/
	@Excel(name = "信用额度", width = 15)
    @ApiModelProperty(value = "信用额度")
	private java.math.BigDecimal xyed;
	/**逾期期数*/
	@Excel(name = "逾期期数", width = 15)
    @ApiModelProperty(value = "逾期期数")
	private String yqqs;
	/**账单最小还款额*/
	@Excel(name = "账单最小还款额", width = 15)
    @ApiModelProperty(value = "账单最小还款额")
	private java.math.BigDecimal zdzxhke;
	/**透支金额*/
	@Excel(name = "透支金额", width = 15)
    @ApiModelProperty(value = "透支金额")
	private java.math.BigDecimal tzje;
	/**透支本金*/
	@Excel(name = "透支本金", width = 15,groupName = "其中")
    @ApiModelProperty(value = "透支本金")
	private java.math.BigDecimal tzbj;
	/**违约金*/
	@Excel(name = "违约金", width = 15,groupName = "其中")
    @ApiModelProperty(value = "违约金")
	private java.math.BigDecimal wyj;
	/**罚息*/
	@Excel(name = "罚息", width = 15,groupName = "其中")
    @ApiModelProperty(value = "罚息")
	private java.math.BigDecimal fx;
	/**推广人员*/
	@Excel(name = "推广人员", width = 15)
    @ApiModelProperty(value = "推广人员")
	private String tgry;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

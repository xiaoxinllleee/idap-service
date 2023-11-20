package org.cmms.modules.jylrhs.jylrsj.fyftjz.entity;

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
 * @Description: 机构费用上限金额合计
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_jgfysx_jehj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_jgfysx_jehj对象", description="机构费用上限金额合计")
public class JgfysxJehj {

	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**记账年份*/
	@Excel(name = "会计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "会计日期")
	private Date fiscalDate;
	/**记账分类*/
	@Excel(name = "记账分类", width = 15, dicCode = "zbdl")
	@ApiModelProperty(value = "记账分类")
	@Dict(dicCode = "zbdl")
	private String jzfl;
	/**记账科目*/
	@Excel(name = "记账科目", width = 15, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds="jylrhs")
	@ApiModelProperty(value = "记账科目")
	@Dict(dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds="jylrhs")
	private String jzkm;
	/**分摊记账金额*/
	@Excel(name = "分摊记账金额", width = 15)
    @ApiModelProperty(value = "分摊记账金额")
	private java.math.BigDecimal ftjzJe;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
	@ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

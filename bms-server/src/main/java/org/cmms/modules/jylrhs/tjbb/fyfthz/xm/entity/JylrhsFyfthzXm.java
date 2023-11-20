package org.cmms.modules.jylrhs.tjbb.fyfthz.xm.entity;

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
 * @Description: 费用分摊（项目汇总）
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_fyfthz_xm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_fyfthz_xm对象", description="费用分摊（项目汇总）")
public class JylrhsFyfthzXm {

	/**会计日期*/
	@Excel(name = "会计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "会计日期")
	private Date fiscalDate;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	@ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**记账分类*/
	@Excel(name = "记账分类", width = 15, dicCode = "zbdl")
    @ApiModelProperty(value = "记账分类")
	@Dict(dicCode = "zbdl")
	private String jzfl;
	/**一级科目*/
	@Excel(name = "一级科目", width = 15, dicCode = "subject_no1", dictTable = "jylrhs_kmsz", dicText = "subject_name1", ds="jylrhs")
    @ApiModelProperty(value = "一级科目")
	@Dict(dicCode = "subject_no1", dictTable = "jylrhs_kmsz", dicText = "subject_name1", ds="jylrhs")
	private String yjkm;
	/**记账科目*/
	@Excel(name = "记账科目", width = 15, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds="jylrhs")
    @ApiModelProperty(value = "记账科目")
	@Dict(dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds="jylrhs")
	private String jzkm;
	/**月度汇总*/
	@Excel(name = "月度汇总", width = 15)
    @ApiModelProperty(value = "月度汇总")
	private java.math.BigDecimal hzM;
	/**季度汇总*/
	@Excel(name = "季度汇总", width = 15)
    @ApiModelProperty(value = "季度汇总")
	private java.math.BigDecimal hzQ;
	/**年度汇总*/
	@Excel(name = "年度汇总", width = 15)
    @ApiModelProperty(value = "年度汇总")
	private java.math.BigDecimal hzY;
	/**统计时间*/
	@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "统计时间")
	private Date tjsj;

	/**操作类型*/
	//@Excel(name = "操作类型", width = 15)
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	//@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	//@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

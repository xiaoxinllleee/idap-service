package org.cmms.modules.jylrhs.csgl.jgkmsz.entity;

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
 * @Description: 机构科目设置
 * @Author: jeecg-boot
 * @Date:   2023-06-29
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_kmsz_jg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_kmsz_jg对象", description="机构科目设置")
public class JylrhsKmszJg {

	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**一级科目号*/
	@Excel(name = "一级科目", width = 15, dicCode = "subject_no1", dictTable = "jylrhs_kmsz", dicText = "subject_name1", ds = "jylrhs")/**/
    @ApiModelProperty(value = "一级科目号")
	@Dict(dicCode = "subject_no1", dictTable = "jylrhs_kmsz", dicText = "subject_name1", ds = "jylrhs")
	private String subjectNo1;
	/**二级科目号*/
	@Excel(name = "二级科目", width = 15, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs")/**/
    @ApiModelProperty(value = "二级科目号")
	@Dict(dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs")
	private String subjectNo2;
	/**上限额度*/
	@Excel(name = "上限额度(元)", width = 15)
    @ApiModelProperty(value = "上限额度")
	private java.math.BigDecimal sxed;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
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
	@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

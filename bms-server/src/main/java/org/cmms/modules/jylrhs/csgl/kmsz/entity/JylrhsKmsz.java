package org.cmms.modules.jylrhs.csgl.kmsz.entity;

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
 * @Description: 经营利润核算（科目设置）
 * @Author: jeecg-boot
 * @Date:   2023-06-06
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_kmsz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_kmsz对象", description="经营利润核算（科目设置）")
public class JylrhsKmsz {

	/**收支分类*/
	@Excel(name = "收支分类", width = 15, dicCode = "zbdl")
    @ApiModelProperty(value = "收支分类")
	@Dict(dicCode = "zbdl")
	private String szfl;
	/**统计分类*/
	@Excel(name = "统计分类", width = 15, dicCode = "zbxl")
    @ApiModelProperty(value = "统计分类")
	@Dict(dicCode = "zbxl")
	private String tjfl;
	/**一级科目号*/
	@Excel(name = "一级科目号", width = 15)
    @ApiModelProperty(value = "一级科目号")
	private String subjectNo1;
	/**一级科目名*/
	@Excel(name = "一级科目名", width = 15)
    @ApiModelProperty(value = "一级科目名")
	private String subjectName1;
	/**二级科目号*/
	@Excel(name = "二级科目号", width = 15)
    @ApiModelProperty(value = "二级科目号")
	private String subjectNo2;
	/**二级科目名*/
	@Excel(name = "二级科目名", width = 15)
    @ApiModelProperty(value = "二级科目名")
	private String subjectName2;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**操作员*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "操作员")
	private String operator;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作时间*/
	@Excel(name = "录入/修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

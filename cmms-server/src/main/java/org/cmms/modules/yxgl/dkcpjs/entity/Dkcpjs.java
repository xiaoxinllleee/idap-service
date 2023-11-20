package org.cmms.modules.yxgl.dkcpjs.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款产品介绍
 * @Author: jeecg-boot
 * @Date:   2023-07-03
 * @Version: V1.0
 */
@Data
@TableName("YXGL_DKCPJS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXGL_DKCPJS对象", description="贷款产品介绍")
public class Dkcpjs {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**贷款产品大类*/
	@Excel(name = "贷款品种大类", width = 15)
    @ApiModelProperty(value = "贷款品种大类")
	private java.lang.String dkcpdl;
	/**贷款产品小类1*/
	@Excel(name = "贷款品种小类1", width = 15)
    @ApiModelProperty(value = "贷款品种小类1")
	private java.lang.String dkcpxl;
	/**贷款产品小类2*/
	@Excel(name = "贷款品种小类2", width = 15)
    @ApiModelProperty(value = "贷款品种小类2")
	private java.lang.String dkcpzl;
	/**一年期以内(含)*/
	@Excel(name = "一年以内（含）", width = 15)
    @ApiModelProperty(value = "一年以内（含）")
	private java.lang.String ynqll;
	/**二年期*/
		@Excel(name = "二年", width = 15)
    @ApiModelProperty(value = "二年")
	private java.lang.String enqll;
	/**三年期*/
	@Excel(name = "三年", width = 15)
    @ApiModelProperty(value = "三年 ")
	private java.lang.String snqll;
	/**三年至五年期*/
	@Excel(name = "三年至五年（含）", width = 15)
    @ApiModelProperty(value = "三年至五年（含）")
	private java.lang.String sdwnqll;
	/**五年以上*/
	@Excel(name = "五年以上", width = 15)
    @ApiModelProperty(value = "五年以上")
	private java.lang.String wnqysll;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;

	/**附件路径*/
	@Excel(name = "附件路径", width = 15)
	@ApiModelProperty(value = "附件路径")
	private java.lang.String fjlj;

	/**PDF路径*/
	@Excel(name = "PDF路径", width = 15)
	@ApiModelProperty(value = "PDF路径")
	private java.lang.String pdflj;
	/**PDF名称*/
	@Excel(name = "PDF名称", width = 15)
	@ApiModelProperty(value = "PDF名称")
	private java.lang.String pdfname;

	/**pdftplj路径*/
	@Excel(name = "pdftplj路径", width = 15)
	@ApiModelProperty(value = "pdftplj路径")
	private java.lang.String pdftplj;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private java.util.Date updateTime;
}

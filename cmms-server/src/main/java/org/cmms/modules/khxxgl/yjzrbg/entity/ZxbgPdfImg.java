package org.cmms.modules.khxxgl.yjzrbg.entity;

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
 * @Description: 征信报告转PDF
 * @Author: jeecg-boot
 * @Date:   2023-04-17
 * @Version: V1.0
 */
@Data
@TableName("ZXBG_PDF_IMG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZXBG_PDF_IMG对象", description="征信报告转PDF")
public class ZxbgPdfImg {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
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
	/**pdf访问路径*/
	@Excel(name = "pdf访问路径", width = 15)
    @ApiModelProperty(value = "pdf访问路径")
	private String fwlj;
	/**pdf物理路径*/
	@Excel(name = "pdf物理路径", width = 15)
    @ApiModelProperty(value = "pdf物理路径")
	private String wljl;
	/**图片访问路径*/
	@Excel(name = "图片访问路径", width = 15)
    @ApiModelProperty(value = "图片访问路径")
	private String imgFwlj;
	/**图片物理路径*/
	@Excel(name = "图片物理路径", width = 15)
    @ApiModelProperty(value = "图片物理路径")
	private String imgWllj;
	/**pdf转化为图片的数量*/
	@Excel(name = "pdf转化为图片的数量", width = 15)
    @ApiModelProperty(value = "pdf转化为图片的数量")
	private String imgNumber;
	/**查询时间*/
	@Excel(name = "查询时间", width = 15)
    @ApiModelProperty(value = "查询时间")
	private String queryTime;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String sjhm;
}

package org.cmms.modules.qxfk.entity;

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
 * @Description: 七星风控
 * @Author: jeecg-boot
 * @Date:   2022-07-28
 * @Version: V1.0
 */
@Data
@TableName("QXFK_PDF_IMG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="QXFK_PDF_IMG对象", description="七星风控")
public class QxfkPdfImg {
    
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
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**报告编码*/
	@Excel(name = "报告编码", width = 15)
    @ApiModelProperty(value = "报告编码")
	private String reportCode;
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
	@Dict(dicCode = "qxfk_zw")
	private String riskLevel;
	private String riskLevelCode;
	private String queryTime;
	private String hhbm;
	private String khmc;
	private String sjhm;
}

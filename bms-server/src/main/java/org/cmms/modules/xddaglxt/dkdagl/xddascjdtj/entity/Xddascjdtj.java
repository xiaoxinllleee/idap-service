package org.cmms.modules.xddaglxt.dkdagl.xddascjdtj.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 信贷档案上传进度统计
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("V_XDDAGL_DASCJD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_XDDAGL_DASCJD对象", description="信贷档案上传进度统计")
public class Xddascjdtj {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String jgdm;
	/**总档案数*/
	@Excel(name = "总档案数", width = 15)
    @ApiModelProperty(value = "总档案数")
	private Long zdas;
	/**已上传数*/
	@Excel(name = "已上交数", width = 15)
    @ApiModelProperty(value = "已上交数")
	@ExcelVerify(interHandler = true)
	private Long ysjs;
	/**未上传数*/
	@Excel(name = "未上交数", width = 15)
    @ApiModelProperty(value = "未上交数")
	private Integer wsjs;
	/**已上传数*/
	@Excel(name = "已上传数", width = 15)
    @ApiModelProperty(value = "已上传数")
	private Long yscs;
	/**未上传数*/
	@Excel(name = "未上传数", width = 15)
    @ApiModelProperty(value = "未上传数")
	private Integer wscs;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
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

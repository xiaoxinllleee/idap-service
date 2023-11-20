package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 家庭成员移除log
 * @Author: jeecg-boot
 * @Date:   2023-06-05
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_JTCYYC_LOG")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_JTCYYC_LOG对象", description="家庭成员移除log")
public class JtcyYcLog {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**移除日期*/
	@Excel(name = "移除日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "移除日期")
	private java.util.Date ycrq;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	private java.lang.String xm;
	/**身份证号*/
	@Excel(name = "身份证号", width = 15)
    @ApiModelProperty(value = "身份证号")
	private java.lang.String sfzh;
	/**原户号编码*/
	@Excel(name = "原户号编码", width = 15)
    @ApiModelProperty(value = "原户号编码")
	private java.lang.String yhhbm;
	/**原与户主关系*/
	@Excel(name = "原与户主关系", width = 15,dicCode = "yhzgx")
    @ApiModelProperty(value = "原与户主关系")
	@Dict(dicCode = "yhzgx")
	private java.lang.String yyhzgx;
	/**现户号编码*/
	@Excel(name = "现户号编码", width = 15)
    @ApiModelProperty(value = "现户号编码")
	private java.lang.String xhhbm;
	/**移除原因*/
	@Excel(name = "移除原因", width = 15,dicCode = "ycyy"	)
    @ApiModelProperty(value = "移除原因")
	@Dict(dicCode = "ycyy")
	private java.lang.String ycyy;
	/**创建人*/
	@Excel(name = "操作人", width = 15)
    @ApiModelProperty(value = "操作人")
	private java.lang.String createBy;

	@Excel(name = "移除时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "移除时间")
	private java.util.Date createTime;
	/**更新人*/
	/*@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;*/
	/**更新时间*/
	/*@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;*/
}

package org.cmms.modules.zhgl.khrl.entity;

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
 * @Description: 信用卡管户历史表
 * @Author: jeecg-boot
 * @Date:   2022-03-29
 * @Version: V1.0
 */
@Data
@TableName("KHGXGL_XYKKHGHLSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHGXGL_XYKKHGHLSB对象", description="信用卡管户历史表")
public class KhgxglXykkhghlsh {
    
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	private java.lang.String jgdm;
	/**khbh*/
	@Excel(name = "khbh", width = 15)
    @ApiModelProperty(value = "khbh")
	private java.lang.String khbh;
	/**ghlx*/
	@Excel(name = "ghlx", width = 15)
    @ApiModelProperty(value = "ghlx")
	private java.lang.Integer ghlx;
	/**ghr*/
	@Excel(name = "ghr", width = 15)
    @ApiModelProperty(value = "ghr")
	private java.lang.String ghr;
	/**ghbl*/
	@Excel(name = "ghbl", width = 15)
    @ApiModelProperty(value = "ghbl")
	private java.math.BigDecimal ghbl;
	/**ksrq*/
	@Excel(name = "ksrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "ksrq")
	private java.util.Date ksrq;
	/**jsrq*/
	@Excel(name = "jsrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "jsrq")
	private java.util.Date jsrq;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private java.lang.Integer lrbz;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private java.lang.String lrr;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private java.util.Date lrsj;
}

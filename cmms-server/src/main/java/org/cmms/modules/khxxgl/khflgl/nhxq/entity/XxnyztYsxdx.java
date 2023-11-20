package org.cmms.modules.khxxgl.khflgl.nhxq.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @Description: 已授信对象
 * @Author: jeecg-boot
 * @Date:   2022-12-09
 * @Version: V1.0
 */
@Data
@TableName("XXNYZT_YSXDX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XXNYZT_YSXDX对象", description="已授信对象")
public class XxnyztYsxdx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	private String xxnyztId;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**已授信对象*/
	@Excel(name = "已授信对象", width = 15)
    @ApiModelProperty(value = "已授信对象")
	private String ysxdx;
	/**已授信证件号码*/
	@Excel(name = "已授信证件号码", width = 15)
    @ApiModelProperty(value = "已授信证件号码")
	private String ysxdxzjhm;
	private String hhbm;
	/**已授信额度*/
	@Excel(name = "已授信额度", width = 15)
    @ApiModelProperty(value = "已授信额度")
	private BigDecimal ysxed;
	private BigDecimal bwysxed;
	private BigDecimal yyxed;
	/**录入标志（1是系统录入 2是手工录入）*/
	@Excel(name = "录入标志（ 0系统录入 1是手工录入）", width = 15)
    @ApiModelProperty(value = "录入标志（0系统录入 1是手工录入）")
	private String lrbz;
}

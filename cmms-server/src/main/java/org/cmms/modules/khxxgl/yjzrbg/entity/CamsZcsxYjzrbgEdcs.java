package org.cmms.modules.khxxgl.yjzrbg.entity;

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
 * @Description: 额度测算
 * @Author: jeecg-boot
 * @Date:   2023-05-05
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_YJZRBG_EDCS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_YJZRBG_EDCS对象", description="额度测算")
public class CamsZcsxYjzrbgEdcs {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
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
	/**1城区 2非城区*/
	@Excel(name = "1城区 2非城区", width = 15)
    @ApiModelProperty(value = "1城区 2非城区")
	private String type;
	/**收入月份*/
	@Excel(name = "收入月份", width = 15)
    @ApiModelProperty(value = "收入月份")
	private BigDecimal month;
	/**个人月缴存额*/
	@Excel(name = "个人月缴存额", width = 15)
    @ApiModelProperty(value = "个人月缴存额")
	private BigDecimal gryjce;
	/**单位月缴存额*/
	@Excel(name = "单位月缴存额", width = 15)
    @ApiModelProperty(value = "单位月缴存额")
	private BigDecimal dwyjce;
	/**个人缴存比例*/
	@Excel(name = "个人缴存比例", width = 15)
    @ApiModelProperty(value = "个人缴存比例")
	private BigDecimal grjcbl;
	/**单位缴存比例*/
	@Excel(name = "单位缴存比例", width = 15)
    @ApiModelProperty(value = "单位缴存比例")
	private BigDecimal dwjcbl;
	/**年收入*/
	@Excel(name = "年收入", width = 15)
    @ApiModelProperty(value = "年收入")
	private BigDecimal nsr;
	/**公积金*/
	@Excel(name = "公积金", width = 15)
    @ApiModelProperty(value = "公积金")
	private BigDecimal gjj;
	/**社保*/
	@Excel(name = "社保", width = 15)
    @ApiModelProperty(value = "社保")
	private BigDecimal sb;
	/**医保*/
	@Excel(name = "医保", width = 15)
    @ApiModelProperty(value = "医保")
	private BigDecimal yb;
	/**已有融资总额*/
	@Excel(name = "已有融资总额", width = 15)
    @ApiModelProperty(value = "已有融资总额")
	private BigDecimal yyrzze;
	/**信用贷款额度*/
	@Excel(name = "信用贷款额度", width = 15)
    @ApiModelProperty(value = "信用贷款额度")
	private BigDecimal xydked;
	/**可计资产价值*/
	@Excel(name = "可计资产价值", width = 15)
    @ApiModelProperty(value = "可计资产价值")
	private BigDecimal kjzcjz;
	/**资产佐证方式 - 已有融资总额*/
	@Excel(name = "资产佐证方式 - 已有融资总额", width = 15)
    @ApiModelProperty(value = "资产佐证方式 - 已有融资总额")
	private BigDecimal yyrzzezc;
	/**信用额度*/
	@Excel(name = "信用额度", width = 15)
    @ApiModelProperty(value = "信用额度")
	private BigDecimal xyed;
	/**汇总额度*/
	@Excel(name = "汇总额度", width = 15)
    @ApiModelProperty(value = "汇总额度")
	private BigDecimal hzed;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
}

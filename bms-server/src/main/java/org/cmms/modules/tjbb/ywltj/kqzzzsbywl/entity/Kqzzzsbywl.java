package org.cmms.modules.tjbb.ywltj.kqzzzsbywl.entity;

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
 * @Description: 卡前置自助设备业务量
 * @Author: jeecg-boot
 * @Date:   2021-08-25
 * @Version: V1.0
 */
@Data
@TableName("TJBB_YWLTJ_KQZZZSBYWL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_YWLTJ_KQZZZSBYWL对象", description="卡前置自助设备业务量")
public class Kqzzzsbywl {
    
	/**统计维度*/
	@Excel(name = "统计维度", width = 15,dicCode = "ywltj_tjwd")
    @ApiModelProperty(value = "统计维度")
	@Dict(dicCode = "ywltj_tjwd")
	private String tjwd;
	/**统计月份*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjyf;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerNo;
	/**终端标识*/
	@Excel(name = "终端标识", width = 15)
    @ApiModelProperty(value = "终端标识")
	private String devId;
	/**终端类型*/
	@Excel(name = "终端类型", width = 15)
    @ApiModelProperty(value = "终端类型")
	private String devType;
	/**业务类型(1-本代本/2-本代他/3-他代本)*/
	@Excel(name = "业务类型", width = 15,dicCode = "trade_type")
    @ApiModelProperty(value = "业务类型")
	@Dict(dicCode = "trade_type")
	private String tradeType;
	/**业务标识*/
	@Excel(name = "业务标识", width = 15)
    @ApiModelProperty(value = "业务标识")
	private String busiFlag;
	/**交易笔数*/
	@Excel(name = "交易笔数", width = 15)
    @ApiModelProperty(value = "交易笔数")
	private Integer transNumber;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String transAmount;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

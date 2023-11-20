package org.cmms.modules.khxxgl.khywxx.qhywxx.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 客户信息管理贷款数据明细全行
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_DKSJMX_QH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_DKSJMX_ZH对象", description="客户信息管理贷款数据明细全行")
public class KhxxglDksjmxQh {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**贷款月日平*/
	@Excel(name = "贷款月日平", width = 15)
    @ApiModelProperty(value = "贷款月日平")
	private java.math.BigDecimal yrp;
	/**贷款年日平*/
	@Excel(name = "贷款年日平", width = 15)
    @ApiModelProperty(value = "贷款年日平")
	private java.math.BigDecimal nrp;
	/**正常余额*/
	@Excel(name = "正常余额", width = 15)
    @ApiModelProperty(value = "正常余额")
	private java.math.BigDecimal zcye;
	/**关注余额*/
	@Excel(name = "关注余额", width = 15)
    @ApiModelProperty(value = "关注余额")
	private java.math.BigDecimal gzye;
	/**次级余额*/
	@Excel(name = "次级余额", width = 15)
    @ApiModelProperty(value = "次级余额")
	private java.math.BigDecimal cjye;
	/**可疑余额*/
	@Excel(name = "可疑余额", width = 15)
    @ApiModelProperty(value = "可疑余额")
	private java.math.BigDecimal kyye;
	/**损失余额*/
	@Excel(name = "损失余额", width = 15)
    @ApiModelProperty(value = "损失余额")
	private java.math.BigDecimal ssye;
	/**年初贷款余额*/
	@Excel(name = "年初贷款余额", width = 15)
    @ApiModelProperty(value = "年初贷款余额")
	private java.math.BigDecimal ncdkye;
	/**年初年日平*/
	@Excel(name = "年初年日平", width = 15)
    @ApiModelProperty(value = "年初年日平")
	private java.math.BigDecimal ncnrp;
	/**年初月日平*/
	@Excel(name = "年初月日平", width = 15)
    @ApiModelProperty(value = "年初月日平")
	private java.math.BigDecimal ncyrp;
	/**上月贷款余额*/
	@Excel(name = "上月贷款余额", width = 15)
    @ApiModelProperty(value = "上月贷款余额")
	private java.math.BigDecimal sydkye;
	/**上月年日平*/
	@Excel(name = "上月年日平", width = 15)
    @ApiModelProperty(value = "上月年日平")
	private java.math.BigDecimal synrp;
	/**上月月日平*/
	@Excel(name = "上月月日平", width = 15)
    @ApiModelProperty(value = "上月月日平")
	private java.math.BigDecimal syyrp;
}

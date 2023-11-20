package org.cmms.modules.ywgl.etcyxgl.qhetcywyxtj.entity;

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
 * @Description: 全行etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_QHETCYWYXTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_QHETCYWYXTJ对象", description="全行etc业务营销统计")
public class Qhetcywyxtj {
    
	/**统计月份*/
	@Excel(name = "数据月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据月份")
	private Date tjyf;
	/**年初数量*/
	@Excel(name = "年初数量", width = 15)
    @ApiModelProperty(value = "年初数量")
	private java.math.BigDecimal ncsl;
	/**年初线上*/
	@Excel(name = "年初线上", width = 15)
    @ApiModelProperty(value = "年初线上")
	private java.math.BigDecimal ncxs;
	/**年初线下*/
	@Excel(name = "年初线下", width = 15)
    @ApiModelProperty(value = "年初线下")
	private java.math.BigDecimal ncxx;
	/**期末数量*/
	@Excel(name = "期末数量", width = 15)
    @ApiModelProperty(value = "期末数量")
	private java.math.BigDecimal qmsl;
	/**期末线上*/
	@Excel(name = "期末线上", width = 15)
    @ApiModelProperty(value = "期末线上")
	private java.math.BigDecimal qmxs;
	/**期末线下*/
	@Excel(name = "期末线下", width = 15)
    @ApiModelProperty(value = "期末线下")
	private java.math.BigDecimal qmxx;
	/**本年净增*/
	@Excel(name = "本年净增", width = 15)
    @ApiModelProperty(value = "本年净增")
	private java.math.BigDecimal bnjz;
	/**线上净增*/
	@Excel(name = "线上净增", width = 15)
    @ApiModelProperty(value = "线上净增")
	private java.math.BigDecimal xsjz;
	/**线下净增*/
	@Excel(name = "线下净增", width = 15)
    @ApiModelProperty(value = "线下净增")
	private java.math.BigDecimal xxjz;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrczy;
}

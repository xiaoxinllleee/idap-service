package org.cmms.modules.pad.nhxxgl.entity;

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
import java.util.List;

/**
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-07-22
 * @Version: V1.0
 */
@Data
@TableName("cams_zcsx_nhfcxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cams_zcsx_nhfcxx对象", description="1")
public class CamsZcsxNhfcxxPadReceive {
    
	/**hmcId*/
	@Excel(name = "hmcId", width = 15)
    @ApiModelProperty(value = "hmcId")
	private String hmcId;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**房产位置*/
	@Excel(name = "房产位置", width = 15)
    @ApiModelProperty(value = "房产位置")
	private String fcwz;
	/**房产数量*/
	@Excel(name = "房产数量", width = 15)
    @ApiModelProperty(value = "房产数量")
	private Integer fcsl;
	/**房产面积*/
	@Excel(name = "房产面积", width = 15)
    @ApiModelProperty(value = "房产面积")
	private String fcmj;
	@Excel(name = "房产单价", width = 15)
	@ApiModelProperty(value = "房产单价")
	private String fcdj;
	/**房产价值*/
	@Excel(name = "房产价值", width = 15)
    @ApiModelProperty(value = "房产价值")
	private java.math.BigDecimal fcjz;
	/**房产性质*/
	@Excel(name = "房产性质", width = 15)
    @ApiModelProperty(value = "房产性质")
	private String fcxz;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**fcbm*/
	@Excel(name = "fcbm", width = 15)
    @ApiModelProperty(value = "fcbm")
	private String fcbm;

	List<CamsZcsxNhfcxxPad> cams;
}

package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 农户房产信息
 * @Author: cmms
 * @Date:   2019-12-03
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHFCXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHFCXX对象", description="农户房产信息")
public class Nhfcxx {

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
	/**房产价值*/
	@Excel(name = "房产价值", width = 15)
    @ApiModelProperty(value = "房产价值")
	private java.math.BigDecimal fcjz;
	/**房产性质*/
	@Excel(name = "房产性质", width = 15,dicCode = "khgl_fcxz")
    @ApiModelProperty(value = "房产性质")
    @Dict(dicCode = "khgl_fcxz")
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
	/**房产编码*/
	@Excel(name = "房产编码", width = 15)
	@ApiModelProperty(value = "fcbm")
	private String fcbm;
	/**房产单价*/
	@Excel(name = "房产单价", width = 15)
	@ApiModelProperty(value = "fcdj")
	private String fcdj;
}

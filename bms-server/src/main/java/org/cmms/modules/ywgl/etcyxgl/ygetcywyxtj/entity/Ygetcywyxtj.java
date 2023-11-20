package org.cmms.modules.ywgl.etcyxgl.ygetcywyxtj.entity;

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
 * @Description: 员工etc业务营销统计
 * @Author: jeecg-boot
 * @Date:   2021-09-28
 * @Version: V1.0
 */
@Data
@TableName("ERP_BAS_YGETCYWYXTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_BAS_YGETCYWYXTJ对象", description="员工etc业务营销统计")
public class Ygetcywyxtj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "网点名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
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
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrczy;
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
	/**上级机构*/
    @ApiModelProperty(value = "上级机构")
	private String sjjg;
}

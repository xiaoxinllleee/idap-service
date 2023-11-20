package org.cmms.modules.ygjx.entity;

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
 * @Description: 其他薪酬
 * @Author: jeecg-boot
 * @Date:   2022-12-04
 * @Version: V1.0
 */
@Data
@TableName("erp_qtxc")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_qtxc对象", description="其他薪酬")
public class ErpQtxc {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**网点*/
	@Excel(name = "网点", width = 15)
    @ApiModelProperty(value = "网点")
	@Dict(dicCode = "zzbz",dictTable = "Hr_bas_organization",dicText = "zzjc")
	private String wd;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**加班工资*/
	@Excel(name = "加班工资", width = 15)
    @ApiModelProperty(value = "加班工资")
	private java.math.BigDecimal jbgz;
	/**守库费*/
	@Excel(name = "守库费", width = 15)
    @ApiModelProperty(value = "守库费")
	private java.math.BigDecimal skf;
	/**先进奖励*/
	@Excel(name = "先进奖励", width = 15)
    @ApiModelProperty(value = "先进奖励")
	private java.math.BigDecimal xjjl;
	/**产假奖励*/
	@Excel(name = "产假奖励", width = 15)
    @ApiModelProperty(value = "产假奖励")
	private java.math.BigDecimal cjjl;
	/**优秀中层奖励*/
	@Excel(name = "优秀中层奖励", width = 15)
    @ApiModelProperty(value = "优秀中层奖励")
	private java.math.BigDecimal yxzcjl;
	/**代收代发*/
	@Excel(name = "代收代发", width = 15)
    @ApiModelProperty(value = "代收代发")
	private java.math.BigDecimal dsdf;
	/**空项1*/
	@Excel(name = "空项1", width = 15)
    @ApiModelProperty(value = "空项1")
	private java.math.BigDecimal kx1;
	/**空项2*/
	@Excel(name = "空项2", width = 15)
    @ApiModelProperty(value = "空项2")
	private java.math.BigDecimal kx2;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**其他薪酬合计*/
	@Excel(name = "其他薪酬合计", width = 15)
    @ApiModelProperty(value = "其他薪酬合计")
	private java.math.BigDecimal qtxchj;
}

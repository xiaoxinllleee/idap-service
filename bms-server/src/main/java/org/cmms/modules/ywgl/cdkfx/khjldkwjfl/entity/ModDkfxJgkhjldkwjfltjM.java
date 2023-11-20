package org.cmms.modules.ywgl.cdkfx.khjldkwjfl.entity;

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
 * @Description: 客户经理贷款五级分类
 * @Author: jeecg-boot
 * @Date:   2021-06-29
 * @Version: V1.0
 */
@Data
@TableName("MOD_DKFX_JGKHJLDKWJFLTJ_M")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MOD_DKFX_JGKHJLDKWJFLTJ_M对象", description="客户经理贷款五级分类")
public class ModDkfxJgkhjldkwjfltjM {

	@Excel(name = "支行名称",width = 15,dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	@Dict(dicCode = "ywjgdm",dictTable = "v_hr_bas_organization",dicText = "ZZJC")
	private String zhjgdm;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
    @ApiModelProperty(value = "客户经理标识")
	private String custid;

	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date beginday;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date endday;
	/**正常余额(元)*/
	@Excel(name = "正常余额(元)", width = 15)
    @ApiModelProperty(value = "正常余额(元)")
	private java.math.BigDecimal zcye;
	/**关注余额(元)*/
	@Excel(name = "关注余额(元)", width = 15)
    @ApiModelProperty(value = "关注余额(元)")
	private java.math.BigDecimal gzye;
	/**次级余额(元)*/
	@Excel(name = "次级余额(元)", width = 15)
    @ApiModelProperty(value = "次级余额(元)")
	private java.math.BigDecimal cjye;
	/**可疑余额(元)*/
	@Excel(name = "可疑余额(元)", width = 15)
    @ApiModelProperty(value = "可疑余额(元)")
	private java.math.BigDecimal kyye;
	/**损失余额(元)*/
	@Excel(name = "损失余额(元)", width = 15)
    @ApiModelProperty(value = "损失余额(元)")
	private java.math.BigDecimal ssye;
	/**zcbs*/
	@Excel(name = "zcbs", width = 15)
    @ApiModelProperty(value = "zcbs")
	private java.math.BigDecimal zcbs;
	/**gzbs*/
	@Excel(name = "gzbs", width = 15)
    @ApiModelProperty(value = "gzbs")
	private java.math.BigDecimal gzbs;
	/**kybs*/
	@Excel(name = "kybs", width = 15)
    @ApiModelProperty(value = "kybs")
	private java.math.BigDecimal kybs;
	/**ssbs*/
	@Excel(name = "ssbs", width = 15)
    @ApiModelProperty(value = "ssbs")
	private java.math.BigDecimal ssbs;
	/**cjbs*/
	@Excel(name = "cjbs", width = 15)
    @ApiModelProperty(value = "cjbs")
	private java.math.BigDecimal cjbs;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private Integer lrbz;
	/**lrczy*/
	@Excel(name = "lrczy", width = 15)
    @ApiModelProperty(value = "lrczy")
	private String lrczy;
}

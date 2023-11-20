package org.cmms.modules.ywgl.nxt.shbb.shpjbb.entity;

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
 * @Description: 商户评级报表
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_SHDJPD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_SHDJPD对象", description="商户评级报表")
public class Shpjbb {
    
	/**评定类型*/
	@Excel(name = "评定类型", width = 15,dicCode = "pdlx")
    @ApiModelProperty(value = "评定类型")
	@Dict(dicCode = "pdlx")
	private String pdlx;
	/**评定季度*/
	@Excel(name = "评定周期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定周期")
	private Date pdzq;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**商户编码*/
	@Excel(name = "商户编码", width = 15)
    @ApiModelProperty(value = "商户编码")
	private String shbm;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**客户经理名称*/
	@Excel(name = "客户经理名称", width = 15)
    @ApiModelProperty(value = "客户经理名称")
	private String khjlbz;
	/**关联账户*/
	@Excel(name = "关联账户", width = 15)
    @ApiModelProperty(value = "关联账户")
	private String glzh;
	/**上期评定日平*/
	@Excel(name = "上期评定日平", width = 15)
    @ApiModelProperty(value = "上期评定日平")
	private java.math.BigDecimal sqpdrp;
	/**上期评定等级*/
	@Excel(name = "上期评定等级", width = 15)
    @ApiModelProperty(value = "上期评定等级")
	private String sqpddj;
	/**本期评定日平*/
	@Excel(name = "本期评定日平", width = 15)
    @ApiModelProperty(value = "本期评定日平")
	private java.math.BigDecimal bqpdrp;
	/**本期评定等级*/
	@Excel(name = "本期评定等级", width = 15)
    @ApiModelProperty(value = "本期评定等级")
	private String bqpddj;
	/**评定日期*/
//	@Excel(name = "评定日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定日期")
	private Date pdrq;
	/**等级是否有调整（1：是 2：否）*/
	@Excel(name = "等级是否有调整", width = 15,dicCode = "sfbz")
    @ApiModelProperty(value = "等级是否有调整")
	@Dict(dicCode = "sfbz")
	private Integer djsfytz;
	/**录入标志（0：导入 1：录入 2：修改）*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入日期")
	private Date lrrq;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String xgr;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改日期")
	private Date xgrq;
}

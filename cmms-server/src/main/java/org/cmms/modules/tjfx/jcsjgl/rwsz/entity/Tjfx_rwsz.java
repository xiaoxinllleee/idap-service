package org.cmms.modules.tjfx.jcsjgl.rwsz.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-05-16
 * @Version: V1.0
 */
@Data
@TableName("TJFX_RWSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_RWSZ对象", description="1")
public class Tjfx_rwsz {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**任务年份*/
	@Excel(name = "任务年份(YYYY-01-01)", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "任务年份")
	private Date rwnf;
	/**组织标识*/
	@Excel(name = "组织机构",dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
    @ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存款户数净增*/
	@Excel(name = "存款户数净增", width = 15)
    @ApiModelProperty(value = "存款户数净增")
	private java.math.BigDecimal ckhsjz;
	/**贷款户数净增*/
	@Excel(name = "贷款户数净增", width = 15)
    @ApiModelProperty(value = "贷款户数净增")
	private java.math.BigDecimal dkhsjz;
	/**贷款不良率*/
	@Excel(name = "贷款不良率(%)", width = 15)
    @ApiModelProperty(value = "贷款不良率")
	private java.math.BigDecimal dkbll;
}

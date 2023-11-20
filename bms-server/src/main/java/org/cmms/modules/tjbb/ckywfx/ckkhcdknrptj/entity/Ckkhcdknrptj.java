package org.cmms.modules.tjbb.ckywfx.ckkhcdknrptj.entity;

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
 * @Description: 存款客户存贷款年日平统计
 * @Author: jeecg-boot
 * @Date:   2021-08-19
 * @Version: V1.0
 */
@Data
@TableName("TJBB_CKYW_QHCKKHCDKNRPTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_CKYW_QHCKKHCDKNRPTJ对象", description="存款客户存贷款年日平统计")
public class Ckkhcdknrptj {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属组织*/
	@Excel(name = "所属组织", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "所属组织")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**授信金额*/
	@Excel(name = "授信金额", width = 15)
	@ApiModelProperty(value = "授信金额")
	private java.math.BigDecimal sxje;
	/**前三年第一年存款日平*/
	@Excel(name = "前三年第一年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第一年存款日平")
	private java.math.BigDecimal qsnDynckrp;
	/**前三年第二年存款日平*/
	@Excel(name = "前三年第二年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第二年存款日平")
	private java.math.BigDecimal qsnDenckrp;
	/**前三年第三年存款日平*/
	@Excel(name = "前三年第三年存款日平", width = 15)
    @ApiModelProperty(value = "前三年第三年存款日平")
	private java.math.BigDecimal qsnDsnckrp;
	/**前三年第一年贷款日平*/
	@Excel(name = "前三年第一年贷款日平", width = 15)
    @ApiModelProperty(value = "前三年第一年贷款日平")
	private java.math.BigDecimal qsnDyndkrp;
	/**前三年第二年贷款日平*/
	@Excel(name = "前三年第二年贷款日平", width = 15)
    @ApiModelProperty(value = "前三年第二年贷款日平")
	private java.math.BigDecimal qsnDendkrp;
	/**前三年第三年贷款日平*/
	@Excel(name = "前三年第三年贷款日平", width = 15)
    @ApiModelProperty(value = "前三年第三年贷款日平")
	private java.math.BigDecimal qsnDsndkrp;

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

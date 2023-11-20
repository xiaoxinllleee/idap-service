package org.cmms.modules.xyjlcx.xybg.cxjltz.entity;

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
 * @Description: 查询记录台账
 * @Author: jeecg-boot
 * @Date:   2021-08-03
 * @Version: V1.0
 */
@Data
@TableName("credit_cxjlmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="credit_cxjlmx对象", description="查询记录台账")
public class Cxjltz {

	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**报告编号*/
	@Excel(name = "报告编号", width = 15)
	@ApiModelProperty(value = "报告编号")
	private String bgbh;
	/**查询日期*/
	@Excel(name = "查询日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "查询日期")
	private Date cxrq;
	/**被查询人证件号码*/
	@Excel(name = "被查询人证件号码", width = 15)
	@ApiModelProperty(value = "被查询人证件号码")
	private String bcxrzjhm;
	/**被查询人姓名*/
	@Excel(name = "被查询人姓名", width = 15)
	@ApiModelProperty(value = "被查询人姓名")
	private String bcxrxm;
	/**操作员机构代码*/
	@Excel(name = "操作员机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "操作员机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String czyjgdm;
	/**查询操作员姓名*/
	@Excel(name = "查询操作员姓名", width = 15)
	@ApiModelProperty(value = "查询操作员姓名")
	private String cxczyxm;
	/**查询原因*/
	@Excel(name = "查询原因", width = 15,dicCode = "cxyy")
	@ApiModelProperty(value = "查询原因")
	@Dict(dicCode = "cxyy")
	private String cxyy;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss ")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**查询人IP*/
	@Excel(name = "查询人IP", width = 15)
	@ApiModelProperty(value = "查询人IP")
	private String cxrip;

	/**查询操作员*/
    @ApiModelProperty(value = "查询操作员")
	private String cxczy;
	//机构名称
	@TableField(exist = false)
	private String jgmc;
	//查询原因名称
	@TableField(exist = false)
	private String cxyyShow;
}

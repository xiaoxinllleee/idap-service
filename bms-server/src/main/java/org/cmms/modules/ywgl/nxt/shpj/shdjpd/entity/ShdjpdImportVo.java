package org.cmms.modules.ywgl.nxt.shpj.shdjpd.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 商户等级评定
 * @Author: jeecg-boot
 * @Date:   2021-09-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_NXT_SHDJPD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_NXT_SHDJPD对象", description="商户等级评定")
public class ShdjpdImportVo {

	/**评定类型*/
	@Excel(name = "评定类型", width = 15,dicCode = "pdlx")
    @ApiModelProperty(value = "评定类型")
	@Dict(dicCode = "pdlx")
	private String pdlx;

	/**评定周期*/
	@Excel(name = "评定周期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评定周期")
	private Date pdzq;


	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
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
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	private String khjlbz;
	/**上期评定日平*/
	@Excel(name = "上期评定日平", width = 15)
	@ApiModelProperty(value = "上期评定日平")
	private java.math.BigDecimal sqpdrp;


	/**等级是否有调整*/
	@Excel(name = "等级是否有调整", width = 15,dicCode = "sfbz")
	@ApiModelProperty(value = "等级是否有调整")
	@Dict(dicCode = "sfbz")
	private Integer djsfytz;


}

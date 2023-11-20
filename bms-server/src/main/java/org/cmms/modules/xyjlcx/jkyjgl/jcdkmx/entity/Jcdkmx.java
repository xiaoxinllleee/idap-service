package org.cmms.modules.xyjlcx.jkyjgl.jcdkmx.entity;

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
 * @Description: 交叉贷款明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@Data
@TableName("Credit_jcdk")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_jcdk对象", description="交叉贷款明细")
public class Jcdkmx {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
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
	/**贷款类型*/
	@Excel(name = "贷款类型", width = 15,dicCode = "dklx")
    @ApiModelProperty(value = "贷款类型")
	@Dict(dicCode = "dklx")
	private String dklx;
	/**授信/用信金额*/
	@Excel(name = "授信/用信金额", width = 15)
    @ApiModelProperty(value = "授信/用信金额")
	private java.math.BigDecimal dkye;
	/**交叉类型*/
	@Excel(name = "交叉类型", width = 15,dicCode = "jclx")
    @ApiModelProperty(value = "交叉类型")
	@Dict(dicCode = "jclx")
	private Integer jclx;
	/**交叉机构代码*/
	@Excel(name = "交叉机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "交叉机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jcjgdm;
	/**交叉客户名称*/
	@Excel(name = "交叉客户名称", width = 15)
    @ApiModelProperty(value = "交叉客户名称")
	private String jckhmc;
	/**交叉证件号码*/
	@Excel(name = "交叉证件号码", width = 15)
    @ApiModelProperty(value = "交叉证件号码")
	private String jczjhm;
	/**交叉贷款类型*/
	@Excel(name = "交叉贷款类型", width = 15, dicCode = "dklx")
    @ApiModelProperty(value = "交叉贷款类型")
	@Dict(dicCode = "dklx")
	private String jcdklx;
	/**交叉余额*/
	@Excel(name = "交叉余额", width = 15)
    @ApiModelProperty(value = "交叉余额")
	private java.math.BigDecimal jcye;
	/**交换标志*/
    @ApiModelProperty(value = "交换标志")
	private Integer jhbz;
}

package org.cmms.modules.yjgl.ygdkywfx.ygghdkdqshl.entity;

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
 * @Description: 员工管户贷款到期收回率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("Tb_dk_ygghdkdqshl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_dk_ygghdkdqshl对象", description="员工管户贷款到期收回率")
public class Ygghdkdqshl {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**员工所属机构代码*/
	@Excel(name = "员工所属机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "员工所属机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ygssjgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**本年应收回金额*/
	@Excel(name = "本年应收回金额", width = 15)
    @ApiModelProperty(value = "本年应收回金额")
	private java.math.BigDecimal bnyshje;
	/**本年收回金额*/
	@Excel(name = "本年收回金额", width = 15)
    @ApiModelProperty(value = "本年收回金额")
	private java.math.BigDecimal bnshje;
	/**本年未收回余额*/
	@Excel(name = "本年未收回余额", width = 15)
    @ApiModelProperty(value = "本年未收回余额")
	private java.math.BigDecimal bnwshye;
	/**本年到期收回率*/
	@Excel(name = "本年到期收回率", width = 15)
    @ApiModelProperty(value = "本年到期收回率")
	private java.math.BigDecimal bndqshl;
	/**历年发放当年到期应收回金额*/
	@Excel(name = "历年发放当年到期应收回金额", width = 15)
    @ApiModelProperty(value = "历年发放当年到期应收回金额")
	private java.math.BigDecimal lnffyshje;
	/**历年发放当年到期收回金额*/
	@Excel(name = "历年发放当年到期收回金额", width = 15)
    @ApiModelProperty(value = "历年发放当年到期收回金额")
	private java.math.BigDecimal lnffshje;
	/**历年发放当年到期未收回余额*/
	@Excel(name = "历年发放当年到期未收回余额", width = 15)
    @ApiModelProperty(value = "历年发放当年到期未收回余额")
	private java.math.BigDecimal lnffwshye;
	/**历年发放到期收回率*/
	@Excel(name = "历年发放到期收回率", width = 15)
    @ApiModelProperty(value = "历年发放到期收回率")
	private java.math.BigDecimal lnffdqshl;
	/**本年发放当年到期应收回金额*/
	@Excel(name = "本年发放当年到期应收回金额", width = 15)
    @ApiModelProperty(value = "本年发放当年到期应收回金额")
	private java.math.BigDecimal bnffyshje;
	/**本年发放当年到期收回金额*/
	@Excel(name = "本年发放当年到期收回金额", width = 15)
    @ApiModelProperty(value = "本年发放当年到期收回金额")
	private java.math.BigDecimal bnffshje;
	/**本年发放当年到期未收回余额*/
	@Excel(name = "本年发放当年到期未收回余额", width = 15)
    @ApiModelProperty(value = "本年发放当年到期未收回余额")
	private java.math.BigDecimal bnffwshye;
	/**本年发放到期收回率*/
	@Excel(name = "本年发放到期收回率", width = 15)
    @ApiModelProperty(value = "本年发放到期收回率")
	private java.math.BigDecimal bnffdqshl;
	/**本月应收回金额*/
	@Excel(name = "本月应收回金额", width = 15)
    @ApiModelProperty(value = "本月应收回金额")
	private java.math.BigDecimal byyshje;
	/**本月收回金额*/
	@Excel(name = "本月收回金额", width = 15)
    @ApiModelProperty(value = "本月收回金额")
	private java.math.BigDecimal byshje;
	/**本月未收回余额*/
	@Excel(name = "本月未收回余额", width = 15)
    @ApiModelProperty(value = "本月未收回余额")
	private java.math.BigDecimal bywshye;
	/**本月到期收回率*/
	@Excel(name = "本月到期收回率", width = 15)
    @ApiModelProperty(value = "本月到期收回率")
	private java.math.BigDecimal bydqshl;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
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

}

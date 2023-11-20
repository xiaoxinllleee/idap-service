package org.cmms.modules.dkjkpt.tjcx.khjldkxxtj.entity;

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
 * @Description: 客户经理贷款信息统计年报
 * @Author: jeecg-boot
 * @Date:   2022-09-26
 * @Version: V1.0
 */
@Data
@TableName("Tb_dk_dyzrrkhjldkxxtjnb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_dk_dyzrrkhjldkxxtjnb对象", description="客户经理贷款信息统计年报")
public class KhjldkxxtjNb {

	/**统计年份*/
	@Excel(name = "统计年份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计年份")
	private Date tjnf;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ApiModelProperty(value = "员工工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	private String yggh;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
    @ApiModelProperty(value = "客户经理编号")
	private String khjlbh;
	/**客户净增数*/
	@Excel(name = "客户净增数", width = 15)
    @ApiModelProperty(value = "客户净增数")
	private java.math.BigDecimal khxzs;
	/**贷款总用户*/
	@Excel(name = "贷款总用户", width = 15)
	@ApiModelProperty(value = "贷款总用户")
	private java.math.BigDecimal dkzyh;
	/**贷款净增余额*/
	@Excel(name = "贷款净增余额", width = 15)
	@ApiModelProperty(value = "贷款净增余额")
	private java.math.BigDecimal dkjzye;

	/**存量贷款总额*/
	@Excel(name = "存量贷款总额", width = 15)
    @ApiModelProperty(value = "存量贷款总额")
	private java.math.BigDecimal cldkze;
	/**存量贷款户数*/
	@Excel(name = "存量贷款户数", width = 15)
    @ApiModelProperty(value = "存量贷款户数")
	private String cldkhs;
	/**存量贷款余额*/
	@Excel(name = "存量贷款余额", width = 15)
    @ApiModelProperty(value = "存量贷款余额")
	private java.math.BigDecimal cldkye;
	/**正常类贷款余额*/
	@Excel(name = "正常类贷款余额", width = 15)
	@ApiModelProperty(value = "正常类贷款余额")
	private java.math.BigDecimal zcldkye;
	/**正常类贷款户数*/
	@Excel(name = "正常类贷款户数", width = 15)
	@ApiModelProperty(value = "正常类贷款户数")
	private String zcldkhs;
	/**正常贷款占比*/
	@Excel(name = "正常贷款占比", width = 15)
	@ApiModelProperty(value = "正常贷款占比")
	private java.math.BigDecimal zcldkzb;
	/**关注类贷款余额*/
	@Excel(name = "关注类贷款余额", width = 15)
	@ApiModelProperty(value = "关注类贷款余额")
	private java.math.BigDecimal gzldkye;

	/**关注类贷款户数*/
	@Excel(name = "关注类贷款户数", width = 15)
    @ApiModelProperty(value = "关注类贷款户数")
	private String gzldkhs;
	/**关注贷款占比*/
	@Excel(name = "关注贷款占比", width = 15)
    @ApiModelProperty(value = "关注贷款占比")
	private java.math.BigDecimal gzdkzb;
	/**不良贷款余额*/
	@Excel(name = "不良贷款余额", width = 15)
    @ApiModelProperty(value = "不良贷款余额")
	private java.math.BigDecimal bldkye;
	/**不良贷款户数*/
	@Excel(name = "不良贷款户数", width = 15)
    @ApiModelProperty(value = "不良贷款户数")
	private String bldkhs;
	/**不良贷款占比*/
	@Excel(name = "不良贷款占比", width = 15)
    @ApiModelProperty(value = "不良贷款占比")
	private java.math.BigDecimal bldkzb;
	/**到期贷款余额*/
	@Excel(name = "到期贷款余额", width = 15)
    @ApiModelProperty(value = "到期贷款余额")
	private java.math.BigDecimal dqdkye;
	/**当期贷款到期收回率*/
	@Excel(name = "当期贷款到期收回率", width = 15)
    @ApiModelProperty(value = "当期贷款到期收回率")
	private java.math.BigDecimal dqdkdqshl;
	/**不良率*/
	@Excel(name = "不良率", width = 15)
    @ApiModelProperty(value = "不良率")
	private java.math.BigDecimal bll;
	/**贷款利息收回额*/
	@Excel(name = "贷款利息收回额", width = 15)
    @ApiModelProperty(value = "贷款利息收回额")
	private java.math.BigDecimal dklxshe;
	/**利息收回率*/
	@Excel(name = "利息收回率", width = 15)
	@ApiModelProperty(value = "利息收回率")
	private java.math.BigDecimal lxshl;
	/**贷款客户存款日平额*/
	@Excel(name = "贷款客户存款日平额", width = 15)
    @ApiModelProperty(value = "贷款客户存款日平额")
	private java.math.BigDecimal dkkhckrpe;
	/**贷款客户贷款日平额*/
	@Excel(name = "贷款客户贷款日平额", width = 15)
    @ApiModelProperty(value = "贷款客户贷款日平额")
	private java.math.BigDecimal dkkhdkrpe;
	/**资金归行率*/
	@Excel(name = "资金归行率", width = 15)
    @ApiModelProperty(value = "资金归行率")
	private java.math.BigDecimal zjghl;
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

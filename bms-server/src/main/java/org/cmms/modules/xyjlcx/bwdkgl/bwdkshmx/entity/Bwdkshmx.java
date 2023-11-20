package org.cmms.modules.xyjlcx.bwdkgl.bwdkshmx.entity;

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
 * @Description: 表外贷款收回明细
 * @Author: jeecg-boot
 * @Date:   2021-08-13
 * @Version: V1.0
 */
@Data
@TableName("Credit_bwdkshmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_bwdkshmx对象", description="表外贷款收回明细")
public class Bwdkshmx {
	/**入账网点*/
	@Excel(name = "入账网点", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "入账网点")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String rzwd;
	/**入账时间*/
	@Excel(name = "入账时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "入账时间")
	private Date rzsj;
	/**清收员工工号*/
	@Excel(name = "清收员工工号", width = 15)
    @ApiModelProperty(value = "清收员工工号")
	private String qsyggh;
	/**清收员工姓名*/
	@Excel(name = "清收员工姓名", width = 15)
	@ApiModelProperty(value = "清收员工姓名")
	private String qskhjl;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**借款人姓名*/
	@Excel(name = "借款人姓名", width = 15)
    @ApiModelProperty(value = "借款人姓名")
	private String jkrxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**收回本息金额*/
	@Excel(name = "收回本息金额", width = 15)
	@ApiModelProperty(value = "收回本息金额")
	private java.math.BigDecimal shbxje;
	/**收回报表本金*/
	@Excel(name = "收回报表本金", width = 15)
    @ApiModelProperty(value = "收回报表本金")
	private java.math.BigDecimal shbbbj;
	/**收回核销本金*/
	@Excel(name = "收回核销本金", width = 15)
    @ApiModelProperty(value = "收回核销本金")
	private java.math.BigDecimal shhxbj;
	/**收回核销利息*/
	@Excel(name = "收回核销利息", width = 15)
    @ApiModelProperty(value = "收回核销利息")
	private java.math.BigDecimal shhxlx;
	/**收回利息*/
	@Excel(name = "收回利息", width = 15)
    @ApiModelProperty(value = "收回利息")
	private java.math.BigDecimal shlx;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**收回日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "收回日期")
	private Date shrq;
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
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;


}

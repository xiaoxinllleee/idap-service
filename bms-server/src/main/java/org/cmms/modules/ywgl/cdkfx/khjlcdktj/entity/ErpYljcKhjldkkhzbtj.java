package org.cmms.modules.ywgl.cdkfx.khjlcdktj.entity;

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
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;


/**
 * @Description: 客户经理指标统计
 * @Author: jeecg-boot
 * @Date:   2021-06-15
 * @Version: V1.0
 */
@Data
@TableName("Erp_yljc_khjldkkhzbtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Erp_yljc_khjldkkhzbtj对象", description="客户经理指标统计")
public class ErpYljcKhjldkkhzbtj implements Serializable {
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private  String jgdm;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;

	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private Date beginday;
//	@Transient
//	@TableField(exist = false)
//	private transient String begindayString;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "结束日期")
	private Date endday;
//	@Transient
//	@TableField(exist = false)
//	private transient String enddayString;
	/**客户经理标识*/
	@Excel(name = "客户经理标识", width = 15)
	@ApiModelProperty(value = "客户经理标识")
	private String custid;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
	@ApiModelProperty(value = "员工工号")
	private String yggh;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
	@ApiModelProperty(value = "员工姓名")
	private String ygxm;
	/**存款日平余额*/
	@Excel(name = "存款日平余额", width = 15)
	@ApiModelProperty(value = "存款日平余额")
	private java.math.BigDecimal ckrpye;
	/**存款年日平余额*/
	@Excel(name = "存款年日平余额", width = 15)
	@ApiModelProperty(value = "存款年日平余额")
	private java.math.BigDecimal cknrpye;
	/**结息账号存款日平余额*/
	@Excel(name = "结息账号存款日平余额", width = 15)
    @ApiModelProperty(value = "结息账号存款日平余额")
	private java.math.BigDecimal jxckrpye;
	/**结息账号存款年日平余额*/
	@Excel(name = "结息账号存款年日平余额", width = 15)
    @ApiModelProperty(value = "结息账号存款年日平余额")
	private java.math.BigDecimal jxcknrpye;
	/**贷款日平余额*/
	@Excel(name = "贷款日平余额", width = 15)
    @ApiModelProperty(value = "贷款日平余额")
	private java.math.BigDecimal dkrpye;
	/**本月收回利息*/
	@Excel(name = "本月收回利息", width = 15)
    @ApiModelProperty(value = "本月收回利息")
	private java.math.BigDecimal byshlx;
	/**权限内贷款户数*/
	@Excel(name = "权限内贷款户数", width = 15)
    @ApiModelProperty(value = "权限内贷款户数")
	private java.math.BigDecimal qxndkhs;
	/**权限外贷款户数*/
	@Excel(name = "权限外贷款户数", width = 15)
    @ApiModelProperty(value = "权限外贷款户数")
	private java.math.BigDecimal qxwdkhs;
	/**权限内贷款笔数*/
	@Excel(name = "权限内贷款笔数", width = 15)
    @ApiModelProperty(value = "权限内贷款笔数")
	private java.math.BigDecimal qxndkbs;
	/**权限外贷款笔数*/
	@Excel(name = "权限外贷款笔数", width = 15)
    @ApiModelProperty(value = "权限外贷款笔数")
	private java.math.BigDecimal qxwdkbs;
	/**贷款当月日平余额*/
	@Excel(name = "贷款当月日平余额", width = 15)
    @ApiModelProperty(value = "贷款当月日平余额")
	private java.math.BigDecimal dkdyrp;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrczy;
}

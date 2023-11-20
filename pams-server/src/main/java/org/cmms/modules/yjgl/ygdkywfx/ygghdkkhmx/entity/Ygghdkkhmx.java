package org.cmms.modules.yjgl.ygdkywfx.ygghdkkhmx.entity;

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
 * @Description: 员工管户贷款客户明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("Tb_dk_ygghdkkhmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_dk_ygghdkkhmx对象", description="员工管户贷款客户明细")
public class Ygghdkkhmx {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**机构号*/
	@Excel(name = "机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**最早贷款发放日期*/
	@Excel(name = "最早贷款发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早贷款发放日期")
	private Date zzffrq;
	/**存量客户贷款余额*/
	@Excel(name = "存量客户贷款余额", width = 15)
    @ApiModelProperty(value = "存量客户贷款余额")
	private java.math.BigDecimal clKhdkye;
	/**客户贷款余额*/
	@Excel(name = "客户贷款余额", width = 15)
    @ApiModelProperty(value = "客户贷款余额")
	private java.math.BigDecimal khdkye;
	/**存量客户月日平1*/
	@Excel(name = "存量客户月日平1", width = 15)
    @ApiModelProperty(value = "存量客户月日平1")
	private java.math.BigDecimal clKhyrp1;
	/**存量客户月日平2*/
	@Excel(name = "存量客户月日平2", width = 15)
    @ApiModelProperty(value = "存量客户月日平2")
	private java.math.BigDecimal clKhyrp2;
	/**存量客户月日平3*/
	@Excel(name = "存量客户月日平3", width = 15)
    @ApiModelProperty(value = "存量客户月日平3")
	private java.math.BigDecimal clKhyrp3;
	/**客户月日平1*/
	@Excel(name = "客户月日平1", width = 15)
    @ApiModelProperty(value = "客户月日平1")
	private java.math.BigDecimal khyrp1;
	/**客户月日平2*/
	@Excel(name = "客户月日平2", width = 15)
    @ApiModelProperty(value = "客户月日平2")
	private java.math.BigDecimal khyrp2;
	/**客户月日平3*/
	@Excel(name = "客户月日平3", width = 15)
    @ApiModelProperty(value = "客户月日平3")
	private java.math.BigDecimal khyrp3;
	/**存量客户季日平1*/
	@Excel(name = "存量客户季日平1", width = 15)
    @ApiModelProperty(value = "存量客户季日平1")
	private java.math.BigDecimal clKhjrp1;
	/**存量客户季日平2*/
	@Excel(name = "存量客户季日平2", width = 15)
    @ApiModelProperty(value = "存量客户季日平2")
	private java.math.BigDecimal clKhjrp2;
	/**存量客户季日平3*/
	@Excel(name = "存量客户季日平3", width = 15)
    @ApiModelProperty(value = "存量客户季日平3")
	private java.math.BigDecimal clKhjrp3;
	/**季日平1*/
	@Excel(name = "季日平1", width = 15)
    @ApiModelProperty(value = "季日平1")
	private java.math.BigDecimal khjrp1;
	/**季日平2*/
	@Excel(name = "季日平2", width = 15)
    @ApiModelProperty(value = "季日平2")
	private java.math.BigDecimal khjrp2;
	/**季日平3*/
	@Excel(name = "季日平3", width = 15)
    @ApiModelProperty(value = "季日平3")
	private java.math.BigDecimal khjrp3;
	/**存量客户年日平1*/
	@Excel(name = "存量客户年日平1", width = 15)
    @ApiModelProperty(value = "存量客户年日平1")
	private java.math.BigDecimal clKhnrp1;
	/**存量客户年日平2*/
	@Excel(name = "存量客户年日平2", width = 15)
    @ApiModelProperty(value = "存量客户年日平2")
	private java.math.BigDecimal clKhnrp2;
	/**存量客户年日平3*/
	@Excel(name = "存量客户年日平3", width = 15)
    @ApiModelProperty(value = "存量客户年日平3")
	private java.math.BigDecimal clKhnrp3;
	/**年日平1*/
	@Excel(name = "年日平1", width = 15)
    @ApiModelProperty(value = "年日平1")
	private java.math.BigDecimal khnrp1;
	/**年日平2*/
	@Excel(name = "年日平2", width = 15)
    @ApiModelProperty(value = "年日平2")
	private java.math.BigDecimal khnrp2;
	/**年日平3*/
	@Excel(name = "年日平3", width = 15)
    @ApiModelProperty(value = "年日平3")
	private java.math.BigDecimal khnrp3;
	/**客户模拟利润*/
	@Excel(name = "客户模拟利润", width = 15)
    @ApiModelProperty(value = "客户模拟利润")
	private java.math.BigDecimal khmnlr;
	/**客户贷款金额*/
	@Excel(name = "客户贷款金额", width = 15)
    @ApiModelProperty(value = "客户贷款金额")
	private java.math.BigDecimal khdkje;
	/**有效客户标志*/
	//@Excel(name = "有效客户标志", width = 15)
    @ApiModelProperty(value = "有效客户标志")
	private String yxkhbz;
	/**存量有效客户标志*/
	//@Excel(name = "存量有效客户标志", width = 15)
    @ApiModelProperty(value = "存量有效客户标志")
	private String clYxkhbz;
	/**贷款标签*/
	//@Excel(name = "贷款标签", width = 15)
    @ApiModelProperty(value = "贷款标签")
	private String dkbq;
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

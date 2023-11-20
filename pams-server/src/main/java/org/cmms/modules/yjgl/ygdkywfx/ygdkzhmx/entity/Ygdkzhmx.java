package org.cmms.modules.yjgl.ygdkywfx.ygdkzhmx.entity;

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
 * @Description: 员工贷款综合明细
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("Tb_dk_ygdkzhsjmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_dk_ygdkzhsjmx对象", description="员工贷款综合明细")
public class Ygdkzhmx {

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
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String custName;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	private String custType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**发放日期*/
	@Excel(name = "发放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "发放日期")
	private Date ffrq;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期")
	private Date qyrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**存量余额*/
	@Excel(name = "存量余额", width = 15)
    @ApiModelProperty(value = "存量余额")
	private java.math.BigDecimal clye;
	/**贷款期限*/
	@Excel(name = "贷款期限", width = 15)
    @ApiModelProperty(value = "贷款期限")
	private String dkqx;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal ll;
	/**成本率*/
	@Excel(name = "成本率", width = 15)
    @ApiModelProperty(value = "成本率")
	private java.math.BigDecimal cbl;
	/**利率差*/
	@Excel(name = "利率差", width = 15)
    @ApiModelProperty(value = "利率差")
	private java.math.BigDecimal llc;
	/**日利率差*/
	@Excel(name = "日利率差", width = 15)
    @ApiModelProperty(value = "日利率差")
	private java.math.BigDecimal rllc;
	/**信贷贷款品种*/
	@Excel(name = "信贷贷款品种", width = 15)
    @ApiModelProperty(value = "信贷贷款品种")
	private String businessPhase;
	/**贷款五级分类(1 正常 2 关注 3 次级 4 可疑 5 损失)*/
	@Excel(name = "贷款五级分类(1 正常 2 关注 3 次级 4 可疑 5 损失)", width = 15)
    @ApiModelProperty(value = "贷款五级分类(1 正常 2 关注 3 次级 4 可疑 5 损失)")
	private String fiveClassType;
	/**存量五级分类*/
	@Excel(name = "存量五级分类", width = 15)
    @ApiModelProperty(value = "存量五级分类")
	private String clFiveClassType;
	/**月初五级分类*/
	@Excel(name = "月初五级分类", width = 15)
    @ApiModelProperty(value = "月初五级分类")
	private String ycFiveClassType;
	/**模拟利润*/
	@Excel(name = "模拟利润", width = 15)
    @ApiModelProperty(value = "模拟利润")
	private java.math.BigDecimal mnlr;
	/**主客户经理员工号*/
	@Excel(name = "主客户经理员工号", width = 15)
    @ApiModelProperty(value = "主客户经理员工号")
	private String custManagerId;
	/**存量贷款月日平1*/
	@Excel(name = "存量贷款月日平1", width = 15)
    @ApiModelProperty(value = "存量贷款月日平1")
	private java.math.BigDecimal clDkyrp1;
	/**存量贷款月日平2*/
	@Excel(name = "存量贷款月日平2", width = 15)
    @ApiModelProperty(value = "存量贷款月日平2")
	private java.math.BigDecimal clDkyrp2;
	/**贷款月日平1*/
	@Excel(name = "贷款月日平1", width = 15)
    @ApiModelProperty(value = "贷款月日平1")
	private java.math.BigDecimal dkyrp1;
	/**贷款月日平2*/
	@Excel(name = "贷款月日平2", width = 15)
    @ApiModelProperty(value = "贷款月日平2")
	private java.math.BigDecimal dkyrp2;
	/**存量贷款季日平1*/
	@Excel(name = "存量贷款季日平1", width = 15)
    @ApiModelProperty(value = "存量贷款季日平1")
	private java.math.BigDecimal clDkjrp1;
	/**存量贷款季日平2*/
	@Excel(name = "存量贷款季日平2", width = 15)
    @ApiModelProperty(value = "存量贷款季日平2")
	private java.math.BigDecimal clDkjrp2;
	/**贷款季日平1*/
	@Excel(name = "贷款季日平1", width = 15)
    @ApiModelProperty(value = "贷款季日平1")
	private java.math.BigDecimal dkjrp1;
	/**贷款季日平2*/
	@Excel(name = "贷款季日平2", width = 15)
    @ApiModelProperty(value = "贷款季日平2")
	private java.math.BigDecimal dkjrp2;
	/**存量贷款年日平1*/
	@Excel(name = "存量贷款年日平1", width = 15)
    @ApiModelProperty(value = "存量贷款年日平1")
	private java.math.BigDecimal clDknrp1;
	/**存量贷款年日平2*/
	@Excel(name = "存量贷款年日平2", width = 15)
    @ApiModelProperty(value = "存量贷款年日平2")
	private java.math.BigDecimal clDknrp2;
	/**贷款年日平1*/
	@Excel(name = "贷款年日平1", width = 15)
    @ApiModelProperty(value = "贷款年日平1")
	private java.math.BigDecimal dknrp1;
	/**贷款年日平2*/
	@Excel(name = "贷款年日平2", width = 15)
    @ApiModelProperty(value = "贷款年日平2")
	private java.math.BigDecimal dknrp2;

	/**存量贷款月日平3*/
	@Excel(name = "存量贷款月日平3", width = 15)
    @ApiModelProperty(value = "存量贷款月日平3")
	private java.math.BigDecimal clDkyrp3;
	/**贷款月日平3*/
	@Excel(name = "贷款月日平3", width = 15)
    @ApiModelProperty(value = "贷款月日平3")
	private java.math.BigDecimal dkyrp3;
	/**存量贷款季日平3*/
	@Excel(name = "存量贷款季日平3", width = 15)
    @ApiModelProperty(value = "存量贷款季日平3")
	private java.math.BigDecimal clDkjrp3;
	/**贷款季日平3*/
	@Excel(name = "贷款季日平3", width = 15)
    @ApiModelProperty(value = "贷款季日平3")
	private java.math.BigDecimal dkjrp3;
	/**存量贷款年日平3*/
	@Excel(name = "存量贷款年日平3", width = 15)
    @ApiModelProperty(value = "存量贷款年日平3")
	private java.math.BigDecimal clDknrp3;
	/**贷款年日平3*/
	@Excel(name = "贷款年日平3", width = 15)
    @ApiModelProperty(value = "贷款年日平3")
	private java.math.BigDecimal dknrp3;
	/**管户比例*/
	@Excel(name = "管户比例", width = 15)
    @ApiModelProperty(value = "管户比例")
	private java.math.BigDecimal ghbl;
	/**月初贷款余额*/
	//@Excel(name = "月初贷款余额", width = 15)
    @ApiModelProperty(value = "月初贷款余额")
	private java.math.BigDecimal ycDkye;
	/**产品大类*/
	//@Excel(name = "产品大类", width = 15)
    @ApiModelProperty(value = "产品大类")
	private String cpdl;
	/**产品小类*/
	//@Excel(name = "产品小类", width = 15)
    @ApiModelProperty(value = "产品小类")
	private String cpxl;
	/**科目号*/
	//@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String subjNo;
	/**贷款标签*/
	//@Excel(name = "贷款标签", width = 15)
    @ApiModelProperty(value = "贷款标签")
	private String dkbq;
	/**录入标志(0 导入 1 录入 2 修改)*/
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

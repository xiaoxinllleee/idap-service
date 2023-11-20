package org.cmms.modules.report.sgtzgl.dkhsdjb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 贷款回收登记簿
 * @Author: jeecg-boot
 * @Date:   2022-09-30
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_DKHSDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_DKHSDJB对象", description="贷款回收登记簿")
public class SgtzglDkhsdjb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**贷款户名*/
	@Excel(name = "贷款户名", width = 15)
    @ApiModelProperty(value = "贷款户名")
	private String dkhm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
	/**贷款帐号*/
	@Excel(name = "贷款帐号", width = 15)
    @ApiModelProperty(value = "贷款帐号")
	private String dkzh;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15)
    @ApiModelProperty(value = "借款日期")
	private String jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String dqrq;
	/**贷款余额(元)*/
	@Excel(name = "贷款余额(元)", width = 15)
    @ApiModelProperty(value = "贷款余额(元)")
	private java.math.BigDecimal dkye;
	/**还款账号*/
	@Excel(name = "还款账号", width = 15)
    @ApiModelProperty(value = "还款账号")
	private String hkzh;
	/**剩余期数*/
	@Excel(name = "剩余期数", width = 15)
    @ApiModelProperty(value = "剩余期数")
	private String syqs;
	/**收回日期*/
	@Excel(name = "收回日期", width = 15)
    @ApiModelProperty(value = "收回日期")
	private String shrq;
	/**收回本金(元)*/
	@Excel(name = "收回本金(元)", width = 15)
    @ApiModelProperty(value = "收回本金(元)")
	private java.math.BigDecimal shbj;
	/**收回利息(元)*/
	@Excel(name = "收回利息(元)", width = 15)
    @ApiModelProperty(value = "收回利息(元)")
	private java.math.BigDecimal shlx;
	/**收回罚息(元)*/
	@Excel(name = "收回罚息(元)", width = 15)
    @ApiModelProperty(value = "收回罚息(元)")
	private java.math.BigDecimal shfx;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	//@ExcelVerify(interHandler = true)
	private String dktx2;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;


}

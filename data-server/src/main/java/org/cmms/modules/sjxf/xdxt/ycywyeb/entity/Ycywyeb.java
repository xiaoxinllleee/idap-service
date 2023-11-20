package org.cmms.modules.sjxf.xdxt.ycywyeb.entity;

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
 * @Description: 银承业务余额表
 * @Author: jeecg-boot
 * @Date:   2021-12-10
 * @Version: V1.0
 */
@Data
@TableName("Cms_businfo_bill")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cms_businfo_bill对象", description="银承业务余额表")
public class Ycywyeb {
    
	/**删除标志*/
	@Excel(name = "删除标志", width = 15)
    @ApiModelProperty(value = "删除标志")
	private String dataFlag;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String getDate;
	/**机构网点号*/
	@Excel(name = "机构网点号", width = 15)
    @ApiModelProperty(value = "机构网点号")
	private String org;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**票据号码*/
	@Excel(name = "票据号码", width = 15)
    @ApiModelProperty(value = "票据号码")
	private String billNo;
	/**票据余额*/
	@Excel(name = "票据余额", width = 15)
    @ApiModelProperty(value = "票据余额")
	private java.math.BigDecimal balance;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**业务标志*/
	@Excel(name = "业务标志", width = 15)
    @ApiModelProperty(value = "业务标志")
	private String kind;
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal rate;
	/**签发日期*/
	@Excel(name = "签发日期", width = 15)
    @ApiModelProperty(value = "签发日期")
	private String putOutDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private String maturity;
	/**表外账号*/
	@Excel(name = "表外账号", width = 15)
    @ApiModelProperty(value = "表外账号")
	private String bwzh;
	/**付款行号*/
	@Excel(name = "付款行号", width = 15)
    @ApiModelProperty(value = "付款行号")
	private String fkhh;
	/**付款行名*/
	@Excel(name = "付款行名", width = 15)
    @ApiModelProperty(value = "付款行名")
	private String fkhm;
	/**出票人名称*/
	@Excel(name = "出票人名称", width = 15)
    @ApiModelProperty(value = "出票人名称")
	private String cprmc;
	/**出票人账号*/
	@Excel(name = "出票人账号", width = 15)
    @ApiModelProperty(value = "出票人账号")
	private String cprzh;
	/**出票人开户行*/
	@Excel(name = "出票人开户行", width = 15)
    @ApiModelProperty(value = "出票人开户行")
	private String cprkhh;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String skrmc;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String skrzh;
	/**收款人开户行*/
	@Excel(name = "收款人开户行", width = 15)
    @ApiModelProperty(value = "收款人开户行")
	private String skrkhh;
	/**票据金额*/
	@Excel(name = "票据金额", width = 15)
    @ApiModelProperty(value = "票据金额")
	private java.math.BigDecimal putoutSum;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String zt;
	/**机构编号*/
	@Excel(name = "机构编号", width = 15)
    @ApiModelProperty(value = "机构编号")
	private String orgId;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String custId;
	/**操作员编号*/
	@Excel(name = "操作员编号", width = 15)
    @ApiModelProperty(value = "操作员编号")
	private String fkId;
	/**业务交易代码*/
	@Excel(name = "业务交易代码", width = 15)
    @ApiModelProperty(value = "业务交易代码")
	private String businessPhase;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String businessNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String custName;
	/**通知书号*/
	@Excel(name = "通知书号", width = 15)
    @ApiModelProperty(value = "通知书号")
	private String sid;
	/**结清日期*/
	@Excel(name = "结清日期", width = 15)
    @ApiModelProperty(value = "结清日期")
	private String cancelDate;
	/**垫款日期*/
	@Excel(name = "垫款日期", width = 15)
    @ApiModelProperty(value = "垫款日期")
	private String laterDate;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15)
    @ApiModelProperty(value = "更新日期")
	private String updateDate;
	/**银承合同号*/
	@Excel(name = "银承合同号", width = 15)
    @ApiModelProperty(value = "银承合同号")
	private String ychth;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String custNo;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**dttime*/
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}

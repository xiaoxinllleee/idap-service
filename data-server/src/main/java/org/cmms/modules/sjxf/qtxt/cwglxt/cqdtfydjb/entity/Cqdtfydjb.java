package org.cmms.modules.sjxf.qtxt.cwglxt.cqdtfydjb.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 长期待摊费用登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_longtermdt_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_longtermdt_reg对象", description="长期待摊费用登记簿")
public class Cqdtfydjb {
    
	/**长期待摊编号*/
	@Excel(name = "长期待摊编号", width = 15)
    @ApiModelProperty(value = "长期待摊编号")
	private String longDtNo;
	/**长期待摊费用 名称*/
	@Excel(name = "长期待摊费用名称", width = 15)
    @ApiModelProperty(value = "长期待摊费用名称")
	private String longDtName;
	/**待摊关联事项号*/
	@Excel(name = "待摊关联事项号", width = 15)
    @ApiModelProperty(value = "待摊关联事项号")
	private String dtAppNo;
	/**待摊类型*/
	@Excel(name = "待摊类型", width = 15)
    @ApiModelProperty(value = "待摊类型")
	private String dtType;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String suppNo;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String suppName;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private String depNo;
	/**记账部门号*/
	@Excel(name = "记账部门号", width = 15)
    @ApiModelProperty(value = "记账部门号")
	private String dcBrNo;
	/**摊销方式*/
	@Excel(name = "摊销方式", width = 15)
    @ApiModelProperty(value = "摊销方式")
	private String depMod;
	/**摊销周期*/
	@Excel(name = "摊销周期", width = 15)
    @ApiModelProperty(value = "摊销周期")
	private String depCyc;
	/**摊销期限(月)*/
	@Excel(name = "摊销期限(月)", width = 15)
    @ApiModelProperty(value = "摊销期限(月)")
	private Integer depTime;
	/**付款账户账号*/
	@Excel(name = "付款账户账号", width = 15)
    @ApiModelProperty(value = "付款账户账号")
	private String payAcNo;
	/**付款账户名称*/
	@Excel(name = "付款账户名称", width = 15)
    @ApiModelProperty(value = "付款账户名称")
	private String payAcName;
	/**支出金额*/
	@Excel(name = "支出金额", width = 15)
    @ApiModelProperty(value = "支出金额")
	private java.math.BigDecimal payAmt;
	/**事由*/
	@Excel(name = "事由", width = 15)
    @ApiModelProperty(value = "事由")
	private String brf;
	/**记账状态*/
	@Excel(name = "记账状态", width = 15)
    @ApiModelProperty(value = "记账状态")
	private String jzFlag;
	/**累计摊销次数*/
	@Excel(name = "累计摊销次数", width = 15)
    @ApiModelProperty(value = "累计摊销次数")
	private Integer totDepCnt;
	/**累计摊销金额*/
	@Excel(name = "累计摊销金额", width = 15)
    @ApiModelProperty(value = "累计摊销金额")
	private java.math.BigDecimal totDepBal;
	/**摊销状态*/
	@Excel(name = "摊销状态", width = 15)
    @ApiModelProperty(value = "摊销状态")
	private String depSts;
	/**待摊账号*/
	@Excel(name = "待摊账号", width = 15)
    @ApiModelProperty(value = "待摊账号")
	private String inAcNo;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private Integer openDate;
	/**当前操作员*/
	@Excel(name = "当前操作员", width = 15)
    @ApiModelProperty(value = "当前操作员")
	private String tel;
	/**记账操作员*/
	@Excel(name = "记账操作员", width = 15)
    @ApiModelProperty(value = "记账操作员")
	private String jzTel;
	/**记账日期*/
	@Excel(name = "记账日期", width = 15)
    @ApiModelProperty(value = "记账日期")
	private Integer jzDate;
	/**操作员流水号*/
	@Excel(name = "操作员流水号", width = 15)
    @ApiModelProperty(value = "操作员流水号")
	private Integer telTraceNo;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer hostTraceNo;
	/**总账流水号*/
	@Excel(name = "总账流水号", width = 15)
    @ApiModelProperty(value = "总账流水号")
	private Integer zzTraceNo;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private Integer hxTraceNo;
	/**产品号*/
	@Excel(name = "产品号", width = 15)
    @ApiModelProperty(value = "产品号")
	private String prdtNo;
	/**租入房屋地址*/
	@Excel(name = "租入房屋地址", width = 15)
    @ApiModelProperty(value = "租入房屋地址")
	private String addr;
	/**租入房屋面积*/
	@Excel(name = "租入房屋面积", width = 15)
    @ApiModelProperty(value = "租入房屋面积")
	private java.math.BigDecimal buildArea;
	/**租入起始日*/
	@Excel(name = "租入起始日", width = 15)
    @ApiModelProperty(value = "租入起始日")
	private Integer rentStdate;
	/**租入终止日*/
	@Excel(name = "租入终止日", width = 15)
    @ApiModelProperty(value = "租入终止日")
	private Integer rentOvdate;
	/**租金支付方式*/
	@Excel(name = "租金支付方式", width = 15)
    @ApiModelProperty(value = "租金支付方式")
	private String rentType;
	/**广告费宣传项目名称*/
	@Excel(name = "广告费宣传项目名称", width = 15)
    @ApiModelProperty(value = "广告费宣传项目名称")
	private String adName;
	/**广告方式*/
	@Excel(name = "广告方式", width = 15)
    @ApiModelProperty(value = "广告方式")
	private String adType;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**交易笔次*/
	@Excel(name = "交易笔次", width = 15)
    @ApiModelProperty(value = "交易笔次")
	private Integer tdCnt;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**上次摊销日期*/
	@Excel(name = "上次摊销日期", width = 15)
    @ApiModelProperty(value = "上次摊销日期")
	private Integer depDate;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15)
    @ApiModelProperty(value = "到期日期")
	private Integer expDate;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String dataDate;
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

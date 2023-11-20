package org.cmms.modules.sjxf.qtxt.cwglxt.yskdjb.entity;

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
 * @Description: 应收款登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_account_app_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_account_app_reg对象", description="应收款登记簿")
public class Yskdjb {
    
	/**应收款审批编号*/
	@Excel(name = "应收款审批编号", width = 15)
    @ApiModelProperty(value = "应收款审批编号")
	private String appNo;
	/**业务编码*/
	@Excel(name = "业务编码", width = 15)
    @ApiModelProperty(value = "业务编码")
	private String ywNo;
	/**明细分类编码*/
	@Excel(name = "明细分类编码", width = 15)
    @ApiModelProperty(value = "明细分类编码")
	private String dtlOptNo;
	/**交易笔数*/
	@Excel(name = "交易笔数", width = 15)
    @ApiModelProperty(value = "交易笔数")
	private Integer tdCnt;
	/**借款职工号*/
	@Excel(name = "借款职工号", width = 15)
    @ApiModelProperty(value = "借款职工号")
	private String budgetDep;
	/**借款人名称*/
	@Excel(name = "借款人名称", width = 15)
    @ApiModelProperty(value = "借款人名称")
	private String budgetDepName;
	/**预算项目编码*/
	@Excel(name = "预算项目编码", width = 15)
    @ApiModelProperty(value = "预算项目编码")
	private String budgetCode;
	/**预算项目编码名称*/
	@Excel(name = "预算项目编码名称", width = 15)
    @ApiModelProperty(value = "预算项目编码名称")
	private String budgetName;
	/**发票号码*/
	@Excel(name = "发票号码", width = 15)
    @ApiModelProperty(value = "发票号码")
	private String billNo;
	/**发票数量*/
	@Excel(name = "发票数量", width = 15)
    @ApiModelProperty(value = "发票数量")
	private Integer billNum;
	/**事项审批编号*/
	@Excel(name = "事项审批编号", width = 15)
    @ApiModelProperty(value = "事项审批编号")
	private String itemNo;
	/**事项审批序号*/
	@Excel(name = "事项审批序号", width = 15)
    @ApiModelProperty(value = "事项审批序号")
	private Integer appSeqn;
	/**已收回金额*/
	@Excel(name = "已收回金额", width = 15)
    @ApiModelProperty(value = "已收回金额")
	private Long backAmt;
	/**冲账标志*/
	@Excel(name = "冲账标志", width = 15)
    @ApiModelProperty(value = "冲账标志")
	private String appSts;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String contractNo;
	/**经办人*/
	@Excel(name = "经办人", width = 15)
    @ApiModelProperty(value = "经办人")
	private String agentNo;
	/**预计还款日期*/
	@Excel(name = "预计还款日期", width = 15)
    @ApiModelProperty(value = "预计还款日期")
	private Integer preBackDate;
	/**贷款人客户号*/
	@Excel(name = "贷款人客户号", width = 15)
    @ApiModelProperty(value = "贷款人客户号")
	private String customerNo;
	/**贷款人名称*/
	@Excel(name = "贷款人名称", width = 15)
    @ApiModelProperty(value = "贷款人名称")
	private String loanName;
	/**垫款类型*/
	@Excel(name = "垫款类型", width = 15)
    @ApiModelProperty(value = "垫款类型")
	private String loanType;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String filler;
	/**已计提坏账准备金额*/
	@Excel(name = "已计提坏账准备金额", width = 15)
    @ApiModelProperty(value = "已计提坏账准备金额")
	private Long hzzbAmt;
	/**所属账务部门*/
	@Excel(name = "所属账务部门", width = 15)
    @ApiModelProperty(value = "所属账务部门")
	private String dcBrNo;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private Integer traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer txDate;
	/**初始挂账金额*/
	@Excel(name = "初始挂账金额", width = 15)
    @ApiModelProperty(value = "初始挂账金额")
	private Long oBal;
	/**产品号*/
	@Excel(name = "产品号", width = 15)
    @ApiModelProperty(value = "产品号")
	private String prdtNo;
	/**账号ID*/
	@Excel(name = "账号ID", width = 15)
    @ApiModelProperty(value = "账号ID")
	private Integer acId;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**已核销金额*/
	@Excel(name = "已核销金额", width = 15)
    @ApiModelProperty(value = "已核销金额")
	private Long hxAmt;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
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

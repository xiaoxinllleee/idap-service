package org.cmms.modules.sjxf.qtxt.cwglxt.txbdjb.entity;

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
 * @Description: 贴现包登记簿--贴现合同与贴现协议关联表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_discount_contract_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_discount_contract_reg对象", description="贴现包登记簿--贴现合同与贴现协议关联表")
public class Txbdjb {
    
	/**贴现合同号*/
	@Excel(name = "贴现合同号", width = 15)
    @ApiModelProperty(value = "贴现合同号")
	private String contractNo;
	/**交易明细笔次*/
	@Excel(name = "交易明细笔次", width = 15)
    @ApiModelProperty(value = "交易明细笔次")
	private String txCnt;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradeType;
	/**交易操作类型*/
	@Excel(name = "交易操作类型", width = 15)
    @ApiModelProperty(value = "交易操作类型")
	private String discountType;
	/**贴现签收日期(贴现日)*/
	@Excel(name = "贴现签收日期(贴现日)", width = 15)
    @ApiModelProperty(value = "贴现签收日期(贴现日)")
	private String discountDate;
	/**票据面值总额(元)*/
	@Excel(name = "票据面值总额(元)", width = 15)
    @ApiModelProperty(value = "票据面值总额(元)")
	private java.math.BigDecimal totalParAmt;
	/**未摊销利息调整总额(元)*/
	@Excel(name = "未摊销利息调整总额(元)", width = 15)
    @ApiModelProperty(value = "未摊销利息调整总额(元)")
	private java.math.BigDecimal adjustAmt;
	/**贴现结算总金额(元)*/
	@Excel(name = "贴现结算总金额(元)", width = 15)
    @ApiModelProperty(value = "贴现结算总金额(元)")
	private java.math.BigDecimal totalPayAmt;
	/**减值准备总额(元)*/
	@Excel(name = "减值准备总额(元)", width = 15)
    @ApiModelProperty(value = "减值准备总额(元)")
	private java.math.BigDecimal totalInpairAmt;
	/**减值损失总额(元)*/
	@Excel(name = "减值损失总额(元)", width = 15)
    @ApiModelProperty(value = "减值损失总额(元)")
	private java.math.BigDecimal totalLossAmt;
	/**已确认利息支出金额(元)*/
	@Excel(name = "已确认利息支出金额(元)", width = 15)
    @ApiModelProperty(value = "已确认利息支出金额(元)")
	private java.math.BigDecimal ytotalAdjustAmt;
	/**线上清算标记*/
	@Excel(name = "线上清算标记", width = 15)
    @ApiModelProperty(value = "线上清算标记")
	private String isOnline;
	/**利息调整标志*/
	@Excel(name = "利息调整标志", width = 15)
    @ApiModelProperty(value = "利息调整标志")
	private String adjustFlag;
	/**利息调整摊销方法*/
	@Excel(name = "利息调整摊销方法", width = 15)
    @ApiModelProperty(value = "利息调整摊销方法")
	private String adjustMethod;
	/**回购约定日期*/
	@Excel(name = "回购约定日期", width = 15)
    @ApiModelProperty(value = "回购约定日期")
	private String backDate;
	/**组织机构代码证*/
	@Excel(name = "组织机构代码证", width = 15)
    @ApiModelProperty(value = "组织机构代码证")
	private String ogzCode;
	/**资金账号*/
	@Excel(name = "资金账号", width = 15)
    @ApiModelProperty(value = "资金账号")
	private String bankAcno;
	/**开户行行号*/
	@Excel(name = "开户行行号", width = 15)
    @ApiModelProperty(value = "开户行行号")
	private String bankNo;
	/**审批编号*/
	@Excel(name = "审批编号", width = 15)
    @ApiModelProperty(value = "审批编号")
	private String appNo;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**贴现合同状态*/
	@Excel(name = "贴现合同状态", width = 15)
    @ApiModelProperty(value = "贴现合同状态")
	private String sts;
	/**登记操作员*/
	@Excel(name = "登记操作员", width = 15)
    @ApiModelProperty(value = "登记操作员")
	private String regTel;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String regDate;
	/**账务机构*/
	@Excel(name = "账务机构", width = 15)
    @ApiModelProperty(value = "账务机构")
	private String brNo;
	/**内部帐AC_NO*/
	@Excel(name = "内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "内部帐AC_NO")
	private String acNo;
	/**内部账AC_ID*/
	@Excel(name = "内部账AC_ID", width = 15)
    @ApiModelProperty(value = "内部账AC_ID")
	private String acId;
	/**利率来源方式*/
	@Excel(name = "利率来源方式", width = 15)
    @ApiModelProperty(value = "利率来源方式")
	private String rateSource;
	/**产品1*/
	@Excel(name = "产品1", width = 15)
    @ApiModelProperty(value = "产品1")
	private String prdtNo1;
	/**产品2*/
	@Excel(name = "产品2", width = 15)
    @ApiModelProperty(value = "产品2")
	private String prdtNo2;
	/**产品3*/
	@Excel(name = "产品3", width = 15)
    @ApiModelProperty(value = "产品3")
	private String prdtNo3;
	/**产品4*/
	@Excel(name = "产品4", width = 15)
    @ApiModelProperty(value = "产品4")
	private String prdtNo4;
	/**产品5*/
	@Excel(name = "产品5", width = 15)
    @ApiModelProperty(value = "产品5")
	private String prdtNo5;
	/**利率类型*/
	@Excel(name = "利率类型", width = 15)
    @ApiModelProperty(value = "利率类型")
	private String rateType;
	/**到期申请审批编号*/
	@Excel(name = "到期申请审批编号", width = 15)
    @ApiModelProperty(value = "到期申请审批编号")
	private String dappNo;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**处理模式*/
	@Excel(name = "处理模式", width = 15)
    @ApiModelProperty(value = "处理模式")
	private String doModel;
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**交易对方名称*/
	@Excel(name = "交易对方名称", width = 15)
    @ApiModelProperty(value = "交易对方名称")
	private String discountName;
	/**资金开户行行名*/
	@Excel(name = "资金开户行行名", width = 15)
    @ApiModelProperty(value = "资金开户行行名")
	private String bankName;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**version*/
	@Excel(name = "version", width = 15)
    @ApiModelProperty(value = "version")
	private String version;
	/**subCtpyNo*/
	@Excel(name = "subCtpyNo", width = 15)
    @ApiModelProperty(value = "subCtpyNo")
	private String subCtpyNo;
	/**subVersion*/
	@Excel(name = "subVersion", width = 15)
    @ApiModelProperty(value = "subVersion")
	private String subVersion;
	/**ctpyDepNo*/
	@Excel(name = "ctpyDepNo", width = 15)
    @ApiModelProperty(value = "ctpyDepNo")
	private String ctpyDepNo;
	/**bctpyDepNo*/
	@Excel(name = "bctpyDepNo", width = 15)
    @ApiModelProperty(value = "bctpyDepNo")
	private String bctpyDepNo;
	/**bversion*/
	@Excel(name = "bversion", width = 15)
    @ApiModelProperty(value = "bversion")
	private String bversion;
	/**bsubCtpyNo*/
	@Excel(name = "bsubCtpyNo", width = 15)
    @ApiModelProperty(value = "bsubCtpyNo")
	private String bsubCtpyNo;
	/**bctpyNo*/
	@Excel(name = "bctpyNo", width = 15)
    @ApiModelProperty(value = "bctpyNo")
	private String bctpyNo;
	/**bsubVersion*/
	@Excel(name = "bsubVersion", width = 15)
    @ApiModelProperty(value = "bsubVersion")
	private String bsubVersion;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**原系统标识*/
	@Excel(name = "原系统标识", width = 15)
    @ApiModelProperty(value = "原系统标识")
	private String sourceId;
	/**加载类型*/
	@Excel(name = "加载类型", width = 15)
    @ApiModelProperty(value = "加载类型")
	private String loadType;
	/**legalNo*/
	@Excel(name = "legalNo", width = 15)
    @ApiModelProperty(value = "legalNo")
	private String legalNo;
}

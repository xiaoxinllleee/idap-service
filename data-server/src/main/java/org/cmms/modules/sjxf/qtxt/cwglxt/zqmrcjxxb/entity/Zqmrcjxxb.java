package org.cmms.modules.sjxf.qtxt.cwglxt.zqmrcjxxb.entity;

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
 * @Description: 债券买入成交单信息薄
 * @Author: jeecg-boot
 * @Date:   2021-12-14
 * @Version: V1.0
 */
@Data
@TableName("Ebss_buymo_invest")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_buymo_invest对象", description="债券买入成交单信息薄")
public class Zqmrcjxxb {
    
	/**成交单编号*/
	@Excel(name = "成交单编号", width = 15)
    @ApiModelProperty(value = "成交单编号")
	private String pactNo;
	/**交易明细笔次*/
	@Excel(name = "交易明细笔次", width = 15)
    @ApiModelProperty(value = "交易明细笔次")
	private String txCnt;
	/**债券代码*/
	@Excel(name = "债券代码", width = 15)
    @ApiModelProperty(value = "债券代码")
	private String bondNo;
	/**债券名称*/
	@Excel(name = "债券名称", width = 15)
    @ApiModelProperty(value = "债券名称")
	private String bondName;
	/**债券市场类型*/
	@Excel(name = "债券市场类型", width = 15)
    @ApiModelProperty(value = "债券市场类型")
	private String bondMarkettype;
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**交易对手版本号*/
	@Excel(name = "交易对手版本号", width = 15)
    @ApiModelProperty(value = "交易对手版本号")
	private String version;
	/**持有意图*/
	@Excel(name = "持有意图", width = 15)
    @ApiModelProperty(value = "持有意图")
	private String holdAim;
	/**剩余期限*/
	@Excel(name = "剩余期限", width = 15)
    @ApiModelProperty(value = "剩余期限")
	private String remianTerm;
	/**产品号*/
	@Excel(name = "产品号", width = 15)
    @ApiModelProperty(value = "产品号")
	private String prdtNo;
	/**结算金额(元)*/
	@Excel(name = "结算金额(元)", width = 15)
    @ApiModelProperty(value = "结算金额(元)")
	private java.math.BigDecimal fullPrice;
	/**券面总额(万元)*/
	@Excel(name = "券面总额(万元)", width = 15)
    @ApiModelProperty(value = "券面总额(万元)")
	private java.math.BigDecimal parBal;
	/**到期收益率(%)*/
	@Excel(name = "到期收益率(%)", width = 15)
    @ApiModelProperty(value = "到期收益率(%)")
	private java.math.BigDecimal yieldMaty;
	/**累计实收利息金额(元)*/
	@Excel(name = "累计实收利息金额(元)", width = 15)
    @ApiModelProperty(value = "累计实收利息金额(元)")
	private java.math.BigDecimal actRecIntst;
	/**累计计提金额(元)*/
	@Excel(name = "累计计提金额(元)", width = 15)
    @ApiModelProperty(value = "累计计提金额(元)")
	private java.math.BigDecimal sumJtAmt;
	/**累计确认投资收益(元)*/
	@Excel(name = "累计确认投资收益(元)", width = 15)
    @ApiModelProperty(value = "累计确认投资收益(元)")
	private java.math.BigDecimal sumSyAmt;
	/**本年累计计提金额(元)*/
	@Excel(name = "本年累计计提金额(元)", width = 15)
    @ApiModelProperty(value = "本年累计计提金额(元)")
	private java.math.BigDecimal bnSumJtAmt;
	/**本年累计确认投资收益(元)*/
	@Excel(name = "本年累计确认投资收益(元)", width = 15)
    @ApiModelProperty(value = "本年累计确认投资收益(元)")
	private java.math.BigDecimal bnSumSyAmt;
	/**内部帐AC_NO*/
	@Excel(name = "内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "内部帐AC_NO")
	private String acNo;
	/**内部账AC_ID*/
	@Excel(name = "内部账AC_ID", width = 15)
    @ApiModelProperty(value = "内部账AC_ID")
	private String acId;
	/**实际利率(%)*/
	@Excel(name = "实际利率(%)", width = 15)
    @ApiModelProperty(value = "实际利率(%)")
	private java.math.BigDecimal actRate;
	/**上次计提日期*/
	@Excel(name = "上次计提日期", width = 15)
    @ApiModelProperty(value = "上次计提日期")
	private String lJtDate;
	/**上次起息日期*/
	@Excel(name = "上次起息日期", width = 15)
    @ApiModelProperty(value = "上次起息日期")
	private String lIcDate;
	/**本次起息日期*/
	@Excel(name = "本次起息日期", width = 15)
    @ApiModelProperty(value = "本次起息日期")
	private String icDate;
	/**下次付息日期*/
	@Excel(name = "下次付息日期", width = 15)
    @ApiModelProperty(value = "下次付息日期")
	private String fxDate;
	/**结算方式*/
	@Excel(name = "结算方式", width = 15)
    @ApiModelProperty(value = "结算方式")
	private String settleType;
	/**资金结算速度*/
	@Excel(name = "资金结算速度", width = 15)
    @ApiModelProperty(value = "资金结算速度")
	private String capSettype;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String regDate;
	/**购入日期*/
	@Excel(name = "购入日期", width = 15)
    @ApiModelProperty(value = "购入日期")
	private String buyDate;
	/**资金结算日*/
	@Excel(name = "资金结算日", width = 15)
    @ApiModelProperty(value = "资金结算日")
	private String capDate;
	/**对方交易员*/
	@Excel(name = "对方交易员", width = 15)
    @ApiModelProperty(value = "对方交易员")
	private String ctpyTrader;
	/**本方交易员*/
	@Excel(name = "本方交易员", width = 15)
    @ApiModelProperty(value = "本方交易员")
	private String trader;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String brf;
	/**买入立项申请编号*/
	@Excel(name = "买入立项申请编号", width = 15)
    @ApiModelProperty(value = "买入立项申请编号")
	private String appNo;
	/**买入立项分项序号*/
	@Excel(name = "买入立项分项序号", width = 15)
    @ApiModelProperty(value = "买入立项分项序号")
	private String appSeqn;
	/**资金拨付申请编号*/
	@Excel(name = "资金拨付申请编号", width = 15)
    @ApiModelProperty(value = "资金拨付申请编号")
	private String zjappNo;
	/**债券所属账务机构*/
	@Excel(name = "债券所属账务机构", width = 15)
    @ApiModelProperty(value = "债券所属账务机构")
	private String brNo;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private String depNo;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
	private String suppNo;
	/**债券状态*/
	@Excel(name = "债券状态", width = 15)
    @ApiModelProperty(value = "债券状态")
	private String sts;
	/**交易净价(元)*/
	@Excel(name = "交易净价(元)", width = 15)
    @ApiModelProperty(value = "交易净价(元)")
	private java.math.BigDecimal byNetPrice;
	/**百元应计利息(元)*/
	@Excel(name = "百元应计利息(元)", width = 15)
    @ApiModelProperty(value = "百元应计利息(元)")
	private java.math.BigDecimal byIntst;
	/**百元全价(元)*/
	@Excel(name = "百元全价(元)", width = 15)
    @ApiModelProperty(value = "百元全价(元)")
	private java.math.BigDecimal byFullPrice;
	/**买入时应计利息总额(元)*/
	@Excel(name = "买入时应计利息总额(元)", width = 15)
    @ApiModelProperty(value = "买入时应计利息总额(元)")
	private java.math.BigDecimal buyintstAmt;
	/**交易金额(元)*/
	@Excel(name = "交易金额(元)", width = 15)
    @ApiModelProperty(value = "交易金额(元)")
	private java.math.BigDecimal netPrice;
	/**业务类型号*/
	@Excel(name = "业务类型号", width = 15)
    @ApiModelProperty(value = "业务类型号")
	private String ywNo;
	/**计划编号*/
	@Excel(name = "计划编号", width = 15)
    @ApiModelProperty(value = "计划编号")
	private String planNo;
	/**计划日期*/
	@Excel(name = "计划日期", width = 15)
    @ApiModelProperty(value = "计划日期")
	private String chnlInd;
	/**计提利息周期*/
	@Excel(name = "计提利息周期", width = 15)
    @ApiModelProperty(value = "计提利息周期")
	private String intstCycle;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**计提利息方式*/
	@Excel(name = "计提利息方式", width = 15)
    @ApiModelProperty(value = "计提利息方式")
	private String jtType;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**正回购质押面值总额(元)*/
	@Excel(name = "正回购质押面值总额(元)", width = 15)
    @ApiModelProperty(value = "正回购质押面值总额(元)")
	private java.math.BigDecimal zyParBal;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**债券序号*/
	@Excel(name = "债券序号", width = 15)
    @ApiModelProperty(value = "债券序号")
	private String bondSeqn;
	/**手续费比例*/
	@Excel(name = "手续费比例", width = 15)
    @ApiModelProperty(value = "手续费比例")
	private java.math.BigDecimal serviceRate;
	/**手续费收入*/
	@Excel(name = "手续费收入", width = 15)
    @ApiModelProperty(value = "手续费收入")
	private java.math.BigDecimal serviceIncome;
	/**本方编号*/
	@Excel(name = "本方编号", width = 15)
    @ApiModelProperty(value = "本方编号")
	private String bctpyNo;
	/**本方版本号*/
	@Excel(name = "本方版本号", width = 15)
    @ApiModelProperty(value = "本方版本号")
	private String bversion;
	/**回购金额*/
	@Excel(name = "回购金额", width = 15)
    @ApiModelProperty(value = "回购金额")
	private java.math.BigDecimal backParBal;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
	/**数据加载日期*/
    @ApiModelProperty(value = "数据加载日期")
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

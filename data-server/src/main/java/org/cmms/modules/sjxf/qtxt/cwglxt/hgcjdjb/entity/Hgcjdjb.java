package org.cmms.modules.sjxf.qtxt.cwglxt.hgcjdjb.entity;

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
 * @Description: 回购成交登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_back_pledge_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_back_pledge_reg对象", description="回购成交登记簿")
public class Hgcjdjb {
    
	/**回购成交单编号*/
	@Excel(name = "回购成交单编号", width = 15)
    @ApiModelProperty(value = "回购成交单编号")
	private String pactNo;
	/**质押物类型*/
	@Excel(name = "质押物类型", width = 15)
    @ApiModelProperty(value = "质押物类型")
	private String pledgeType;
	/**交易明细笔次*/
	@Excel(name = "交易明细笔次", width = 15)
    @ApiModelProperty(value = "交易明细笔次")
	private Integer txCnt;
	/**质押物笔数*/
	@Excel(name = "质押物笔数", width = 15)
    @ApiModelProperty(value = "质押物笔数")
	private Integer pledgeCnt;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradeType;
	/**内部帐AC_NO*/
	@Excel(name = "内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "内部帐AC_NO")
	private String acNo;
	/**内部账AC_ID*/
	@Excel(name = "内部账AC_ID", width = 15)
    @ApiModelProperty(value = "内部账AC_ID")
	private Integer acId;
	/**回购利率(%)*/
	@Excel(name = "回购利率(%)", width = 15)
    @ApiModelProperty(value = "回购利率(%)")
	private java.math.BigDecimal repRate;
	/**质押物面额合计(万元)*/
	@Excel(name = "质押物面额合计(万元)", width = 15)
    @ApiModelProperty(value = "质押物面额合计(万元)")
	private java.math.BigDecimal pledgeBal;
	/**交易金额(元)*/
	@Excel(name = "交易金额(元)", width = 15)
    @ApiModelProperty(value = "交易金额(元)")
	private java.math.BigDecimal firstAmt;
	/**应计利息(元)*/
	@Excel(name = "应计利息(元)", width = 15)
    @ApiModelProperty(value = "应计利息(元)")
	private String isIntst;
	/**日利率计算方式*/
	@Excel(name = "日利率计算方式", width = 15)
    @ApiModelProperty(value = "日利率计算方式")
	private String dateRateNum;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private java.math.BigDecimal intstAmt;
	/**到期结算金额(元)*/
	@Excel(name = "到期结算金额(元)", width = 15)
    @ApiModelProperty(value = "到期结算金额(元)")
	private java.math.BigDecimal mtrAmt;
	/**首次结算方式*/
	@Excel(name = "首次结算方式", width = 15)
    @ApiModelProperty(value = "首次结算方式")
	private String firstSetrType;
	/**到期结算方式*/
	@Excel(name = "到期结算方式", width = 15)
    @ApiModelProperty(value = "到期结算方式")
	private String mtrSetrType;
	/**成交日期*/
	@Excel(name = "成交日期", width = 15)
    @ApiModelProperty(value = "成交日期")
	private Integer signDate;
	/**首次结算日*/
	@Excel(name = "首次结算日", width = 15)
    @ApiModelProperty(value = "首次结算日")
	private Integer firstDate;
	/**到期结算日*/
	@Excel(name = "到期结算日", width = 15)
    @ApiModelProperty(value = "到期结算日")
	private Integer mrtDate;
	/**回购期限(天)*/
	@Excel(name = "回购期限(天)", width = 15)
    @ApiModelProperty(value = "回购期限(天)")
	private Integer dCnt;
	/**实际占款天数*/
	@Excel(name = "实际占款天数", width = 15)
    @ApiModelProperty(value = "实际占款天数")
	private Integer realDays;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private Integer regDate;
	/**上次计提日期*/
	@Excel(name = "上次计提日期", width = 15)
    @ApiModelProperty(value = "上次计提日期")
	private Integer lJtDate;
	/**交易品种*/
	@Excel(name = "交易品种", width = 15)
    @ApiModelProperty(value = "交易品种")
	private String tradeNo;
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**交易对手版本号*/
	@Excel(name = "交易对手版本号", width = 15)
    @ApiModelProperty(value = "交易对手版本号")
	private Integer version;
	/**申请编号*/
	@Excel(name = "申请编号", width = 15)
    @ApiModelProperty(value = "申请编号")
	private String appNo;
	/**回购所属账务机构*/
	@Excel(name = "回购所属账务机构", width = 15)
    @ApiModelProperty(value = "回购所属账务机构")
	private String brNo;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**使用部门*/
	@Excel(name = "使用部门", width = 15)
    @ApiModelProperty(value = "使用部门")
	private String depNo;
	/**使用人*/
	@Excel(name = "使用人", width = 15)
    @ApiModelProperty(value = "使用人")
	private String suppNo;
	/**回购状态*/
	@Excel(name = "回购状态", width = 15)
    @ApiModelProperty(value = "回购状态")
	private String sts;
	/**累计计提利息金额(元)*/
	@Excel(name = "累计计提利息金额(元)", width = 15)
    @ApiModelProperty(value = "累计计提利息金额(元)")
	private java.math.BigDecimal ljJtIntst;
	/**实际收取利息金额(元)*/
	@Excel(name = "实际收取利息金额(元)", width = 15)
    @ApiModelProperty(value = "实际收取利息金额(元)")
	private java.math.BigDecimal ljSqIntst;
	/**主产品号*/
	@Excel(name = "主产品号", width = 15)
    @ApiModelProperty(value = "主产品号")
	private String prdtNo;
	/**对方交易员*/
	@Excel(name = "对方交易员", width = 15)
    @ApiModelProperty(value = "对方交易员")
	private String ctpyTrader;
	/**本方交易员*/
	@Excel(name = "本方交易员", width = 15)
    @ApiModelProperty(value = "本方交易员")
	private String trader;
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
	private Integer chnlInd;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String brf;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**bctpyNo*/
	@Excel(name = "bctpyNo", width = 15)
    @ApiModelProperty(value = "bctpyNo")
	private String bctpyNo;
	/**bversion*/
	@Excel(name = "bversion", width = 15)
    @ApiModelProperty(value = "bversion")
	private Integer bversion;
	/**sDate*/
	@Excel(name = "sDate", width = 15)
    @ApiModelProperty(value = "sDate")
	private String sDate;
	/**eDate*/
	@Excel(name = "eDate", width = 15)
    @ApiModelProperty(value = "eDate")
	private String eDate;
	/**加载时间*/
    @ApiModelProperty(value = "加载时间")
	private Date loadDate;
	/**法人标识*/
	@Excel(name = "法人标识", width = 15)
    @ApiModelProperty(value = "法人标识")
	private String legalNo;
}

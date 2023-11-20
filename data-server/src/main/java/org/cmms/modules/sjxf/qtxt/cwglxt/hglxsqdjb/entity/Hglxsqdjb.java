package org.cmms.modules.sjxf.qtxt.cwglxt.hglxsqdjb.entity;

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
 * @Description: 回购立项申请登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_back_project_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_back_project_reg对象", description="回购立项申请登记簿")
public class Hglxsqdjb {
    
	/**审批编号*/
	@Excel(name = "审批编号", width = 15)
    @ApiModelProperty(value = "审批编号")
	private String appNo;
	/**质押物笔数*/
	@Excel(name = "质押物笔数", width = 15)
    @ApiModelProperty(value = "质押物笔数")
	private Integer pledgeCnt;
	/**交易对手编号*/
	@Excel(name = "交易对手编号", width = 15)
    @ApiModelProperty(value = "交易对手编号")
	private String ctpyNo;
	/**交易对手版本号*/
	@Excel(name = "交易对手版本号", width = 15)
    @ApiModelProperty(value = "交易对手版本号")
	private Integer version;
	/**交易对手方名称*/
	@Excel(name = "交易对手方名称", width = 15)
    @ApiModelProperty(value = "交易对手方名称")
	private String ctpyName;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradeType;
	/**回购期限(天)*/
	@Excel(name = "回购期限(天)", width = 15)
    @ApiModelProperty(value = "回购期限(天)")
	private Integer dCnt;
	/**清算方式*/
	@Excel(name = "清算方式", width = 15)
    @ApiModelProperty(value = "清算方式")
	private String qsSpeed;
	/**回购利率(%)*/
	@Excel(name = "回购利率(%)", width = 15)
    @ApiModelProperty(value = "回购利率(%)")
	private java.math.BigDecimal repRate;
	/**首次结算方式*/
	@Excel(name = "首次结算方式", width = 15)
    @ApiModelProperty(value = "首次结算方式")
	private String firstSetrType;
	/**到期结算方式*/
	@Excel(name = "到期结算方式", width = 15)
    @ApiModelProperty(value = "到期结算方式")
	private String mtrSetrType;
	/**首次结算日*/
	@Excel(name = "首次结算日", width = 15)
    @ApiModelProperty(value = "首次结算日")
	private Integer firstDate;
	/**到期结算日*/
	@Excel(name = "到期结算日", width = 15)
    @ApiModelProperty(value = "到期结算日")
	private Integer mrtDate;
	/**质押物面值合计(万元)*/
	@Excel(name = "质押物面值合计(万元)", width = 15)
    @ApiModelProperty(value = "质押物面值合计(万元)")
	private java.math.BigDecimal pledgeAmt;
	/**结算金额(万元)*/
	@Excel(name = "结算金额(万元)", width = 15)
    @ApiModelProperty(value = "结算金额(万元)")
	private java.math.BigDecimal settlAmt;
	/**申请回购利率(%)*/
	@Excel(name = "申请回购利率(%)", width = 15)
    @ApiModelProperty(value = "申请回购利率(%)")
	@TableField(value = "REP_RATE_0")
	private java.math.BigDecimal repRate0;
	/**第1个审批岗位审批回购利率*/
	@Excel(name = "第1个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第1个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_1")
	private java.math.BigDecimal repRate1;
	/**第2个审批岗位审批回购利率*/
	@Excel(name = "第2个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第2个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_2")
	private java.math.BigDecimal repRate2;
	/**第3个审批岗位审批回购利率*/
	@Excel(name = "第3个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第3个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_3")
	private java.math.BigDecimal repRate3;
	/**第4个审批岗位审批回购利率*/
	@Excel(name = "第4个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第4个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_4")
	private java.math.BigDecimal repRate4;
	/**第5个审批岗位审批回购利率*/
	@Excel(name = "第5个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第5个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_5")
	private java.math.BigDecimal repRate5;
	/**第6个审批岗位审批回购利率*/
	@Excel(name = "第6个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第6个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_6")
	private java.math.BigDecimal repRate6;
	/**第7个审批岗位审批回购利率*/
	@Excel(name = "第7个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第7个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_7")
	private java.math.BigDecimal repRate7;
	/**第8个审批岗位审批回购利率*/
	@Excel(name = "第8个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第8个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_8")
	private java.math.BigDecimal repRate8;
	/**第9个审批岗位审批回购利率*/
	@Excel(name = "第9个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第9个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_9")
	private java.math.BigDecimal repRate9;
	/**第10个审批岗位审批回购利率*/
	@Excel(name = "第10个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第10个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_10")
	private java.math.BigDecimal repRate10;
	/**第11个审批岗位审批回购利率*/
	@Excel(name = "第11个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第11个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_11")
	private java.math.BigDecimal repRate11;
	/**第12个审批岗位审批回购利率*/
	@Excel(name = "第12个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第12个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_12")
	private java.math.BigDecimal repRate12;
	/**第13个审批岗位审批回购利率*/
	@Excel(name = "第13个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第13个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_13")
	private java.math.BigDecimal repRate13;
	/**第14个审批岗位审批回购利率*/
	@Excel(name = "第14个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第14个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_14")
	private java.math.BigDecimal repRate14;
	/**第15个审批岗位审批回购利率*/
	@Excel(name = "第15个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第15个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_15")
	private java.math.BigDecimal repRate15;
	/**第16个审批岗位审批回购利率*/
	@Excel(name = "第16个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第16个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_16")
	private java.math.BigDecimal repRate16;
	/**第17个审批岗位审批回购利率*/
	@Excel(name = "第17个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第17个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_17")
	private java.math.BigDecimal repRate17;
	/**第18个审批岗位审批回购利率*/
	@Excel(name = "第18个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第18个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_18")
	private java.math.BigDecimal repRate18;
	/**第19个审批岗位审批回购利率*/
	@Excel(name = "第19个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第19个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_19")
	private java.math.BigDecimal repRate19;
	/**第20个审批岗位审批回购利率*/
	@Excel(name = "第20个审批岗位审批回购利率", width = 15)
    @ApiModelProperty(value = "第20个审批岗位审批回购利率")
	@TableField(value = "REP_RATE_20")
	private java.math.BigDecimal repRate20;
	/**所属部门*/
	@Excel(name = "所属部门", width = 15)
    @ApiModelProperty(value = "所属部门")
	private String depNo;
	/**回购成交单编号*/
	@Excel(name = "回购成交单编号", width = 15)
    @ApiModelProperty(value = "回购成交单编号")
	private String pactNo;
	/**到期结算申请编号*/
	@Excel(name = "到期结算申请编号", width = 15)
    @ApiModelProperty(value = "到期结算申请编号")
	private String dappNo;
	/**所属账务机构*/
	@Excel(name = "所属账务机构", width = 15)
    @ApiModelProperty(value = "所属账务机构")
	private String brNo;
	/**申请操作员*/
	@Excel(name = "申请操作员", width = 15)
    @ApiModelProperty(value = "申请操作员")
	private String tel;
	/**本方编号*/
	@Excel(name = "本方编号", width = 15)
    @ApiModelProperty(value = "本方编号")
	private String bctpyNo;
	/**本方版本号*/
	@Excel(name = "本方版本号", width = 15)
    @ApiModelProperty(value = "本方版本号")
	private Integer bversion;
	/**本方名称*/
	@Excel(name = "本方名称", width = 15)
    @ApiModelProperty(value = "本方名称")
	private String bctpyName;
	/**法人联社号*/
	@Excel(name = "法人联社号", width = 15)
    @ApiModelProperty(value = "法人联社号")
	private String upBrNo;
	/**询价情况*/
	@Excel(name = "询价情况", width = 15)
    @ApiModelProperty(value = "询价情况")
	private String brf;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**ctpyDepNo*/
	@Excel(name = "ctpyDepNo", width = 15)
    @ApiModelProperty(value = "ctpyDepNo")
	private String ctpyDepNo;
	/**bctpyDepNo*/
	@Excel(name = "bctpyDepNo", width = 15)
    @ApiModelProperty(value = "bctpyDepNo")
	private String bctpyDepNo;
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

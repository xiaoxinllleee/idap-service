package org.cmms.modules.sjxf.hxxt.nbzjymx_22.entity;

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
 * @Description: 内部帐交易明细_22
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_gect_tran_22")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_gect_tran_22对象", description="内部帐交易明细_22")
public class Nbzjymx_22 {
    
	/**金融序号*/
	@Excel(name = "金融序号", width = 15)
    @ApiModelProperty(value = "金融序号")
	private String instNo;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**顺序号*/
	@Excel(name = "顺序号", width = 15)
    @ApiModelProperty(value = "顺序号")
	private String recNo;
	/**交易类型:01-金融交易 20-对账单摘要 21-交易备注36-转账信息*/
	@Excel(name = "交易类型:01-金融交易 20-对账单摘要 21-交易备注36-转账信息", width = 15)
    @ApiModelProperty(value = "交易类型:01-金融交易 20-对账单摘要 21-交易备注36-转账信息")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**过帐日期*/
	@Excel(name = "过帐日期", width = 15)
    @ApiModelProperty(value = "过帐日期")
	private Integer postDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private Integer trnDate;
	/**系统日期*/
	@Excel(name = "系统日期", width = 15)
    @ApiModelProperty(value = "系统日期")
	private Integer systemDate;
	/**系统时间*/
	@Excel(name = "系统时间", width = 15)
    @ApiModelProperty(value = "系统时间")
	@TableField(value = "system_time")
	private Integer systemTime;
	/**机构柜员号*/
	@Excel(name = "机构柜员号", width = 15)
    @ApiModelProperty(value = "机构柜员号")
	private Long tellAndBr;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private Integer jrnlNo;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private Integer trnCode;
	/**交易终端*/
	@Excel(name = "交易终端", width = 15)
    @ApiModelProperty(value = "交易终端")
	private Integer brterm;
	/**渠道类型      0-柜面交易      1-ATM      5-支付渠道      6-信贷系统      8-国际结算      B-批处理      C-贷记卡      H-农信银全国通存通兑      N-网上银行      I-电话银行      P-财付通      W-中间业务      Y-短信银行*/
	@Excel(name = "渠道类型      0-柜面交易      1-ATM      5-支付渠道      6-信贷系统      8-国际结算      B-批处理      C-贷记卡      H-农信银全国通存通兑      N-网上银行      I-电话银行      P-财付通      W-中间业务      Y-短信银行", width = 15)
    @ApiModelProperty(value = "渠道类型      0-柜面交易      1-ATM      5-支付渠道      6-信贷系统      8-国际结算      B-批处理      C-贷记卡      H-农信银全国通存通兑      N-网上银行      I-电话银行      P-财付通      W-中间业务      Y-短信银行")
	private String channel;
	/**删除标志 1-Yes；0-No*/
	@Excel(name = "删除标志 1-Yes；0-No", width = 15)
    @ApiModelProperty(value = "删除标志 1-Yes；0-No")
	private String deli;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String code;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String status;
	/**销帐码信息*/
	@Excel(name = "销帐码信息", width = 15)
    @ApiModelProperty(value = "销帐码信息")
	private String srnInfo;
	/**冲正标识，目前未用*/
	@Excel(name = "冲正标识，目前未用", width = 15)
    @ApiModelProperty(value = "冲正标识，目前未用")
	private String corrInd;
	/**冲正状态，目前未用*/
	@Excel(name = "冲正状态，目前未用", width = 15)
    @ApiModelProperty(value = "冲正状态，目前未用")
	private String corrStat;
	/**填充1*/
	@Excel(name = "填充1", width = 15)
    @ApiModelProperty(value = "填充1")
	private String filler1;
	/**填充2*/
	@Excel(name = "填充2", width = 15)
    @ApiModelProperty(value = "填充2")
	private String filler2;
	/**填充3*/
	@Excel(name = "填充3", width = 15)
    @ApiModelProperty(value = "填充3")
	private String filler3;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
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

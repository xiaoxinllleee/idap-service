package org.cmms.modules.sjxf.qtxt.djkxt.khjysj.entity;

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
 * @Description: 客户交易数据
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ccd_tran")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ccd_tran对象", description="客户交易数据")
public class Khjysj {
    
	/**银行编号*/
	@Excel(name = "银行编号", width = 15)
    @ApiModelProperty(value = "银行编号")
	private String bank;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String transType;
	/**交易类型描述*/
	@Excel(name = "交易类型描述", width = 15)
    @ApiModelProperty(value = "交易类型描述")
	private String descPrint;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String cardNbr;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal billAmt;
	/**交易金额符号*/
	@Excel(name = "交易金额符号", width = 15)
    @ApiModelProperty(value = "交易金额符号")
	private String billAmtSign;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private String merCatCd;
	/**商户代码*/
	@Excel(name = "商户代码", width = 15)
    @ApiModelProperty(value = "商户代码")
	private String merchant;
	/**受卡机终端标识码*/
	@Excel(name = "受卡机终端标识码", width = 15)
    @ApiModelProperty(value = "受卡机终端标识码")
	private String terminali;
	/**检索参考号*/
	@Excel(name = "检索参考号", width = 15)
    @ApiModelProperty(value = "检索参考号")
	private String microRef;
	/**授权应答码*/
	@Excel(name = "授权应答码", width = 15)
    @ApiModelProperty(value = "授权应答码")
	private String authCode;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String settleDay;
	/**交易币种*/
	@Excel(name = "交易币种", width = 15)
    @ApiModelProperty(value = "交易币种")
	private String currncyCd;
	/**撤销、冲正标志*/
	@Excel(name = "撤销、冲正标志", width = 15)
    @ApiModelProperty(value = "撤销、冲正标志")
	private String revInd;
	/**交易描述1*/
	@Excel(name = "交易描述1", width = 15)
    @ApiModelProperty(value = "交易描述1")
	private String desLine1;
	/**交易描述2*/
	@Excel(name = "交易描述2", width = 15)
    @ApiModelProperty(value = "交易描述2")
	private String desLine2;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String inpDate;
	/**入帐日期*/
	@Excel(name = "入帐日期", width = 15)
    @ApiModelProperty(value = "入帐日期")
	private String valDate;
	/**交易发生日期*/
	@Excel(name = "交易发生日期", width = 15)
    @ApiModelProperty(value = "交易发生日期")
	private String purDate;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String inpTime;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String xtranno;
	/**交易网点*/
	@Excel(name = "交易网点", width = 15)
    @ApiModelProperty(value = "交易网点")
	private String brno;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String empno;
	/**汇兑手续费或系统追踪号*/
	@Excel(name = "汇兑手续费或系统追踪号", width = 15)
    @ApiModelProperty(value = "汇兑手续费或系统追踪号")
	private java.math.BigDecimal schmfeeamt;
	/**月份编号*/
	@Excel(name = "月份编号", width = 15)
    @ApiModelProperty(value = "月份编号")
	private String monthNbr;
	/**原始币种*/
	@Excel(name = "原始币种", width = 15)
    @ApiModelProperty(value = "原始币种")
	private String orgnCurr;
	/**原始金额*/
	@Excel(name = "原始金额", width = 15)
    @ApiModelProperty(value = "原始金额")
	private java.math.BigDecimal orgnAmt;
	/**交易来源*/
	@Excel(name = "交易来源", width = 15)
    @ApiModelProperty(value = "交易来源")
	private String transSrc;
	/**受理方标识码*/
	@Excel(name = "受理方标识码", width = 15)
    @ApiModelProperty(value = "受理方标识码")
	private String capAddr;
	/**分期付款类型*/
	@Excel(name = "分期付款类型", width = 15)
    @ApiModelProperty(value = "分期付款类型")
	private String secLvl;
	/**人行渠道标志*/
	@Excel(name = "人行渠道标志", width = 15)
    @ApiModelProperty(value = "人行渠道标志")
	private String cbRights;
	/**分期付款序号及其他信息*/
	@Excel(name = "分期付款序号及其他信息", width = 15)
    @ApiModelProperty(value = "分期付款序号及其他信息")
	private String merchSeq;
	/**交易金额中溢缴款部分金额*/
	@Excel(name = "交易金额中溢缴款部分金额", width = 15)
    @ApiModelProperty(value = "交易金额中溢缴款部分金额")
	private java.math.BigDecimal expayAmt;
	/**万事达免汇兑手续费交易标志或IC卡交易标志*/
	@Excel(name = "万事达免汇兑手续费交易标志或IC卡交易标志", width = 15)
    @ApiModelProperty(value = "万事达免汇兑手续费交易标志或IC卡交易标志")
	private String pointPost;
	/**渠道标识*/
	@Excel(name = "渠道标识", width = 15)
    @ApiModelProperty(value = "渠道标识")
	private String purchseId;
	/**代理机构、转发机构、发卡机构代码*/
	@Excel(name = "代理机构、转发机构、发卡机构代码", width = 15)
    @ApiModelProperty(value = "代理机构、转发机构、发卡机构代码")
	private String mcposData;
	/**国家代码*/
	@Excel(name = "国家代码", width = 15)
    @ApiModelProperty(value = "国家代码")
	private String merchStat;
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

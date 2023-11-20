package org.cmms.modules.sjxf.qtxt.cwglxt.yqckdjb.entity;

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
 * @Description: 约期存款登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ebss_future_account_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ebss_future_account_reg对象", description="约期存款登记簿")
public class Yqckdjb {
    
	/**审批编号*/
	@Excel(name = "审批编号", width = 15)
    @ApiModelProperty(value = "审批编号")
	private String appNo;
	/**交易单编号*/
	@Excel(name = "交易单编号", width = 15)
    @ApiModelProperty(value = "交易单编号")
	private String pactNo;
	/**交易明细笔次*/
	@Excel(name = "交易明细笔次", width = 15)
    @ApiModelProperty(value = "交易明细笔次")
	private String txCnt;
	/**存出部门*/
	@Excel(name = "存出部门", width = 15)
    @ApiModelProperty(value = "存出部门")
	private String outdepNo;
	/**存出联社*/
	@Excel(name = "存出联社", width = 15)
    @ApiModelProperty(value = "存出联社")
	private String outupBrNo;
	/**存出联社名称*/
	@Excel(name = "存出联社名称", width = 15)
    @ApiModelProperty(value = "存出联社名称")
	private String outupBrName;
	/**存入部门*/
	@Excel(name = "存入部门", width = 15)
    @ApiModelProperty(value = "存入部门")
	private String indepNo;
	/**存入联社*/
	@Excel(name = "存入联社", width = 15)
    @ApiModelProperty(value = "存入联社")
	private String inupBrNo;
	/**品种编号*/
	@Excel(name = "品种编号", width = 15)
    @ApiModelProperty(value = "品种编号")
	private String zjType;
	/**期限(天)*/
	@Excel(name = "期限(天)", width = 15)
    @ApiModelProperty(value = "期限(天)")
	private String term;
	/**起息日*/
	@Excel(name = "起息日", width = 15)
    @ApiModelProperty(value = "起息日")
	private String icDate;
	/**到期日*/
	@Excel(name = "到期日", width = 15)
    @ApiModelProperty(value = "到期日")
	private String mtrDate;
	/**存出方划款日期*/
	@Excel(name = "存出方划款日期", width = 15)
    @ApiModelProperty(value = "存出方划款日期")
	private String hkDate;
	/**存出方开户行行名*/
	@Excel(name = "存出方开户行行名", width = 15)
    @ApiModelProperty(value = "存出方开户行行名")
	private String outBankName;
	/**存出方核心账号*/
	@Excel(name = "存出方核心账号", width = 15)
    @ApiModelProperty(value = "存出方核心账号")
	private String outBankAcno;
	/**存入方开户行行名*/
	@Excel(name = "存入方开户行行名", width = 15)
    @ApiModelProperty(value = "存入方开户行行名")
	private String inBankName;
	/**存入方核心账号*/
	@Excel(name = "存入方核心账号", width = 15)
    @ApiModelProperty(value = "存入方核心账号")
	private String inBankAcno;
	/**存入方收款日期*/
	@Excel(name = "存入方收款日期", width = 15)
    @ApiModelProperty(value = "存入方收款日期")
	private String skDate;
	/**存出内部帐AC_NO*/
	@Excel(name = "存出内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "存出内部帐AC_NO")
	private String outAcNo;
	/**存入内部帐AC_NO*/
	@Excel(name = "存入内部帐AC_NO", width = 15)
    @ApiModelProperty(value = "存入内部帐AC_NO")
	private String inAcNo;
	/**存出内部账AC_ID*/
	@Excel(name = "存出内部账AC_ID", width = 15)
    @ApiModelProperty(value = "存出内部账AC_ID")
	private String outAcId;
	/**存入内部账AC_ID*/
	@Excel(name = "存入内部账AC_ID", width = 15)
    @ApiModelProperty(value = "存入内部账AC_ID")
	private String inAcId;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15)
    @ApiModelProperty(value = "登记日期")
	private String regDate;
	/**存出联社交易单寄发日期*/
	@Excel(name = "存出联社交易单寄发日期", width = 15)
    @ApiModelProperty(value = "存出联社交易单寄发日期")
	private String sendDate;
	/**存出联社收到回寄交易单日期*/
	@Excel(name = "存出联社收到回寄交易单日期", width = 15)
    @ApiModelProperty(value = "存出联社收到回寄交易单日期")
	private String getsendDate;
	/**存入联社交易单收到日期*/
	@Excel(name = "存入联社交易单收到日期", width = 15)
    @ApiModelProperty(value = "存入联社交易单收到日期")
	private String getDate;
	/**存入联社交易单回寄日期*/
	@Excel(name = "存入联社交易单回寄日期", width = 15)
    @ApiModelProperty(value = "存入联社交易单回寄日期")
	private String backDate;
	/**存出方状态*/
	@Excel(name = "存出方状态", width = 15)
    @ApiModelProperty(value = "存出方状态")
	private String outSts;
	/**转存后约期存款编号*/
	@Excel(name = "转存后约期存款编号", width = 15)
    @ApiModelProperty(value = "转存后约期存款编号")
	private String newAppNo;
	/**存出方业务编号*/
	@Excel(name = "存出方业务编号", width = 15)
    @ApiModelProperty(value = "存出方业务编号")
	private String outYwNo;
	/**计划编号*/
	@Excel(name = "计划编号", width = 15)
    @ApiModelProperty(value = "计划编号")
	private String planNo;
	/**计划日期*/
	@Excel(name = "计划日期", width = 15)
    @ApiModelProperty(value = "计划日期")
	private String chnlInd;
	/**财务流水号*/
	@Excel(name = "财务流水号", width = 15)
    @ApiModelProperty(value = "财务流水号")
	private String traceNo;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String txDate;
	/**存出方账务部门*/
	@Excel(name = "存出方账务部门", width = 15)
    @ApiModelProperty(value = "存出方账务部门")
	private String outdcBrNo;
	/**存入方账务部门*/
	@Excel(name = "存入方账务部门", width = 15)
    @ApiModelProperty(value = "存入方账务部门")
	private String indcBrNo;
	/**存入方状态*/
	@Excel(name = "存入方状态", width = 15)
    @ApiModelProperty(value = "存入方状态")
	private String inSts;
	/**存入方业务编码*/
	@Excel(name = "存入方业务编码", width = 15)
    @ApiModelProperty(value = "存入方业务编码")
	private String inYwNo;
	/**清算速度*/
	@Excel(name = "清算速度", width = 15)
    @ApiModelProperty(value = "清算速度")
	private String qsSpeed;
	/**存入方经办人*/
	@Excel(name = "存入方经办人", width = 15)
    @ApiModelProperty(value = "存入方经办人")
	private String ctpyTrader;
	/**存出方经办人*/
	@Excel(name = "存出方经办人", width = 15)
    @ApiModelProperty(value = "存出方经办人")
	private String trader;
	/**五级分类结果*/
	@Excel(name = "五级分类结果", width = 15)
    @ApiModelProperty(value = "五级分类结果")
	private String lvlFive;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curNo;
	/**存入方版本*/
	@Excel(name = "存入方版本", width = 15)
    @ApiModelProperty(value = "存入方版本")
	private String version;
	/**存入方个性化编号*/
	@Excel(name = "存入方个性化编号", width = 15)
    @ApiModelProperty(value = "存入方个性化编号")
	private String subCtpyNo;
	/**存入方个性化版本*/
	@Excel(name = "存入方个性化版本", width = 15)
    @ApiModelProperty(value = "存入方个性化版本")
	private String subVersion;
	/**存入方部门*/
	@Excel(name = "存入方部门", width = 15)
    @ApiModelProperty(value = "存入方部门")
	private String ctpyDepNo;
	/**存出方部门*/
	@Excel(name = "存出方部门", width = 15)
    @ApiModelProperty(value = "存出方部门")
	private String bctpyDepNo;
	/**存出方版本*/
	@Excel(name = "存出方版本", width = 15)
    @ApiModelProperty(value = "存出方版本")
	private String bversion;
	/**存出方个性化编号*/
	@Excel(name = "存出方个性化编号", width = 15)
    @ApiModelProperty(value = "存出方个性化编号")
	private String bsubCtpyNo;
	/**存出方编号*/
	@Excel(name = "存出方编号", width = 15)
    @ApiModelProperty(value = "存出方编号")
	private String bctpyNo;
	/**存入方编号*/
	@Excel(name = "存入方编号", width = 15)
    @ApiModelProperty(value = "存入方编号")
	private String ctpyNo;
	/**存出方个性化版本*/
	@Excel(name = "存出方个性化版本", width = 15)
    @ApiModelProperty(value = "存出方个性化版本")
	private String bsubVersion;
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

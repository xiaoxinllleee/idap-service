package org.cmms.modules.sjxf.qtxt.gkjzzf.gkjzzfplcwmxb.entity;

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
 * @Description: 国库集中支付批量账务明细表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_gkzf_batch_list")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_gkzf_batch_list对象", description="国库集中支付批量账务明细表")
public class Gkjzzfplcwmxb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String txDate;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String txTime;
	/**前置流水号*/
	@Excel(name = "前置流水号", width = 15)
    @ApiModelProperty(value = "前置流水号")
	private String txSeqNo;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String serialNo;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**行号*/
	@Excel(name = "行号", width = 15)
    @ApiModelProperty(value = "行号")
	private String bankNo;
	/**行名*/
	@Excel(name = "行名", width = 15)
    @ApiModelProperty(value = "行名")
	private String bankName;
	/**账号类型*/
	@Excel(name = "账号类型", width = 15)
    @ApiModelProperty(value = "账号类型")
	private String acctType;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String acctName;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String certType;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String certCode;
	/**企业明细流水号*/
	@Excel(name = "企业明细流水号", width = 15)
    @ApiModelProperty(value = "企业明细流水号")
	private String listSeqNo;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private Long txAmt;
	/**摘要码*/
	@Excel(name = "摘要码", width = 15)
    @ApiModelProperty(value = "摘要码")
	private String rmrkCode;
	/**摘要说明*/
	@Excel(name = "摘要说明", width = 15)
    @ApiModelProperty(value = "摘要说明")
	private String rmrk;
	/**实际交易成功金额*/
	@Excel(name = "实际交易成功金额", width = 15)
    @ApiModelProperty(value = "实际交易成功金额")
	private Long succAmt;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String state;
	/**出错码*/
	@Excel(name = "出错码", width = 15)
    @ApiModelProperty(value = "出错码")
	private String errCode;
	/**出错信息*/
	@Excel(name = "出错信息", width = 15)
    @ApiModelProperty(value = "出错信息")
	private String errMsg;
	/**核心账务日期*/
	@Excel(name = "核心账务日期", width = 15)
    @ApiModelProperty(value = "核心账务日期")
	private String hostDate;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private String hostSeqNo;
	/**自定义自段1*/
	@Excel(name = "自定义自段1", width = 15)
    @ApiModelProperty(value = "自定义自段1")
	private String usrDefine1;
	/**自定义自段2*/
	@Excel(name = "自定义自段2", width = 15)
    @ApiModelProperty(value = "自定义自段2")
	private String usrDefine2;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**导入日期*/
    @ApiModelProperty(value = "导入日期")
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

package org.cmms.modules.sjxf.hxxt.nbzjykb.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 内部帐交易宽表
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("Cbs_gect_base")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cbs_gect_base对象", description="内部帐交易宽表")
public class Nbzjykb {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
	/**加载日期*/
    @ApiModelProperty(value = "加载日期")
	private Date loadDate;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String acctName;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tranType;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stat;
	/**过账日期*/
	@Excel(name = "过账日期", width = 15)
    @ApiModelProperty(value = "过账日期")
	private String postDate;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String trnDate;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String trnCode;
	/**交易码名称*/
	@Excel(name = "交易码名称", width = 15)
    @ApiModelProperty(value = "交易码名称")
	private String trnName;
	/**现金总额*/
	@Excel(name = "现金总额", width = 15)
    @ApiModelProperty(value = "现金总额")
	private String cashTots;
	/**渠道类型*/
	@Excel(name = "渠道类型", width = 15)
    @ApiModelProperty(value = "渠道类型")
	private String channel;
	/**交易机构*/
	@Excel(name = "交易机构", width = 15)
    @ApiModelProperty(value = "交易机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String brNo;
	/**交易机构名称*/
	@Excel(name = "交易机构名称", width = 15)
    @ApiModelProperty(value = "交易机构名称")
	private String brName;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String brTell;
	/**交易柜员名称*/
	@Excel(name = "交易柜员名称", width = 15)
    @ApiModelProperty(value = "交易柜员名称")
	private String brTellName;
	/**销账信息*/
	@Excel(name = "销账信息", width = 15)
    @ApiModelProperty(value = "销账信息")
	private String srnInfo;
	/**转账信息*/
	@Excel(name = "转账信息", width = 15)
    @ApiModelProperty(value = "转账信息")
	private String accountno;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amount;
	/**当前账户金额*/
	@Excel(name = "当前账户金额", width = 15)
    @ApiModelProperty(value = "当前账户金额")
	private String currentBalance;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String comments;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String jrnlNo;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currency;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String trnTime;
	/**冲正标识*/
	@Excel(name = "冲正标识", width = 15)
    @ApiModelProperty(value = "冲正标识")
	private String correction;
	/**柜员类型,1是虚拟柜员，0是普通柜员*/
	@Excel(name = "柜员类型,1是虚拟柜员，0是普通柜员", width = 15)
    @ApiModelProperty(value = "柜员类型,1是虚拟柜员，0是普通柜员")
	private String empType;
	/**虚拟柜员对应的终端*/
	@Excel(name = "虚拟柜员对应的终端", width = 15)
    @ApiModelProperty(value = "虚拟柜员对应的终端")
	private String terminalId;
	/**记录号*/
	@Excel(name = "记录号", width = 15)
    @ApiModelProperty(value = "记录号")
	private String recNo;
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

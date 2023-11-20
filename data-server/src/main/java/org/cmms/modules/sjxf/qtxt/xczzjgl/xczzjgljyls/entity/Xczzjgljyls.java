package org.cmms.modules.sjxf.qtxt.xczzjgl.xczzjgljyls.entity;

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
 * @Description: 乡财政资金管理交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_xczzj_trans_reg")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_xczzj_trans_reg对象", description="乡财政资金管理交易流水")
public class Xczzjgljyls {
    
	/**前置日期*/
	@Excel(name = "前置日期", width = 15)
    @ApiModelProperty(value = "前置日期")
	private String txDate;
	/**前置流水号*/
	@Excel(name = "前置流水号", width = 15)
    @ApiModelProperty(value = "前置流水号")
	private String seqNo;
	/**前置时间*/
	@Excel(name = "前置时间", width = 15)
    @ApiModelProperty(value = "前置时间")
	private String txTime;
	/**前置交易码*/
	@Excel(name = "前置交易码", width = 15)
    @ApiModelProperty(value = "前置交易码")
	private String txCode;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String state;
	/**请求渠道编码*/
	@Excel(name = "请求渠道编码", width = 15)
    @ApiModelProperty(value = "请求渠道编码")
	private String chNo;
	/**接入节点号*/
	@Excel(name = "接入节点号", width = 15)
    @ApiModelProperty(value = "接入节点号")
	private String sndApp;
	/**请求渠道日期*/
	@Excel(name = "请求渠道日期", width = 15)
    @ApiModelProperty(value = "请求渠道日期")
	private String chDate;
	/**请求渠道交易码*/
	@Excel(name = "请求渠道交易码", width = 15)
    @ApiModelProperty(value = "请求渠道交易码")
	private String chTxCode;
	/**渠道流水号*/
	@Excel(name = "渠道流水号", width = 15)
    @ApiModelProperty(value = "渠道流水号")
	private String chSeqNo;
	/**机构 操作机构代码*/
	@Excel(name = "机构 操作机构代码", width = 15)
    @ApiModelProperty(value = "机构 操作机构代码")
	private String instNo;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String operNo;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String authOperNo;
	/**中间业务类型*/
	@Excel(name = "中间业务类型", width = 15)
    @ApiModelProperty(value = "中间业务类型")
	private String zjywlx;
	/**核心交易码*/
	@Excel(name = "核心交易码", width = 15)
    @ApiModelProperty(value = "核心交易码")
	private String hostTxCode;
	/**核心日期*/
	@Excel(name = "核心日期", width = 15)
    @ApiModelProperty(value = "核心日期")
	private String hostDate;
	/**核心流水号*/
	@Excel(name = "核心流水号", width = 15)
    @ApiModelProperty(value = "核心流水号")
	private String hostSeqNo;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private Long txAmt;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String dcFlag;
	/**单位编码*/
	@Excel(name = "单位编码", width = 15)
    @ApiModelProperty(value = "单位编码")
	private String unitNo;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String unitName;
	/**单位账号*/
	@Excel(name = "单位账号", width = 15)
    @ApiModelProperty(value = "单位账号")
	private String unitAcctNo;
	/**第三方交易码*/
	@Excel(name = "第三方交易码", width = 15)
    @ApiModelProperty(value = "第三方交易码")
	private String entTxCode;
	/**企业日期*/
	@Excel(name = "企业日期", width = 15)
    @ApiModelProperty(value = "企业日期")
	private String entDate;
	/**企业流水号*/
	@Excel(name = "企业流水号", width = 15)
    @ApiModelProperty(value = "企业流水号")
	private String entSeqNo;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "客户账号")
	private String custAcctNo;
	/**客户账号类型 */
	@Excel(name = "客户账号类型", width = 15)
    @ApiModelProperty(value = "客户账号类型")
	private String custAcctType;
	/**客户账号机构*/
	@Excel(name = "客户账号机构", width = 15)
    @ApiModelProperty(value = "客户账号机构")
	private String custAcctInst;
	/**客户账号科目*/
	@Excel(name = "客户账号科目", width = 15)
    @ApiModelProperty(value = "客户账号科目")
	private String custAcctKmh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String custName;
	/**第三方客户标识符*/
	@Excel(name = "第三方客户标识符", width = 15)
    @ApiModelProperty(value = "第三方客户标识符")
	private String fCustNo;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String tranStat;
	/**错误码*/
	@Excel(name = "错误码", width = 15)
    @ApiModelProperty(value = "错误码")
	private String errCode;
	/**错误信息*/
	@Excel(name = "错误信息", width = 15)
    @ApiModelProperty(value = "错误信息")
	private String errMsg;
	/**自定义自段1*/
	@Excel(name = "自定义自段1", width = 15)
    @ApiModelProperty(value = "自定义自段1")
	private String usrdefine1;
	/**自定义自段2*/
	@Excel(name = "自定义自段2", width = 15)
    @ApiModelProperty(value = "自定义自段2")
	private String usrdefine2;
	/**uuid*/
	@Excel(name = "uuid", width = 15)
    @ApiModelProperty(value = "uuid")
	private String uuid;
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

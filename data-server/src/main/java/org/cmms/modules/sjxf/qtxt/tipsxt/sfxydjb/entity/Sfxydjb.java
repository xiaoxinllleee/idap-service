package org.cmms.modules.sjxf.qtxt.tipsxt.sfxydjb.entity;

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
 * @Description: TIPS三方协议登记簿
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ibus_tipbprotocol")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_tipbprotocol对象", description="TIPS三方协议登记簿")
public class Sfxydjb {
    
	/**协议书号*/
	@Excel(name = "协议书号", width = 15)
    @ApiModelProperty(value = "协议书号")
	private String protocolno;
	/**付款账户*/
	@Excel(name = "付款账户", width = 15)
    @ApiModelProperty(value = "付款账户")
	private String payacct;
	/**征收机关代码*/
	@Excel(name = "征收机关代码", width = 15)
    @ApiModelProperty(value = "征收机关代码")
	private String taxorgcode;
	/**纳税人名称*/
	@Excel(name = "纳税人名称", width = 15)
    @ApiModelProperty(value = "纳税人名称")
	private String taxpayname;
	/**纳税人编码*/
	@Excel(name = "纳税人编码", width = 15)
    @ApiModelProperty(value = "纳税人编码")
	private String taxpaycode;
	/**付款开户行行号*/
	@Excel(name = "付款开户行行号", width = 15)
    @ApiModelProperty(value = "付款开户行行号")
	private String payopbkcode;
	/**付款行行号*/
	@Excel(name = "付款行行号", width = 15)
    @ApiModelProperty(value = "付款行行号")
	private String paybkcode;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String accbrno;
	/**缴款单位名称*/
	@Excel(name = "缴款单位名称", width = 15)
    @ApiModelProperty(value = "缴款单位名称")
	private String handorgname;
	/**征收机关名称*/
	@Excel(name = "征收机关名称", width = 15)
    @ApiModelProperty(value = "征收机关名称")
	private String taxorgname;
	/**付款开户行行名*/
	@Excel(name = "付款开户行行名", width = 15)
    @ApiModelProperty(value = "付款开户行行名")
	private String payopbkname;
	/**付款行行名*/
	@Excel(name = "付款行行名", width = 15)
    @ApiModelProperty(value = "付款行行名")
	private String paybkname;
	/**账户类型*/
	@Excel(name = "账户类型", width = 15)
    @ApiModelProperty(value = "账户类型")
	private String acctype;
	/**协议状态*/
	@Excel(name = "协议状态", width = 15)
    @ApiModelProperty(value = "协议状态")
	private String protocolstatus;
	/**录入柜员*/
	@Excel(name = "录入柜员", width = 15)
    @ApiModelProperty(value = "录入柜员")
	private String tellerno;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String busidate;
	/**验证日期*/
	@Excel(name = "验证日期", width = 15)
    @ApiModelProperty(value = "验证日期")
	private String vcdate;
	/**撤销日期*/
	@Excel(name = "撤销日期", width = 15)
    @ApiModelProperty(value = "撤销日期")
	private String canceldate;
	/**打印次数*/
	@Excel(name = "打印次数", width = 15)
    @ApiModelProperty(value = "打印次数")
	private String printcnt;
	/**数据来源*/
	@Excel(name = "数据来源", width = 15)
    @ApiModelProperty(value = "数据来源")
	private String datasource;
	/**预留字段1*/
	@Excel(name = "预留字段1", width = 15)
    @ApiModelProperty(value = "预留字段1")
	private String reserved1;
	/**预留字段2*/
	@Excel(name = "预留字段2", width = 15)
    @ApiModelProperty(value = "预留字段2")
	private String reserved2;
	/**预留字段3*/
	@Excel(name = "预留字段3", width = 15)
    @ApiModelProperty(value = "预留字段3")
	private String reserved3;
	/**新付款账号*/
	@Excel(name = "新付款账号", width = 15)
    @ApiModelProperty(value = "新付款账号")
	private String xpayacct;
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

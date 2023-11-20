package org.cmms.modules.sjxf.qtxt.dsxf.dsxfjyls.entity;

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
 * @Description: 代收学费交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_dsxfjfmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_dsxfjfmx对象", description="代收学费交易流水")
public class Dsxfjyls {
    
	/**渠道交易日期*/
	@Excel(name = "渠道交易日期", width = 15)
    @ApiModelProperty(value = "渠道交易日期")
	private String chnldate;
	/**渠道交易流水*/
	@Excel(name = "渠道交易流水", width = 15)
    @ApiModelProperty(value = "渠道交易流水")
	private String chnlseqno;
	/**平台交易日期*/
	@Excel(name = "平台交易日期", width = 15)
    @ApiModelProperty(value = "平台交易日期")
	private String workdate;
	/**平台交易流水*/
	@Excel(name = "平台交易流水", width = 15)
    @ApiModelProperty(value = "平台交易流水")
	private String workseqno;
	/**上核心流水号*/
	@Excel(name = "上核心流水号", width = 15)
    @ApiModelProperty(value = "上核心流水号")
	private String hostserialno;
	/**平台时间*/
	@Excel(name = "平台时间", width = 15)
    @ApiModelProperty(value = "平台时间")
	private String worktime;
	/**地区代码*/
	@Excel(name = "地区代码", width = 15)
    @ApiModelProperty(value = "地区代码")
	private String zoneid;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String tradetype;
	/**对于代收代付类的交易*/
	@Excel(name = "对于代收代付类的交易", width = 15)
    @ApiModelProperty(value = "对于代收代付类的交易")
	private String cupflag;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerno;
	/**操作终端号*/
	@Excel(name = "操作终端号", width = 15)
    @ApiModelProperty(value = "操作终端号")
	private String terminalno;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String bankno;
	/**柜面流水号*/
	@Excel(name = "柜面流水号", width = 15)
    @ApiModelProperty(value = "柜面流水号")
	private String counterseqno;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String authtellerno;
	/**对账区分*/
	@Excel(name = "对账区分", width = 15)
    @ApiModelProperty(value = "对账区分")
	private String needchk;
	/**核心记账状态*/
	@Excel(name = "核心记账状态", width = 15)
    @ApiModelProperty(value = "核心记账状态")
	private String hoststatus;
	/**核心交易流水号*/
	@Excel(name = "核心交易流水号", width = 15)
    @ApiModelProperty(value = "核心交易流水号")
	private String hostseqno;
	/**核心交易日期*/
	@Excel(name = "核心交易日期", width = 15)
    @ApiModelProperty(value = "核心交易日期")
	private String hostdate;
	/**核心错误码*/
	@Excel(name = "核心错误码", width = 15)
    @ApiModelProperty(value = "核心错误码")
	private String hosterrcode;
	/**核心错误信息*/
	@Excel(name = "核心错误信息", width = 15)
    @ApiModelProperty(value = "核心错误信息")
	private String hosterrmsg;
	/**核心对账标志*/
	@Excel(name = "核心对账标志", width = 15)
    @ApiModelProperty(value = "核心对账标志")
	private String hostchkflag;
	/**第三方处理状态*/
	@Excel(name = "第三方处理状态", width = 15)
    @ApiModelProperty(value = "第三方处理状态")
	private String corpstatus;
	/**第三方流水号*/
	@Excel(name = "第三方流水号", width = 15)
    @ApiModelProperty(value = "第三方流水号")
	private String corpseqno;
	/**第三方交易日期*/
	@Excel(name = "第三方交易日期", width = 15)
    @ApiModelProperty(value = "第三方交易日期")
	private String corpdate;
	/**第三方错误码*/
	@Excel(name = "第三方错误码", width = 15)
    @ApiModelProperty(value = "第三方错误码")
	private String corperrcode;
	/**第三方错误信息*/
	@Excel(name = "第三方错误信息", width = 15)
    @ApiModelProperty(value = "第三方错误信息")
	private String corperrmsg;
	/**第三方对账标志*/
	@Excel(name = "第三方对账标志", width = 15)
    @ApiModelProperty(value = "第三方对账标志")
	private String corpchkflag;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**业务错误码*/
	@Excel(name = "业务错误码", width = 15)
    @ApiModelProperty(value = "业务错误码")
	private String busierrcode;
	/**业务错误信息*/
	@Excel(name = "业务错误信息", width = 15)
    @ApiModelProperty(value = "业务错误信息")
	private String busierrmsg;
	/**学校编码*/
	@Excel(name = "学校编码", width = 15)
    @ApiModelProperty(value = "学校编码")
	private String xxbm;
	/**学校名称*/
	@Excel(name = "学校名称", width = 15)
    @ApiModelProperty(value = "学校名称")
	private String xxmc;
	/**学费期次*/
	@Excel(name = "学费期次", width = 15)
    @ApiModelProperty(value = "学费期次")
	private String termno;
	/**用户号码*/
	@Excel(name = "用户号码", width = 15)
    @ApiModelProperty(value = "用户号码")
	private String userid;
	/**用户姓名*/
	@Excel(name = "用户姓名", width = 15)
    @ApiModelProperty(value = "用户姓名")
	private String username;
	/**专业*/
	@Excel(name = "专业", width = 15)
    @ApiModelProperty(value = "专业")
	private String major;
	/**班级*/
	@Excel(name = "班级", width = 15)
    @ApiModelProperty(value = "班级")
	@TableField(value = "CLASS")
	private String classxx;
	/**电话*/
	@Excel(name = "电话", width = 15)
    @ApiModelProperty(value = "电话")
	private String mobile;
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String payeraccno;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payername;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String acctseqno;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String payeeaccno;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String payeename;
	/**收款人开户机构号*/
	@Excel(name = "收款人开户机构号", width = 15)
    @ApiModelProperty(value = "收款人开户机构号")
	private String payeeopenbrno;
	/**缴费方式*/
	@Excel(name = "缴费方式", width = 15)
    @ApiModelProperty(value = "缴费方式")
	private String cashflag;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cardflag;
	/**现金项目编号*/
	@Excel(name = "现金项目编号", width = 15)
    @ApiModelProperty(value = "现金项目编号")
	private String cashprjcode;
	/**中间账户*/
	@Excel(name = "中间账户", width = 15)
    @ApiModelProperty(value = "中间账户")
	private String acctbrno;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String currcode;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amt;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private String fee;
	/**摘要码*/
	@Excel(name = "摘要码", width = 15)
    @ApiModelProperty(value = "摘要码")
	private String memcode;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String idno;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String idtype;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String vouchtype;
	/**凭证机构*/
	@Excel(name = "凭证机构", width = 15)
    @ApiModelProperty(value = "凭证机构")
	private String vouchbatch;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String vouchno;
	/**凭证日期*/
	@Excel(name = "凭证日期", width = 15)
    @ApiModelProperty(value = "凭证日期")
	private String vouchdate;
	/**发票打印次数*/
	@Excel(name = "发票打印次数", width = 15)
    @ApiModelProperty(value = "发票打印次数")
	private String prtnum;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String revflag;
	/**原交易日期*/
	@Excel(name = "原交易日期", width = 15)
    @ApiModelProperty(value = "原交易日期")
	private String otrandate;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private String otranseqno;
	/**原交易上核心流水号*/
	@Excel(name = "原交易上核心流水号", width = 15)
    @ApiModelProperty(value = "原交易上核心流水号")
	private String otranhostno;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String reserve1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String reserve2;
	/**备注3*/
	@Excel(name = "备注3", width = 15)
    @ApiModelProperty(value = "备注3")
	private String reserve3;
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

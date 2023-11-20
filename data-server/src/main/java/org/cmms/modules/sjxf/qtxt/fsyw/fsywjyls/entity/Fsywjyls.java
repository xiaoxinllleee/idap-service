package org.cmms.modules.sjxf.qtxt.fsyw.fsywjyls.entity;

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
 * @Description: 非税业务交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_fss_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_fss_debtinfo对象", description="非税业务交易流水")
public class Fsywjyls {

	/**平台交易日期*/
	@Excel(name = "平台交易日期", width = 15)
    @ApiModelProperty(value = "平台交易日期")
	private String workdate;
	/**平台时间*/
	@Excel(name = "平台时间", width = 15)
    @ApiModelProperty(value = "平台时间")
	private String worktime;
	/**上送核心流水号*/
	@Excel(name = "上送核心流水号", width = 15)
    @ApiModelProperty(value = "上送核心流水号")
	private String uuid;
	/**票号*/
	@Excel(name = "票号", width = 15)
    @ApiModelProperty(value = "票号")
	private String ph;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String ywlx;
	/**缴款方式*/
	@Excel(name = "缴款方式", width = 15)
    @ApiModelProperty(value = "缴款方式")
	private String jkfs;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String skrqc;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String skrzh;
	/**缴款人姓名*/
	@Excel(name = "缴款人姓名", width = 15)
    @ApiModelProperty(value = "缴款人姓名")
	private String jkr;
	/**缴款人账号*/
	@Excel(name = "缴款人账号", width = 15)
    @ApiModelProperty(value = "缴款人账号")
	private String jkrzh;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal je;
	/**核心记账状态*/
	@Excel(name = "核心记账状态", width = 15)
    @ApiModelProperty(value = "核心记账状态")
	private String hoststatus;
	/**核心对账标志*/
	@Excel(name = "核心对账标志", width = 15)
    @ApiModelProperty(value = "核心对账标志")
	private String hostchkflag;
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
	/**银行交易流水号*/
	@Excel(name = "银行交易流水号", width = 15)
    @ApiModelProperty(value = "银行交易流水号")
	private String yhjylsh;
	/**银行收款时间*/
	@Excel(name = "银行收款时间", width = 15)
    @ApiModelProperty(value = "银行收款时间")
	private String yhsksj;
	/**渠道流水*/
	@Excel(name = "渠道流水", width = 15)
    @ApiModelProperty(value = "渠道流水")
	private String chnlseqno;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnldate;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String operbankno;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String operno;
	/**财政区划内码*/
	@Excel(name = "财政区划内码", width = 15)
    @ApiModelProperty(value = "财政区划内码")
	private String czqhnm;
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

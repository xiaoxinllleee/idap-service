package org.cmms.modules.sjxf.qtxt.yddssf.yddsfdsjyls.entity;

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
 * @Description: 移动电视费代收交易流水
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_ydds_debtinfo")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_ydds_debtinfo对象", description="移动电视费代收交易流水")
public class Yddsfdsjyls {
    
	/**平台交易日期时间*/
	@Excel(name = "平台交易日期时间", width = 15)
    @ApiModelProperty(value = "平台交易日期时间")
	private String workdate;
	/**平台交易流水*/
	@Excel(name = "平台交易流水", width = 15)
    @ApiModelProperty(value = "平台交易流水")
	private String serialno;
	/**平台交易日期*/
	@Excel(name = "平台交易日期", width = 15)
    @ApiModelProperty(value = "平台交易日期")
	private String acctDate;
	/**前置流水号*/
	@Excel(name = "前置流水号", width = 15)
    @ApiModelProperty(value = "前置流水号")
	private String hostserialno;
	/**第三方流水号*/
	@Excel(name = "第三方流水号", width = 15)
    @ApiModelProperty(value = "第三方流水号")
	private String transno;
	/**第三方请求日期时间*/
	@Excel(name = "第三方请求日期时间", width = 15)
    @ApiModelProperty(value = "第三方请求日期时间")
	private String reqdatetime;
	/**业务类型*/
	@Excel(name = "业务类型", width = 15)
    @ApiModelProperty(value = "业务类型")
	private String tradetype;
	/**操作柜员*/
	@Excel(name = "操作柜员", width = 15)
    @ApiModelProperty(value = "操作柜员")
	private String tellerno;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String bankno;
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
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String payeraccno;
	/**付款人名称*/
	@Excel(name = "付款人名称", width = 15)
    @ApiModelProperty(value = "付款人名称")
	private String payername;
	/**账号序号*/
	@Excel(name = "账号序号", width = 15)
    @ApiModelProperty(value = "账号序号")
	private String acctseqno;
	/**收款人账号*/
	@Excel(name = "收款人账号", width = 15)
    @ApiModelProperty(value = "收款人账号")
	private String payeeaccno;
	/**收款人名称*/
	@Excel(name = "收款人名称", width = 15)
    @ApiModelProperty(value = "收款人名称")
	private String payeename;
	/**缴费方式*/
	@Excel(name = "缴费方式", width = 15)
    @ApiModelProperty(value = "缴费方式")
	private String payway;
	/**卡折标志*/
	@Excel(name = "卡折标志", width = 15)
    @ApiModelProperty(value = "卡折标志")
	private String cardflag;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amt;
	/**冲正标志*/
	@Excel(name = "冲正标志", width = 15)
    @ApiModelProperty(value = "冲正标志")
	private String revflag;
	/**用户名*/
	@Excel(name = "用户名", width = 15)
    @ApiModelProperty(value = "用户名")
	private String username;
	/**用户地址*/
	@Excel(name = "用户地址", width = 15)
    @ApiModelProperty(value = "用户地址")
	private String useraddr;
	/**用户身份证号*/
	@Excel(name = "用户身份证号", width = 15)
    @ApiModelProperty(value = "用户身份证号")
	private String useridentno;
	/**电视卡卡号*/
	@Excel(name = "电视卡卡号", width = 15)
    @ApiModelProperty(value = "电视卡卡号")
	private String tvcardno;
	/**节目ID*/
	@Excel(name = "节目ID", width = 15)
    @ApiModelProperty(value = "节目ID")
	private String gameid;
	/**节目名称*/
	@Excel(name = "节目名称", width = 15)
    @ApiModelProperty(value = "节目名称")
	private String gamename;
	/**原交易日期*/
	@Excel(name = "原交易日期", width = 15)
    @ApiModelProperty(value = "原交易日期")
	private String origtrandate;
	/**原交易流水号*/
	@Excel(name = "原交易流水号", width = 15)
    @ApiModelProperty(value = "原交易流水号")
	private String orighostno;
	/**批量标志*/
	@Excel(name = "批量标志", width = 15)
    @ApiModelProperty(value = "批量标志")
	private String batchflag;
	/**批量流水号*/
	@Excel(name = "批量流水号", width = 15)
    @ApiModelProperty(value = "批量流水号")
	private String batchtransno;
	/**批量明细序号*/
	@Excel(name = "批量明细序号", width = 15)
    @ApiModelProperty(value = "批量明细序号")
	private String batchxh;
	/**渠道流水*/
	@Excel(name = "渠道流水", width = 15)
    @ApiModelProperty(value = "渠道流水")
	private String chnlseqno;
	/**渠道日期*/
	@Excel(name = "渠道日期", width = 15)
    @ApiModelProperty(value = "渠道日期")
	private String chnldate;
	/**付款人账号序号*/
	@Excel(name = "付款人账号序号", width = 15)
    @ApiModelProperty(value = "付款人账号序号")
	private String payeracctseqno;
	/**收款人账号序号*/
	@Excel(name = "收款人账号序号", width = 15)
    @ApiModelProperty(value = "收款人账号序号")
	private String payeeacctseqno;
	/**控制子*/
	@Excel(name = "控制子", width = 15)
    @ApiModelProperty(value = "控制子")
	private String controlflag;
	/**uuid*/
	@Excel(name = "uuid", width = 15)
    @ApiModelProperty(value = "uuid")
	private String uuid;
	/**中间业务类型*/
	@Excel(name = "中间业务类型", width = 15)
    @ApiModelProperty(value = "中间业务类型")
	private String zjywlx;
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

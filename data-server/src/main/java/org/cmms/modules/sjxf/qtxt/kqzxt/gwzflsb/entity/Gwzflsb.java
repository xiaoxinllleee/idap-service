package org.cmms.modules.sjxf.qtxt.kqzxt.gwzflsb.entity;

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
 * @Description: 固网支付流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-13
 * @Version: V1.0
 */
@Data
@TableName("Cpps_gwzfmtranjnl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Cpps_gwzfmtranjnl对象", description="固网支付流水表")
public class Gwzflsb {
    
	/**系统编号*/
	@Excel(name = "系统编号", width = 15)
    @ApiModelProperty(value = "系统编号")
	private String sysid;
	/**应用编号*/
	@Excel(name = "应用编号", width = 15)
    @ApiModelProperty(value = "应用编号")
	private String appid;
	/**应用子类*/
	@Excel(name = "应用子类", width = 15)
    @ApiModelProperty(value = "应用子类")
	private String resid;
	/**业务日期*/
	@Excel(name = "业务日期", width = 15)
    @ApiModelProperty(value = "业务日期")
	private String busidate;
	/**平台日期*/
	@Excel(name = "平台日期", width = 15)
    @ApiModelProperty(value = "平台日期")
	private String workdate;
	/**平台流水*/
	@Excel(name = "平台流水", width = 15)
    @ApiModelProperty(value = "平台流水")
	private String workseqid;
	/**平台时间*/
	@Excel(name = "平台时间", width = 15)
    @ApiModelProperty(value = "平台时间")
	private String worktime;
	/**模板代码*/
	@Excel(name = "模板代码", width = 15)
    @ApiModelProperty(value = "模板代码")
	private String templatecode;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String tradecode;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradetype;
	/**交易柜员*/
	@Excel(name = "交易柜员", width = 15)
    @ApiModelProperty(value = "交易柜员")
	private String tellerno;
	/**交易机构*/
	@Excel(name = "交易机构", width = 15)
    @ApiModelProperty(value = "交易机构")
	private String brno;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String amt;
	/**手续费*/
	@Excel(name = "手续费", width = 15)
    @ApiModelProperty(value = "手续费")
	private String feeamt;
	/**实际金额*/
	@Excel(name = "实际金额", width = 15)
    @ApiModelProperty(value = "实际金额")
	private String sjamt;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String curcode;
	/**卡友日期*/
	@Excel(name = "卡友日期", width = 15)
    @ApiModelProperty(value = "卡友日期")
	private String bdjyrq;
	/**系统跟踪号*/
	@Excel(name = "系统跟踪号", width = 15)
    @ApiModelProperty(value = "系统跟踪号")
	private String xtgzh;
	/**卡友交易时间*/
	@Excel(name = "卡友交易时间", width = 15)
    @ApiModelProperty(value = "卡友交易时间")
	private String bdjysj;
	/**清算日期*/
	@Excel(name = "清算日期", width = 15)
    @ApiModelProperty(value = "清算日期")
	private String qsrq;
	/**商户类型*/
	@Excel(name = "商户类型", width = 15)
    @ApiModelProperty(value = "商户类型")
	private String shlx;
	/**商户号*/
	@Excel(name = "商户号", width = 15)
    @ApiModelProperty(value = "商户号")
	private String shh;
	/**受理机构代码*/
	@Excel(name = "受理机构代码", width = 15)
    @ApiModelProperty(value = "受理机构代码")
	private String sljgdm;
	/**发送机构代码*/
	@Excel(name = "发送机构代码", width = 15)
    @ApiModelProperty(value = "发送机构代码")
	private String fsjgdm;
	/**终端号*/
	@Excel(name = "终端号", width = 15)
    @ApiModelProperty(value = "终端号")
	private String zdh;
	/**转出卡号*/
	@Excel(name = "转出卡号", width = 15)
    @ApiModelProperty(value = "转出卡号")
	private String outcardno;
	/**转入卡号*/
	@Excel(name = "转入卡号", width = 15)
    @ApiModelProperty(value = "转入卡号")
	private String incardno;
	/**转出账号*/
	@Excel(name = "转出账号", width = 15)
    @ApiModelProperty(value = "转出账号")
	private String outacctno;
	/**转入账号*/
	@Excel(name = "转入账号", width = 15)
    @ApiModelProperty(value = "转入账号")
	private String inacctno;
	/**中心状态*/
	@Excel(name = "中心状态", width = 15)
    @ApiModelProperty(value = "中心状态")
	private String corpstatus;
	/**中心代码*/
	@Excel(name = "中心代码", width = 15)
    @ApiModelProperty(value = "中心代码")
	private String corperrcode;
	/**中心信息*/
	@Excel(name = "中心信息", width = 15)
    @ApiModelProperty(value = "中心信息")
	private String corperrmsg;
	/**业务状态*/
	@Excel(name = "业务状态", width = 15)
    @ApiModelProperty(value = "业务状态")
	private String busistatus;
	/**交易步骤*/
	@Excel(name = "交易步骤", width = 15)
    @ApiModelProperty(value = "交易步骤")
	private String tradebusistep;
	/**错误代码*/
	@Excel(name = "错误代码", width = 15)
    @ApiModelProperty(value = "错误代码")
	private String errcode;
	/**错误信息*/
	@Excel(name = "错误信息", width = 15)
    @ApiModelProperty(value = "错误信息")
	private String errmsg;
	/**凭证类型*/
	@Excel(name = "凭证类型", width = 15)
    @ApiModelProperty(value = "凭证类型")
	private String vouchtype;
	/**凭证号码*/
	@Excel(name = "凭证号码", width = 15)
    @ApiModelProperty(value = "凭证号码")
	private String vouchno;
	/**圈存类型*/
	@Excel(name = "圈存类型", width = 15)
    @ApiModelProperty(value = "圈存类型")
	private String qclx;
	/**圈存号码*/
	@Excel(name = "圈存号码", width = 15)
    @ApiModelProperty(value = "圈存号码")
	private String qchm;
	/**圈存状态(0未开始1已圈存2已解圈)*/
	@Excel(name = "圈存状态(0未开始1已圈存2已解圈)", width = 15)
    @ApiModelProperty(value = "圈存状态(0未开始1已圈存2已解圈)")
	private String qcstatus;
	/**圈存信息*/
	@Excel(name = "圈存信息", width = 15)
    @ApiModelProperty(value = "圈存信息")
	private String qcinfo;
	/**备注1*/
	@Excel(name = "备注1", width = 15)
    @ApiModelProperty(value = "备注1")
	private String remark1;
	/**备注2*/
	@Excel(name = "备注2", width = 15)
    @ApiModelProperty(value = "备注2")
	private String remark2;
	/**备注3*/
	@Excel(name = "备注3", width = 15)
    @ApiModelProperty(value = "备注3")
	private String remark3;
	/**圈存标志*/
	@Excel(name = "圈存标志", width = 15)
    @ApiModelProperty(value = "圈存标志")
	private String qcbz;
	/**日志编号*/
	@Excel(name = "日志编号", width = 15)
    @ApiModelProperty(value = "日志编号")
	private String logsid;
	/**核心状态*/
	@Excel(name = "核心状态", width = 15)
    @ApiModelProperty(value = "核心状态")
	private String bankstatus;
	/**核心日期*/
	@Excel(name = "核心日期", width = 15)
    @ApiModelProperty(value = "核心日期")
	private String bankdate;
	/**最后处理时间*/
	@Excel(name = "最后处理时间", width = 15)
    @ApiModelProperty(value = "最后处理时间")
	private String lasttime;
	/**固网支付业务类型*/
	@Excel(name = "固网支付业务类型", width = 15)
    @ApiModelProperty(value = "固网支付业务类型")
	private String custno;
	/**请求日期时间*/
	@Excel(name = "请求日期时间", width = 15)
    @ApiModelProperty(value = "请求日期时间")
	private String reqdatetime;
	/**交易流水号*/
	@Excel(name = "交易流水号", width = 15)
    @ApiModelProperty(value = "交易流水号")
	private String transno;
	/**转出账号行号*/
	@Excel(name = "转出账号行号", width = 15)
    @ApiModelProperty(value = "转出账号行号")
	private String fkhno;
	/**转出账号行名*/
	@Excel(name = "转出账号行名", width = 15)
    @ApiModelProperty(value = "转出账号行名")
	private String fkhname;
	/**加急标志 0-不加急 1-加急*/
	@Excel(name = "加急标志 0-不加急 1-加急", width = 15)
    @ApiModelProperty(value = "加急标志 0-不加急 1-加急")
	private String tranchl;
	/**收款名称*/
	@Excel(name = "收款名称", width = 15)
    @ApiModelProperty(value = "收款名称")
	private String skname;
	/**转入账号行号*/
	@Excel(name = "转入账号行号", width = 15)
    @ApiModelProperty(value = "转入账号行号")
	private String skhno;
	/**转入账号行名*/
	@Excel(name = "转入账号行名", width = 15)
    @ApiModelProperty(value = "转入账号行名")
	private String skhname;
	/**固网支付交易码*/
	@Excel(name = "固网支付交易码", width = 15)
    @ApiModelProperty(value = "固网支付交易码")
	private String trancode;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String dataDate;
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

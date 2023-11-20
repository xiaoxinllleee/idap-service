package org.cmms.modules.sjxf.qtxt.ycyw.ycywjylsb.entity;

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
 * @Description: 烟草业务交易流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-20
 * @Version: V1.0
 */
@Data
@TableName("Ibus_ycdslsb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_ycdslsb对象", description="烟草业务交易流水表")
public class Ycywjylsb {
    
	/**查询日期*/
	@Excel(name = "查询日期", width = 15)
    @ApiModelProperty(value = "查询日期")
	private String cxrq;
	/**交易码*/
	@Excel(name = "交易码", width = 15)
    @ApiModelProperty(value = "交易码")
	private String jym;
	/**机构码*/
	@Excel(name = "机构码", width = 15)
    @ApiModelProperty(value = "机构码")
	private String jgm;
	/**项目编号*/
	@Excel(name = "项目编号", width = 15)
    @ApiModelProperty(value = "项目编号")
	private String xmbh;
	/**柜员号*/
	@Excel(name = "柜员号", width = 15)
    @ApiModelProperty(value = "柜员号")
	private String gyh;
	/**授权柜员*/
	@Excel(name = "授权柜员", width = 15)
    @ApiModelProperty(value = "授权柜员")
	private String sqgy;
	/**废弃字段1*/
	@Excel(name = "废弃字段1", width = 15)
    @ApiModelProperty(value = "废弃字段1")
	private String czgy;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private String khbh;
	/**客户账号*/
	@Excel(name = "客户账号", width = 15)
    @ApiModelProperty(value = "客户账号")
	private String khzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**账号密码*/
	@Excel(name = "账号密码", width = 15)
    @ApiModelProperty(value = "账号密码")
	private String zhmm;
	/**废弃字段2*/
	@Excel(name = "废弃字段2", width = 15)
    @ApiModelProperty(value = "废弃字段2")
	private String khzj;
	/**废弃字段3*/
	@Excel(name = "废弃字段3", width = 15)
    @ApiModelProperty(value = "废弃字段3")
	private String khpz;
	/**废弃字段4*/
	@Excel(name = "废弃字段4", width = 15)
    @ApiModelProperty(value = "废弃字段4")
	private String xzbz;
	/**支付单位代码*/
	@Excel(name = "支付单位代码", width = 15)
    @ApiModelProperty(value = "支付单位代码")
	private String idno;
	/**接收流水号*/
	@Excel(name = "接收流水号", width = 15)
    @ApiModelProperty(value = "接收流水号")
	private String jslsh;
	/**发送流水号流水号*/
	@Excel(name = "发送流水号流水号", width = 15)
    @ApiModelProperty(value = "发送流水号流水号")
	private String fslsh;
	/**处理日期*/
	@Excel(name = "处理日期", width = 15)
    @ApiModelProperty(value = "处理日期")
	private String clrq;
	/**查询流水号*/
	@Excel(name = "查询流水号", width = 15)
    @ApiModelProperty(value = "查询流水号")
	private String cxlsh;
	/**总笔数*/
	@Excel(name = "总笔数", width = 15)
    @ApiModelProperty(value = "总笔数")
	private String zbs;
	/**总金额*/
	@Excel(name = "总金额", width = 15)
    @ApiModelProperty(value = "总金额")
	private String zje;
	/**成功笔数*/
	@Excel(name = "成功笔数", width = 15)
    @ApiModelProperty(value = "成功笔数")
	private String cgbs;
	/**成功金额*/
	@Excel(name = "成功金额", width = 15)
    @ApiModelProperty(value = "成功金额")
	private String cgje;
	/**失败笔数*/
	@Excel(name = "失败笔数", width = 15)
    @ApiModelProperty(value = "失败笔数")
	private String sbbs;
	/**失败金额*/
	@Excel(name = "失败金额", width = 15)
    @ApiModelProperty(value = "失败金额")
	private String sbje;
	/**余额*/
	@Excel(name = "余额", width = 15)
    @ApiModelProperty(value = "余额")
	private String ye;
	/**明细笔数*/
	@Excel(name = "明细笔数", width = 15)
    @ApiModelProperty(value = "明细笔数")
	private String mxbs;
	/**废弃字段5*/
	@Excel(name = "废弃字段5", width = 15)
    @ApiModelProperty(value = "废弃字段5")
	private String dycs;
	/**废弃字段6*/
	@Excel(name = "废弃字段6", width = 15)
    @ApiModelProperty(value = "废弃字段6")
	private String dzbz;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String zt;
	/**返回状态*/
	@Excel(name = "返回状态", width = 15)
    @ApiModelProperty(value = "返回状态")
	private String retcode;
	/**返回错误码*/
	@Excel(name = "返回错误码", width = 15)
    @ApiModelProperty(value = "返回错误码")
	private String errmsg;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**贷方账号*/
	@Excel(name = "贷方账号", width = 15)
    @ApiModelProperty(value = "贷方账号")
	private String dfzh;
	/**贷方名称*/
	@Excel(name = "贷方名称", width = 15)
    @ApiModelProperty(value = "贷方名称")
	private String dfmc;
	/**核心返回流水号*/
	@Excel(name = "核心返回流水号", width = 15)
    @ApiModelProperty(value = "核心返回流水号")
	private String hostseqno;
	/**主机日期*/
	@Excel(name = "主机日期", width = 15)
    @ApiModelProperty(value = "主机日期")
	private String hostdate;
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

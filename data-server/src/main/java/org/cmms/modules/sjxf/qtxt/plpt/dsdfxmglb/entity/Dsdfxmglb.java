package org.cmms.modules.sjxf.qtxt.plpt.dsdfxmglb.entity;

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
 * @Description: 代收代发项目管理表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_bsp_sdsfxm")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_bsp_sdsfxm对象", description="代收代发项目管理表")
public class Dsdfxmglb {
    
	/**单位编号*/
	@Excel(name = "单位编号", width = 15)
    @ApiModelProperty(value = "单位编号")
	private String dwbh;
	/**项目编号*/
	@Excel(name = "项目编号", width = 15)
    @ApiModelProperty(value = "项目编号")
	private String xmbh;
	/**单位名称*/
	@Excel(name = "单位名称", width = 15)
    @ApiModelProperty(value = "单位名称")
	private String dwmc;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**单位账户类型*/
	@Excel(name = "单位账户类型", width = 15)
    @ApiModelProperty(value = "单位账户类型")
	private String dwzhlx;
	/**单位帐号*/
	@Excel(name = "单位帐号", width = 15)
    @ApiModelProperty(value = "单位帐号")
	private String dwzh;
	/**单位账号户名*/
	@Excel(name = "单位账号户名", width = 15)
    @ApiModelProperty(value = "单位账号户名")
	private String dwzhhm;
	/**子账户编码*/
	@Excel(name = "子账户编码", width = 15)
    @ApiModelProperty(value = "子账户编码")
	private String zhbm;
	/**立项日期*/
	@Excel(name = "立项日期", width = 15)
    @ApiModelProperty(value = "立项日期")
	private String lxrq;
	/**代收代发进度*/
	@Excel(name = "代收代发进度", width = 15)
    @ApiModelProperty(value = "代收代发进度")
	private String dsdfjd;
	/**是否转入过渡户*/
	@Excel(name = "是否转入过渡户", width = 15)
    @ApiModelProperty(value = "是否转入过渡户")
	private String sfzrgdh;
	/**是否转账支票*/
	@Excel(name = "是否转账支票", width = 15)
    @ApiModelProperty(value = "是否转账支票")
	private String sfzzzp;
	/**代收发标志*/
	@Excel(name = "代收发标志", width = 15)
    @ApiModelProperty(value = "代收发标志")
	private String dsfbz;
	/**数据格式*/
	@Excel(name = "数据格式", width = 15)
    @ApiModelProperty(value = "数据格式")
	private String sjgs;
	/**打印格式*/
	@Excel(name = "打印格式", width = 15)
    @ApiModelProperty(value = "打印格式")
	private String dygs;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String zy;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String zt;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**交易批次号*/
	@Excel(name = "交易批次号", width = 15)
    @ApiModelProperty(value = "交易批次号")
	private String jypch;
	/**核心UUID*/
	@Excel(name = "核心UUID", width = 15)
    @ApiModelProperty(value = "核心UUID")
	private String hxuuid;
	/**核心批次号*/
	@Excel(name = "核心批次号", width = 15)
    @ApiModelProperty(value = "核心批次号")
	private String hxpch;
	/**明细1*/
	@Excel(name = "明细1", width = 15)
    @ApiModelProperty(value = "明细1")
	private String mx1;
	/**明细2*/
	@Excel(name = "明细2", width = 15)
    @ApiModelProperty(value = "明细2")
	private String mx2;
	/**明细3*/
	@Excel(name = "明细3", width = 15)
    @ApiModelProperty(value = "明细3")
	private String mx3;
	/**明细4*/
	@Excel(name = "明细4", width = 15)
    @ApiModelProperty(value = "明细4")
	private String mx4;
	/**明细5*/
	@Excel(name = "明细5", width = 15)
    @ApiModelProperty(value = "明细5")
	private String mx5;
	/**明细6*/
	@Excel(name = "明细6", width = 15)
    @ApiModelProperty(value = "明细6")
	private String mx6;
	/**明细7*/
	@Excel(name = "明细7", width = 15)
    @ApiModelProperty(value = "明细7")
	private String mx7;
	/**明细8*/
	@Excel(name = "明细8", width = 15)
    @ApiModelProperty(value = "明细8")
	private String mx8;
	/**明细9*/
	@Excel(name = "明细9", width = 15)
    @ApiModelProperty(value = "明细9")
	private String mx9;
	/**明细10*/
	@Excel(name = "明细10", width = 15)
    @ApiModelProperty(value = "明细10")
	private String mx10;
	/**明细11*/
	@Excel(name = "明细11", width = 15)
    @ApiModelProperty(value = "明细11")
	private String mx11;
	/**明细12*/
	@Excel(name = "明细12", width = 15)
    @ApiModelProperty(value = "明细12")
	private String mx12;
	/**明细13*/
	@Excel(name = "明细13", width = 15)
    @ApiModelProperty(value = "明细13")
	private String mx13;
	/**明细14*/
	@Excel(name = "明细14", width = 15)
    @ApiModelProperty(value = "明细14")
	private String mx14;
	/**明细15*/
	@Excel(name = "明细15", width = 15)
    @ApiModelProperty(value = "明细15")
	private String mx15;
	/**处理状态*/
	@Excel(name = "处理状态", width = 15)
    @ApiModelProperty(value = "处理状态")
	private String clzt;
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
	/**退回金额*/
	@Excel(name = "退回金额", width = 15)
    @ApiModelProperty(value = "退回金额")
	private String thje;
	/**退回状态*/
	@Excel(name = "退回状态", width = 15)
    @ApiModelProperty(value = "退回状态")
	private String thzt;
	/**请求文件路径*/
	@Excel(name = "请求文件路径", width = 15)
    @ApiModelProperty(value = "请求文件路径")
	private String qqwjlj;
	/**请求文件名称*/
	@Excel(name = "请求文件名称", width = 15)
    @ApiModelProperty(value = "请求文件名称")
	private String qqwjmc;
	/**应答文件路径*/
	@Excel(name = "应答文件路径", width = 15)
    @ApiModelProperty(value = "应答文件路径")
	private String ydwjlj;
	/**应答文件名称*/
	@Excel(name = "应答文件名称", width = 15)
    @ApiModelProperty(value = "应答文件名称")
	private String ydwjmc;
	/**回盘文件路径*/
	@Excel(name = "回盘文件路径", width = 15)
    @ApiModelProperty(value = "回盘文件路径")
	private String hpwjlj;
	/**回盘文件名称*/
	@Excel(name = "回盘文件名称", width = 15)
    @ApiModelProperty(value = "回盘文件名称")
	private String hpwjmc;
	/**录入操作柜员*/
	@Excel(name = "录入操作柜员", width = 15)
    @ApiModelProperty(value = "录入操作柜员")
	private String lrczgy;
	/**录入操作机构*/
	@Excel(name = "录入操作机构", width = 15)
    @ApiModelProperty(value = "录入操作机构")
	private String lrczjg;
	/**录入日期*/
	@Excel(name = "录入日期", width = 15)
    @ApiModelProperty(value = "录入日期")
	private String lrrq;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15)
    @ApiModelProperty(value = "录入时间")
	private String lrsj;
	/**修改操作柜员*/
	@Excel(name = "修改操作柜员", width = 15)
    @ApiModelProperty(value = "修改操作柜员")
	private String xgczgy;
	/**修改操作机构*/
	@Excel(name = "修改操作机构", width = 15)
    @ApiModelProperty(value = "修改操作机构")
	private String xgczjg;
	/**修改日期*/
	@Excel(name = "修改日期", width = 15)
    @ApiModelProperty(value = "修改日期")
	private String xgrq;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15)
    @ApiModelProperty(value = "修改时间")
	private String xgsj;
	/**初始操作柜员*/
	@Excel(name = "初始操作柜员", width = 15)
    @ApiModelProperty(value = "初始操作柜员")
	private String csczgy;
	/**初始操作机构*/
	@Excel(name = "初始操作机构", width = 15)
    @ApiModelProperty(value = "初始操作机构")
	private String csczjg;
	/**初始日期*/
	@Excel(name = "初始日期", width = 15)
    @ApiModelProperty(value = "初始日期")
	private String csrq;
	/**初始时间*/
	@Excel(name = "初始时间", width = 15)
    @ApiModelProperty(value = "初始时间")
	private String cssj;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**预留1*/
	@Excel(name = "预留1", width = 15)
    @ApiModelProperty(value = "预留1")
	private String yl1;
	/**预留2*/
	@Excel(name = "预留2", width = 15)
    @ApiModelProperty(value = "预留2")
	private String yl2;
	/**预留3*/
	@Excel(name = "预留3", width = 15)
    @ApiModelProperty(value = "预留3")
	private String yl3;
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

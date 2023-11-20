package org.cmms.modules.sjxf.qtxt.plpt.dsdflsb.entity;

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
 * @Description: 代收代发流水表
 * @Author: jeecg-boot
 * @Date:   2021-12-17
 * @Version: V1.0
 */
@Data
@TableName("Ibus_bsp_sdsfls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_bsp_sdsfls对象", description="代收代发流水表")
public class Dsdflsb {
    
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**交易批次号*/
	@Excel(name = "交易批次号", width = 15)
    @ApiModelProperty(value = "交易批次号")
	private String jypch;
	/**单位编号*/
	@Excel(name = "单位编号", width = 15)
    @ApiModelProperty(value = "单位编号")
	private String dwbh;
	/**项目编号*/
	@Excel(name = "项目编号", width = 15)
    @ApiModelProperty(value = "项目编号")
	private String xmbh;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
    @ApiModelProperty(value = "序列号")
	private String xlh;
	/**单位账号*/
	@Excel(name = "单位账号", width = 15)
    @ApiModelProperty(value = "单位账号")
	private String dwzh;
	/**代收发标志*/
	@Excel(name = "代收发标志", width = 15)
    @ApiModelProperty(value = "代收发标志")
	private String dsfbz;
	/**证件种类*/
	@Excel(name = "证件种类", width = 15)
    @ApiModelProperty(value = "证件种类")
	private String zjlb;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjh;
	/**帐号*/
	@Excel(name = "帐号", width = 15)
    @ApiModelProperty(value = "帐号")
	private String zh;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**行别*/
	@Excel(name = "行别", width = 15)
    @ApiModelProperty(value = "行别")
	private String hb;
	/**应收金额*/
	@Excel(name = "应收金额", width = 15)
    @ApiModelProperty(value = "应收金额")
	private String ysje;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private String jyje;
	/**交易标志*/
	@Excel(name = "交易标志", width = 15)
    @ApiModelProperty(value = "交易标志")
	private String jybz;
	/**大写金额*/
	@Excel(name = "大写金额", width = 15)
    @ApiModelProperty(value = "大写金额")
	private String dxje;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**纳税编号*/
	@Excel(name = "纳税编号", width = 15)
    @ApiModelProperty(value = "纳税编号")
	private String nsbh;
	/**明细金额1*/
	@Excel(name = "明细金额1", width = 15)
    @ApiModelProperty(value = "明细金额1")
	private String mxje1;
	/**明细金额2*/
	@Excel(name = "明细金额2", width = 15)
    @ApiModelProperty(value = "明细金额2")
	private String mxje2;
	/**明细金额3*/
	@Excel(name = "明细金额3", width = 15)
    @ApiModelProperty(value = "明细金额3")
	private String mxje3;
	/**明细金额4*/
	@Excel(name = "明细金额4", width = 15)
    @ApiModelProperty(value = "明细金额4")
	private String mxje4;
	/**明细金额5*/
	@Excel(name = "明细金额5", width = 15)
    @ApiModelProperty(value = "明细金额5")
	private String mxje5;
	/**明细金额6*/
	@Excel(name = "明细金额6", width = 15)
    @ApiModelProperty(value = "明细金额6")
	private String mxje6;
	/**明细金额7*/
	@Excel(name = "明细金额7", width = 15)
    @ApiModelProperty(value = "明细金额7")
	private String mxje7;
	/**明细金额8*/
	@Excel(name = "明细金额8", width = 15)
    @ApiModelProperty(value = "明细金额8")
	private String mxje8;
	/**明细金额9*/
	@Excel(name = "明细金额9", width = 15)
    @ApiModelProperty(value = "明细金额9")
	private String mxje9;
	/**明细金额10*/
	@Excel(name = "明细金额10", width = 15)
    @ApiModelProperty(value = "明细金额10")
	private String mxje10;
	/**明细金额11*/
	@Excel(name = "明细金额11", width = 15)
    @ApiModelProperty(value = "明细金额11")
	private String mxje11;
	/**明细金额12*/
	@Excel(name = "明细金额12", width = 15)
    @ApiModelProperty(value = "明细金额12")
	private String mxje12;
	/**明细金额13*/
	@Excel(name = "明细金额13", width = 15)
    @ApiModelProperty(value = "明细金额13")
	private String mxje13;
	/**明细金额14*/
	@Excel(name = "明细金额14", width = 15)
    @ApiModelProperty(value = "明细金额14")
	private String mxje14;
	/**明细金额15*/
	@Excel(name = "明细金额15", width = 15)
    @ApiModelProperty(value = "明细金额15")
	private String mxje15;
	/**检查操作柜员*/
	@Excel(name = "检查操作柜员", width = 15)
    @ApiModelProperty(value = "检查操作柜员")
	private String jcczgy;
	/**检查操作机构*/
	@Excel(name = "检查操作机构", width = 15)
    @ApiModelProperty(value = "检查操作机构")
	private String jcczjg;
	/**检查日期*/
	@Excel(name = "检查日期", width = 15)
    @ApiModelProperty(value = "检查日期")
	private String jcrq;
	/**检查时间*/
	@Excel(name = "检查时间", width = 15)
    @ApiModelProperty(value = "检查时间")
	private String jcsj;
	/**检验UUID*/
	@Excel(name = "检验UUID", width = 15)
    @ApiModelProperty(value = "检验UUID")
	private String jyuuid;
	/**通过标志*/
	@Excel(name = "通过标志", width = 15)
    @ApiModelProperty(value = "通过标志")
	private String tgbz;
	/**错误信息*/
	@Excel(name = "错误信息", width = 15)
    @ApiModelProperty(value = "错误信息")
	private String cwxx;
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
	/**核心错误码*/
	@Excel(name = "核心错误码", width = 15)
    @ApiModelProperty(value = "核心错误码")
	private String hostcode;
	/**核心错误信息*/
	@Excel(name = "核心错误信息", width = 15)
    @ApiModelProperty(value = "核心错误信息")
	private String hostcwxx;
	/**核心流水*/
	@Excel(name = "核心流水", width = 15)
    @ApiModelProperty(value = "核心流水")
	private String hostseqno;
	/**核心交易日期*/
	@Excel(name = "核心交易日期", width = 15)
    @ApiModelProperty(value = "核心交易日期")
	private String hosttradedate;
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

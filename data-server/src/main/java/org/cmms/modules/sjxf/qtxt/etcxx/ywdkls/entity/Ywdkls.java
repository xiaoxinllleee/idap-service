package org.cmms.modules.sjxf.qtxt.etcxx.ywdkls.entity;

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
 * @Description: ETC业务代扣流水
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Ibus_etc_pkmxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibus_etc_pkmxb对象", description="ETC业务代扣流水")
public class Ywdkls {
    
	/**争议流水号*/
	@Excel(name = "争议流水号", width = 15)
    @ApiModelProperty(value = "争议流水号")
	private String disputedlistno;
	/**入口路段编码*/
	@Excel(name = "入口路段编码", width = 15)
    @ApiModelProperty(value = "入口路段编码")
	private String inroadno;
	/**入口站编码*/
	@Excel(name = "入口站编码", width = 15)
    @ApiModelProperty(value = "入口站编码")
	private String instationno;
	/**入口站名*/
	@Excel(name = "入口站名", width = 15)
    @ApiModelProperty(value = "入口站名")
	private String instationname;
	/**入口车道号*/
	@Excel(name = "入口车道号", width = 15)
    @ApiModelProperty(value = "入口车道号")
	private String inlaneno;
	/**出口路段*/
	@Excel(name = "出口路段", width = 15)
    @ApiModelProperty(value = "出口路段")
	private String roadno;
	/**站编码*/
	@Excel(name = "站编码", width = 15)
    @ApiModelProperty(value = "站编码")
	private String stationid;
	/**站名*/
	@Excel(name = "站名", width = 15)
    @ApiModelProperty(value = "站名")
	private String stationname;
	/**车道号*/
	@Excel(name = "车道号", width = 15)
    @ApiModelProperty(value = "车道号")
	private String laneno;
	/**车型*/
	@Excel(name = "车型", width = 15)
    @ApiModelProperty(value = "车型")
	private String vehtype;
	/**车种*/
	@Excel(name = "车种", width = 15)
    @ApiModelProperty(value = "车种")
	private String vehclass;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
	private String vehplate;
	/**工班号*/
	@Excel(name = "工班号", width = 15)
    @ApiModelProperty(value = "工班号")
	private String squadno;
	/**工班日期*/
	@Excel(name = "工班日期", width = 15)
    @ApiModelProperty(value = "工班日期")
	private String squaddate;
	/**收费时间*/
	@Excel(name = "收费时间", width = 15)
    @ApiModelProperty(value = "收费时间")
	private String optime;
	/**湘通卡卡号*/
	@Excel(name = "湘通卡卡号", width = 15)
    @ApiModelProperty(value = "湘通卡卡号")
	private String paycardid;
	/**湘通卡表面号*/
	@Excel(name = "湘通卡表面号", width = 15)
    @ApiModelProperty(value = "湘通卡表面号")
	private String paycardno;
	/**湘通卡类型*/
	@Excel(name = "湘通卡类型", width = 15)
    @ApiModelProperty(value = "湘通卡类型")
	private String paycardtype;
	/**OBU编码*/
	@Excel(name = "OBU编码", width = 15)
    @ApiModelProperty(value = "OBU编码")
	private String obuid;
	/**湘通卡余额*/
	@Excel(name = "湘通卡余额", width = 15)
    @ApiModelProperty(value = "湘通卡余额")
	private String paycardbalance;
	/**湘通卡折扣率*/
	@Excel(name = "湘通卡折扣率", width = 15)
    @ApiModelProperty(value = "湘通卡折扣率")
	private String paycarddiscount;
	/**优惠前金额*/
	@Excel(name = "优惠前金额", width = 15)
    @ApiModelProperty(value = "优惠前金额")
	private String pdiscounttoll;
	/**收费车金额*/
	@Excel(name = "收费车金额", width = 15)
    @ApiModelProperty(value = "收费车金额")
	private String cashmoney;
	/**免费车金额*/
	@Excel(name = "免费车金额", width = 15)
    @ApiModelProperty(value = "免费车金额")
	private String freemoney;
	/**公务车金额*/
	@Excel(name = "公务车金额", width = 15)
    @ApiModelProperty(value = "公务车金额")
	private String officemoney;
	/**未付金额*/
	@Excel(name = "未付金额", width = 15)
    @ApiModelProperty(value = "未付金额")
	private String unpaymoney;
	/**湘通卡金额*/
	@Excel(name = "湘通卡金额", width = 15)
    @ApiModelProperty(value = "湘通卡金额")
	private String etcmoney;
	/**Tac码*/
	@Excel(name = "Tac码", width = 15)
    @ApiModelProperty(value = "Tac码")
	private String etctac;
	/**终端交易序列号*/
	@Excel(name = "终端交易序列号", width = 15)
    @ApiModelProperty(value = "终端交易序列号")
	private String etctermtradno;
	/**终端机编号*/
	@Excel(name = "终端机编号", width = 15)
    @ApiModelProperty(value = "终端机编号")
	private String etctermcode;
	/**卡区域号*/
	@Excel(name = "卡区域号", width = 15)
    @ApiModelProperty(value = "卡区域号")
	private String cardnetwork;
	/**交易类型*/
	@Excel(name = "交易类型", width = 15)
    @ApiModelProperty(value = "交易类型")
	private String tradtype;
	/**确认为争议流水时间*/
	@Excel(name = "确认为争议流水时间", width = 15)
    @ApiModelProperty(value = "确认为争议流水时间")
	private String disputedtime;
	/**争议原因*/
	@Excel(name = "争议原因", width = 15)
    @ApiModelProperty(value = "争议原因")
	private String disputedreason;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String remark;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15)
    @ApiModelProperty(value = "更新时间")
	private String upddate;
	/**入口时间*/
	@Excel(name = "入口时间", width = 15)
    @ApiModelProperty(value = "入口时间")
	private String inoptime;
	/**批次号*/
	@Excel(name = "批次号", width = 15)
    @ApiModelProperty(value = "批次号")
	private String pch;
	/**上核心流水号*/
	@Excel(name = "上核心流水号", width = 15)
    @ApiModelProperty(value = "上核心流水号")
	private String hostserialno;
	/**处理日期*/
	@Excel(name = "处理日期", width = 15)
    @ApiModelProperty(value = "处理日期")
	private String workdate;
	/**处理时间*/
	@Excel(name = "处理时间", width = 15)
    @ApiModelProperty(value = "处理时间")
	private String worktime;
	/**账户类型:*/
	@Excel(name = "账户类型:", width = 15)
    @ApiModelProperty(value = "账户类型:")
	private String zhlx;
	/**扣款账号*/
	@Excel(name = "扣款账号", width = 15)
    @ApiModelProperty(value = "扣款账号")
	private String kkzh;
	/**收款账号*/
	@Excel(name = "收款账号", width = 15)
    @ApiModelProperty(value = "收款账号")
	private String skzh;
	/**是否争议*/
	@Excel(name = "是否争议", width = 15)
    @ApiModelProperty(value = "是否争议")
	private String isdisputed;
	/**户名*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**交易状态*/
	@Excel(name = "交易状态", width = 15)
    @ApiModelProperty(value = "交易状态")
	private String tradestatus;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String khjg;
	/**贷记卡银行代号*/
	@Excel(name = "贷记卡银行代号", width = 15)
    @ApiModelProperty(value = "贷记卡银行代号")
	private String djkyhdh;
	/**贷记卡分行代号*/
	@Excel(name = "贷记卡分行代号", width = 15)
    @ApiModelProperty(value = "贷记卡分行代号")
	private String djkfhdh;
	/**流水类型:*/
	@Excel(name = "流水类型:", width = 15)
    @ApiModelProperty(value = "流水类型:")
	private String eexittype;
	/**(未绑定)回收日期*/
	@Excel(name = "(未绑定)回收日期", width = 15)
    @ApiModelProperty(value = "(未绑定)回收日期")
	private String hsrq;
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

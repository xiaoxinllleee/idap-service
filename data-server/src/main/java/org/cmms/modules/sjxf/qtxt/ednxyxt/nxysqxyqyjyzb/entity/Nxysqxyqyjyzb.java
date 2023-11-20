package org.cmms.modules.sjxf.qtxt.ednxyxt.nxysqxyqyjyzb.entity;

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
 * @Description: 农信银授权协议签约交易主表
 * @Author: jeecg-boot
 * @Date:   2021-12-15
 * @Version: V1.0
 */
@Data
@TableName("Tgacs_nps_dwyktqyxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tgacs_nps_dwyktqyxx对象", description="农信银授权协议签约交易主表")
public class Nxysqxyqyjyzb {
    
	/**协议号*/
	@Excel(name = "协议号", width = 15)
    @ApiModelProperty(value = "协议号")
	private String qyxyh;
	/**签约类型*/
	@Excel(name = "签约类型", width = 15)
    @ApiModelProperty(value = "签约类型")
	private Integer qylx;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**移动电话*/
	@Excel(name = "移动电话", width = 15)
    @ApiModelProperty(value = "移动电话")
	private String yddh;
	/**支付宝账户*/
	@Excel(name = "支付宝账户", width = 15)
    @ApiModelProperty(value = "支付宝账户")
	private String zfbzh;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**撤约渠道*/
	@Excel(name = "撤约渠道", width = 15)
    @ApiModelProperty(value = "撤约渠道")
	private String qyqd;
	/**修改渠道*/
	@Excel(name = "修改渠道", width = 15)
    @ApiModelProperty(value = "修改渠道")
	private String cyqd;
	/**运营商*/
	@Excel(name = "运营商", width = 15)
    @ApiModelProperty(value = "运营商")
	private String xgqd;
	/**单笔限额*/
	@Excel(name = "单笔限额", width = 15)
    @ApiModelProperty(value = "单笔限额")
	private String yys;
	/**单日限额*/
	@Excel(name = "单日限额", width = 15)
    @ApiModelProperty(value = "单日限额")
	private java.math.BigDecimal dbxe;
	/**签约状态*/
	@Excel(name = "签约状态", width = 15)
    @ApiModelProperty(value = "签约状态")
	private java.math.BigDecimal drxe;
	/**签约时间*/
	@Excel(name = "签约时间", width = 15)
    @ApiModelProperty(value = "签约时间")
	private String qyzt;
	/**签约渠道*/
	@Excel(name = "签约渠道", width = 15)
    @ApiModelProperty(value = "签约渠道")
	private String qysj;
	/**撤约时间*/
	@Excel(name = "撤约时间", width = 15)
    @ApiModelProperty(value = "撤约时间")
	private String cysj;
	/**最近修改时间*/
	@Excel(name = "最近修改时间", width = 15)
    @ApiModelProperty(value = "最近修改时间")
	private String zjxgsj;
	/**备用1*/
	@Excel(name = "备用1", width = 15)
    @ApiModelProperty(value = "备用1")
	private String byzd1;
	/**备用2*/
	@Excel(name = "备用2", width = 15)
    @ApiModelProperty(value = "备用2")
	private String byzd2;
	/**备用3*/
	@Excel(name = "备用3", width = 15)
    @ApiModelProperty(value = "备用3")
	private java.math.BigDecimal byzd3;
	/**数据开始日期*/
	@Excel(name = "数据开始日期", width = 15)
    @ApiModelProperty(value = "数据开始日期")
	private String sDate;
	/**数据结束日期*/
	@Excel(name = "数据结束日期", width = 15)
    @ApiModelProperty(value = "数据结束日期")
	private String eDate;
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

package org.cmms.modules.sjxf.qtxt.wsyhxt.zfbktqyb.entity;

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
 * @Description: 支付宝卡通签约表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_cat_card_sign")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_cat_card_sign对象", description="支付宝卡通签约表")
public class Zfbktqyb {
    
	/**签约流水号*/
	@Excel(name = "签约流水号", width = 15)
    @ApiModelProperty(value = "签约流水号")
	private String ccsFlowno;
	/**卡通协议号*/
	@Excel(name = "卡通协议号", width = 15)
    @ApiModelProperty(value = "卡通协议号")
	private String ccsSignno;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String ccsCerttype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String ccsCertno;
	/**支付宝账号*/
	@Excel(name = "支付宝账号", width = 15)
    @ApiModelProperty(value = "支付宝账号")
	private String ccsCtaccno;
	/**个人网银客户号*/
	@Excel(name = "个人网银客户号", width = 15)
    @ApiModelProperty(value = "个人网银客户号")
	private String ccsCstno;
	/**网银账号*/
	@Excel(name = "网银账号", width = 15)
    @ApiModelProperty(value = "网银账号")
	private String ccsAccno;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	private String ccsPhoneno;
	/**0:签约1:注销*/
	@Excel(name = "0:签约1:注销", width = 15)
    @ApiModelProperty(value = "0:签约1:注销")
	private String ccsType;
	/**0:成功 1:失败 4:处理中*/
	@Excel(name = "0:成功 1:失败 4:处理中", width = 15)
    @ApiModelProperty(value = "0:成功 1:失败 4:处理中")
	private String ccsState;
	/**签约渠道*/
	@Excel(name = "签约渠道", width = 15)
    @ApiModelProperty(value = "签约渠道")
	private String ccsStartchannel;
	/**解约渠道*/
	@Excel(name = "解约渠道", width = 15)
    @ApiModelProperty(value = "解约渠道")
	private String ccsEndcheannel;
	/**签约柜员号*/
	@Excel(name = "签约柜员号", width = 15)
    @ApiModelProperty(value = "签约柜员号")
	private String ccsOpenteller;
	/**签约授权柜员号*/
	@Excel(name = "签约授权柜员号", width = 15)
    @ApiModelProperty(value = "签约授权柜员号")
	private String ccsOpenauthteller;
	/**签约网点机构号*/
	@Excel(name = "签约网点机构号", width = 15)
    @ApiModelProperty(value = "签约网点机构号")
	private String ccsOpennode;
	/**签约时间*/
	@Excel(name = "签约时间", width = 15)
    @ApiModelProperty(value = "签约时间")
	private String ccsOpendate;
	/**解约柜员号*/
	@Excel(name = "解约柜员号", width = 15)
    @ApiModelProperty(value = "解约柜员号")
	private String ccsCancelteller;
	/**解约授权柜员号*/
	@Excel(name = "解约授权柜员号", width = 15)
    @ApiModelProperty(value = "解约授权柜员号")
	private String ccsCancelauthteller;
	/**解约网点机构号*/
	@Excel(name = "解约网点机构号", width = 15)
    @ApiModelProperty(value = "解约网点机构号")
	private String ccsCancelnode;
	/**解约时间*/
	@Excel(name = "解约时间", width = 15)
    @ApiModelProperty(value = "解约时间")
	private String ccsEnddate;
	/**监控状态*/
	@Excel(name = "监控状态", width = 15)
    @ApiModelProperty(value = "监控状态")
	private String ccsBankStat;
	/**单笔限额*/
	@Excel(name = "单笔限额", width = 15)
    @ApiModelProperty(value = "单笔限额")
	private java.math.BigDecimal ccsSinglemax;
	/**日累计限额*/
	@Excel(name = "日累计限额", width = 15)
    @ApiModelProperty(value = "日累计限额")
	private java.math.BigDecimal ccsDaymax;
	/**限额修改时间*/
	@Excel(name = "限额修改时间", width = 15)
    @ApiModelProperty(value = "限额修改时间")
	private String ccsLimitupdatedate;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String ccsName;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15)
    @ApiModelProperty(value = "修改时间")
	private String ccsOptime;
	/**经理号*/
	@Excel(name = "经理号", width = 15)
    @ApiModelProperty(value = "经理号")
	private String ccsManageno;
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

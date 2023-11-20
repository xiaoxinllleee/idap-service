package org.cmms.modules.sjxf.qtxt.wsyhxt.grkhxx.entity;

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
 * @Description: 个人开户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_pb_open")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_pb_open对象", description="个人开户信息")
public class Grkhxx {
    
	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String cifCstno;
	/**客户名称(中文)*/
	@Excel(name = "客户名称(中文)", width = 15)
    @ApiModelProperty(value = "客户名称(中文)")
	private String cifNamecn;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String cifCtftype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String cifCtfno;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String cifMobile;
	/**安全认证方式0000000000*/
	@Excel(name = "安全认证方式0000000000", width = 15)
    @ApiModelProperty(value = "安全认证方式0000000000")
	private String cifSecurity;
	/**Ukey编号*/
	@Excel(name = "Ukey编号", width = 15)
    @ApiModelProperty(value = "Ukey编号")
	private String cifEquipno;
	/**客户经理编号*/
	@Excel(name = "客户经理编号", width = 15)
    @ApiModelProperty(value = "客户经理编号")
	private String cifManamcode;
	/**客户经理姓名*/
	@Excel(name = "客户经理姓名", width = 15)
    @ApiModelProperty(value = "客户经理姓名")
	private String cifManamname;
	/**开户柜员号*/
	@Excel(name = "开户柜员号", width = 15)
    @ApiModelProperty(value = "开户柜员号")
	private String cifOpenteller;
	/**签约渠道0:网上签约1:柜面签约*/
	@Excel(name = "签约渠道0:网上签约1:柜面签约", width = 15)
    @ApiModelProperty(value = "签约渠道0:网上签约1:柜面签约")
	private String cifChannel;
	/**开户时间yyyymmddhhmiss*/
	@Excel(name = "开户时间yyyymmddhhmiss", width = 15)
    @ApiModelProperty(value = "开户时间yyyymmddhhmiss")
	private String cifOpentime;
	/**开户网点机构号*/
	@Excel(name = "开户网点机构号", width = 15)
    @ApiModelProperty(value = "开户网点机构号")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String cifOpennode;
	/**状态*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String cifStt;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String cifHostno;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String cifModifytime;
	/**销户时间格式yyyymmddhhmiss*/
	@Excel(name = "销户时间格式yyyymmddhhmiss", width = 15)
    @ApiModelProperty(value = "销户时间格式yyyymmddhhmiss")
	private String cifCanceltime;
	/**销户网点机构号*/
	@Excel(name = "销户网点机构号", width = 15)
    @ApiModelProperty(value = "销户网点机构号")
	private String cifCancelnode;
	/**销户柜员号*/
	@Excel(name = "销户柜员号", width = 15)
    @ApiModelProperty(value = "销户柜员号")
	private String cifCancelteller;
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

package org.cmms.modules.sjxf.qtxt.wsyhxt.qykhkhxx.entity;

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
 * @Description: 企业客户开户信息
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Ibss_cb_open")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ibss_cb_open对象", description="企业客户开户信息")
public class Qykhkhxx {

	/**网银客户号*/
	@Excel(name = "网银客户号", width = 15)
    @ApiModelProperty(value = "网银客户号")
	private String cifCstno;
	/**客户名称(中文)*/
	@Excel(name = "客户名称(中文)", width = 15)
    @ApiModelProperty(value = "客户名称(中文)")
	private String cifNamecn;
	/**证件类型(11:全国组织机构代码12:营业执照号码)*/
	@Excel(name = "证件类型(11:全国组织机构代码12:营业执照号码)", width = 15)
    @ApiModelProperty(value = "证件类型(11:全国组织机构代码12:营业执照号码)")
	private String cifCtftyp;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String cifCtfno;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private String cifOpentime;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String cifOpennode;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String cifPhone;
	/**状态(0:正常;1:未激活;2:冻结3:销户)*/
	@Excel(name = "状态(0:正常;1:未激活;2:冻结3:销户)", width = 15)
    @ApiModelProperty(value = "状态(0:正常;1:未激活;2:冻结3:销户)")
	private String cifStt;
	/**营销客户经理编号*/
	@Excel(name = "营销客户经理编号", width = 15)
    @ApiModelProperty(value = "营销客户经理编号")
	private String cifMcode;
	/**营销客户经理姓名*/
	@Excel(name = "营销客户经理姓名", width = 15)
    @ApiModelProperty(value = "营销客户经理姓名")
	private String cifMname;
	/**核心客户号*/
	@Excel(name = "核心客户号", width = 15)
    @ApiModelProperty(value = "核心客户号")
	private String cifHostno;
	/**开户操作员*/
	@Excel(name = "开户操作员", width = 15)
    @ApiModelProperty(value = "开户操作员")
	private String cifOpenteller;
	/**最后更新时间*/
	@Excel(name = "最后更新时间", width = 15)
    @ApiModelProperty(value = "最后更新时间")
	private String cifModifytime;
	/**注销机构*/
	@Excel(name = "注销机构", width = 15)
    @ApiModelProperty(value = "注销机构")
	private String cifCancelnode;
	/**注销操作员*/
	@Excel(name = "注销操作员", width = 15)
    @ApiModelProperty(value = "注销操作员")
	private String cifCancelteller;
	/**注销时间*/
	@Excel(name = "注销时间", width = 15)
    @ApiModelProperty(value = "注销时间")
	private String cifCanceltime;
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
	/**dttime*//*
	/*@Excel(name = "dttime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "dttime")
	private Date dttime;*/
}

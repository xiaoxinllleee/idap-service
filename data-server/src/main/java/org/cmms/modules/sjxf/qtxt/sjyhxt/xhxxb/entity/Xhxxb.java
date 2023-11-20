package org.cmms.modules.sjxf.qtxt.sjyhxt.xhxxb.entity;

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
 * @Description: 销户信息表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("Mbs_mb_cstinf_his")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Mbs_mb_cstinf_his对象", description="销户信息表")
public class Xhxxb {
    
	/**客户号用户唯一标识*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String cstno;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String name;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String ctftype;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String ctfno;
	/**手机号码*/
	@Excel(name = "手机号码", width = 15)
    @ApiModelProperty(value = "手机号码")
	private String mobile;
	/**状态(0:正常 1:暂停)*/
	@Excel(name = "状态", width = 15)
    @ApiModelProperty(value = "状态")
	private String stt;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String opennode;
	/**开户柜员*/
	@Excel(name = "开户柜员", width = 15)
    @ApiModelProperty(value = "开户柜员")
	private String openteller;
	/**开户时间*/
	@Excel(name = "开户时间", width = 15)
    @ApiModelProperty(value = "开户时间")
	private String opentime;
	/**注销日期*/
	@Excel(name = "注销日期", width = 15)
    @ApiModelProperty(value = "注销日期")
	private String clsdate;
	/**注销网点*/
	@Excel(name = "注销网点", width = 15)
    @ApiModelProperty(value = "注销网点")
	private String clsbrchid;
	/**注销柜员*/
	@Excel(name = "注销柜员", width = 15)
    @ApiModelProperty(value = "注销柜员")
	private String clsteller;
	/**注销原因*/
	@Excel(name = "注销原因", width = 15)
    @ApiModelProperty(value = "注销原因")
	private String clsrsn;
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

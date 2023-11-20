package org.cmms.modules.dklldj.jbxxgl.khxxgl.entity;

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
 * @Description: 1
 * @Author: jeecg-boot
 * @Date:   2020-03-04
 * @Version: V1.0
 */
@Data
@TableName("rate_khjbxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_khjbxxb对象", description="客户基本信息管理")
public class Rate_khjbxxb {

	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String zzbz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@TableId(type = IdType.NONE)
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型(1 个人 2 企业)*/
	@Excel(name = "客户类型", width = 15,dicCode = "lldj_khlx")
	@Dict(dicCode = "lldj_khlx")
    @ApiModelProperty(value = "客户类型")
	private Integer khlx;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**删除标志*/
    @ApiModelProperty(value = "删除标志")
	@Dict(dicCode = "del_flag")
	private Integer scbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入操作员*/
    @ApiModelProperty(value = "录入操作员")
	private String lrczy;
	/**录入标志（0、通过存储过程自动写入；1、操作员通过界面添加；2、操作员通过界面修改）*/
    @ApiModelProperty(value = "录入标志（0.通过存储过程自动写入/1.操作员通过界面添加/2.操作员通过界面修改）")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**创建时间*//*
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;*/
}

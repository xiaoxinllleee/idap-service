package org.cmms.modules.dklldj.jbxxgl.khzyjl.entity;

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
 * @Description: 客户转移记录
 * @Author: jeecg-boot
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Data
@TableName("rate_khjbxxb_ls")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="rate_khjbxxb_ls对象", description="客户转移记录")
public class RateKhjbxxbLs {

	@TableId(type = IdType.ASSIGN_ID)
	@ApiModelProperty(value = "id")
	private String id;
	/**原组织标识*/
	@Excel(name = "原所属组织", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "原组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String yzzbz;
	/**现组织标识*/
	@Excel(name = "现所属组织", width = 15, dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "现组织标识")
	@Dict(dicCode = "zzbz", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String xzzbz;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型（1、个人；2、企业）*/
	@Excel(name = "客户类型）", width = 15, dicCode = "lldj_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "lldj_khlx")
	private Integer khlx;
	/**法人代表*/
	@Excel(name = "法人代表", width = 15)
    @ApiModelProperty(value = "法人代表")
	private String frdb;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
	private Date xgsj;
	/**修改操作员*/
	@Excel(name = "修改操作员", width = 15)
    @ApiModelProperty(value = "修改操作员")
	private String xgczy;
}

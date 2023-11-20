package org.cmms.modules.khgl.clkhxxgl.entity;

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
 * @Description: 存量客户信息管理
 * @Author: cmms
 * @Date:   2019-09-19
 * @Version: V1.0
 */
@Data
@TableName("v_khgl_khjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_khgl_khjbxx对象", description="存量客户信息管理")
public class Clkhxxgl {

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**所属机构*/
	@Excel(name = "所属机构", width = 15, dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
    @ApiModelProperty(value = "所属机构")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private java.lang.String zzbz;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private java.lang.String jgdm;
	/**客户编号*/
	@Excel(name = "客户编号", width = 15)
    @ApiModelProperty(value = "客户编号")
	private java.lang.String khbh;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15, dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
    @ApiModelProperty(value = "所属营销单元")
	@Dict(dicCode="EJDYBH", dictTable="V_KHHMC_SSYXDY", dicText="XZMC || CMC")
	private java.lang.String ssyxdy;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型*/
	//@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
    //@Dict(dicCode = "dkjkpt_zjlx")
	private java.lang.String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**客户性质*/
	@Excel(name = "客户性质", width = 15, dicCode = "khxz")
    @ApiModelProperty(value = "客户性质")
	@Dict(dicCode = "khxz")
	private java.lang.String khxz;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private java.lang.String dz;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private java.lang.String lxfs;
	/**客户重要程度*/
	@Excel(name = "客户重要程度", width = 15, dicCode = "khzycd")
    @ApiModelProperty(value = "客户重要程度")
	@Dict(dicCode = "khzycd")
	private java.lang.String khzycd;
	/**客户潜在业务*/
	@Excel(name = "客户潜在业务", width = 15)
    @ApiModelProperty(value = "客户潜在业务")
	private java.lang.String khqzyw;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
	private java.lang.String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private java.util.Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private java.lang.String lrr;
}

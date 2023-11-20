package org.cmms.modules.khxxgl.khjbzl.entity;

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
 * @Description: 客户画像
 * @Author: jeecg-boot
 * @Date:   2021-11-29
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHJBZL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHJBZL对象", description="客户画像")
public class Khjbzl {
    

	/**ID */
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID ")
	private java.lang.String id;
	/**归属机构*/
	@Excel(name = "归属机构", width = 15)
    @ApiModelProperty(value = "归属机构")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String jgdm;

	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private java.lang.String sszh;

	/**归属网格*/
	@Excel(name = "归属网格", width = 15)
    @ApiModelProperty(value = "归属网格")
	@Dict(dicCode="ID",dictTable="V_YXDYGL_MAIN",dicText="WGMC_SHOW")
	private java.lang.String wgbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private java.lang.String khmc;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	@Dict(dicCode = "hxzjlx")
	private java.lang.String zjlx;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**联系方式*/
	@Excel(name = "联系方式", width = 15)
    @ApiModelProperty(value = "联系方式")
	private java.lang.String lxfs;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private java.lang.String dz;
	/**客户性质*/
	@Excel(name = "客户性质", width = 15)
    @ApiModelProperty(value = "客户性质")
	@Dict(dicCode = "khlx")
	private java.lang.String khxz;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "clkhlx")
	private java.lang.String khlx;
	/**客户等级*/
	@Excel(name = "客户等级", width = 15)
    @ApiModelProperty(value = "客户等级")
	private java.lang.String khdj;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private java.lang.String dabh;

	/**客户类别*/
	@Excel(name = "客户类别", width = 15)
	@ApiModelProperty(value = "客户类别")
	@Dict(dicCode = "khlb")
	private java.lang.String khlb;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
	private java.util.Date updateTime;
}

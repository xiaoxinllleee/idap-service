package org.cmms.modules.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 组织机构管理
 * @Author: cmms
 * @Date:   2019-09-18
 * @Version: V1.0
 */
@Data
@TableName("HR_BAS_ORGANIZATION")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HR_BAS_ORGANIZATION对象", description="组织机构管理")
public class HrBasOrganization {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	@TableId(type = IdType.ASSIGN_ID)
	@ExcelVerify(notNull = true, interHandler = true)
	private String zzbz;
	/**组织名称*/
	@Excel(name = "组织名称", width = 15)
    @ApiModelProperty(value = "组织名称")
	@ExcelVerify(notNull = true)
	private String zzmc;
	/**组织简称*/
	@Excel(name = "组织简称", width = 15)
	@ApiModelProperty(value = "组织简称")
	@ExcelVerify(notNull = true)
	private String zzjc;
	/**上级组织标识*/
	@Excel(name = "上级组织简称", width = 15, dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "上级组织标识")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String sjzzbz;
	/**排序序号*/
	@Excel(name = "排序序号", width = 15)
	@ApiModelProperty(value = "排序序号")
	private java.math.BigDecimal pxxh;
	/**启用标识*/
	@Excel(name = "启用标识", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "启用标识")
	@Dict(dicCode = "sfbz")
	private String qybz;
	/**组织类别*/
	@Excel(name = "组织类别", width = 15, dicCode = "zzlb")
    @ApiModelProperty(value = "组织类别")
	@Dict(dicCode = "zzlb")
	private String zzlb;
	/**组织级别*/
	@Excel(name = "组织级别", width = 15, dicCode = "zzjb")
    @ApiModelProperty(value = "组织级别")
	@Dict(dicCode = "zzjb")
	private String zzjb;
	/**业务机构代码*/
	@Excel(name = "业务机构代码", width = 15)
    @ApiModelProperty(value = "业务机构代码")
	private String ywjgdm;
	/**报表权限机构代码*/
	@Excel(name = "报表权限机构代码", width = 15)
    @ApiModelProperty(value = "报表权限机构代码")
	private String bbqxjgdm;
	/**业务机构标识*/
	@Excel(name = "业务机构标识", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "业务机构标识")
	@Dict(dicCode = "sfbz")
	private String ywjgbz;
	/**机构经营类型（1：经营型 2：服务型 3：效益型）*/
	@Excel(name = "机构经营类型", width = 15, dicCode = "jgjylx")
	@ApiModelProperty(value = "机构经营类型")
	@Dict(dicCode = "jgjylx")
	private String jgjylx;
	/**业务机构类型*/
	@Excel(name = "业务机构类型", width = 15, dicCode = "wdlx")
    @ApiModelProperty(value = "业务机构类型")
	@Dict(dicCode = "wdlx")
	private String ywjglx;
	/**业务机构性质*/
	@Excel(name = "业务机构性质", width = 15, dicCode = "wdxz")
    @ApiModelProperty(value = "业务机构性质")
	@Dict(dicCode = "wdxz")
	private String ywjgxz;
	/**所在区域*/
	@Excel(name = "所在区域", width = 15, dicCode = "szqy")
    @ApiModelProperty(value = "所在区域")
	@Dict(dicCode = "szqy")
	private String szqy;
	@TableField(exist=false)
	private List<HrBasOrganization> children;
	@TableField(exist = false)
	private String level;
}

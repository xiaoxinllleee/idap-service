package org.cmms.modules.xddagl.dkdagl.dkdazlgl.entity;

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
 * @Description: 贷款档案资料管理
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("V_xddagl_xddazlgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_xddagl_xddazlgl对象", description="贷款档案资料管理")
public class Dkdazlgl {
    
	/**wjid*/
	@Excel(name = "文件ID", width = 15)
    @ApiModelProperty(value = "文件ID")
	private Long wjid;
	/**hth*/
	@Excel(name = "贷款合同", width = 15)
    @ApiModelProperty(value = "贷款合同")
	private String hth;
	/**dkzl*/
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	@Dict(dicCode = "dkzl")
	private String dkzl;
	/**fjlx*/
	@Excel(name = "档案类型", width = 15)
    @ApiModelProperty(value = "档案类型")
	@Dict(dicCode = "dict_value",dictTable = "XDDAGL_DAMLGL",dicText = "dict_key",ds = "eweb")
	private Integer fjlx;
	/**wjlj*/
	@Excel(name = "文件路径", width = 15)
    @ApiModelProperty(value = "文件路径")
	private String wjlj;
	/**fwlj*/
	@Excel(name = "fwlj", width = 15)
    @ApiModelProperty(value = "fwlj")
	private String fwlj;
	/**wjdx*/
	@Excel(name = "wjdx", width = 15)
    @ApiModelProperty(value = "wjdx")
	private java.math.BigDecimal wjdx;
	/**lrsj*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**lrbz*/
	@Excel(name = "录入标识", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**lrr*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**fjlx1*/
	@Excel(name = "fjlx1", width = 15)
    @ApiModelProperty(value = "fjlx1")
	private Integer fjlx1;
	/**fwlj1*/
	@Excel(name = "fwlj1", width = 15)
    @ApiModelProperty(value = "fwlj1")
	private String fwlj1;
	/**wjlj1*/
	@Excel(name = "wjlj1", width = 15)
    @ApiModelProperty(value = "wjlj1")
	private String wjlj1;
	/**jgdm*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**khmc*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**khlx*/
	@Excel(name = "客户类型", width = 15,dicCode = "xddagl_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xddagl_khlx")
	private String khlx;
	/**ywbh*/
	@Excel(name = "ywbh", width = 15)
    @ApiModelProperty(value = "ywbh")
	private String ywbh;
	/**lxdh*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**lxdz*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**dkpz*/
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	@Dict(dicCode = "dkzl")
	private String dkpz;
	/**dkzrr*/
	@Excel(name = "dkzrr", width = 15)
    @ApiModelProperty(value = "dkzrr")
	private String dkzrr;
}

package org.cmms.modules.xddaglxt.dkdagl.xddazlgl.entity;

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
 * @Description: 信贷档案资料管理
 * @Author: jeecg-boot
 * @Date:   2021-11-30
 * @Version: V1.0
 */
@Data
@TableName("V_XDDAGL_DKHTFJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_XDDAGL_DKHTFJXX对象", description="信贷档案资料管理")
public class Xddazlgl {

	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xdda_khlx")
	private String khlx;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
	@ApiModelProperty(value = "客户姓名")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款合同*/
	@Excel(name = "贷款合同", width = 15)
	@ApiModelProperty(value = "贷款合同")
	private String hth;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15)
	@ApiModelProperty(value = "贷款品种")
	@Dict(dicCode = "dkzl")
	private String dkpz;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
	@ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
	@ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**档案类型*/
	@Excel(name = "档案类型", width = 15)
	@ApiModelProperty(value = "档案类型")
	private Integer fjlx;
	/**文件路径*/
	@Excel(name = "文件路径", width = 15)
	@ApiModelProperty(value = "文件路径")
	private String wjlj;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
	@ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
	@ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "录入时间")
	private Date lrsj;



	/**wjid*/
    @ApiModelProperty(value = "wjid")
	private Long wjid;
	/**fwlj*/
    @ApiModelProperty(value = "fwlj")
	private String fwlj;
	/**wjdx*/
    @ApiModelProperty(value = "wjdx")
	private java.math.BigDecimal wjdx;
	/**fjlx1*/
    @ApiModelProperty(value = "fjlx1")
	private Integer fjlx1;
	/**fwlj1*/
    @ApiModelProperty(value = "fwlj1")
	private String fwlj1;
	/**wjlj1*/
    @ApiModelProperty(value = "wjlj1")
	private String wjlj1;
	/**ywbh*/
    @ApiModelProperty(value = "ywbh")
	private String ywbh;
	/**贷款品种*/
	@ApiModelProperty(value = "贷款品种")
	private String dkzl;
	/**贷款责任人*/
    @ApiModelProperty(value = "贷款责任人")
	private String dkzrr;
}

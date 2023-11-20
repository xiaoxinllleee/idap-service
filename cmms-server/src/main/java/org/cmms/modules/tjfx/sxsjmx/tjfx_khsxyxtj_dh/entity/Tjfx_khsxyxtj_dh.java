package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_dh.entity;

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
 * @Date:   2020-03-19
 * @Version: V1.0
 */
@Data
@TableName("tjfx_khsxyxtj_dh")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_khsxyxtj_dh对象", description="1")
public class Tjfx_khsxyxtj_dh {

	/**组织标识*/
	@Excel(name = "组织标识", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "组织标识")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**户主名称*/
	@Excel(name = "户主名称", width = 15)
	@ApiModelProperty(value = "户主名称")
	private String hzmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
	/**授信额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
	/**用信额度*/
	@Excel(name = "用信额度", width = 15)
    @ApiModelProperty(value = "用信额度")
	private java.math.BigDecimal yxed;
	/**预授信额度*/
	@Excel(name = "预授信额度", width = 15)
    @ApiModelProperty(value = "预授信额度")
	private java.math.BigDecimal ysxed;
	/**行政村*/
	@Excel(name = "行政村", width = 15,dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	private String xzc;
	/**组*/
    @ApiModelProperty(value = "组")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_SJYXDYGL", dicText = "DYMC")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15)
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**合同可用金额*/
	@Excel(name = "合同可用金额", width = 15)
	@ApiModelProperty(value = "授信户数")
	private java.math.BigDecimal  htkyje;
	/**授信户数*/
	@Excel(name = "授信户数", width = 15)
	@ApiModelProperty(value = "授信户数")
	private java.lang.Long  sxhs;
	/**授信工作评议人数*/
	@Excel(name = "授信工作评议人数", width = 15)
	@ApiModelProperty(value = "授信工作评议人数")
	private java.lang.Long sxgzpyrs;
	/**授信工作评议人数*/
	@Excel(name = "评议人数", width = 15)
	@ApiModelProperty(value = "评议人数")
	private java.lang.Long pyrs;
	/**用信户数*/
	@Excel(name = "用信户数", width = 15)
	@ApiModelProperty(value = "用信户数")
	private java.lang.Long yxhs;
}

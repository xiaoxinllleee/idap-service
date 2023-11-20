package org.cmms.modules.tjfx.sxsjmx.tjfx_khsxyxtj_gr.entity;

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
@TableName("tjfx_khsxyxtj_gr")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_khsxyxtj_gr对象", description="1")
public class Tjfx_khsxyxtj_gr {


	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**评级日期*/
	@Excel(name = "评级日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评级日期")
	private Date pjrq;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "khlx")
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
	/**行政村*/
	@Excel(name = "行政村", width = 15,dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
    @ApiModelProperty(value = "行政村")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_EJYXDYGL", dicText = "DYMC")
	private String xzc;
	/**预授信额度*/
	@Excel(name = "预授信额度", width = 15)
    @ApiModelProperty(value = "预授信额度")
	private java.math.BigDecimal ysxed;
	/**组*/
    @ApiModelProperty(value = "组")
	@Dict(dicCode = "DYBH",dictTable = "YXDYGL_SJYXDYGL", dicText = "DYMC")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15,dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "责任人")
	@Dict(dicCode="yggh",dictTable = "hr_bas_staff", dicText = "ygxm")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15)
    @ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	/**合同可用金额*/
	@Excel(name = "合同可用金额", width = 15)
	@ApiModelProperty(value = "授信户数")
	private java.math.BigDecimal  htkyje;
	/**预授信户数*/
	@Excel(name = "预授信户数", width = 15)
	@ApiModelProperty(value = "预授信户数")
	private java.lang.Long  ysxhs;
	/**评议附件数量*/
	@Excel(name = "评议附件数量", width = 15)
	@ApiModelProperty(value = "评议附件数量")
	private java.lang.Long pyfjsl;
	/**评议记录*/
	@Excel(name = "评议记录", width = 15)
	@ApiModelProperty(value = "评议记录")
	private java.lang.Long pyjl;
}

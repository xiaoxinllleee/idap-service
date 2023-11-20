package org.cmms.modules.tjfx.pjsxwcqkmxb.grwcqkmxb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 个人明细
 * @Author: jeecg-boot
 * @Date:   2020-04-21
 * @Version: V1.0
 */
@Data
@TableName("TJFX_PJSXWCQK_GRMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_PJSXWCQK_GRMX对象", description="个人明细")
public class PjsxwcqkGr {
    
	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
	@ApiModelProperty(value = "行政村")
	private String xzc;
	/**行政组*/
	@Excel(name = "行政组", width = 15, dicCode = "QYBM",dictTable = "V_CZXXGL_ORGANIZE", dicText = "ORGANIZE")
	@ApiModelProperty(value = "行政组")
	@Dict(dicCode = "QYBM",dictTable = "V_CZXXGL_ORGANIZE", dicText = "ORGANIZE")
	private String xzz;
	/**责任人*/
	@Excel(name = "责任人", width = 15, dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	@ApiModelProperty(value = "责任人")
	@Dict(dicCode = "YGGH",dictTable = "HR_BAS_STAFF", dicText = "YGXM")
	private String zkhjl;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**是否户主*/
	@Excel(name = "是否户主", width = 15, dicCode = "sfbz")
	@ApiModelProperty(value = "是否户主")
	@Dict(dicCode = "sfbz")
	private String sfhz;
	@Excel(name = "是否已走访", width = 15, dicCode = "sfbz_ex")
	@ApiModelProperty(value = "是否已走访")
	@Dict(dicCode = "sfbz_ex")
	private Long sfyzf;
	/**是否已评级（0：否，1：是）*/
	@Excel(name = "是否已评级", width = 15, dicCode = "sfbz_ex")
	@ApiModelProperty(value = "是否已评级")
	@Dict(dicCode = "sfbz_ex")
	private Long sfyjpj;
	/**是否已授信（0：否，1：是）*/
	@Excel(name = "是否已授信", width = 15, dicCode = "sfbz_ex")
	@ApiModelProperty(value = "是否已授信")
	@Dict(dicCode = "sfbz_ex")
	private Long sfysx;
	/**是否已用信（0：否，1：是）*/
	@Excel(name = "是否已用信", width = 15, dicCode = "sfbz_ex")
	@ApiModelProperty(value = "是否已用信")
	@Dict(dicCode = "sfbz_ex")
	private Long sfyyx;
	/**最终授信额度*/
	@Excel(name = "最终授信额度", width = 15)
    @ApiModelProperty(value = "最终授信额度")
	private String zzsxed;
	/**系统测算评定等级*/
	@Excel(name = "系统测算评定等级", width = 15)
    @ApiModelProperty(value = "系统测算评定等级")
	private String xtcspddj;
	/**用信金额*/
	@Excel(name = "用信金额", width = 15)
    @ApiModelProperty(value = "用信金额")
	private java.math.BigDecimal yxje;

}

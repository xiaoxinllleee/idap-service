package org.cmms.modules.xdgl.nsb.entity;

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
 * @Description: 年审统计分析
 * @Author: jeecg-boot
 * @Date:   2023-07-04
 * @Version: V1.0
 */
@Data
@TableName("NSB_TJFX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="NSB_TJFX对象", description="年审统计分析")
public class NsbTjfx {
    
	/**tjsj*/
	@Excel(name = "统计时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计时间")
	private Date tjsj;
	/**zzbz*/
	//@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private String zzbz;
	/**组织简称*/
	@Excel(name = "组织简称", width = 15)
    @ApiModelProperty(value = "组织简称")
	private String zzjc;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**总人数*/
	@Excel(name = "总人数", width = 15)
    @ApiModelProperty(value = "总人数")
	private Integer zrs;
	/**等级A*/
	@Excel(name = "等级A", width = 15)
    @ApiModelProperty(value = "等级A")
	private Integer dja;
	/**A比例*/
	//@Excel(name = "A比例", width = 15)
    @ApiModelProperty(value = "A比例")
	private java.math.BigDecimal abl;
	/**等级B*/
	@Excel(name = "等级B", width = 15)
    @ApiModelProperty(value = "等级B")
	private Integer djb;
	/**B比例*/
	//@Excel(name = "B比例", width = 15)
    @ApiModelProperty(value = "B比例")
	private java.math.BigDecimal bbl;
	/**等级C*/
	@Excel(name = "等级C", width = 15)
    @ApiModelProperty(value = "等级C")
	private Integer djc;
	/**C比例*/
	//@Excel(name = "C比例", width = 15)
    @ApiModelProperty(value = "C比例")
	private java.math.BigDecimal cbl;
	/**等级D*/
	@Excel(name = "等级D", width = 15)
    @ApiModelProperty(value = "等级D")
	private Integer djd;
	/**D比例*/
	//@Excel(name = "D比例", width = 15)
    @ApiModelProperty(value = "D比例")
	private java.math.BigDecimal dbl;
	/**等级E*/
	@Excel(name = "等级E", width = 15)
    @ApiModelProperty(value = "等级E")
	private Integer dje;
	/**E比例*/
	//@Excel(name = "E比例", width = 15)
    @ApiModelProperty(value = "E比例")
	private java.math.BigDecimal ebl;
	/**手机号码重复人数*/
	//@Excel(name = "手机号码重复人数", width = 15)
    @ApiModelProperty(value = "手机号码重复人数")
	private Integer sjhmcfrs;
	/**比例*/
	//@Excel(name = "比例", width = 15)
    @ApiModelProperty(value = "比例")
	private java.math.BigDecimal sjhmbl;
	/**授信对象年龄过大人数*/
	//@Excel(name = "授信对象年龄过大人数", width = 15)
    @ApiModelProperty(value = "授信对象年龄过大人数")
	private Integer sxdxnlrs;
	/**比例*/
	//@Excel(name = "比例", width = 15)
    @ApiModelProperty(value = "比例")
	private java.math.BigDecimal sxdxbl;
	/**授信等级和金额不对人数*/
	//@Excel(name = "授信等级和金额不对人数", width = 15)
    @ApiModelProperty(value = "授信等级和金额不对人数")
	private Integer sxedrs;
	/**比例*/
	//@Excel(name = "比例", width = 15)
    @ApiModelProperty(value = "比例")
	private java.math.BigDecimal sxedbl;
	/**a授信金额*/
	@Excel(name = "A授信金额", width = 15)
    @ApiModelProperty(value = "a授信金额")
	private Integer asxje;
	/**b授信金额*/
	@Excel(name = "B授信金额", width = 15)
    @ApiModelProperty(value = "b授信金额")
	private Integer bsxje;
	/**c授信金额*/
	@Excel(name = "C授信金额", width = 15)
    @ApiModelProperty(value = "c授信金额")
	private Integer csxje;
	/**e授信金额*/
	//@Excel(name = "E授信金额", width = 15)
    @ApiModelProperty(value = "e授信金额")
	private Integer esxje;
	/**d授信金额*/
	@Excel(name = "D授信金额", width = 15)
    @ApiModelProperty(value = "d授信金额")
	private Integer dsxje;
	/**zje*/
	//@Excel(name = "zje", width = 15)
    @ApiModelProperty(value = "zje")
	private Integer zje;
	/**下发户数*/
	//@Excel(name = "下发户数", width = 15)
    @ApiModelProperty(value = "下发户数")
	private Integer xfhs;
	/**下发人数*/
	//@Excel(name = "下发人数", width = 15)
    @ApiModelProperty(value = "下发人数")
	private Integer xfrs;
	/**新增人数*/
	//@Excel(name = "新增人数", width = 15)
    @ApiModelProperty(value = "新增人数")
	private Integer xzrs;
	/**xgrs*/
	//@Excel(name = "修改人数", width = 15)
    @ApiModelProperty(value = "修改人数")
	private Integer xgrs;
}

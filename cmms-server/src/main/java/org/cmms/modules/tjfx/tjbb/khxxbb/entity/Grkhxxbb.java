package org.cmms.modules.tjfx.tjbb.khxxbb.entity;

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
 * @Description: 客户信息报名(明细)
 * @Author: jeecg-boot
 * @Date:   2020-03-30
 * @Version: V1.0
 */
@Data
@TableName("TJFX_KHXXBB_GRMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_KHXXBB_GRMX对象", description="客户信息报名(明细)")
public class Grkhxxbb {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属营销单元*/
	@Excel(name = "所属营销单元", width = 15)
    @ApiModelProperty(value = "所属营销单元")
	private String ssyxdy;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	@Excel(name = "乡镇", width = 15)
	@ApiModelProperty(value = "乡镇")
	private String xz;
	/**村*/
	@Excel(name = "村", width = 15)
    @ApiModelProperty(value = "村")
	private String xzc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
	@ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "khlx")
	private String khlx;
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
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String zkhjl;
	/**建档完整度*/
	@Excel(name = "建档完整度", width = 15)
    @ApiModelProperty(value = "建档完整度")
	private java.math.BigDecimal infoRate;
	/**累计授信*/
	@Excel(name = "累计授信", width = 15)
    @ApiModelProperty(value = "累计授信")
	private java.math.BigDecimal ljsx;
	/**本月授信*/
	@Excel(name = "本月授信", width = 15)
    @ApiModelProperty(value = "本月授信")
	private java.math.BigDecimal bysx;
	/**累计用信*/
	@Excel(name = "累计用信", width = 15)
    @ApiModelProperty(value = "累计用信")
	private java.math.BigDecimal ljyx;
	/**本月用信*/
	@Excel(name = "本月用信", width = 15)
    @ApiModelProperty(value = "本月用信")
	private java.math.BigDecimal byyx;
}

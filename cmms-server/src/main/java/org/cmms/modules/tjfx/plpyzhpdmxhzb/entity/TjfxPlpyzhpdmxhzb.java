package org.cmms.modules.tjfx.plpyzhpdmxhzb.entity;

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
 * @Description: 批量评议综合评定明细汇总表
 * @Author: jeecg-boot
 * @Date:   2023-06-28
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_PLPYZHPDMXHZB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_PLPYZHPDMXHZB对象", description="批量评议综合评定明细汇总表")
public class TjfxPlpyzhpdmxhzb {

	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	@ApiModelProperty(value = "所属支行")
	@Dict(dicCode="ZZBZ", dictTable="HR_BAS_ORGANIZATION", dicText="ZZJC")
	private String sszh;
	/**村组编码*/
	@Excel(name = "村组名称", width = 15,dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	@ApiModelProperty(value = "村组名称")
	@Dict(dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String czbm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String khxm;
	/**第一轮评议得分*/
	@Excel(name = "第一轮评议得分", width = 15)
    @ApiModelProperty(value = "第一轮评议得分")
	private String dylpyde;
	/**第一轮评议等级*/
	@Excel(name = "第一轮评议等级", width = 15)
    @ApiModelProperty(value = "第一轮评议等级")
	@Dict(dicCode = "plpypddj")
	private String dylpydj;
	/**第一轮评议得分*/
	@Excel(name = "第一轮授信", width = 15)
	@ApiModelProperty(value = "第一轮授信")
	private java.math.BigDecimal dylsx;
	/**第二轮评议得分*/
	@Excel(name = "第二轮评议得分", width = 15)
    @ApiModelProperty(value = "第二轮评议得分")
	private String delpyde;
	/**第二轮评议等级*/
	@Excel(name = "第二轮评议等级", width = 15)
    @ApiModelProperty(value = "第二轮评议等级")
	@Dict(dicCode = "plpypddj")
	private String delpydj;
	/**第一轮评议得分*/
	@Excel(name = "第二轮授信", width = 15)
	@ApiModelProperty(value = "第二轮授信")
	private java.math.BigDecimal delsx;
	/**第三轮评议得分*/
	@Excel(name = "第三轮评议得分", width = 15)
    @ApiModelProperty(value = "第三轮评议得分")
	private String dslpyde;
	/**第三轮评议等级*/
	@Excel(name = "第三轮评议等级", width = 15)
    @ApiModelProperty(value = "第三轮评议等级")
	@Dict(dicCode = "plpypddj")
	private String dslpydj;
	/**第一轮评议得分*/
	@Excel(name = "第三轮授信", width = 15)
	@ApiModelProperty(value = "第三轮授信")
	private java.math.BigDecimal dslsx;
	/**综合评议得分*/
	@Excel(name = "综合评议得分", width = 15)
    @ApiModelProperty(value = "综合评议得分")
	private String zhpydf;
	/**综合评议等级*/
	@Excel(name = "综合评议等级", width = 15)
    @ApiModelProperty(value = "综合评议等级")
	@Dict(dicCode = "plpypddj")
	private String zhpydj;
	/**综合建议授信额度*/
	@Excel(name = "综合建议授信额度", width = 15)
    @ApiModelProperty(value = "综合建议授信额度")
	private java.math.BigDecimal zhjysxed;
}

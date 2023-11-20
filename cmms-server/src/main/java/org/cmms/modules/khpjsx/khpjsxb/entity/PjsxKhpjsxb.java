package org.cmms.modules.khpjsx.khpjsxb.entity;

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
 * @Description: 客户评级授信表
 * @Author: jeecg-boot
 * @Date:   2020-01-13
 * @Version: V1.0
 */
@Data
@TableName("PJSX_KHPJSXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_KHPJSXB对象", description="客户评级授信表")
public class PjsxKhpjsxb {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15, dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "组织标识")
    @Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String zzbz;
	/**评级日期*/
	@Excel(name = "评级日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "评级日期")
	private Date pjrq;
	/**评级得分*/
	@Excel(name = "评级得分", width = 15)
    @ApiModelProperty(value = "评级得分")
	private Long pjdf;
	/**评级等级*/
	@Excel(name = "评级等级", width = 15, dicCode = "DJBH", dictTable = "PJSX_KHDJSZ", dicText = "DJMC")
    @ApiModelProperty(value = "评级等级")
    @Dict(dicCode = "DJBH", dictTable = "PJSX_KHDJSZ", dicText = "DJMC")
	private String pjdj;
	/**推荐额度*/
	@Excel(name = "授信额度", width = 15)
    @ApiModelProperty(value = "授信额度")
	private java.math.BigDecimal sxed;
    /**推荐额度*/
    @Excel(name = "等级系数", width = 15)
    @ApiModelProperty(value = "等级系数")
    private java.math.BigDecimal djxs;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15, dicCode = "khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "khlx")
	private String khlx;
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
	/**录入标识*/
	@Excel(name = "录入标识", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标识")
    @Dict(dicCode = "lrbz")
	private Integer lrbz;

}

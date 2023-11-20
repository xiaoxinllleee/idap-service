package org.cmms.modules.tjbb.ckywfx.ckjgfxb.entity;

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
 * @Description: 存款结构分析表
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Data
@TableName("TJBB_CKYW_CKJGFXB_ZH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_CKYW_CKJGFXB_ZH对象", description="存款结构分析表")
public class Ckjgfxb {
    
	/**统计日期*/
	@Excel(name = "统计日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计日期")
	private Date tjrq;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**户数(户)*/
	@Excel(name = "户数", width = 15)
    @ApiModelProperty(value = "户数")
	private Integer hs;
	/**对私户数(户)*/
	@Excel(name = "对私户数", width = 15)
    @ApiModelProperty(value = "对私户数")
	private Integer dshs;
	/**对私余额*/
	@Excel(name = "对私余额", width = 15)
    @ApiModelProperty(value = "对私余额")
	private java.math.BigDecimal dsye;
	/**对公户数(户)*/
	@Excel(name = "对公户数", width = 15)
    @ApiModelProperty(value = "对公户数")
	private Integer dghs;
	/**对公余额*/
	@Excel(name = "对公余额", width = 15)
    @ApiModelProperty(value = "对公余额")
	private java.math.BigDecimal dgye;
	/**<30岁户数*/
	@Excel(name = "<30岁(含)户数", width = 15)
    @ApiModelProperty(value = "<30岁(含)户数")
	private Integer anlhs1;
	/**<30岁余额*/
	@Excel(name = "<30岁(含)余额", width = 15)
    @ApiModelProperty(value = "<30岁(含)余额")
	private java.math.BigDecimal anlye1;
	/**(30-50]岁户数*/
	@Excel(name = "30-50(含)岁户数", width = 15)
    @ApiModelProperty(value = "30-50(含)岁户数")
	private Integer anlhs2;
	/**(30-50]岁余额*/
	@Excel(name = "30-50(含)岁余额", width = 15)
    @ApiModelProperty(value = "30-50(含)岁余额")
	private java.math.BigDecimal anlye2;
	/**(50-65]岁户数*/
	@Excel(name = "50-65(含)岁户数", width = 15)
    @ApiModelProperty(value = "50-65(含)岁户数")
	private Integer anlhs3;
	/**(50-65]岁余额*/
	@Excel(name = "50-65(含)岁余额", width = 15)
    @ApiModelProperty(value = "50-65(含)岁余额")
	private java.math.BigDecimal anlye3;
	/**>65岁户数*/
	@Excel(name = ">65岁户数", width = 15)
    @ApiModelProperty(value = ">65岁户数")
	private Integer anlhs4;
	/**>65岁余额*/
	@Excel(name = ">65岁余额", width = 15)
    @ApiModelProperty(value = ">65岁余额")
	private java.math.BigDecimal anlye4;
	/**<1万元户数*/
	@Excel(name = "<1万元(含)户数", width = 15)
    @ApiModelProperty(value = "<1万元(含)户数")
	private Integer ayehs1;
	/**<1万元余额*/
	@Excel(name = "<1万元(含)余额", width = 15)
    @ApiModelProperty(value = "<1万元(含)余额")
	private java.math.BigDecimal ayeye1;
	/**(1万-5万]户数*/
	@Excel(name = "1-5(含)万户数", width = 15)
    @ApiModelProperty(value = "1-5(含)万户数")
	private Integer ayehs2;
	/**(1万-5万]余额*/
	@Excel(name = "1-5(含)万余额", width = 15)
    @ApiModelProperty(value = "1-5(含)万余额")
	private java.math.BigDecimal ayeye2;
	/**(5万-10万]户数*/
	@Excel(name = "5-10(含)万户数", width = 15)
    @ApiModelProperty(value = "5-10(含)万户数")
	private Integer ayehs3;
	/**(5万-10万]余额*/
	@Excel(name = "5-10(含)万余额", width = 15)
    @ApiModelProperty(value = "5-10(含)万余额")
	private java.math.BigDecimal ayeye3;
	/**(10万-50万]户数*/
	@Excel(name = "10-50(含)万户数", width = 15)
    @ApiModelProperty(value = "10-50(含)万户数")
	private Integer ayehs4;
	/**(10万-50万]余额*/
	@Excel(name = "10-50(含)万余额", width = 15)
    @ApiModelProperty(value = "10-50(含)万余额")
	private java.math.BigDecimal ayeye4;
	/**>50万户数*/
	@Excel(name = ">50万户数", width = 15)
    @ApiModelProperty(value = ">50万户数")
	private Integer ayehs5;
	/**>50万余额*/
	@Excel(name = ">50万余额", width = 15)
    @ApiModelProperty(value = ">50万余额")
	private java.math.BigDecimal ayeye5;
	/**活期户数*/
	@Excel(name = "活期户数", width = 15)
    @ApiModelProperty(value = "活期户数")
	private Integer hqhs;
	/**其中财政性资金活期户数*/
	@Excel(name = "其中财政性资金活期户数", width = 15)
    @ApiModelProperty(value = "其中财政性资金活期户数")
	private Integer hqCzxzjhs;
	/**活期余额*/
	@Excel(name = "活期余额", width = 15)
    @ApiModelProperty(value = "活期余额")
	private java.math.BigDecimal hqye;
	/**其中财政性资金活期余额*/
	@Excel(name = "其中财政性资金活期余额", width = 15)
    @ApiModelProperty(value = "其中财政性资金活期余额")
	private java.math.BigDecimal hqCzxzjye;
	/**定期户数*/
	@Excel(name = "定期户数", width = 15)
    @ApiModelProperty(value = "定期户数")
	private Integer dqhs;
	/**其中财政性资金定期户数*/
	@Excel(name = "其中财政性资金定期户数", width = 15)
    @ApiModelProperty(value = "其中财政性资金定期户数")
	private Integer dqCzxzjhs;
	/**定期余额*/
	@Excel(name = "定期余额", width = 15)
    @ApiModelProperty(value = "定期余额")
	private java.math.BigDecimal dqye;
	/**其中财政性资金定期余额*/
	@Excel(name = "其中财政性资金定期余额", width = 15)
    @ApiModelProperty(value = "其中财政性资金定期余额")
	private java.math.BigDecimal dqCzxzjye;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

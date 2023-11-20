package org.cmms.modules.yxdygl.ejyxdygl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 村情统计
 * @Author: jeecg-boot
 * @Date:   2021-04-14
 * @Version: V1.0
 */
@Data
@TableName("tjfx_cqtj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_cqtj对象", description="村情统计")
public class Cqtj {
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**所属乡镇*/
	@Excel(name = "所属乡镇", width = 15)
	@ApiModelProperty(value = "所属乡镇")
	private String ssxz;
	/**行政村*/
	@Excel(name = "行政村", width = 15)
	@ApiModelProperty(value = "行政村")
	private String xzc;
	/**人口数*/
	@Excel(name = "人口数", width = 15)
	@ApiModelProperty(value = "人口数")
	private Integer rks;
	/**户数*/
	@Excel(name = "户数", width = 15)
	@ApiModelProperty(value = "户数")
	private Integer hs;


	/**总客户数*/
	@Excel(name = "总客户数", width = 15)
	@ApiModelProperty(value = "总客户数")
	private Integer zkhs;
	/**高端客户数*/
	@Excel(name = "高端客户数", width = 15)
	@ApiModelProperty(value = "高端客户数")
	private Integer gdkhs;
	/**核心客户数*/
	@Excel(name = "核心客户数", width = 15)
	@ApiModelProperty(value = "核心客户数")
	private Integer hxkhs;
	/**建档客户数*/
	@Excel(name = "建档客户数", width = 15)
	@ApiModelProperty(value = "建档客户数")
	private Integer jdkhs;
	/**开通存款业务人数*/
	@Excel(name = "开通存款业务人数", width = 15)
	@ApiModelProperty(value = "开通存款业务人数")
	private Integer ktckywrs;
	/**存款总额*/
	@Excel(name = "存款总额", width = 15)
	@ApiModelProperty(value = "存款总额")
	private java.math.BigDecimal ckze;
	/**开通贷款业务人数*/
	@Excel(name = "开通贷款业务人数", width = 15)
	@ApiModelProperty(value = "开通贷款业务人数")
	private Integer ktdkywrs;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
	@ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**用信人数*/
	@Excel(name = "用信人数", width = 15)
	@ApiModelProperty(value = "用信人数")
	private Integer yxrs;
	/**开通手机银行人数*/
	@Excel(name = "开通手机银行人数", width = 15)
	@ApiModelProperty(value = "开通手机银行人数")
	private Integer ktsjyhrs;
	/**开通etc人数*/
	@Excel(name = "开通etc人数", width = 15)
	@ApiModelProperty(value = "开通etc人数")
	private Integer ktetcrs;




	/**30岁以下存款人均*/
	@Excel(name = "30岁以下存款人均", width = 15)
    @ApiModelProperty(value = "30岁以下存款人均")
	private java.math.BigDecimal ckrj0z30;

	/**30-50岁存款人均*/
	@Excel(name = "30-50岁存款人均", width = 15)
	@ApiModelProperty(value = "30-50岁存款人均")
	private java.math.BigDecimal ckrj30z50;

	/**50-60岁存款人均*/
	@Excel(name = "50-60岁存款人均", width = 15)
	@ApiModelProperty(value = "50-60岁存款人均")
	private java.math.BigDecimal ckrj50z60;

	/**60岁以上存款人均*/
	@Excel(name = "60岁以上存款人均", width = 15)
	@ApiModelProperty(value = "60岁以上存款人均")
	private java.math.BigDecimal ckrj61;





	/**30岁以下存款客户数*/
	@Excel(name = "30岁以下存款客户数", width = 15)
	@ApiModelProperty(value = "30岁以下存款客户数")
	private Integer ckkhs0z30;


	/**30-50岁存款客户数*/
	@Excel(name = "30-50岁存款客户数", width = 15)
    @ApiModelProperty(value = "30-50岁存款客户数")
	private Integer ckkhs30z50;

	/**50-60岁存款客户数*/
	@Excel(name = "50-60岁存款客户数", width = 15)
	@ApiModelProperty(value = "50-60岁存款客户数")
	private Integer ckkhs50z60;

	/**60岁以上存款客户数*/
	@Excel(name = "60岁以上存款客户数", width = 15)
	@ApiModelProperty(value = "60岁以上存款客户数")
	private Integer ckkhs61;







	/**30岁以下存款余额*/
	@Excel(name = "30岁以下存款余额", width = 15)
	@ApiModelProperty(value = "30岁以下存款余额")
	private java.math.BigDecimal ckye0z30;

	/**30-50岁存款余额*/
	@Excel(name = "30-50岁存款余额", width = 15)
    @ApiModelProperty(value = "30-50岁存款余额")
	private java.math.BigDecimal ckye30z50;


	/**50-60岁存款余额*/
	@Excel(name = "50-60岁存款余额", width = 15)
    @ApiModelProperty(value = "50-60岁存款余额")
	private java.math.BigDecimal ckye50z60;


	/**60岁以上存款余额*/
	@Excel(name = "60岁以上存款余额", width = 15)
    @ApiModelProperty(value = "60岁以上存款余额")
	private java.math.BigDecimal ckye61;





	/**存款5万以上客户数*/
	@Excel(name = "存款5万以上客户数", width = 15)
    @ApiModelProperty(value = "存款5万以上客户数")
	private Integer ckkhs5w;

	/**存款5万以上存款余额*/
	@Excel(name = "存款5万以上存款余额", width = 15)
    @ApiModelProperty(value = "存款5万以上存款余额")
	private java.math.BigDecimal ckye5w;


	/**18岁以下人数*/
	@Excel(name = "18岁以下人数", width = 15)
	@ApiModelProperty(value = "18岁以下人数")
	private Integer rks0z18;
	/**19-45岁人数*/
	@Excel(name = "19-45岁人数", width = 15)
	@ApiModelProperty(value = "19-45岁人数")
	private Integer rks19z45;
	/**46-60岁人数*/
	@Excel(name = "46-60岁人数", width = 15)
	@ApiModelProperty(value = "46-60岁人数")
	private Integer rks46z60;
	/**60岁以上人数*/
	@Excel(name = "60岁以上人数", width = 15)
	@ApiModelProperty(value = "60岁以上人数")
	private Integer rks61;




	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;




}

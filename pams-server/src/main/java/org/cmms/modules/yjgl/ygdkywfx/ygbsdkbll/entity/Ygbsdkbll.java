package org.cmms.modules.yjgl.ygdkywfx.ygbsdkbll.entity;

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
 * @Description: 员工包收贷款不良率
 * @Author: jeecg-boot
 * @Date:   2023-07-13
 * @Version: V1.0
 */
@Data
@TableName("Tb_dk_ygbsdkbll")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tb_dk_ygbsdkbll对象", description="员工包收贷款不良率")
public class Ygbsdkbll {

	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**员工所属机构代码*/
	@Excel(name = "员工所属机构名称", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	@ApiModelProperty(value = "员工所属机构名称")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ygssjgdm;
	/**员工工号*/
	@Excel(name = "员工姓名", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	@ApiModelProperty(value = "员工姓名")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String yggh;
	/**本月贷款总余额*/
	@Excel(name = "本月贷款总余额", width = 15)
    @ApiModelProperty(value = "本月贷款总余额")
	private java.math.BigDecimal bydkzye;
	/**本月正常类余额*/
	@Excel(name = "本月正常类余额", width = 15)
    @ApiModelProperty(value = "本月正常类余额")
	private java.math.BigDecimal byzclye;
	/**本月关注类余额*/
	@Excel(name = "本月关注类余额", width = 15)
    @ApiModelProperty(value = "本月关注类余额")
	private java.math.BigDecimal bygzlye;
	/**本月次级类余额*/
	@Excel(name = "本月次级类余额", width = 15)
    @ApiModelProperty(value = "本月次级类余额")
	private java.math.BigDecimal bycjlye;
	/**本月可疑类余额*/
	@Excel(name = "本月可疑类余额", width = 15)
    @ApiModelProperty(value = "本月可疑类余额")
	private java.math.BigDecimal bykylye;
	/**本月损失类余额*/
	@Excel(name = "本月损失类余额", width = 15)
    @ApiModelProperty(value = "本月损失类余额")
	private java.math.BigDecimal bysslye;
	/**本月不良余额合计*/
	@Excel(name = "本月不良余额合计", width = 15)
    @ApiModelProperty(value = "本月不良余额合计")
	private java.math.BigDecimal byblyehj;
	/**本月不良率*/
	@Excel(name = "本月不良率", width = 15)
    @ApiModelProperty(value = "本月不良率")
	private java.math.BigDecimal bybll;
	/**存量贷款总余额*/
	@Excel(name = "存量贷款总余额", width = 15)
    @ApiModelProperty(value = "存量贷款总余额")
	private java.math.BigDecimal cldkzye;
	/**存量正常类余额*/
	@Excel(name = "存量正常类余额", width = 15)
    @ApiModelProperty(value = "存量正常类余额")
	private java.math.BigDecimal clzclye;
	/**存量关注类余额*/
	@Excel(name = "存量关注类余额", width = 15)
    @ApiModelProperty(value = "存量关注类余额")
	private java.math.BigDecimal clgzlye;
	/**存量次级类余额*/
	@Excel(name = "存量次级类余额", width = 15)
    @ApiModelProperty(value = "存量次级类余额")
	private java.math.BigDecimal clcjlye;
	/**存量可疑类余额*/
	@Excel(name = "存量可疑类余额", width = 15)
    @ApiModelProperty(value = "存量可疑类余额")
	private java.math.BigDecimal clkylye;
	/**存量损失类余额*/
	@Excel(name = "存量损失类余额", width = 15)
    @ApiModelProperty(value = "存量损失类余额")
	private java.math.BigDecimal clsslye;
	/**存量不良余额合计*/
	@Excel(name = "存量不良余额合计", width = 15)
    @ApiModelProperty(value = "存量不良余额合计")
	private java.math.BigDecimal clblyehj;
	/**存量不良率*/
	@Excel(name = "存量不良率", width = 15)
    @ApiModelProperty(value = "存量不良率")
	private java.math.BigDecimal clbll;
	/**上月贷款总余额*/
	@Excel(name = "上月贷款总余额", width = 15)
    @ApiModelProperty(value = "上月贷款总余额")
	private java.math.BigDecimal sydkzye;
	/**上月正常类余额*/
	@Excel(name = "上月正常类余额", width = 15)
    @ApiModelProperty(value = "上月正常类余额")
	private java.math.BigDecimal syzclye;
	/**上月关注类余额*/
	@Excel(name = "上月关注类余额", width = 15)
    @ApiModelProperty(value = "上月关注类余额")
	private java.math.BigDecimal sygzlye;
	/**上月次级类余额*/
	@Excel(name = "上月次级类余额", width = 15)
    @ApiModelProperty(value = "上月次级类余额")
	private java.math.BigDecimal sycjlye;
	/**上月可疑类余额*/
	@Excel(name = "上月可疑类余额", width = 15)
    @ApiModelProperty(value = "上月可疑类余额")
	private java.math.BigDecimal sykylye;
	/**上月损失类余额*/
	@Excel(name = "上月损失类余额", width = 15)
    @ApiModelProperty(value = "上月损失类余额")
	private java.math.BigDecimal sysslye;
	/**上月不良余额合计*/
	@Excel(name = "上月不良余额合计", width = 15)
    @ApiModelProperty(value = "上月不良余额合计")
	private java.math.BigDecimal syblyehj;
	/**上月不良率*/
	@Excel(name = "上月不良率", width = 15)
    @ApiModelProperty(value = "上月不良率")
	private java.math.BigDecimal sybll;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15,dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志")
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

}

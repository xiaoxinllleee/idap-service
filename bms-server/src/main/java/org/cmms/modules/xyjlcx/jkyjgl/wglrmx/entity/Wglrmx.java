package org.cmms.modules.xyjlcx.jkyjgl.wglrmx.entity;

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
 * @Description: 无关联人明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@Data
@TableName("Credit_wglrmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Credit_wglrmx对象", description="无关联人明细")
public class Wglrmx {
    
	/**业务机构*/
	@Excel(name = "业务机构", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywjg;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**无关联人类型*/
	@Excel(name = "无关联人类型", width = 15,dicCode = "wglrlx")
    @ApiModelProperty(value = "无关联人类型")
	@Dict(dicCode = "wglrlx")
	private Integer wglrlx;
	/**所属业务种类*/
	@Excel(name = "所属业务种类", width = 15,dicCode = "ssywzl")
    @ApiModelProperty(value = "所属业务种类")
	@Dict(dicCode = "ssywzl")
	private Integer ssywzl;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

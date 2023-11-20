package org.cmms.modules.tjfx.jcsjgl.bmk.entity;

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
 * @Date:   2020-08-10
 * @Version: V1.0
 */
@Data
@TableName("TJFX_BMKHTB_XDXTSJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_BMKHTB_XDXTSJ对象", description="1")
public class TjfxBmkhtbXdxtsj {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**卡号*/
	@Excel(name = "卡号", width = 15)
    @ApiModelProperty(value = "卡号")
	private String kh;
	/**合同开始日期*/
	@Excel(name = "合同开始日期", width = 15)
    @ApiModelProperty(value = "合同开始日期")
	private String htksrq;
	/**合同到期日期*/
	@Excel(name = "合同到期日期", width = 15)
    @ApiModelProperty(value = "合同到期日期")
	private String htdqrq;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**合同可用金额*/
	@Excel(name = "合同可用金额", width = 15)
    @ApiModelProperty(value = "合同可用金额")
	private java.math.BigDecimal htkyje;
	/**业务品种*/
	@Excel(name = "业务品种", width = 15,dicCode = "ywpz")
    @ApiModelProperty(value = "业务品种")
	@Dict(dicCode = "ywpz")
	private String ywpz;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "第一责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String dyzrr;
	/**关联责任人*/
	@Excel(name = "关联责任人", width = 15, dicCode="yggh", dictTable="HR_BAS_STAFF", dicText="ygxm")
    @ApiModelProperty(value = "关联责任人")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String glzrr;
	/**合同状态*/
	@Excel(name = "合同状态", width = 15)
    @ApiModelProperty(value = "合同状态")
	private String htzt;
}

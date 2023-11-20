package org.cmms.modules.xddaglxt.xtgl.dkhtsjls.entity;

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
 * @Description: 贷款合同数据流水
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_DKHTSJLSB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_DKHTSJLSB对象", description="贷款合同数据流水")
public class Dkhtsjls {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
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
	/**客户类型*/
	@Excel(name = "客户类型", width = 15,dicCode = "xdda_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	private String hth;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String ywbh;
	/**责任人工号*/
	@Excel(name = "责任人工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "责任人工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String dkzrr;
	/**移交后责任人工号*/
	@Excel(name = "移交后责任人工号", width = 15,dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
    @ApiModelProperty(value = "移交后责任人工号")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private String yjhzrr;
	/**移交日期*/
	@Excel(name = "移交日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "移交日期")
	private Date yjrq;
	/**操作人*/
	@Excel(name = "操作人", width = 15)
	@ApiModelProperty(value = "操作人")
	private String czr;
}

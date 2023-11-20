package org.cmms.modules.dkjkpt.dksjjk.dksjahtj.entity;

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
 * @Description: 贷款数据按户统计
 * @Author: Penghr
 * @Date:   2020-03-31
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_BNDKSJAHTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_BNDKSJAHTJ对象", description="贷款数据按户统计")
public class Dksjahtj {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15, dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "YWJGDM", dictTable = "V_HR_BAS_ORGANIZATION_CMMSZH", dicText = "ZZJC")
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
	@Excel(name = "客户类型", width = 15, dicCode = "dkjkpt_khlx")
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "dkjkpt_khlx")
	private String khlx;
	/**地址*/
	@Excel(name = "住址", width = 15)
    @ApiModelProperty(value = "住址")
	private String dz;
	/**笔数*/
	@Excel(name = "笔数", width = 15)
    @ApiModelProperty(value = "笔数")
	private Long bs;
	/**贷款金额合计*/
	@Excel(name = "贷款金额合计", width = 15)
    @ApiModelProperty(value = "贷款金额合计")
	private java.math.BigDecimal dkjehj;
	/**贷款余额合计*/
	@Excel(name = "贷款余额合计", width = 15)
    @ApiModelProperty(value = "贷款余额合计")
	private java.math.BigDecimal dkyehj;
	/**客户类型1*/
	@Excel(name = "客户类型1", width = 15, dicCode = "dkjkpt_khlx1")
    @ApiModelProperty(value = "客户类型1")
	@Dict(dicCode = "dkjkpt_khlx1")
	private String khlx1;
	/**客户类型2*/
	@Excel(name = "客户类型2", width = 15, dicCode = "dkjkpt_khlx2")
    @ApiModelProperty(value = "客户类型2")
	@Dict(dicCode = "dkjkpt_khlx2")
	private String khlx2;
	/**客户类型3*/
	@Excel(name = "客户类型3", width = 15, dicCode = "dkjkpt_khlx3")
    @ApiModelProperty(value = "客户类型3")
	@Dict(dicCode = "dkjkpt_khlx3")
	private String khlx3;
	/**企业规模*/
	@Excel(name = "企业规模", width = 15, dicCode = "qygm")
    @ApiModelProperty(value = "企业规模")
	@Dict(dicCode = "qygm")
	private String qygm;
	/**是否多机构有贷款*/
	@Excel(name = "是否跨机构", width = 15, dicCode = "sfbz")
    @ApiModelProperty(value = "是否跨机构")
	@Dict(dicCode = "sfbz")
	private String sfcf;
}

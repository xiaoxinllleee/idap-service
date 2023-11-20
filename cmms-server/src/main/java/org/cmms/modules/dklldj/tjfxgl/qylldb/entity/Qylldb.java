package org.cmms.modules.dklldj.tjfxgl.qylldb.entity;

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
 * @Description: 签约利率对比
 * @Author: Penghr
 * @Date:   2020-03-28
 * @Version: V1.0
 */
@Data
@TableName("RATE_LLDJ_QYLLDB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RATE_LLDJ_QYLLDB对象", description="签约利率对比")
public class Qylldb {
    
	/**统计月份*/
	@Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
	private Date tjyf;
	/**机构代码*/
	@Excel(name = "机构名称", width = 15, dicCode = "YWJGDM", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "YWJGDM", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String jgdm;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
	@ApiModelProperty(value = "业务编号")
	private String ywbh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款金额合计*/
	@Excel(name = "贷款金额合计(元)", width = 15)
    @ApiModelProperty(value = "贷款金额合计")
	private java.math.BigDecimal dkjehj;
	/**贷款笔数*/
	@Excel(name = "贷款笔数", width = 15)
    @ApiModelProperty(value = "贷款笔数")
	private java.math.BigDecimal dkbs;
	/**合同日期*/
	@Excel(name = "合同日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "合同日期")
	private Date htrq;
	/**签约利率*/
	@Excel(name = "签约利率(‰)", width = 15)
    @ApiModelProperty(value = "签约利率")
	private java.math.BigDecimal qyll;
	/**执行定价ID*/
	@Excel(name = "执行定价ID", width = 15)
    @ApiModelProperty(value = "执行定价ID")
	private Integer zxdjid;
	/**定价年份*/
	@Excel(name = "定价日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "定价年份")
	private Date djnf;
	/**定价得分*/
	@Excel(name = "定价得分", width = 15)
    @ApiModelProperty(value = "定价得分")
	private java.math.BigDecimal djdf;
	/**基准利率*/
	@Excel(name = "基准利率(%)", width = 15)
    @ApiModelProperty(value = "基准利率")
	private java.math.BigDecimal jzll;
	/**上浮幅度*/
	@Excel(name = "上浮幅度(%)", width = 15)
    @ApiModelProperty(value = "上浮幅度")
	private java.math.BigDecimal sffd;
	/**执行利率*/
	@Excel(name = "执行利率(%)", width = 15)
    @ApiModelProperty(value = "执行利率")
	private java.math.BigDecimal zxll;
	/**利率对比*/
	@Excel(name = "利率对比", width = 15, dicCode = "lldj_lldb")
    @ApiModelProperty(value = "利率对比")
	@Dict(dicCode = "lldj_lldb")
	private String lldb;
	/**LPR利率*/
	//@Excel(name = "LPR利率", width = 15)
    @ApiModelProperty(value = "LPR利率")
	private java.math.BigDecimal lprll;
	/**基点(加/减)BP*/
	//@Excel(name = "基点(加/减)BP", width = 15)
    @ApiModelProperty(value = "基点(加/减)BP")
	private java.math.BigDecimal jdbp;
}

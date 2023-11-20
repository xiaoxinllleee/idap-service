package org.cmms.modules.xddagl.xdhc.xdhc02.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 信贷T+1核查
 * @Author: jeecg-boot
 * @Date:   2022-01-18
 * @Version: V1.0
 */
@Data
@TableName("Xddagl_dkhtsjxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Xddagl_dkhtsjxx对象", description="信贷T+1核查")
public class Xdhc02 {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
    @ApiModelProperty(value = "客户类型")
	@Dict(dicCode = "xddagl_khlx")
	private String khlx;
	/**联系电话*/
	@Excel(name = "联系电话", width = 15)
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
	@Excel(name = "联系地址", width = 15)
    @ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "签约日期")
	private Date qyrq;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
    @ApiModelProperty(value = "合同号")
	@ExcelVerify(notNull = true)
	private String hth;
	/**业务编号*/
	@Excel(name = "业务编号", width = 15)
    @ApiModelProperty(value = "业务编号")
	private String ywbh;
	/**放款日期*/
	@Excel(name = "放款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "放款日期")
	private Date fkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**签约期限*/
	@Excel(name = "签约期限", width = 15)
    @ApiModelProperty(value = "签约期限")
	private String qyqx;
	/**签约金额*/
	@Excel(name = "签约金额", width = 15)
    @ApiModelProperty(value = "签约金额")
	private java.math.BigDecimal qyje;
	/**最早贷款日期*/
	@Excel(name = "最早贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早贷款日期")
	private Date zzdkrq;
	/**最早到期日期*/
	@Excel(name = "最早到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早到期日期")
	private Date zzdqrq;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**是否授信*/
	@Excel(name = "是否授信", width = 15)
    @ApiModelProperty(value = "是否授信")
	@Dict(dicCode = "yxzt")
	private String sfsx;
	/**贷款责任人*/
	@Excel(name = "贷款责任人工号", width = 15,dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
    @ApiModelProperty(value = "贷款责任人工号")
	@Dict(dicCode = "yggh",dictTable = "Hr_bas_staff",dicText = "ygxm")
	@ExcelVerify(interHandler = true)
	private String dkzrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**贷款品种*/
	@Excel(name = "贷款品种", width = 15)
    @ApiModelProperty(value = "贷款品种")
	@Dict(dicCode = "dkzl")
	private String dkpz;
	/**档案编号*/
	@Excel(name = "档案编号", width = 15)
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**是否上传档案*/
	@Excel(name = "是否上传档案", width = 15)
    @ApiModelProperty(value = "是否上传档案")
	@Dict(dicCode = "sfbs")
	private String sfscda;
	/**贷款品种(补充)*/
	@Excel(name = "贷款品种(补充)", width = 15)
    @ApiModelProperty(value = "贷款品种(补充)")
	@Dict(dicCode = "dkzlbc")
	private String dkpzbc;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	@Dict(dicCode = "gljydbfs")
	private String dbfs;
	/**提取日期*/
	@Excel(name = "提取日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "提取日期")
	private Date tqrq;
	/**贷款形态*/
	@Excel(name = "贷款形态", width = 15)
    @ApiModelProperty(value = "贷款形态")
	private String dkxt;
	/**类型*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	@Dict(dicCode = "xddagl_dklx")
	private String lx;
	/**businessStatus*/
	@Excel(name = "businessStatus", width = 15)
    @ApiModelProperty(value = "businessStatus")
	private String businessStatus;
	/**是否启用授信*/
	@Excel(name = "是否启用授信", width = 15)
    @ApiModelProperty(value = "是否启用授信")
	private String sfqysx;
	/**审核状态*/
	@Excel(name = "审核状态", width = 15)
    @ApiModelProperty(value = "审核状态")
	@Dict(dicCode = "dkjkpt_shzt")
	private Integer shzt;
	/**月结审核标识: 1:审核, 0:未审核*/
	@Excel(name = "月结审核标识", width = 15)
    @ApiModelProperty(value = "月结审核标识")
	private Integer htzt;
}

package org.cmms.modules.xddagl.sytx.xddalrtx.entity;

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
 * @Description: 信贷档案录入提醒
 * @Author: jeecg-boot
 * @Date:   2022-01-05
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_DKHTSJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_DKHTSJXX对象", description="信贷档案录入提醒")
public class Xddalrtx {
    
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
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**合同号*/
	@Excel(name = "合同号", width = 15)
	@ApiModelProperty(value = "合同号")
	private String hth;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "签约日期")
	private Date qyrq;



	/**客户类型*/
    @ApiModelProperty(value = "客户类型")
	private String khlx;
	/**联系电话*/
    @ApiModelProperty(value = "联系电话")
	private String lxdh;
	/**联系地址*/
    @ApiModelProperty(value = "联系地址")
	private String lxdz;
	/**业务编号*/
    @ApiModelProperty(value = "业务编号")
	private String ywbh;
	/**放款日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "放款日期")
	private Date fkrq;
	/**到期日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**签约期限*/
    @ApiModelProperty(value = "签约期限")
	private String qyqx;
	/**签约金额*/
    @ApiModelProperty(value = "签约金额")
	private java.math.BigDecimal qyje;
	/**最早贷款日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早贷款日期")
	private Date zzdkrq;
	/**最早到期日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "最早到期日期")
	private Date zzdqrq;
	/**贷款金额*/
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**是否授信*/
    @ApiModelProperty(value = "是否授信")
	private String sfsx;
	/**贷款责任人*/
    @ApiModelProperty(value = "贷款责任人")
	private String dkzrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入标志*/
    @ApiModelProperty(value = "录入标志")
	private Integer lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**贷款品种*/
    @ApiModelProperty(value = "贷款品种")
	private String dkpz;
	/**档案编号*/
    @ApiModelProperty(value = "档案编号")
	private String dabh;
	/**是否上传档案*/
    @ApiModelProperty(value = "是否上传档案")
	private String sfscda;
	/**贷款品种(补充)*/
    @ApiModelProperty(value = "贷款品种(补充)")
	private String dkpzbc;
	/**担保方式*/
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**提取日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "提取日期")
	private Date tqrq;
	/**贷款形态*/
    @ApiModelProperty(value = "贷款形态")
	private String dkxt;
	/**类型*/
    @ApiModelProperty(value = "类型")
	private String lx;
	/**businessStatus*/
    @ApiModelProperty(value = "businessStatus")
	private String businessStatus;
	/**是否启用授信*/
    @ApiModelProperty(value = "是否启用授信")
	private String sfqysx;
	/**审核状态*/
    @ApiModelProperty(value = "审核状态")
	private Integer shzt;
	/**月结审核标识: 1:审核, 0:未审核*/
    @ApiModelProperty(value = "月结审核标识: 1:审核, 0:未审核")
	private Integer htzt;
}

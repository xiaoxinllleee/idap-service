package org.cmms.modules.xdgl.dksp.dkzqywsp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
 * @Description: 贷款展期业务审批注册表
 * @Author: jeecg-boot
 * @Date:   2023-10-10
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CamsZqywspYwzc {
    
	/**唯一标识id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识id")
	private String id;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
	@ApiModelProperty(value = "所属支行")
	private String sszh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**贷款种类*/
	@Excel(name = "贷款种类", width = 15)
	@ApiModelProperty(value = "贷款种类")
	@Dict(dicCode = "dkzl")
	private String dkzl;
	/**原合同编号*/
	@Excel(name = "原合同编号", width = 15)
	@ApiModelProperty(value = "原合同编号")
	private String yhtbh;
	/**原贷款日期*/
	@Excel(name = "原贷款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "原贷款日期")
	private Date ydkrq;
	/**原贷款金额*/
	@Excel(name = "原贷款金额", width = 15)
	@ApiModelProperty(value = "原贷款金额")
	private java.math.BigDecimal ydkje;
	/**原到期日期*/
	@Excel(name = "原到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "原到期日期")
	private Date ydqrq;
	/**申请展期金额*/
	@Excel(name = "申请展期金额", width = 15)
	@ApiModelProperty(value = "申请展期金额")
	private java.math.BigDecimal sqzqje;
	/**申请展期金日期*/
	@Excel(name = "申请展期金日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "createTime")
	private Date sqzqrq;
	/**申请展期期限*/
	@Excel(name = "申请展期期限", width = 15)
	@ApiModelProperty(value = "申请展期期限")
	private String sqzqqx;
	/**利率*/
	@Excel(name = "利率", width = 15)
	@ApiModelProperty(value = "利率")
	private String ll;
	/**包收责任人*/
	@Excel(name = "包收责任人", width = 15)
	@ApiModelProperty(value = "包收责任人")
	private String bszrr;
	/**管理责任人*/
	@Excel(name = "管理责任人", width = 15)
	@ApiModelProperty(value = "管理责任人")
	private String glzrr;
	/**申请展期还款理由*/
	@Excel(name = "申请展期还款理由", width = 15)
	@ApiModelProperty(value = "申请展期还款理由")
	private String sqzqhkly;
	/**备注*/
	@Excel(name = "备注", width = 15)
	@ApiModelProperty(value = "备注")
	private String bz;
	/**业务编号*/
	@ApiModelProperty(value = "业务编号")
	private String businessNumber;
	/**流程编号*/
	@ApiModelProperty(value = "流程编号")
	private String processId;
	/**createBy*/
	@Excel(name = "提交人", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "createBy")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String createBy;
	/**createTime*/
	@Excel(name = "提交时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}

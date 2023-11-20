package org.cmms.modules.dkjkpt.bldkftjk.entity;

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
 * @Description: 不良贷款反弹监控（比上月）
 * @Author: cmms
 * @Date:   2019-09-11
 * @Version: V1.0
 */
@Data
@TableName("DKJKPT_BLDKFTJKB_BSY")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DKJKPT_BLDKFTJKB_BSY对象", description="不良贷款反弹监控比上月")
public class DkjkptBldkftjkBsy {

    /**统计月份*/
    @Excel(name = "统计月份", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "统计月份")
    private Date tjyf;
    /**机构代码*/
    @Excel(name = "机构代码", width = 15, dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "机构代码")
    @Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
    private String jgdm;
    /**客户姓名*/
    @Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
    private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
	/**贷款金额*/
	@Excel(name = "贷款金额", width = 15)
    @ApiModelProperty(value = "贷款金额")
	private java.math.BigDecimal dkje;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**借款日期*/
	@Excel(name = "借款日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "借款日期")
	private Date jkrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
    @ApiModelProperty(value = "剩余天数")
	private Long syts;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "第一责任人")
    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    private String dyzrr;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15,dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    @ApiModelProperty(value = "主客户经理")
    @Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
    private String zkhjl;
    /**上月贷款形态*/
    @Excel(name = "上月贷款形态", width = 15, dicCode = "dkxt")
    @ApiModelProperty(value = "上月贷款形态")
    @Dict(dicCode = "dkxt")
    private Integer sydkxt;
    /**上月时间*/
    @Excel(name = "上月时间", width = 15, format = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上月时间")
    private Date sysj;
	/**当日贷款形态*/
	@Excel(name = "当日贷款形态", width = 15, dicCode = "dkxt")
    @ApiModelProperty(value = "当日贷款形态")
    @Dict(dicCode = "dkxt")
	private Integer drdkxt;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
}

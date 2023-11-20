package org.cmms.modules.sjxf.qtxt.sjyhxt.sjyhjymxb.entity;

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
 * @Description: 手机银行交易明细表
 * @Author: jeecg-boot
 * @Date:   2021-12-21
 * @Version: V1.0
 */
@Data
@TableName("V_sjxf_sjyhjymx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_sjxf_sjyhjymx对象", description="手机银行交易明细表")
public class Sjyhjymxb {
    
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String opennode;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String type;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String no;
	/**开户类型*/
	@Excel(name = "开户类型", width = 15)
    @ApiModelProperty(value = "开户类型")
	private String opentype;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	private String namecn;
	/**mobile*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	private String mobile;
	/**开户机构名称*/
	@Excel(name = "开户机构名称", width = 15)
    @ApiModelProperty(value = "开户机构名称")
	private String orgName;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String openDt;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15)
    @ApiModelProperty(value = "销户日期")
	private String closeDt;
	/**开销户状态*/
	@Excel(name = "开销户状态", width = 15)
    @ApiModelProperty(value = "开销户状态")
	private String accStat;
	/**本月交易金额*/
	@Excel(name = "本月交易金额", width = 15)
    @ApiModelProperty(value = "本月交易金额")
	private java.math.BigDecimal tranamtMm;
	/**本月交易笔数*/
	@Excel(name = "本月交易笔数", width = 15)
    @ApiModelProperty(value = "本月交易笔数")
	private Long trannoMm;
	/**本年交易金额*/
	@Excel(name = "本年交易金额", width = 15)
    @ApiModelProperty(value = "本年交易金额")
	private java.math.BigDecimal tranamtYy;
	/**本年交易笔数*/
	@Excel(name = "本年交易笔数", width = 15)
    @ApiModelProperty(value = "本年交易笔数")
	private Long trannoYy;
	/**上线以来交易金额*/
	@Excel(name = "上线以来交易金额", width = 15)
    @ApiModelProperty(value = "上线以来交易金额")
	private java.math.BigDecimal tranamt;
	/**上线以来交易笔数*/
	@Excel(name = "上线以来交易笔数", width = 15)
    @ApiModelProperty(value = "上线以来交易笔数")
	private Long tranno;
	/**legalNo*/
	@Excel(name = "legalNo", width = 15)
    @ApiModelProperty(value = "legalNo")
	private String legalNo;
	/**dtnum*/
	/*@Excel(name = "dtnum", width = 15)
    @ApiModelProperty(value = "dtnum")
	private Integer dtnum;*/
	/**name*/
	@Excel(name = "客户姓名1", width = 15)
    @ApiModelProperty(value = "客户姓名1")
	private String name;
	/**dhhm*/
	@Excel(name = "手机号1", width = 15)
    @ApiModelProperty(value = "手机号1")
	private String dhhm;
}

package org.cmms.modules.khgl.sjyh.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.dynamic.datasource.annotation.DS;
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
import org.cmms.common.annotation.Desensitize;
import org.cmms.common.aspect.annotation.Dict;
import org.cmms.common.enums.DesensitizeRuleEnums;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 客户管理_手机银行
 * @Author: jeecg-boot
 * @Date:   2022-03-16
 * @Version: V1.0
 */
@Data
@TableName("MBS_TRAN_INF")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MBS_TRAN_INF对象", description="客户管理_手机银行")
public class Ckglsjyh {

	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String opennode;
	/**证件类型*/
	@Excel(name = "证件类型", width = 15)
    @ApiModelProperty(value = "证件类型")
	private String type;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	@Dict(dicCode = "zjhm",dictTable = "KHGXGL_KHZLGL_GRKH",dicText = "cust_id",ds = "eweb")
	@Desensitize(rule = DesensitizeRuleEnums.ID_CARD)
	private String no;
	/**开户类型1-客户端 2-贴芯*/
	@Excel(name = "开户类型1-客户端 2-贴芯", width = 15)
    @ApiModelProperty(value = "开户类型1-客户端 2-贴芯")
	@Dict(dicCode = "sjyh_khlx")
	private String opentype;
	/**客户姓名*/
	@Excel(name = "客户姓名", width = 15)
    @ApiModelProperty(value = "客户姓名")
	@Desensitize(rule = DesensitizeRuleEnums.CHINESE_NAME)
	private String namecn;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	@Desensitize(rule = DesensitizeRuleEnums.MOBILE_PHONE)
	private String mobile;
	/**开户机构名称*/
	@Excel(name = "开户机构名称", width = 15)
    @ApiModelProperty(value = "开户机构名称")
	@Dict(dicCode = "zzjc", dictTable = "HR_BAS_ORGANIZATION", dicText = "ywjgdm")
	private String orgName;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
    @ApiModelProperty(value = "开户日期")
	private String openDt;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15)
    @ApiModelProperty(value = "销户日期")
	private String closeDt;
	/**开销户状态 1-销户 0-开户*/
	@Excel(name = "开销户状态 1-销户 0-开户", width = 15)
    @ApiModelProperty(value = "开销户状态 1-销户 0-开户")
	@Dict(dicCode = "sjyh_kxhzt")
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
	/**法人编号*/
	@Excel(name = "法人编号", width = 15)
    @ApiModelProperty(value = "法人编号")
	private String legalNo;
	/**天入库表编号-对不同的表名唯一*/
	/*@Excel(name = "天入库表编号-对不同的表名唯一", width = 15)
    @ApiModelProperty(value = "天入库表编号-对不同的表名唯一")
	private Integer dtnum;*/
	/**openteller*/
	@Excel(name = "经办人", width = 15)
    @ApiModelProperty(value = "经办人")
	@Dict(dicCode = "gyh", dictTable = "hr_bas_staff", dicText = "ygxm")
	private String openteller;
	/**canclenode*/
	@Excel(name = "canclenode", width = 15)
    @ApiModelProperty(value = "canclenode")
	private String canclenode;
	/**cancelteller*/
	@Excel(name = "cancelteller", width = 15)
    @ApiModelProperty(value = "cancelteller")
	private String cancelteller;

	@TableField(exist = false)
	private int xb;

	/**开户机构名称*/
	@TableField(exist = false)
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String orgNameShow;
}

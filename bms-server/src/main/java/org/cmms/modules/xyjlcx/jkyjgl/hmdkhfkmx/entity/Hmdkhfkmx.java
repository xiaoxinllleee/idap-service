package org.cmms.modules.xyjlcx.jkyjgl.hmdkhfkmx.entity;

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
 * @Description: 黑名单客户放款明细
 * @Author: jeecg-boot
 * @Date:   2021-08-12
 * @Version: V1.0
 */
@Data
@TableName("V_credit_hmdkhfkmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="V_credit_hmdkhfkmx对象", description="黑名单客户放款明细")
public class Hmdkhfkmx {
    
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String jgdm;
	/**业务机构代码*/
	@Excel(name = "业务机构代码", width = 15,dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构代码")
	@Dict(dicCode = "ywjgdm", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private String ywjgdm;
	/**不良行为记录描述*/
	@Excel(name = "不良行为记录描述", width = 15,dicCode = "bljlxwms")
	@ApiModelProperty(value = "不良行为记录描述")
	@Dict(dicCode = "bljlxwms")
	private String bljlxwms;
	/**业务种类*/
	@Excel(name = "业务种类", width = 15,dicCode = "dklx")
    @ApiModelProperty(value = "业务种类")
	@Dict(dicCode = "dklx")
	private Integer ywzl;
	/**账号/卡号*/
	@Excel(name = "账号/卡号", width = 15)
    @ApiModelProperty(value = "账号/卡号")
	private String dkzh;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date khrq;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**登记日期*/
	@Excel(name = "登记日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "登记日期")
	private Date djrq;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private Integer htje;
	/**用信余额*/
	@Excel(name = "用信余额", width = 15)
    @ApiModelProperty(value = "用信余额")
	private Integer yxye;


}

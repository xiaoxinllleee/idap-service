package org.cmms.modules.ckjkpt.ckjkptdqckdqyj.entity;

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
 * @Description: 存款到期预警
 * @Author: cmms
 * @Date:   2019-10-29
 * @Version: V1.0
 */
@Data
@TableName("v_ckjkpt_ckdqyj")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_ckjkpt_ckdqyj对象", description="存款到期预警")
public class VckjkptCkdqyj {
	/**机构代码*/
	@Excel(name = "机构代码", width = 15,dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	@ApiModelProperty(value = "机构代码")
	@Dict(dicCode="ywjgdm",dictTable="hr_bas_organization",dicText="zzjc")
	private String jgdm;
	/**存款账号*/
	@Excel(name = "存款账号", width = 15)
	@ApiModelProperty(value = "存款账号")
	private String ckzh;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
	@ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
	@ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**联系方式*/
	/*@Excel(name = "手机号码", width = 15)
	@ApiModelProperty(value = "手机号码")
	private String lxfs;*/
	/**地址*/
	/*@Excel(name = "地址", width = 15)
	@ApiModelProperty(value = "地址")
	private String dz;*/
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
	@ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**累计余额*/
//	@Excel(name = "累计余额", width = 15)
    @ApiModelProperty(value = "累计余额")
	private java.math.BigDecimal ljye;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15)
	@ApiModelProperty(value = "开户日期")
	private String khrq;
	/**定期最大存期*/
	@Excel(name = "定期最大存期", width = 15)
	@ApiModelProperty(value = "定期最大存期")
	private String dqzdcq;
	/**本期起息日*/
	@Excel(name = "本期起息日", width = 15)
	@ApiModelProperty(value = "本期起息日")
	private String bqqxr;
	/**本期截至日*/
	@Excel(name = "本期截至日", width = 15)
	@ApiModelProperty(value = "本期截至日")
	private String bqjzr;
	/**到期日期*/
	@Excel(name = "到期日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日期")
	private Date dqrq;
	/**剩余天数*/
	@Excel(name = "剩余天数", width = 15)
	@ApiModelProperty(value = "剩余天数")
	private Integer syts;
	/**账户处理标示*/
    @ApiModelProperty(value = "账户处理标示")
	private String zhclbs;
}

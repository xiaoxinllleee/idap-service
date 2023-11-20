package org.cmms.modules.khgl.sh.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 手机银行
 * @Author: jeecg-boot
 * @Date:   2020-10-24
 * @Version: V1.0
 */
@Data
@TableName("SHYWXX_SJYH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SHYWXX_SJYH对象", description="手机银行")
public class Sjyh {

	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
	private String mobile;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**本月交易金额*/
	@Excel(name = "本月交易金额", width = 15)
    @ApiModelProperty(value = "本月交易金额")
	private java.math.BigDecimal jyjeBy;
	/**本月交易笔数*/
	@Excel(name = "本月交易笔数", width = 15)
    @ApiModelProperty(value = "本月交易笔数")
	private Long jybsBy;
	/**本年交易金额*/
	@Excel(name = "本年交易金额", width = 15)
    @ApiModelProperty(value = "本年交易金额")
	private java.math.BigDecimal jyjeBn;
	/**本年交易笔数*/
	@Excel(name = "本年交易笔数", width = 15)
    @ApiModelProperty(value = "本年交易笔数")
	private Long jybsBn;
	/**历史交易金额*/
	@Excel(name = "历史交易金额", width = 15)
    @ApiModelProperty(value = "历史交易金额")
	private java.math.BigDecimal jyjeLs;
	/**历史交易笔数*/
	@Excel(name = "历史交易笔数", width = 15)
    @ApiModelProperty(value = "历史交易笔数")
	private Long jybsLs;
	/**开户日期*/
	@Excel(name = "开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "开户日期")
	private Date openDate;
	/**开户机构*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String openJgdm;
	/**开户柜员*/
	@Excel(name = "开户柜员", width = 15)
    @ApiModelProperty(value = "开户柜员")
	private String openGyh;
	/**开户类型(1-客户端;2-贴芯)*/
	@Excel(name = "开户类型(1-客户端;2-贴芯)", width = 15)
    @ApiModelProperty(value = "开户类型(1-客户端;2-贴芯)")
	private String openType;
	/**开销户状态(1-销户;2-开户)*/
	@Excel(name = "开销户状态(1-销户;2-开户)", width = 15)
    @ApiModelProperty(value = "开销户状态(1-销户;2-开户)")
	private String status;
	/**销户日期*/
	@Excel(name = "销户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "销户日期")
	private Date cancelDate;
	/**销户机构*/
	@Excel(name = "销户机构", width = 15)
    @ApiModelProperty(value = "销户机构")
	private String cancelJgdm;
	/**销户柜员*/
	@Excel(name = "销户柜员", width = 15)
    @ApiModelProperty(value = "销户柜员")
	private String cancelGyh;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**开户机构名称*/
	@Excel(name = "开户机构名称", width = 15)
    @ApiModelProperty(value = "开户机构名称")
	private String openJgmc;
	/**销户机构名称*/
	@Excel(name = "销户机构名称", width = 15)
    @ApiModelProperty(value = "销户机构名称")
	private String cancelJgmc;
}

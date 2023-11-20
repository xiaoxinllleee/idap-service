package org.cmms.modules.tjbb.ckywfx.lscx.sdlywzjls.entity;

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
 * @Description: 收单类业务资金流水
 * @Author: jeecg-boot
 * @Date:   2021-08-20
 * @Version: V1.0
 */
@Data
@TableName("TJBB_CKYW_SDLYWZJLS")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJBB_CKYW_SDLYWZJLS对象", description="收单类业务资金流水")
public class Sdlywzjls {

	/**开始日期*/
	@Excel(name = "开始日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "开始日期")
	private Date ksrq;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "结束日期")
	private Date jsrq;

	/**开户行行号*/
	@Excel(name = "收款人开户行行号", width = 15)
    @ApiModelProperty(value = "收款人开户行行号")
	private String khhh;
	/**账号*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String acctNo;
	/**账号名称*/
	@Excel(name = "账号名称", width = 15)
    @ApiModelProperty(value = "账号名称")
	private String acctName;
	/**交易日期*/
	@Excel(name = "交易日期", width = 15)
    @ApiModelProperty(value = "交易日期")
	private String jyrq;
	/**交易时间*/
	@Excel(name = "交易时间", width = 15)
    @ApiModelProperty(value = "交易时间")
	private String jysj;
	/**交易金额*/
	@Excel(name = "交易金额", width = 15)
    @ApiModelProperty(value = "交易金额")
	private java.math.BigDecimal jyje;
	/**币种*/
	@Excel(name = "币种", width = 15)
    @ApiModelProperty(value = "币种")
	private String bz;
	/**当前账户金额*/
	@Excel(name = "当前账户金额", width = 15)
    @ApiModelProperty(value = "当前账户金额")
	private java.math.BigDecimal dqzhje;
	/**流水号*/
	@Excel(name = "流水号", width = 15)
    @ApiModelProperty(value = "流水号")
	private String lsh;
	/**借贷标志*/
	@Excel(name = "借贷标志", width = 15)
    @ApiModelProperty(value = "借贷标志")
	private String jdbz;
	/**付款人姓名*/
	@Excel(name = "付款人姓名", width = 15)
    @ApiModelProperty(value = "付款人姓名")
	private String fkrxm;
	/**付款人账号*/
	@Excel(name = "付款人账号", width = 15)
    @ApiModelProperty(value = "付款人账号")
	private String fkrzh;
	/**付款人开户行名*/
	@Excel(name = "付款人开户行名", width = 15)
    @ApiModelProperty(value = "付款人开户行名")
	private String fkrkhhm;
	/**附言*/
	@Excel(name = "附言", width = 15)
    @ApiModelProperty(value = "附言")
	private String fy;
	/**录入标识*/
    @ApiModelProperty(value = "录入标识")
	@Dict(dicCode = "lrbz")
	private String lrbz;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

package org.cmms.modules.report.tzsjgl.nydkffdjb.entity;

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
 * @Description: 宁远贷款发放登记簿
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_NY_DKFFDJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_NY_DKFFDJB对象", description="宁远贷款发放登记簿")
public class Nydkffdjb {
	/**数据日期*/
	//@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**开户几个*/
	@Excel(name = "开户几个", width = 15)
    @ApiModelProperty(value = "开户几个")
	private String khjg;
	/**贷款户名*/
	@Excel(name = "贷款户名", width = 15)
    @ApiModelProperty(value = "贷款户名")
	private String dkhm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**便民卡卡号*/
	@Excel(name = "便民卡卡号", width = 15)
    @ApiModelProperty(value = "便民卡卡号")
	private String bmkkh;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
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
	/**利率*/
	@Excel(name = "利率", width = 15)
    @ApiModelProperty(value = "利率")
	private java.math.BigDecimal ll;
	/**放款账号*/
	@Excel(name = "放款账号", width = 15)
    @ApiModelProperty(value = "放款账号")
	private String fkzh;
	/**发放金额（远）*/
	@Excel(name = "发放金额（远）", width = 15)
    @ApiModelProperty(value = "发放金额（远）")
	private java.math.BigDecimal ffje;
	/**第一责任人*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**主客户经理*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**放款渠道*/
	@Excel(name = "放款渠道", width = 15)
    @ApiModelProperty(value = "放款渠道")
	private String fkqd;
	/**贷款投向*/
	@Excel(name = "贷款投向", width = 15)
    @ApiModelProperty(value = "贷款投向")
	private String dktx;
	/**贷款投向1*/
	@Excel(name = "贷款投向1", width = 15)
    @ApiModelProperty(value = "贷款投向1")
	private String dktx1;
	/**贷款投向2*/
	@Excel(name = "贷款投向2", width = 15)
    @ApiModelProperty(value = "贷款投向2")
	private String dktx2;
}

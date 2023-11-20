package org.cmms.modules.tjfx.tjfxJkhtdjb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 借款合同登记簿
 * @Author: Penghr
 * @Date:   2022-12-29
 * @Version: V1.0
 */
@Data
@TableName("erp_tjfx_jkhtdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="erp_tjfx_jkhtdjb对象", description="借款合同登记簿")
public class TjfxJkhtdjbVO {

	/**序号*/
	@Excel(name = "序号", width = 15)
	@ApiModelProperty(value = "序号")
	private String xh;
	/**签约机构*/
	@Excel(name = "签约机构", width = 15)
	@ApiModelProperty(value = "签约机构")
	private String qyjg;
	/**客户类型*/
	@Excel(name = "客户类型", width = 15)
	@ApiModelProperty(value = "客户类型")
	private String khlx;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**客户号*/
	@Excel(name = "客户号", width = 15)
    @ApiModelProperty(value = "客户号")
	private String khh;
	/**业务品种*/
	@Excel(name = "业务品种", width = 15)
    @ApiModelProperty(value = "业务品种")
	private String ywpz;
	/**合同编号*/
	@Excel(name = "合同编号", width = 15)
    @ApiModelProperty(value = "合同编号")
	private String htbh;
	/**合同类型*/
	@Excel(name = "合同类型", width = 15)
    @ApiModelProperty(value = "合同类型")
	private String htlx;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**已使用额度*/
	@Excel(name = "已使用额度", width = 15)
    @ApiModelProperty(value = "已使用额度")
	private java.math.BigDecimal ysyed;
	/**可用额度*/
	@Excel(name = "可用额度", width = 15)
    @ApiModelProperty(value = "可用额度")
	private java.math.BigDecimal kyed;
	/**合同起始日*/
	@Excel(name = "合同起始日", width = 15)
    @ApiModelProperty(value = "合同起始日")
	private String htqsr;
	/**合同到期日*/
	@Excel(name = "合同到期日", width = 15)
    @ApiModelProperty(value = "合同到期日")
	private String htdqr;
	/**签约日期*/
	@Excel(name = "签约日期", width = 15)
    @ApiModelProperty(value = "签约日期")
	private String qyrq;
	/**终止日期*/
	@Excel(name = "终止日期", width = 15)
    @ApiModelProperty(value = "终止日期")
	private String zzrq;
	/**还款周期*/
	@Excel(name = "还款周期", width = 15)
    @ApiModelProperty(value = "还款周期")
	private String hkzq;
	/**还款方式*/
	@Excel(name = "还款方式", width = 15)
    @ApiModelProperty(value = "还款方式")
	private String hkfs;
	/**签约利率*/
	@Excel(name = "签约利率", width = 15)
    @ApiModelProperty(value = "签约利率")
	private java.math.BigDecimal qyll;
	/**担保方式*/
	@Excel(name = "担保方式", width = 15)
    @ApiModelProperty(value = "担保方式")
	private String dbfs;
	/**抵押/质押/保证合同编号*/
	@Excel(name = "抵押/质押/保证合同编号", width = 15)
    @ApiModelProperty(value = "抵押/质押/保证合同编号")
	private String dyzybzhtbh;
	/**他项权证号码*/
	@Excel(name = "他项权证号码", width = 15)
    @ApiModelProperty(value = "他项权证号码")
	private String xqzhmt;
	/**借款原因*/
	@Excel(name = "借款原因", width = 15)
    @ApiModelProperty(value = "借款原因")
	private String jkyy;
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;

}

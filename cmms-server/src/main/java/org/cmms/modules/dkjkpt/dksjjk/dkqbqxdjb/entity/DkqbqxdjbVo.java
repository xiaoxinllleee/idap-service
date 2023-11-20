package org.cmms.modules.dkjkpt.dksjjk.dkqbqxdjb.entity;

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
 * @Description: 贷款欠本欠息登记簿
 * @Author: jeecg-boot
 * @Date:   2023-08-28
 * @Version: V1.0
 */
@Data
@TableName("Rep_tzgl_dkqbqxdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Rep_tzgl_dkqbqxdjb对象", description="贷款欠本欠息登记簿")
public class DkqbqxdjbVo {
	/**khjg*/
	@Excel(name = "开户机构", width = 15)
    @ApiModelProperty(value = "开户机构")
	private String khjg;
	/**hm*/
	@Excel(name = "户名", width = 15)
    @ApiModelProperty(value = "户名")
	private String hm;
	/**zjhm*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**zh*/
	@Excel(name = "账号", width = 15)
    @ApiModelProperty(value = "账号")
	private String zh;
	/**zhjyr*/
	@Excel(name = "最后交易日", width = 15)
    @ApiModelProperty(value = "最后交易日")
	private String zhjyr;
	/**dkye*/
	@Excel(name = "贷款金额（元）", width = 15)
    @ApiModelProperty(value = "贷款金额（元）")
	private java.math.BigDecimal dkye;
	/**qs*/
	@Excel(name = "期数", width = 15)
    @ApiModelProperty(value = "期数")
	private Integer qs;
	/**jqbj*/
	@Excel(name = "结欠本金（元）", width = 15)
    @ApiModelProperty(value = "结欠本金（元）")
	private java.math.BigDecimal jqbj;
	/**jqlx*/
	@Excel(name = "结欠利息（元）", width = 15)
    @ApiModelProperty(value = "结欠利息（元）")
	private java.math.BigDecimal jqlx;
	/**jqfx*/
	@Excel(name = "结欠罚息（元）", width = 15)
    @ApiModelProperty(value = "结欠罚息（元）")
	private java.math.BigDecimal jqfx;
	/**dyzrr*/
	@Excel(name = "第一责任人", width = 15)
    @ApiModelProperty(value = "第一责任人")
	private String dyzrr;
	/**zkhjl*/
	@Excel(name = "主客户经理", width = 15)
    @ApiModelProperty(value = "主客户经理")
	private String zkhjl;
	/**dklx*/
	@Excel(name = "贷款类型", width = 15)
    @ApiModelProperty(value = "贷款类型")
	private String dklx;
	/**jxqsr*/
	@Excel(name = "计息起始日", width = 15)
    @ApiModelProperty(value = "计息起始日")
	private String jxqsr;
	/**jxzzr*/
	@Excel(name = "计息终止日", width = 15)
    @ApiModelProperty(value = "计息终止日")
	private String jxzzr;
}

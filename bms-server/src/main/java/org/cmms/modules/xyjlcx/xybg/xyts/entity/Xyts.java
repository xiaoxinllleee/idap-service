package org.cmms.modules.xyjlcx.xybg.xyts.entity;

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
 * @Description: 信用提示
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Data
@TableName("credit_xyts")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="credit_xyts对象", description="信用提示")
public class Xyts {

	/**信用提示项目*/
	@Excel(name = "信用提示项目", width = 15)
    @ApiModelProperty(value = "信用提示项目")
	@Dict(dicCode = "xytsxm")
	private java.lang.String xytsxm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String zjhm;
	/**信贷往来机构数*/
	@Excel(name = "信贷往来机构数", width = 15)
    @ApiModelProperty(value = "信贷往来机构数")
	private java.lang.Integer xdwljgs;
	/**历史业务次数*/
	@Excel(name = "历史业务次数", width = 15)
    @ApiModelProperty(value = "历史业务次数")
	private java.lang.Integer lsywcslj;
	/**存量笔数*/
	@Excel(name = "存量笔数", width = 15)
    @ApiModelProperty(value = "存量笔数")
	private java.lang.Integer clbs;
	/**存量首笔开户日期*/
	@Excel(name = "存量首笔开户日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "存量首笔开户日期")
	private java.util.Date clsbkhrq;
	/**合同金额*/
	@Excel(name = "合同金额", width = 15)
    @ApiModelProperty(value = "合同金额")
	private java.math.BigDecimal htje;
	/**用信余额*/
	@Excel(name = "用信余额", width = 15)
    @ApiModelProperty(value = "用信余额")
	private java.math.BigDecimal yxye;
	/**本金逾期次数*/
	@Excel(name = "本金逾期次数", width = 15)
    @ApiModelProperty(value = "本金逾期次数")
	private java.lang.Integer bjyqcs;
	/**利息逾期次数*/
	@Excel(name = "利息逾期次数", width = 15)
    @ApiModelProperty(value = "利息逾期次数")
	private java.lang.Integer lxyqcs;
	/**单月最高欠息*/
	@Excel(name = "单月最高欠息", width = 15)
    @ApiModelProperty(value = "单月最高欠息")
	private java.math.BigDecimal dyzgqxze;
	/**特殊交易次数*/
	@Excel(name = "特殊交易次数", width = 15)
    @ApiModelProperty(value = "特殊交易次数")
	private java.lang.Integer tsjycs;
}

package org.cmms.modules.xyjlcx.xybg.qxmx.entity;

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
 * @Description: 欠息明细
 * @Author: jeecg-boot
 * @Date:   2022-04-24
 * @Version: V1.0
 */
@Data
@TableName("credit_qxmx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="credit_qxmx对象", description="欠息明细")
public class Qxmx {

	/**逾期月份*/
	@Excel(name = "逾期月份", width = 15)
    @ApiModelProperty(value = "逾期月份")
	private java.lang.String yqyf;
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private java.lang.String brNo;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private java.lang.String identNo;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private java.lang.String acctNo;
	/**逾期利息*/
	@Excel(name = "逾期利息", width = 15)
    @ApiModelProperty(value = "逾期利息")
	private java.math.BigDecimal yqlx;
	/**逾期本金*/
	@Excel(name = "逾期本金", width = 15)
    @ApiModelProperty(value = "逾期本金")
	private java.math.BigDecimal yqbj;
	/**利息逾期次数*/
	@Excel(name = "利息逾期次数", width = 15)
    @ApiModelProperty(value = "利息逾期次数")
	private java.lang.Integer lxyqcs;
	/**本金逾期次数*/
	@Excel(name = "本金逾期次数", width = 15)
    @ApiModelProperty(value = "本金逾期次数")
	private java.lang.Integer bjyqcs;
}

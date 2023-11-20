package org.cmms.modules.workplace.entity;

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
 * @Description: 工作台-存贷款数据汇总-客户经理
 * @Author: Penghr
 * @Date:   2020-09-12
 * @Version: V1.0
 */
@Data
@TableName("WORKPLACE_CDKKHSJHZ_KHJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="WORKPLACE_CDKKHSJHZ_KHJL对象", description="工作台-存贷款数据汇总-客户经理")
public class WorkplaceCdkkhsjhzKhjl {
    
	/**客户经理*/
	@Excel(name = "客户经理", width = 15)
    @ApiModelProperty(value = "客户经理")
	private String khjl;
	/**存款余额*/
	@Excel(name = "存款余额", width = 15)
    @ApiModelProperty(value = "存款余额")
	private java.math.BigDecimal ckye;
	/**其中定期存款余额*/
	@Excel(name = "其中定期存款余额", width = 15)
    @ApiModelProperty(value = "其中定期存款余额")
	private java.math.BigDecimal qzdqckye;
	/**当年存款日平*/
	@Excel(name = "当年存款日平", width = 15)
    @ApiModelProperty(value = "当年存款日平")
	private java.math.BigDecimal dnckrp;
	/**存款客户数*/
	@Excel(name = "存款客户数", width = 15)
    @ApiModelProperty(value = "存款客户数")
	private Integer ckkhs;
	/**贷款余额*/
	@Excel(name = "贷款余额", width = 15)
    @ApiModelProperty(value = "贷款余额")
	private java.math.BigDecimal dkye;
	/**表内不良贷款余额*/
	@Excel(name = "表内不良贷款余额", width = 15)
    @ApiModelProperty(value = "表内不良贷款余额")
	private java.math.BigDecimal bnbldkye;
	/**表外不良贷款余额*/
	@Excel(name = "表外不良贷款余额", width = 15)
    @ApiModelProperty(value = "表外不良贷款余额")
	private java.math.BigDecimal bwbldkye;
	/**贷款客户数*/
	@Excel(name = "贷款客户数", width = 15)
    @ApiModelProperty(value = "贷款客户数")
	private Integer dkkhs;
}

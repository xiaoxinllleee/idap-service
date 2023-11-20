package org.cmms.modules.report.sgtzgl.zfjsywtjbs.entity;

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
 * @Description: 1104-手工台帐-支付结算业务统计表四
 * @Author: jeecg-boot
 * @Date:   2023-11-17
 * @Version: V1.0
 */
@Data
@TableName("ADS_REP_SGTZ_ZFJSYWTJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ADS_REP_SGTZ_ZFJSYWTJB对象", description="1104-手工台帐-支付结算业务统计表四")
public class AdsRepSgtzZfjsywtjbs {
    
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**项目*/
	@Excel(name = "项目", width = 15)
    @ApiModelProperty(value = "项目")
	private String xm;
	/**代收笔数*/
	@Excel(name = "代收笔数", width = 15)
    @ApiModelProperty(value = "代收笔数")
	private Integer dsbs;
	/**代收金额*/
	@Excel(name = "代收金额", width = 15)
    @ApiModelProperty(value = "代收金额")
	private java.math.BigDecimal dsje;
	/**代付笔数*/
	@Excel(name = "代付笔数", width = 15)
    @ApiModelProperty(value = "代付笔数")
	private Integer dfbs;
	/**代付金额*/
	@Excel(name = "代付金额", width = 15)
    @ApiModelProperty(value = "代付金额")
	private java.math.BigDecimal dfje;
}

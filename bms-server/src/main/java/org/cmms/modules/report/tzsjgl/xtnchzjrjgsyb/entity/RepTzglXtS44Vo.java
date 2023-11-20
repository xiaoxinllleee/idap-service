package org.cmms.modules.report.tzsjgl.xtnchzjrjgsyb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 湘潭-S44农村合作金融机构损益表
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_S44")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_S44对象", description="湘潭-S44农村合作金融机构损益表")
public class RepTzglXtS44Vo {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
	@ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**上年同期数*/
	@Excel(name = "上年同期数", width = 15)
    @ApiModelProperty(value = "上年同期数 ")
	private java.math.BigDecimal sntqs;
	/**本期累计数*/
	@Excel(name = "本期累计数", width = 15)
    @ApiModelProperty(value = "本期累计数 ")
	private java.math.BigDecimal bqljs;
}

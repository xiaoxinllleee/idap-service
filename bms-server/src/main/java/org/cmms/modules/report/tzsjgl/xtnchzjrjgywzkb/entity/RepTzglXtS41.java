package org.cmms.modules.report.tzsjgl.xtnchzjrjgywzkb.entity;

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
 * @Description: 湘潭-S41农村合作金融机构业务状况表
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Data
@TableName("REP_TZGL_XT_S41")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="REP_TZGL_XT_S41对象", description="湘潭-S41农村合作金融机构业务状况表")
public class RepTzglXtS41 {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	private String xmdh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**上期末借方余额*/
	@Excel(name = "上期末借方余额", width = 15)
    @ApiModelProperty(value = "上期末借方余额")
	private java.math.BigDecimal sqmjfye;
	/**上期末贷方余额*/
	@Excel(name = "上期末贷方余额", width = 15)
    @ApiModelProperty(value = "上期末贷方余额 ")
	private java.math.BigDecimal sqmdfye;
	/**本期借方发生额*/
	@Excel(name = "本期借方发生额", width = 15)
    @ApiModelProperty(value = "本期借方发生额 ")
	private java.math.BigDecimal bqjffse;
	/**本期贷方发生额*/
	@Excel(name = "本期贷方发生额", width = 15)
    @ApiModelProperty(value = "本期贷方发生额")
	private java.math.BigDecimal bqdffse;
	/**本期末借方余额*/
	@Excel(name = "本期末借方余额", width = 15)
    @ApiModelProperty(value = "本期末借方余额 ")
	private java.math.BigDecimal bqmjfye;
	/**本期末贷方余额*/
	@Excel(name = "本期末贷方余额", width = 15)
    @ApiModelProperty(value = "本期末贷方余额 ")
	private java.math.BigDecimal bqmdfye;
}

package org.cmms.modules.report.sgtzgl.cwbbqkmbRmb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @Description: 财务报表全科目表-人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cwqkmb_rmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwqkmb_rmb对象", description="财务报表全科目表-人民币")
public class SgtzCwqkmbRmbVO {



	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	private String xmdh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**期初借方余额*/
	@Excel(name = "期初借方余额", width = 15)
    @ApiModelProperty(value = "期初借方余额")
	private java.math.BigDecimal qcjfye;
	/**期初贷方余额*/
	@Excel(name = "期初贷方余额", width = 15)
    @ApiModelProperty(value = "期初贷方余额")
	private java.math.BigDecimal qcdfye;
	/**本期借方发生额*/
	@Excel(name = "本期借方发生额", width = 15)
    @ApiModelProperty(value = "本期借方发生额")
	private java.math.BigDecimal bqjffse;
	/**本期贷方发生额*/
	@Excel(name = "本期贷方发生额", width = 15)
    @ApiModelProperty(value = "本期贷方发生额")
	private java.math.BigDecimal bqdffse;
	/**期末借方余额*/
	@Excel(name = "期末借方余额", width = 15)
    @ApiModelProperty(value = "期末借方余额")
	private java.math.BigDecimal qmjfye;
	/**期末贷方余额*/
	@Excel(name = "期末贷方余额", width = 15)
    @ApiModelProperty(value = "期末贷方余额")
	private java.math.BigDecimal qmdfye;
	/**项目代号*/
	@Excel(name = "项目代号", width = 15)
    @ApiModelProperty(value = "项目代号")
	private String xmdh1;

}

package org.cmms.modules.report.sgtzgl.cwbbbwkmbWzrmb.entity;

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
import org.jeecgframework.poi.excel.annotation.ExcelVerify;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 财务报表表外科目表-外折人民币
 * @Author: jeecg-boot
 * @Date:   2022-10-28
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cwbwkmb_wzrmb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cwbwkmb_wzrmb对象", description="财务报表表外科目表-外折人民币")
public class SgtzcwbwkmbWzrmb {

	/**唯一标识*/
//	@TableId(type = IdType.ASSIGN_ID)
//    @ApiModelProperty(value = "唯一标识")
//	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String sjrq;
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
	//@ExcelVerify(interHandler = true)
	private String xmdh1;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;


}
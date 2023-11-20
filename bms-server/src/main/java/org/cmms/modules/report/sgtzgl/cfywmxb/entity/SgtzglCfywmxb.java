package org.cmms.modules.report.sgtzgl.cfywmxb.entity;

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
 * @Description: 手工台账-存放业务明细表
 * @Author: jeecg-boot
 * @Date:   2023-08-07
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cfywzmxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cfywzmxb对象", description="手工台账-存放业务明细表")
public class SgtzglCfywmxb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15)
    @ApiModelProperty(value = "数据日期")
	private String fiscalDate;
	/**科目*/
	@Excel(name = "科目", width = 15)
    @ApiModelProperty(value = "科目")
	private String km;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**账面余额*/
	@Excel(name = "账面余额", width = 15)
    @ApiModelProperty(value = "账面余额")
	private java.math.BigDecimal zmye;
	/**交易对手*/
	@Excel(name = "交易对手", width = 15)
    @ApiModelProperty(value = "交易对手")
	private String jyds;
	/**交易对手监管评级*/
	@Excel(name = "交易对手监管评级", width = 15)
    @ApiModelProperty(value = "交易对手监管评级")
	private String jydsjgpj;
	/**期限(天)*/
	@Excel(name = "期限(天)", width = 15)
    @ApiModelProperty(value = "期限(天)")
	private String qx;
	/**到期日*/
	@Excel(name = "到期日", width = 15,format ="yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "到期日")
	private Date dqr;
	/**资产五级分类情况*/
	@Excel(name = "资产五级分类情况", width = 15)
    @ApiModelProperty(value = "资产五级分类情况")
	private String wjfl;
	/**减值准备*/
	@Excel(name = "减值准备", width = 15)
    @ApiModelProperty(value = "减值准备")
	private java.math.BigDecimal jzzb;
	/**最终审批人*/
	@Excel(name = "最终审批人", width = 15)
    @ApiModelProperty(value = "最终审批人")
	private String zzspr;
	/**本行风险评估结果*/
	@Excel(name = "本行风险评估结果", width = 15)
    @ApiModelProperty(value = "本行风险评估结果")
	private String bhfxpgjg;
}

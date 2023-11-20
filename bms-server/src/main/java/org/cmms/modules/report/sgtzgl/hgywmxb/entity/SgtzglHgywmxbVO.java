package org.cmms.modules.report.sgtzgl.hgywmxb.entity;

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
 * @Description: 手工台账-回购业务明细表
 * @Author: jeecg-boot
 * @Date:   2023-08-07
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_hgywmxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_hgywmxb对象", description="手工台账-回购业务明细表")
public class SgtzglHgywmxbVO {


	/**科目*/
	@Excel(name = "科目", width = 15)
    @ApiModelProperty(value = "科目")
	private String km;
	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**发生额*/
	@Excel(name = "发生额", width = 15)
    @ApiModelProperty(value = "发生额")
	private java.math.BigDecimal fse;
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
	private java.math.BigDecimal qx;
	/**到期日*/
	@Excel(name = "到期日", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期日")
	private Date dqr;
	/**具体质押标*/
	@Excel(name = "具体质押标", width = 15)
    @ApiModelProperty(value = "具体质押标")
	private String jtzyb;
	/**质押标的金额*/
	@Excel(name = "质押标的金额", width = 15)
    @ApiModelProperty(value = "质押标的金额")
	private String zybdje;
	/**质押标的折扣率*/
	@Excel(name = "质押标的折扣率", width = 15)
    @ApiModelProperty(value = "质押标的折扣率")
	private String zybdzkl;
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
	/**其他风险*/
	@Excel(name = "其他风险", width = 15)
    @ApiModelProperty(value = "其他风险")
	private String qtfx;
}

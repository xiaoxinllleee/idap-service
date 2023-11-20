package org.cmms.modules.report.sgtzgl.tztycdcyqkb.entity;

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
 * @Description: 手工台账-投资同业存单持有情况表
 * @Author: jeecg-boot
 * @Date:   2023-08-07
 * @Version: V1.0
 */
@Data
@TableName("ads_rep_sgtz_cyzdqdjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ads_rep_sgtz_cyzdqdjb对象", description="手工台账-投资同业存单持有情况表")
public class SgtzglTztycdcyqkbVO {


	/**序号*/
	@Excel(name = "序号", width = 15)
    @ApiModelProperty(value = "序号")
	private String xh;
	/**银行名称*/
	@Excel(name = "银行名称", width = 15)
    @ApiModelProperty(value = "银行名称")
	private String yhmc;
	/**自主/委外投资*/
	@Excel(name = "自主/委外投资", width = 15)
    @ApiModelProperty(value = "自主/委外投资")
	private String zztz;
	/**受托机构名称*/
	@Excel(name = "受托机构名称", width = 15)
    @ApiModelProperty(value = "受托机构名称")
	private String stjgmc;
	/**产品名称*/
	@Excel(name = "产品名称", width = 15)
    @ApiModelProperty(value = "产品名称")
	private String cpmc;
	/**发行人*/
	@Excel(name = "发行人", width = 15)
    @ApiModelProperty(value = "发行人")
	private String fxr;
	/**交易对手*/
	@Excel(name = "交易对手", width = 15)
    @ApiModelProperty(value = "交易对手")
	private String jyds;
	/**发行金额(万元)*/
	@Excel(name = "发行金额(万元)", width = 15)
    @ApiModelProperty(value = "发行金额(万元)")
	private java.math.BigDecimal fxje;
	/**投资金额(万元)*/
	@Excel(name = "投资金额(万元)", width = 15)
    @ApiModelProperty(value = "投资金额(万元)")
	private java.math.BigDecimal tzje;
	/**账面余额(万元)*/
	@Excel(name = "账面余额(万元)", width = 15)
    @ApiModelProperty(value = "账面余额(万元)")
	private java.math.BigDecimal zmye;
	/**期限(天)*/
	@Excel(name = "期限(天)", width = 15)
    @ApiModelProperty(value = "期限(天)")
	private java.math.BigDecimal qx;
	@Excel(name = "到期", width = 15,format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "到期")
	private Date dq;
	/**五级分类*/
	@Excel(name = "五级分类", width = 15)
    @ApiModelProperty(value = "五级分类")
	private String wjfl;
	/**减值准备*/
	@Excel(name = "减值准备", width = 15)
    @ApiModelProperty(value = "减值准备")
	private java.math.BigDecimal jzzb;
	/**预期收益率%*/
	@Excel(name = "预期收益率%", width = 15)
    @ApiModelProperty(value = "预期收益率%")
	private java.math.BigDecimal yqsyl;
	/**最终审批人*/
	@Excel(name = "最终审批人", width = 15)
    @ApiModelProperty(value = "最终审批人")
	private String zzspr;
	/**本行风险评估结果*/
	@Excel(name = "本行风险评估结果", width = 15)
    @ApiModelProperty(value = "本行风险评估结果")
	private String bhfxpgjg;
}

package org.cmms.modules.khlc.khfagl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 按量计酬考核设置
 * @Author: jeecg-boot
 * @Date:   2023-02-23
 * @Version: V1.0
 */
@Data
@TableName("ERP_ASSESS_ALJC")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ERP_ASSESS_ALJC对象", description="按量计酬考核设置")
public class ErpAssessAljcExportJGVO {


	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
    @ApiModelProperty(value = "方案名称")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	private String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	private String zzbz;
	@Excel(name = "指标ID", width = 15)
	@TableField(exist = false)
	private java.lang.String zbmc;

	public String getZbmc() {
		return zbid;
	}
	/**指标ID*/
	@Excel(name = "指标名称", width = 15,dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
    @ApiModelProperty(value = "指标名称")
	@Dict(dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	private String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
    @ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	private String zbwd;
	/**指标单价*/
	@Excel(name = "指标单价", width = 15, numFormat = "0.######")
    @ApiModelProperty(value = "指标单价")
	private java.math.BigDecimal zbdj;
	/**指标单位*/
	@Excel(name = "指标单位", width = 15)
    @ApiModelProperty(value = "指标单位")
	private java.math.BigDecimal zbdw;
	/**指标权重*/
	@Excel(name = "指标权重", width = 15)
    @ApiModelProperty(value = "指标权重")
	private java.math.BigDecimal zbqz;
	/**调节系数*/
	@Excel(name = "调节系数", width = 15)
    @ApiModelProperty(value = "调节系数")
	private java.math.BigDecimal tjxs;
	/**任务外单价*/
	@Excel(name = "任务外单价", width = 15, numFormat = "0.######")
    @ApiModelProperty(value = "任务外单价")
	private java.math.BigDecimal rwwdj;

	/**创建人*/
	@Excel(name = "创建人", width = 15)
	@ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
	@ApiModelProperty(value = "更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "更新时间")
	private java.util.Date updateTime;
}

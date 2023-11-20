package org.cmms.modules.khlc.khfagl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.cmms.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

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
public class ErpAssessAljcJGVO {


	/**方案ID*/
	@Excel(name = "考核项目", width = 15, dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
    @ApiModelProperty(value = "方案名称")
	@Dict(dicCode = "SCHEME_ID",dictTable = "V_PMA_A_SCHEME",dicText="SHOW_NAME")
	@ExcelVerify(notNull = true)
	private String schemeId;
	/**组织标识*/
	@Excel(name = "组织名称", width = 15,dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
    @ApiModelProperty(value = "组织名称")
	@Dict(dicCode = "zzbz",dictTable = "hr_bas_organization",dicText = "zzjc")
	@ExcelVerify(notNull = true)
	private String zzbz;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	@Dict(dicCode = "zbid", dictTable = "ERP_BAS_ZBK", dicText = "zbmc")
	@ExcelVerify(notNull = true)
	private String zbid;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15,dicCode = "zbwd")
    @ApiModelProperty(value = "指标维度")
	@Dict(dicCode = "zbwd")
	@ExcelVerify(notNull = true)
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
}

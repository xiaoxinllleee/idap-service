package org.cmms.modules.jylrhs.csgl.dxfyjz.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 经营利润核算（单项费用记账）
 * @Author: jeecg-boot
 * @Date:   2023-08-21
 * @Version: V1.0
 */
@Data
@TableName("jylrhs_dxfyjz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="jylrhs_dxfyjz对象", description="经营利润核算（单项费用记账）")
public class JylrhsDxfyjz {

	/**记账ID*/
	@Excel(name = "记账ID", width = 15)
    @ApiModelProperty(value = "记账ID")
	private String jzid;
	/**业务机构*/
	@Excel(name = "业务机构", width = 15, dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "业务机构")
	@Dict(dicCode = "ywjgdm", dictTable = "hr_bas_organization", dicText = "zzjc")
	private String jgdm;
	/**记账日期*/
	@Excel(name = "记账日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "记账日期")
	private Date jzrq;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
	private String zy;
	/**金额*/
	@Excel(name = "金额", width = 15)
    @ApiModelProperty(value = "金额")
	private java.math.BigDecimal je;
	/**列账子目（6601科目下辖二级科目）*/
	@Excel(name = "列账子目", width = 15, dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs")
    @ApiModelProperty(value = "列账子目（6601科目下辖二级科目）")
	@Dict(dicCode = "subject_no2", dictTable = "jylrhs_kmsz", dicText = "subject_name2", ds = "jylrhs")
	private String lzzm;
	/**操作类型*/
	@Excel(name = "操作类型", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "操作类型")
	@Dict(dicCode = "lrbz")
	private String oprationType;
	/**操作员*/
	@Excel(name = "操作员", width = 15)
    @ApiModelProperty(value = "操作员")
	private String operator;
	/**操作时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date oprationTime;
}

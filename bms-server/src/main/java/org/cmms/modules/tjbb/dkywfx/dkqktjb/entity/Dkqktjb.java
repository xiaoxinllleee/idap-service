package org.cmms.modules.tjbb.dkywfx.dkqktjb.entity;

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
 * @Description: 贷款情况统计表(二）
 * @Author: Penghr
 * @Date:   2022-12-13
 * @Version: V1.0
 */
@Data
@TableName("tjbb_xdb_dkqktjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_xdb_dkqktjb对象", description="贷款情况统计表(二）")
public class Dkqktjb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**本期贷款余额*/
	@Excel(name = "本期余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "本期贷款余额")
	private java.math.BigDecimal bqdkye;
	/**今年增减额：比年初（+，-）*/
	@Excel(name = "今年增减额：比年初（+，-）", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "今年增减额：比年初（+，-）")
	private java.math.BigDecimal jnzjeBnc;
	/**今年增减额：比上月（+，-）*/
	@Excel(name = "今年增减额：比上月（+，-）", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "今年增减额：比上月（+，-）")
	private java.math.BigDecimal jnzjeBsy;
	/**比上年同期增减额*/
	@Excel(name = "比上年同期增减额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "比上年同期增减额")
	private java.math.BigDecimal bsntqzje;
	/**增长率*/
	@Excel(name = "增长率", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "增长率")
	private java.math.BigDecimal zzl;
	/**全年任务*/
	@Excel(name = "全年任务", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "全年任务")
	private java.math.BigDecimal qnrw;
	/**完成比例*/
	@Excel(name = "完成比例", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "完成比例")
	private java.math.BigDecimal wcbl;
	/**贷款余额：排名*/
	@Excel(name = "排名", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "贷款余额：排名")
	private Integer dkyePm;
	/**其中：农业贷款余额*/
	@Excel(name = "其中：农业贷款余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "其中：农业贷款余额")
	private java.math.BigDecimal nydkye;
	/**其中：非农业贷款余额*/
	@Excel(name = "其中：非农业贷款余额", width = 15, groupName = "贷款余额")
    @ApiModelProperty(value = "其中：非农业贷款余额")
	private java.math.BigDecimal fnydkye;
	/**贷款累放*/
	@Excel(name = "贷款累放", width = 15)
    @ApiModelProperty(value = "贷款累放")
	private java.math.BigDecimal dklf;
	/**贷款累收*/
	@Excel(name = "贷款累收", width = 15)
    @ApiModelProperty(value = "贷款累收")
	private java.math.BigDecimal djls;
	/**应收金额*/
	@Excel(name = "应收金额", width = 15, groupName = "贷款到期收回率")
    @ApiModelProperty(value = "应收金额")
	private java.math.BigDecimal ysje;
	/**实收金额*/
	@Excel(name = "实收金额", width = 15, groupName = "贷款到期收回率")
    @ApiModelProperty(value = "实收金额")
	private java.math.BigDecimal ssje;
	/**下欠余额*/
	@Excel(name = "下欠余额", width = 15, groupName = "贷款到期收回率")
    @ApiModelProperty(value = "下欠余额")
	private java.math.BigDecimal xqye;
	/**收回率*/
	@Excel(name = "收回率", width = 15, groupName = "贷款到期收回率")
    @ApiModelProperty(value = "收回率")
	private java.math.BigDecimal shl;
	/**贷款到期收回率：排名*/
	@Excel(name = "排名", width = 15, groupName = "贷款到期收回率")
    @ApiModelProperty(value = "贷款到期收回率：排名")
	private Integer dkdqshlPm;
	/**录入标识(0 导入 1 录入 2 修改)*/
//	@Excel(name = "录入标识(0 导入 1 录入 2 修改)", width = 15)
    @ApiModelProperty(value = "录入标识(0 导入 1 录入 2 修改)")
	@Dict(dicCode = "lrbz")
	private Integer lrbz;
	/**录入人*/
//	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
//	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

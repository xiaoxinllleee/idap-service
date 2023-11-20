package org.cmms.modules.tjbb.dkywfx.bldkrwwcqktjb.entity;

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
 * @Description: 不良贷款任务完成情况统计表
 * @Author: Penghr
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("tjbb_fxb_bldkrwwcqktjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_fxb_bldkrwwcqktjb对象", description="不良贷款任务完成情况统计表")
public class Bldkrwwcqktjb {

	/**数据截止日期*/
	@Excel(name = "数据截止日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据截止日期")
	private Date dataDate;
	/**机构编码*/
	@Excel(name = "机构名称", width = 15, dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
    @ApiModelProperty(value = "机构编码")
	@Dict(dicCode = "ywjgdm", dictTable = "v_hr_bas_organization", dicText = "zzjc")
	private String jgbm;
	/**各项贷款合计：余额*/
	@Excel(name = "余额", width = 15, groupName = "各项贷款合计")
	@ApiModelProperty(value = "各项贷款合计：余额")
	private java.math.BigDecimal gxdkhjYe;
	/**各项贷款合计：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "各项贷款合计")
	@ApiModelProperty(value = "各项贷款合计：今年增减额：比上月")
	private java.math.BigDecimal gxdkhjJnzjeBsy;
	/**各项贷款合计：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "各项贷款合计")
	@ApiModelProperty(value = "各项贷款合计：今年增减额：比年初")
	private java.math.BigDecimal gxdkhjJnzjeBnc;
	/**正常关注类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "正常关注类贷款")
    @ApiModelProperty(value = "正常关注类贷款：余额")
	private java.math.BigDecimal zcgzDkye;
	/**正常关注类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "正常关注类贷款")
    @ApiModelProperty(value = "正常关注类贷款：今年增减额：比上月")
	private java.math.BigDecimal zcgzJnzjeBsy;
	/**正常关注类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "正常关注类贷款")
    @ApiModelProperty(value = "正常关注类贷款：今年增减额：比年初")
	private java.math.BigDecimal zcgzJnzjeBnc;
	/**不良类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "不良类贷款")
    @ApiModelProperty(value = "不良类贷款：余额")
	private java.math.BigDecimal blDkye;
	/**不良类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "不良类贷款")
    @ApiModelProperty(value = "不良类贷款：今年增减额：比上月")
	private java.math.BigDecimal blJnzjeBsy;
	/**不良类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "不良类贷款")
    @ApiModelProperty(value = "不良类贷款：今年增减额：比年初")
	private java.math.BigDecimal blJnzjeBnc;
	/**年初不良率*/
	@Excel(name = "年初不良率", width = 15)
    @ApiModelProperty(value = "年初不良率")
	private java.math.BigDecimal ncbll;
	/**本月不良率*/
	@Excel(name = "本月不良率", width = 15)
    @ApiModelProperty(value = "本月不良率")
	private java.math.BigDecimal bybll;
	/**比年初占比（+，-）*/
	@Excel(name = "比年初占比（+，-）", width = 15)
    @ApiModelProperty(value = "比年初占比（+，-）")
	private java.math.BigDecimal bnczb;
	/**排名*/
	@Excel(name = "排名", width = 15)
    @ApiModelProperty(value = "排名")
	private Integer pm;
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

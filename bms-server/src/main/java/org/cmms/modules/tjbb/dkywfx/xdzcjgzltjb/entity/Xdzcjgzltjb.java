package org.cmms.modules.tjbb.dkywfx.xdzcjgzltjb.entity;

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
 * @Description: 信贷资产结构质量统计表（一）
 * @Author: Penghr
 * @Date:   2022-12-14
 * @Version: V1.0
 */
@Data
@TableName("tjbb_fxb_xdzcjgzltjb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_fxb_xdzcjgzltjb对象", description="信贷资产结构质量统计表（一）")
public class Xdzcjgzltjb {

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
	/**正常类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "正常类贷款")
    @ApiModelProperty(value = "正常类贷款：余额")
	private java.math.BigDecimal zcDkye;
	/**正常类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "正常类贷款")
    @ApiModelProperty(value = "正常类贷款：今年增减额：比上月")
	private java.math.BigDecimal zcJnzjeBsy;
	/**正常类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "正常类贷款")
    @ApiModelProperty(value = "正常类贷款：今年增减额：比年初")
	private java.math.BigDecimal zcJnzjeBnc;
	/**关注类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "关注类贷款")
    @ApiModelProperty(value = "关注类贷款：余额")
	private java.math.BigDecimal gzDkye;
	/**关注类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "关注类贷款")
    @ApiModelProperty(value = "关注类贷款：今年增减额：比上月")
	private java.math.BigDecimal gzJnzjeBsy;
	/**关注类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "关注类贷款")
    @ApiModelProperty(value = "关注类贷款：今年增减额：比年初")
	private java.math.BigDecimal gzJnzjeBnc;
	/**不良贷款小计：余额*/
	@Excel(name = "余额", width = 15, groupName = "不良贷款小计")
    @ApiModelProperty(value = "不良贷款小计：余额")
	private java.math.BigDecimal bldkxjYe;
	/**不良贷款小计：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "不良贷款小计")
    @ApiModelProperty(value = "不良贷款小计：今年增减额：比上月")
	private java.math.BigDecimal bldkxjJnzjeBsy;
	/**不良贷款小计：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "不良贷款小计")
    @ApiModelProperty(value = "不良贷款小计：今年增减额：比年初")
	private java.math.BigDecimal bldkxjJnzjeBnc;
	/**不良贷款小计：不良率*/
	@Excel(name = "不良率", width = 15, groupName = "不良贷款小计")
    @ApiModelProperty(value = "不良贷款小计：不良率")
	private java.math.BigDecimal bldkxjBll;
	/**次级类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "次级类贷款")
    @ApiModelProperty(value = "次级类贷款：余额")
	private java.math.BigDecimal cjDkye;
	/**次级类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "次级类贷款")
    @ApiModelProperty(value = "次级类贷款：今年增减额：比上月")
	private java.math.BigDecimal cjJnzjeBsy;
	/**次级类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "次级类贷款")
    @ApiModelProperty(value = "次级类贷款：今年增减额：比年初")
	private java.math.BigDecimal cjJnzjeBnc;
	/**可疑类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "可疑类贷款")
    @ApiModelProperty(value = "可疑类贷款：余额")
	private java.math.BigDecimal kyDkye;
	/**可疑类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "可疑类贷款")
    @ApiModelProperty(value = "可疑类贷款：今年增减额：比上月")
	private java.math.BigDecimal kyJnzjeBsy;
	/**可疑类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "可疑类贷款")
    @ApiModelProperty(value = "可疑类贷款：今年增减额：比年初")
	private java.math.BigDecimal kyJnzjeBnc;
	/**损失类贷款：余额*/
	@Excel(name = "余额", width = 15, groupName = "损失类贷款")
    @ApiModelProperty(value = "损失类贷款：余额")
	private java.math.BigDecimal ssDkye;
	/**损失类贷款：今年增减额：比上月*/
	@Excel(name = "今年增减额：比上月", width = 15, groupName = "损失类贷款")
    @ApiModelProperty(value = "损失类贷款：今年增减额：比上月")
	private java.math.BigDecimal ssJnzjeBsy;
	/**损失类贷款：今年增减额：比年初*/
	@Excel(name = "今年增减额：比年初", width = 15, groupName = "损失类贷款")
    @ApiModelProperty(value = "损失类贷款：今年增减额：比年初")
	private java.math.BigDecimal ssJnzjeBnc;
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

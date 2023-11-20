package org.cmms.modules.hr.xsgl.gwkhsz.entity;

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
 * @Description: 岗位考核设置
 * @Author: jeecg-boot
 * @Date:   2021-10-28
 * @Version: V1.0
 */
@Data
@TableName("PMA_GWKHSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PMA_GWKHSZ对象", description="岗位考核设置")
public class PmaGwkhsz {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**指标id*/
	@Excel(name = "指标id", width = 15)
    @ApiModelProperty(value = "指标id")
	@Dict(dicCode = "index_id", dictTable = "V_ALL_INDEX_INFO", dicText = "index_name")
	private String indexId;
	/**方案id*/
	@Excel(name = "方案id", width = 15)
    @ApiModelProperty(value = "方案id")
	@Dict(dicCode = "scheme_id", dictTable = "PMA_A_SCHEME", dicText = "scheme_name")
	private String schemeId;
	/**指标单价*/
	@Excel(name = "指标单价", width = 15)
    @ApiModelProperty(value = "指标单价")
	private java.math.BigDecimal zbdj;
	/**指标单位  1=元/万元  2=万/户 3=元/笔  4=元/张 5=元/台*/
	@Excel(name = "指标单位  1=元/万元  2=万/户 3=元/笔  4=元/张 5=元/台", width = 15)
    @ApiModelProperty(value = "指标单位  1=元/万元  2=万/户 3=元/笔  4=元/张 5=元/台")
	@Dict(dicCode = "zbdw")
	private String zbdw;
	/**调节系数*/
	@Excel(name = "调节系数", width = 15)
    @ApiModelProperty(value = "调节系数")
	private java.math.BigDecimal tjxs;
	/**指标权重*/
	@Excel(name = "指标权重", width = 15)
    @ApiModelProperty(value = "指标权重")
	private java.math.BigDecimal zbqz;
	/**任务外单价*/
	@Excel(name = "任务外单价", width = 15)
    @ApiModelProperty(value = "任务外单价")
	private java.math.BigDecimal rwwdj;
	/**1平衡计分卡 2 贡献率 3 按量计酬*/
	@Excel(name = "1平衡计分卡 2 贡献率 3 按量计酬", width = 15)
    @ApiModelProperty(value = "1平衡计分卡 2 贡献率 3 按量计酬")
	private String khfs;
	/**d天/m月/q季/y年*/
	@Excel(name = "d天/m月/q季/y年", width = 15)
    @ApiModelProperty(value = "d天/m月/q季/y年")
	private String khwd;
	private String zzbz;
}

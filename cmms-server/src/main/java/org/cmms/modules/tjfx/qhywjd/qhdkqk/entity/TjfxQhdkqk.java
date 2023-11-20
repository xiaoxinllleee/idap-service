package org.cmms.modules.tjfx.qhywjd.qhdkqk.entity;

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
 * @Description: 全行贷款情况
 * @Author: jeecg-boot
 * @Date:   2023-08-16
 * @Version: V1.0
 */
@Data
@TableName("TJFX_QHDKQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_QHDKQK对象", description="全行贷款情况")
public class TjfxQhdkqk {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String sszh;
	/**当期贷款总额*/
	@Excel(name = "当期贷款总额", width = 15)
    @ApiModelProperty(value = "当期贷款总额")
	private java.math.BigDecimal dqdkze;
	/**月初贷款总额*/
	@Excel(name = "月初贷款总额", width = 15)
    @ApiModelProperty(value = "月初贷款总额")
	private java.math.BigDecimal ycdkze;
	/**较月初净增减*/
	@Excel(name = "较月初净增减", width = 15)
    @ApiModelProperty(value = "较月初净增减")
	private java.math.BigDecimal jycjzj;
	/**年初贷款总额*/
	@Excel(name = "年初贷款总额", width = 15)
    @ApiModelProperty(value = "年初贷款总额")
	private java.math.BigDecimal ncdkze;
	/**较年初净增减*/
	@Excel(name = "较年初净增减", width = 15)
    @ApiModelProperty(value = "较年初净增减")
	private java.math.BigDecimal jncjzj;
	/**关注贷款-当前余额*/
	@Excel(name = "当前余额", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-当前余额")
	private java.math.BigDecimal gzdkDqye;
	/**关注贷款-月初余额*/
	@Excel(name = "月初余额", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-月初余额")
	private java.math.BigDecimal gzdkYcye;
	/**关注贷款-比月初净增减*/
	@Excel(name = "比月初净增减", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-比月初净增减")
	private java.math.BigDecimal gzdkBycjzj;
	/**关注贷款-年初余额*/
	@Excel(name = "年初余额", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-年初余额")
	private java.math.BigDecimal gzdkNcye;
	/**关注贷款-比年初净增减*/
	@Excel(name = "比年初净增减", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-比年初净增减")
	private java.math.BigDecimal gzdkBncjzj;
	/**关注贷款-占比*/
	@Excel(name = "占比(%)", width = 15,groupName = "关注贷款")
    @ApiModelProperty(value = "关注贷款-占比")
	private java.math.BigDecimal gzdkZb;
	/**不良贷款-次级贷款-当前余额*/
	@Excel(name = "当前余额", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-当前余额")
	private java.math.BigDecimal bldkCjdkDqye;
	/**不良贷款-次级贷款-月初余额*/
	@Excel(name = "月初余额", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-月初余额")
	private java.math.BigDecimal bldkCjdkYcye;
	/**不良贷款-次级贷款-比月初净增减*/
	@Excel(name = "比月初净增减", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-比月初净增减")
	private java.math.BigDecimal bldkCjdkBycjzj;
	/**不良贷款-次级贷款-年初余额*/
	@Excel(name = "年初余额", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-年初余额")
	private java.math.BigDecimal bldkCjdkNcye;
	/**不良贷款-次级贷款-比年初净增减*/
	@Excel(name = "比年初净增减", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-比年初净增减")
	private java.math.BigDecimal bldkCjdkBncjzj;
	/**不良贷款-次级贷款-占比*/
	@Excel(name = "占比(%)", width = 15,groupName = "次级贷款")
    @ApiModelProperty(value = "不良贷款-次级贷款-占比")
	private java.math.BigDecimal bldkCjdkZb;
	/**不良贷款-可疑贷款-当前余额*/
	@Excel(name = "当前余额", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-当前余额")
	private java.math.BigDecimal bldkKydkDqye;
	/**不良贷款-可疑贷款-月初余额*/
	@Excel(name = "月初余额", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-月初余额")
	private java.math.BigDecimal bldkKydkYcye;
	/**不良贷款-可疑贷款-比月初净增减*/
	@Excel(name = "比月初净增减", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-比月初净增减")
	private java.math.BigDecimal bldkKydkBycjzj;
	/**不良贷款-可疑贷款-年初余额*/
	@Excel(name = "年初余额", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-年初余额")
	private java.math.BigDecimal bldkKydkNcye;
	/**不良贷款-可疑贷款-比年初净增减*/
	@Excel(name = "比年初净增减", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-比年初净增减")
	private java.math.BigDecimal bldkKydkBncjzj;
	/**不良贷款-可疑贷款-占比*/
	@Excel(name = "占比(%)", width = 15,groupName = "可疑贷款")
    @ApiModelProperty(value = "不良贷款-可疑贷款-占比")
	private java.math.BigDecimal bldkKydkZb;
	/**不良贷款-损失贷款-当前余额*/
	@Excel(name = "当前余额", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-当前余额")
	private java.math.BigDecimal bldkSsdkDqye;
	/**不良贷款-损失贷款-月初余额*/
	@Excel(name = "月初余额", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-月初余额")
	private java.math.BigDecimal bldkSsdkYcye;
	/**不良贷款-损失贷款-比月初净增减*/
	@Excel(name = "比月初净增减", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-比月初净增减")
	private java.math.BigDecimal bldkSsdkBycjzj;
	/**不良贷款-损失贷款-年初余额*/
	@Excel(name = "年初余额", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-年初余额")
	private java.math.BigDecimal bldkSsdkNcye;
	/**不良贷款-损失贷款-比年初净增减*/
	@Excel(name = "比年初净增减", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-比年初净增减")
	private java.math.BigDecimal bldkSsdkBncjzj;
	/**不良贷款-损失贷款-占比*/
	@Excel(name = "占比(%)", width = 15,groupName = "损失贷款")
	@ApiModelProperty(value = "不良贷款-损失贷款-占比")
	private java.math.BigDecimal bldkSsdkZb;
	/**贷款到期收回率*/
//	@Excel(name = "贷款到期收回率(%)", width = 15)
    @ApiModelProperty(value = "贷款到期收回率")
	private java.math.BigDecimal dkdqshl;
	/**不良贷款总额*/
	@Excel(name = "不良贷款总额", width = 15)
	@ApiModelProperty(value = "不良贷款总额")
	private java.math.BigDecimal bldkhj;
	/**不良贷款占比*/
	@Excel(name = "不良贷款占比(%)", width = 15)
	@ApiModelProperty(value = "不良贷款占比(%)")
	private java.math.BigDecimal bldkzb;
	/**创建者*/
    @ApiModelProperty(value = "创建者")
	private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
}

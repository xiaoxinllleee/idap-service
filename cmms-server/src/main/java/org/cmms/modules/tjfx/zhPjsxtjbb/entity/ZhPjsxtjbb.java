package org.cmms.modules.tjfx.zhPjsxtjbb.entity;

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
 * @Description: 支行评级授信统计报表
 * @Author: jeecg-boot
 * @Date:   2023-01-09
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_TJFX_ZHPJSXTJBB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_TJFX_ZHPJSXTJBB对象", description="支行评级授信统计报表")
public class ZhPjsxtjbb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date sjrq;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15,dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "ZZBZ", dictTable = "HR_BAS_ORGANIZATION", dicText = "ZZJC")
	private String sszh;
	/**总户数*/
	@Excel(name = "总户数", width = 15)
    @ApiModelProperty(value = "总户数")
	private Integer zhs;
	/**评级授信户数*/
	@Excel(name = "评级授信户数", width = 15)
    @ApiModelProperty(value = "评级授信户数")
	private Integer pjsxhs;
	/**评级授信金额*/
	@Excel(name = "评级授信金额", width = 15)
    @ApiModelProperty(value = "评级授信金额")
	private java.math.BigDecimal pjsxje;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String createBy;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date createTime;
}

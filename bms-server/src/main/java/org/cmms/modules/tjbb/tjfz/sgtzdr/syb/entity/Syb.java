package org.cmms.modules.tjbb.tjfz.sgtzdr.syb.entity;

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
 * @Description: 湖南省农村信用社损益表
 * @Author: Penghr
 * @Date:   2022-12-28
 * @Version: V1.0
 */
@Data
@TableName("tjbb_sgtz_syb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_sgtz_syb对象", description="湖南省农村信用社损益表")
public class Syb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**科目号*/
	@Excel(name = "科目号", width = 15)
    @ApiModelProperty(value = "科目号")
	private String kmh;
	/**项目名称*/
	@Excel(name = "项目名称", width = 15)
    @ApiModelProperty(value = "项目名称")
	private String xmmc;
	/**币种*/
//	@Excel(name = "币种", width = 15)
//    @ApiModelProperty(value = "币种")
//	private String bz;
	/**上年同期数*/
	@Excel(name = "上年同期数", width = 15)
    @ApiModelProperty(value = "上年同期数")
	private java.math.BigDecimal sntqs;
	/**本年累计数*/
	@Excel(name = "本年累计数", width = 15)
    @ApiModelProperty(value = "本年累计数")
	private java.math.BigDecimal bnljs;
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
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

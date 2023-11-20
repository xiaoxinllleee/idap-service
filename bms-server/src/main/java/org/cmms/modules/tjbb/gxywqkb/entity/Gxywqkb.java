package org.cmms.modules.tjbb.gxywqkb.entity;

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
 * @Description: 各项业务情况表
 * @Author: Penghr
 * @Date:   2022-12-12
 * @Version: V1.0
 */
@Data
@TableName("tjbb_xdb_gxywqkb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjbb_xdb_gxywqkb对象", description="各项业务情况表")
public class Gxywqkb {

	/**数据日期*/
	@Excel(name = "数据日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "数据日期")
	private Date dataDate;
	/**序号*/
	@Excel(name = "业务名称", width = 15, dicCode = "ywzbmc")
    @ApiModelProperty(value = "序号")
	@Dict(dicCode = "ywzbmc")
	private Integer xh;
	/**业务名称*/
	//@Excel(name = "业务名称", width = 15)
    @ApiModelProperty(value = "业务名称")
	private String ywmc;
	/**本期余额*/
	@Excel(name = "本期余额", width = 15)
    @ApiModelProperty(value = "本期余额")
	private java.math.BigDecimal bqye;
	/**比昨日*/
	@Excel(name = "比昨日", width = 15)
    @ApiModelProperty(value = "比昨日")
	private java.math.BigDecimal bzr;
	/**比上月*/
	@Excel(name = "比上月", width = 15)
    @ApiModelProperty(value = "比上月")
	private java.math.BigDecimal bsy;
	/**比年初*/
	@Excel(name = "比年初", width = 15)
    @ApiModelProperty(value = "比年初")
	private java.math.BigDecimal bnc;
	/**比上年同期*/
	@Excel(name = "比上年同期", width = 15)
    @ApiModelProperty(value = "比上年同期")
	private java.math.BigDecimal bsntq;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
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

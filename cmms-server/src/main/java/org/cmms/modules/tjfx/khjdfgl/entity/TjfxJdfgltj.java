package org.cmms.modules.tjfx.khjdfgl.entity;

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
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 建档覆盖率统计
 * @Author: cmms
 * @Date:   2019-09-06
 * @Version: V1.0
 */
@Data
@TableName("TJFX_JDFGLTJ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TJFX_JDFGLTJ对象", description="建档覆盖率统计")
public class TjfxJdfgltj {
    
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private String zzbz;
	/**组织名称*/
	@Excel(name = "组织名称", width = 15)
    @ApiModelProperty(value = "组织名称")
	private String zzmc;
	/**营销单元名称*/
	@Excel(name = "营销单元名称", width = 15)
    @ApiModelProperty(value = "营销单元名称")
	private String yxdymc;
	/**建档月份*/
	@Excel(name = "建档月份", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "建档月份")
	private Date jdyf;
	/**月建档人数*/
	@Excel(name = "月建档人数", width = 15)
    @ApiModelProperty(value = "月建档人数")
	private Long yjdrs;
	/**累计建档人数*/
	@Excel(name = "累计建档人数", width = 15)
    @ApiModelProperty(value = "累计建档人数")
	private Long ljjdrs;
	/**单元总人数*/
	@Excel(name = "单元总人数", width = 15)
    @ApiModelProperty(value = "单元总人数")
	private Long dyzrs;
	/**累计建档覆盖率*/
	@Excel(name = "累计建档覆盖率", width = 15)
    @ApiModelProperty(value = "累计建档覆盖率")
	private java.math.BigDecimal ljjdfgl;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}

package org.cmms.modules.gr.mrjbxx.entity;

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
 * @Description: 每日简报信息
 * @Author: jeecg-boot
 * @Date:   2021-05-31
 * @Version: V1.0
 */
@Data
@TableName("tb_tjfx_mrjbxx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tb_tjfx_mrjbxx对象", description="每日简报信息")
public class TbTjfxMrjbxx {
    
	/**组织标识*/
	@Excel(name = "组织标识", width = 15)
    @ApiModelProperty(value = "组织标识")
	private String zzbz;
	/**岗位标识*/
	@Excel(name = "岗位标识", width = 15)
    @ApiModelProperty(value = "岗位标识")
	private Integer gwbz;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**简报ID*/
	@Excel(name = "简报ID", width = 15)
    @ApiModelProperty(value = "简报ID")
	private String jbid;
	/**简报名称*/
	@Excel(name = "简报名称", width = 15)
    @ApiModelProperty(value = "简报名称")
	private String jbmc;
	/**简报结果*/
	@Excel(name = "简报结果", width = 15)
    @ApiModelProperty(value = "简报结果")
	private java.math.BigDecimal jbjg;
	/**简报单位*/
	@Excel(name = "简报单位", width = 15)
    @ApiModelProperty(value = "简报单位")
	private String jbdw;
	/**是否显示*/
	@Excel(name = "是否显示", width = 15)
    @ApiModelProperty(value = "是否显示")
	private Integer sfxs;
	/**显示顺序*/
	@Excel(name = "显示顺序", width = 15)
    @ApiModelProperty(value = "显示顺序")
	private Integer xssx;
	/**显示符号*/
	@Excel(name = "显示符号", width = 15)
    @ApiModelProperty(value = "显示符号")
	private String xsfh;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private Integer lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

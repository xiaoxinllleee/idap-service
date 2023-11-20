package org.cmms.modules.khxxgl.wbsjgl.tjf.grtjf.entity;

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
 * @Description: 个人碳积分详情
 * @Author: jeecg-boot
 * @Date:   2022-11-21
 * @Version: V1.0
 */
@Data
@TableName("v_wbsjgl_grtjf")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_wbsjgl_grtjf对象", description="个人碳积分详情")
public class VWbsjglGrtjs {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**sjrq*/
	@Excel(name = "sjrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sjrq")
	private Date sjrq;
	/**khxm*/
	@Excel(name = "khxm", width = 15)
    @ApiModelProperty(value = "khxm")
	private String khxm;
	/**sfz*/
	@Excel(name = "sfz", width = 15)
    @ApiModelProperty(value = "sfz")
	private String sfz;
	/**lx*/
	@Excel(name = "lx", width = 15,dicCode = "grtjf_lx")
	@ApiModelProperty(value = "lx")
	@Dict(dicCode = "grtjf_lx")
	private String lx;
	/**srfs*/
	@Excel(name = "srfs", width = 15 ,dicCode = "lrbz")
	@ApiModelProperty(value = "srfs")
	@Dict(dicCode = "lrbz")
	private String srfs;
	/**sjly*/
	@Excel(name = "sjly", width = 15)
    @ApiModelProperty(value = "sjly")
	private String sjly;
	/**dsc*/
	@Excel(name = "dsc", width = 15)
    @ApiModelProperty(value = "dsc")
	private Integer dsc;
	/**createTime*/
//	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**createBy*/
//	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	@Dict(dicCode = "yggh", dictTable = "HR_BAS_STAFF", dicText = "ygxm")
	private String createBy;
	/**updateTime*/
//	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**updateBy*/
//	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**jf*/
	@Excel(name = "jf", width = 15)
    @ApiModelProperty(value = "jf")
	private String jf;
}

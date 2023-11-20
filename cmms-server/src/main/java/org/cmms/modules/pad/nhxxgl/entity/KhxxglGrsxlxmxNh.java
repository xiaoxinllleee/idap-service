package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 客户信息管理-个人授信类型明细-农户
 * @Author: jeecg-boot
 * @Date:   2023-03-21
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_GRSXLXMX_NH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_GRSXLXMX_NH对象", description="客户信息管理-个人授信类型明细-农户")
public class KhxxglGrsxlxmxNh {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**hhbm*/
	@Excel(name = "hhbm", width = 15)
    @ApiModelProperty(value = "hhbm")
	private String hhbm;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**sxlx*/
	@Excel(name = "sxlx", width = 15)
    @ApiModelProperty(value = "sxlx")
	private String sxlx;
	/**dkye*/
	@Excel(name = "dkye", width = 15)
    @ApiModelProperty(value = "dkye")
	private java.math.BigDecimal dkye;
	/**sxed*/
	@Excel(name = "sxed", width = 15)
    @ApiModelProperty(value = "sxed")
	private java.math.BigDecimal sxed;
	/**sxksrq*/
	@Excel(name = "sxksrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sxksrq")
	private Date sxksrq;
	/**sxdqrq*/
	@Excel(name = "sxdqrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sxdqrq")
	private Date sxdqrq;
	/**bysxqx*/
	@Excel(name = "bysxqx", width = 15)
    @ApiModelProperty(value = "bysxqx")
	private String bysxqx;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
}

package org.cmms.modules.fxd.entity;

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
 * @Description: 客户经理日志
 * @Author: jeecg-boot
 * @Date:   2023-05-25
 * @Version: V1.0
 */
@Data
@TableName("TASK_KHJLRZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TASK_KHJLRZ对象", description="客户经理日志")
public class TaskKhjlrz {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private String creator;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private String yggh;
	/**zzbz*/
	@Excel(name = "zzbz", width = 15)
    @ApiModelProperty(value = "zzbz")
	private String zzbz;
	/**fxdxm*/
	@Excel(name = "fxdxm", width = 15)
    @ApiModelProperty(value = "fxdxm")
	private String fxdxm;
	/**省*/
	@Excel(name = "省", width = 15)
    @ApiModelProperty(value = "省")
	private String province;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
	private String address;
	/**市*/
	@Excel(name = "市", width = 15)
    @ApiModelProperty(value = "市")
	private String city;
	/**errMsg*/
	@Excel(name = "errMsg", width = 15)
    @ApiModelProperty(value = "errMsg")
	private String errMsg;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
	private String latitude;
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
	private String longitude;
}

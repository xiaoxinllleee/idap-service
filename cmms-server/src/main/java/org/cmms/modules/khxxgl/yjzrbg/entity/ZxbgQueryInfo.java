package org.cmms.modules.khxxgl.yjzrbg.entity;

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
 * @Description: 征信报告查询记录
 * @Author: jeecg-boot
 * @Date:   2023-04-26
 * @Version: V1.0
 */
@Data
@TableName("ZXBG_QUERY_INFO")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZXBG_QUERY_INFO对象", description="征信报告查询记录")
public class ZxbgQueryInfo {
    
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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private String updator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**result*/
	@Excel(name = "result", width = 15)
    @ApiModelProperty(value = "result")
	private String result;
	/**resultReason*/
	@Excel(name = "resultReason", width = 15)
    @ApiModelProperty(value = "resultReason")
	private String resultReason;
	/**fwlj*/
	@Excel(name = "fwlj", width = 15)
    @ApiModelProperty(value = "fwlj")
	private String fwlj;
	/**username*/
	@Excel(name = "username", width = 15)
    @ApiModelProperty(value = "username")
	private String username;
}

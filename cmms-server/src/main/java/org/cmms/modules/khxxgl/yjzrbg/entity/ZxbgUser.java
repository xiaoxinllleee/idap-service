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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 征信报告
 * @Author: jeecg-boot
 * @Date:   2023-04-20
 * @Version: V1.0
 */
@Data
@TableName("ZXBG_USER")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZXBG_USER对象", description="征信报告")
public class ZxbgUser {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**crestor*/
	@Excel(name = "crestor", width = 15)
    @ApiModelProperty(value = "crestor")
	private String crestor;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**username*/
	@Excel(name = "username", width = 15)
    @ApiModelProperty(value = "username")
	private String username;
	/**password*/
	@Excel(name = "password", width = 15)
    @ApiModelProperty(value = "password")
	private String password;
	/**ip*/
	@Excel(name = "ip", width = 15)
    @ApiModelProperty(value = "ip")
	private String ip;

	@Excel(name = "type", width = 15,dicCode = "yjzrbg_yhlx")
	@Dict(dicCode = "yjzrbg_yhlx")
	private String type;
	//错误原因
	private String errorReason;
	//是否查询
	@Dict(dicCode = "sfbz")
	private String sfcx;
}

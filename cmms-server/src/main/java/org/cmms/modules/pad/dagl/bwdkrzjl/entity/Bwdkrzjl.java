package org.cmms.modules.pad.dagl.bwdkrzjl.entity;

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
 * @Description: 表外贷款日志记录
 * @Author: jeecg-boot
 * @Date:   2023-07-20
 * @Version: V1.0
 */
@Data
@TableName("loan_bwdk_rzjl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="loan_bwdk_rzjl对象", description="表外贷款日志记录")
public class Bwdkrzjl {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**贷款账号*/
	@Excel(name = "贷款账号", width = 15)
    @ApiModelProperty(value = "贷款账号")
	private String dkzh;
	/**登录账号*/
	@Excel(name = "登录账号", width = 15)
    @ApiModelProperty(value = "登录账号")
	@Dict(dicCode = "username", dictTable = "sys_user", dicText = "realname")
	private String username;
	/**操作功能*/
	@Excel(name = "操作功能", width = 15)
    @ApiModelProperty(value = "操作功能")
	private String czgn;
	/**操作时间*/
	@Excel(name = "操作时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "操作时间")
	private Date czsj;
	/**操作内容*/
	@Excel(name = "操作内容", width = 15)
    @ApiModelProperty(value = "操作内容")
	private String cznr;
}

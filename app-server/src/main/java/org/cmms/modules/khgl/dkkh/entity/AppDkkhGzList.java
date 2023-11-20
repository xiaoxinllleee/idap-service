package org.cmms.modules.khgl.dkkh.entity;

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
 * @Description: 贷款客户关注列表
 * @Author: jeecg-boot
 * @Date:   2022-03-10
 * @Version: V1.0
 */
@Data
@TableName("APP_DKKH_GZ_LIST")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="APP_DKKH_GZ_LIST对象", description="贷款客户关注列表")
public class AppDkkhGzList {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private String zjhm;
	/**jgdm*/
	@Excel(name = "jgdm", width = 15)
    @ApiModelProperty(value = "jgdm")
	private String jgdm;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private String yggh;
	/**对公 对私*/
	@Excel(name = "对公 对私", width = 15)
    @ApiModelProperty(value = "对公 对私")
	private String custType;

	private String fiveClassType;
	private String khmc;
}

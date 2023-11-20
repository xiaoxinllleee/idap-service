package org.cmms.modules.system.sjbd.entity;

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
 * @Description: 测试脚本
 * @Author: jeecg-boot
 * @Date:   2022-06-22
 * @Version: V1.0
 */
@Data
@TableName("SJXF_CSJB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SJXF_CSJB对象", description="测试脚本")
public class SjxfCsjb {
    
	/**表名*/
	@Excel(name = "表名", width = 15)
    @ApiModelProperty(value = "表名")
	private java.lang.String tablename;
	/**hzfs*/
	@Excel(name = "hzfs", width = 15)
    @ApiModelProperty(value = "hzfs")
	private java.lang.String hzfs;
	/**分组字段*/
	@Excel(name = "分组字段", width = 15)
    @ApiModelProperty(value = "分组字段")
	private java.lang.String fzzd;
	/**汇总字段*/
	@Excel(name = "汇总字段", width = 15)
    @ApiModelProperty(value = "汇总字段")
	private java.lang.String hzzd;
}

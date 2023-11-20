package org.cmms.modules.khgl.nh.entity;

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
 * @Description: 验收详情中间表
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Data
@TableName("CAMS_PLPY_YSXQ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_PLPY_YSXQ对象", description="验收详情中间表")
public class CamsPlpyYsxq {
    
	/**验收id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "验收id")
	private String id;
	/**NHBKBPYid*/
	@Excel(name = "NHBKBPYid", width = 15)
    @ApiModelProperty(value = "NHBKBPYid")
	private String nhbkbpyid;
}

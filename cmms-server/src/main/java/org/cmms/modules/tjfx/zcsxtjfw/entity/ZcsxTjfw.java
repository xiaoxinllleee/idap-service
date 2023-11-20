package org.cmms.modules.tjfx.zcsxtjfw.entity;

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
 * @Description: 整村授信统计范围
 * @Author: jeecg-boot
 * @Date:   2023-05-22
 * @Version: V1.0
 */
@Data
@TableName("tjfx_zcsxjdb_tjfw")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="tjfx_zcsxjdb_tjfw对象", description="整村授信统计范围")
public class ZcsxTjfw {

	/**年份*/
	@Excel(name = "年份", width = 15)
    @ApiModelProperty(value = "年份")
	private String nf;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
    @ApiModelProperty(value = "网格编号")
	private String wgbh;
	/**批次*/
	@Excel(name = "批次", width = 15)
    @ApiModelProperty(value = "批次")
	private String pc;
}

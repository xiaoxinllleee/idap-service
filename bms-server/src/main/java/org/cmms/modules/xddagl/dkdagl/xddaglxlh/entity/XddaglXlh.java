package org.cmms.modules.xddagl.dkdagl.xddaglxlh.entity;

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
 * @Description: 信贷档案管理序列号
 * @Author: jeecg-boot
 * @Date:   2022-01-20
 * @Version: V1.0
 */
@Data
@TableName("XDDAGL_XLH")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="XDDAGL_XLH对象", description="信贷档案管理序列号")
public class XddaglXlh {
    
	/**机构代码*/
	@Excel(name = "机构代码", width = 15)
    @ApiModelProperty(value = "机构代码")
	private String jgdm;
	/**序列号*/
	@Excel(name = "序列号", width = 15)
    @ApiModelProperty(value = "序列号")
	private String xlh;
}

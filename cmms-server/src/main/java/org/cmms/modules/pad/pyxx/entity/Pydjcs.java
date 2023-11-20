package org.cmms.modules.pad.pyxx.entity;

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
 * @Description: 评议得分对应等级额度
 * @Author: jeecg-boot
 * @Date:   2020-07-31
 * @Version: V1.0
 */
@Data
@TableName("CAMS_JBXX_PYDJSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_JBXX_PYDJSZ对象", description="评议得分对应等级额度")
public class Pydjcs {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**dfq*/
	@Excel(name = "dfq", width = 15)
    @ApiModelProperty(value = "dfq")
	private java.math.BigDecimal dfq;
	/**dfz*/
	@Excel(name = "dfz", width = 15)
    @ApiModelProperty(value = "dfz")
	private java.math.BigDecimal dfz;
	/**xydj*/
	@Excel(name = "xydj", width = 15)
    @ApiModelProperty(value = "xydj")
	private String xydj;
	/**jysxed*/
	@Excel(name = "jysxed", width = 15)
    @ApiModelProperty(value = "jysxed")
	private Long jysxed;
}

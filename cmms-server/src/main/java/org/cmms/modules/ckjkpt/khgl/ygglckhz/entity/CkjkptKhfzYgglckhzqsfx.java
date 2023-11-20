package org.cmms.modules.ckjkpt.khgl.ygglckhz.entity;

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
 * @Description: 员工揽储趋势分析
 * @Author: jeecg-boot
 * @Date:   2021-11-02
 * @Version: V1.0
 */
@Data
@TableName("Ckjkpt_khfz_ygglckhzqsfx")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Ckjkpt_khfz_ygglckhzqsfx对象", description="员工揽储趋势分析")
public class CkjkptKhfzYgglckhzqsfx {
    
	/**tjyf*/
	@Excel(name = "tjyf", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "tjyf")
	private java.util.Date tjyf;
	/**zzbz*/
	@Excel(name = "zzbz", width = 15)
    @ApiModelProperty(value = "zzbz")
	private java.lang.String zzbz;
	/**gwbz*/
	@Excel(name = "gwbz", width = 15)
    @ApiModelProperty(value = "gwbz")
	private java.lang.String gwbz;
	/**yggh*/
	@Excel(name = "yggh", width = 15)
    @ApiModelProperty(value = "yggh")
	private java.lang.String yggh;
	/**ckye*/
	@Excel(name = "ckye", width = 15)
    @ApiModelProperty(value = "ckye")
	private java.math.BigDecimal ckye;
}

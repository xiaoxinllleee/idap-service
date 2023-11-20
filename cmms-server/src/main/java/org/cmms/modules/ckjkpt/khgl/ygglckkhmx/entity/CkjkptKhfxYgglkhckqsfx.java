package org.cmms.modules.ckjkpt.khgl.ygglckkhmx.entity;

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
 * @Description: 客户近10日存款余额
 * @Author: jeecg-boot
 * @Date:   2021-11-01
 * @Version: V1.0
 */
@Data
@TableName("CKJKPT_KHFZ_YGGLKHCKQSFX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CKJKPT_KHFZ_YGGLKHCKQSFX对象", description="客户近10日存款余额")
public class CkjkptKhfxYgglkhckqsfx {
    
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
	/**zjhm*/
	@Excel(name = "zjhm", width = 15)
    @ApiModelProperty(value = "zjhm")
	private java.lang.String zjhm;
	/**ckye*/
	@Excel(name = "ckye", width = 15)
    @ApiModelProperty(value = "ckye")
	private java.math.BigDecimal ckye;
}

package org.cmms.modules.bigscreen.entity;

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
 * @Description: 大屏业务汇总视图
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
@Data
@TableName("v_dp_ywhz")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="v_dp_ywhz对象", description="大屏业务汇总视图")
public class VDpYwhz {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**ywid*/
	@Excel(name = "ywid", width = 15)
    @ApiModelProperty(value = "ywid")
	private java.lang.String ywid;
	/**ywmc*/
	@Excel(name = "ywmc", width = 15)
    @ApiModelProperty(value = "ywmc")
	private java.lang.String ywmc;
	/**ywzsmc*/
	@Excel(name = "ywzsmc", width = 15)
    @ApiModelProperty(value = "ywzsmc")
	private java.lang.String ywzsmc;
	/**ywzsmchz*/
	@Excel(name = "ywzsmchz", width = 15)
    @ApiModelProperty(value = "ywzsmchz")
	private java.lang.String ywzsmchz;
	/**ywyjfl*/
	@Excel(name = "ywyjfl", width = 15)
    @ApiModelProperty(value = "ywyjfl")
	private java.lang.String ywyjfl;
	/**ywejfl*/
	@Excel(name = "ywejfl", width = 15)
    @ApiModelProperty(value = "ywejfl")
	private java.lang.String ywejfl;
	/**ywlx*/
	@Excel(name = "ywlx", width = 15)
    @ApiModelProperty(value = "ywlx")
	private java.lang.String ywlx;
	/**ywjg*/
	@Excel(name = "ywjg", width = 15)
    @ApiModelProperty(value = "ywjg")
	private java.lang.String ywjg;
	/**sjrq*/
	@Excel(name = "sjrq", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "sjrq")
	private java.util.Date sjrq;
}

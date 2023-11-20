package org.cmms.modules.pad.shxxgl.entity;

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
 * @Description: 商户采集信息
 * @Author: jeecg-boot
 * @Date:   2020-08-29
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_SHCJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_SHCJXX对象", description="商户采集信息")
public class CamsZcsxShcjxx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**shid*/
	@Excel(name = "shid", width = 15)
    @ApiModelProperty(value = "shid")
	private String shid;
	/**shmc*/
	@Excel(name = "shmc", width = 15)
    @ApiModelProperty(value = "shmc")
	private String shmc;
	/**longitude*/
	@Excel(name = "longitude", width = 15)
    @ApiModelProperty(value = "longitude")
	private String longitude;
	/**latitude*/
	@Excel(name = "latitude", width = 15)
    @ApiModelProperty(value = "latitude")
	private String latitude;
	/**bysxqx*/
	@Excel(name = "bysxqx", width = 15)
    @ApiModelProperty(value = "bysxqx")
	private String bysxqx;
	/**sxdxzjh*/
	@Excel(name = "sxdxzjh", width = 15)
	@ApiModelProperty(value = "sxdxzjh")
	private String sxdxzjh;
	/**sxdxmc*/
	@Excel(name = "sxdxmc", width = 15)
	@ApiModelProperty(value = "sxdxmc")
	private String sxdxmc;
	/**lrbz*/
	@Excel(name = "lrbz", width = 15)
    @ApiModelProperty(value = "lrbz")
	private String lrbz;
	/**lrsj*/
	@Excel(name = "lrsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "lrsj")
	private Date lrsj;
	/**lrr*/
	@Excel(name = "lrr", width = 15)
    @ApiModelProperty(value = "lrr")
	private String lrr;
	/**xgr*/
	@Excel(name = "xgr", width = 15)
    @ApiModelProperty(value = "xgr")
	private String xgr;
	/**xgsj*/
	@Excel(name = "xgsj", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "xgsj")
	private Date xgsj;
}

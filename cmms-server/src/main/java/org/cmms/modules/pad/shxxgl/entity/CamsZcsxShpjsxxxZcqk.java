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
 * @Description: 商户评级授信-资产情况
 * @Author: jeecg-boot
 * @Date:   2023-09-14
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_SHPJSXXX_ZCQK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_SHPJSXXX_ZCQK对象", description="商户评级授信-资产情况")
public class CamsZcsxShpjsxxxZcqk {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private String id;
	/**商户id*/
	@Excel(name = "商户id", width = 15)
    @ApiModelProperty(value = "商户id")
	private String shid;
	/**资产类型*/
	@Excel(name = "资产类型", width = 15)
    @ApiModelProperty(value = "资产类型")
	private String zclx;
	/**资产价值*/
	@Excel(name = "资产价值", width = 15)
    @ApiModelProperty(value = "资产价值")
	private String zcjz;
	/**资产数量*/
	@Excel(name = "资产数量", width = 15)
    @ApiModelProperty(value = "资产数量")
	private String zcsl;
	/**资产说明*/
	@Excel(name = "资产说明", width = 15)
    @ApiModelProperty(value = "资产说明")
	private String zcsm;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateUp;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
	/**排序*/
	@Excel(name = "排序", width = 15)
	@ApiModelProperty(value = "排序")
	private Integer px;
}

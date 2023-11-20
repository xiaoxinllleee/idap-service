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
 * @Description: 大屏指标明细
 * @Author: jeecg-boot
 * @Date:   2023-10-07
 * @Version: V1.0
 */
@Data
@TableName("TYDP_ZBMX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TYDP_ZBMX对象", description="大屏指标明细")
public class TydpZbmx {
    
	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
	private java.lang.String id;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private java.util.Date createTime;
	/**creator*/
	@Excel(name = "creator", width = 15)
    @ApiModelProperty(value = "creator")
	private java.lang.String creator;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private java.util.Date updateTime;
	/**updator*/
	@Excel(name = "updator", width = 15)
    @ApiModelProperty(value = "updator")
	private java.lang.String updator;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private java.lang.String bz;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15)
    @ApiModelProperty(value = "组织标志")
	private java.lang.String zzbz;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	private java.lang.String sszh;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private java.lang.String yggh;
	/**指标id*/
	@Excel(name = "指标id", width = 15)
    @ApiModelProperty(value = "指标id")
	private java.lang.String zbid;
	/**指标结果*/
	@Excel(name = "指标结果", width = 15)
    @ApiModelProperty(value = "指标结果")
	private java.lang.String zbjg;
	/**页面标题*/
	@Excel(name = "页面标题", width = 15)
    @ApiModelProperty(value = "页面标题")
	private java.lang.String ymbt;
	/**指标分类*/
	@Excel(name = "指标分类", width = 15)
    @ApiModelProperty(value = "指标分类")
	private java.lang.String zbfl;
	/**指标2级分类*/
	@Excel(name = "指标2级分类", width = 15)
    @ApiModelProperty(value = "指标2级分类")
	private java.lang.String zbfl2;
}

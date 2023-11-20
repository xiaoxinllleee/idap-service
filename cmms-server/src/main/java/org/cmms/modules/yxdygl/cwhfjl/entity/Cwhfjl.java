package org.cmms.modules.yxdygl.cwhfjl.entity;

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
import org.cmms.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * @Description: 村委回访
 * @Author: jeecg-boot
 * @Date:   2023-06-27
 * @Version: V1.0
 */
@Data
@TableName("YXGL_CWHFJL")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YXGL_CWHFJL对象", description="村委回访")
public class Cwhfjl {

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private java.lang.String id;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15)
	@ApiModelProperty(value = "网格编号")
	@Dict(dicCode="wgbh", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private java.lang.String wgbh;
	/**所属支行*/
	@Excel(name = "所属支行", width = 15)
    @ApiModelProperty(value = "所属支行")
	@Dict(dicCode = "zzbz", dictTable = "HR_BAS_ORGANIZATION", dicText = "zzjc")
	private java.lang.String zzbz;
	/**回访目的*/
	@Excel(name = "回访目的", width = 15)
    @ApiModelProperty(value = "回访目的")
	@Dict(dicCode = "cw_hfmd")
	private java.lang.String hfmd;
	/**目的其他备注*/
	@Excel(name = "目的其他备注", width = 15)
	@ApiModelProperty(value = "目的其他备注")
	private java.lang.String mdqtbz;
	/**回访情况*/
	@Excel(name = "回访情况", width = 15)
    @ApiModelProperty(value = "回访情况")
	private java.lang.String hfqk;
	/**村委评价*/
	@Excel(name = "村委评价", width = 15)
    @ApiModelProperty(value = "村委评价")
	private java.lang.String cwpj;
	/**回访日期*/
	@Excel(name = "回访日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "回访日期")
	private java.util.Date hfrq;
	/**回访人*/
	@Excel(name = "回访人", width = 15)
    @ApiModelProperty(value = "回访人")
	@Dict(dicCode = "yggh", dictTable = "Hr_bas_staff", dicText = "ygxm")
	private java.lang.String hfr;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private java.util.Date createTime;
}

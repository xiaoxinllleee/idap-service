package org.cmms.modules.wggl.jjxxgl.entity;

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
 * @Description: 机具信息管理
 * @Author: jeecg-boot
 * @Date:   2021-12-07
 * @Version: V1.0
 */
@Data
@TableName("wghgl_jjxxgl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="wghgl_jjxxgl对象", description="机具信息管理")
public class Jjxxgl {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**机具编号*/
	@Excel(name = "机具编号", width = 15)
    @ApiModelProperty(value = "机具编号")
	private String jjbh;
	/**机具名称*/
	@Excel(name = "机具名称", width = 15)
    @ApiModelProperty(value = "机具名称")
	private String jjmc;
	/**组织标志*/
	@Excel(name = "组织标志", width = 15, dicCode ="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
    @ApiModelProperty(value = "组织标志")
	@Dict(dicCode ="zzbz",dictTable="hr_bas_organization",dicText="zzjc")
	private String zzbz;
	/**网格编号*/
	@Excel(name = "网格编号", width = 15, dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
    @ApiModelProperty(value = "网格编号")
	@Dict(dicCode ="ID",dictTable="YXDYGL_MAIN",dicText="WGMC")
	private String wgbh;
	/**员工工号*/
	@Excel(name = "员工工号", width = 15)
    @ApiModelProperty(value = "员工工号")
	private String yggh;
	/**机具类型*/
	@Excel(name = "机具类型", width = 15)
    @ApiModelProperty(value = "机具类型")
	private String jjlx;
	/**布放日期*/
	@Excel(name = "布放日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "布放日期")
	private Date bfrq;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
}

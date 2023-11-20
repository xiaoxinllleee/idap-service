package org.cmms.modules.khpjsx.pjsxdjsz.entity;

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
 * @Description: 评级授信等级设置
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("PJSX_KHDJSZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_KHDJSZ对象", description="评级授信等级设置")
public class PjsxKhdjsz {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**等级编号*/
	@Excel(name = "等级编号", width = 15)
    @ApiModelProperty(value = "等级编号")
	private String djbh;
	/**等级名称*/
	@Excel(name = "等级名称", width = 15)
    @ApiModelProperty(value = "等级名称")
	private String djmc;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15, dicCode = "khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "khlx")
    private String khlx;
	/**分数值起*/
	@Excel(name = "分数值起", width = 15)
    @ApiModelProperty(value = "分数值起")
	private java.math.BigDecimal fszbegin;
	/**分数值止*/
	@Excel(name = "分数值止", width = 15)
    @ApiModelProperty(value = "分数值止")
	private java.math.BigDecimal fszend;
	/**等级系数*/
	@Excel(name = "等级系数", width = 15)
    @ApiModelProperty(value = "等级系数")
	private java.math.BigDecimal djxs;
	/**是否启用(1.启用;2.禁用)*/
	/*@Excel(name = "是否启用", width = 15, dicCode = "qybz")*/
    @ApiModelProperty(value = "是否启用")
    @Dict(dicCode = "qybz")
	private String status;
	/**区域代码*/
	/*@Excel(name = "区域代码", width = 15)*/
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**创建人*/
	/*@Excel(name = "创建人", width = 15)*/
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	/*@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**修改人*/
	/*@Excel(name = "修改人", width = 15)*/
    @ApiModelProperty(value = "修改人")
	private String updateBy;
	/**修改时间*/
	/*@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date updateTime;
}

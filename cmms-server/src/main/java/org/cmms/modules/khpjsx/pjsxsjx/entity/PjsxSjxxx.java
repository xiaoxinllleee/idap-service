package org.cmms.modules.khpjsx.pjsxsjx.entity;

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
 * @Description: 评级授信数据项
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("PJSX_SJXXXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_SJXXXB对象", description="评级授信数据项")
public class PjsxSjxxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**数据项ID*/
	@Excel(name = "数据项ID", width = 15)
    @ApiModelProperty(value = "数据项ID")
	private String sjxid;
	/**数据项名称*/
	@Excel(name = "数据项名称", width = 15)
    @ApiModelProperty(value = "数据项名称")
	private String sjxmc;
	/**数据项维度*/
	/*@Excel(name = "数据项维度(天/月/季/年)", width = 15, dicCode = "rqwd")*/
    @ApiModelProperty(value = "数据项维度")
    @Dict(dicCode = "rqwd")
	private String sjxwd;
    /**数据项SQL*/
    @Excel(name = "数据项SQL", width = 15)
    @ApiModelProperty(value = "数据项SQL")
    private String sjxsql;
    /**客户类型*/
    @Excel(name = "客户类型", width = 15, dicCode = "khlx")
    @ApiModelProperty(value = "客户类型")
    @Dict(dicCode = "khlx")
    private String khlx;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
	private Integer zxsx;
    /**数据来源(1.PC端录入;2.平板端录入)*/
    @Excel(name = "数据来源(PC端录入/平板端录入)", width = 15, dicCode = "sjly")
    @ApiModelProperty(value = "数据来源")
    @Dict(dicCode = "sjly")
	private String sjly;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
    /**是否启用(1.启用;2.禁用)*/
    /*@Excel(name = "是否启用", width = 15, dicCode = "qybz")*/
    @ApiModelProperty(value = "是否启用")
    @Dict(dicCode = "qybz")
    private String status;
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

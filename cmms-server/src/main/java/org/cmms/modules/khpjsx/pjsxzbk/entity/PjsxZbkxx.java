package org.cmms.modules.khpjsx.pjsxzbk.entity;

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
 * @Description: 评级授信指标库
 * @Author: jeecg-boot
 * @Date:   2020-01-11
 * @Version: V1.0
 */
@Data
@TableName("PJSX_ZBKXXB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PJSX_ZBKXXB对象", description="评级授信指标库")
public class PjsxZbkxx {

    /**ID*/
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private String id;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
	/**指标维度*/
	@Excel(name = "指标维度", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "指标维度")
    @Dict(dicCode = "rqwd")
	private String zbwd;
	/**授信类别*/
	/*@Excel(name = "授信类别", width = 15)
    @ApiModelProperty(value = "授信类别")
	private String sxlb;*/
	/**评级方式*/
	/*@Excel(name = "评级方式", width = 15)
    @ApiModelProperty(value = "评级方式")
	private String pjfs;*/
    /**是否启用(1.启用;2.禁用)*/
    /*@Excel(name = "是否启用", width = 15, dicCode = "qybz")*/
    @ApiModelProperty(value = "是否启用")
    @Dict(dicCode = "qybz")
    private String status;
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

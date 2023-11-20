package org.cmms.modules.khdj.khdjpdzbk.entity;

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
 * @Description: 客户等级评定指标库
 * @Author: cmms
 * @Date:   2019-10-24
 * @Version: V1.0
 */
@Data
@TableName("KHDJ_DJPDZBK")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHDJ_DJPDZBK对象", description="客户等级评定指标库")
public class KhdjpdZbk {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
    /**指标维度(DD.天/MM.月/Q.季/YYYY.年)*/
    @Excel(name = "指标维度", width = 15, dicCode = "rqwd")
    @ApiModelProperty(value = "指标维度")
    @Dict(dicCode = "rqwd")
    private String zbwd;
	/**指标ID*/
	@Excel(name = "指标ID", width = 15)
    @ApiModelProperty(value = "指标ID")
	private String zbid;
	/**指标名称*/
	@Excel(name = "指标名称", width = 15)
    @ApiModelProperty(value = "指标名称")
	private String zbmc;
    /**版本类型(1.通用/2.自定义)*/
    @Excel(name = "版本类型", width = 15, dicCode = "bblx")
    @ApiModelProperty(value = "版本类型(1.通用/2.自定义)")
    @Dict(dicCode = "bblx")
    private String bblx;
    /**是否启用(1.启用/2.停用)*/
    @Excel(name = "是否启用", width = 15, dicCode = "qybz")
    @ApiModelProperty(value = "是否启用(1.启用/2.停用)")
    @Dict(dicCode = "qybz")
    private String sfqy;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
    /**录入标志(0.导入/1.录入/2.修改)*/
    @Excel(name = "录入标志", width = 15, dicCode = "lrbz")
    @ApiModelProperty(value = "录入标志(0.导入/1.录入/2.修改)")
    @Dict(dicCode = "lrbz")
    private String lrbz;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
}

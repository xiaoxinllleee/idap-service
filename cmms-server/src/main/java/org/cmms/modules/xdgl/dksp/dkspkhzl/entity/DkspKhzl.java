package org.cmms.modules.xdgl.dksp.dkspkhzl.entity;

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
 * @Description: 贷款审批客户资料
 * @Author: jeecg-boot
 * @Date:   2021-12-01
 * @Version: V1.0
 */
@Data
@TableName("cams_dksp_khzl")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="cams_dksp_khzl对象", description="贷款审批客户资料")
public class DkspKhzl {
    
	/**updateTime*/
	@Excel(name = "updateTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "updateTime")
	private Date updateTime;
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**客户ID*/
	@Excel(name = "客户ID", width = 15)
    @ApiModelProperty(value = "客户ID")
	private String khid;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**资料类型*/
	@Excel(name = "资料类型", width = 15)
    @ApiModelProperty(value = "资料类型")
	@Dict(dicCode = "dksp_zllx")
	private String zllx;
	/**资料名称*/
	@Excel(name = "资料名称", width = 15)
    @ApiModelProperty(value = "资料名称")
	private String zlmc;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "上传时间")
	private Date scsj;
	/**上传人*/
	@Excel(name = "上传人", width = 15)
    @ApiModelProperty(value = "上传人")
	private String scr;
	/**资料大小*/
	@Excel(name = "资料大小", width = 15)
    @ApiModelProperty(value = "资料大小")
	private java.math.BigDecimal zldx;
	/**资料路径*/
	@Excel(name = "资料路径", width = 15)
    @ApiModelProperty(value = "资料路径")
	private String zllj;
	/**访问路径*/
	@Excel(name = "访问路径", width = 15)
    @ApiModelProperty(value = "访问路径")
	private String fwlj;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**createBy*/
	@Excel(name = "createBy", width = 15)
    @ApiModelProperty(value = "createBy")
	private String createBy;
	/**createTime*/
	@Excel(name = "createTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "createTime")
	private Date createTime;
	/**updateBy*/
	@Excel(name = "updateBy", width = 15)
    @ApiModelProperty(value = "updateBy")
	private String updateBy;
}

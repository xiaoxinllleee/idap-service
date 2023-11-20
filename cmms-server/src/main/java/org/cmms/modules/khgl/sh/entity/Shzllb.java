package org.cmms.modules.khgl.sh.entity;

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
 * @Description: 商户资料表
 * @Author: jeecg-boot
 * @Date:   2020-10-22
 * @Version: V1.0
 */
@Data
@TableName("CAMS_JBXX_SHZLLB")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_JBXX_SHZLLB对象", description="商户资料表")
public class Shzllb {

	/**资料编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "资料编号")
	private String id;
	/**区域代码*/
	@Excel(name = "区域代码", width = 15)
    @ApiModelProperty(value = "区域代码")
	private String qydm;
	/**商户ID*/
	@Excel(name = "商户ID", width = 15)
    @ApiModelProperty(value = "商户ID")
	private String shid;
	/**商户名称*/
	@Excel(name = "商户名称", width = 15)
    @ApiModelProperty(value = "商户名称")
	private String shmc;
	/**资料类型*/
	@Excel(name = "资料类型", width = 15)
    @ApiModelProperty(value = "资料类型")
	private String zllx;
	/**资料名称*/
	@Excel(name = "资料名称", width = 15)
    @ApiModelProperty(value = "资料名称")
	private String zlmc;
	/**上传时间*/
	@Excel(name = "上传时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
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
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
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

package org.cmms.modules.pad.nhxxgl.entity;

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
 * @Description: 农户佐证资料（待审批）
 * @Author: jeecg-boot
 * @Date:   2023-07-11
 * @Version: V1.0
 */
@Data
@TableName("khgl_nhzzzlxxb")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="khgl_nhzzzlxxb对象", description="农户佐证资料（待审批）")
public class Nhzzzlxxb {

	/**主键ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键ID")
	private String id;
	/**待审核信息ID*/
	@Excel(name = "待审核信息ID", width = 15)
	@ApiModelProperty(value = "待审核信息ID")
	private String shid;
	/**佐证类型（1 户主 2 家庭成员）*/
	@Excel(name = "佐证类型（1 户主 2 家庭成员）", width = 15)
    @ApiModelProperty(value = "佐证类型（1 户主 2 家庭成员）")
	private String zzlx;
	/**区域代码/所属网格*/
	@Excel(name = "区域代码/所属网格", width = 15)
    @ApiModelProperty(value = "区域代码/所属网格")
	private String qydm;
	/**户号编码*/
	@Excel(name = "户号编码", width = 15)
    @ApiModelProperty(value = "户号编码")
	private String hhbm;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**资料类型*/
	@Excel(name = "资料类型", width = 15)
    @ApiModelProperty(value = "资料类型")
	private String zllx;
	/**资料编号*/
	@Excel(name = "资料编号", width = 15)
    @ApiModelProperty(value = "资料编号")
	private String zlbh;
	/**资料名称*/
	@Excel(name = "资料名称", width = 15)
    @ApiModelProperty(value = "资料名称")
	private String zlmc;
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
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
	private String bz;
	/**录入标识*/
	@Excel(name = "录入标识", width = 15)
    @ApiModelProperty(value = "录入标识")
	private String lrbz;
	/**录入人*/
	@Excel(name = "录入人", width = 15)
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**录入时间*/
	@Excel(name = "录入时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
}

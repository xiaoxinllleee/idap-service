package org.cmms.modules.pad.gzryxxgl.entity;

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
 * @Description: 公职人员附件信息
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Data
@TableName("KHXXGL_KHXQ_GZRYFJXX")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_GZRYFJXX对象", description="公职人员附件信息")
public class KhxxglKhxqGzryfjxx {
    
	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
	private String id;
	/**公职人员id**/
	@Excel(name = "公职人员id", width = 15)
	@ApiModelProperty(value = "公职人员id")
	private String gzryid;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**资料类型(1:与客户合影，2：其他附件1，3：其他附件2,4：其他附件3，5：其他附件4，6：其他附件5)*/
	@Excel(name = "资料类型(1:与客户合影，2：其他附件1，3：其他附件2,4：其他附件3，5：其他附件4，6：其他附件5)", width = 15)
    @ApiModelProperty(value = "资料类型(1:与客户合影，2：其他附件1，3：其他附件2,4：其他附件3，5：其他附件4，6：其他附件5)")
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
	/**录入标志*/
	@Excel(name = "录入标志", width = 15)
    @ApiModelProperty(value = "录入标志")
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
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
	private String modifyby;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date modifydate;
}

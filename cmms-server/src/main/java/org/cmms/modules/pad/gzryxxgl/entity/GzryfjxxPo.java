package org.cmms.modules.pad.gzryxxgl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description: 公职人员附件信息
 * @Author: jeecg-boot
 * @Date:   2022-08-15
 * @Version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="KHXXGL_KHXQ_GZRYFJXX对象", description="公职人员附件信息")
public class GzryfjxxPo {

    @ApiModelProperty(value = "ID")
	private String id;
	/**证件号码*/
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**资料类型(1:与客户合影，2：其他附件1，3：其他附件2,4：其他附件3，5：其他附件4，6：其他附件5)*/
    @ApiModelProperty(value = "资料类型(1:与客户合影，2：其他附件1，3：其他附件2,4：其他附件3，5：其他附件4，6：其他附件5)")
	private String zllx;
	/**资料名称*/
	@Excel(name = "资料名称", width = 15)
    @ApiModelProperty(value = "资料名称")
	private String zlmc;
	/**上传时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "上传时间")
	private Date scsj;
	/**上传人*/
    @ApiModelProperty(value = "上传人")
	private String scr;
	/**资料大小*/
    @ApiModelProperty(value = "资料大小")
	private java.math.BigDecimal zldx;
	/**资料路径*/
    @ApiModelProperty(value = "资料路径")
	private String zllj;
	/**访问路径*/
    @ApiModelProperty(value = "访问路径")
	private String fwlj;
	/**录入标志*/
    @ApiModelProperty(value = "录入标志")
	private String lrbz;
	/**录入时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "录入时间")
	private Date lrsj;
	/**录入人*/
    @ApiModelProperty(value = "录入人")
	private String lrr;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
	private String modifyby;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
	private Date modifydate;

	private String gzryId;
}

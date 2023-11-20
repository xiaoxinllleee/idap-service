package org.cmms.modules.xdgl.jtspcy.entity;

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
 * @Description: 集体审批成员
 * @Author: jeecg-boot
 * @Date:   2020-09-14
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_GRXDJTSPXZ")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_GRXDJTSPXZ对象", description="集体审批成员")
public class Jtspcy {

	/**责任人岗位*/
	@Excel(name = "责任人岗位", width = 15)
    @ApiModelProperty(value = "责任人岗位")
	private String zrrgw;
	/**责任人ID*/
	@Excel(name = "责任人ID", width = 15)
    @ApiModelProperty(value = "责任人ID")
	private String zrrid;
	/**责任人岗位ID*/
	@Excel(name = "责任人岗位ID", width = 15)
    @ApiModelProperty(value = "责任人岗位ID")
	@Dict(dicCode = "gwbz", dictTable = "HR_BAS_POST", dicText = "gwmc")
	private String zrrgwid;
	/**表决意见(1-同意,2-否决.3-弃权)*/
	@Excel(name = "表决意见(1-同意,2-否决.3-弃权)", width = 15)
    @ApiModelProperty(value = "表决意见(1-同意,2-否决.3-弃权)")
	private String bjyj;
	/**其他意见*/
	@Excel(name = "其他意见", width = 15)
    @ApiModelProperty(value = "其他意见")
	private String qtyj;
	/**签名图片*/
	@Excel(name = "签名图片", width = 15)
    @ApiModelProperty(value = "签名图片")
	private String qmtp;
	/**签名时间*/
	@Excel(name = "签名时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(value = "签名时间")
	private Date qmsj;
	/**责任人名称*/
	@Excel(name = "责任人名称", width = 15)
    @ApiModelProperty(value = "责任人名称")
	private String zrrmc;

	/**责任人类型*/
	@Excel(name = "责任人类型", width = 15)
	@ApiModelProperty(value = "责任人类型")
	private String zrrlx;
	/**ID*/
	private String id;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
	private String khmc;
	/**证件号码*/
	@Excel(name = "证件号码", width = 15)
    @ApiModelProperty(value = "证件号码")
	private String zjhm;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间 ", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}

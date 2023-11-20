package org.cmms.modules.pad.nhzhsd.entity;

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
 * @Description: 农户支行审定
 * @Author: jeecg-boot
 * @Date:   2023-03-28
 * @Version: V1.0
 */
@Data
@TableName("CAMS_ZCSX_NHZHSD")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CAMS_ZCSX_NHZHSD对象", description="农户支行审定")
public class Nhzhsd {
    
	/**唯一标识*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "唯一标识")
	private String id;
	/**审定网格*/
	@Excel(name = "审定网格", width = 15)
    @ApiModelProperty(value = "审定网格")
	@Dict( dicCode="id", dictTable="v_yxdygl_main", dicText="wgmc_show")
	private String sdwg;
	/**审定支行长*/
	@Excel(name = "审定支行长", width = 15)
    @ApiModelProperty(value = "审定支行长")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String sdzhz;
	/**审定副行长*/
	@Excel(name = "审定副行长", width = 15)
    @ApiModelProperty(value = "审定副行长")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String sdfhz;
	/**审定客户经理*/
	@Excel(name = "审定客户经理", width = 15)
    @ApiModelProperty(value = "审定客户经理")
	@Dict(dicCode = "yggh",dictTable = "hr_bas_staff",dicText = "ygxm")
	private String sdkhjl;
	/**审定支行长电子签名*/
	@Excel(name = "审定支行长电子签名", width = 15)
    @ApiModelProperty(value = "审定支行长电子签名")
	private String sdzhzdzqm;
	/**审定副行长电子签名*/
	@Excel(name = "审定副行长电子签名", width = 15)
    @ApiModelProperty(value = "审定副行长电子签名")
	private String sdfhzdzqm;
	/**审定客户经理电子签名*/
	@Excel(name = "审定客户经理电子签名", width = 15)
    @ApiModelProperty(value = "审定客户经理电子签名")
	private String sdkhjldzqm;
	/**是否完成审定*/
	@Excel(name = "是否完成审定", width = 15)
	@ApiModelProperty(value = "是否完成审定")
	@Dict(dicCode = "sfbz")
	private String sfwcsd;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
	private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
	private String updateBy;
	/**更新时间*/
	@Excel(name = "更新时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
